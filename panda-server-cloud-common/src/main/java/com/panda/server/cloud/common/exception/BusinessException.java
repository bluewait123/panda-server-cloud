package com.panda.server.cloud.common.exception;

import com.panda.server.cloud.common.enums.BasicResultCode;
import lombok.Getter;

/**
 * 业务异常
 * @author w
 * @date 2020-06-28
 */
@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 响应码
     */
    private String respCode;

    /**
     * 响应信息
     */
    private String respDesc;

    /**
     * 异常来源
     */
    private Throwable source;

    public BusinessException(String respCode, String respDesc){
        super(respDesc);
        this.respCode = respCode;
        this.respDesc = respDesc;
    }

    public BusinessException(Throwable source, String respCode, String respDesc){
        super(respDesc);
        this.respCode = respCode;
        this.respDesc = respDesc;
        this.source = source;
    }

    public BusinessException(BasicResultCode error, Object...param){
        super(error.getMsg(param));
        this.respCode = error.getCode();
        this.respDesc = super.getMessage();
    }

    public BusinessException(Throwable source, BasicResultCode error, Object...param){
        super(error.getMsg(param));
        this.respCode = error.getCode();
        this.respDesc = super.getMessage();
        this.source = source;
    }
}
