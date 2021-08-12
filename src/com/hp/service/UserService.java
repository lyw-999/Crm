package com.hp.service;

import com.hp.bean.User;
import com.hp.dao.Userr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    //登录
    public Map login(String username, String password, HttpServletRequest reqeust){
      // service层 要调用dao层
        Map map =new HashMap();
        Userr userr = new Userr();
        User userFromDB =userr.login(username,password);
        if ( null==userFromDB) {
            //没查出来,即:账户名或者密码错误
            map.put("code",4001);
            map.put("msg","账户名或者密码不正确");

            return map;
        }else {
            //当登录成功后,需要吧登录的账户密码放入到 session中去
            HttpSession session = reqeust.getSession();
            session.setAttribute("user",userFromDB);
            map.put("code",0);
            map.put("msg","登录成功");
            return map;
        }
    }

    // 带参数的分页查询
    public Map selectAllByParam(int page, int limit){
        Userr us = new Userr();
        int i = us.selectCount();
        List<User> users = us.selectAllByParam(page, limit);
        Map map = new HashMap();
      //  map.put("code",0); //必须和layui的json 返回的格式一样
        map.put("code",0);
        map.put("msg","数据查询成功");
        map.put("count",i);
        map.put("data",users);
        // 根据layui的格式 去封装数据 如果不一样,需要layui解析.
//        {
//             "code":0
//             "msg":""
//             "count":1000
//             "data":[每条数据]
//        }
//        Map map2 = new HashMap();
//        map.put("number",2001);
//        map.put("msg","数据查询成功");
//        map.put("object",map);

        return map;
    }
}
