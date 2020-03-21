package com.goodtime.controller;


import com.goodtime.mapper.UserMapper;
import com.goodtime.service.UserService;
import com.goodtime.service.impl.UserServiceImpl2;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模板模式
 * ApplicationContext为接口，实现了BeanFactory
 * 三个实现类
 *
 * AppliactionContext为立即加载，读完bean.xml就加载，单例对象适用（在启动程序时加载即可）
 * BeanFactory为延迟加载，用时才加载，可以通过加断点查看，多例对象适用（用时创建）
 * 后续，我们可以指定创建bean的方式，一般会用AppliactionContext，它的方法更多
 * 而且，两种创建方式加载的不是在同一个工厂中
 * @author goodtime
 * @create 2020-03-12 9:47 下午
 */
//模拟表现层调用
public class Controller {

    public static void main(String[] args) {

        //1.拿到核心容器
        //ApplicationContext context =  new ClassPathXmlApplicationContext("bean.xml");
        //2.根据容器获得bean对象,两种强转的方式
//        System.out.println("aa");
//        UserService userService = (UserService)context.getBean("UserService");
//        UserMapper userMapper = context.getBean("UserMapper",UserMapper.class);
//        UserMapper userMapper2 = context.getBean("UserMapper",UserMapper.class);
//
//
//        System.out.println(userMapper == userMapper2);
//        UserServiceImpl2 userServiceImpl2 = context.getBean("UserService3",UserServiceImpl2.class);
        System.out.println("1");
        ClassPathXmlApplicationContext context2 =  new ClassPathXmlApplicationContext("bean2.xml");
        System.out.println("2");
        UserService userServiceImpl3 = context2.getBean("aaa",UserService.class);
        userServiceImpl3.findAll();
        context2.close();//单例随容器关闭而销毁，多例则不会

//        System.out.println(userServiceImpl2);
//        System.out.println(userServiceImpl3);
//
//        userService.findAll();







//        Resource resource = new ClassPathResource("bean.xml");
//        System.out.println("aa");
//        BeanFactory beanFactory = new XmlBeanFactory(resource);
//        UserService userService1 = (UserService)beanFactory.getBean("UserService");
//        UserMapper userMapper1 = beanFactory.getBean("UserMapper",UserMapper.class);
//        System.out.println(userMapper1);
//        System.out.println(userMapper == userMapper1);

    }
}
