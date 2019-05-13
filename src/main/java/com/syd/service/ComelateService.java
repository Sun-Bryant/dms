package com.syd.service;

import com.alibaba.fastjson.JSON;
import com.syd.dao.ComelateDAO;
import com.syd.model.Comelate;
import com.syd.util.DmsUtil;
import com.syd.util.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ComelateService {
    private static final Logger logger = LoggerFactory.getLogger(ComelateService.class);


    @Autowired
    private ComelateDAO ComelateDAO;


    public List<Comelate> getComelateList() {
        List<Comelate> list = ComelateDAO.getComelateList();
        return list;
    }
    public List<Comelate> getComelateList_Page(int pageIndex, int pageSize) {
        //分页
        List<Comelate> list = ComelateDAO.getComelateList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public List<Comelate> getComelateList_time(int pageIndex, int pageSize, String startDate, String endDate) {
        //分页
        List<Comelate> list = ComelateDAO.getComelateList_time((pageIndex - 1) * pageSize, pageSize, startDate, endDate);

        return list;
    }

    public Page<Comelate> findAllComelateWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Comelate> allComelate  = ComelateDAO.getComelateList();
        int totalCount = allComelate.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(ComelateDAO.getComelateList_Page(startRow, pageSize));

        return page;
    }
    public Page<Comelate> findAllComelateWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Comelate> allComelate = ComelateDAO.getComelateList_time_all(startDate, endDate);
        int totalCount = allComelate.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(ComelateDAO.getComelateList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteComelate(int id) {
        return ComelateDAO.deleteComelate(id);
    }

    public int updateStatus(int id, int status) {
        return ComelateDAO.updateStatus(id, status);
    }


    public int add(String name, String password, String gender, String iphone, String email) {
        //先判断是否为空，
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(name)) {
            map.put("msg", "用户名不能为空");
            return 0;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return 0;
        }
        //判断用户名是否注册过
        Comelate  Comelate= ComelateDAO.selectByName(name);
        if (Comelate != null) {
            map.put("msg", "用户名已被注册");
            return 0;
        }
        //正常
        Comelate comelate= new Comelate();
        comelate.setStudentNo(name);
        comelate.setStudentClass(password);
        comelate.setStudentName(iphone);


        return ComelateDAO.addComelate(Comelate);

    }


    public String data(int id) {
        Comelate Comelate = ComelateDAO.selectById(id);
        return  JSON.toJSONString(Comelate);
    }

    public int update(int id, String name, String gender, String iphone, String email) {
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        return ComelateDAO.update(id, name, gender1, iphone, email);
    }

}
