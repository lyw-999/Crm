package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.dao.CustomerDao;
import com.hp.dao.UserDao;
import com.hp.service.CustomerService;
import com.hp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "CustomerDeleteServlet" ,urlPatterns = "/CustomerDeleteServlet")
public class CustomerDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html ; charset=UTF-8");

        CustomerService service = new CustomerService();
        String id = req.getParameter("id");
        System.out.println("id = " + id);

        // 调用dao 层
        CustomerDao dao = new CustomerDao();
        int i = dao.delete(Integer.parseInt(id));
        System.out.println("删除成功 " + i);
    }
}
