package com.panda.server.cloud.boss.vo.auth;

import com.panda.server.cloud.common.vo.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录认证
 * @author w
 * @date 2020-09-01
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class LoginAuthReq extends Request {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 认证密码
     */
    private String pwd;
}
