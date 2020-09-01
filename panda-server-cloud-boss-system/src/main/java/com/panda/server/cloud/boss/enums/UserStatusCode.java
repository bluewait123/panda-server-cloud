package com.panda.server.cloud.boss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态枚举
 * @author w
 * @date 2020-06-30
 */
@AllArgsConstructor
@Getter
public enum UserStatusCode {
    /**
     * 定义用户各种状态
     */
    ENABLE(1,"正常"),
    DISABLE(0,"禁用"),
    LOCK(2,"锁定")


    ;

    private Integer code;
    private String desc;

    public static UserStatusCode getEnumByCode(Integer code){
        UserStatusCode e = null;
        for(UserStatusCode status : values()){
            if(status.getCode().equals(code)){
                e = status;
                break;
            }
        }
        return e;
    }
}
