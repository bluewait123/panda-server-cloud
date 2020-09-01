package com.panda.server.cloud.boss.auth.enums;

/**
 * 定义认证相关key
 * @author w
 * @date 2020-07-01
 */
public enum AuthKeyCode {
    /**
     * 定义认证相关key
     */
    TOKEN("token","登录认证ToKen"),
    USER_INFO("userInfo","登录用户信息"),
    USER_ID("userId","登录用户ID")

    ;

    private String code;
    private String desc;

    AuthKeyCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
