package com.panda.server.cloud.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页请求公共信息
 * @author w
 * @date 2020-06-28
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageRequest extends Request {
    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 条数
     */
    private Integer pageSize = 10;
}
