package com.panda.server.cloud.mybatis.mapper;

import com.panda.server.cloud.mybatis.po.SystemRoleResource;

public interface SystemRoleResourceMapper {
    int insert(SystemRoleResource record);

    int insertSelective(SystemRoleResource record);
}