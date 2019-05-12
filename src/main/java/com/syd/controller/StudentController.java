package com.syd.controller;


import com.syd.model.Manager;
import com.syd.model.Student;
import com.syd.service.ManagerService;
import com.syd.service.StudentService;
import com.syd.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(path = {"/student/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getManagerList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
//        System.out.println(pageIndex);

        List<Student> list = studentService.getManagerList_Page(pageIndex, 2);
        Page<Student> page = studentService.findAllManagerWithPage(pageIndex, 2);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "pages/member/list_student";
    }

//    @RequestMapping(path = {"/manager/list/{pageIndex}/{startDate}/{endDate}"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String getManagerList_time(Model model,
//                                       @PathVariable("pageIndex") int pageIndex,
//                                       @PathVariable("startDate") String startDate,
//                                       @PathVariable("endDate") String endDate) {
//        System.out.println(startDate);
//        System.out.println(endDate);
//
//        List<Manager> list = managerService.getManagerList_time(pageIndex, 2, startDate, endDate);
//        Page<Manager> page = managerService.findAllManagerWithPageTime(pageIndex, 2, startDate, endDate);
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
    @RequestMapping(path = {"/student/add"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String add(Model model,
                      @RequestParam(value = "no") int no,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "classname") String classname,
                      @RequestParam(value = "password") String password,
                      @RequestParam(value = "gender") String gender,
                      @RequestParam(value = "iphone") String iphone,
                      @RequestParam(value = "email") String email,
                      @RequestParam(value = "dorm") int dorm) {
        if (studentService.add(no,name, classname,password, gender, iphone, email,dorm) > 0) {
            return "1";
        }else {
            return "0";
        }
    }

//    @RequestMapping(path = {"/manager/pass"}, method = {RequestMethod.POST,RequestMethod.GET})
//    @ResponseBody
//    public int pass(Model model,
//                      @RequestParam(value = "id") int id,
//                      @RequestParam(value = "oldpass") String oldpass,
//                      @RequestParam(value = "password") String password) {
////        if (managerService.getPassByName(name, password, gender, iphone, email) > 0) {
////            return "1";
////        }else {
////            return "0";
////        }
//        int result = managerService.pass(id, oldpass, password);
//        return result;
//    }
//
//
//    @RequestMapping(path = {"/manager/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int deleteManager(Model model, @RequestParam(value = "id") int id) {
//        return managerService.deleteManager(id);
//    }
//
//    @RequestMapping(path = {"/manager/updateStatus"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int updateStatus(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
//        return managerService.updateStatus(id, status);
//    }
//
//    @RequestMapping(path = {"/manager/data"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private String data(Model model,@RequestParam(value = "id") int id) {
//        return managerService.data(id);
//    }
//
//    @RequestMapping(path = {"/manager/time"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String time(Model model,
//                        @RequestParam(value = "start") String start,
//                        @RequestParam(value = "end") String end) {
//
////        return managerService.data(id);
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
////        managerService.time(start, end);
//        return "redirect:/manager/list/1/" + start + "/" + end;
////        return "login";
//    }
//
//
//    @RequestMapping(path = {"/manager/update"}, method = {RequestMethod.POST,RequestMethod.GET})
//    @ResponseBody
//    public String update(Model model,
//                      @RequestParam(value = "id") int id,
//                      @RequestParam(value = "name") String name,
//                      @RequestParam(value = "gender") String gender,
//                      @RequestParam(value = "iphone") String iphone,
//                      @RequestParam(value = "email") String email) {
//        if (managerService.update(id,name, gender, iphone, email) > 0) {
//            return "1";
//        }else {
//            return "0";
//        }
//    }

}
