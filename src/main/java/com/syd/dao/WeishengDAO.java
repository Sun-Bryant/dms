package com.syd.dao;

import com.syd.model.Weisheng;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WeishengDAO {
    // 注意空格
    String TABLE_NAME = " weisheng ";
    String INSERT_FIELDS = " dorm, floor1, balcony, bed";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select * from ", TABLE_NAME})
    List<Weisheng> getWeishengList();

    @Select({"select * from ", TABLE_NAME, "where floor1 >=3 and balcony >= 3 and bed >= 3 "})
    List<Weisheng> getAwardList();

    List<Weisheng> getWeishengList_Page(@Param("offset") int offset, @Param("limit") int limit);

    List<Weisheng> getAwardList_Page(@Param("offset") int offset, @Param("limit") int limit);

    List<Weisheng> getWeishengList_time(@Param("offset") int offset, @Param("limit") int limit, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Weisheng> getWeishengList_time_all(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteWeisheng(int id);

    @Update({"update ", TABLE_NAME, " set status=#{status} where id=#{id}"})
    int updateStatus(@Param("id") int id, @Param("status") int status);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{dorm},#{floor1},#{balcony},#{bed})"})
    int addWeisheng(Weisheng Weisheng);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where dorm=#{dorm}"})
    Weisheng selectByName(int dorm);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Weisheng selectById(int id);

    @Update({"update ", TABLE_NAME, " set password=#{password},salt=#{salt} where id=#{id}"})
    int updatePass(@Param("id") int id, @Param("salt") String salt, @Param("password") String password);

    @Update({"update ", TABLE_NAME, " set dorm=#{dorm},floor1=#{floor1},balcony=#{balcony},bed=#{bed} where id=#{id}"})
    int update(@Param("id") int id, @Param("dorm") int dorm, @Param("floor1") int floor1, @Param("balcony") int balcony, @Param("bed") int bed );


}
