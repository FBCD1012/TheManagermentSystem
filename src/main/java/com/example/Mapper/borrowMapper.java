package com.example.Mapper;

import com.example.Entity.bookInfo;
import com.example.Entity.borrowInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface borrowMapper {
    //插入借阅信息列表
    @Insert("INSERT INTO borrowinfo (sid,bid,time) values (#{sid},#{bid},NOW())")
    int insertBorrowInfo(@Param("sid")Integer sid,@Param("bid")Integer bid);


    @Select("SELECT b.* FROM bookinfo AS b LEFT JOIN borrowinfo AS bo ON b.bookId=bo.bid WHERE bo.bid IS NULL;")
    List<bookInfo> getTheLatest();


    @Select("""
            SELECT b.bookId,b.title,bo.time,s.sName,bo.sid
            FROM borrowinfo AS bo\s
            INNER JOIN bookinfo AS b\s
            ON bo.bid=b.bookId
            INNER JOIN studentinfo AS s
            ON bo.sid=s.studentId;
            """)
    List<borrowInfo> getBorrowInfos();


    @Delete("DELETE FROM borrowinfo where bid=#{bookId}")
    void deleteTheBorrowInfo(Integer bookId);
}
