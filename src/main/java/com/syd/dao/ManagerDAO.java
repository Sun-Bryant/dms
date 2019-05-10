package com.syd.dao;

import com.syd.model.Manager;
import com.syd.model.RootManager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ManagerDAO {
    // 注意空格
    String TABLE_NAME = " manager ";
    String INSERT_FIELDS = " name, password, salt, head_url, gender, iphone, email, date, status";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{name},#{password},#{salt},#{headUrl})"})
    int addUser(RootManager rootManager);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    RootManager selectById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    RootManager selectByName(String name);

    @Select({"select * from ", TABLE_NAME})
    List<Manager> getManagerList();

    List<Manager> getManagerList_Page(@Param("offset") int offset, @Param("limit") int limit);
}
