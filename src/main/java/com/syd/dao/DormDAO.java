package com.syd.dao;

import com.syd.model.Dorm;
import com.syd.model.Dorm;
import com.syd.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DormDAO {
    // 注意空格
    String TABLE_NAME = " dorm ";
    String INSERT_FIELDS = " capacity, utilities";
    String SELECT_FIELDS = " dorm, " + INSERT_FIELDS;

    @Select({"select * from ", TABLE_NAME})
    List<Dorm> getDormList();

    List<Dorm> getDormList_Page(@Param("offset") int offset, @Param("limit") int limit);

    List<Dorm> getDormList_time(@Param("offset") int offset, @Param("limit") int limit, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Dorm> getDormList_time_all(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteDorm(int id);

    @Update({"update ", TABLE_NAME, " set status=#{status} where id=#{id}"})
    int updateStatus(@Param("id") int id, @Param("status") int status);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{name},#{password},#{salt},#{headUrl},#{gender},#{iphone},#{email},#{date},#{status})"})
    int addDorm(Dorm Dorm);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    Dorm selectByName(String name);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Dorm selectById(int id);

    @Update({"update ", TABLE_NAME, " set password=#{password},salt=#{salt} where id=#{id}"})
    int updatePass(@Param("id") int id, @Param("salt") String salt, @Param("password") String password);

    @Update({"update ", TABLE_NAME, " set name=#{name},gender=#{gender1},iphone=#{iphone},email=#{email} where id=#{id}"})
    int update(@Param("id") int id, @Param("name") String name, @Param("gender1") int gender1, @Param("iphone") String iphone, @Param("email") String email);


}