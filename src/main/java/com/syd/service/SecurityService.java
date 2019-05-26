package com.syd.service;

import com.alibaba.fastjson.JSON;
import com.syd.dao.SecurityDAO;
import com.syd.model.Security;
import com.syd.util.DmsUtil;
import com.syd.util.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SecurityService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);


    @Autowired
    private SecurityDAO securityDAO;


    public List<Security> getSecurityList() {
        List<Security> list = securityDAO.getSecurityList();
        return list;
    }
    public List<Security> getSecurityList_Page(int pageIndex, int pageSize) {
        //分页
        List<Security> list = securityDAO.getSecurityList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public List<Security> getSecurityList_time(int pageIndex, int pageSize, String startDate, String endDate) {
        //分页
        List<Security> list = securityDAO.getSecurityList_time((pageIndex - 1) * pageSize, pageSize, startDate, endDate);

        return list;
    }

    public Page<Security> findAllSecurityWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Security> allSecurity  = securityDAO.getSecurityList();
        int totalCount = allSecurity.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(securityDAO.getSecurityList_Page(startRow, pageSize));

        return page;
    }
    public Page<Security> findAllSecurityWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Security> allSecurity = securityDAO.getSecurityList_time_all(startDate, endDate);
        int totalCount = allSecurity.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(securityDAO.getSecurityList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteSecurity(int id) {
        return securityDAO.deleteSecurity(id);
    }

    public int updateStatus(int id, int status) {
        return securityDAO.updateStatus(id, status);
    }


    public int add(int dorm, String electricity, String dangerGood, String lockDoor) {

        //判断用户名是否注册过
        Security security = securityDAO.selectByName(dorm);
        if (security != null) {
            return 0;
        }
        security = new Security();
        security.setDorm(dorm);
        security.setDangerGood(dangerGood);
        security.setElectricity(electricity);
        security.setLockDoor(lockDoor);
        security.setDate(new Date());
        return securityDAO.addSecurity(security);

    }

    public String data(int id) {
        Security security = securityDAO.selectById(id);
        return  JSON.toJSONString(security);
    }

    public int update(int id, int dorm, String electricity, String dangerGood, String lockDoor) {

        return securityDAO.update(id, dorm, electricity, dangerGood, lockDoor);
    }

    public List<Security> getSecurityList_student(int dorm) {
        return  securityDAO.getSecurityList_student(dorm);
    }
}
