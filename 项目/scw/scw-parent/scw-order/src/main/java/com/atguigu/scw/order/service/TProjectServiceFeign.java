package com.atguigu.scw.order.service;

import com.atguigu.scw.order.bean.TReturn;
import com.atguigu.scw.order.exp.handler.TProjectServiceFeignExceptionhandler;
import com.atguigu.scw.vo.resp.AppResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author goodtime
 * @create 2020-02-28 1:40 下午
 */
@FeignClient(value = "SCW-PROJECT",fallback = TProjectServiceFeignExceptionhandler.class)
public interface TProjectServiceFeign {

    @GetMapping("/project/details/returns/info/{returnId}")
    AppResponse<TReturn> returnInfo(@PathVariable("returnId") Integer returnId);
}