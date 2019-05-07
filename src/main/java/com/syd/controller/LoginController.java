package com.syd.controller;


import com.syd.result.CodeMsg;
import com.syd.result.Result;
import com.syd.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping(path = {"/register"},method = {RequestMethod.POST})
    @ResponseBody
    public Result<String> register(Model model,
                                   @RequestParam(value = "name") String name,
                                   @RequestParam(value = "password") String password,
                                   HttpServletResponse response) {
        try {
            Map<String, String> map = userService.register(response, name, password);
            if (map.containsKey("msg")) {
                model.addAttribute("msg", map.get("msg"));
                return Result.error(CodeMsg.REGISTER_ERROR);
            }
            return Result.success("成功");
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
        }
        return Result.error(CodeMsg.REGISTER_ERROR);
    }

    @RequestMapping(path = {"/login"},method = {RequestMethod.POST})
    @ResponseBody
    public Result<String> login(Model model,
                                   @RequestParam(value = "name") String name,
                                   @RequestParam(value = "password") String password,
                                   HttpServletResponse response) {
        try {
            Map<String, String> map = userService.login(response, name, password);
            if (map.containsKey("ticket")) {
                return Result.success("成功");
            }else{
                model.addAttribute("msg", map.get("msg"));
                return Result.error(CodeMsg.NAMEPASSWOED_ERROR);
            }
//            return Result.success("注册");
        } catch (Exception e) {
            logger.error("登录异常" + e.getMessage());
//            return "register";
        }
        return Result.error(CodeMsg.LOGIN_ERROR);
    }

    @RequestMapping(path = {"/home"})
    public String toHome(){
        return "frame";
    }

    @RequestMapping(path = {"/", "/to_login"})
    public String toLogin(){
        return "login";
    }

    @RequestMapping(path = { "/to_register"})
    public String toRegister(){
        return "register";
    }


    /**
     * just for test below
     * @return
     */

    @RequestMapping(path = {"/hello"})
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello imooc");
    }

    @RequestMapping(path = {"/helloError"})
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

}
