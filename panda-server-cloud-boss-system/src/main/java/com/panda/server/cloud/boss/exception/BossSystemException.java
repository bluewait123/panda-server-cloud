package com.panda.server.cloud.boss.exception;

import com.panda.server.cloud.boss.enums.BossSystemResultCode;
import com.panda.server.cloud.common.exception.BusinessException;
import lombok.Getter;

/**
 * 业务异常
 * @author w
 * @date 2020-06-28
 */
@Getter
public class BossSystemException extends BusinessException {
    private static final long serialVersionUID = 1L;

    public BossSystemException(Throwable source, String respCode, String respDesc){
        super(source,respCode, respDesc);
    }

    public BossSystemException(BossSystemResultCode error, Object...param){
        super(error.getCode(), error.getMsg(param));
    }
}
