import bean.Account;
import config.SpringConfiguration;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AccountService;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-21 11:34 上午
 */
public class TestPureAnno {

    AccountService accountService;

    @Before
    public void before() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
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
        accountService.deleteById(10);
    }

    @org.junit.Test
    public void testAdd() {
        Account account = new Account();
        account.setName("aa");
        account.setMoney(120D);
        accountService.addminusAccount(account);
    }
}
