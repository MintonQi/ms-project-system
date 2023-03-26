package com.minton.system.model.dao;

import com.minton.system.model.pojo.RolesMenusKey;

public interface RolesMenusMapper {
    int deleteByPrimaryKey(RolesMenusKey key);

    int insert(RolesMenusKey row);

    int insertSelective(RolesMenusKey row);
}