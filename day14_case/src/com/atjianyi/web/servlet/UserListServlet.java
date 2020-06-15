package com.atjianyi.web.servlet;

import com.atjianyi.domain.User;
import com.atjianyi.service.UserService;
import com.atjianyi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用Service层查询
        List<User> users = userService.findAll();
        //1. 存入request当中转发
        req.setAttribute("list",users);
        //2. 转发数据
        req.getRequestDispatcher("list.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
