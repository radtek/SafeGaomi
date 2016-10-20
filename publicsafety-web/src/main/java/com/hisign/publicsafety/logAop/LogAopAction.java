package com.hisign.publicsafety.logAop;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hisign.publicsafety.entity.Log;
import com.hisign.publicsafety.mapper.LogMapper;
import com.hisign.publicsafety.service.abs.BaseBean;
import com.hisign.publicsafety.util.Common;

/**
 * AOP注解方法实现日志管理 利用spring AOP 切面技术记录日志 
 * 
 * 定义切面类（这个是切面类和切入点整合在一起的),这种情况是共享切入点情况;
 * 
 * @author ly 2014-04-10
 * 
 * @version 1.0v
 */
@Aspect
@Component
public class LogAopAction {
	@Autowired
	private LogMapper logMapper;

	private static Logger logger = LoggerFactory.getLogger(LogAopAction.class);

	public Object logAll(ProceedingJoinPoint point) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Object result = null;
		// 执行方法名
		String methodName = point.getSignature().getName();
		Object target = point.getTarget();
		String className = target.getClass().getSimpleName();
		String user = null;
		Long start = 0L;
		Long end = 0L;
		String ip = null;
		// 当前用户

		// 执行方法所消耗的时间
		start = System.currentTimeMillis();
		result = point.proceed();
		end = System.currentTimeMillis();

		try {
			ip = Common.toIpAddr(request);
		} catch (Exception e) {
			ip = "无法获取登录用户Ip";
		}
		try {
			// 登录名
			user = Common.findAuthenticatedUsername();
			if (Common.isEmpty(user)) {
				user = "无法获取登录用户信息！";
			}
		} catch (Exception e) {
			user = "无法获取登录用户信息！";
		}
		String name = null;
		// 操作范围
		name = className;
		// 操作类型
		String opertype = "";
		opertype = methodName;
		boolean toLog = false;
		Log log = null;
		if (target instanceof BaseBean) {
			BaseBean baseBean = (BaseBean) target;
			log = baseBean.getLog(methodName);
			if (log != null) {
				toLog = true;
			}
		}
		if (toLog) {
			Long time = end - start;
			log.setUsername(user);
			log.setAction(name + "," + opertype);
			logger.debug("log.getRev1()=" + log.getRev1());
			log.setActionTime(time.toString());
			log.setUserIP(ip);
			log.setOperTime(new Date());
			logMapper.add(log);
		}
		return result;
	}
}
