package com.goodtime.controller;

import com.goodtime.domain.Account;
import com.goodtime.domain.Member;
import com.goodtime.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

/**
 * @author goodtime
 * @create 2020-03-21 7:56 下午
 */
@Controller
@Slf4j
@SessionAttributes({"msg"})//把request域中的msg=meimei属性，存到session域中一份,该注解只能加载类上
//不能加在方法上
public class HelloController {

    //method,表示方法必须为get请求，post等其他请求会报405错误
    //params，表示请求的key（或者key和value）必须有（如果配了key和value，则必须一模一样）,没有的话，会报400，badrequest
    //注意：method中的字符，区分大小写，header中的字符，不区分大小写
    @RequestMapping(path = "/hello",method = {RequestMethod.GET},params = {"username=aa"},headers = {"ACCept"})

    //参数绑定，注意，string是大小写敏感的
    //requestParam：指定我们要绑定的参数，比如传过来的是参数为username，我们给它绑定到name上
    //requesrParam有个属性，required，默认为true，也就是说，该参数必须有，和@RequestMapping的params功能一样
    public String sayhello(@RequestParam(value = "username",required = false) String name, String password){
        System.out.println("hello springmvc");
        System.out.println(name);
        System.out.println(password);
        return "success";
    }

    @RequestMapping("/hi")
    public String sayhi(Account account){
        System.out.println(account.toString());
        return "success";
    }

    @RequestMapping("/hei")
    //我们的member中，可能有integer、double型数据，前端传过来的都是字符串，是springmvc帮我们进行类型转换，
    //但有些类型是无法转换的
    public String sayhi(Member member){
        System.out.println(member.toString());
        return "success";
    }

    @RequestMapping("/saveUser")
    //因为user有一个date的成员变量，默认只能为xxxx/xx/xx xx:xx:xx的形式，写其他形式会报400错误，也就是类型转换错误
    //我们需要自定义一个类型转换器
    public String saveUser(User user){
        System.out.println(user);
        return "success";
    }

    /**
     * 测试Javaweb原生api
     * @return
     */
    @RequestMapping("/test/Servlet")
    public String servlet(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getContextPath());//获取上下文路径
        System.out.println(request.getSession());//获取session
        System.out.println(request.getCookies());//获取cookie
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        System.out.println(servletContext);
        return "success";
    }

    @RequestMapping("/body")
    //如果用RequestBody注解,中文不会被转码，需要我们手动转码，如果直接用一个类接收，不写@RequestBody的话，解析是正常的
    //也就是说，@RequestBody传过来的数据，不会经过我们的过滤器
    public String body(@RequestBody String body){
        String decode = null;
        try {
            decode = URLDecoder.decode(body, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(decode);

        return "success";
    }

    @RequestMapping("/rest/{id}")
    //@PathVariable，rest风格，获取请求路径上的id值,后面的sid名字是无所谓的，相当于绑定了,类似requestparam
    public String rest(@PathVariable("id") String sid){
        System.out.println(sid);
        return "success";
    }

    @RequestMapping("/test/requestheader")
    //Requestheader，获取请求头中指定的值
    public String requestheader(@RequestHeader("accept") String lala){
        System.out.println(lala);
        return "success";
    }

    @RequestMapping("/test/cookieValue")
    //tomcat，会给每个连接创建session，并给浏览器返回一个jsessionid的cookie
    //注意:jsessionid区分大小写，必须全大写才行
    public String cookieValue(@CookieValue("JSESSIONID") String cookie){
        System.out.println(cookie);
        return "success";
    }

    //这个写法与下面被注释掉的ComUser配套
//    @RequestMapping("/test/modelAttribute")
//    public String attribute(User user){
//        System.out.println("attribute执行了");
//        System.out.println(user);
//        return "success";
//    }


    //在方法上用ModelAttribute，在该控制器中执行所有方法前，都会先执行该方法
    //应用场景：可以让数据库对象有些字段，前台不提交时，不是null值，而是给定我们想要的默认值
    //比如该例中，我们想让用户修改某些字段（age），但是它的date和sex字段，是不会被改变的，而且，如果它
    //不传ager这个字段，就会用30这个原来的默认值
//    @ModelAttribute
//    public User ComUser(String age){
//        //假设数据库中已经有这个用户，这个用户逇age、date、sex如下，现在他要修改age，但是其他的都不会动
//        //如果不传age，那么在调用/test/modelAttribute时，age就是30，如果传了age，那么就会以用户上传的age为准
//        User user = new User();
//        user.setAge(30);
//        user.setDate(new Date());
//        user.setSex("男");
//        System.out.println("ComUser执行了");
//        return user;
//    }
//
    //第二种 @ModelAttribute写法，也可以加在参数上,表示从model中取对应的值，此时可以不需要返回值

    @ModelAttribute
    public void ComUser(String age, Map<String,User> map){
        //模拟通过数据库查询
        User user = new User();
        user.setAge(30);
        user.setDate(new Date());
        user.setSex("男");
        map.put("abc",user);
        System.out.println("ComUser执行了");
    }

    @RequestMapping("/test/modelAttribute")
    public String attribute(@ModelAttribute("abc") User user) {
        System.out.println("attribute执行了");
        System.out.println(user);
        return "success";
    }


    /**
     * 测试放入参数，到session和request（请求域中）
     * 测试sessionsttribute注解
     * 如果我们想存个值，然后在success.jsp中取，一种是依赖原生的httpServletRequest的setAttribute方法，但是这样太依赖
     * javax.servlet这个jar包，耦合度高
     * @param
     * @return
     */
    @RequestMapping("/test/sessionattributes")
    //model,中可以存值，放入request域中，实现了request的功能
    public String session(Model model) {
        model.addAttribute("msg","meimei");
        System.out.println("sessionattributes执行了");
        System.out.println();
        return "success";
    }

    /**
     * ModelMap,可以从session域中获取值
     * @param model
     * @return
     */
    @RequestMapping("/test/getsessionattributes")
    //model,中可以存值，放入request域中，实现了request的功能
    public String session(ModelMap model) {
        String msg = (String)model.get("msg");
        System.out.println("getsessionattributes执行了");
        System.out.println(msg);
        return "success";
    }

    @RequestMapping("/test/deletesessionattributes")
    //model,中可以存值，放入request域中，实现了request的功能
    public String deletesession(SessionStatus sessionStatus) {
        sessionStatus.setComplete();//设置session的状态为完成，就会清楚session中的数据
        System.out.println("deletesessionattributes执行了");
        return "success";
    }

    //后续操作session时，其实我们用原生的httpsession后更多些，而且有了SpringSession后，会依赖那个组件的session
    public String httpSession(HttpSession session){
        session.setAttribute("hah","heihie");
        session.removeAttribute("hah");
        session.invalidate();
        return "success";
    }


}

