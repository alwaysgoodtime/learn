package com.atguigu.scw.webui.service.exp.handler;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.bean.TMemberAddress;
import com.atguigu.scw.webui.service.TMemberServiceFeign;
import com.atguigu.scw.webui.vo.resp.UserRespVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-02-26 10:00 下午
 */

//熔断后的兜底值
@Component
public class TMemberServiceFeignExceptionhandler implements TMemberServiceFeign{

    @Override
    public AppResponse<UserRespVo> login(String loginacct, String userpswd) {
        AppResponse<UserRespVo> resp = AppResponse.fail(null);
        resp.setMsg("调用远程服务「登录」失败");
        return resp;
    }

    @Override
    public AppResponse<List<TMemberAddress>> addresses(String accessToken) {
        AppResponse<List<TMemberAddress>> resp = AppResponse.fail(null);
        resp.setMsg("调用远程服务【根据accessToken查询用户地址】失败");
        return resp;
    }
}
