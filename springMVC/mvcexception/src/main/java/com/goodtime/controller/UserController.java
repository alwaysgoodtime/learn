package com.goodtime.controller;

import com.goodtime.exceptions.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author goodtime
 * @create 2020-03-22 7:18 下午
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //如果不捕获异常，并写异常处理器，就会直接抛给用户，非常不友好

    //第一步：自定义异常类
    //第二步：抛出异常
    //第三步：编写自定义异常处理器
    //第四步：配置异常处理器(在springmvc.xml中配置一个bean即可)
    @RequestMapping("/testException")
    public String find() throws SysException {

        try {
            System.out.println("testException执行了");
            int i = 1/0;
        } catch (Exception e) {
            //打印异常信息
            e.printStackTrace();
            throw new SysException("查询用户出现错误");
        }
        return "success";
    }


    @RequestMapping("/testInterceptor")
    public String testInterceptor(){
        System.out.println("interceptor方法执行了");
        return "success";
    }
}
