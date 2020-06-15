package com.atjianyi.web.servlet;

import com.atjianyi.domain.User;
import com.atjianyi.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 设置字符集
        request.setCharacterEncoding("UTF-8");
        //2. 获取数据
        // 2.1 获取验证码用户输入的养正吗
        String verifycode = request.getParameter("verifycode");
        //2.2 获取服务端生成的验证码
        HttpSession check = request.getSession();
        String check1 = (String)check.getAttribute("check");

        //3. 判断验证码是否正确
        if(check1.equalsIgnoreCase(verifycode)){
            //1. 如果正确
            //1.1 获取用户所有信息
            User user = new User();
            Map<String, String[]> map = request.getParameterMap();
            try {
                BeanUtils.populate(user,map);
                User login = new UserServiceImpl().login(user);
                if(login == null){
                    request.setAttribute("n_err","账号或者密码错误");
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                }else {
                    HttpSession session = request.getSession();
                    session.setAttribute("user",login);
                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //2. 如果错误装回去之前页面，显示错误信息
            request.setAttribute("code_err","验证码错误");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
