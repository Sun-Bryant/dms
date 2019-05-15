package com.syd.dao;

import com.syd.model.Breakdown;
import com.syd.model.Breakdown;
import com.syd.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BreakdownDAO {
    //注意空格
    String TABLE_NAME = " breakdown ";
    String INSERT_FIELDS = " breakContent, status, examine";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select * from ", TABLE_NAME})
    List<Breakdown> getBreakdownList();

    List<Breakdown> getBreakdownList_time_all(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Breakdown> getBreakdownList_Page(@Param("offset") int offset, @Param("limit") int limit);

    List<Breakdown> getBreakdownList_time(@Param("offset") int offset, @Param("limit") int limit, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteBreakdown(int id);

    @Update({"update ", TABLE_NAME, " set examine=#{examine} where id=#{id}"})
    int updateStatus(@Param("id") int id, @Param("examine") int examine);

    @Update({"update ", TABLE_NAME, " set status=#{status} where id=#{id}"})
    int updateStatus1(@Param("id") int id, @Param("status") int status);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{breakContent},#{status},#{examine})"})
    int addBreakdown(Breakdown breakdown);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where no=#{no}"})
    Breakdown selectByNo(int no);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Breakdown selectById(int id);

    @Update({"update ", TABLE_NAME, " set password=#{password},salt=#{salt} where id=#{id}"})
    int updatePass(@Param("id") int id, @Param("salt") String salt, @Param("password") String password);

    @Update({"update ", TABLE_NAME, " set capacity=#{capacity}phone} where dorm=#{dorm}"})
    int update(@Param("dorm") int dorm, @Param("name") String name, @Param("gender1") int gender1, @Param("iphone") String iphone, @Param("email") String email);


}
