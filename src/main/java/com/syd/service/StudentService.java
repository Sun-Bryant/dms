package com.syd.service;

import com.alibaba.fastjson.JSON;
import com.syd.dao.DormDAO;
import com.syd.dao.ManagerDAO;
import com.syd.dao.StudentDAO;
import com.syd.model.Manager;
import com.syd.model.Student;
import com.syd.util.DmsUtil;
import com.syd.util.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.debugger.posix.elf.ELFException;

import java.util.*;


@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);


    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private DormDAO dormDAO;

    public List<Student> getManagerList() {
        List<Student> list = studentDAO.getManagerList();
        return list;
    }
    public List<Student> getManagerList_Page(int pageIndex, int pageSize) {
        //分页
        List<Student> list = studentDAO.getManagerList_Page((pageIndex - 1) * pageSize, pageSize);
        return list;
    }
    public List<Student> getStudent_List_dorm(int dorm) {
        //不分页
        List<Student> list = studentDAO.getManagerList_dorm(dorm);
        return list;
    }
    public List<Student> getStudent_List_class(String classname) {
        //不分页
        List<Student> list = studentDAO.getManagerList_class(classname);
        return list;
    }
    public List<Student> getManagerList_Page_dorm(int pageIndex, int pageSize, int dorm) {
        //分页
        List<Student> list = studentDAO.getManagerList_Page_dorm((pageIndex - 1) * pageSize, pageSize, dorm);
        return list;
    }

    public List<Student> getManagerList_time(int pageIndex, int pageSize, String startDate, String endDate) {
        //分页
        List<Student> list = studentDAO.getManagerList_time((pageIndex - 1) * pageSize, pageSize, startDate, endDate);
        return list;
    }

    public Page<Student> findAllManagerWithPage(int pageIndex, int pageSize) {
        //获取数据库中所有的记录
        List<Student> allManager  = studentDAO.getManagerList();
        int totalCount = allManager.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(studentDAO.getManagerList_Page(startRow, pageSize));

        return page;
    }

    public Page<Student> findAllManagerWithPage_dorm(int pageIndex, int pageSize, int dorm) {
        //获取数据库中所有的记录
        List<Student> allStudents  = studentDAO.getManagerList_dorm(dorm);
        int totalCount = allStudents.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(studentDAO.getManagerList_Page_dorm(startRow, pageSize,dorm));
        return page;
    }

    public Page<Student> findAllManagerWithPageTime(int pageIndex, int pageSize, String startDate, String endDate) {
        List<Student> allManager = studentDAO.getManagerList_time_all(startDate, endDate);
        int totalCount = allManager.size();

        //使用这三个参数，创建一个Page对象。
        Page page = new Page(pageIndex, pageSize, totalCount);
        //获取page中的StartRow（数据库起始记录指针）
        int startRow = page.getStartRow();
        //有了startRow和pageSize就可以拿到每页的数据。
        page.setList(studentDAO.getManagerList_time(startRow, pageSize, startDate, endDate));

        return page;
    }

    public int deleteManager(int no) {
        if (dormDAO.updateCapacity1(studentDAO.selectByNo(no).getDorm()) > 0) {
            return studentDAO.deleteManager(no);
        }else {
            return 0;
        }
    }

    public int updateStatus(int no, int status) {
        return studentDAO.updateStatus(no, status);
    }

    public int add(int no, String name, String classname, String password, String gender, String iphone, String email,int dorm) {
        //判断用户名是否注册过
        Student  student= studentDAO.selectByNo(no);
        if (student != null) {
            return 0;
        }
        //正常
        student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setClassname(classname);
        student.setDorm(dorm);
        student.setSalt(UUID.randomUUID().toString().substring(0, 5));
        student.setPassword(DmsUtil.MD5(password + student.getSalt()));
        student.setEmail(email);
        student.setIphone(iphone);
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        student.setGender(gender1);
        student.setStatus(0);

        int arg1 = studentDAO.addmanager(student);
//        int arg2 = dormDAO.;
        return arg1;

    }

    public int pass(int no, String oldpass, String password) {
        Student student = studentDAO.selectById(no);
        if (student == null) {
            return 0;
        }
        //判断旧密码是否正确
        if (!DmsUtil.MD5(oldpass + student.getSalt()).equals(student.getPassword())) {
            return 0;
        } else {
            //设置新密码
            student.setSalt(UUID.randomUUID().toString().substring(0, 5));
            student.setPassword(DmsUtil.MD5(password + student.getSalt()));
            int result = studentDAO.updatePass(no, student.getSalt(), student.getPassword());
            return result;
        }
    }

    public String data(int no) {
        Student student = studentDAO.selectById(no);
        return  JSON.toJSONString(student);
    }

    public int update(int no, String name,String classname, String gender, String iphone, String email) {
        int gender1 = 0;
        if ("男".equals(gender)) {
            gender1 = 1;
        }else {
            gender1 = 0;
        }
        return studentDAO.update(no, name, classname, gender1, iphone, email);
    }

    public Student info(int no) {
        return studentDAO.selectByNo(no);
    }

    public List<Student> dorm_info(int dorm) {
        return studentDAO.getManagerList_dorm(dorm);
    }

    public Student getStudentByNo(int no) {
        if (studentDAO.selectByNo(no) != null) {
            return studentDAO.selectByNo(no);
        } else {
            return null;
        }

    }
}
