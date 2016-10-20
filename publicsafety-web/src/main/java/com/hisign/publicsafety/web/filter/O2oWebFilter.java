/**
 * 
 */
package com.hisign.publicsafety.web.filter;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.SpringHolder;
import com.hisign.publicsafety.common.bean.cookie.UserInfo;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.util.WebContext;
import com.hisign.publicsafety.util.WebUtil;
import com.hisign.publicsafety.web.tool.O2oCookieHelper;

/**
 * 根过滤器,获取web上下问信息
 * 并做一些初始化工作
 * @author chailiangzhi
 * @date 2015-9-18
 * 
 */
public class O2oWebFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(O2oWebFilter.class);

	private int cookieTimeout = 3600 * 1000;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String cookieTimeoutStr = filterConfig.getInitParameter("cookieTimeout");
		int cookieTimeoutMinute = 60;
		if (cookieTimeoutStr != null) {
			try {
				cookieTimeoutMinute = Integer.valueOf(cookieTimeoutStr);
			} catch (NumberFormatException e) {
				logger.warn("cookieTimeout NumberFormatException");
			}
		}
		cookieTimeout = cookieTimeoutMinute * 60 * 1000;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest requestHttp = (HttpServletRequest) request;
		WebContext.setRequest(requestHttp);
		HttpServletResponse responseHttp = (HttpServletResponse) response;
		WebContext.setResponse(responseHttp);
		String reqUri = requestHttp.getRequestURI();
		String page = WebUtil.getSysCheckPagePara(requestHttp);
		if (page != null) {
			logger.info("reqUri:{},page:{}", reqUri, page);
			chain.doFilter(request, response);
			return;
		}
		// 检查Cookie中的自定义会话ID
		String sessionId = O2oCookieHelper.getSessionId();
		// 如果cookie中没有自定义会话ID，创建它
		if (sessionId == null || sessionId.isEmpty()) {
			sessionId = UUID.randomUUID().toString();
			logger.warn("自定义会话Id空,Create:{},RequestURI={},need reLogin", sessionId, reqUri);
			O2oCookieHelper.setSessionId(sessionId);
			WebUtil.filterForbidDispatch(requestHttp, responseHttp, Constant.STATUS_COOKIE_CHECK,
					Constant.URI_COOKIE_CHECK);
			return;
		}
		boolean isTimeOut = false;
		// 获取Cookie中的用户信息
		try {
			UserInfo userInfo = O2oCookieHelper.getUserInfo();
			if (logger.isDebugEnabled()) {
				Date timeOutDate = new Date(userInfo.getLastAccessTime() + cookieTimeout);
				logger.debug("Cookie will time out after:{}", timeOutDate);
			}
			if (System.currentTimeMillis() - userInfo.getLastAccessTime() > cookieTimeout) {
				isTimeOut = true;
			}
			if (isTimeOut && WebUtil.needForwardOnTimeOut(requestHttp)) {
				// 超时，擦除用户cookie
				logger.warn("Cookie TimeOut,need reLogin");
				O2oCookieHelper.clearUserInfo();
				WebUtil.filterForbidDispatch(requestHttp, responseHttp, Constant.STATUS_COOKIE_TIMEOUT,
						Constant.URI_COOKIE_TIMEOUT);
				return;
			}
			if (userInfo.isLogin()) {
				userInfo.setLastAccessTime(System.currentTimeMillis());
				O2oCookieHelper.setUserInfo(userInfo);
				boolean result = SpringHolder.CLUSTER_AUTH.authByCookie(userInfo);
			}
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}

		chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
