package com.syd.controller;


import com.syd.model.Notice;
import com.syd.service.NoticeService;
import com.syd.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NoticeController {
    private static Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(path = {"/notice/list/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getNoticeList_Page(Model model, @PathVariable("pageIndex") int pageIndex) {
//        System.out.println(pageIndex);

        List<Notice> list = noticeService.getNoticeList_Page(pageIndex, 2);
        Page<Notice> page = noticeService.findAllNoticeWithPage(pageIndex, 2);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "pages/others/list_notice";
    }

    @RequestMapping(path = {"/notice/list_student/{pageIndex}"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String getNoticeList_Page_student(Model model, @PathVariable("pageIndex") int pageIndex) {
//        System.out.println(pageIndex);

        List<Notice> list = noticeService.getNoticeList_Page(pageIndex, 2);
        Page<Notice> page = noticeService.findAllNoticeWithPage(pageIndex, 2);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("start", page.getStart());
        model.addAttribute("end", page.getEnd());
        return "pages/others/list_notice_student";
    }

//    @RequestMapping(path = {"/Notice/list/{pageIndex}/{startDate}/{endDate}"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String getNoticeList_time(Model model,
//                                       @PathVariable("pageIndex") int pageIndex,
//                                       @PathVariable("startDate") String startDate,
//                                       @PathVariable("endDate") String endDate) {
//        System.out.println(startDate);
//        System.out.println(endDate);
//
//        List<Notice> list = noticeService.getNoticeList_time(pageIndex, 2, startDate, endDate);
//        Page<Notice> page = noticeService.findAllNoticeWithPageTime(pageIndex, 2, startDate, endDate);
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
    @RequestMapping(path = {"/notice/add"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String add(Model model,
                      @RequestParam(value = "noticetitle") String noticetitle,
                      @RequestParam(value = "noticecontent") String noticecontent) {
        if (noticeService.add(noticetitle, noticecontent) > 0) {
            return "1";
        }else {
            return "0";
        }
    }

    @RequestMapping(path = {"/notice/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private int deleteNotice(Model model, @RequestParam(value = "id") int id) {
        return noticeService.deleteNotice(id);
    }

//    @RequestMapping(path = {"/Notice/updateStatus"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    private int updateStatus(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
//        return noticeService.updateStatus(id, status);
//    }

    @RequestMapping(path = {"/notice/data"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    private String data(Model model,@RequestParam(value = "id") int id) {
        return noticeService.data(id);
    }

//    @RequestMapping(path = {"/Notice/time"}, method = {RequestMethod.GET, RequestMethod.POST})
//    private String time(Model model,
//                        @RequestParam(value = "start") String start,
//                        @RequestParam(value = "end") String end) {
//
////        return NoticeService.data(id);
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
////        NoticeService.time(start, end);
//        return "redirect:/Notice/list/1/" + start + "/" + end;
////        return "login";
//    }
//
//
    @RequestMapping(path = {"/notice/update"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String update(Model model,
                      @RequestParam(value = "id") int id,
                      @RequestParam(value = "noticetitle") String noticetitle,
                      @RequestParam(value = "noticecontent") String noticecontent) {
        if (noticeService.update(id,noticetitle, noticecontent) > 0) {
            return "1";
        }else {
            return "0";
        }
    }

}
