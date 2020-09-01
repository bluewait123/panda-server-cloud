package com.panda.server.cloud.boss.controller;

import com.panda.server.cloud.boss.auth.vo.SessionUserInfo;
import com.panda.server.cloud.boss.service.SystemResourceService;
import com.panda.server.cloud.boss.session.annotation.SessionUser;
import com.panda.server.cloud.common.web.controller.BasicController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源相关API
 * @author w
 * @date 2020-08-31
 */
@RequestMapping("/v1/resource")
@RestController
@Slf4j
public class ResourceController extends BasicController {

    @Autowired
    private SystemResourceService systemResourceService;

    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity menus(@SessionUser SessionUserInfo userInfo){
        return assemble(systemResourceService.findUserResource(userInfo.getRoleId()));
    }
}
