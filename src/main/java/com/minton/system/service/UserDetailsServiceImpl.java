package com.minton.system.service;

import com.minton.system.model.dao.UserMapper;
import com.minton.system.model.dao.UsersRolesMapper;
import com.minton.system.model.pojo.Role;
import com.minton.system.model.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 从数据库中加载用户信息
        User user = userMapper.selectByUsername(username);

        // 定义用户权限
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String role_name : userMapper.selectRoleNamesByUsername(user.getUsername())) {
            authorities.add(new SimpleGrantedAuthority(role_name));
        }

        // 创建UserDetails对象
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }
}
