package com.syd.service;

import com.alibaba.fastjson.JSON;
import com.syd.dao.PunishDAO;
import com.syd.dao.SecurityDAO;
import com.syd.model.Punish;
import com.syd.model.Security;
import com.syd.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class PunishService {
    private static final Logger logger = LoggerFactory.getLogger(PunishService.class);


    @Autowired
    private PunishDAO punishDAO;

    public List<Punish> getPunishList() {
        List<Punish> list = punishDAO.getPunishList();
        return list;
    }

    public List<Punish> getPunishList_Page(int pageIndex, int pageSize) {
        //分页
        List<Punish> list = punishDAO.getPunishList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public Page<Punish> findAllPunishWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Punish> allPunish  = punishDAO.getPunishList();
        int totalCount = allPunish.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(punishDAO.getPunishList_Page(startRow, pageSize));

        return page;
    }

//    public Page<Security> findAllSecurityWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
//        List<Security> allSecurity = securityDAO.getSecurityList_time_all(startDate, endDate);
//        int totalCount = allSecurity.size();
//
//        //使用这三个参数，创建一个Page对象。
//        Page page = new Page(pageIndex, pageSize, totalCount);
//        //获取page中的StartRow（数据库起始记录指针）
//        int startRow = page.getStartRow();
//        //有了startRow和pageSize就可以拿到每页的数据。
//        page.setList(securityDAO.getSecurityList_time(startRow, pageSize, startDate, endDate));
//
//        return page;
//    }

    public int deletePunish(int id) {
        return punishDAO.deletePunish(id);
    }

//    public int updateStatus(int id, int status) {
//        return securityDAO.updateStatus(id, status);
//    }
//

    public int add(int dorm, String punishContent) {

        Punish punish = new Punish();
        punish.setDorm(dorm);
        punish.setPunishContent(punishContent);
        punish.setDate(new Date());
        return punishDAO.addPunish(punish);

    }
//
//    public String data(int id) {
//        Security security = securityDAO.selectById(id);
//        return  JSON.toJSONString(security);
//    }
//
//    public int update(int id, int dorm, String electricity, String dangerGood, String lockDoor) {
//
//        return securityDAO.update(id, dorm, electricity, dangerGood, lockDoor);
//    }

}
