package com.panda.server.cloud.common.utils;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate处理
 */
public class RestTemplateBeanUtils {

    /**
     * 创建 RestTemplate处理
     * @param connectionRequestTime 从连接池获取连接时间
     * @param connectionTime 请求超时时间
     * @param readTimeout 读取内容超时时间
     * @return RestTemplate
     */
    public static RestTemplate create(int connectionRequestTime,int connectionTime,int readTimeout){
        // maxConnTotal 是整个连接池的大小，根据自己的业务需求进行设置
        // maxConnPerRoute 是单个路由连接的最大数，可以根据自己的业务需求进行设置
        int maxCpuCore = Runtime.getRuntime().availableProcessors();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create()
                .setMaxConnTotal(2*maxCpuCore + 3)
                .setMaxConnPerRoute(2*maxCpuCore)
                .build());
        // 从连接池获取连接的
        httpRequestFactory.setConnectionRequestTimeout(connectionRequestTime);
        // 设置连接超时时间
        httpRequestFactory.setConnectTimeout(connectionTime);
        // 读取内容超时时间
        httpRequestFactory.setReadTimeout(readTimeout);
        // 标志是否使用缓存流的形式，默认是true，缺点是当发送大量数据时，比如put/post的保存和修改，那么可能内存消耗严重。设置为false
        httpRequestFactory.setBufferRequestBody(false);
        return new RestTemplate(httpRequestFactory);
    }
}
