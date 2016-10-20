/**
 * 
 */
package com.hisign.publicsafety.service.auth;

import org.springframework.security.core.context.SecurityContext;

import com.hisign.publicsafety.entity.Account;

/**
 * 认证成功之后的初始化服务
 * @author chailiangzhi
 *
 */
public interface InitAfterAuth {
	/**
	 * 初始化当前登录用户相关的信息到内存，方便其他页面使用
	 * @param securityContext
	 * @return
	 * @throws Exception
	 */
	public boolean initLoginUserRelated(SecurityContext securityContext, Account user) throws Exception;
}
