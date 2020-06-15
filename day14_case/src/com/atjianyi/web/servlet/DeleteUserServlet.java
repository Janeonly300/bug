package com.atjianyi.web.servlet;

import com.atjianyi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 设置字符集
        request.setCharacterEncoding("UTF-8");
        //2. 获取用户的id
        String id = request.getParameter("id");
        //3. 调用service层
        new UserServiceImpl().delUser(id);
        //4. 转发向到list
        response.sendRedirect(request.getContextPath()+"/FindUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
