package com.panda.server.cloud.boss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型枚举
 * @author w
 * @date 2020-06-30
 */
@AllArgsConstructor
@Getter
public enum MenuType {
    /**
     * 定义
     */
    MENU(1,"菜单"),
    BUTTON(2,"按钮"),

    ;

    private Integer code;
    private String desc;

    public static MenuType getEnumByCode(Integer code){
        MenuType e = null;
        for(MenuType status : values()){
            if(status.getCode().equals(code)){
                e = status;
                break;
            }
        }
        return e;
    }
}
