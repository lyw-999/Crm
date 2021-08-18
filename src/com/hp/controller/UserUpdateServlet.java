package com.hp.controller;

import com.hp.bean.User;
import com.hp.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateServlet",urlPatterns = "/UpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html ; charset=UTF-8");

        // 2.接受参数
        String id = req.getParameter("id");
        System.out.println("id = " + id);

        String username = req.getParameter("username");
        System.out.println("username = " + username);

        String password  = req.getParameter("password");
        System.out.println("password = " + password);

        String real_name = req.getParameter("real_name");
        System.out.println("real_name = " + real_name);

        String img = req.getParameter("img");
        System.out.println("img = " + img);

        String type = req.getParameter("type");

        String is_del = req.getParameter("is_del");
        System.out.println("is_del = " + is_del);

        String create_time = req.getParameter("create_time");
        System.out.println("create_time = " + create_time);
        String modify_time = req.getParameter("modify_time");
        System.out.println("modify_time = " + modify_time);

       int isdel1=((is_del=="1") ? 1 : 2);
       // int isdel1 = Integer.parseInt(is_del);
        int id1 = Integer.parseInt(id);
        int type1 = Integer.parseInt(type);
        System.out.println("id1 = " + id1);
        System.out.println("isdel1 = " + isdel1);
        System.out.println("type = " + type);
        System.out.println("type1 = " + type1);
        // 调用dao 层
        UserDao us = new UserDao();
        User user = new User();

        user.setUsername(username);
        user.setType(type1);
        user.setReal_name(real_name);
        user.setPassword(password);
        user.setModify_time(modify_time);
        user.setIs_del(isdel1);
        user.setImg(img);
        user.setCreate_time(create_time);
        user.setId(id1);

        System.out.println("user = " + user);
        int i = us.update(user);
        System.out.println("提交成功 " + i);

    }
}
