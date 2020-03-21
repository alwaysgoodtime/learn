package com.atguigu.scw.order.exp.handler;

import com.atguigu.scw.order.bean.TReturn;
import com.atguigu.scw.order.service.TProjectServiceFeign;
import com.atguigu.scw.vo.resp.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author goodtime
 * @create 2020-02-28 1:45 下午
 */
@Slf4j
@Component
public class TProjectServiceFeignExceptionhandler implements TProjectServiceFeign {
    @Override
    public AppResponse<TReturn> returnInfo(Integer returnId) {
        AppResponse<TReturn> fail = AppResponse.fail(null);
        fail.setMsg("调用【查询returnid返回TReturn】服务失败");
        log.debug("调用【查询returnid返回TReturn】服务失败");
        return fail;
    }
}
