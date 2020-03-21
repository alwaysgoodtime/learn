package com.atguigu.scw.project.exp.handler;

import com.atguigu.scw.project.service.TMemberServiceFeign;
import com.atguigu.scw.project.vo.req.UserRespVo;
import com.atguigu.scw.vo.resp.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author goodtime
 * @create 2020-02-27 6:52 下午
 */
@Component
@Slf4j
public class TMemberServiceFeignExceptionHandler implements TMemberServiceFeign {
    @Override
    public AppResponse<UserRespVo> getMemberInfo(Integer memberid) {
        AppResponse<UserRespVo> fail = AppResponse.fail(null);
        fail.setMsg("调取远程服务【根据用户id获取用户信息】失败");
        log.error("调取远程服务【根据用户id获取用户信息】失败");
        return fail;
    }
}
