package com.atguigu.scw.webui.service.exp.handler;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.bean.TOrder;
import com.atguigu.scw.webui.service.TOrderServiceFeign;
import com.atguigu.scw.webui.vo.resp.req.OrderInfoSubmitVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author goodtime
 * @create 2020-02-28 1:59 上午
 */
@Component
@Slf4j
public class TOrderServiceFeignExceptionhandler implements TOrderServiceFeign {
    @Override
    public AppResponse<TOrder> saveOrder(OrderInfoSubmitVo order) {
        AppResponse<TOrder> resp = AppResponse.fail(null);
        resp.setMsg("远程调用【保存订单服务】失败");
        log.debug("远程调用【保存订单服务】失败");
        return resp;
    }
}
