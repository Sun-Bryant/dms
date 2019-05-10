package com.syd.controller;


import com.syd.model.Manager;
import com.syd.service.ManagerService;
import com.syd.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ManagerController {
    private static Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;


    @RequestMapping(path = {"/manager/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getQuestions(Model model, @PathVariable("pageIndex") int pageIndex) {
        List<Manager> list = managerService.getManagerList_Page(pageIndex, 2);
        Page<Manager> page = managerService.findAllManagerWithPage(pageIndex, 2);

        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "./pages/member/list";
    }

}
