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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SecurityController {
    private static Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private SecurityService securityService;

    @RequestMapping(path = {"/security/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getSecurityList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
//        System.out.println(pageIndex);
        List<Security> list = securityService.getSecurityList_Page(pageIndex, 2);
        Page<Security> page = securityService.findAllSecurityWithPage(pageIndex, 2);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "pages/member/list_security";
    }

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
    @RequestMapping(path = {"/security/add"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String add(Model model,
                      @RequestParam(value = "dorm") int dorm,
                      @RequestParam(value = "electricity") String electricity,
                      @RequestParam(value = "dangerGood") String dangerGood,
                      @RequestParam(value = "lockDoor") String lockDoor) {
        if (securityService.add(dorm, electricity, dangerGood, lockDoor) > 0) {
            return "1";
        } else {
            return "0";
        }
    }



    @RequestMapping(path = {"/security/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private int deleteSecurity(Model model, @RequestParam(value = "id") int id) {
        return securityService.deleteSecurity(id);
    }

//    @RequestMapping(path = {"/Security/updateStatus"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int updateStatus(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
//        return SecurityService.updateStatus(id, status);
//    }
//
    @RequestMapping(path = {"/security/data"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private String data(Model model,@RequestParam(value = "id") int id) {
        return securityService.data(id);
    }

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

    @RequestMapping(path = {"/security/update"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String update(Model model,
                      @RequestParam(value = "id") int id,
                      @RequestParam(value = "dorm") int dorm,
                      @RequestParam(value = "electricity") String electricity,
                      @RequestParam(value = "dangerGood") String dangerGood,
                      @RequestParam(value = "lockDoor") String lockDoor) {
        if (securityService.update(id,dorm, electricity, dangerGood, lockDoor) > 0) {
            return "1";
        }else {
            return "0";
        }
    }

}
