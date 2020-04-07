package com.goodtime.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前后端分离，传给前端的数据类型
 * @author goodtime
 * @create 2020-04-07 11:06 下午
 */
@Data
@AllArgsConstructor//全参构造器
@NoArgsConstructor//空参构造器
public class CommonResult<T> {
    private Integer code;//200,404
    private String message;//成功，失败
    private T data;//我们传的数据，写成泛型，保证任何的数据类都能返回

    public CommonResult(Integer code, String message) {//自定义一个两个参数的构造器
        this(code,message,null);//传的数据为null
    }
}
