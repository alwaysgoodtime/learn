package com.atguigu.scw.user.controller;


import com.atguigu.scw.user.component.SmsTemplate;
import com.atguigu.scw.user.service.TMemberService;
import com.atguigu.scw.user.vo.req.UserRegistVo;
import com.atguigu.scw.user.vo.resp.UserRespVo;
import com.atguigu.scw.vo.resp.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Api(tags = "用户登陆注册模块")
@RequestMapping("/user")
@RestController
public class UserLoginRegistController {

    Logger log = LoggerFactory.getLogger(UserLoginRegistController.class);

    @Autowired
    SmsTemplate smsTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    TMemberService tMemberService;

    @ApiOperation(value = "用户登陆")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "登陆账号", name = "loginacct"),
            @ApiImplicitParam(value = "用户密码", name = "userpswd")
    })
    @PostMapping("/login")
    public AppResponse<UserRespVo> login(@RequestParam("loginacct") String loginacct, @RequestParam("userpswd") String userpswd) {

        try {
            UserRespVo vo = tMemberService.getUserByLogin(loginacct, userpswd);
            log.debug("用户{}登录成功", loginacct);
            //log.debug("用户{}登录密码",userpswd); 生产环境一定要删除
            return AppResponse.ok(vo);
        } catch (Exception e) {
            //异步开发，接口一定要把自己包装后的值返回，不允许直接抛异常
            e.printStackTrace();
            log.debug("用户登录失败", loginacct);
            AppResponse<UserRespVo> resp = AppResponse.fail(null);
            resp.setMsg(e.getMessage());
            return resp;
        }
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public AppResponse<Object> register(UserRegistVo vo) {

        String loginacct = vo.getLoginacct();

        //这里用了Spring框架的StringUtils工具类，其实就是以前我们判断其是否为null+其是否为空字符串
        if (!StringUtils.isEmpty(loginacct)) {

            //通过给注册用户手机发的验证码，得到Redis中给用户存的值
            String code = stringRedisTemplate.opsForValue().get(loginacct);

            if (!StringUtils.isEmpty(code)) {

                //防止前台没有做code的表单校验，有时候我们用postman和swwagger测试，也不会经过前台表单校验
                if (code.equals(vo.getCode())) {

                    //还需要校验账号是否唯一，和email地址是否唯一，这里省略，直接保存数据

                    int i = tMemberService.saveTmember(vo);
                    if (i == 1) {
                        stringRedisTemplate.delete(loginacct);//注册成功，清理缓存
                        return AppResponse.ok("ok");
                    } else {
                        return AppResponse.fail(null);
                    }

                } else {
                    //这也还可以在缓存中存个值，表示该用户一天注册了几个，给他设个上限即可
                    AppResponse resp = AppResponse.fail(null);
                    resp.setMsg("验证码不一致，请重新输入");
                    return resp;
                }


            } else {
                AppResponse resp = AppResponse.fail(null);
                resp.setMsg("验证码失效，请重新发送");
                return resp;
            }

        } else {
            AppResponse response = AppResponse.fail(null);
            response.setMsg("用户名不能为空");
            return response;

        }
    }

    @ApiOperation(value = "发送短信验证码，默认登录账号就是手机号")
    @PostMapping("/sendsms")
    public AppResponse<Object> sendsms(String loginacct) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(new Random().nextInt(10));//随机生成0-9之间的整数值
        }

        //登录时，发送短信验证码
        Map<String, String> querys = new HashMap<>();
        querys.put("mobile", loginacct);
        querys.put("param", "code:" + stringBuilder.toString());
        querys.put("tpl_id", "TP1711063");
        smsTemplate.sendCode(querys);

        //把验证码存到Redis中，方便在规定时间内验证（比如五分钟内）
        stringRedisTemplate.opsForValue().set(loginacct, stringBuilder.toString(), 5l, TimeUnit.MINUTES);

        log.debug("发送验证码成功，验证码为{}", stringBuilder.toString());


        return AppResponse.ok("ok");
    }

    @ApiOperation(value = "验证短信验证码")
    @PostMapping("/valide")
    public AppResponse<Object> valide() {
        return AppResponse.ok("ok");
    }

    @ApiOperation(value = "重置密码")
    @PostMapping("/reset")
    public AppResponse<Object> reset() {
        return AppResponse.ok("ok");
    }
}
