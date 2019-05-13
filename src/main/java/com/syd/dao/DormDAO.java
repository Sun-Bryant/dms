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

    @Delete({"delete from ", TABLE_NAME, " where dorm=#{dorm}"})
    int deleteDorm(int dorm);

    @Update({"update ", TABLE_NAME, " set status=#{status} where id=#{id}"})
    int updateStatus(@Param("id") int id, @Param("status") int status);

    @Insert({"insert into ", TABLE_NAME, "(", SELECT_FIELDS, ") values (#{dorm},#{capacity},#{utilities})"})
    int addDorm(Dorm Dorm);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where dorm=#{dorm}"})
    Dorm selectByName(int dorm);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where dorm=#{dorm}"})
    Dorm selectById(int dorm);

    @Update({"update ", TABLE_NAME, " set capacity=#{capacity},utilities=#{utilities} where dorm=#{dorm}"})
    int update(@Param("dorm") int dorm, @Param("capacity") int capacity, @Param("utilities") double utilities);


}
