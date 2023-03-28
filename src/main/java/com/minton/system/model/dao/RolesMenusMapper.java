package com.minton.system.model.dao;

import com.minton.system.model.pojo.RolesMenusKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.jmx.export.annotation.ManagedNotification;

@Mapper
public interface RolesMenusMapper {
    int deleteByPrimaryKey(RolesMenusKey key);

    int insert(RolesMenusKey row);

    int insertSelective(RolesMenusKey row);
}