package com.panda.server.cloud.feign.service;

import com.panda.server.cloud.common.vo.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "manager-file")
public interface RemoteFileService {

    /**
     * 查询图片文件详细信息
     * @param imageId 图片文件ID
     * @return Response
     */
    @RequestMapping(value = "/image/{imageId}", method = RequestMethod.GET)
    Response queryImageDetail(@PathVariable("imageId") String imageId);
}
