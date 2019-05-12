package com.syd.controller;


import com.syd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(path = {"/login"})
    public String login(Model model,
                                @RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password,
                                HttpServletResponse response) {
        try {
            Map<String, Object> map = userService.login(username, password);
            //包含ticket信息说明登录成功   返回给用户浏览器一个cookie（包含ticket票）
            if (map.containsKey("ticket")) {
                model.addAttribute("username", username);
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return "index";
            } else {
                model.addAttribute("msg", map.get("msg"));
                return "login";
            }
        } catch (Exception e) {
            logger.error("登录异常" + e.getMessage());
            return "login";
        }
    }

    @RequestMapping(path = {"/", "/to_login"})
    public String toLogin(){
        return "login";
    }

}
