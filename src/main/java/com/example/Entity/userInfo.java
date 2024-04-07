package com.example.Entity;


import lombok.Data;

@Data
public class userInfo {
    //用户ID
    Integer userId;
    //用户名称
    String userName;
    //用户密码
    String userPassword;
    //用户权限信息
    String roles;
}
