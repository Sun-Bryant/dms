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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ComelateController {
    private static Logger logger = LoggerFactory.getLogger(ComelateController.class);

    @Autowired
    private ComelateService comelateService;

    @RequestMapping(path = {"/comelate/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getComelateList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
//        System.out.println(pageIndex);
        List<Comelate> list = comelateService.getComelateList_Page(pageIndex, 2);
        Page<Comelate> page = comelateService.findAllComelateWithPage(pageIndex, 2);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "pages/others/list_comelate";
    }

    @RequestMapping(path = {"/comelate/list1/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getComelateList_Page1(Model model, @PathVariable("pageIndex") int pageIndex) {
//        System.out.println(pageIndex);
        List<Comelate> list = comelateService.getComelateList_Page(pageIndex, 2);
        Page<Comelate> page = comelateService.findAllComelateWithPage(pageIndex, 2);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "pages/others/list_comelate_student";
    }

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

    @RequestMapping(path = {"/comelate/add"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String add(Model model,
                      @RequestParam(value = "studentNo") int studentNo,
                      @RequestParam(value = "studentName") String studentName,
                      @RequestParam(value = "studentClass") String studentClass,
                      @RequestParam(value = "latetime") String latetime) {

        System.out.println(studentNo);
        System.out.println(studentName);
        System.out.println(studentClass);
        System.out.println(latetime);
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = fmt.parse(latetime);
            if (comelateService.add(studentNo, studentName, studentClass, date) > 0) {
                return "1";
            } else {
                return "0";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }

    @RequestMapping(path = {"/comelate/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private int deleteComelate(Model model, @RequestParam(value = "id") int id) {
        return comelateService.deleteComelate(id);
    }

//    @RequestMapping(path = {"/Comelate/updateStatus"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int updateStatus(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
//        return ComelateService.updateStatus(id, status);
//    }

    @RequestMapping(path = {"/comelate/data"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private String data(Model model, @RequestParam(value = "id") int id) {
        return comelateService.data(id);
    }

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


    @RequestMapping(path = {"/comelate/update"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String update(Model model,
                         @RequestParam(value = "id") int id,
                         @RequestParam(value = "studentNo") int studentNo,
                         @RequestParam(value = "studentName") String studentName,
                         @RequestParam(value = "studentClass") String studentClass,
                         @RequestParam(value = "latetime") String latetime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = fmt.parse(latetime);
            if (comelateService.update(id, studentNo, studentName, studentClass, date) > 0) {
                return "1";
            } else {
                return "0";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }

}
