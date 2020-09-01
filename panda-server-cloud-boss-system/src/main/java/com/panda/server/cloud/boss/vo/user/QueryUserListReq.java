package com.panda.server.cloud.boss.vo.user;

import com.panda.server.cloud.common.vo.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询用户信息列表，查询条件信息
 * @author w
 * @date 2020-08-31
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class QueryUserListReq extends PageRequest {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户状态
     */
    private Integer status;
}
