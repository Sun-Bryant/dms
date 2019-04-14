package com.syd.service;


import com.syd.dao.UserDao;
import com.syd.model.User;
import com.syd.util.DmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    UserDao userDao;

    public Map<String, String> register(String name, String password) {
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
        if (user != null) {
            map.put("msg", "用户名已被注册");
            return map;
        }
        user = new User();
        user.setName(name);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        user.setHeadUrl(head);
        user.setPassword(DmsUtil.MD5(password + user.getSalt()));
        userDao.addManger(user);

        return map;
    }

}
