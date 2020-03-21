package com.goodtime;

import com.goodtime.domain.Account;
import com.goodtime.mapper.AccountMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.executor.BatchExecutor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-11 11:51 下午
 */
public class TestBatch {
    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂,建造者模式
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象

        //5.使用代理对象执行方法


        SqlSession sqlSession1 = factory.openSession(ExecutorType.BATCH,false);//开启批处理，后面是开启自动提交
        //默认insert、update、delete语句为false，select语句为true

        AccountMapper mapper1 = sqlSession1.getMapper(AccountMapper.class);

        long l2 = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            mapper1.updateAccount(new Account(1,"2",null));
        }

        System.out.println("执行时间为"+(System.currentTimeMillis()-l2)+"毫秒");

        //sqlSession1.flushStatements();//可以显式刷新语句情况

        sqlSession1.commit();//如果开启了批处理，commit会自动刷新所有的Statements语句

        sqlSession1.close();//批处理快多了



        SqlSession sqlSession = factory.openSession();
        //4.使用SqlSession创建Dao(Mapper)接口的代理对象
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            mapper.findUserById(i);
        }
        System.out.println("执行时间为"+(System.currentTimeMillis()-l)+"毫秒");

        sqlSession.close();







        //5.释放资源
        in.close();
    }

}
