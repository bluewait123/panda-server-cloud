package com.panda.server.cloud.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页响应公共信息
 * @author w
 * @date 2020-06-28
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageResponse extends Response {
    /**
     * 分页信息
     */
    private PageInfo page;
}
