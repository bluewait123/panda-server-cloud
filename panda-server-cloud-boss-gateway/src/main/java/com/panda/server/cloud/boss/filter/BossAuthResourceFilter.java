package com.panda.server.cloud.boss.filter;

import com.alibaba.fastjson.JSONObject;
import com.panda.server.cloud.boss.auth.enums.AuthKeyCode;
import com.panda.server.cloud.boss.auth.enums.AuthRedisKey;
import com.panda.server.cloud.boss.auth.vo.SessionUserInfo;
import com.panda.server.cloud.boss.service.GatewayRedisService;
import com.panda.server.cloud.boss.utils.AuthIgnoreResources;
import com.panda.server.cloud.common.utils.StringUtils;
import com.panda.server.cloud.common.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 资源鉴权-检查是否有权限登录
 * @author w
 * @date 2020-08-31
 */
@Component
@Slf4j
public class BossAuthResourceFilter extends AbstractGatewayFilterFactory<BossAuthResourceFilter.NameConfig> {
    @Autowired
    private AuthIgnoreResources authIgnoreResources;

    @Resource
    private GatewayRedisService redisService;

    public BossAuthResourceFilter(){
        super(NameConfig.class);
        log.info("Loaded GatewayFilterFactory [BossAuthResourceFilter]");
    }

    @Override
    public GatewayFilter apply(BossAuthResourceFilter.NameConfig nameConfig) {
        return (exchange, chain) -> auth(exchange) ? chain.filter(exchange) : getErrorResponse(exchange.getResponse());
    }

    private boolean auth(ServerWebExchange exchange){
        try{
            ServerHttpRequest request = exchange.getRequest();
            String requestUri = request.getURI().getPath();

            // 检查白名单
            List<String> urls = getIgnoreInfo(authIgnoreResources.getUrl());
            if(urls.contains(requestUri)){
                log.info("资源认证完成,URL:[{}],认证结果:[白名单通过]",requestUri);
                return true;
            }

            // TOKEN检查
            String token = request.getHeaders().getFirst(AuthKeyCode.TOKEN.getCode());
            if (StringUtils.isEmpty(token)) {
                log.error("TOKEN不存在！资源URL:{}",requestUri);
                return false;
            }

            String sessionUserKey = AuthRedisKey.SESSION_USER_KEY.getCode(token);
            SessionUserInfo sessionUserInfo = redisService.getObject(sessionUserKey,SessionUserInfo.class);
            if(null == sessionUserInfo){
                log.error("登录信息不存在！token:{}, 资源URL:{}",token, requestUri);
                return false;
            }

            // 检查资源白名单
            List<String> resource = getIgnoreInfo(authIgnoreResources.getResource());
            if(!resource.contains(requestUri)){
                // 资源鉴权
                String resourceKey = AuthRedisKey.AUTH_RESOURCE_KEY.getCode(sessionUserInfo.getId());
                resource = redisService.getList(resourceKey,String.class);
                if(null == resource || !resource.contains(requestUri)){
                    log.debug("resource:{}", JSONObject.toJSONString(resource));
                    log.error("用户无该资源权限！资源URL:{}",requestUri);
//                    return false;
                }
            }

            // 更新用户信息有效期 默认300秒
            String redisAuthTimeOut = redisService.get(AuthRedisKey.AUTH_USER_TIMEOUT.getCode(sessionUserInfo.getId()));
            Long authTimeOut = StringUtils.isEmpty(redisAuthTimeOut) ? 300 : Long.valueOf(redisAuthTimeOut);
            redisService.putObject(sessionUserKey,sessionUserInfo,authTimeOut);
            log.info("资源认证完成,URL:[{}],认证结果:[认证通过]", requestUri);
            return true;

        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }

    private List<String> getIgnoreInfo(List<String> ignoreInfo){
        if(null == ignoreInfo){
            return new ArrayList<>();
        }

        if(ignoreInfo.size() == 0){
            return ignoreInfo;
        }

        List<String> result = new ArrayList<>();
        for(String s : ignoreInfo){
            String[] arr = s.split(",");
            result.addAll(Arrays.asList(arr));
        }
        return result;
    }

    /**
     * 写入错误信息
     * @param response http响应
     */
    private Mono<Void> getErrorResponse(ServerHttpResponse response){
        Response resp = new Response();
        resp.setCode("AUTH_FAIL");
        resp.setMsg("非法请求!");
        DataBuffer buffer = response.bufferFactory().wrap(JSONObject.toJSONString(resp).getBytes(StandardCharsets.UTF_8));
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    public static class NameConfig {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
