package cn.itcast.test;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


/**
 * @author goodtime
 * @create 2020-02-01 3:57 下午
 */
public class TestMybatis {

//    测试查询
    @Test
    public void run2() throws Exception {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = factory.openSession();

        AccountDao mapper = sqlSession.getMapper(AccountDao.class);


        List<Account> all = mapper.findAll();

        for (Account a : all
        ) {
            System.out.println(a);
        }

        sqlSession.close();
        resourceAsStream.close();

    }

//    测试保存
    @Test
    public void run3() throws Exception {

        Account account = new Account();
        account.setMoney(12d);
        account.setName("hrhie");


        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = factory.openSession();

        AccountDao mapper = sqlSession.getMapper(AccountDao.class);


        mapper.saveAccount(account);

        sqlSession.commit();

        sqlSession.close();
        resourceAsStream.close();

    }
}
