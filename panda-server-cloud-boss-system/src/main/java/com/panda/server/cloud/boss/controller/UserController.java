package com.panda.server.cloud.boss.controller;

import com.alibaba.fastjson.JSONObject;
import com.panda.server.cloud.boss.auth.vo.SessionUserInfo;
import com.panda.server.cloud.boss.service.UserService;
import com.panda.server.cloud.boss.session.annotation.SessionUser;
import com.panda.server.cloud.boss.vo.user.AddUserReq;
import com.panda.server.cloud.boss.vo.user.QueryUserListReq;
import com.panda.server.cloud.boss.vo.user.UpdateUserReq;
import com.panda.server.cloud.common.web.controller.BasicController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息管理
 * @author w
 * @date 2020-08-31
 */
@RequestMapping("/v1/users")
@RestController
@Slf4j
public class UserController extends BasicController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户信息
     * @param req 查询条件信息
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUserList(QueryUserListReq req, @SessionUser SessionUserInfo sessionUserInfo){
        log.debug("sessionUserInfo:{}",JSONObject.toJSONString(sessionUserInfo));
        return assemble(userService.selectUserList(sessionUserInfo.getId(), req));
    }

    /**
     * 根据用户ID获取用户信息
     * @param userId 用户ID
     */
    @RequestMapping(value = "/id/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUser(@PathVariable("userId") String userId){
        return assemble(userService.selectByUserId(userId));
    }

    /**
     * 根据用户名获取用户信息
     * @param userName 用户名
     * @param pwd 用户密码
     */
    @RequestMapping(value = "/userName/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUserByUserName(@PathVariable("userName") String userName, @RequestParam("pwd") String pwd){
        log.debug("userName:{}",userName);
        log.debug("pwd:{}",pwd);
        return assemble();
    }

    /**
     * 新增用户信息
     * @param req 请求信息
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity add(@RequestBody AddUserReq req){
        log.debug("req:{}", JSONObject.toJSONString(req));
        return assemble();
    }

    /**
     * 更新用户信息
     * @param userId 用户ID
     * @param req 请求信息
     */
    @RequestMapping(value = "/id/{userId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity update(@PathVariable("userId") String userId, @RequestBody UpdateUserReq req){
        log.debug("userId:{}",userId);
        log.debug("req:{}",JSONObject.toJSONString(req));
        return assemble();
    }

    /**
     * 更新用户状态
     * @param userId 用户Id
     * @param status 用户状态
     */
    @RequestMapping(value = "/status/{userId}/{status}", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity updateStatus(@PathVariable("userId") String userId, @PathVariable("status") String status){
        log.debug("userId:{}",userId);
        log.debug("status:{}",status);
        return assemble();
    }
}
