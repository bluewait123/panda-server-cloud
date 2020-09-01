//package com.panda.server.cloud.boss.config;
//
//import com.panda.server.cloud.common.utils.RestTemplateBeanUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.web.client.RestTemplate;
//
///**
// * RestTemplate配置
// * 目前使用HTTP CLIENT 支持高并发请求
// * @author w
// * @date 2020-07-08
// */
//@Configuration
//@Slf4j
//@EnableAsync
//public class RestTemplateConfig {
//    /**
//     * 从连接池获取连接时间
//     */
//    @Value("${rest.template.request:10000}")
//    private int connectionRequestTime;
//
//    /**
//     * 请求超时时间
//     */
//    @Value("${rest.template.connect:10000}")
//    private int connectionTime;
//
//    /**
//     * 读取内容超时时间
//     */
//    @Value("${rest.template.read:10000}")
//    private int readTimeout;
//
//    @Bean
//    @Primary
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return RestTemplateBeanUtils.create(connectionRequestTime,connectionTime,readTimeout);
//    }
//}
