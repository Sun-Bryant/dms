package com.syd.controller;


import com.syd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                        @RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password,
                                HttpServletResponse response) {
        try {
//            System.out.println(username);
            Map<String, Object> map = userService.login(username, password);
            //包含ticket信息说明登录成功   返回给用户浏览器一个cookie（包含ticket票）
            if (map.containsKey("root")) {
                model.addAttribute("username", username);
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return "index";
            }else if (map.containsKey("wx")) {
                model.addAttribute("username", username);
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return "index_wx";
            } else if (map.containsKey("g")) {
                model.addAttribute("username", username);
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return "index_g";
            } else if (map.containsKey("s")) {
                model.addAttribute("username", username);
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return "index_s";
            } else {
                model.addAttribute("msg", map.get("msg"));
                return "redirect:/";
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

    @RequestMapping(path = {"/logout"}, method = {RequestMethod.GET})
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        return "redirect:/";
    }

//    @ExceptionHandler()
//    @ResponseBody
//    public String error(Exception e) {
//        return "error:" + e.getMessage();
//    }

}
