package com.goodtime;

import com.goodtime.domain.Account;
import com.goodtime.domain.AccountUser;
import com.goodtime.domain.User;
import com.goodtime.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-11 8:12 下午
 */
public class OneToOne {
    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂,建造者模式
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.使用SqlSession创建Dao(Mapper)接口的代理对象
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        //5.使用代理对象执行方法
        List<User> accountAndUser = mapper.findAccountAndUser();
        for (User a: accountAndUser //只要一遍历a，马上就会把延迟加载的也加载出来
        ) {
            System.out.println(a);
//            System.out.println(a.getAccount());
        }
        //5.释放资源
        sqlSession.close();
        in.close();
    }
}
