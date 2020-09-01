package com.panda.server.cloud.mybatis.mapper;

import com.panda.server.cloud.mybatis.po.SystemParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 系统参数数据管理
 * @author w
 * @dage 2020-09-01
 */
@Component
public interface SystemParamMapper {
    /**
     * 根据主键获取系统参数信息
     * @param id 主键
     * @return com.panda.server.cloud.mybatis.po.SystemParam
     */
    SystemParam selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 根据系统参数编码获取系统参数信息
     * @param paraCode 系统编码
     * @return com.panda.server.cloud.mybatis.po.SystemParam
     */
    SystemParam selectByParaCode(@Param("paraCode") String paraCode);

    /**
     * 更新系统参数信息
     * @param record 系统参数信息
     * @return int
     */
    int updateByPrimaryKeySelective(SystemParam record);
}