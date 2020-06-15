package com.atjianyi.web.servlet;

import com.atjianyi.domain.User;
import com.atjianyi.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 修改用户时，回响信息
 */
@WebServlet("/FindUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 修改字符
        req.setCharacterEncoding("UTF-8");
        //2. 获取id，根据id查询user
        String id = req.getParameter("id");
        User user =  new UserServiceImpl().findUserById(Integer.parseInt(id));

        //3. 封装对象，转发给前端
        req.setAttribute("user",user);
        req.getRequestDispatcher("update.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
