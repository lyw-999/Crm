package com.hp.filter;

import com.hp.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginSessionFilter" ,urlPatterns = "/*")
public class LoginSessionFilter implements Filter {
    public void destroy() {
        System.out.println("过滤器死亡了");
    }

    /**
     *
     * @param req  请求
     * @param resp  响应
     * @param chain  过滤链 相当于保安 有权利让他 进/不进
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //根据session来判断 有则进入 没有退出到登录页
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("usersession = " + user);
        //  http://localhost:8080/login.html
        String requestURI =request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        if (requestURI.equals("/login.html")
                || requestURI.equals("regist.html")
                || requestURI.equals("/CodeServlet")
                || requestURI.equals("/LoginServlet")
                || requestURI.equals("/layui-v2.5.6/layui/css/layui.css")
                || requestURI.equals("/layui-v2.5.6/layui/layui.js")
                || requestURI.equals("/favicon.ico")
                || requestURI.equals( "/layui-v2.5.6/layui/lay/modules/form.js")
                || requestURI.equals( "/layui-v2.5.6/layui/lay/modules/layer.js" )
                || requestURI.equals( "/layui-v2.5.6/layui/css/modules/layer/default/layer.css" )
                || requestURI.equals( "/layui-v2.5.6/layui/lay/modules/jquery.js")

        ) {
            chain.doFilter(req, resp);// 履行 的意思不然不让进
        }else{
        if (user != null) {
            chain.doFilter(req, resp);// 履行 的意思不然不让进
        }else{
            //  跳转到登录
            response.sendRedirect("/login.html");
        }
    }
}

    public void init(FilterConfig config) throws ServletException {
        System.out.println("过滤器出生了，出生的特别早，项目一运行就出生了");
    }

}
