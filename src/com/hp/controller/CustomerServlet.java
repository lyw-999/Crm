package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.service.CustrService;
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

@WebServlet(name = "CustomerServlet" ,urlPatterns = "/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html ; charset=UTF-8");
        //2.接受 2个参数 page limit
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");

        Map map1 = new HashMap();
        map1.put("page",page);
        map1.put("limit",limit);

        CustrService  custrService = new CustrService();
        Map map = custrService.selectAllByParam(map1);

        String s = JSONObject.toJSONString(map);
        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.close();
    }
}
