package com.good.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author goodtime
 * @create 2020-03-13 1:00 下午
 */
@Data
@ToString
public class Account implements Serializable {
    Integer id;
    String name;
    Double money;
}
