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
    private DormDAO dormDAO;

//
//    public List<Dorm> getDormList() {
//        List<Dorm> list = dormDAO.getDormList();
//        return list;
//    }

    public List<Dorm> getDormList_Page(int pageIndex, int pageSize) {
        //分页
        List<Dorm> list = dormDAO.getDormList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public Page<Dorm> findAllDormWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Dorm> allDorm  = dormDAO.getDormList();
        int totalCount = allDorm.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(dormDAO.getDormList_Page(startRow, pageSize));

        return page;
    }

//    public Page<Dorm> findAllDormWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
//        List<Dorm> allDorm = DormDAO.getDormList_time_all(startDate, endDate);
//        int totalCount = allDorm.size();
//
//        //使用这三个参数，创建一个Page对象。
//        Page page = new Page(pageIndex, pageSize, totalCount);
//        //获取page中的StartRow（数据库起始记录指针）
//        int startRow = page.getStartRow();
//        //有了startRow和pageSize就可以拿到每页的数据。
//        page.setList(dormDAO.getDormList_time(startRow, pageSize, startDate, endDate));
//
//        return page;
//    }
//
    public int deleteDorm(int dorm) {
        return dormDAO.deleteDorm(dorm);
    }

//    public int updateStatus(int id, int status) {
//        return dormDAO.updateStatus(id, status);
//    }


    public int add(int dorm, int capacity, double utilities) {

        Dorm  dorm1= dormDAO.selectByName(dorm);
        if (dorm1 != null) {
            return 0;
        }
        //正常
        dorm1 = new Dorm();
        dorm1.setDorm(dorm);
        dorm1.setCapacity(capacity);
        dorm1.setUtilities(utilities);

        return dormDAO.addDorm(dorm1);

    }

    public String data(int dorm) {
        Dorm dorm1 = dormDAO.selectById(dorm);
        return  JSON.toJSONString(dorm1);
    }

    public int update(int dorm, int capacity, double utilities ) {
        return dormDAO.update(dorm, capacity, utilities);
    }

    public double getUtilities(int dorm) {
        return dormDAO.getUtilities(dorm);
    }

    public int updateCapacity(int dorm) {
        return dormDAO.updateCapacity(dorm);
    }
}
