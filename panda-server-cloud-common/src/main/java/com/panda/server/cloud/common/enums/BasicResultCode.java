package com.panda.server.cloud.common.enums;

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
public enum BasicResultCode {

    /**
     * 定义通用响应码
     */
    SUCCESS("0000","交易成功!"),
    FAIL("9999","系统繁忙,请稍后再试!"),
    NOT_NULL( "9901", "{0}参数不能为空"),
    AUTH_TIME_OUT("AUTH_TIME_OUT","登录超时!"),

    ;

    /**
     *
     */
    private String code;
    private String desc;

    public String getMsg(Object...param){
        return MessageFormat.format(desc,param);
    }
}
