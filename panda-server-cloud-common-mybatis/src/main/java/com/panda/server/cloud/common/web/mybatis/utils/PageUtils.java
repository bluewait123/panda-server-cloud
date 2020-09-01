package com.panda.server.cloud.common.web.mybatis.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panda.server.cloud.common.utils.StringUtils;
import com.panda.server.cloud.common.vo.PageRequest;
import com.panda.server.cloud.common.vo.PageResponse;
import com.panda.server.cloud.common.vo.PageResponseData;

import java.util.List;

/**
 * 分页工具类
 * @author w
 * @date 2020-08-31
 */
public class PageUtils {

    /**
     * 设置分页信息
     * @param request 分页请求信息
     */
    public static void setPaging(PageRequest request){
        // 第一页进行count统计总数，其余翻页不统计总数
        PageHelper.startPage(request.getPageNum(),request.getPageSize(), request.getPageNum() == 1);
    }

    /**
     * 获取分页信息
     * @param list 查询数据
     * @return PageInfo<?>
     */
    public static  <T> PageInfo<T> getPageInfo(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        PageHelper.clearPage();
        return pageInfo;
    }

    /**
     * 返回分页响应信息
     * @param pageInfo 分页信息
     */
    public static PageResponse getPageResponse(PageInfo pageInfo){
        return getPageResponse(pageInfo,null);
    }

    /**
     * 返回分页响应信息
     * @param pageInfo 分页信息
     * @param other 其他返回信息
     */
    public static PageResponse getPageResponse(PageInfo pageInfo, Object other){
        // 转换分页信息
        com.panda.server.cloud.common.vo.PageInfo resultPageInfo = new com.panda.server.cloud.common.vo.PageInfo();
        resultPageInfo.setPageNum(0 == pageInfo.getPageNum() ? 1 : pageInfo.getPageNum());
        resultPageInfo.setPageSize(pageInfo.getPageSize());
        resultPageInfo.setTotalCount(pageInfo.getTotal());
        resultPageInfo.setPageCount(pageInfo.getPages());

        // 封装响应对象
        PageResponse response = new PageResponse();
        response.setPage(resultPageInfo);

        // 设置返回数据
        if(StringUtils.isEmpty(other)){
            response.setData(pageInfo.getList());
        }else{
            PageResponseData data = new PageResponseData();
            data.setList(pageInfo.getList());
            data.setOther(other);
            response.setData(data);
        }
        return response;
    }
}
