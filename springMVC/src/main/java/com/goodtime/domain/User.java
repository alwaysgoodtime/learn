package com.goodtime.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author goodtime
 * @create 2020-03-21 8:52 下午
 */
@Data
public class User {
    private Integer age;
    private String sex;
    private Date date;
}
