package com.atjianyi.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 100;
        int height = 50;

        //1 画出图片
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //2 美化图片
        //2.1 设置背景颜色
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.pink);
        g.fillRect(0,0,width,height);
        //2.2 画出边框
        g.setColor(Color.black);
        g.drawRect(0,0,width-1,height-1);

        //3 画出验证码
        //3.1 验证码的来源
        String code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMEOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        //3.2 验证码的输入
        for (int i= 1;i<=4;i++){
            char ch = code.charAt(random.nextInt(code.length()));
            stringBuilder.append(ch); //存储验证码
            g.drawString(ch+"",width/5*i ,height/2);
        }
        String code1 = stringBuilder.toString();

        //将验证码保存到session当中
        HttpSession session = req.getSession();
        session.setAttribute("check",code1);

        g.setColor(Color.green);

        //4 画出干扰线
        for (int i=0;i<10;i++){
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);

            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);

            g.drawLine(x1,y1,x2,y2);
        }



        //5 输出到页面展示
        ImageIO.write(bufferedImage,"jpg",resp.getOutputStream());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
