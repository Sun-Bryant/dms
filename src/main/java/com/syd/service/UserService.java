package com.syd.service;


import com.syd.dao.LoginTicketDAO;
import com.syd.dao.UserDao;
import com.syd.exception.GlobalException;
import com.syd.model.LoginTicket;
import com.syd.model.User;
import com.syd.result.CodeMsg;
import com.syd.util.DmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserDao userDao;

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    public Map<String, String> register(HttpServletResponse response, String name, String password) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isBlank(name)) {
            map.put("msg", "用户名不能为空");
//            throw new GlobalException(CodeMsg.NAME_EMPTY);
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
//            throw new GlobalException(CodeMsg.PASSWORD_EMPTY);
            return map;
        }
        //判断用户名是否注册过
        User user = userDao.selectByName(name);
        if (user != null) {
            map.put("msg", "用户名已被注册");
//            throw new GlobalException(CodeMsg.NAME_REPEST_ERROR);
            return map;
        }
        user = new User();
        user.setName(name);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        user.setHeadUrl(head);
        user.setPassword(DmsUtil.MD5(password + user.getSalt()));
        userDao.addManger(user);

        //添加t票
        String ticket = addLoginTicket(response, user.getId());
        map.put("ticket", ticket);
        return map;
    }

    public Map<String, String> login(HttpServletResponse response, String name, String password) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isBlank(name)) {
            map.put("msg", "用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }
        //判断用户名是否注册过
        User user = userDao.selectByName(name);
        if (user == null) {
            map.put("msg", "用户不存在");
            return map;
        }

        if (!DmsUtil.MD5(password + user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "密码错误");
            return map;
        }

        //添加t票
        String ticket = addLoginTicket(response, user.getId());
        map.put("ticket", ticket);

        return map;
    }

    //下发（增加）t票
    private String addLoginTicket(HttpServletResponse response, int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 100*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.addTicket(ticket);

        Cookie cookie = new Cookie("ticket",ticket.getTicket());
        cookie.setPath("/");
        response.addCookie(cookie);
        return ticket.getTicket();
    }

}
