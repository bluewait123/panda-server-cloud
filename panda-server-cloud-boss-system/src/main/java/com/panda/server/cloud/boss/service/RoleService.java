package com.panda.server.cloud.boss.service;

import com.panda.server.cloud.boss.enums.BossSystemResultCode;
import com.panda.server.cloud.boss.exception.BossSystemException;
import com.panda.server.cloud.mybatis.mapper.SystemUserRoleMapper;
import com.panda.server.cloud.mybatis.po.SystemUserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 角色信息管理
 * @author w
 * @date 2020-07-01
 */
@Slf4j
@Service
public class RoleService {

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    /**
     * 根据用户ID获取用户角色信息
     * @param userId 用户ID
     * @return
     */
    public SystemUserRole findUserRoleByUserId(String userId){
        SystemUserRole role = systemUserRoleMapper.selectByUserId(userId);
        Optional.ofNullable(role).orElseThrow(() -> new BossSystemException(BossSystemResultCode.ROLE_NOT_EXITS_ERROR));
        return role;
    }
}
