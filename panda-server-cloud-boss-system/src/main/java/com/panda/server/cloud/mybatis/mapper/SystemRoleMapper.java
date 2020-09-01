package com.panda.server.cloud.mybatis.mapper;

import com.panda.server.cloud.mybatis.po.SystemRole;

public interface SystemRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    SystemRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);
}