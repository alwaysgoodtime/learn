package com.goodtime;

import com.goodtime.domain.Account;
import com.goodtime.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-11 3:21 下午
 */
public class testInsert {
    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂,建造者模式
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession sqlSession = factory.openSession(true);//设置自动提交为true，增删改操作就不用手动提交了，默认为false
        //4.使用SqlSession创建Dao(Mapper)接口的代理对象
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        //5.使用代理对象执行方法

        //如果是insert/update/delete方法，会默认设置自动提交为false，所以要手动提交才行
        Account lisi = new Account(null, "lisi", 199d);
        System.out.println(lisi);
        int a = mapper.saveAccount(lisi);
        System.out.println(a);
        System.out.println("保存操作之后");
        System.out.println(lisi);

        //5.释放资源
        sqlSession.close();
        in.close();
    }
}
