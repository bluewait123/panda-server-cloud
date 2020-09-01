package com.panda.server.cloud.boss.session.resolver;

import com.panda.server.cloud.boss.auth.enums.AuthKeyCode;
import com.panda.server.cloud.boss.auth.enums.AuthRedisKey;
import com.panda.server.cloud.boss.auth.vo.SessionUserInfo;
import com.panda.server.cloud.boss.session.annotation.SessionUser;
import com.panda.server.cloud.common.enums.BasicResultCode;
import com.panda.server.cloud.common.exception.BusinessException;
import com.panda.server.cloud.common.redis.service.RedisService;
import com.panda.server.cloud.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 给controller层中添加@SessionUser SessionUserInfo 注解的对象封装数据
 * @author wujianhui
 * @date 2020-03-05
 */
@Component
public class SessionUserInfoResolver implements HandlerMethodArgumentResolver {
    /**
     * 获取缓存接口实现
     */
    @Autowired
    private RedisService redisService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 如果参数类型是CurrentUser并且有CurrentSession注解则支持
        return parameter.getParameterType().isAssignableFrom(SessionUserInfo.class)
                && parameter.hasParameterAnnotation(SessionUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        // 获取请求头的TOKEN
        String token = webRequest.getHeader(AuthKeyCode.TOKEN.getCode());
        if(StringUtils.isEmpty(token)){
            token = (String) webRequest.getAttribute(AuthKeyCode.TOKEN.getCode(),RequestAttributes.SCOPE_REQUEST);
        }

        if(StringUtils.isNotEmpty(token)){
            // 从缓存中查询并返回
            String sessionUserKey = AuthRedisKey.SESSION_USER_KEY.getCode(token);
            SessionUserInfo sessionUserInfo = redisService.getObject(sessionUserKey,SessionUserInfo.class);
            if (sessionUserInfo != null) {
                return sessionUserInfo;
            }
        }

        throw new BusinessException(BasicResultCode.AUTH_TIME_OUT);
    }
}
