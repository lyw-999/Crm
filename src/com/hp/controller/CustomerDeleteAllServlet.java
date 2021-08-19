package com.hp.controller;

import com.alibaba.fastjson.JSON;
import com.hp.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CustomerDeleteAllServlet" ,urlPatterns ="/CustomerDeleteAllServlet" )
public class CustomerDeleteAllServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html ; charset=UTF-8");
        //2. 重点 servlet 接受数组数据
        String[] parameterValues = req.getParameterValues("ids[]");
        CustomerService service = new CustomerService();
        //3.遍历获取id
        for (String idstr : parameterValues) {
            int i =service.delete(Integer.parseInt(idstr));
            System.out.println("i = " + i);
         }

        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","ok");
        String s = JSON.toJSONString(map);

        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.close();
    }
}
