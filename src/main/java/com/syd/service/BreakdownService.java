package com.syd.service;

import com.alibaba.fastjson.JSON;
import com.syd.dao.BreakdownDAO;
import com.syd.model.Breakdown;
import com.syd.model.Breakdown;
import com.syd.util.DmsUtil;
import com.syd.util.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class BreakdownService {
    private static final Logger logger = LoggerFactory.getLogger(BreakdownService.class);

    @Autowired
    private BreakdownDAO breakdownDAO;

    public List<Breakdown> getBreakdownList() {
        List<Breakdown> list = breakdownDAO.getBreakdownList();
        return list;
    }
    public List<Breakdown> getBreakdownList_Page(int pageIndex, int pageSize) {
        //分页
        List<Breakdown> list = breakdownDAO.getBreakdownList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

//    public List<Breakdown> getBreakdownList_time(int pageIndex, int pageSize, String startDate, String endDate) {
//        //分页
//        List<Breakdown> list = breakdownDAO.getBreakdownList_time((pageIndex - 1) * pageSize, pageSize, startDate, endDate);
//
//        return list;
//    }

    public Page<Breakdown> findAllBreakdownWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Breakdown> allBreakdown  = breakdownDAO.getBreakdownList();
        int totalCount = allBreakdown.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(breakdownDAO.getBreakdownList_Page(startRow, pageSize));

        return page;
    }

    public Page<Breakdown> findAllBreakdownWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Breakdown> allBreakdown = breakdownDAO.getBreakdownList_time_all(startDate, endDate);
        int totalCount = allBreakdown.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(breakdownDAO.getBreakdownList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteBreakdown(int id) {
        return breakdownDAO.deleteBreakdown(id);
    }

    public int updateStatus(int id, int status) {
        return breakdownDAO.updateStatus(id, status);
    }


    public int add(String breakContent) {
        Breakdown breakdown = new Breakdown();
        breakdown.setBreakContent(breakContent);
        breakdown.setStatus(0);
        breakdown.setExamine(0);
        return breakdownDAO.addBreakdown(breakdown);

    }

//    public int pass(int id, String oldpass, String password) {
//        Breakdown breakdown = breakdownDAO.selectById(id);
//        if (breakdown == null) {
//            return 0;
//        }
//        //判断旧密码是否正确
//        if (!DmsUtil.MD5(oldpass + breakdown.getSalt()).equals(Breakdown.getPassword())) {
//            return 0;
//        } else {
//            //设置新密码
//            Breakdown.setSalt(UUID.randomUUID().toString().substring(0, 5));
//            Breakdown.setPassword(DmsUtil.MD5(password + Breakdown.getSalt()));
//            int result = breakdownDAO.updatePass(id, Breakdown.getSalt(), Breakdown.getPassword());
//            return result;
//        }
//    }

    public String data(int id) {
        Breakdown breakdown = breakdownDAO.selectById(id);
        return  JSON.toJSONString(breakdown);
    }

    public int update(int id, String name, String gender, String iphone, String email) {
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        return breakdownDAO.update(id, name, gender1, iphone, email);
    }

}
