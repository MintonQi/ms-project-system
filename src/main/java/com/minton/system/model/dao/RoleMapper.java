package com.minton.system.model.dao;

import com.minton.system.model.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(Role row);

    int insertSelective(Role row);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role row);

    int updateByPrimaryKey(Role row);
}