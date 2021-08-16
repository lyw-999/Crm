package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.bean.User;
import com.hp.dao.Userr;
import com.hp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "InsertServlet" ,urlPatterns = "/InsertServlet")
public class InsertServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html ; charset=UTF-8");
        //2.接受 User的属性

        String username = req.getParameter("username");
        System.out.println("username = " + username);

        String password  = req.getParameter("password");
        System.out.println("password = " + password);

        String real_name = req.getParameter("real_name");
        System.out.println("real_name = " + real_name);

        String img = req.getParameter("img");
        System.out.println("img = " + img);

        String type = req.getParameter("type");
        System.out.println("type = " + type);

        String is_del = req.getParameter("is_del");
        System.out.println("is_del = " + is_del);

        String create_time = req.getParameter("create_time");
        System.out.println("create_time = " + create_time);
        String modify_time = req.getParameter("modify_time");
        System.out.println("modify_time = " + modify_time);

        Userr us = new Userr();
        User user = new User();

        user.setUsername(username);
        user.setType(Integer.parseInt(type));
        user.setReal_name(real_name);
        user.setPassword(password);
        user.setModify_time(modify_time);
        user.setIs_del(Integer.parseInt(is_del));
        user.setImg(img);
        user.setCreate_time(create_time);

        int i  = us.addUser(user);
        System.out.println("提交成功 " + i);

    }
}
