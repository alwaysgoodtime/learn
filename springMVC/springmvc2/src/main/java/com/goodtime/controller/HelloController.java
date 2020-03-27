package com.goodtime.controller;

import com.goodtime.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author goodtime
 * @create 2020-03-21 7:56 下午
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class HelloController {

    /**
     * 测试返回值为String，被视图解析器解析
     * @param model
     * @return
     */
    @RequestMapping("/testString")
    public String hello(Model model){
        //模拟从数据库查询对象
        User user = new User();
        user.setAge(18);
        user.setUsername("meimei");
        user.setPassword("123456");
        model.addAttribute("abc",user);//用model存值，是个map键值对，存到request域中
        model.addAttribute(user);//这样是直接存对象
        return "success";
    }

    //返回值为void
    //默认会请求跳转到/WEB-INF/pages/user/testVoid.jsp页面
    //这样需要我们在pages下写个user文件，然后里面创建个testVoid.jsp页面
    @RequestMapping("/testVoid")
    public void rtVoid(Model model){
        System.out.println("testVoid执行了");
    }

    //返回值为void，处理方式1
    @RequestMapping("/void1")
    public void rtVoid1(HttpServletRequest request, HttpServletResponse response){
        try {
            //此时视图解析器不会解析，我们需要写相对于项目名的绝对路径
            request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Void1执行了");
        return;//加上return之后，后续的代码就不会执行了
    }

    //返回值为void，处理方式2，重定向
    @RequestMapping("/void2")
    public void rtVoid2(HttpServletRequest request, HttpServletResponse response){
        try {
            //重定向，此时要加项目名，给浏览器看，也就是request.getContextPath()
            //注意：重定向是无法跳转到web-inf下面的页面的，只能转发到其中才行
            //注意2：index.jsp，记得加/，如果不加/，又是相对路径，会跳转到/user/index.jsp
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Void2执行了");
        return;//加上return之后，后续的代码就不会执行了
    }


    //用response直接写回值
    @RequestMapping("/void3")
    public void rtVoid3(HttpServletRequest request, HttpServletResponse response){

        response.setCharacterEncoding("utf-8");//设置响应编码方式
        response.setContentType("text/html;charset=UTF-8");//设置浏览器解析的编码和类型

        try {
            response.getWriter().print("<h1>你好<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Void3执行了");
        return;//加上return之后，后续的代码就不会执行了
    }

    //用springmvc的方式进行转发，注意：返回值为String，return后的代码也不会执行
    @RequestMapping("/void4")
    public String rtVoid4(){
        System.out.println("Void4执行了");
        //forward不会被视图解析器解析
        //以前转发是request.getRequestDispatcher(path).forward(request，response);
        //return "forward:/WEB-INF/pages/index.jsp";
        //这样可以转发到index.jsp,不会被视图解析器解析，记得forward:之后加/，表示为绝对路径
        //不加就又是相对路径了，在这里，就是相对于/user的路径
        return "forward:/WEB-INF/pages/success.jsp";
        //return "forward:../WEB-INF/pages/success.jsp";//下面这种写法也是可以的，只不过这样是相对路径的写法
        //return "forward:/user/void1";  //这种写法也可以，相当于借助别的接口实现页面跳转
    }

    //用springmvc的方式进行重定向，注意：返回值为String，return后的代码也不会执行
    @RequestMapping("/void5")
    public String rtVoid5(){

        //return "redirect:/index.jsp";
        //这种是重定向操作，我们以前用response.sendRedirect()转发，还需要写项目的路径名，比如localhost:8080，因为是给浏览器显示的
        //但现在，这一步由spring底层处理了，直接写项目名下的路径名即可。
        //不过同样无法转发到Web-inf中的页面

        System.out.println("Void5执行了");

        return "redirect:/index.jsp";
    }

    //返回值是modelandview对象
    @RequestMapping("/testModelAndView")
    public ModelAndView modelAndView(){
        //模拟从数据库查询对象
        User user = new User();
        user.setAge(18);
        user.setUsername("meimei");
        user.setPassword("123456");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);//就是调用modelmap存keyvalue值，modelmap实现了model，
        //所以还是将user这个键值对放到了request域中
        modelAndView.setViewName("success");//这是view的功能，其实我们return，返回的也是一个view对象
        return modelAndView;
    }


}

