package com.panda.server.cloud.common.vo;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 通用Response
 * @author w
 * @date 2020-06-30
 */
@Component
@Scope("prototype")
@Primary
@Data
public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应描述信息
     */
    private String msg;

    /**
     * 响应信息
     */
    private Object data;
}
