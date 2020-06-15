package com.atjianyi.web.servlet;

import com.atjianyi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteSelectUserServlet")
public class DeleteSelectUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1. 获取提交的信息
        String[] ids = request.getParameterValues("id");
        new UserServiceImpl().delSelectUser(ids);

        //2.在转发到到UserListServlet
        response.sendRedirect(request.getContextPath()+"/FindUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
