package com.panda.server.cloud.common.vo;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 通用Request
 * @author w
 * @date 2020-06-30
 */
@Component
@Scope("prototype")
@Primary
@Data
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
}
