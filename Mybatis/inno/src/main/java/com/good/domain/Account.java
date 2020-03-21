package com.good.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-11 2:13 下午
 */
@Data
@ToString
public class Account implements Serializable {

    private Integer id;
    private String name;
    private Double money;
    private List<User> user;//这里假设一个user对应一个账户，一个账户对应对个user
    //一对多，一个账户对应多个用户

    public Account(Integer id, String name, Double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

}
