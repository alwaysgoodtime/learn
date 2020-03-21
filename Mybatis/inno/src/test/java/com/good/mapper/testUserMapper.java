package com.good.mapper;

import com.good.domain.Account;
import com.good.domain.User;
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
public class testUserMapper {
    public static void main(String[] args) throws Exception{
        //第一步：获取字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");//从类路径下加载
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = mapper.findAll();
        System.out.println(all.toString());
        List<User> byUid = mapper.findByUid(1);
        System.out.println(byUid);
        sqlSession.close();
        resourceAsStream.close();
    }
}
