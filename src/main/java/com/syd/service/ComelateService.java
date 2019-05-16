package com.syd.service;

import com.alibaba.fastjson.JSON;
import com.syd.dao.ComelateDAO;
import com.syd.model.Comelate;
import com.syd.model.Comelate1;
import com.syd.util.DmsUtil;
import com.syd.util.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ComelateService {
    private static final Logger logger = LoggerFactory.getLogger(ComelateService.class);


    @Autowired
    private ComelateDAO comelateDAO;


    public List<Comelate> getComelateList() {
        List<Comelate> list = comelateDAO.getComelateList();
        return list;
    }

    public List<Comelate> getComelateList_Page(int pageIndex, int pageSize) {
        //分页
        List<Comelate> list = comelateDAO.getComelateList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public List<Comelate> getComelateList_time(int pageIndex, int pageSize, String startDate, String endDate) {
        //分页
        List<Comelate> list = comelateDAO.getComelateList_time((pageIndex - 1) * pageSize, pageSize, startDate, endDate);
        return list;
    }

    public Page<Comelate> findAllComelateWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Comelate> allComelate  = comelateDAO.getComelateList();
        int totalCount = allComelate.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(comelateDAO.getComelateList_Page(startRow, pageSize));

        return page;
    }
    public Page<Comelate> findAllComelateWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Comelate> allComelate = comelateDAO.getComelateList_time_all(startDate, endDate);
        int totalCount = allComelate.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(comelateDAO.getComelateList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteComelate(int id) {
        return comelateDAO.deleteComelate(id);
    }

    public int updateStatus(int id, int status) {
        return comelateDAO.updateStatus(id, status);
    }


    public int add(int studentNo, String studentName, String studentClass, Date latetime) {

        Comelate comelate= new Comelate();
        comelate.setStudentNo(studentNo);
        comelate.setStudentClass(studentClass);
        comelate.setStudentName(studentName);
        comelate.setLatetime(latetime);

        return comelateDAO.addComelate(comelate);

    }


    public String data(int id) {
        Comelate comelate = comelateDAO.selectById(id);
        Comelate1 comelate1 = new Comelate1();
        comelate1.setId(comelate.getId());
        comelate1.setStudentNo(comelate.getStudentNo());
        comelate1.setStudentName(comelate.getStudentName());
        comelate1.setStudentClass(comelate.getStudentClass());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(comelate.getLatetime());
        comelate1.setLatetime(time);
        return  JSON.toJSONString(comelate1);
    }

    public int update(int id, int studentNo, String studentName, String studentClass, Date latetime) {

        return comelateDAO.update(id, studentNo, studentName, studentClass, latetime);
    }

}
