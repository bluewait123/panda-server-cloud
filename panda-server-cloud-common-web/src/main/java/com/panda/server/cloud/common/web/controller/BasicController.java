package com.panda.server.cloud.common.web.controller;

import com.panda.server.cloud.common.enums.BasicResultCode;
import com.panda.server.cloud.common.exception.BusinessException;
import com.panda.server.cloud.common.vo.PageResponse;
import com.panda.server.cloud.common.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共控制器(包含全局异常处理)
 * @author w
 * @date 2020-06-28
 */
@Slf4j
public class BasicController {

    public ResponseEntity assemble(String code, String desc){
        Response resp = new Response();
        resp.setCode(code);
        resp.setMsg(desc);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    public ResponseEntity assemble(Object data){
        Response resp = new Response();
        resp.setCode(BasicResultCode.SUCCESS.getCode());
        resp.setMsg(BasicResultCode.SUCCESS.getDesc());
        resp.setData(data);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    public ResponseEntity assemble(PageResponse response){
        response.setCode(BasicResultCode.SUCCESS.getCode());
        response.setMsg(BasicResultCode.SUCCESS.getDesc());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity assemble(){
        return assemble(BasicResultCode.SUCCESS.getCode(),BasicResultCode.SUCCESS.getDesc());
    }

    /**
     * 全局异常处理
     * @param request HTTP 请求信息
     * @param ex 异常信息
     * @return BossResp 转换后的响应信息
     */
    @ExceptionHandler(value = { Exception.class, Throwable.class })
    public @ResponseBody ResponseEntity handleException(HttpServletRequest request, Throwable ex) {
        log.error(ex.getMessage(), ex);
        String errorCode;
        String errorMsg;

        // 自定义异常类型
        if (ex instanceof BusinessException) {
            BusinessException be = (BusinessException) ex;
            if(null != be.getSource()){
                log.error(be.getMessage(),be.getSource());
            }
            errorCode = be.getRespCode();
            errorMsg = be.getRespDesc();
        }else{
            // 通用错误类型
            errorCode = BasicResultCode.FAIL.getCode();
            errorMsg = BasicResultCode.FAIL.getDesc();
        }

        Response response = new Response();
        response.setCode(errorCode);
        response.setMsg(errorMsg);
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
