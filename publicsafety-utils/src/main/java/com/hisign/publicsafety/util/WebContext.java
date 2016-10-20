/**
 * 
 */
package com.hisign.publicsafety.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hisign.publicsafety.common.bean.cookie.UserInfo;

/**
 * 存放Web应用的request,response
 * Web上下文的工具类
 * @author chailiangzhi
 * @date 2015-9-19
 * 
 */
public class WebContext {
	/**
	 * request单例,采用线程局部变量解决并发问题
	 */
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	/**
	 * response单例,采用线程局部变量解决并发问题
	 */
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
	/**
	 * 自定义会话ID
	 * 存放mSessionId需要线程局部变量
	 * 解决并发问题
	 */
	private static ThreadLocal<String> mSessionIdLocal = new ThreadLocal<String>();
	/**
	 * 浏览器保存的用户信息
	 * 需要线程局部变量
	 * 解决并发问题
	 */
	private static ThreadLocal<UserInfo> userInfoLocal = new ThreadLocal<UserInfo>();

	/**
	 * @return the request
	 */
	public static HttpServletRequest getRequest() {
		return requestLocal.get();
	}

	/**
	 * @param request the request to set
	 */
	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}

	/**
	 * @return the response
	 */
	public static HttpServletResponse getResponse() {
		return responseLocal.get();
	}

	/**
	 * @param response the response to set
	 */
	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}

	/**
	 * 获取自定义会话ID
	 * @return
	 */
	public static String getSessionId() {
		return mSessionIdLocal.get();
	}

	/**
	 * 设置自定义会话ID
	 * @param sessionId
	 */
	public static void setSessionId(String sessionId) {
		mSessionIdLocal.set(sessionId);
	}

	/**
	 * 获取浏览器保存的用户信息
	 * @return
	 */
	public static UserInfo getUserInfo() {
		return userInfoLocal.get();
	}

	/**
	 * 
	 * @param userInfo 浏览器保存的用户信息
	 */
	public static void setUserInfo(UserInfo userInfo) {
		userInfoLocal.set(userInfo);
	}
}
