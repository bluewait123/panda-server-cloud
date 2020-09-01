package com.panda.server.cloud.boss.auth.enums;

import java.text.MessageFormat;

/**
 * 认证Redis Key
 */
public enum AuthRedisKey {
    /**
     * 定义授权KEY
     */
    PROJECT_PREFIX("panda:boss:auth:","存储key前缀"),
    SESSION_USER_KEY("session:user:{0}","登录用户信息KEY; {0}:TOKEN"),
    AUTH_RESOURCE_KEY("resource:user:{0}","用户授权资源信息KEY; {0}:USER_ID"),
    AUTH_USER_TIMEOUT("session.timeout:{0}","登录用户有效期; {0}:USER_ID")

    ;

    private String code;
    private String desc;

    AuthRedisKey(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode(Object...params) {
        return this.equals(PROJECT_PREFIX) ? code : PROJECT_PREFIX.getCode() + MessageFormat.format(code,params);
    }

    public String getDesc() {
        return desc;
    }}
