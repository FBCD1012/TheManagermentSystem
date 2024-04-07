package com.example.Mapper;

import com.example.Entity.userInfo;
import org.apache.ibatis.annotations.Select;

public interface userMapper {
    @Select("SELECT * FROM userinfo where userName=#{userName}")
    userInfo getUserInfo(String userName);
}
