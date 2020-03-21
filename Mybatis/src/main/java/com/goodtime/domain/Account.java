package com.goodtime.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
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

    public Account(Integer id, String name, Double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    //如果实体类名字和数据库无法对应
//    private Integer accountid;
//    private String accountname;
//    private Double accountmoney;
//
//    public Account(Integer accountid, String accountname, Double accountmoney) {
//        this.accountid = accountid;
//        this.accountname = accountname;
//        this.accountmoney = accountmoney;
//    }
}
