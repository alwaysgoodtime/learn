package com.atguigu.scw.order.service;

import com.atguigu.scw.order.bean.TOrder;
import com.atguigu.scw.order.vo.OrderInfoSubmitVo;

/**
 * @author goodtime
 * @create 2020-02-28 1:15 下午
 */
public interface TOrderService {
    TOrder saveOrder(OrderInfoSubmitVo order);
}
