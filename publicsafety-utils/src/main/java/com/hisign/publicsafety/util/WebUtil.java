/**
 * 
 */
package com.hisign.publicsafety.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.SpringHolder;

/**
 * Web层公共函数
 * @author chailiangzhi
 *
 */
public class WebUtil {

	/**
	 * 获取请求URL中的sys_check_page参数
	 * @param requestHttp
	 * @return
	 */
	public static String getSysCheckPagePara(HttpServletRequest requestHttp) {
		String sysCheckPageName = requestHttp.getParameter("sys_check_page");
		if (sysCheckPageName != null && sysCheckPageName.length() > 0) {
			return sysCheckPageName;
		} else {
			return null;
		}
	}

	/**
	 * 根据请求URL判断在会话超时的时候是否需要转发
	 * @param requestHttp
	 * @return
	 */
	public static boolean needForwardOnTimeOut(HttpServletRequest requestHttp) {
		String reqUri = requestHttp.getRequestURI();
		if (reqUri.indexOf(Constant.LOGIN_URL) > -1 || reqUri.indexOf(Constant.SUBMIT_LOGIN_URL) > -1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 过滤器中禁止访问时的转发方式
	 * @param request
	 * @param response
	 * @param statusCode AJAX请求返回的状态码
	 * @param forwardUri 页面请求时跳转的页面链接
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void filterForbidDispatch(HttpServletRequest request, HttpServletResponse response, int statusCode,
			String forwardUri) throws ServletException, IOException {
		if (Constant.AJAX_REQ_TAG_VALUE.equals(request.getHeader(Constant.AJAX_REQ_TAG_KEY))) {
			//对于ajax请求不重定向  而是返回错误代码
			response.sendError(statusCode, "Access Denied");
		} else {
			request.getRequestDispatcher(forwardUri).forward(request, response);
		}
	}

	/**
	 * 保存授权列表到缓存
	 * @param securityContext
	 */
	public static void saveAuthorityStrs(SecurityContext securityContext) {
		// 取出授权列表
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContext.getAuthentication()
				.getAuthorities();
		List<String> authorityStrs = new ArrayList<String>();
		for (GrantedAuthority grantedAuthority : authorities) {
			authorityStrs.add(grantedAuthority.getAuthority());
		}
		// 授权列表放入缓存
		SpringHolder.CACHE_SESSION_SERVICE.setAttribute(Constant.AUTHORITY_STRS_KEY, authorityStrs);
	}
}
