package com.goodtime.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author goodtime
 * @create 2020-03-22 1:49 下午
 */
@Data
public class User implements Serializable {
    String username;
    String password;
    Integer age;
}
