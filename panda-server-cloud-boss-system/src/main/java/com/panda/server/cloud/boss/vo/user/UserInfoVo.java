package com.panda.server.cloud.boss.vo.user;

import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 * @author w
 * @date 2020-08-31
 */
@Data
public class UserInfoVo {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户头像访问地址
     */
    private String imageUrl;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 角色名称
     */
    private String roleName;
}
