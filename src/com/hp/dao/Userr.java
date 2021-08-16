package com.hp.dao;


import com.hp.bean.User;
import com.hp.util.DBHelper;
import com.hp.util.PageBeanUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Userr {
    // 增 删 改 查
    // dao层 如何 和数据库做对接 ,我们用的知识点叫做jdbc

    // 登录
    public User login(String username,String password){
            User user  =null;

            //1.创建连接
            Connection  connection = DBHelper.getConnection();
            //2. sql语句
            String sql="select * from user where username=? and password=?" ;
            // 3.使用连接对象获取预编译对象
            PreparedStatement ps =null;
            ResultSet rs = null;
        try {
            ps =connection.prepareCall(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs =ps.executeQuery();
            //4.执行预编译对象,将结果取出来
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setCreate_time(rs.getString("create_time"));
                user.setImg(rs.getString("img"));
                user.setIs_del(rs.getInt("is_del"));
                user.setModify_time(rs.getString("modify_time"));
                user.setPassword(rs.getString("password"));
                user.setReal_name(rs.getString("real_name"));
                user.setType(rs.getInt("type"));
                user.setUsername(rs.getString("username"));
            }
        } catch (Exception e) {
            // TODO Auto- generated catch block
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return user;
    }
    // 查询总条数
    public int selectCount(Map map1){
        String real_name = (String) map1.get("real_name");
        String type = (String) map1.get("type");
        String username = (String) map1.get("username");

        int total =0;
        //1. 创建连接
        Connection connection = DBHelper.getConnection();
        //2.书写sql语句
        String sql = "select count(*) total  from user where   1=1";// where 1=1因为有多余的 and
        if (null != real_name&& real_name.length()>0) {
            sql = sql + " and real_name like '%" +real_name +"%' ";
        }
        if (null !=type) {
            sql = sql + " and type = " +type +" ";
        }
        if (null !=username) {
            sql = sql + " and username like '%" +username +"%' ";
        }
        System.out.println("count 的 sql= " + sql);
        PreparedStatement ps = null;
        ResultSet rs = null;
        //3. 预编译sql
        try {
             ps = connection.prepareStatement(sql);
             //4. 执行预编译对象
              rs = ps.executeQuery();
            if (rs.next()) {
               total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return total;
    }
    // 动态的带参数的分页查询
    //  page 是 页数  条数 是limit
    public List<User> selectAllByParam(Map map){

        String page = (String) map.get("page");
        String limit = (String) map.get("limit");
        String real_name = (String) map.get("real_name");
        String type = (String) map.get("type");
        String username = (String) map.get("username");
        // 如果说  real_name 不为空
        //sql  =   select * from t_user where  real_name like %张%  limit ? , ?
        // 如果说  type 不为空   real_name 不为空
        /// sql  =   select * from t_user where  real_name like %张%  and  type=1   limit ? , ?
        // 如果说  type 不为空   real_name 不为空    username  不为空
        /// sql  =   select * from t_user where  real_name like %张%  and  type=1  and username like %李%   limit ? , ?

            List<User> lists = new ArrayList<>();
        //1.创建连接
        Connection connection = DBHelper.getConnection();
        //2.sql语句
        String sql = "select * from user where 1=1  ";// where 1=1因为有多余的 and
        if (null != real_name&& real_name.length()>0) {
            sql = sql + " and real_name like '%" +real_name +"%' ";
        }
        if (null !=type) {
            sql = sql + " and type = " +type +" ";
        }
        if (null !=username) {
            sql = sql + " and username like '%" +username +"%' ";
        }
        sql = sql + "limit ? ,?" ;
        System.out.println("dao 的 sql = " + sql);
        //3.编译 sql
        PreparedStatement ps = null;
        ResultSet rs = null;
        PageBeanUtil pageBeanUtil = new PageBeanUtil(Integer.parseInt(page),Integer.parseInt(limit));
        try {
             ps = connection.prepareStatement(sql);
             ps.setInt(1,pageBeanUtil.getStart());//索引
             ps.setInt(2,Integer.parseInt(limit));
             // 4.执行sql
              rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setCreate_time(rs.getString("create_time"));
                user.setImg(rs.getString("img"));
                user.setIs_del(rs.getInt("is_del"));
                user.setModify_time(rs.getString("modify_time"));
                user.setPassword(rs.getString("password"));
                user.setReal_name(rs.getString("real_name"));
                user.setType(rs.getInt("type"));
                user.setUsername(rs.getString("username"));

                lists.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  lists;
    }
    //全查
    public  List<User> selectAll() {

        // 步骤1: 创建出 连接对象
        Connection connection = DBHelper.getConnection();
        // 步骤2: 创建出 sql 语句
        String sql = "select * from user";

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        try {
            // 步骤3: 使用连接对象 获取 预编译对象
            ps = connection.prepareStatement(sql);
            //  步骤4: 执行 预编译对象得出结果集
            rs = ps.executeQuery();
            // 步骤5: 遍历结果
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setCreate_time(rs.getString("create_time"));
                user.setImg(rs.getString("img"));
                user.setIs_del(rs.getInt("is_del"));
                user.setModify_time(rs.getString("modify_time"));
                user.setPassword(rs.getString("password"));
                user.setReal_name(rs.getString("real_name"));
                user.setType(rs.getInt("type"));
                user.setUsername(rs.getString("username"));

                 list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // 添加
    public int addUser(User user){
        //第一步 创建连接对象
        Connection connection = DBHelper.getConnection();
        //第二步  sql语句
        String sql ="insert into user values (null,?,?,?,?,?,?,?,?)";

        PreparedStatement ps =null;
        int i =0;
        try {
            //第三步 预编译 sql
            ps =  connection.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getReal_name());
            ps.setString(4,user.getImg());
            ps.setInt(5,user.getType());
            ps.setInt(6,user.getIs_del());
            ps.setString(7,user.getCreate_time());
            ps.setString(8,user.getModify_time());

            //第四步 执行预编译对象
            i =ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
    //删除
    public int delete(int id) {

        Connection connection=null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            //第一步 创建连接对象
            connection = DBHelper.getConnection();
            //第二步  sql语句
            String sql = "delete from user where id = ?";
            //第三步 预编译 sql
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            //第四步 执行预编译对象
            i = ps.executeUpdate();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return i;
    }
    //修改
    public int update(User user) {
        // TODO Auto-generated method stub

        Connection connection=null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            // 第一步创建连接对象
            connection =DBHelper.getConnection();
            // 第二步 书写sql语句
            String sql = "update user set   username=?, password =?,real_name=?,img=?,type =?,is_del=?,create_time=?,modify_time=? where id = ?";
            //第三步 预编译sql
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername() );
            ps.setString(2, user.getPassword() );
            ps.setString(3, user.getReal_name() );
            ps.setString(4, user.getImg() );
            ps.setInt(5, user.getType() );
            ps.setInt(6, user.getIs_del() );
            ps.setString(7, user.getCreate_time() );
            ps.setString(8, user.getModify_time() );
            ps.setInt(9, user.getId());
            //执行预编译对象
            i = ps.executeUpdate();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return i;
    }


    //修改 是否能用
    public int updateDel(Integer sfDel,Integer userId ) {
        // TODO Auto-generated method stub

        Connection connection=null;
        PreparedStatement ps = null;
        int i = 0;
        try {
            // 第一步创建连接对象
            connection =DBHelper.getConnection();
            // 第二步 书写sql语句
            String sql = "update user set  is_del=? where id = ? ";
            //第三步 预编译sql
            ps = connection.prepareStatement(sql);
            ps.setInt(1, sfDel );
            ps.setInt(2, userId );
            //执行预编译对象
            i = ps.executeUpdate();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return i;
    }

    public static void main(String[] args) {
        //全查//
       Userr us = new Userr();
        // 修改 是否能用
        int sfDel = us.updateDel(1,39);
        System.out.println("del = " + sfDel);
        //     User user = new User();
//        List<User> list = us.selectAll();
//        for (User user : list) {
//            System.out.println("user = " + user);
//        }

          // 添加
//        user.setUsername("SuperS");
//        user.setType(1);
//        user.setReal_name("起飞");
//        user.setPassword("123");
//        user.setModify_time("2012-12-12");
//        user.setIs_del(1);
//        user.setImg("xxx");
//        user.setCreate_time("2012-12-12");
//        int i =  us.addUser(user);
//        System.out.println("i = " + i);

        //删除
//        int i =us.delete(62);
//        System.out.println("i = " + i);

         // 修改
//        user.setUsername("SuperS");
//        user.setType(1);
//        user.setReal_name("芜湖");
//        user.setPassword("123");
//        user.setModify_time("2011-11-11");
//        user.setIs_del(5);
//        user.setImg("xxx");
//        user.setCreate_time("2021-11-1");
//        user.setId(64);
//        int i=us.update(user);
//        System.out.println("i = " + i);
        //登录的测试
//        User user =us.login("yyy","345");
//        System.out.println("user = " + user);

        //分页查询的 测试
//        List<User> users = us.selectAllByParam( 1,5);
//        System.out.println("users = " + users);
//        System.out.println("users.size() = " + users.size());


        // 查询 总条数
//        int i = us.selectCount();
//        System.out.println("i = " + i);
    }


}
