package com.hp.service;

import com.hp.bean.User;
import com.hp.dao.UserDao;

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
        UserDao userDao = new UserDao();
        User userFromDB = userDao.login(username,password);
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
    public Map selectAllByParam(Map map1){
        UserDao us = new UserDao();
        List<User> users = us.selectAllByParam(map1);
        int i = us.selectCount(map1);

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
//        map.put("message","数据查询成功");
//        map.put("object",map);

        return map;
    }

    // 修改是否 可用
    public Map updateDel (Integer sfDel, Integer userId){
            UserDao us = new UserDao();
        int i = us.updateDel(sfDel,userId);

        Map map  = new HashMap();
        if (i == 1) {
            map.put("code",0);
            map.put("msg","修改成功");
        }else{
            map.put("code",400);
            map.put("msg","修改不成功");
        }
       return map;
    }
//String  username,String password,String  real_name,String img,Integer  type,Integer is_del,String  create_time,String modify_time

    //修改全部
    public Map Update(User user){
        Map codeMap = new HashMap();
        UserDao us = new UserDao();
        int i = us.update(user);
        if (i == 1) {
             codeMap.put("code",0);
             codeMap.put("msg","请求成功");
        }else{
            codeMap.put("code",400);
            codeMap.put("msg","请求失败");
        }
        return codeMap;
    }

    //按id 查询1个 user
    public Map selectUserByid(Integer id){
        UserDao us = new UserDao();
        List<User> users = us.selectAll();

        Map codeMap = new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","ok");
        codeMap.put("data",users);

        return codeMap;
    }
    //全查 业务员
    public Map selectAll(){
        UserDao us = new UserDao();
        List<User> users = us.selectAll();

        Map codeMap = new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","ok");
        codeMap.put("data",users);

        return codeMap;
    }

}
