package com.atguigu.scw.order.controller;

import com.atguigu.scw.order.bean.TOrder;
import com.atguigu.scw.order.service.TOrderService;
import com.atguigu.scw.order.vo.OrderInfoSubmitVo;
import com.atguigu.scw.vo.resp.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author goodtime
 * @create 2020-02-28 1:07 下午
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class TOrderController {

    @Autowired
    TOrderService tOrderService;

    @RequestMapping("/saveOrder")
    AppResponse<TOrder> saveOrder(@RequestBody OrderInfoSubmitVo order) {

        log.debug("传过来的订单为{}", order);

        try {
            TOrder tOrder = tOrderService.saveOrder(order);

            if (tOrder == null) {
                log.error("保存订单失败");
                AppResponse<TOrder> fail = AppResponse.fail(null);
                fail.setMsg("保存订单失败");
                return fail;
            }

            log.debug("保存订单服务返回的订单为{}", tOrder);
            return AppResponse.ok(tOrder);

        } catch (Exception e) {
            e.printStackTrace();
            log.debug("保存订单整体失败");
            AppResponse<TOrder> fail = AppResponse.fail(null);
            fail.setMsg("保存订单整体失败");
            return fail;
        }
    }

}
