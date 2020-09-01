package com.panda.server.cloud.mybatis.mapper;

import com.panda.server.cloud.boss.vo.user.QueryUserListReq;
import com.panda.server.cloud.boss.vo.user.UserDetailVo;
import com.panda.server.cloud.boss.vo.user.UserInfoVo;
import com.panda.server.cloud.mybatis.po.SystemUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户信息操作
 * @author w
 * @date 2020-08-31
 */
@Component
public interface SystemUserMapper {
    /**
     * 查询用户信息列表
     * @param currentUserId 用户ID
     * @param req 筛选条件
     * @return List<com.panda.server.cloud.boss.vo.user>
     */
    List<UserInfoVo> selectAll(@Param("currentUserId") String currentUserId, @Param("req") QueryUserListReq req);

    /**
     * 根据用户ID获取用户信息
     * @param id 用户ID
     * @return com.panda.server.cloud.mybatis.po.SystemUser
     */
    SystemUser selectByPrimaryKey(@Param("id") String id);

    /**
     * 根据用户名获取用户信息
     * @param userName 用户名
     * @return com.panda.server.cloud.mybatis.po.SystemUser
     */
    SystemUser selectByUserName(@Param("userName") String userName);

    /**
     * 根据用户ID获取用户信息详情
     * @param id 用户ID
     * @return com.panda.server.cloud.mybatis.po.SystemUser
     */
    UserDetailVo selectDetailByPrimaryKey(@Param("id") String id);

    /**
     * 新增用户信息
     * @param record 用户信息
     * @return Integer
     */
    int insertSelective(SystemUser record);

    /**
     * 根据用户ID更新用户信息
     * @param record 用户信息
     * @return Integer
     */
    int updateByPrimaryKeySelective(SystemUser record);
}