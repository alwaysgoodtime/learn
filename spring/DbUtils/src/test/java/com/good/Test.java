package com.good;

import com.good.domain.Account;
import com.good.mapper.AccountMapper;
import com.good.service.AccountService;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-13 1:26 下午
 */
public class Test {

    AccountService accountService;

    @Before
    public void before(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        accountService = ac.getBean("accountService", AccountService.class);
    }

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
        accountService.deleteById(12);
    }

    @org.junit.Test
    public void testAdd() {
        Account account = new Account();
        account.setName("aa");
        account.setMoney(120D);
        accountService.addAccount(account);
    }




}
