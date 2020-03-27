package com.goodtime.controller;

import com.goodtime.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author goodtime
 * @create 2020-03-21 7:56 下午
 */
@Controller
@Slf4j
public class AjaxController {


    //测试响应ajax请求
    @RequestMapping("/testAjax")
    //ResponseBody，该注解表示，直接将返回的内容变为字符串，返回给前端jsp页面，而不需要经过视图解析器处理。
    //它可以加载类和方法上，如果加在类上，表示该控制器类所有方法都是异步方法，直接返回json串
    //它也可以放到返回值前面
    public @ResponseBody User testAjax(@RequestBody User user1){
        //requestbody的数据，返回的是post请求体中的json字符串，如果我们想把数据直接封装到javabean中，需要引入
        //jackson包的相关依赖，加入jackson后，原来utf8编码post不解析的问题也解决了，否则还得手动再编码一遍
        System.out.println(user1);
        //模拟从数据库查询对象，做响应
        User user = new User();
        user.setAge(18);
        user.setUsername("郝建宇");
        user.setPassword("123456");
        return user;
    }


}

