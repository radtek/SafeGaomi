package com.hisign.publicsafety.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;
/**
 * 核心的InterceptorStatusToken token = super.beforeInvocation(fi);会调用我们定义的accessDecisionManager:decide(Object object)和securityMetadataSource
 
  :getAttributes(Object object)方法。
 * 自己实现的过滤用户请求类，也可以直接使用 FilterSecurityInterceptor
 * 
 * AbstractSecurityInterceptor有三个派生类：
 * FilterSecurityInterceptor，负责处理FilterInvocation，实现对URL资源的拦截。
 * MethodSecurityInterceptor，负责处理MethodInvocation，实现对方法调用的拦截。
 * AspectJSecurityInterceptor，负责处理JoinPoint，主要是用于对切面方法(AOP)调用的拦截。
 * 
 * 还可以直接使用注解对Action方法进行拦截，例如在方法上加：
 * @PreAuthorize("hasRole('ROLE_SUPER')")
 * 
 * 
 * @author ly
 * 2013-11-19
 *  
 * @version 1.0v
 */
@Service("o2oFilterSecurityInterceptor")
public class O2oFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	public static final Logger logger = LoggerFactory.getLogger(O2oFilterSecurityInterceptor.class);
	//与applicationContext-security.xml里的myFilter的属性securityMetadataSource对应，
	//其他的两个组件，已经在AbstractSecurityInterceptor定义
	@Autowired
	private DbSecurityMetadataSource securityMetadataSource;
	@Autowired
	private AccessDecisionManagerByPrivilegeImpl accessDecisionManager;
	@Autowired
	private AuthenticationManager o2oAuthenticationManager; 
	
	@PostConstruct
	public void init(){
		super.setAuthenticationManager(o2oAuthenticationManager);
		super.setAccessDecisionManager(accessDecisionManager);
	}
	
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}
	
	private void invoke(FilterInvocation fi) throws IOException, ServletException {
		InterceptorStatusToken token = super.beforeInvocation(fi);
		HttpServletRequest request = fi.getRequest();
		String requestUri = request.getRequestURI();
		try {
			fi.getChain().doFilter(request, fi.getResponse());
		} catch (Exception e) {
			logger.error("{},requestUri={}",e.getMessage(),requestUri);
		} finally {
			super.afterInvocation(token, null);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
	
	public void destroy() {
		
	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		//下面的AccessDecisionManagerByPrivilegeImpl的supports方面必须放回true,否则会提醒类型错误
		return FilterInvocation.class;
	}
}
