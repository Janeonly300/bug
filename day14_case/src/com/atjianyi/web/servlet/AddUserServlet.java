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
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 设置字符集
        request.setCharacterEncoding("UTF-8");
        //2. 获取jsp页面提交的
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        //3. 使用beanUtils封装大对象
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4. 将对象传入service层
        new UserServiceImpl().addUser(user);
        //5. 转发到list
        request.getRequestDispatcher("UserListServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
