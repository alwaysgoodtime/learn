package com.atguigu.scw.webui.service;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.bean.TOrder;
import com.atguigu.scw.webui.service.exp.handler.TOrderServiceFeignExceptionhandler;
import com.atguigu.scw.webui.vo.resp.req.OrderInfoSubmitVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author goodtime
 * @create 2020-02-28 1:57 上午
 */
@FeignClient(value = "SCW-ORDER",fallback = TOrderServiceFeignExceptionhandler.class)
public interface TOrderServiceFeign {

    //返回具体的订单,远程调用参数传递
    //1.简单参数参数 @RequestParam(rest风格) @RequestVariable
    //1.传递对象  @RequestBody 相当于把对象变成json串传过去，对面再转成具体的对象
    @RequestMapping("/order/saveOrder")
    AppResponse<TOrder> saveOrder(@RequestBody OrderInfoSubmitVo order);
}
