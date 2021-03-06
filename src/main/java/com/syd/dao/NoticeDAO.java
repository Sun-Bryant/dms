package com.syd.dao;

import com.syd.model.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeDAO {
    // 注意空格
    String TABLE_NAME = " notice ";
    String INSERT_FIELDS = " noticetitle, noticecontent, date";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select * from ", TABLE_NAME})
    List<Notice> getNoticeList();

    List<Notice> getNoticeList_Page(@Param("offset") int offset, @Param("limit") int limit);

    List<Notice> getNoticeList_time(@Param("offset") int offset, @Param("limit") int limit, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Notice> getNoticeList_time_all(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteNotice(int id);

    @Update({"update ", TABLE_NAME, " set status=#{status} where id=#{id}"})
    int updateStatus(@Param("id") int id, @Param("status") int status);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{noticetitle},#{noticecontent},#{date})"})
    int addNotice(Notice notice);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    Notice selectByName(String name);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Notice selectById(int id);

    @Update({"update ", TABLE_NAME, " set password=#{password},salt=#{salt} where id=#{id}"})
    int updatePass(@Param("id") int id, @Param("salt") String salt, @Param("password") String password);

    @Update({"update ", TABLE_NAME, " set noticetitle=#{noticetitle},noticecontent=#{noticecontent} where id=#{id}"})
    int update(@Param("id") int id, @Param("noticetitle") String noticetitle, @Param("noticecontent") String noticecontent);


}
