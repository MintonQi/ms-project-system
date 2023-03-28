package com.minton.system.model.dao;

import com.minton.system.model.pojo.Role;
import com.minton.system.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
    User selectByUsername(String username);

    List<String> selectRoleNamesByUsername(String username);

    int deleteByPrimaryKey(Long userId);

    int insert(User row);

    int insertSelective(User row);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User row);

    int updateByPrimaryKey(User row);
}