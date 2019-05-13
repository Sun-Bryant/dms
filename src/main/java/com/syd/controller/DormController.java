package com.syd.controller;


import com.syd.model.Dorm;
import com.syd.service.DormService;
import com.syd.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DormController {
    private static Logger logger = LoggerFactory.getLogger(DormController.class);

    @Autowired
    private DormService dormService;

    @RequestMapping(path = {"/dorm/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getDormList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
//        System.out.println(pageIndex);

        List<Dorm> list = dormService.getDormList_Page(pageIndex, 2);
        Page<Dorm> page = dormService.findAllDormWithPage(pageIndex, 2);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "pages/member/list_dorm";
    }

//    @RequestMapping(path = {"/Dorm/list/{pageIndex}/{startDate}/{endDate}"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String getDormList_time(Model model,
//                                       @PathVariable("pageIndex") int pageIndex,
//                                       @PathVariable("startDate") String startDate,
//                                       @PathVariable("endDate") String endDate) {
//        System.out.println(startDate);
//        System.out.println(endDate);
//
//        List<Dorm> list = DormService.getDormList_time(pageIndex, 2, startDate, endDate);
//        Page<Dorm> page = DormService.findAllDormWithPageTime(pageIndex, 2, startDate, endDate);
//        model.addAttribute("list", list);
//        model.addAttribute("page", page);
//        System.out.println(page.getStart());
//        System.out.println(page.getEnd());
//
//        model.addAttribute("start", page.getStart());
//        model.addAttribute("end", page.getEnd());
//        return "./pages/member/list1";
//    }


    @RequestMapping(path = {"/dorm/add"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String add(Model model,
                      @RequestParam(value = "dorm") int dorm,
                      @RequestParam(value = "capacity") int capacity,
                      @RequestParam(value = "utilities") double utilities) {
        System.out.println(dorm);
        System.out.println(capacity);
        if (dormService.add(dorm, capacity, utilities) > 0) {
            return "1";
        }else {
            return "0";
        }
    }


    @RequestMapping(path = {"/dorm/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private int deleteDorm(Model model, @RequestParam(value = "dorm") int dorm) {
        return dormService.deleteDorm(dorm);
    }

//    @RequestMapping(path = {"/Dorm/updateStatus"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int updateStatus(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
//        return DormService.updateStatus(id, status);
//    }
//
    @RequestMapping(path = {"/dorm/data"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private String data(Model model,@RequestParam(value = "dorm") int dorm) {
        return dormService.data(dorm);
    }
//
//    @RequestMapping(path = {"/Dorm/time"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String time(Model model,
//                        @RequestParam(value = "start") String start,
//                        @RequestParam(value = "end") String end) {
//
////        return DormService.data(id);
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
////        DormService.time(start, end);
//        return "redirect:/Dorm/list/1/" + start + "/" + end;
////        return "login";
//    }
//
//
    @RequestMapping(path = {"/dorm/update"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String update(Model model,
                      @RequestParam(value = "dorm") int dorm,
                      @RequestParam(value = "capacity") int capacity,
                      @RequestParam(value = "utilities") double utilities) {
        if (dormService.update(dorm, capacity,utilities) > 0) {
            return "1";
        }else {
            return "0";
        }
    }

}
