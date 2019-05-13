package com.syd.controller;

import com.syd.model.Security;
import com.syd.service.SecurityService;
import com.syd.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.syd.service.SecurityService;

@Controller
public class SecurityController {
    private static Logger logger = LoggerFactory.getLogger(SecurityController.class);

//    @Autowired
//    private SecurityService SecurityService;
//
//    @RequestMapping(path = {"/Security/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String getSecurityList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
////        System.out.println(pageIndex);
//
//        List<Security> list = SecurityService.getSecurityList_Page(pageIndex, 2);
//        Page<Security> page = SecurityService.findAllSecurityWithPage(pageIndex, 2);
//        model.addAttribute("list", list);
//        model.addAttribute("page", page);
//        model.addAttribute("start", page.getStart());
//        model.addAttribute("end", page.getEnd());
//        return "pages/member/list";
//    }
//
//    @RequestMapping(path = {"/Security/list/{pageIndex}/{startDate}/{endDate}"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String getSecurityList_time(Model model,
//                                       @PathVariable("pageIndex") int pageIndex,
//                                       @PathVariable("startDate") String startDate,
//                                       @PathVariable("endDate") String endDate) {
//        System.out.println(startDate);
//        System.out.println(endDate);
//
//        List<Security> list = SecurityService.getSecurityList_time(pageIndex, 2, startDate, endDate);
//        Page<Security> page = SecurityService.findAllSecurityWithPageTime(pageIndex, 2, startDate, endDate);
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
//    @RequestMapping(path = {"/Security/add"}, method = {RequestMethod.POST,RequestMethod.GET})
//    @ResponseBody
//    public String add(Model model,
//                      @RequestParam(value = "name") String name,
//                      @RequestParam(value = "password") String password,
//                      @RequestParam(value = "gender") String gender,
//                      @RequestParam(value = "iphone") String iphone,
//                      @RequestParam(value = "email") String email) {
//        if (SecurityService.add(name, password, gender, iphone, email) > 0) {
//            return "1";
//        }else {
//            return "0";
//        }
//    }
//
//
//
//    @RequestMapping(path = {"/Security/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int deleteSecurity(Model model, @RequestParam(value = "id") int id) {
//        return SecurityService.deleteSecurity(id);
//    }
//
//    @RequestMapping(path = {"/Security/updateStatus"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int updateStatus(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
//        return SecurityService.updateStatus(id, status);
//    }
//
//    @RequestMapping(path = {"/Security/data"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private String data(Model model,@RequestParam(value = "id") int id) {
//        return SecurityService.data(id);
//    }
//
//    @RequestMapping(path = {"/Security/time"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String time(Model model,
//                        @RequestParam(value = "start") String start,
//                        @RequestParam(value = "end") String end) {
//
////        return SecurityService.data(id);
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
////        SecurityService.time(start, end);
//        return "redirect:/Security/list/1/" + start + "/" + end;
////        return "login";
//    }
//
//
//    @RequestMapping(path = {"/Security/update"}, method = {RequestMethod.POST,RequestMethod.GET})
//    @ResponseBody
//    public String update(Model model,
//                      @RequestParam(value = "id") int id,
//                      @RequestParam(value = "name") String name,
//                      @RequestParam(value = "gender") String gender,
//                      @RequestParam(value = "iphone") String iphone,
//                      @RequestParam(value = "email") String email) {
//        if (SecurityService.update(id,name, gender, iphone, email) > 0) {
//            return "1";
//        }else {
//            return "0";
//        }
//    }

}
