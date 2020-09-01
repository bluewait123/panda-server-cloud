package com.panda.server.cloud.boss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

/**
 * 启动应用
 * @author w
 * @date 2020-06-23
 */
@EnableFeignClients(basePackages="com.panda.server.cloud.feign.service")
@EnableDiscoveryClient
@RestController
@EnableAsync
@SpringBootApplication
@ComponentScan(value = "com.panda.server.cloud.**")
public class Application {

    @GetMapping("/")
    @ResponseBody
    public String defaultPage(HttpSession session) {
        return session.getId();
    }

    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
