package com.example.Service.Impl;

import com.example.Entity.userInfo;
import com.example.Mapper.userMapper;
import com.example.Service.userTestService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class userTestServiceImpl implements UserDetailsService {
    @Resource
    userMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userInfo userInfo = userMapper.getUserInfo(username);
        if (userInfo == null)
            throw new UsernameNotFoundException("userInfo not found");
        //返回对应的用户细节信息操作
        return User.withUsername(userInfo.getUserName()).password(userInfo.getUserPassword()).roles(userInfo.getRoles()).build();
    }
}
