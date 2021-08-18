package com.hp.dao;

import com.hp.bean.Customer;
import com.hp.bean.User;
import com.hp.util.DBHelper;
import com.hp.util.PageBeanUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDao {
    // 1. 带参数的全查(2个表的 ) // 作业1
    public List<Map> selectAll(Map map){

        String page = (String) map.get("page");
        String limit = (String) map.get("limit");

        String cust_name = (String) map.get("cust_name");
        String cust_phone = (String) map.get("cust_phone");
        String cust_sex = (String) map.get("cust_sex");
        String username = (String) map.get("username");
        String modify_time = (String) map.get("modify_time");
        //1.打开连接
        Connection connection = DBHelper.getConnection();
        //2. 书写sql语句
        // select * from customer c join user u on c.user_id = u.id where 后面要加参数
        String sql = "select c.* ,t.username as username , t.password as password ,  t.real_name as real_name , t.type as type   from customer c  join user  t  on c.user_id  = t.id  where 1=1 ";
        if (null != cust_name && cust_name.length()>0) {
            sql = sql + " and cust_name like '%" +cust_name +"%' ";
        }
        if (null !=cust_phone && cust_phone.length()>0) {
            sql = sql + " and cust_phone = " +cust_phone +"  ";
        }
        if (null !=cust_sex) {
            sql = sql + " and cust_sex = " +cust_sex +" ";
        }
        if (null !=username && username.length()>0) {
            sql = sql + " and username like '%" +username +"%' ";
        }
        if (null !=modify_time && modify_time.length()>0) {
            sql = sql + " and modify_time = " +modify_time +" ";
        }
        sql = sql + " and t.is_del=1   ";
        sql = sql + "limit ? ,?" ;
        System.out.println("dao 的 sql = " + sql);
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        PageBeanUtil pageBeanUtil = new PageBeanUtil(Integer.parseInt(page),Integer.parseInt(limit));
        try {
            //3. 预编译对象
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBeanUtil.getStart());//索引
            ps.setInt(2,Integer.parseInt(limit));
            // 4.执行sql
            rs = ps.executeQuery();
            while (rs.next()){
              Map dataMap = new HashMap();
                    dataMap.put("id",rs.getInt("id"));
                    dataMap.put("cust_name",rs.getString("cust_name"));
                    dataMap.put("cust_company",rs.getString("cust_company"));
                    dataMap.put("cust_position",rs.getString("cust_position"));
                    dataMap.put("cust_phone",rs.getString("cust_phone"));
                    dataMap.put("cust_birth",rs.getString("cust_birth"));
                    dataMap.put("cust_sex",rs.getInt("cust_sex"));
                    dataMap.put("cust_desc",rs.getString("cust_desc"));
                    dataMap.put("user_id",rs.getInt("user_id"));
                    dataMap.put("create_time",rs.getString("create_time"));
                    dataMap.put("modify_time",rs.getString("modify_time"));
                    dataMap.put("username",rs.getString("username"));
                    dataMap.put("password",rs.getString("password"));
                    dataMap.put("real_name",rs.getString("real_name"));
                    dataMap.put("type",rs.getString("type"));

                    list.add(dataMap);
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
        return list;
    }
    //2. 带参数的查总条数 (2个表的) // 作业2:
    public int selectAllCount(Map map){

        int total =0;
        //1. 创建连接
        Connection connection = DBHelper.getConnection();
        //2.书写sql语句
        String sql = "select count(*) total  from customer c join user u on c.user_id = u.id where   1=1";// where 1=1因为有多余的 and

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

    // 添加
    public int addUser(Customer customer){
        //第一步 创建连接对象
        Connection connection = DBHelper.getConnection();
        //第二步  sql语句
        String sql ="insert into customer values (null,?,?,?,?,?,?,?,?)";

        PreparedStatement ps =null;
        int i =0;
        try {
            //第三步 预编译 sql
            ps =  connection.prepareStatement(sql);


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


    // 查询总条数
    public int selectCount(Map map1){

        int total =0;
        //1. 创建连接
        Connection connection = DBHelper.getConnection();
        //2.书写sql语句
        String sql = "select count(*) total  from customer where   1=1";// where 1=1因为有多余的 and
//        if (null != real_name&& real_name.length()>0) {
//            sql = sql + " and real_name like '%" +real_name +"%' ";
//        }
//        if (null !=type) {
//            sql = sql + " and type = " +type +" ";
//        }
//        if (null !=username) {
//            sql = sql + " and username like '%" +username +"%' ";
//        }
        System.out.println("customer 的 sql= " + sql);
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
    // 动态的带参数的分页查询   page 是 页数  条数 是limit
    public List<Customer> selectAllByParam(Map map){

        String page = (String) map.get("page");
        String limit = (String) map.get("limit");

        List<Customer> lists = new ArrayList<>();
        //1.创建连接
        Connection connection = DBHelper.getConnection();
        //2.sql语句
        String sql = "select * from customer where 1=1  ";// where 1=1因为有多余的 and

        sql = sql + "limit ? ,?" ;
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
                Customer cust  =new Customer();
                cust.setId(rs.getInt("id"));
                cust.setCust_name(rs.getString("cust_name"));
                cust.setCust_company(rs.getString("cust_company"));
                cust.setCust_position(rs.getString("cust_position"));
                cust.setCust_phone(rs.getString("cust_phone"));
                cust.setCust_birth(rs.getString("cust_birth"));
                cust.setCust_sex(rs.getInt("cust_sex"));
                cust.setCust_desc(rs.getString("cust_desc"));
                cust.setUser_id(rs.getInt("user_id"));
                cust.setCreate_time(rs.getString("create_time"));
                cust.setModify_time(rs.getString("modify_time"));
                lists.add(cust );
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


    public static void main(String[] args) {
        CustomerDao dao = new CustomerDao();
        Map paramMap = new HashMap();
//        // 全查

//        paramMap.put("page","1");
//        paramMap.put("limit","5");
//        List<Map> maps = dao.selectAll(paramMap);
//        System.out.println("maps = " + maps);
//        System.out.println("maps.size() = " + maps.size());
        //查询总条数

        int i = dao.selectAllCount(paramMap);
        System.out.println("i = " + i);
    }

}


