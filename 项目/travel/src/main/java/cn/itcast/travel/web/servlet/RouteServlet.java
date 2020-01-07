package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-01-05 4:43 下午
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    RouteService routeService = new RouteServiceImpl();
    ObjectMapper objectMapper = new ObjectMapper();

    public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String currentPage =request.getParameter("page");
        String rname = request.getParameter("rname");
        String pageCount = request.getParameter("pagecount");
        PageBean pageBean = routeService.queryForCate(cid, currentPage, pageCount,rname);
        String info = objectMapper.writeValueAsString(pageBean);
        response.getWriter().write(info);

        }

    public void queryForRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        String coll = request.getParameter("coll");
        String username = (String)request.getSession().getAttribute("username");
        List li = routeService.queryForRoute(rid,username,coll);
        String routeInfo = objectMapper.writeValueAsString(li);
        response.getWriter().write(routeInfo);
    }

    }
