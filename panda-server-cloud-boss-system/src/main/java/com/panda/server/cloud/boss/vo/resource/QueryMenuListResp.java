package com.panda.server.cloud.boss.vo.resource;

import lombok.Data;

import java.util.List;

/**
 * 查询用户菜单列表
 * @author w
 * @date 2020-09-01
 */
@Data
public class QueryMenuListResp {
    private List<MenuListItem> list;
    private List<String> buttons;
}
