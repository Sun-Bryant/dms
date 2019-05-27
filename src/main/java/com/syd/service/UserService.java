package com.syd.service;


import com.syd.dao.*;
import com.syd.model.*;
import com.syd.util.DmsUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ServicemanDAO servicemanDAO;

    @Autowired
    private ManagerDAO managerDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    public RootManager selectByName(String name) {
        return userDAO.selectByName(name);
    }

    public RootManager getUser(int id) {
        return userDAO.selectById(id);
    }

    public Map<String, Object> regieter(String username, String password) {
        //先判断是否为空， 即进行格式检查
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }
        //判断用户名是否注册过
        RootManager user = userDAO.selectByName(username);
        if (user != null) {
            map.put("msg", "用户名已被注册");
            return map;
        }
        //正常注册
        user = new RootManager();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        user.setHeadUrl(head);
        user.setPassword(DmsUtil.MD5(password + user.getSalt()));
        userDAO.addUser(user);

        // 下发t票
        String ticket = addLoginTicket(user.getId(),1);
        map.put("ticket", ticket);
        return map;
    }

    public Map<String, Object> login(String username, String password) {

        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }
        if ("root".equals(username)) {
            RootManager rootManager = userDAO.selectByName(username);
            if (rootManager == null) {
                map.put("msg", "用户名不存在");
                return map;
            }
            if (!DmsUtil.MD5(password+rootManager.getSalt()).equals(rootManager.getPassword())) {
                map.put("msg", "密码不正确");
                return map;
            }
            String ticket = addLoginTicket(rootManager.getId(), 1);
            map.put("ticket", ticket);
            map.put("root", "root");
        } else if ("wx".equals(username.substring(0, 2))) {
            Serviceman serviceman = servicemanDAO.selectByName(username);
            if (serviceman == null) {
                map.put("msg", "用户名不存在");
                return map;
            }
            if (!DmsUtil.MD5(password+serviceman.getSalt()).equals(serviceman.getPassword())) {
                map.put("msg", "密码不正确");
                return map;
            }
            String ticket = addLoginTicket(serviceman.getId(), 2);
            map.put("ticket", ticket);
            map.put("wx", "wx");
        } else if ("g".equals(username.substring(0, 1))) {
//            System.out.println(username);
            Manager manager = managerDAO.selectByName(username);
            if (manager == null) {
                map.put("msg", "用户名不存在");
                return map;
            }
            if (!DmsUtil.MD5(password+manager.getSalt()).equals(manager.getPassword())) {
                map.put("msg", "密码不正确");
                return map;
            }
            String ticket = addLoginTicket(manager.getId(), 3);
            map.put("ticket", ticket);
            map.put("g", "g");
        } else {
            int no = Integer.parseInt(username);
            Student student = studentDAO.selectByNo(no);
            if (student == null) {
                System.out.println("-------");

                map.put("msg", "用户名不存在");
                return map;
            }
            if (!DmsUtil.MD5(password+student.getSalt()).equals(student.getPassword())) {
                map.put("msg", "密码不正确");
                return map;
            }
            String ticket = addLoginTicket(student.getNo(), 4);
            map.put("ticket", ticket);
            map.put("s", "s");

        }
        return map;
    }

    //下发（增加）t票
    private String addLoginTicket(int userId, int type) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setType(type);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.addTicket(ticket);
//        System.out.println(result);

        return ticket.getTicket();
    }
    public void logout(String ticket) {
        loginTicketDAO.updateStatus(ticket, 1);
    }

}
