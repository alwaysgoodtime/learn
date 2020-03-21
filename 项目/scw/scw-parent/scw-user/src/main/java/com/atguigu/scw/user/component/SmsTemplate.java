package com.atguigu.scw.user.component;

import java.util.HashMap;
import java.util.Map;

import com.atguigu.scw.utils.HttpUtils;
import com.atguigu.scw.vo.resp.AppResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsTemplate {

    Logger log = LoggerFactory.getLogger(SmsTemplate.class);

    @Value("${sms.host}")
    private String host;

    @Value("${sms.path}")
    private String path;

    // :表示没配的默认值
    @Value("${sms.method:POST}")
    private String method;

    @Value("${sms.appcode}")
    private String appcode;

    //暴露了sendCode方法，可以设置querys,需要放如下三组之
    //querys.put("mobile", "159xxxx9999");
    //querys.put("param", "code:1234");
    //querys.put("tpl_id", "TP1711063");
    public AppResponse<String> sendCode(Map<String, String> querys) {
        HttpResponse response = null;
        Map<String, String> headers = new HashMap<String, String>();
// 授权头
        headers.put("Authorization", "APPCODE " + appcode);//这里注意：有空格
        Map<String, String> bodys = new HashMap<String, String>();
        try {
            if (method.equalsIgnoreCase("get")) {
                response = HttpUtils.doGet(host, path, method, headers, querys);
            } else {
                response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            }
            String string = EntityUtils.toString(response.getEntity());
            log.info("短信发送完成；响应数据是：{}", string);
            return AppResponse.ok(response.toString());
//获取返回的响应数据
        } catch (Exception e) {
            log.error("短信发送失败；发送参数是：{}", querys);
            return AppResponse.fail(null);
        }
    }
}
