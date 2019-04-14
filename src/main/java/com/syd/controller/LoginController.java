package com.syd.controller;


import com.syd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping(path = {"/register"},method = {RequestMethod.POST})
    public String register(Model model,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "password") String password) {

        try {
            Map<String, String> map = userService.register(name, password);
            if (map.containsKey("msg")) {
                model.addAttribute("msg", map.get("msg"));
                return "login";
            }
            return "redirect:/";
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            return "register";
        }
    }

    @RequestMapping(path = {"/", "/to_login"})
    public String toLogin(){
        return "login";
    }

    @RequestMapping(path = { "/to_register"})
    public String toRegister(){
        return "register";
    }


}
