package com.panda.server.cloud.boss.service;

import com.panda.server.cloud.boss.enums.MenuType;
import com.panda.server.cloud.boss.vo.resource.MenuListItem;
import com.panda.server.cloud.boss.vo.resource.QueryMenuListResp;
import com.panda.server.cloud.common.utils.StringUtils;
import com.panda.server.cloud.mybatis.mapper.SystemResourceMapper;
import com.panda.server.cloud.mybatis.po.SystemResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单资源管理
 * @author w
 * @date 2020-09-01
 */
@Service
public class SystemResourceService {

    @Autowired
    private SystemResourceMapper systemResourceMapper;

    /**
     * 根据角色ID获取权限信息
     * @param roleId 角色ID
     * @return java.util.List<com.panda.server.cloud.mybatis.po.SystemResource>
     */
    public List<SystemResource> selectByRoleId(String roleId){
        return systemResourceMapper.selectByRoleId(roleId);
    }

    /**
     * 查询用户菜单资源
     * @param roleId 角色ID
     * @return UserMenusResp
     */
    public QueryMenuListResp findUserResource(String roleId) {
        List<SystemResource> resources = selectByRoleId(roleId);
        QueryMenuListResp resp = new QueryMenuListResp();

        if (StringUtils.isNotEmpty(resources) && resources.size() > 0) {
            List<MenuListItem> menus = new ArrayList<>();
            List<String> buttons = new ArrayList<>();

            MenuListItem item;
            for (SystemResource resource : resources) {
                if(MenuType.BUTTON.getCode().equals(resource.getMenuType())){
                    buttons.add(resource.getRouterUrl());
                }

                item = new MenuListItem();
                item.setId(resource.getId());
                item.setName(resource.getMenuName());
                item.setIcon(resource.getMenuIcon());
                item.setParentId(resource.getParentId());
                item.setRouterUrl(resource.getRouterUrl());
                item.setMenuType(resource.getMenuType());
                menus.add(item);
            }
            resp.setList(menus);
            resp.setButtons(buttons);
        }
        return resp;
    }
}
