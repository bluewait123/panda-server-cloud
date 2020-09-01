package com.panda.server.cloud.mybatis.mapper;

import com.panda.server.cloud.mybatis.po.SystemResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 菜单资源维护
 * @author w
 * @date 2020-09-01
 */
@Component
public interface SystemResourceMapper {
    /**
     * 根据角色ID获取权限信息
     * @param roleId 角色ID
     * @return java.util.List<com.panda.server.cloud.mybatis.po.SystemResource>
     */
    List<SystemResource> selectByRoleId(@Param("roleId") String roleId);

    /**
     * 更新菜单信息
     * @param record 菜单记录
     * @return int
     */
    int updateByPrimaryKeySelective(SystemResource record);
}