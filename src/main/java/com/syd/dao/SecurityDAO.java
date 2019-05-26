package com.syd.dao;

import com.syd.model.Security;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SecurityDAO {
    // 注意空格
    String TABLE_NAME = " security ";
    String INSERT_FIELDS = " electricity, dangerGood, lockDoor, dorm, date";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select * from ", TABLE_NAME})
    List<Security> getSecurityList();

    List<Security> getSecurityList_Page(@Param("offset") int offset, @Param("limit") int limit);

    List<Security> getSecurityList_time(@Param("offset") int offset, @Param("limit") int limit, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Security> getSecurityList_time_all(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteSecurity(int id);

    @Update({"update ", TABLE_NAME, " set status=#{status} where id=#{id}"})
    int updateStatus(@Param("id") int id, @Param("status") int status);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{electricity},#{dangerGood},#{lockDoor},#{dorm},#{date})"})
    int addSecurity(Security Security);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where dorm=#{dorm}"})
    Security selectByName(int dorm);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Security selectById(int id);

    @Update({"update ", TABLE_NAME, " set electricity=#{electricity},dangerGood=#{dangerGood},lockDoor=#{lockDoor},dorm=#{dorm} where id=#{id}"})
    int update(@Param("id") int id, @Param("dorm") int dorm, @Param("electricity") String electricity, @Param("dangerGood") String dangerGood, @Param("lockDoor") String lockDoor);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where dorm=#{dorm}"})
    List<Security> getSecurityList_student(int dorm);
}
