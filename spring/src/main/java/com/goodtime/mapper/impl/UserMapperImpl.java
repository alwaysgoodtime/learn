package com.goodtime.mapper.impl;

import com.goodtime.mapper.UserMapper;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author goodtime
 * @create 2020-03-12 9:50 下午
 */
@ToString
@Setter
@Repository
public class UserMapperImpl implements UserMapper {

    //这里模拟的是dao中的数据，但其实dao中的数据经常变动，我们不能进行依赖注入，每次要创建新的，一般是在方法中写（也就是局部变量）
    //不过这里是测试依赖注入，
    private String name;
    private Date date;
    private int id;

    public UserMapperImpl() {
    }

    public UserMapperImpl(String name, Date date, int id) {
        this.name = name;
        this.date = date;
        this.id = id;
    }

    public void findAll() {
        System.out.println("找到啦");
    }
}
