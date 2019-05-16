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
    private NoticeDAO noticeDAO;


    public List<Notice> getNoticeList() {
        List<Notice> list = noticeDAO.getNoticeList();
        return list;
    }
    public List<Notice> getNoticeList_Page(int pageIndex, int pageSize) {
        //分页
        List<Notice> list = noticeDAO.getNoticeList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }
    public List<Notice> getNoticeList_time(int pageIndex, int pageSize, String startDate, String endDate) {
        //分页
        List<Notice> list = noticeDAO.getNoticeList_time((pageIndex - 1) * pageSize, pageSize, startDate, endDate);
        return list;
    }

    public Page<Notice> findAllNoticeWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Notice> allNotice  = noticeDAO.getNoticeList();
        int totalCount = allNotice.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(noticeDAO.getNoticeList_Page(startRow, pageSize));

        return page;
    }
    public Page<Notice> findAllNoticeWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Notice> allNotice = noticeDAO.getNoticeList_time_all(startDate, endDate);
        int totalCount = allNotice.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(noticeDAO.getNoticeList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteNotice(int id) {
        return noticeDAO.deleteNotice(id);
    }

    public int updateStatus(int id, int status) {
        return noticeDAO.updateStatus(id, status);
    }


    public int add(String noticetitle, String noticecontent) {
        //正常
        Notice notice = new Notice();
        notice.setDate(new Date());
        notice.setNoticetitle(noticetitle);
        notice.setNoticecontent(noticecontent);

        return noticeDAO.addNotice(notice);

    }


    public String data(int id) {
        Notice Notice = noticeDAO.selectById(id);
        return  JSON.toJSONString(Notice);
    }

    public int update(int id, String noticetitle, String noticecontent) {
        return noticeDAO.update(id, noticetitle, noticecontent);
    }

}
