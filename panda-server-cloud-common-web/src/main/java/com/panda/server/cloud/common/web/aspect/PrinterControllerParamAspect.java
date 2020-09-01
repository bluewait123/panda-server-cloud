package com.panda.server.cloud.common.web.aspect;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonIOException;
import com.panda.server.cloud.common.utils.StringUtils;
import com.panda.server.cloud.common.vo.Request;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller请求切面
 * @author wujianhui
 * @date 2019/2/20
 */
@Aspect
@Component
@Slf4j
public class PrinterControllerParamAspect {
	/**
	 * 定义切点Pointcut
	 */
	@Pointcut("execution(* com.panda..*(..)) && (@annotation(org.springframework.web.bind.annotation.GetMapping)" +
			" || @annotation(org.springframework.web.bind.annotation.PostMapping) " +
			"|| @annotation(org.springframework.web.bind.annotation.RequestMapping))")
	public void printerControllerAspect() {
	}

	@Around("printerControllerAspect()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();

		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String contentType = request.getContentType();
		String queryString = request.getQueryString();
		
		log.info("接收数据-AOP: {}, method: {}, url: {}, contentType:{}, params: {}", url, method, uri, contentType,
				queryString);
		try {
			if(HttpMethod.GET.toString().equalsIgnoreCase(method) && StringUtils.isNotEmpty(request.getParameterMap())) {
				log.info("接收数据-AOP: {}", JSON.toJSONString(request.getParameterMap(),true));
			}else{
				Object[] args = pjp.getArgs();
				for (Object arg : args) {
					if(arg instanceof MultipartFile){
						log.info("接收数据-AOP：文件上传");
					}else if(arg instanceof Request){
						log.info("接收数据-AOP: {}", JSON.toJSONString(arg, true));
					}else{
						log.info("接收数据-AOP：{}", args);
					}
				}
			}
		} catch (JsonIOException e) {
			log.info("接收数据-AOP: {}", queryString);
		}
		Object result = pjp.proceed(pjp.getArgs());
		try {
			// 如果类型为文件上传、下载时不打印内容 不然会非常耗内存
			if(result instanceof ResponseEntity){
				ResponseEntity responseEntity = (ResponseEntity)result;
				log.info("响应数据-AOP: {}", JSON.toJSONString(responseEntity.getBody(),true));
			}

		} catch (JsonIOException e) {
			log.info("响应数据-AOP: {}", result.toString());
		}
		return result;
	}
}
