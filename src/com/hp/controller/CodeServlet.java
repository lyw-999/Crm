package com.hp.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CodeServlet" ,urlPatterns = "/CodeServlet")
public class CodeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 使用 hutool 工具 创建验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        //  拿到验证码
        String  code = captcha.getCode();
        // 3.获取session
        HttpServletRequest request;
        HttpSession session = req.getSession();
        // 将验证码放入到session
        session.setAttribute("code",code);
        //5.将验证码发送到浏览器
        HttpServletResponse response;
        ServletOutputStream outputStream = resp.getOutputStream();;
         captcha.write(outputStream);
        outputStream.close();

    }
}
