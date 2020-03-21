package com.goodtime;

import com.goodtime.domain.Account;
import com.goodtime.domain.QueryVo;
import com.goodtime.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-11 7:22 下午
 */
public class TestForeach {
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
        List<Integer> a = new ArrayList();
        a.add(3);
        a.add(5);
        QueryVo queryVo = new QueryVo();
        queryVo.setIds(a);

        List<Account> lisi = mapper.findAccountByIds(queryVo);
        for (Account b: lisi
        ) {
            System.out.println(b);
        }
        //5.释放资源
        sqlSession.close();
        in.close();
    }
}
