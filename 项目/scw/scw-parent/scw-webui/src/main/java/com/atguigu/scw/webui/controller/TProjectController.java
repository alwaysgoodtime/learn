package com.atguigu.scw.webui.controller;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.bean.TMemberAddress;
import com.atguigu.scw.webui.service.TMemberServiceFeign;
import com.atguigu.scw.webui.service.TProjectServiceFeign;
import com.atguigu.scw.webui.vo.resp.ProjectDetailVo;
import com.atguigu.scw.webui.vo.resp.ReturnPayConfirmVo;
import com.atguigu.scw.webui.vo.resp.UserRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * 一般Controller的命名，都是与实体类有关，操作哪个实体类，就用那个实体类+controller命名
 *
 * @author goodtime
 * @create 2020-02-27 12:34 下午
 */
@Slf4j
@Controller
public class TProjectController {

    @Autowired
    TProjectServiceFeign tProjectServiceFeign;

    @Autowired
    TMemberServiceFeign tMemberServiceFeign;

    //根据项目的name，跳转到对应项目的详情页面
    @RequestMapping("/project/info")
    public String projectInfo(Integer id, Model model) {

        //这里直接调方法即可，不用管对面参数是rest风格的，因为rest和普通的传参，只是传递方式有区别，取到参数调用方法都是一样的
        AppResponse<ProjectDetailVo> response = tProjectServiceFeign.detailsInfo(id);

        ProjectDetailVo data = response.getData();

        if (data == null) {
            log.error("远程调用「查询项目详情」接口失败");
        }

        model.addAttribute("projectDetailVo", data);

        return "project/info";
    }

    //支持项目
    @RequestMapping("/project/support/{projectId}/{returnId}")
    public String projectSupport(@PathVariable("projectId") Integer projectId, @PathVariable("returnId") Integer returnId,Model model,HttpSession session) {

        AppResponse<ReturnPayConfirmVo> resp = tProjectServiceFeign.returnPayConfirmVo(projectId,returnId);

        ReturnPayConfirmVo data = resp.getData();

        log.debug("返回的确认支付信息ReturnPayConfirmVo为{}",data);

        model.addAttribute("payConfirmVo",data);

        session.setAttribute("payConfirmVo",data);

        return "project/pay-step-1";
    }


    //确认订单
    @RequestMapping("/project/confirmOrder/{num}")
    public String confirmOrder(HttpSession httpSession,Model model,@PathVariable("num") Integer num){
        //获取用户登录时存到session（也就是redis）中的信息
        UserRespVo userRespVo = (UserRespVo) httpSession.getAttribute("loginMember");

        //如果用户未登录，让他登录后，再跳转到此页面，而且用户num值也给他保存到session中了。
        if(userRespVo == null){
            log.debug("用户还未登录，或者登录时间过期,跳转登录页面");
            httpSession.setAttribute("preUrl","/project/confirmOrder/"+num);
            return "redirect:/login";
        }

        log.debug("xx是{}",userRespVo);
        log.debug("TOKEN是{}",userRespVo.getAccessToken());

        //1.数据展示，地址信息
        String accessToken = userRespVo.getAccessToken();

        //调用远程接口，查询accessToken对应用户id的地址信息

        AppResponse<List<TMemberAddress>> addresses = tMemberServiceFeign.addresses(accessToken);

        List<TMemberAddress> data = addresses.getData();

        if(data == null){
            log.error("该用户没有地址，或者远程服务端口【根据accesstoken查地址】调用失败");
        }

        model.addAttribute("MemberAddressList",data);

        //还可以获取用户在刚才项目支持页面，所填的信息（注意：购物车功能未实现）
        ReturnPayConfirmVo rpcv = (ReturnPayConfirmVo) httpSession.getAttribute("payConfirmVo");

        //根据用户的设置，重新定义支持的数量和总价
        rpcv.setNum(num);

        rpcv.setTotalPrice(new BigDecimal(num*rpcv.getPrice()+rpcv.getFreight()));

        //因为我们是从redis中取出了session（我们已经设置了session一致性），现在要重新序列化一遍，也就是重新设置一遍

        //如果不重新设置，虽然我们拿到了session中的rpcv，也直接对其进行了设置（传地址，直接改值），但redis中session存的rpcv并没有被修改

        //因为我们的改动是在内存层面，而redis是将数据缓存到了自己的数据库中，所以我们的改动无法对redis中的数据进行修改

        httpSession.setAttribute("payConfirmVo",rpcv);

        return "project/pay-step-2";

    }
}
