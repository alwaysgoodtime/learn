package utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.transaction.AfterTransaction;

import java.sql.SQLException;

/**
 * 事务管理工具类，是管理connection事务的工具类，包含开启、关闭、回滚事务和释放连接
 * @author goodtime
 * @create 2020-03-13 4:20 下午
 */
@Component
@Aspect//现在是用切面来代理service，不用再写代理工厂了
//未测试它是否是线程安全的，现在是单例模式
public class TransactionUtils {

    //获取当前线程的connection
    @Autowired
    ConnectionUtils connectionUtils;

    //这里不能将这么写：
    //Connection connection = connectionUtils.getThreadConnection();
    //原因可能是，每次获取的字段是不一样的

    @Pointcut("execution(* service.impl.*.*(..))")
    private void pt(){}

    public void beginTransaction(){
        //开启事务，就是将事务自动提交关掉，让它手动提交
        try {
            System.out.println("关闭自动提交了");
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ConnectionUtils getConnectionUtils(){
        return this.connectionUtils;
    }

    //回滚事务
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
            System.out.println("回滚了");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Around("pt()")
    //环绕通知
    //尽量还是选用环绕通知，@After和@AfterRunning执行顺序很成问题，After肯定会执行，应该是执行release方法，可AfterRUnning应该执行的是commit方法
    //还后于After执行，此时连接已经释放， 就又会新建一个连接
    public Object around(ProceedingJoinPoint point){
        Object[] args = point.getArgs();
        Object rt = null;
        try {
            beginTransaction();
            rt = point.proceed(args);
            commit();
        } catch (Throwable throwable) {
            rollback();
            throwable.printStackTrace();
        }finally {
            release();
            return rt;
        }

    }

    public void commit(){
        try {
            System.out.println("提交了");
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void release(){
        try {
            System.out.println("释放了");
            connectionUtils.getThreadConnection().close();
            //因为我们用了连接池，所以这里的关闭，是把连接归还到了连接池
            //tomcat也有一个线程池，没当来一次访问，就创建一个线程取接待，而线程在接客的时候，如果需要用到连接池中的
            //连接，因为我们这里用了ThreadLocal，将连接绑定到了该线程的ThreadLocalMap中去，其key是弱引用，但value并不是
            //所以，连接关闭了，返回连接池是干干净净的，线程用完了，返回线程池，其内部的ThreadLocalMap就会带有这次的连接池的连接
            //因此，在关闭连接的时候，对线程也要进行处理，否则会导致内存泄漏，而且下次再判断线程中是否有连接的时候，因为没清干净
            //所以一判断，还是有连接，但连接是以前用过的，早就该作废了
            connectionUtils.removeConnection();//这一步，就是在关闭连接的同时，让连接和线程解绑
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void haha(){
    }

}


