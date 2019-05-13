package com.syd.service;

import com.alibaba.fastjson.JSON;
import com.syd.dao.DormDAO;
import com.syd.model.Dorm;
import com.syd.util.DmsUtil;
import com.syd.util.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DormService {
    private static final Logger logger = LoggerFactory.getLogger(DormService.class);


    @Autowired
    private DormDAO DormDAO;


    public List<Dorm> getDormList() {
        List<Dorm> list = DormDAO.getDormList();
        return list;
    }
    public List<Dorm> getDormList_Page(int pageIndex, int pageSize) {
        //分页
        List<Dorm> list = DormDAO.getDormList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }


    public Page<Dorm> findAllDormWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Dorm> allDorm  = DormDAO.getDormList();
        int totalCount = allDorm.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(DormDAO.getDormList_Page(startRow, pageSize));

        return page;
    }
    public Page<Dorm> findAllDormWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Dorm> allDorm = DormDAO.getDormList_time_all(startDate, endDate);
        int totalCount = allDorm.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(DormDAO.getDormList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteDorm(int id) {
        return DormDAO.deleteDorm(id);
    }

    public int updateStatus(int id, int status) {
        return DormDAO.updateStatus(id, status);
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
        Dorm  dorm= DormDAO.selectByName(name);
        if (dorm != null) {
            map.put("msg", "已经有了");
            return 0;
        }
        //正常
        dorm = new Dorm();

        dorm.setCapacity(1);
        dorm.setUtilities(543);

        return DormDAO.addDorm(dorm);

    }


    public String data(int id) {
        Dorm Dorm = DormDAO.selectById(id);
        return  JSON.toJSONString(Dorm);
    }

    public int update(int id, String name, String gender, String iphone, String email) {
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        return DormDAO.update(id, name, gender1, iphone, email);
    }

}
