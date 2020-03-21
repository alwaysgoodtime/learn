package com.good;

import com.good.domain.Account;
import com.good.service.AccountService;
import config.SpringConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * spring整合Junit
 * 1.导入spring整合junit的坐标
 * 2.使用junit的@runwith注解，把junit原来的main方法替换成spring中可以自动注入的main方法
 * 3.告知spring的运行期，spring的ioc文件是基于xml还是基于配置类，并告知配置文件的位置
 * @ContextConfiguration 写classes，说明是基于配置类的，写对应配置类的class文件即可
 * 写locations，说明是基于xml文件的，默认是类路径下的，写classpath，对应的xml文件地址
 *
 *
 * 注意：当我们使用spring5.0版本后，要求junit的jar包必须是4.12及以上的版本
 * @author goodtime
 * @create 2020-03-13 3:31 下午
 */
//测试的时候，测试人员不知道Spring的容器注入，也不会创建，我们需要抽取出来

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class TestSpringJunit {

    //注入的是被代理的对象
    @Autowired
    @Qualifier("proxy")
    AccountService accountService;

//    @Before
//    public void before() {
//        //用applicationcontext的另一个实现类才行
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        accountService = ac.getBean("accountService", AccountService.class);
//    }

    @org.junit.Test
    public void testFindAll() {
        List<Account> all = accountService.findAll();
        for (int i = 0; i < all.size(); i++) {
            System.out.println(all.get(i).toString());
        }
    }

    @org.junit.Test
    public void testFindById() {
        accountService.findById(12);
    }

    @org.junit.Test
    public void testUpadate() {
        Account account = new Account();
        account.setId(12);
        account.setName("aa");
        account.setMoney(120D);
        accountService.updateAccount(account);
    }

    @org.junit.Test
    public void testDelete() {
        accountService.deleteById(13);
    }

    @org.junit.Test
    public void testAdd() {
        Account account = new Account();
        account.setName("aa");
        account.setMoney(120D);
        accountService.addAccount(account);
    }


    //事务控制测试
    @org.junit.Test
    public void testAddMinusAccount() {
        Account account = new Account();
        account.setId(90);
        account.setName("aa");
        account.setMoney(120D);
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                accountService.addminusAccount(account);
            }).start();
        }
        try {
            Thread.sleep(100000);//因为junit主线程main结束的话，所有创建的子线程也结束，
            //所以又让主线程休眠，保证子线程能执行完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
