package com.syd.controller;


import com.syd.model.Student;
import com.syd.model.Weisheng;
import com.syd.service.StudentService;
import com.syd.service.WeishengService;
import com.syd.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WeishengController {
    private static Logger logger = LoggerFactory.getLogger(WeishengController.class);

    @Autowired
    private WeishengService weishengService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(path = {"/weisheng/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getWeishengList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
//        System.out.println(pageIndex);

        List<Weisheng> list = weishengService.getWeishengList_Page(pageIndex, 2);
        Page<Weisheng> page = weishengService.findAllWeishengWithPage(pageIndex, 2);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "pages/member/list_weisheng";
    }

    @RequestMapping(path = {"/weisheng/list_student"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getWeishengList_Page_student(Model model, @RequestParam("name") String name) {

//        System.out.println(pageIndex);

        int no = Integer.parseInt(name);
        Student student = studentService.getStudentByNo(no);
        List<Weisheng> list = weishengService.getWeishengList_student(student.getDorm());
        model.addAttribute("list", list);

        return "pages/member/list_weisheng_student";
    }

    @RequestMapping(path = {"/weisheng/award/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getAwardList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
//        System.out.println(pageIndex);
        List<Weisheng> list = weishengService.getAwardList_Page(pageIndex, 2);
        Page<Weisheng> page = weishengService.findAllAwardWithPage(pageIndex, 2);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "pages/member/list_award";
    }


//    @RequestMapping(path = {"/Weisheng/list/{pageIndex}/{startDate}/{endDate}"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String getWeishengList_time(Model model,
//                                       @PathVariable("pageIndex") int pageIndex,
//                                       @PathVariable("startDate") String startDate,
//                                       @PathVariable("endDate") String endDate) {
//        System.out.println(startDate);
//        System.out.println(endDate);
//
//        List<Weisheng> list = WeishengService.getWeishengList_time(pageIndex, 2, startDate, endDate);
//        Page<Weisheng> page = WeishengService.findAllWeishengWithPageTime(pageIndex, 2, startDate, endDate);
//        model.addAttribute("list", list);
//        model.addAttribute("page", page);
//        System.out.println(page.getStart());
//        System.out.println(page.getEnd());
//
//        model.addAttribute("start", page.getStart());
//        model.addAttribute("end", page.getEnd());
//        return "./pages/member/list1";
//    }

    @RequestMapping(path = {"/weisheng/add"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String add(Model model,
                      @RequestParam(value = "dorm") int dorm,
                      @RequestParam(value = "floor1") int floor1,
                      @RequestParam(value = "balcony") int balcony,
                      @RequestParam(value = "bed") int bed) {
//        System.out.println(dorm);

        if (weishengService.add(dorm, floor1, balcony, bed) > 0) {
            return "1";
        } else {
            return "0";
        }
    }


    @RequestMapping(path = {"/weisheng/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private int deleteWeisheng(Model model, @RequestParam(value = "id") int id) {
        return weishengService.deleteWeisheng(id);
    }

    //    @RequestMapping(path = {"/Weisheng/updateStatus"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int updateStatus(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
//        return WeishengService.updateStatus(id, status);
//    }
//
    @RequestMapping(path = {"/weisheng/data"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private String data(Model model, @RequestParam(value = "id") int id) {
        return weishengService.data(id);
    }

    //    @RequestMapping(path = {"/Weisheng/time"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String time(Model model,
//                        @RequestParam(value = "start") String start,
//                        @RequestParam(value = "end") String end) {
//
////        return WeishengService.data(id);
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
////        WeishengService.time(start, end);
//        return "redirect:/Weisheng/list/1/" + start + "/" + end;
////        return "login";
//    }
//
//
    @RequestMapping(path = {"/weisheng/update"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String update(Model model,
                         @RequestParam(value = "id") int id,
                         @RequestParam(value = "dorm") int dorm,
                         @RequestParam(value = "floor1") int floor1,
                         @RequestParam(value = "balcony") int balcony,
                         @RequestParam(value = "bed") int bed) {
        if (weishengService.update(id, dorm, floor1, balcony, bed) > 0) {
            return "1";
        } else {
            return "0";
        }
    }

}
