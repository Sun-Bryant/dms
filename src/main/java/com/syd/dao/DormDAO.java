package com.syd.dao;

import com.syd.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DormDAO {
    // 注意空格
    String TABLE_NAME = " dorm ";
    String INSERT_FIELDS = " capacity, utilities,";
    String SELECT_FIELDS = " dorm, " + INSERT_FIELDS;

    @Select({"select * from ", TABLE_NAME})
    List<Student> getManagerList();

    List<Student> getManagerList_time_all(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Delete({"delete from ", TABLE_NAME, " where no=#{no}"})
    int deleteManager(int no);

    @Update({"update ", TABLE_NAME, " set status=#{status} where id=#{id}"})
    int updateStatus(@Param("id") int id, @Param("status") int status);

    @Insert({"insert into ", TABLE_NAME, "(", SELECT_FIELDS,
            ") values (#{no},#{name},#{classname},#{gender},#{iphone},#{email},#{password},#{salt},#{dorm_id},#{status})"})
    int addmanager(Student student);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where no=#{no}"})
    Student selectByNo(int no);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Student selectById(int id);

    @Update({"update ", TABLE_NAME, " set password=#{password},salt=#{salt} where id=#{id}"})
    int updatePass(@Param("id") int id, @Param("salt") String salt, @Param("password") String password);

    @Update({"update ", TABLE_NAME, " set capacity=#{capacity}phone} where dorm=#{dorm}"})
    int update(@Param("dorm") int dorm, @Param("name") String name, @Param("gender1") int gender1, @Param("iphone") String iphone, @Param("email") String email);


}
