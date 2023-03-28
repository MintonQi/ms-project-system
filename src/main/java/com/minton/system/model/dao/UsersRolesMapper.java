package com.minton.system.model.dao;

import com.minton.system.model.pojo.Role;
import com.minton.system.model.pojo.UsersRolesKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersRolesMapper {

    int deleteByPrimaryKey(UsersRolesKey key);

    int insert(UsersRolesKey row);

    int insertSelective(UsersRolesKey row);

    List<Role> selectRoleIdByUserID(Long userId);
}