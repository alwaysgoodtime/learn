package com.atguigu.scw.webui.service;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.service.exp.handler.TProjectServiceFeignExceptionhandler;
import com.atguigu.scw.webui.vo.resp.ProjectDetailVo;
import com.atguigu.scw.webui.vo.resp.ProjectVo;
import com.atguigu.scw.webui.vo.resp.ReturnPayConfirmVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-02-27 1:38 上午
 */
@FeignClient(value = "SCW-PROJECT", fallback = TProjectServiceFeignExceptionhandler.class)
public interface TProjectServiceFeign {
    @GetMapping("/project/all")
    public AppResponse<List<ProjectVo>> all();

    @GetMapping("project/details/info/{projectId}")
    public AppResponse<ProjectDetailVo> detailsInfo(@PathVariable("projectId") Integer projectId);

    //这里必须和调用的接口写的一模一样，不能丢掉@PathVariable("projectId")
    @GetMapping("project/details/returns/info/{projectId}/{returnId}")
    public AppResponse<ReturnPayConfirmVo> returnPayConfirmVo(@PathVariable("projectId")Integer projectId,@PathVariable("returnId")Integer returnId);


}
