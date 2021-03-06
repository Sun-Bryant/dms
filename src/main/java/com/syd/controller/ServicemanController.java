package com.syd.controller;


import com.syd.model.Manager;
import com.syd.model.Serviceman;
import com.syd.service.SensitiveService;
import com.syd.service.ServicemanService;
import com.syd.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
public class ServicemanController {
    private static Logger logger = LoggerFactory.getLogger(ServicemanController.class);

    @Autowired
    private ServicemanService servicemanService;


    @Autowired
    private SensitiveService sensitiveService;

    @RequestMapping(path = {"/serviceman/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getManagerList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
//        System.out.println(pageIndex);

        List<Serviceman> list = servicemanService.getManagerList_Page(pageIndex, 2);
        Page<Serviceman> page = servicemanService.findAllManagerWithPage(pageIndex, 2);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "pages/member/list_service";
    }

    @RequestMapping(path = {"/serviceman/name"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String name(Model model,
                        @RequestParam(value = "name") String name) {
        System.out.println(name);
//        System.out.println(end);
        Serviceman serviceman = servicemanService.name(name);
        if (serviceman != null) {
            model.addAttribute("serviceman", serviceman);
            return "pages/member/list_name_serviceman";
        } else {
            return "pages/member/list_name_serviceman_not";
        }
    }

    @RequestMapping(path = {"/serviceman/info"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String info(Model model, @RequestParam(value = "name") String name) {
//        System.out.println(name);
        Serviceman serviceman = servicemanService.info(name);
        model.addAttribute("serviceman", serviceman);
        return "pages/breakdown/list_info";
    }


    @RequestMapping(path = {"/serviceman/add"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String add(Model model,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "password") String password,
                      @RequestParam(value = "gender") String gender,
                      @RequestParam(value = "iphone") String iphone,
                      @RequestParam(value = "email") String email) {
        name = HtmlUtils.htmlEscape(name);//html过滤
        name = sensitiveService.filter(name);//敏感词过滤

        if (servicemanService.add(name, password, gender, iphone, email) > 0) {
            return "1";
        }else {
            return "0";
        }
    }

    @RequestMapping(path = {"/serviceman/pass"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public int pass(Model model,
                      @RequestParam(value = "id") int id,
                      @RequestParam(value = "oldpass") String oldpass,
                      @RequestParam(value = "password") String password) {
//        if (servicemanService.getPassByName(name, password, gender, iphone, email) > 0) {
//            return "1";
//        }else {
//            return "0";
//        }
        int result = servicemanService.pass(id, oldpass, password);
        return result;
    }


    @RequestMapping(path = {"/serviceman/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private int deleteManager(Model model, @RequestParam(value = "id") int id) {
        return servicemanService.deleteManager(id);
    }

    @RequestMapping(path = {"/serviceman/updateStatus"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private int updateStatus(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
        return servicemanService.updateStatus(id, status);
    }

    @RequestMapping(path = {"/serviceman/data"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private String data(Model model,@RequestParam(value = "id") int id) {
        return servicemanService.data(id);
    }
//
//    @RequestMapping(path = {"/manager/time"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String time(Model model,
//                        @RequestParam(value = "start") String start,
//                        @RequestParam(value = "end") String end) {
//
////        return servicemanService.data(id);
////        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );
////        try {
////            Date dateStart = sdf.parse(start);
////            Date dateEnd = sdf.parse(end);
//////            System.out.println(dateStart);
////
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
//
////        System.out.println(start);
////        System.out.println(end);
////        servicemanService.time(start, end);
//        return "redirect:/manager/list/1/" + start + "/" + end;
////        return "login";
//    }
//

    @RequestMapping(path = {"/serviceman/update"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String update(Model model,
                      @RequestParam(value = "id") int id,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "gender") String gender,
                      @RequestParam(value = "iphone") String iphone,
                      @RequestParam(value = "email") String email) {
        if (servicemanService.update(id,name, gender, iphone, email) > 0) {
            return "1";
        }else {
            return "0";
        }
    }

}
