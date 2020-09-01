package com.panda.server.cloud.boss.controller;


import com.panda.server.cloud.boss.service.LoginAuthService;
import com.panda.server.cloud.boss.vo.auth.LoginAuthReq;
import com.panda.server.cloud.common.web.controller.BasicController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 认证相关API
 * @author w
 * @date 2020-08-31
 */
@RequestMapping("/v1/auth")
@RestController
@Slf4j
public class AuthController extends BasicController {

    @Autowired
    private LoginAuthService loginAuthService;

    /**
     * 登录认证
     * @param req 认证信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginAuthReq req){
        return assemble(loginAuthService.loginAuth(req));
    }
}
