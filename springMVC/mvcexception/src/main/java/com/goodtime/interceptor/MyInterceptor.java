package com.goodtime.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 编写自定义拦截器，因为jdk1.8对接口做了增强，可以有默认实现了
 * @author goodtime
 * @create 2020-03-22 7:58 下午
 */

public class MyInterceptor implements HandlerInterceptor {



    /**
     * 预处理，
     * return true; 如果有下一个拦截器，就执行下一个拦截器，如果没有，就执行controller中的方法
     * rerturn false;不放行，比如可以让拦截器跳转到其他页面，（比如springsecurity,就是用拦截器链，实现的权限管理）
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器执行了");
        //这里可以执行页面转发
//        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
        return true;
    }

    //最后的处理，视图页面解析完成后，才会执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器结尾");
    }

    //后处理，在视图解析之前执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //这里也可以执行页面转发
//        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);

        System.out.println("拦截器后处理执行了");
    }
}
