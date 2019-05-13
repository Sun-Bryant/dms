package com.syd.dao;

import com.syd.model.Weisheng;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WeishengDAO {
    // 注意空格
    String TABLE_NAME = " weisheng ";
    String INSERT_FIELDS = " dorm, floor, balcony, bed";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select * from ", TABLE_NAME})
    List<Weisheng> getWeishengList();

    List<Weisheng> getWeishengList_Page(@Param("offset") int offset, @Param("limit") int limit);

    List<Weisheng> getWeishengList_time(@Param("offset") int offset, @Param("limit") int limit, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Weisheng> getWeishengList_time_all(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteWeisheng(int id);

    @Update({"update ", TABLE_NAME, " set status=#{status} where id=#{id}"})
    int updateStatus(@Param("id") int id, @Param("status") int status);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{name},#{password},#{salt},#{headUrl},#{gender},#{iphone},#{email},#{date},#{status})"})
    int addWeisheng(Weisheng Weisheng);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    Weisheng selectByName(String name);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Weisheng selectById(int id);

    @Update({"update ", TABLE_NAME, " set password=#{password},salt=#{salt} where id=#{id}"})
    int updatePass(@Param("id") int id, @Param("salt") String salt, @Param("password") String password);

    @Update({"update ", TABLE_NAME, " set name=#{name},gender=#{gender1},iphone=#{iphone},email=#{email} where id=#{id}"})
    int update(@Param("id") int id, @Param("name") String name, @Param("gender1") int gender1, @Param("iphone") String iphone, @Param("email") String email);


}
