package com.panda.server.cloud.boss.service;

import com.panda.server.cloud.boss.auth.enums.AuthRedisKey;
import com.panda.server.cloud.boss.auth.vo.SessionUserInfo;
import com.panda.server.cloud.boss.enums.BossSystemResultCode;
import com.panda.server.cloud.boss.enums.SystemParamCode;
import com.panda.server.cloud.boss.enums.UserStatusCode;
import com.panda.server.cloud.boss.exception.BossSystemException;
import com.panda.server.cloud.boss.vo.auth.LoginAuthReq;
import com.panda.server.cloud.boss.vo.auth.LoginAuthResp;
import com.panda.server.cloud.common.redis.service.RedisService;
import com.panda.server.cloud.common.utils.MD5Utils;
import com.panda.server.cloud.common.utils.PrimaryKeyUtils;
import com.panda.server.cloud.common.utils.StringUtils;
import com.panda.server.cloud.mybatis.mapper.SystemUserMapper;
import com.panda.server.cloud.mybatis.po.SystemParam;
import com.panda.server.cloud.mybatis.po.SystemResource;
import com.panda.server.cloud.mybatis.po.SystemUser;
import com.panda.server.cloud.mybatis.po.SystemUserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 认证处理
 * @author w
 * @date 2020-09-01
 */
@Slf4j
@Service
public class LoginAuthService {
    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemParamService systemParamService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SystemResourceService systemResourceService;

    /**
     * 登录认证
     * @param req 认证信息
     */
    public LoginAuthResp loginAuth(LoginAuthReq req){
        // 通过用户名获取用户信息
        SystemUser systemUser = systemUserMapper.selectByUserName(req.getUserName());
        Optional.ofNullable(systemUser).orElseThrow(() -> {
            log.error("{}用户不存在",req.getUserName());
            return new BossSystemException(BossSystemResultCode.USER_INFO_NOT_EXITS_OR_LOCK,req.getUserName());
        });

        // 检查登录密码
        checkLoginPwd(systemUser,req.getPwd());

        // 检查用户状态
        checkUserStatus(systemUser);

        // 设置登录有效期
        SystemParam authTime = systemParamService.getSystemParamByParaCode(SystemParamCode.LOGIN_AUTH_TIME_OUT);
        if(StringUtils.isNotEmpty(authTime)){
            redisService.put(AuthRedisKey.AUTH_RESOURCE_KEY.getCode(systemUser.getId()),authTime.getParaValue());
        }

        // 缓存用户信息
        String token = PrimaryKeyUtils.getUuid();
        SessionUserInfo sessionUserInfo = concatCacheUserInfo(systemUser);
        redisService.putObject(AuthRedisKey.SESSION_USER_KEY.getCode(token),sessionUserInfo);

        // 缓存权限信息
        List<String> urls = getAuthUrl(sessionUserInfo.getRoleId());
        redisService.putObject(AuthRedisKey.AUTH_RESOURCE_KEY.getCode(systemUser.getId()),urls);

        LoginAuthResp resp = new LoginAuthResp();
        resp.setNickName(sessionUserInfo.getNickName());
        resp.setToken(token);
        return resp;
    }

    /**
     * 检查登录密码
     * @param systemUser 用户信息
     * @param userPwd 前端输入的密码
     */
    private void checkLoginPwd(SystemUser systemUser, String userPwd){
        userPwd = MD5Utils.hmacMD5(userPwd,systemUser.getPasswordSalt());
        String dbPwd = systemUser.getUserPassword();
        if(!userPwd.equals(dbPwd)){
            log.error("{}密码错误!", systemUser.getUserName());
            log.debug("user:{},db:{}",userPwd,dbPwd);
            throw new BossSystemException(BossSystemResultCode.USER_NOT_EXITS_OR_PWD_ERROR,systemUser.getUserName());
        }
    }

    /**
     * 检查用户状态
     * @param systemUser 用户信息
     */
    private void checkUserStatus(SystemUser systemUser){
        // 检查状态
        switch (UserStatusCode.getEnumByCode(systemUser.getStatus())){
            case ENABLE:
                break;
            case LOCK:
                throw new BossSystemException(BossSystemResultCode.USER_STATUS_LOCK_ERROR);
            case DISABLE:
                throw new BossSystemException(BossSystemResultCode.USER_INFO_NOT_EXITS_OR_LOCK,systemUser.getUserName());
            default:
                throw new BossSystemException(BossSystemResultCode.USER_STATUS_UNKNOWN_ERROR);
        }
    }

    /**
     * 组装缓存用户信息
     * @param systemUser 用户信息
     * @return SessionUserInfo
     */
    private SessionUserInfo concatCacheUserInfo(SystemUser systemUser){
        SessionUserInfo userInfo = new SessionUserInfo();
        userInfo.setId(systemUser.getId());
        userInfo.setMobile(systemUser.getMobile());
        userInfo.setNickName(systemUser.getNickName());
        userInfo.setAdminFlag(systemUser.getAdminFlag());
        userInfo.setStatus(systemUser.getStatus());

        // 获取用户角色ID
        SystemUserRole role = roleService.findUserRoleByUserId(systemUser.getId());
        userInfo.setRoleId(role.getRoleId());
        return userInfo;
    }

    /**
     * 根据角色ID获取授权资源
     * @param roleId 角色ID
     * @return List<String>
     */
    private List<String> getAuthUrl(String roleId){
        List<SystemResource> list = systemResourceService.selectByRoleId(roleId);
        List<String> urls = new ArrayList<>();
        if(StringUtils.isNotEmpty(list)){
            for(SystemResource resource : list){
                String url = resource.getAuthApi();
                if(StringUtils.isNotEmpty(url)){
                    urls.addAll(Arrays.asList(url.split(",")));
                }
            }
        }
        return urls;
    }
}
