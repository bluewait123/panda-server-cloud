package com.panda.server.cloud.boss.vo.user;

import com.panda.server.cloud.common.vo.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新用户信息
 * @author w
 * @date 2020-08-31
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UpdateUserReq extends Request {
    /**
     * 昵称
     */
    private String nickName;

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

    /**
     * 用户状态
     */
    private Integer status;
}
