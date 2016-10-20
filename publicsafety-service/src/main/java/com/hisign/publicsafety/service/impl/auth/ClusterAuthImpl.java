/**
 * 
 */
package com.hisign.publicsafety.service.impl.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.api.CacheSessionService;
import com.hisign.publicsafety.common.api.ClusterAuth;
import com.hisign.publicsafety.common.bean.cookie.UserInfo;
import com.hisign.publicsafety.entity.Account;
import com.hisign.publicsafety.service.AccountService;
import com.hisign.publicsafety.service.auth.InitAfterAuth;

/**
 * 集群下的认证接口的实现
 * @author chailiangzhi
 * 
 */
@Service("clusterAuth")
public class ClusterAuthImpl implements ClusterAuth {
	private static final Logger logger = LoggerFactory.getLogger(ClusterAuthImpl.class);
	@Autowired
	private AccountService accountService;
	@Autowired
	private AuthenticationManager o2oAuthenticationManager;
	@Autowired
	private InitAfterAuth initAfterAuth;
	@Autowired
	private CacheSessionService cacheSessionService;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.auth.ClusterAuth#authByCookie()
	 */
	@Override
	public boolean authByCookie(UserInfo userInfo) throws Exception {
		if (needReAuth(userInfo)) {
			//锁定会话ID,保证每个会话只做一次认证
			synchronized (cacheSessionService.getId().intern()) {
				//双重检查,获得锁之后再检查一次是否需要重新认证
				if (needReAuth(userInfo)) {
					return doAuth(userInfo);
				}
			}
		}
		return true;
	}

	/**
	 * 将本地服务器存储的信息与cookie中的用户信息对比
	 * ，判断是否需要重新认证
	 * @param userInfo 存储在cookie中的用户信息
	 * @return
	 */
	private boolean needReAuth(UserInfo userInfo) {
		Object userIdObjInLocalCache = cacheSessionService.getAttribute(Constant.LOGIN_USER_ID);
		if (userIdObjInLocalCache == null) {
			// 如果本地内存中没有存放当前登录的用户ID，需要重新认证
			return true;
		}
		int userIdInLocalCache = Integer.valueOf(userIdObjInLocalCache.toString());
		if (userIdInLocalCache != userInfo.getAccountId()) {
			// 如果本地内存中的用户ID与cookie中的用户ID不同，需要重新认证
			return true;
		}
		SecurityContext securityContextInCache = (SecurityContext) cacheSessionService
				.getAttribute(Constant.SPRING_SECURITY_CONTEXT_CACHE);
		if (securityContextInCache == null) {
			// 如果本地内存中没有SecurityContext，需要重新认证
			return true;
		}
		return false;
	}

	/**
	 * 通过Cookie里的userInfo进行认证
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	private boolean doAuth(UserInfo userInfo) throws Exception {
		String userId = String.valueOf(userInfo.getAccountId());
		Account user = accountService.getById(userId);
		if (user == null) {
			logger.error("can't find user,id:{}", userId);
			return false;
		}
		Authentication authentication = o2oAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				user.getAccountName(), user.getPassword()));
		SecurityContext securityContextNew = SecurityContextHolder.getContext();
		securityContextNew.setAuthentication(authentication);
		initAfterAuth.initLoginUserRelated(securityContextNew, user);
		logger.info("authByCookie finished");
		return true;
	}

}
