package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.MailUtilss;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.SoundbankResource;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.Map;

/**
 * @author goodtime
 * @create 2020-01-03 8:48 下午
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    User user = new User();
    ResultInfo resultInfo = new ResultInfo();
    ObjectMapper objectMapper = new ObjectMapper();
    UserService userService = new UserServiceImpl();

    /**
     * 实现注册功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (check == null || check.length() == 0 || check.equalsIgnoreCase(checkcode_server) == false) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            String json = objectMapper.writeValueAsString(resultInfo);
            PrintWriter writer = response.getWriter();
            writer.write(json);
            return;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = userService.findUserByUsername(user.getUsername());

        if (flag == true) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名已存在");
            String json = objectMapper.writeValueAsString(resultInfo);
            PrintWriter writer = response.getWriter();
            writer.write(json);
            return;
        } else {
            userService.addUser(user);
            resultInfo.setFlag(true);
            String json = objectMapper.writeValueAsString(resultInfo);
            PrintWriter writer = response.getWriter();
            writer.write(json);
            StringBuffer requestURL = request.getRequestURL();
            int i = requestURL.lastIndexOf("/");
            requestURL.delete(i,i+10);
            requestURL.append("/activateUser");
            String s = requestURL.toString();
            System.out.println(s);
            String content = "<a href='"+s+"?code=" + user.getCode() + "'>点击激活您的账号</a>";
            MailUtilss.sendEmail("验证邮件",content,user.getEmail());
            System.out.println("发送成功");
        }
    }

    public void activateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        boolean activate = userService.getUserByCode(code);
        if(!activate){
            PrintWriter writer = response.getWriter();
            writer.write("激活失败，请联系管理员");
        }else{
            PrintWriter writer = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            writer.write("激活成功，<a href='../login.html'>点击登录</a>");
        }

    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (check == null || check.length() == 0 || check.equalsIgnoreCase(checkcode_server) == false) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            String json = objectMapper.writeValueAsString(resultInfo);
            PrintWriter writer = response.getWriter();
            writer.write(json);
            return;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = userService.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (flag == false) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误");
            String json = objectMapper.writeValueAsString(resultInfo);
            PrintWriter writer = response.getWriter();
            writer.write(json);
        } else {
            boolean statusByUsername = userService.findStatusByUsername(user.getUsername());
            if (statusByUsername == false) {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账号未激活");
            } else {
                resultInfo.setFlag(true);
                HttpSession session1 = request.getSession();
                session1.setAttribute("username",user.getUsername());
            }

            String json = objectMapper.writeValueAsString(resultInfo);
            PrintWriter writer = response.getWriter();
            writer.write(json);
        }
    }

    /**
     * 主页上回写用户名
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("username");
        if(username == null || username.length() == 0){
            resultInfo.setFlag(false);
        }else{
            resultInfo.setFlag(true);
            resultInfo.setData(username);
        }

        String json = objectMapper.writeValueAsString(resultInfo);
        PrintWriter writer = response.getWriter();
        writer.write(json);

    }
    public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        response.sendRedirect(request.getContextPath()+"/login.html");
    }



}

