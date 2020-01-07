package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-01-05 10:12 上午
 */
@WebServlet("/cate/*")
public class CategoryServlet extends BaseServlet {

    CategoryService categoryService = new CategoryServiceImpl();
    ResultInfo resultInfo = new ResultInfo();
    ObjectMapper objectMapper = new ObjectMapper();


    public void cate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List li = categoryService.findCategory();
        resultInfo.setData(li);
        String s = objectMapper.writeValueAsString(resultInfo);
        response.setContentType("Application/json;charset=utf-8");
        response.getWriter().write(s);
    }

}

