package com.syd.service;

import com.alibaba.fastjson.JSON;
import com.syd.dao.WeishengDAO;
import com.syd.model.Weisheng;
import com.syd.util.DmsUtil;
import com.syd.util.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class WeishengService {
    private static final Logger logger = LoggerFactory.getLogger(WeishengService.class);


    @Autowired
    private WeishengDAO WeishengDAO;


    public List<Weisheng> getWeishengList() {
        List<Weisheng> list = WeishengDAO.getWeishengList();
        return list;
    }
    public List<Weisheng> getWeishengList_Page(int pageIndex, int pageSize) {
        //分页
        List<Weisheng> list = WeishengDAO.getWeishengList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public List<Weisheng> getWeishengList_time(int pageIndex, int pageSize, String startDate, String endDate) {
        //分页
        List<Weisheng> list = WeishengDAO.getWeishengList_time((pageIndex - 1) * pageSize, pageSize, startDate, endDate);

        return list;
    }

    public Page<Weisheng> findAllWeishengWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Weisheng> allWeisheng  = WeishengDAO.getWeishengList();
        int totalCount = allWeisheng.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(WeishengDAO.getWeishengList_Page(startRow, pageSize));

        return page;
    }
    public Page<Weisheng> findAllWeishengWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Weisheng> allWeisheng = WeishengDAO.getWeishengList_time_all(startDate, endDate);
        int totalCount = allWeisheng.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(WeishengDAO.getWeishengList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteWeisheng(int id) {
        return WeishengDAO.deleteWeisheng(id);
    }

    public int updateStatus(int id, int status) {
        return WeishengDAO.updateStatus(id, status);
    }


    public int add(String name, String password, String gender, String iphone, String email) {
        //先判断是否为空， 即进行格式检查
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
        Weisheng  Weisheng= WeishengDAO.selectByName(name);
        if (Weisheng != null) {
            map.put("msg", "用户名已被注册");
            return 0;
        }

        return WeishengDAO.addWeisheng(Weisheng);

    }


    public String data(int id) {
        Weisheng Weisheng = WeishengDAO.selectById(id);
        return  JSON.toJSONString(Weisheng);
    }

    public int update(int id, String name, String gender, String iphone, String email) {
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        return WeishengDAO.update(id, name, gender1, iphone, email);
    }

}
