package com.goodtime.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author goodtime
 * @create 2020-03-11 7:59 下午
 */
@Data
@ToString
public class User implements Serializable {
    private Integer id;
    private Integer uid;
    private String password;

    //从表实体应该包含一个主表实体的对象引用
    private Account account;
}
