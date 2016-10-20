/**
 * 
 */
package com.hisign.publicsafety.security.web.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.api.CacheSessionService;

/**
 * @author chailiangzhi
 * @date 2015-9-22
 * 自定义存储springframework SecurityContext的方式
 */
@Service("cacheSecurityContextRepo")
public class CacheSecurityContextRepository implements SecurityContextRepository {

	@Autowired
	private CacheSessionService cacheSessionService;

	/* (non-Javadoc)
	 * @see org.springframework.security.web.context.SecurityContextRepository#loadContext(org.springframework.security.web.context.HttpRequestResponseHolder)
	 */
	@Override
	public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
		SecurityContext context = (SecurityContext) cacheSessionService
				.getAttribute(Constant.SPRING_SECURITY_CONTEXT_CACHE);
		if (context == null) {
			return generateNewContext();
		}
		return context;
	}

	/**
	 * 生成空的SecurityContext
	 * @return
	 */
	protected SecurityContext generateNewContext() {
		return SecurityContextHolder.createEmptyContext();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.context.SecurityContextRepository#saveContext(org.springframework.security.core.context.SecurityContext, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.context.SecurityContextRepository#containsContext(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean containsContext(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

}
