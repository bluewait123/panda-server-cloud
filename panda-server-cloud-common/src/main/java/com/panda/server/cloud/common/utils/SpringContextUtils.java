package com.panda.server.cloud.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext.
 */
@Component
@Slf4j
public class SpringContextUtils implements ApplicationContextAware, DisposableBean {
	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		if (SpringContextUtils.applicationContext == null) {
			// NOSONAR
			SpringContextUtils.applicationContext = applicationContext;
		}
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return applicationContext.getBean(clazz);
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean,
	 * 自动转型为所赋值对象的类型.com.froad.ebank.filter.service.transform.impl.ConvertXML
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanByClassName(String className) {
		try {
			Class<?> classInstance = Class.forName(className);
			return (T) getBean(classInstance);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(),e);
		}
		return null;
	}

	/**
	 * 清除applicationContext静态变量.
	 */
	public static void cleanApplicationContext() {
		applicationContext = null;
	}

	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在beans.xml中定义SpringContextHolder");
		}
	}

	@Override
	public void destroy() {
		SpringContextUtils.cleanApplicationContext();
	}
}
