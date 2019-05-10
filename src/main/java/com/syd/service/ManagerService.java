package com.syd.service;

import com.syd.dao.ManagerDAO;
import com.syd.model.Manager;
import com.syd.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ManagerService {
    private static final Logger logger = LoggerFactory.getLogger(ManagerService.class);

    @Autowired
    private ManagerDAO managerDAO;

    public List<Manager> getManagerList() {
        List<Manager> list = managerDAO.getManagerList();
        return list;
    }
    public List<Manager> getManagerList_Page(int pageIndex, int pageSize) {
        //分页
        List<Manager> list = managerDAO.getManagerList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }

    public Page<Manager> findAllManagerWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Manager> allManager  = managerDAO.getManagerList();
        int totalCount = allManager.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(managerDAO.getManagerList_Page(startRow, pageSize));

        return page;
    }

    public int deleteManager(int id) {
        return managerDAO.deleteManager(id);
    }

    public int updateStatus(int id, int status) {
        return managerDAO.updateStatus(id, status);
    }



}
