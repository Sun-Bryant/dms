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
    private SecurityDAO SecurityDAO;


    public List<Security> getSecurityList() {
        List<Security> list = SecurityDAO.getSecurityList();
        return list;
    }
    public List<Security> getSecurityList_Page(int pageIndex, int pageSize) {
        //分页
        List<Security> list = SecurityDAO.getSecurityList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public List<Security> getSecurityList_time(int pageIndex, int pageSize, String startDate, String endDate) {
        //分页
        List<Security> list = SecurityDAO.getSecurityList_time((pageIndex - 1) * pageSize, pageSize, startDate, endDate);

        return list;
    }

    public Page<Security> findAllSecurityWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Security> allSecurity  = SecurityDAO.getSecurityList();
        int totalCount = allSecurity.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(SecurityDAO.getSecurityList_Page(startRow, pageSize));

        return page;
    }
    public Page<Security> findAllSecurityWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Security> allSecurity = SecurityDAO.getSecurityList_time_all(startDate, endDate);
        int totalCount = allSecurity.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(SecurityDAO.getSecurityList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteSecurity(int id) {
        return SecurityDAO.deleteSecurity(id);
    }

    public int updateStatus(int id, int status) {
        return SecurityDAO.updateStatus(id, status);
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
        Security  Security= SecurityDAO.selectByName(name);
        if (Security != null) {
            map.put("msg", "用户名已被注册");
            return 0;
        }

        return SecurityDAO.addSecurity(Security);

    }


    public String data(int id) {
        Security Security = SecurityDAO.selectById(id);
        return  JSON.toJSONString(Security);
    }

    public int update(int id, String name, String gender, String iphone, String email) {
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        return SecurityDAO.update(id, name, gender1, iphone, email);
    }

}
