package com.panda.server.cloud.boss.auth.vo;


/**
 * 登录用户信息
 * @author wujianhui
 * @date 2020-07-01
 */
public class SessionUserInfo {
    /**
     * 用户ID
     */
    private String id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 管理员标志
     */
    private Integer adminFlag;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 状态
     */
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(Integer adminFlag) {
        this.adminFlag = adminFlag;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
