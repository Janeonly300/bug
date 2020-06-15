package com.atjianyi.web.servlet;

import com.atjianyi.domain.PageBean;
import com.atjianyi.domain.User;
import com.atjianyi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("utf-8");

        //1. 获取当前页码，和页放置条数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if ("".equals(currentPage)||currentPage==null){
            currentPage="1";
        }
        if ("".equals(rows)||rows==null){
            rows="5";
        }
        //接收map集合
        Map<String, String[]> condition = request.getParameterMap();


        //2. 调用service层调用查询
        PageBean<User> pageBean =  new UserServiceImpl().findUserByPage(currentPage,rows,condition);

        System.out.println(pageBean);

        //3. 将pageBean转发到前端
        request.setAttribute("pb",pageBean);
        request.setAttribute("condition",condition);
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
