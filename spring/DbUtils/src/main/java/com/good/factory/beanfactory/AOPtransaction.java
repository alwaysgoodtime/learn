package com.good.factory.beanfactory;

import com.good.domain.Account;
import com.good.service.AccountService;
import com.good.utils.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author goodtime
 * @create 2020-03-13 7:30 下午
 */
@Component
public class AOPtransaction {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionUtils transactionUtils;//匿名内部类访问变量，其实是要加final的

    @Bean(name = "proxy")
    //获取AccountService的代理对象
    public AccountService getAccountService(AccountService O) {
        AccountService a = (AccountService) Proxy.newProxyInstance(O.getClass().getClassLoader(), O.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    //开启事务
                    transactionUtils.beginTransaction();
                    //执行操作
                    Object invoke = method.invoke(accountService, args);//这里传的就是被代理的对象，以及其参数
                    //提交事务
                    transactionUtils.commit();
                    //返回结果,有则写，没有则不写
                    return invoke;
                } catch (Exception e) {
                    //回滚
                    e.getCause();
                    System.out.println("回滚了");
                    transactionUtils.rollback();
                    throw new RuntimeException();
                } finally {
                    //释放资源
                    transactionUtils.release();
                }
            }
        });
        return a;
    }
}
