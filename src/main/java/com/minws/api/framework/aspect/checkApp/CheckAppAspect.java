/**
 * @author Hadong
 *
 */
package com.minws.api.framework.aspect.checkApp;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minws.api.app.AppUtil;
import com.minws.api.framework.util.mybatis.MybatisUtil;

@Component
@Aspect
public class CheckAppAspect {
	private SqlSessionFactory appSqlSessionFactory = MybatisUtil.connectDb("com/minws/api/app/data/App-mybatis-config.xml", "public");

	@Autowired
	private HttpServletRequest request;

	@Pointcut("@annotation(com.minws.api.framework.aspect.checkApp.CheckApp)")
	public void aspect() {
	}

	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
	}

	// 配置后置通知,使用在方法aspect()上注册的切入点
	@After("aspect()")
	public void after(JoinPoint joinPoint) {
	}

	// 配置环绕通知,使用在方法aspect()上注册的切入点
	@Around("aspect()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object jp = null;
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();// 当前执行的方法全名
		String[] allowAppId = method.getAnnotation(CheckApp.class).allowAppId();// 获取注解参数值
		SqlSession systemSqlSession = appSqlSessionFactory.openSession();
		if (!AppUtil.verifyAppSignature(request.getParameter("signature"), request.getParameter("timestamp"), request.getParameter("appid"), allowAppId, systemSqlSession)) {
			throw new Exception("Signature check failure");
		}
		jp = joinPoint.proceed();
		return jp;
	}

	// 配置后置返回通知,使用在方法aspect()上注册的切入点
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint) {
	}

	// 配置抛出异常后通知,使用在方法aspect()上注册的切入点
	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex) {
	}

}