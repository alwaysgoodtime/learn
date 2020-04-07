package com.goodtime.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author goodtime
 * @create 2020-04-07 11:04 下午
 */

@Data//get、set、toString
@AllArgsConstructor//全参构造器
@NoArgsConstructor//空参构造器
public class Payment {
    private Long id;//因为我们的主键是bigint，所以这里用Long
    private String serial;
}
