package com.syd.controller;


import com.syd.model.Breakdown;
import com.syd.service.BreakdownService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BreakdownController {
    private static Logger logger = LoggerFactory.getLogger(BreakdownController.class);

    @Autowired
    private BreakdownService breakdownService;
//
//    @RequestMapping(path = {"/breakdown/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String getbreakdownList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
////        System.out.println(pageIndex);
//        List<Breakdown> list = breakdownService.getBreakdownList_Page(pageIndex, 2);
//        Page<Breakdown> page = breakdownService.findAllBreakdownWithPage(pageIndex, 2);
//        model.addAttribute("list", list);
//        model.addAttribute("page", page);
//        model.addAttribute("start", page.getStart());
//        model.addAttribute("end", page.getEnd());
//        return "pages/member/list";
//    }
////
////    @RequestMapping(path = {"/breakdown/list/{pageIndex}/{startDate}/{endDate}"}, method = {RequestMethod.GET, RequestMethod.POST})
////    private String getbreakdownList_time(Model model,
////                                       @PathVariable("pageIndex") int pageIndex,
////                                       @PathVariable("startDate") String startDate,
////                                       @PathVariable("endDate") String endDate) {
////        System.out.println(startDate);
////        System.out.println(endDate);
////
////        List<Breakdown> list = breakdownService.getBreakdownList_time(pageIndex, 2, startDate, endDate);
////        Page<Breakdown> page = breakdownService.findAllBreakdownWithPageTime(pageIndex, 2, startDate, endDate);
////        model.addAttribute("list", list);
////        model.addAttribute("page", page);
////        System.out.println(page.getStart());
////        System.out.println(page.getEnd());
////
////        model.addAttribute("start", page.getStart());
////        model.addAttribute("end", page.getEnd());
////        return "./pages/member/list1";
////    }
//
////    @RequestMapping(path = {"/breakdown/add"}, method = {RequestMethod.POST,RequestMethod.GET})
////    @ResponseBody
////    public String add(Model model,
////                      @RequestParam(value = "name") String name,
////                      @RequestParam(value = "password") String password,
////                      @RequestParam(value = "gender") String gender,
////                      @RequestParam(value = "iphone") String iphone,
////                      @RequestParam(value = "email") String email) {
////        if (breakdownService.add(name, password, gender, iphone, email) > 0) {
////            return "1";
////        }else {
////            return "0";
////        }
////    }
//
//    @RequestMapping(path = {"/breakdown/pass"}, method = {RequestMethod.POST,RequestMethod.GET})
//    @ResponseBody
//    public int pass(Model model,
//                      @RequestParam(value = "id") int id,
//                      @RequestParam(value = "oldpass") String oldpass,
//                      @RequestParam(value = "password") String password) {
////        if (breakdownService.getPassByName(name, password, gender, iphone, email) > 0) {
////            return "1";
////        }else {
////            return "0";
////        }
//        int result = breakdownService.pass(id, oldpass, password);
//        return result;
//    }
//
//
//    @RequestMapping(path = {"/breakdown/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int deletebreakdown(Model model, @RequestParam(value = "id") int id) {
//        return breakdownService.deleteBreakdown(id);
//    }
//
//    @RequestMapping(path = {"/breakdown/updateStatus"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int updateStatus(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
//        return breakdownService.updateStatus(id, status);
//    }
//
//    @RequestMapping(path = {"/breakdown/data"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private String data(Model model,@RequestParam(value = "id") int id) {
//        return breakdownService.data(id);
//    }
//
//    @RequestMapping(path = {"/breakdown/time"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String time(Model model,
//                        @RequestParam(value = "start") String start,
//                        @RequestParam(value = "end") String end) {
//
////        return breakdownService.data(id);
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
////        breakdownService.time(start, end);
//        return "redirect:/breakdown/list/1/" + start + "/" + end;
////        return "login";
//    }
//
//
//    @RequestMapping(path = {"/breakdown/update"}, method = {RequestMethod.POST,RequestMethod.GET})
//    @ResponseBody
//    public String update(Model model,
//                      @RequestParam(value = "id") int id,
//                      @RequestParam(value = "name") String name,
//                      @RequestParam(value = "gender") String gender,
//                      @RequestParam(value = "iphone") String iphone,
//                      @RequestParam(value = "email") String email) {
//        if (breakdownService.update(id,name, gender, iphone, email) > 0) {
//            return "1";
//        }else {
//            return "0";
//        }
//    }

}
