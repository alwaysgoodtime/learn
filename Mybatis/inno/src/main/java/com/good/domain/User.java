package com.good.domain;

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
    //本身是多对一，但在mybatis中表现为1对1的映射
    private Account account;
}
