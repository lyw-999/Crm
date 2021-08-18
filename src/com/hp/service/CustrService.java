package com.hp.service;

import com.hp.bean.Customer;
import com.hp.dao.CustomerDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustrService {

    // 带参数的分页查询
    public Map selectAllByParam(Map map1){
        CustomerDao customerDao = new CustomerDao();
        List<Customer> cs = customerDao.selectAllByParam(map1);
        int i = customerDao.selectCount(map1);

        Map map = new HashMap();
        //  map.put("code",0); //必须和layui的json 返回的格式一样
        map.put("code",0);
        map.put("msg","数据查询成功");
        map.put("count",i);
        map.put("data",cs);
        // 根据layui的格式 去封装数据 如果不一样,需要layui解析.
        return map;
    }

    //  课堂查询
    public Map selectAll(Map map){
        CustomerDao customerDao = new CustomerDao();
        List<Map>  maps = customerDao.selectAll(map);


        Map codemap = new HashMap();
        codemap.put("code",0);
        codemap.put("msg","数据查询成功");
        codemap.put("data",maps);

        Map countMap = selectAllCount(map);
        int count = (int)countMap.get("data");
        codemap.put("count",count);
        return codemap;
    }
    // 总条数查询
    public Map selectAllCount(Map map){
        Map codemap = new HashMap();

        CustomerDao customerDao = new CustomerDao();
        int i =customerDao.selectAllCount(map);

        codemap.put("code",0);
        codemap.put("msg","ok");
        codemap.put("data",i);
        return codemap;
    }
}
