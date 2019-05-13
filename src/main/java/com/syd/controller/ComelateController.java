package com.syd.controller;


import com.syd.model.Comelate;
import com.syd.service.ComelateService;
import com.syd.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ComelateController {
    private static Logger logger = LoggerFactory.getLogger(ComelateController.class);
//
//    @Autowired
//    private ComelateService ComelateService;
//
//    @RequestMapping(path = {"/Comelate/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String getComelateList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
////        System.out.println(pageIndex);
//
//        List<Comelate> list = ComelateService.getComelateList_Page(pageIndex, 2);
//        Page<Comelate> page = ComelateService.findAllComelateWithPage(pageIndex, 2);
//        model.addAttribute("list", list);
//        model.addAttribute("page", page);
//        model.addAttribute("start", page.getStart());
//        model.addAttribute("end", page.getEnd());
//        return "pages/member/list";
//    }
//
//    @RequestMapping(path = {"/Comelate/list/{pageIndex}/{startDate}/{endDate}"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String getComelateList_time(Model model,
//                                       @PathVariable("pageIndex") int pageIndex,
//                                       @PathVariable("startDate") String startDate,
//                                       @PathVariable("endDate") String endDate) {
//        System.out.println(startDate);
//        System.out.println(endDate);
//
//        List<Comelate> list = ComelateService.getComelateList_time(pageIndex, 2, startDate, endDate);
//        Page<Comelate> page = ComelateService.findAllComelateWithPageTime(pageIndex, 2, startDate, endDate);
//        model.addAttribute("list", list);
//        model.addAttribute("page", page);
//        System.out.println(page.getStart());
//        System.out.println(page.getEnd());
//
//        model.addAttribute("start", page.getStart());
//        model.addAttribute("end", page.getEnd());
//        return "./pages/member/list1";
//    }
//
//    @RequestMapping(path = {"/Comelate/add"}, method = {RequestMethod.POST,RequestMethod.GET})
//    @ResponseBody
//    public String add(Model model,
//                      @RequestParam(value = "name") String name,
//                      @RequestParam(value = "password") String password,
//                      @RequestParam(value = "gender") String gender,
//                      @RequestParam(value = "iphone") String iphone,
//                      @RequestParam(value = "email") String email) {
//        if (ComelateService.add(name, password, gender, iphone, email) > 0) {
//            return "1";
//        }else {
//            return "0";
//        }
//    }
//
//
//    @RequestMapping(path = {"/Comelate/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int deleteComelate(Model model, @RequestParam(value = "id") int id) {
//        return ComelateService.deleteComelate(id);
//    }
//
//    @RequestMapping(path = {"/Comelate/updateStatus"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int updateStatus(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
//        return ComelateService.updateStatus(id, status);
//    }
//
//    @RequestMapping(path = {"/Comelate/data"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private String data(Model model,@RequestParam(value = "id") int id) {
//        return ComelateService.data(id);
//    }
//
//    @RequestMapping(path = {"/Comelate/time"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String time(Model model,
//                        @RequestParam(value = "start") String start,
//                        @RequestParam(value = "end") String end) {
//
////        return ComelateService.data(id);
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
////        ComelateService.time(start, end);
//        return "redirect:/Comelate/list/1/" + start + "/" + end;
////        return "login";
//    }
//
//
//    @RequestMapping(path = {"/Comelate/update"}, method = {RequestMethod.POST,RequestMethod.GET})
//    @ResponseBody
//    public String update(Model model,
//                      @RequestParam(value = "id") int id,
//                      @RequestParam(value = "name") String name,
//                      @RequestParam(value = "gender") String gender,
//                      @RequestParam(value = "iphone") String iphone,
//                      @RequestParam(value = "email") String email) {
//        if (ComelateService.update(id,name, gender, iphone, email) > 0) {
//            return "1";
//        }else {
//            return "0";
//        }
//    }

}
