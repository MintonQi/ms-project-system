package com.minton.system.model.dao;

import com.minton.system.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserByUsername(String username);

    int deleteByPrimaryKey(Long userId);

    int insert(User row);

    int insertSelective(User row);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User row);

    int updateByPrimaryKey(User row);
}