package com.panda.server.cloud.boss.controller;

import com.alibaba.fastjson.JSONObject;
import com.panda.server.cloud.common.web.controller.BasicController;
import com.panda.server.cloud.common.vo.Response;
import com.panda.server.cloud.feign.service.RemoteFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/note/data")
public class NoteDataController extends BasicController {

    @Autowired
    private RemoteFileService remoteFileService;

    @GetMapping("/{noteId}")
    @ResponseBody
    public ResponseEntity detail(@PathVariable("noteId") String noteId){
        log.debug("开始查询ID:{},笔记信息",noteId);
        log.debug("查询图片信息...");
        Response response = remoteFileService.queryImageDetail(noteId);
        log.debug("查询图片信息.结果:{}", JSONObject.toJSONString(response));
        return assemble();
    }
}
