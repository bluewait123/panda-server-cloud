package com.panda.server.cloud.boss.service;

import com.panda.server.cloud.boss.enums.SystemParamCode;
import com.panda.server.cloud.mybatis.mapper.SystemParamMapper;
import com.panda.server.cloud.mybatis.po.SystemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统参数管理
 * @author w
 * @date 2020-09-01
 */
@Service
public class SystemParamService {

    @Autowired
    private SystemParamMapper systemParamMapper;

    /**
     * 根据系统参数编码获取系统参数信息
     * @param systemParamCode 系统编码
     * @return com.panda.server.cloud.mybatis.po.SystemParam
     */
    public SystemParam getSystemParamByParaCode(SystemParamCode systemParamCode){
        return systemParamMapper.selectByParaCode(systemParamCode.getCode());
    }
}
