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

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteManager(int id);

    @Update({"update ", TABLE_NAME, " set status=#{status} where id=#{id}"})
    int updateStatus(@Param("id")int id, @Param("status")int status);
}
