package com.panda.server.cloud.boss.vo.user;

import com.panda.server.cloud.common.vo.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 新增用户信息
 * @author w
 * @date 2020-08-31
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AddUserReq extends Request {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像ID
     */
    private String headImgId;

    /**
     * 角色ID
     */
    private String roleId;
}
