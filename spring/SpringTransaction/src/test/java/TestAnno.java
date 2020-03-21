
import bean.Account;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AccountService;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-13 2:11 下午
 */
public class TestAnno {

    AccountService accountService;

    @Before
    public void before() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beanAnnoNotPure.xml");
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
        accountService.findById(11);
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
        accountService.deleteById(9);
    }

    @org.junit.Test
    public void testAddMinus() {
        Account account = new Account();
        account.setName("bb");
        account.setMoney(120D);
        accountService.addminusAccount(account);
    }

    @org.junit.Test
    public void testAdd() {
        Account account = new Account();
        account.setName("bb");
        account.setMoney(120D);
        accountService.addAccount(account);
    }

}

