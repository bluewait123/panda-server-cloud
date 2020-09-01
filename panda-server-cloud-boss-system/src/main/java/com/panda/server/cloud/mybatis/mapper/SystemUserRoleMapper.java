package com.panda.server.cloud.mybatis.mapper;

import com.panda.server.cloud.mybatis.po.SystemUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 角色信息维护
 * @author w
 * @date 2020-09-01
 */
@Component
public interface SystemUserRoleMapper {
    /**
     * 新增用户角色关联信息
     * @param record 记录
     * @return int
     */
    int insertSelective(SystemUserRole record);

    /**
     * 根据用户ID获取角色ID
     * @param userId 用户ID
     * @return com.panda.server.cloud.mybatis.po.SystemUserRole
     */
    SystemUserRole selectByUserId(@Param("userId") String userId);
}