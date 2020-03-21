package com.atguigu.scw.webui.service;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.bean.TMemberAddress;
import com.atguigu.scw.webui.service.exp.handler.TMemberServiceFeignExceptionhandler;
import com.atguigu.scw.webui.vo.resp.UserRespVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-02-26 9:53 下午
 */
//找到调用的客户端，并加上异常处理类
@FeignClient(value = "SCW-USER",fallback = TMemberServiceFeignExceptionhandler.class)
public interface TMemberServiceFeign {

    //调用远程SCW—USER的服务,最好都加上@RequestParam，保证数据可以顺利且安全地传输
    //这里一定要记得加全路径，是"/user/login"，而非"/login",整个controller上还有映射
    @PostMapping("/user/login")
    public AppResponse<UserRespVo> login(@RequestParam("loginacct") String loginacct, @RequestParam("userpswd") String userpswd);

    @GetMapping("/user/info/address")
    //最好写上@RequestParam("accessToken"),rest接口调用要加@PathParam()
    public AppResponse<List<TMemberAddress>> addresses(@RequestParam("accessToken")  String accessToken);

}
