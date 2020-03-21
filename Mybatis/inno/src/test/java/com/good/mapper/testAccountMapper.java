package com.good.mapper;

import com.good.domain.Account;
import com.good.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-12 12:48 上午
 */
public class testAccountMapper {
    public static void main(String[] args) throws Exception{
        //第一步：获取字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");//从类路径下加载
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> all = mapper.findAll();
//        mapper.saveAccount(new Account("haha",195d));
        for (Account a : all){
            System.out.println(a.toString());
        }
        mapper.updateAccount(new Account(1,"hei",15d));
        mapper.deleteAccountById(40);
        Account accountByid = mapper.findAccountByid(40);
        System.out.println(accountByid);
        List<Account> accountByName = mapper.findAccountByName("%lisi%");
        System.out.println(accountByName.toString());
        System.out.println(mapper.CountAccount());
        sqlSession.commit();
        resourceAsStream.close();
    }
}
