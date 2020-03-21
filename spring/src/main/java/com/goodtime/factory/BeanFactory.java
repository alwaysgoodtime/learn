package com.goodtime.factory;

import com.goodtime.service.UserService;
import com.goodtime.service.impl.UserServiceImpl;

/**
 * 假设该工厂类存在于我们依赖的jar包，而且UserServiceImpl没有默认构造函数，我们就无法用默认构造函数创建到工厂中
 * 我们想得到的是UserServiceImpl
 * @author goodtime
 * @create 2020-03-12 10:24 下午
 */
public class BeanFactory {


    public UserServiceImpl getUserService(){
        return new UserServiceImpl();
    }

}
