/**
 * 
 */
package com.hisign.publicsafety.web.tool;

import org.springframework.util.Assert;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.SpringHolder;
import com.hisign.publicsafety.common.bean.cookie.UserInfo;
import com.hisign.publicsafety.util.CookieUtils;
import com.hisign.publicsafety.util.WebContext;

/**
 * @author chailiangzhi
 * 存取Cookie中会话ID和用户信息的工具类
 */
public class O2oCookieHelper {

	/**
	 * 获取Cookie中的自定义会话ID
	 * @return
	 */
	public static String getSessionId() {
		String sessionId = CookieUtils.getCookieValue(WebContext.getRequest(), Constant.J_SESSION_ID_NAME);
		WebContext.setSessionId(sessionId);
		return sessionId;
	}

	/**
	 * 设置Cookie中的自定义会话ID
	 * @param sessionId
	 */
	public static void setSessionId(String sessionId) {
		WebContext.setSessionId(sessionId);
		CookieUtils.setCookie(WebContext.getRequest(), WebContext.getResponse(), Constant.J_SESSION_ID_NAME, sessionId,
				Constant.COOKIE_MAX_AGE, Constant.COOKIE_PATH);
	}

	/**
	 * 获取Cookie中的用户信息
	 * @return
	 */
	public static UserInfo getUserInfo() throws Exception {
		String userInfoStr = CookieUtils.getCookieValue(WebContext.getRequest(), Constant.USER_INFO_NAME);
		UserInfo userInfo;
		if (userInfoStr == null || userInfoStr.isEmpty()) {
			//如果Cookie中无用户信息，创建空的
			userInfo = new UserInfo();
			userInfo.setAccountId(-2);
			userInfo.setLogin(false);
			userInfo.setLastAccessTime(System.currentTimeMillis());
		} else {
			userInfo = SpringHolder.OBJECT_TO_STRING.unserial(userInfoStr, UserInfo.class);
		}
		WebContext.setUserInfo(userInfo);
		return userInfo;
	}

	/**
	 * 设置Cookie中的用户信息
	 * @param sessionId
	 */
	public static void setUserInfo(UserInfo userInfo) throws Exception {
		Assert.notNull(userInfo, "userInfo to Cookie can't be null");
		WebContext.setUserInfo(userInfo);
		String userInfoStr = SpringHolder.OBJECT_TO_STRING.serial(userInfo);
		CookieUtils.setCookie(WebContext.getRequest(), WebContext.getResponse(), Constant.USER_INFO_NAME, userInfoStr,
				Constant.COOKIE_MAX_AGE, Constant.COOKIE_PATH);
	}

	/**
	 * 清除Cookie中的用户信息
	 * @param sessionId
	 */
	public static void clearUserInfo() {
		CookieUtils.setCookie(WebContext.getRequest(), WebContext.getResponse(), Constant.USER_INFO_NAME, "", 0,
				Constant.COOKIE_PATH);
	}
}
