package com.syd.service;

import com.alibaba.fastjson.JSON;
import com.syd.dao.ManagerDAO;
import com.syd.model.Manager;
import com.syd.util.DmsUtil;
import com.syd.util.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ManagerService {
    private static final Logger logger = LoggerFactory.getLogger(ManagerService.class);

    @Autowired
    private ManagerDAO managerDAO;

    public List<Manager> getManagerList() {
        List<Manager> list = managerDAO.getManagerList();
        return list;
    }
    public List<Manager> getManagerList_Page(int pageIndex, int pageSize) {
        //分页
        List<Manager> list = managerDAO.getManagerList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public Page<Manager> findAllManagerWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Manager> allManager  = managerDAO.getManagerList();
        int totalCount = allManager.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(managerDAO.getManagerList_Page(startRow, pageSize));

        return page;
    }

    public int deleteManager(int id) {
        return managerDAO.deleteManager(id);
    }

    public int updateStatus(int id, int status) {
        return managerDAO.updateStatus(id, status);
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
        Manager  manager= managerDAO.selectByName(name);
        if (manager != null) {
            map.put("msg", "用户名已被注册");
            return 0;
        }
        //正常
        manager = new Manager();
        manager.setName(name);
        manager.setSalt(UUID.randomUUID().toString().substring(0, 5));
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        manager.setHeadUrl(head);
        manager.setPassword(DmsUtil.MD5(password + manager.getSalt()));
        manager.setDate(new Date());
        manager.setEmail(email);
        manager.setIphone(iphone);
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        manager.setGender(gender1);
        manager.setStatus(0);

        return managerDAO.addmanager(manager);

    }

    public int pass(int id, String oldpass, String password) {
        Manager manager = managerDAO.selectById(id);
        if (manager == null) {
            return 0;
        }
        //判断旧密码是否正确
        if (!DmsUtil.MD5(oldpass + manager.getSalt()).equals(manager.getPassword())) {
            System.out.println("------");
            return 0;
        } else {
            //设置新密码
            manager.setSalt(UUID.randomUUID().toString().substring(0, 5));
            manager.setPassword(DmsUtil.MD5(password + manager.getSalt()));
            int result = managerDAO.updatePass(id, manager.getSalt(), manager.getPassword());
            return result;
        }
    }

    public String data(int id) {
        Manager manager = managerDAO.selectById(id);
        return  JSON.toJSONString(manager);
    }

    public int update(int id, String name, String gender, String iphone, String email) {
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        return managerDAO.update(id, name, gender1, iphone, email);
    }
}
