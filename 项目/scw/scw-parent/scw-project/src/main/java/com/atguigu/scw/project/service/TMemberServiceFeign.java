package com.atguigu.scw.project.service;

import com.atguigu.scw.project.exp.handler.TMemberServiceFeignExceptionHandler;
import com.atguigu.scw.project.vo.req.UserRespVo;
import com.atguigu.scw.vo.resp.AppResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author goodtime
 * @create 2020-02-27 6:50 下午
 */
@FeignClient(value = "SCW-USER",fallback = TMemberServiceFeignExceptionHandler.class)
public interface TMemberServiceFeign {

    //服务端SCW-PROJECT调用远程其他服务端的服务
    @GetMapping("/user/info/{id}")
    public AppResponse<UserRespVo> getMemberInfo(@PathVariable("id") Integer memberid);
}
