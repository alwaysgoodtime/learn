package com.atguigu.scw.webui.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.bean.TOrder;
import com.atguigu.scw.webui.config.AlipayConfig;
import com.atguigu.scw.webui.service.TOrderServiceFeign;
import com.atguigu.scw.webui.vo.resp.OrderFormInfoSubmitVo;
import com.atguigu.scw.webui.vo.resp.ReturnPayConfirmVo;
import com.atguigu.scw.webui.vo.resp.UserRespVo;
import com.atguigu.scw.webui.vo.resp.req.OrderInfoSubmitVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.apache.http.RequestLine;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author goodtime
 * @create 2020-02-28 1:45 上午
 */
@Controller
@Slf4j
public class TOrderController {


    @Autowired
    TOrderServiceFeign tOrderServiceFeign;

    //接收用户提交的数据，封装成OrderFormInfoSubmitVo对象，然后从session中查到数据，进一步封装成OrderInfoSubmitVo
    //最后提交到远程order模块的接口

    //返回json字符串，让表单原样返回
    @ResponseBody
    @PostMapping("/order/pay")
    public String payOrder(OrderFormInfoSubmitVo resp /*接收表单数据*/, HttpSession httpSession) {

        log.debug("用户传回的订单是{}", resp);

        //1.封装OrderInfoSubmitVo表单

        OrderInfoSubmitVo order = new OrderInfoSubmitVo();

        BeanUtils.copyProperties(resp, order);

        //从session中取登录用户的信息
        UserRespVo respVo = (UserRespVo) httpSession.getAttribute("loginMember");

        if (respVo == null) {
            log.debug("用户登录过期");
            return "redirect:/login";//都是重定向，不要转发，否则会表单重复提交
        }

        String accessToken = respVo.getAccessToken();

        if (accessToken == null) {
            log.debug("用户登录过期");
            return "redirect:/login";
        }

        order.setAccessToken(accessToken);

        //从session中取订单和回报的信息
        ReturnPayConfirmVo payConfirmVo = (ReturnPayConfirmVo) httpSession.getAttribute("payConfirmVo");
        Integer projectId = payConfirmVo.getProjectId();
        Integer returnId = payConfirmVo.getReturnId();
        Integer num = payConfirmVo.getNum();//默认num就是下单的数量，也就是用户要下几单（这里我们默认一单只回报一个商品，所以也就等于回报的商品数）
        order.setProjectid(projectId);
        order.setReturnid(returnId);
        order.setRtncount(num);

        log.debug("传递的订单数据为{}", order);

        //2.调用远程SCW——ORDER的订单提交服务

        //返回一个Torder表单对象
        //这一步的目的是，让数据库保存订单（设置为未支付状态，而且返回用户id，相当于生成一张正式的订单）
        AppResponse<TOrder> response = tOrderServiceFeign.saveOrder(order);

        TOrder tOrder = response.getData();

        log.debug("返回的表单对象为{}", tOrder);

        //3.调用支付宝接口，完成支付
        String orderName = payConfirmVo.getProjectName();//我们用项目名字作为订单名

        //方法需要传4个参数：订单号，订单总金额，订单的名称，订单的备注
        String result = payOrder(tOrder.getOrdernum(), tOrder.getMoney()+"", orderName, resp.getRemark());

        return result;//Alipay返回的表单，返回给浏览器，并且立即提交表单，出来二维码支付页面

    }

    //这里应该是写在order服务端那里的服务，要通过Feign远程调用,这里主要是需要内网穿透
    @ResponseBody
    @RequestMapping("/order/updateOrderStatus")
    public String updateOrderStatus(HttpServletRequest request){

        Map<String, String[]> parameterMap = request.getParameterMap();
        log.debug("返回的requestLine为{}",parameterMap);

        //修改订单为已支付状态，等待发货

        log.debug("支付宝支付完成后的异步通知");
        return "success";//业务完成，必须给支付宝返回"success",这个是最终的支付结果
    }


    //调支付宝的支付页面接口
    private String payOrder(String ordernum, String money, String orderName, String remark) {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);


        alipayRequest.setBizContent("{\"out_trade_no\":\"" + ordernum + "\","
                + "\"total_amount\":\"" + money + "\","
                + "\"subject\":\"" + orderName + "\","
                + "\"body\":\"" + remark + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //product_code就是产品编号

        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.debug("Alipay报错");
            return null;
        }
        return result;
    }

}
