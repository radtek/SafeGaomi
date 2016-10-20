/**
 * 
 */
package com.hisign.publicsafety.web.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
import com.hisign.publicsafety.util.FileUtil;
import com.hisign.publicsafety.util.PropertiesUtils;
import com.hisign.publicsafety.util.WebUtil;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;
import com.hisign.publicsafety.web.beans.HttpParaCleanRequestWrapper;

/**
 * @author chailiangzhi
 * @date 2015-9-1
 * 防止CSRF攻击，XSS攻击，SQL注入
 */
public class DefendHackFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(DefendHackFilter.class);
	private List<String> csrfIgnoreList;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("csrf_ignore.properties");
		csrfIgnoreList = FileUtil.readFile2List(is);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest requestServ, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if (!(requestServ instanceof HttpServletRequest)) {
			chain.doFilter(requestServ, response);
			return;
		}

		HttpServletRequest requestHttpOriginal = (HttpServletRequest) requestServ;
		// 包装Request,防XSS攻击和SQL注入
		HttpServletRequest requestHttp = new HttpParaCleanRequestWrapper(requestHttpOriginal);

		String reqUri = requestHttp.getRequestURI();
		// 去除jsessionid后缀
		reqUri = reqUri.split(";")[0];
		String contextPath = requestHttp.getContextPath();
		String reqUriPostfix = reqUri.replaceAll(contextPath, "");
		if (csrfIgnoreList.indexOf(reqUriPostfix) > -1) {
			logger.warn("csrfIgnoreList have:" + reqUriPostfix);
			chain.doFilter(requestHttp, response);
			return;
		}
		csrfDefend(requestHttp, response, chain);
	}

	/**
	 * @param requestHttp
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 * 防御CSRF
	 */
	private void csrfDefend(HttpServletRequest requestHttp, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean refererOk = false;
		boolean tokenOk = false;
		// 判 断   Referer  是 否 以   hostName  开 头
		String referer = requestHttp.getHeader("Referer");
		String hostNameCfg = PropertiesUtils.findPropertiesKey("hostName");
		if (hostNameCfg == null || hostNameCfg.isEmpty()) {
			logger.warn("hostNameCfg is null,not check referer");
			refererOk = true;
		} else {
			String[] hostNames = hostNameCfg.split(",");
			if (referer != null) {
				referer = referer.trim();
				for (String hostName : hostNames) {
					if (referer.startsWith(hostName)) {
						refererOk = true;
						break;
					}
				}
			}
		}
		if (checkToken(requestHttp, response)) {
			tokenOk = true;
		}
		if (refererOk && tokenOk) {
			chain.doFilter(requestHttp, response);
		} else {
			String reqUri = requestHttp.getRequestURI();
			logger.warn("CSRF attack detected.refererOk:{},tokenOk:{},reqUri:{},need reLogin", refererOk, tokenOk,
					reqUri);
			WebUtil.filterForbidDispatch(requestHttp, (HttpServletResponse) response, Constant.STATUS_HACK_CSRF,
					Constant.LOGIN_URL);
		}
	}

	/**
	 * @param requestHttp
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * 校验防御CSRF的token
	 */
	private boolean checkToken(HttpServletRequest requestHttp, ServletResponse response) throws IOException,
			ServletException {
		String reqUri = requestHttp.getRequestURI();
		if (!requestHttp.getMethod().equalsIgnoreCase("POST")) {
			logger.warn("request not POST,not checkToken,reqUri:{}", reqUri);
			return true;
		}
		String CSRFTokenInHead = requestHttp.getHeader(Constant.CSRF_TOKEN_KEY);
		String CSRFTokenInPara = requestHttp.getParameter(Constant.CSRF_TOKEN_KEY);
		if (CsrfTokenManager.chkToken(CSRFTokenInHead) || CsrfTokenManager.chkToken(CSRFTokenInPara)) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
