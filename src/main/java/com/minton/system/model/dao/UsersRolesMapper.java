package com.minton.system.model.dao;

import com.minton.system.model.pojo.UsersRolesKey;

public interface UsersRolesMapper {
    int deleteByPrimaryKey(UsersRolesKey key);

    int insert(UsersRolesKey row);

    int insertSelective(UsersRolesKey row);
}