package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 基本servlet，通过反射调用不同实体类的控制servlet，简化代码
 * @author goodtime
 * @create 2020-01-03 8:39 下午
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String requestURI = req.getRequestURI();
        int i = requestURI.lastIndexOf("/");
        String substring = requestURI.substring(requestURI.lastIndexOf("/")+1);
        try {
            Method method = this.getClass().getMethod(substring, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
