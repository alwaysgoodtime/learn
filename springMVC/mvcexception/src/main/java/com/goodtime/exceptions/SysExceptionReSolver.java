package com.goodtime.exceptions;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author goodtime
 * @create 2020-03-22 7:38 下午
 */
public class SysExceptionReSolver implements HandlerExceptionResolver {

    /**
     * 处理controller抛出的异常
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return
     */

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //获取到异常对象
        Exception exception = null;
        if(e instanceof SysException){//说明抛出的是我们想捕获的异常
            exception = (SysException)e;
        }else{
            e = new SysException("系统正在维护");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg",exception.getMessage());
        modelAndView.setViewName("error");//跳到异常页面
        return modelAndView;
    }
}
