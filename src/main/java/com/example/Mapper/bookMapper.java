package com.example.Mapper;

import com.example.Entity.bookInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface bookMapper {

    @Select("SELECT * FROM bookInfo")
    List<bookInfo> getTheBookInfos();

    //插入图书信息
    @Insert("INSERT INTO bookinfo (title,description,price) " +
            "values(#{title},#{desc},#{price})")
    int InsertIntoBookInfo(
            @Param("title")String title,
            @Param("desc")String description, @Param("price") String price
    );
    @Delete("DELETE FROM bookinfo where bookId=#{bookId}")
    void  deleteBookInfo(Integer bookId);
}
