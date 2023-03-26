package com.minton.system.service;

import com.minton.system.model.dao.UserMapper;
import com.minton.system.model.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

    private final UserMapper userMapper;

    public UserDetailsServiceImp(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserDetailsByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        return user;
    }

}
