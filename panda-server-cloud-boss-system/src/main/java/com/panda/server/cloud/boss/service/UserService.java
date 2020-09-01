package com.panda.server.cloud.boss.service;

import com.github.pagehelper.PageInfo;
import com.panda.server.cloud.boss.enums.BossSystemResultCode;
import com.panda.server.cloud.boss.exception.BossSystemException;
import com.panda.server.cloud.boss.vo.user.QueryUserListReq;
import com.panda.server.cloud.boss.vo.user.UserDetailVo;
import com.panda.server.cloud.boss.vo.user.UserInfoVo;
import com.panda.server.cloud.common.web.mybatis.utils.PageUtils;
import com.panda.server.cloud.common.vo.PageResponse;
import com.panda.server.cloud.mybatis.mapper.SystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 用户信息管理
 * @author w
 * @date 2020-08-31
 */
@Service
public class UserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    /**
     * 查询用户信息列表
     * @param currentUserId 当前用户ID
     * @param req 筛选条件信息
     * @return PageInfo<com.panda.server.cloud.boss.vo.user.UserInfoVo>
     */
    public PageResponse selectUserList(String currentUserId, QueryUserListReq req){
        // 设置分页信息
        PageUtils.setPaging(req);

        // 查询用户信息列表
        PageInfo<UserInfoVo> pageInfo = PageUtils.getPageInfo(systemUserMapper.selectAll(currentUserId, req));
        // 响应分页对象
        return PageUtils.getPageResponse(pageInfo);
    }

    /**
     * 根据用户ID查询用户信息
     * @param userId 用户ID
     * @return com.panda.server.cloud.boss.vo.user.UserDetailVo
     */
    public UserDetailVo selectByUserId(String userId){
        UserDetailVo user = systemUserMapper.selectDetailByPrimaryKey(userId);
        Optional.ofNullable(user).orElseThrow(() -> new BossSystemException(BossSystemResultCode.USER_INFO_NOT_EXITS_OR_LOCK, userId));
        return user;
    }

}
