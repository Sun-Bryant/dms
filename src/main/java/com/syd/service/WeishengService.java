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
    private WeishengDAO weishengDAO;


    public List<Weisheng> getWeishengList() {
        List<Weisheng> list = weishengDAO.getWeishengList();
        return list;
    }
    public List<Weisheng> getWeishengList_Page(int pageIndex, int pageSize) {
        //分页
        List<Weisheng> list = weishengDAO.getWeishengList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public List<Weisheng> getAwardList_Page(int pageIndex, int pageSize) {
        //分页
        List<Weisheng> list = weishengDAO.getAwardList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public List<Weisheng> getWeishengList_time(int pageIndex, int pageSize, String startDate, String endDate) {
        //分页
        List<Weisheng> list = weishengDAO.getWeishengList_time((pageIndex - 1) * pageSize, pageSize, startDate, endDate);

        return list;
    }

    public Page<Weisheng> findAllWeishengWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Weisheng> allWeisheng  = weishengDAO.getWeishengList();
        int totalCount = allWeisheng.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(weishengDAO.getWeishengList_Page(startRow, pageSize));

        return page;
    }


    public Page<Weisheng> findAllAwardWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Weisheng> allWeisheng  = weishengDAO.getAwardList();
        int totalCount = allWeisheng.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(weishengDAO.getWeishengList_Page(startRow, pageSize));

        return page;
    }

    public Page<Weisheng> findAllWeishengWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Weisheng> allWeisheng = weishengDAO.getWeishengList_time_all(startDate, endDate);
        int totalCount = allWeisheng.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(weishengDAO.getWeishengList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteWeisheng(int id) {
        return weishengDAO.deleteWeisheng(id);
    }

    public int updateStatus(int id, int status) {
        return weishengDAO.updateStatus(id, status);
    }


    public int add(int dorm, int floor1, int balcony, int bed) {
        Weisheng  weisheng= weishengDAO.selectByName(dorm);
        if (weisheng != null) {
            return 0;
        }
        weisheng = new Weisheng();
        weisheng.setDorm(dorm);
        weisheng.setFloor1(floor1);
        weisheng.setBalcony(balcony);
        weisheng.setBed(bed);
        return weishengDAO.addWeisheng(weisheng);
    }

    public String data(int id) {
        Weisheng weisheng = weishengDAO.selectById(id);
        return  JSON.toJSONString(weisheng);
    }

    public int update(int id, int dorm, int floor1, int balcony, int bed) {
        return weishengDAO.update(id, dorm, floor1, balcony, bed);
    }

}
