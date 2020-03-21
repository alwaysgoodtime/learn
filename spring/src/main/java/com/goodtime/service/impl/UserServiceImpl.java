package com.goodtime.service.impl;

import com.goodtime.mapper.UserMapper;
import com.goodtime.service.UserService;

/**
 * @author goodtime
 * @create 2020-03-12 6:26 下午
 */
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public UserServiceImpl() {
        System.out.println("我来啦");
    }

    public void findAll() {
        userMapper.findAll();
        System.out.println("666");
    }
}
