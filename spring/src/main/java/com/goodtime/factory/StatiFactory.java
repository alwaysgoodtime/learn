package com.goodtime.factory;

import com.goodtime.service.impl.UserServiceImpl;

/**
 * @author goodtime
 * @create 2020-03-12 10:33 下午
 */
public class StatiFactory {
    public StatiFactory() {

    }

    public static UserServiceImpl getUserService(){
        return new UserServiceImpl();
    }
}
