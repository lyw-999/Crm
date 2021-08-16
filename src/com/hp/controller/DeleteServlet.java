package com.hp.controller;

import com.hp.bean.User;
import com.hp.dao.Userr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet",urlPatterns = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html ; charset=UTF-8");
        // 2.接受参数
        String id = req.getParameter("id");
        System.out.println("id = " + id);

        // 调用dao 层
        Userr us = new Userr();
        int i = us.delete(Integer.parseInt(id));
        System.out.println("删除成功 " + i);
    }
}
