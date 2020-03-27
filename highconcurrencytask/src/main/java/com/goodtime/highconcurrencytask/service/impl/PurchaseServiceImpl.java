package com.goodtime.highconcurrencytask.service.impl;

import com.goodtime.highconcurrencytask.bean.Product;
import com.goodtime.highconcurrencytask.bean.PurchaseRecord;
import com.goodtime.highconcurrencytask.mapper.ProductMapper;
import com.goodtime.highconcurrencytask.mapper.PurchaseRecordMapper;
import com.goodtime.highconcurrencytask.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author goodtime
 * @create 2020-03-27 5:39 下午
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    @Transactional
    //会发生超卖的服务，在多并发情况下（比如同时发起多个ajax请求，会因为线程数据同步不及时，而导致超卖问题）
    public Boolean purchaseProduct(Integer userId, Integer productId, Integer quantity) {

        Product product = productMapper.getProduct(productId);//根据id得到产品

        if (product != null && product.getStock() < quantity) {//查看产品库存是否小于要客户要抢购的产品数量
            return false;
        }

        productMapper.decreaseProduct(productId, quantity);//减库存

        PurchaseRecord purchaseRecord = wrapperPurchaseRecord(userId, quantity, product);//得到订单记录

        purchaseRecordMapper.insertPurchaseRecord(purchaseRecord);//插入订单记录

        return true;
    }


    @Transactional
    //在java业务层面，用悲观锁解决超卖，相当于锁方法，同一时间，因为spring默认都是单例的，这样一锁，就算有多个访问
    //让tomcat开启多个线程，但serviceimpl类只有一个，而该方法，因为加了synchronized关键字，因此只允许一个线程访问完，
    //其他线程才能访问
    //存在问题，锁的粒度大，而且只能锁本机jvm进程中的线程，如果该接口同时部署在多台电脑上，就会出问题
    public synchronized Boolean purchaseProduct2(Integer userId, Integer productId, Integer quantity) {


        Product product = productMapper.getProduct(productId);//根据id得到产品

        if (product != null && product.getStock() < quantity) {//查看产品库存是否小于要客户要抢购的产品数量
            return false;
        }

        productMapper.decreaseProduct(productId, quantity);//减库存

        PurchaseRecord purchaseRecord = wrapperPurchaseRecord(userId, quantity, product);//得到订单记录

        purchaseRecordMapper.insertPurchaseRecord(purchaseRecord);//插入订单记录

        return true;
    }

    private PurchaseRecord wrapperPurchaseRecord(Integer userId, Integer quantity, Product product) {

        Long purchaseDate = System.currentTimeMillis();

        PurchaseRecord purchaseRecord = new PurchaseRecord();

        purchaseRecord.setUserId(userId);

        purchaseRecord.setQuantity(quantity);

        purchaseRecord.setProductId(product.getProductId());

        purchaseRecord.setPrice(product.getPrice());
        BigDecimal totalPrice = new BigDecimal(quantity.toString());
        totalPrice = totalPrice.multiply(product.getPrice());
        purchaseRecord.setTotalPrice(totalPrice);
        purchaseRecord.setPurchaseDate(new Timestamp(purchaseDate));
        purchaseRecord.setNote("购买日志，时间：" + purchaseDate);
        return purchaseRecord;
    }


    //解决方案2，在productMapper.getProductplus中，进行改造，将mysql的查询，从快照读改为当前读(for update)，也就是对产品那一行加锁
    //也会出现库存剩余，因为有些事务长期等不到锁，于是就回滚了
    @Transactional
    public Boolean purchaseProduct3(Integer userId, Integer productId, Integer quantity) {


        Product product = productMapper.getProductPlus(productId);//根据id得到产品

        if (product != null && product.getStock() < quantity) {//查看产品库存是否小于要客户要抢购的产品数量
            return false;
        }

        productMapper.decreaseProduct(productId, quantity);//减库存

        PurchaseRecord purchaseRecord = wrapperPurchaseRecord(userId, quantity, product);//得到订单记录

        purchaseRecordMapper.insertPurchaseRecord(purchaseRecord);//插入订单记录

        return true;
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)//这里最好改成读已提交，这样可以方便的读到最新的数据，而可重复读，在事务中是读不到其他事务在该事务开始后提交的数据的
    //同时，也能保证version读到的版本较新，增加成功几率
    //解决方案三：数据库加乐观锁，用version列实现版本控制,简单加了个重试机制，否则一旦版本不一致，此次访问时可能还有库存，但是因为并发的原因，更新失败，因此抢不到了
    //后果就是会发生库存剩余
    public Boolean purchaseProduct4(Integer userId, Integer productId, Integer quantity) {


        for (int i = 0; i < 100; i++) {//默认循环200次，也就是执行200次重试，重试次数越多，剩的库存是个玄学...

            Product product = productMapper.getProductPlus(productId);//根据id得到产品

            if (product != null && product.getStock() < quantity) {//查看产品库存是否小于要客户要抢购的产品数量
                return false;
            }

            Integer result = productMapper.decreaseProductPlus(productId, quantity, product.getVersion());//根据version减库存
            if (result == 0) {
                continue;
            }

            PurchaseRecord purchaseRecord = wrapperPurchaseRecord(userId, quantity, product);//得到订单记录

            purchaseRecordMapper.insertPurchaseRecord(purchaseRecord);//插入订单记录

            return true;
        }

        return false;
    }

    @Override
    @Transactional
    //用redis解决库存超卖，利用redis实现synchronized的功能，因为不同机器上的同一个服务，它们用的redis缓存是相同的
    //这里发现一个问题，如果库存卖不完，redis中剩余库存的值，总是比数据库中剩余库存的值大，不知道是哪里配置出了问题，可能是redis中的线程因为等待时间过长，就没有执行-1操作
    public Boolean purchaseProductRedis(Integer userId, Integer productId, Integer quantity) {
        //存的值为"lock"："haveKey"的String，用它来上锁，保证分布式部分情况下，也能实现悲观锁（redis的悲观锁）

        String key = "lock";

        try {
            Boolean haveKey = redisTemplate.opsForValue().setIfAbsent(key, "haveKey");//这个底层用的是redis的setnx命令，该命令为原子命令
            //如果设置成功，会返回ture，设置失败，会返回false
            if (haveKey == false) {
                int stock = Integer.parseInt(redisTemplate.opsForValue().get("" + productId));
                //如果获取锁失败，判断是否还有库存，如果有，等待1秒后继续请求，如果没有，就直接返回false
                if (stock > 0) {
                    return purchaseProductRedis(userId, productId, quantity);
                } else {
                    return false;
                }
            } else {
                //如果获得锁，就可以执行数据库中的命令

                //redis也存一个关于库存的值，方便判断，不用查询数据库，是否还有库存了
                int stock = Integer.parseInt(redisTemplate.opsForValue().get("" + productId));

                if (stock > 0) {
                    redisTemplate.opsForValue().set("" + productId, String.valueOf(stock - 1));//缓存中的库存值-1
                    purchaseProduct(userId, productId, quantity);//真正去连接数据库，进行减库存和保存订单操作
                    return true;
                } else {
                    return false;
                }
            }
        } finally {
            redisTemplate.delete(key);//保证如果程序出错，也能执行解锁操作，让后面的用户可以访问
        }
    }

    @Transactional
    public Boolean purchaseProductRedisPlus(Integer userId, Integer productId, Integer quantity) {
        //存的值为"lock"："haveKey"的String，用它来上锁，保证分布式部分情况下，也能实现悲观锁（redis的悲观锁）

        String key = "lock";
        String randomKey = UUID.randomUUID().toString();

        try {
            Boolean haveKey = redisTemplate.opsForValue().setIfAbsent(key, randomKey, 20, TimeUnit.SECONDS);//这个底层用的是redis的setnx命令，该命令为原子命令
            //如果设置成功，会返回ture，设置失败，会返回false
            if (haveKey == false) {
                int stock = Integer.parseInt(redisTemplate.opsForValue().get("" + productId));
                //如果获取锁失败，判断是否还有库存，如果有，继续请求，如果没有，就直接返回false
                if (stock > 0) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return purchaseProductRedisPlus(userId, productId, quantity);//这种重试机制记得要加return
                } else {
                    return false;
                }
            } else {
                //如果获得锁，就可以执行数据库中的命令

                //redis也存一个关于库存的值，方便判断，不用查询数据库，是否还有库存了
                int stock = Integer.parseInt(redisTemplate.opsForValue().get("" + productId));

                if (stock > 0) {
                    redisTemplate.opsForValue().set("" + productId, String.valueOf(stock - 1));//缓存中的库存值-1
                    purchaseProduct(userId, productId, quantity);//真正去连接数据库，进行减库存和保存订单操作
                    return true;
                } else {
                    return false;
                }
            }
        } finally {
            boolean equals = randomKey.equals(redisTemplate.opsForValue().get(key));
            System.out.println("看看真假" + equals);
            if (randomKey.equals(redisTemplate.opsForValue().get(key))) {//验证是否是该线程上的锁，不要乱释放
                redisTemplate.delete(key);//保证如果程序出错，也能执行解锁操作，让后面的用户可以访问
            }
        }

    }


    //用redis的watch和事务，实现redis乐观锁解决超卖问题
    public Boolean purchaseProductRedisPlus2(Integer userId, Integer productId, Integer quantity) {

        String stock = redisTemplate.opsForValue().get(productId + "");
        int stock2 = Integer.parseInt(stock);//得到当前库存
        if (stock2 > 0) {
            //它必须放在setEnableTransactionSupport(true)的前面
            //监视当前商品id这个key，存的值为stock，开启事务，如果别的事务动它的值，当前事务就失败，自动回滚
            redisTemplate.watch(productId+"");//监视这一行，当执行完一次该事务，就会自动取消watch
            redisTemplate.setEnableTransactionSupport(true);//开启对事务的支持
            redisTemplate.multi();//开始执行事务，
            //！！！！！注意：事务中的后续redisTemplate的语句其实是未执行的，所以
            //无法get值，也无法取得这些语句的返回值，它们在exec方法执行后才会执行
            redisTemplate.opsForValue().decrement(productId+"");
            List<Object> exec = redisTemplate.exec();//返回值为当前的库存值
            redisTemplate.setEnableTransactionSupport(false);//关闭事务
            if (exec == null || exec.isEmpty()) {
                String s1 = redisTemplate.opsForValue().get(productId + "");//得到当前库存
                int newstock = Integer.parseInt(s1);
                if (newstock > 0) {
                    return purchaseProductRedisPlus2(userId, productId, quantity);
                } else {
                    System.out.println("事务执行失败");
                    return false;
                }
            } else if((Long)(exec.toArray()[0]) < 0.0){//返回值为Long类型，不能直接转换成Integer类型，这点需注意，但是long可以直接强转成int
                //可能直接减为了负数，所以这里要做一个判断，
                //如果是减为了负数，就校正其值为0，而且不再访问数据库，进行减数据的操作。
                //为什么会减为负数呢？（比如当stock2 = 1的时候）因为我们在判断stock2>0之后，可能该线程还未执行watch操作，别的线程也进入if语句
                //而且已经watch完，事务也执行完了。此时我们回到if语句里，没有再执行一遍if判断，而是直接watch，这时再减1，就会变成负数
                redisTemplate.opsForValue().set(productId+"","0");
                return false;
            }
            else{
                purchaseProduct(userId, productId, quantity);//执行数据库的操作
                return true;
            }
        } else {
            return false;
        }
    }
}