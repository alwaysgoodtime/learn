package com.goodtime.service.impl;

import com.goodtime.service.UserService;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author goodtime
 * @create 2020-03-12 6:26 下午
 */
@ToString
@Setter
public class UserServiceImpl2  {

    String mystr;
    List<String> mystrs;
    String[] strings;
    Set<String> a;
    Map<String,String> maps;
    Properties properties;

    public UserServiceImpl2() {
        System.out.println("我来啦");
    }

    public void findAll() {
        System.out.println("666");
        System.out.println(mystr);
        System.out.println(mystrs);
        System.out.println(maps);
        System.out.println(properties);
    }


}
