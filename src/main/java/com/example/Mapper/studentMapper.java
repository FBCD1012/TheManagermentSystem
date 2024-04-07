package com.example.Mapper;

import com.example.Entity.studentInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface studentMapper {
    //获取学生信息列表
    @Select("SELECT * FROM studentInfo")
    List<studentInfo> getTheStudentList();


    @Select("Select count(*) from studentInfo")
    int getTheStudentCountInt();
}
