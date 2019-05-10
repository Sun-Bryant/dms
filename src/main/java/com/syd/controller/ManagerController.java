package com.syd.controller;


import com.syd.model.Manager;
import com.syd.service.ManagerService;
import com.syd.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManagerController {
    private static Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;

    @RequestMapping(path = {"/manager/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getManagerList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
        List<Manager> list = managerService.getManagerList_Page(pageIndex, 2);
        Page<Manager> page = managerService.findAllManagerWithPage(pageIndex, 2);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "./pages/member/list";
    }

    @RequestMapping(path = {"/manager/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private int deleteManager(Model model, @RequestParam(value = "id") int id) {
        return managerService.deleteManager(id);
    }

    @RequestMapping(path = {"/manager/updateStatus"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private int updateStatus(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
        return managerService.updateStatus(id, status);
    }

    @RequestMapping(path = {"/test"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String test(Model model) {
        return "./pages/member/add";
    }

}
