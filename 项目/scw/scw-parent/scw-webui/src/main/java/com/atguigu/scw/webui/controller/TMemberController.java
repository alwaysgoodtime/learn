package com.atguigu.scw.webui.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author goodtime
 * @create 2020-02-28 2:14 上午
 */
@Slf4j
@Controller
public class TMemberController {

    //不论是从订单那里转到此页面，还是从其他页面转到此页面，这个映射都得查出用户的订单详情，并展示在页面上
    @RequestMapping("/member/minecrowdfunding")
    public String orderList(){

        log.debug("支付后，同步请求处理....");
        return "member/minecrowdfunding";
    }
}
