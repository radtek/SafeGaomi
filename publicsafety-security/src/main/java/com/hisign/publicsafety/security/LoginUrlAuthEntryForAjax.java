package com.hisign.publicsafety.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.hisign.publicsafety.common.Constant;

/**
 * 未登录(会话超时)的切入点
 * session超时拦截包括ajax的所有请求，重定向至登录页面
 * @author hotdog
 * 下午5:55:21
 */
public class LoginUrlAuthEntryForAjax extends LoginUrlAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (Constant.AJAX_REQ_TAG_VALUE.equals(httpRequest.getHeader(Constant.AJAX_REQ_TAG_KEY))) {
			//对于ajax请求不重定向  而是返回错误代码
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
		} else
			super.commence(request, response, authException);
	}
}
