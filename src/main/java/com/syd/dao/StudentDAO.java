package com.syd.dao;

import com.syd.model.Manager;
import com.syd.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentDAO {
    // 注意空格
    String TABLE_NAME = " student ";
    String INSERT_FIELDS = " name, classname, gender, iphone, email, password, salt, dorm, status";
    String SELECT_FIELDS = " no, " + INSERT_FIELDS;

    @Select({"select * from ", TABLE_NAME})
    List<Student> getManagerList();

    @Select({"select * from ", TABLE_NAME," where dorm= #{dorm}"})
    List<Student> getManagerList_dorm(int dorm);

    @Select({"select * from ", TABLE_NAME," where classname= #{classname}"})
    List<Student> getManagerList_class(String classname);

    List<Student> getManagerList_Page(@Param("offset") int offset, @Param("limit") int limit);

    List<Student> getManagerList_Page_dorm(@Param("offset") int offset, @Param("limit") int limit, @Param("dorm") int dorm);

    List<Student> getManagerList_time(@Param("offset") int offset, @Param("limit") int limit, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Student> getManagerList_time_all(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Delete({"delete from ", TABLE_NAME, " where no=#{no}"})
    int deleteManager(int no);

    @Update({"update ", TABLE_NAME, " set status=#{status} where no=#{no}"})
    int updateStatus(@Param("no") int no, @Param("status") int status);

    @Insert({"insert into ", TABLE_NAME, "(", SELECT_FIELDS,
            ") values (#{no},#{name},#{classname},#{gender},#{iphone},#{email},#{password},#{salt},#{dorm},#{status})"})
    int addmanager(Student student);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where no=#{no}"})
    Student selectByNo(int no);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where no=#{no}"})
    Student selectById(int no);

    @Update({"update ", TABLE_NAME, " set password=#{password},salt=#{salt} where no=#{no}"})
    int updatePass(@Param("no") int no, @Param("salt") String salt, @Param("password") String password);

    @Update({"update ", TABLE_NAME, " set name=#{name},classname=#{classname},gender=#{gender1},iphone=#{iphone},email=#{email} where no=#{no}"})
    int update(@Param("no") int no, @Param("name") String name, @Param("classname") String classname, @Param("gender1") int gender1, @Param("iphone") String iphone, @Param("email") String email);


}
