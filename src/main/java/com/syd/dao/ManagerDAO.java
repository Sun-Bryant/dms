package com.syd.dao;

import com.syd.model.Manager;
import com.syd.model.RootManager;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ManagerDAO {
    // 注意空格
    String TABLE_NAME = " manager ";
    String INSERT_FIELDS = " name, password, salt, head_url, gender, iphone, email, date, status";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select * from ", TABLE_NAME})
    List<Manager> getManagerList();

    List<Manager> getManagerList_Page(@Param("offset") int offset, @Param("limit") int limit);

    List<Manager> getManagerList_time(@Param("offset") int offset, @Param("limit") int limit, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Manager> getManagerList_time_all(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteManager(int id);

    @Update({"update ", TABLE_NAME, " set status=#{status} where id=#{id}"})
    int updateStatus(@Param("id")int id, @Param("status")int status);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{name},#{password},#{salt},#{headUrl},#{gender},#{iphone},#{email},#{date},#{status})"})
    int addmanager(Manager manager);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    Manager selectByName(String name);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Manager selectById(int id);

    @Update({"update ", TABLE_NAME, " set password=#{password},salt=#{salt} where id=#{id}"})
    int updatePass(@Param("id")int id,@Param("salt")String salt, @Param("password")String password);

    @Update({"update ", TABLE_NAME, " set name=#{name},gender=#{gender1},iphone=#{iphone},email=#{email} where id=#{id}"})
    int update(@Param("id")int id, @Param("name")String name, @Param("gender1")int gender1, @Param("iphone")String iphone, @Param("email")String email);


}
