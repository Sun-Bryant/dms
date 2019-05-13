package com.syd.service;

import com.alibaba.fastjson.JSON;
import com.syd.dao.NoticeDAO;
import com.syd.model.Notice;
import com.syd.util.DmsUtil;
import com.syd.util.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class NoticeService {
    private static final Logger logger = LoggerFactory.getLogger(NoticeService.class);


    @Autowired
    private NoticeDAO NoticeDAO;


    public List<Notice> getNoticeList() {
        List<Notice> list = NoticeDAO.getNoticeList();
        return list;
    }
    public List<Notice> getNoticeList_Page(int pageIndex, int pageSize) {
        //分页
        List<Notice> list = NoticeDAO.getNoticeList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public List<Notice> getNoticeList_time(int pageIndex, int pageSize, String startDate, String endDate) {
        //分页
        List<Notice> list = NoticeDAO.getNoticeList_time((pageIndex - 1) * pageSize, pageSize, startDate, endDate);

        return list;
    }

    public Page<Notice> findAllNoticeWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Notice> allNotice  = NoticeDAO.getNoticeList();
        int totalCount = allNotice.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(NoticeDAO.getNoticeList_Page(startRow, pageSize));

        return page;
    }
    public Page<Notice> findAllNoticeWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Notice> allNotice = NoticeDAO.getNoticeList_time_all(startDate, endDate);
        int totalCount = allNotice.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(NoticeDAO.getNoticeList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteNotice(int id) {
        return NoticeDAO.deleteNotice(id);
    }

    public int updateStatus(int id, int status) {
        return NoticeDAO.updateStatus(id, status);
    }


//    public int add(String name, String password, String gender, String iphone, String email) {
//        //先判断是否为空， 即进行格式检查
//        Map<String, Object> map = new HashMap<>();
//        if (StringUtils.isBlank(name)) {
//            map.put("msg", "用户名不能为空");
//            return 0;
//        }
//        if (StringUtils.isBlank(password)) {
//            map.put("msg", "密码不能为空");
//            return 0;
//        }
//        //判断用户名是否注册过
//        Notice  Notice= NoticeDAO.selectByName(name);
//        if (Notice != null) {
//            map.put("msg", "用户名已被注册");
//            return 0;
//        }
//        //正常
//        Notice = new Notice();
//        Notice.setName(name);
//        Notice.setSalt(UUID.randomUUID().toString().substring(0, 5));
//        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
//        Notice.setHeadUrl(head);
//        Notice.setPassword(DmsUtil.MD5(password + Notice.getSalt()));
//        Notice.setDate(new Date())
//        Notice.setGender(gender1);
//        Notice.setStatus(0);
//
//        return NoticeDAO.addNotice(Notice);
//
//    }


    public String data(int id) {
        Notice Notice = NoticeDAO.selectById(id);
        return  JSON.toJSONString(Notice);
    }

    public int update(int id, String name, String gender, String iphone, String email) {
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        return NoticeDAO.update(id, name, gender1, iphone, email);
    }

}
