package com.atguigu.scw.webui.controller;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.service.TMemberServiceFeign;
import com.atguigu.scw.webui.service.TProjectServiceFeign;
import com.atguigu.scw.webui.vo.resp.ProjectVo;
import com.atguigu.scw.webui.vo.resp.UserRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author goodtime
 * @create 2020-02-26 5:08 下午
 */

//这里就不用RestController注解了，前端有同步有异步
@Controller
@Slf4j
public class DispatcherController {

    @Autowired
    TMemberServiceFeign tMemberServiceFeign;

    @Autowired
    TProjectServiceFeign tProjectServiceFeign;

    //这里用redisTemplate，不用StringRedisTemplate,因为后者转换出来的是字符串，不好用JSON解析成集合
    @Autowired
    RedisTemplate redisTemplate;

    //如果这个方法只负责映射页面跳转，不负责其他业务逻辑，以前可以直接在springmvc文件中
    //做此配置，现在没有配置文件，可以在配置类中进行配置
    //<mvc:view-controller path="/" view-name="index">
    //设置首页
    @GetMapping("/")
    public String index(Model model) {

        //不过这里可能有一种情况，就是热点项目的发布者修改自己的项目信息，或者项目头图，也得1小时后才生效，如果遇到
        //这种情况，只能是手动刷新缓存了。不过项目信息和项目头图等信息，在实际生产中是不可能随意改动的。

        //首页热点项目展示，如果redis中有，则直接返回，如果没有，则调用后端服务器接口从数据库查询一次
        List<ProjectVo> projectInfo = (List<ProjectVo>) redisTemplate.opsForValue().get("projectInfo");
        if (projectInfo == null) {
            log.debug("开始调用服务端查询所有项目接口");
            AppResponse<List<ProjectVo>> resp = tProjectServiceFeign.all();
            projectInfo = resp.getData();
            //设置该值60s过期，因为我们此时只有三个数据，所以调了查询所有项目接口，后续可以调用查询所有项目中follower前三的项目
            //放到首页展示，limit（0，3），如果项目follower相同，选出的项目其实是按主键（项目id）排序
            redisTemplate.opsForValue().set("projectInfo", projectInfo, 60, TimeUnit.MINUTES);
            if (projectInfo == null) {
                log.error("远程调用查询所有项目失败，自动熔断");
                return "index";
            }
        }

        //放到请求域中，方便Themeleaf取
        log.debug("热点数据{}显示成功", projectInfo);
        model.addAttribute("projectVoList", projectInfo);
        return "index";
    }


    //登录页面映射
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    //登录请求，调用我们的远程服务
    @PostMapping("/doLogin")
    public String dologin(String loginacct, String userpswd, HttpSession httpSession) {

        AppResponse<UserRespVo> resp = tMemberServiceFeign.login(loginacct, userpswd);

        System.out.println(resp);

        UserRespVo data = resp.getData();

        //说明远程调用失败
        if (data == null) {
            log.debug("远程调用失败或用户不存在或密码错误");
            return "login";

        }

        System.out.println(data.getUsername());

        httpSession.setAttribute("loginMember", data);
        log.debug("用户{}登录成功", loginacct);
        //重定向到首页，不要转发，防止用户刷新页面，导致表单重复提交

        String preUrl =(String)httpSession.getAttribute("preUrl");

        //查看用户是否是下订单时来登录的，如果是，那么跳回登录前的页面，如果不是，跳回首页
        if(StringUtils.isEmpty(preUrl)){
            return "redirect:/";
        }else {
            return  "redirect:"+preUrl;
        }

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.removeAttribute("loginMember");
            session.invalidate();
        }
        return "redirect:/";
    }

}
