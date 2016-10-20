package com.hisign.publicsafety.security;
import java.util.Collection;

import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
/**
 * 自己实现的过滤用户请求类，也可以直接使用 FilterSecurityInterceptor
 * 
 * AbstractSecurityInterceptor有三个派生类：
 * FilterSecurityInterceptor，负责处理FilterInvocation，实现对URL资源的拦截。
 * MethodSecurityInterceptor，负责处理MethodInvocation，实现对方法调用的拦截。
 * AspectJSecurityInterceptor，负责处理JoinPoint，主要是用于对切面方法(AOP)调用的拦截。
 * 
 * 还可以直接使用注解对Action方法进行拦截，例如在方法上加：
 * @PreAuthorize("hasRole('ROLE_SUPER')")
 * <!-- 用户是否拥有所请求资源的权限 -->
 * 
 * @author ly
 * 2013-11-19
 * 
 * @version 1.0v
 */
@Service("accessDecisionManager")
public class AccessDecisionManagerByPrivilegeImpl implements AccessDecisionManager {
	
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if(configAttributes == null) {
			//不需要权限即可访问
			return;
		}
		//所请求的资源需要的权限(一个资源可以配置多个权限)
		//用户拥有其中一个权限即可以访问
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while(iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			//访问所请求资源所需要的某个权限
			String needPermission = configAttribute.getAttribute();
			//用户所拥有的权限GrantedAuthority
			for(GrantedAuthority ga : authentication.getAuthorities()) {
				if(needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		//没有权限
		throw new AccessDeniedException(" 没有权限访问！ ");
	}

	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
