package com.syd.service;

import com.alibaba.fastjson.JSON;
import com.syd.dao.ServicemanDAO;
import com.syd.model.Manager;
import com.syd.model.Serviceman;
import com.syd.util.DmsUtil;
import com.syd.util.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ServicemanService {
    private static final Logger logger = LoggerFactory.getLogger(ServicemanService.class);

    @Autowired
    private ServicemanDAO servicemanDAO;

    
    
    public List<Serviceman> getManagerList() {
        List<Serviceman> list = servicemanDAO.getManagerList();
        return list;
    }
    public List<Serviceman> getManagerList_Page(int pageIndex, int pageSize) {
        //分页
        List<Serviceman> list = servicemanDAO.getManagerList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public List<Serviceman> getManagerList_time(int pageIndex, int pageSize, String startDate, String endDate) {
        //分页
        List<Serviceman> list = servicemanDAO.getManagerList_time((pageIndex - 1) * pageSize, pageSize, startDate, endDate);

        return list;
    }

    public Page<Serviceman> findAllManagerWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Serviceman> allManager  = servicemanDAO.getManagerList();
        int totalCount = allManager.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(servicemanDAO.getManagerList_Page(startRow, pageSize));

        return page;
    }
    public Page<Serviceman> findAllManagerWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Serviceman> allManager = servicemanDAO.getManagerList_time_all(startDate, endDate);
        int totalCount = allManager.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(servicemanDAO.getManagerList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteManager(int id) {
        return servicemanDAO.deleteManager(id);
    }

    public int updateStatus(int id, int status) {
        return servicemanDAO.updateStatus(id, status);
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
         Serviceman serviceman = servicemanDAO.selectByName(name);
        if (serviceman != null) {
            map.put("msg", "用户名已被注册");
            return 0;
        }
        //正常
        serviceman = new Serviceman();
        serviceman.setName(name);
        serviceman.setSalt(UUID.randomUUID().toString().substring(0, 5));
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        serviceman.setHeadUrl(head);
        serviceman.setPassword(DmsUtil.MD5(password + serviceman.getSalt()));
        serviceman.setDate(new Date());
        serviceman.setEmail(email);
        serviceman.setIphone(iphone);
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        serviceman.setGender(gender1);
        serviceman.setStatus(0);
        return servicemanDAO.addmanager(serviceman);

    }

    public int pass(int id, String oldpass, String password) {
        Serviceman serviceman = servicemanDAO.selectById(id);
        if (serviceman == null) {
            return 0;
        }
        //判断旧密码是否正确
        if (!DmsUtil.MD5(oldpass + serviceman.getSalt()).equals(serviceman.getPassword())) {
            return 0;
        } else {
            //设置新密码
            serviceman.setSalt(UUID.randomUUID().toString().substring(0, 5));
            serviceman.setPassword(DmsUtil.MD5(password + serviceman.getSalt()));
            int result = servicemanDAO.updatePass(id, serviceman.getSalt(), serviceman.getPassword());
            return result;
        }
    }

    public String data(int id) {
        Serviceman serviceman = servicemanDAO.selectById(id);
        return  JSON.toJSONString(serviceman);
    }

    public int update(int id, String name, String gender, String iphone, String email) {
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        return servicemanDAO.update(id, name, gender1, iphone, email);
    }

    public Serviceman info(String name) {
        return servicemanDAO.selectByName(name);
    }

    public Serviceman name(String name) {
        if (servicemanDAO.selectByName(name) != null) {
            return servicemanDAO.selectByName(name);
        }else {
            return null;
        }
    }
}
