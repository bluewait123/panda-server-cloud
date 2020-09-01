package com.panda.server.cloud.boss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.MessageFormat;

/**
 * 通用响应码
 * @author w
 * @date 2020-06-28
 */
@AllArgsConstructor
@Getter
public enum BossSystemResultCode {

    /**
     * 1001-1099 操作用户信息异常
     */
    USER_INFO_NOT_EXITS_OR_LOCK("1001","{0}用户不存在或被禁用!"),
    USER_NOT_EXITS_OR_PWD_ERROR("1002","{0}用户不存在或密码错误!"),
    USER_STATUS_LOCK_ERROR("1003","您的账号已被锁定，请联系管理员重置密码!"),
    USER_STATUS_UNKNOWN_ERROR("1004","您的账号状态异常，请联系管理员!"),

    /**
     * 1101 -1199 角色相关
     */
    ROLE_NOT_EXITS_ERROR("1101","角色信息不存在!"),

    ;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String desc;

    public String getMsg(Object...param){
        return MessageFormat.format(desc,param);
    }
}
