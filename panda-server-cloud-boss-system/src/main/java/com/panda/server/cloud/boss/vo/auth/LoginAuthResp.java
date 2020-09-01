package com.panda.server.cloud.boss.vo.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录认证
 * @author w
 * @date 2020-09-01
 */
@Data
public class LoginAuthResp {
    private String nickName;
    private String token;
}
