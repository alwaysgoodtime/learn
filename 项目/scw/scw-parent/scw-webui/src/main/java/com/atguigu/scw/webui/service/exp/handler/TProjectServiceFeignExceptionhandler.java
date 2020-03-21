package com.atguigu.scw.webui.service.exp.handler;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.service.TProjectServiceFeign;
import com.atguigu.scw.webui.vo.resp.ProjectDetailVo;
import com.atguigu.scw.webui.vo.resp.ProjectVo;
import com.atguigu.scw.webui.vo.resp.ReturnPayConfirmVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-02-27 1:40 上午
 */
//记得注册组件，让spring接管控制
@Component
@Slf4j
public class TProjectServiceFeignExceptionhandler implements TProjectServiceFeign {
    @Override
    public AppResponse<List<ProjectVo>> all() {
        AppResponse<List<ProjectVo>> resp = AppResponse.fail(null);
        log.error("调用服务端[查询所有项目接口]失败");
        resp.setMsg("调用服务端[查询所有项目接口]失败");
        return resp;
    }

    @Override
    public AppResponse<ProjectDetailVo> detailsInfo(Integer projectId) {
        AppResponse<ProjectDetailVo> resp = AppResponse.fail(null);
        log.error("调用服务端[查询项目详情接口]失败");
        resp.setMsg("调用服务端[查询项目详情接口]失败");
        return resp;
    }

    @Override
    public AppResponse<ReturnPayConfirmVo> returnPayConfirmVo(Integer projectId, Integer returnId) {
        AppResponse<ReturnPayConfirmVo> resp = AppResponse.fail(null);
        log.error("调用服务端[返回支付订单具体信息]失败");
        resp.setMsg("调用服务端[返回支付订单信息]失败");
        return resp;
    }
}
