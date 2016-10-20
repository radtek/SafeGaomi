package com.hisign.publicsafety.web.beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.publicsafety.common.bean.cookie.UserInfo;
import com.hisign.publicsafety.util.EncodeUtil;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.util.WebContext;

/**
 * A manager for the CSRF token for a given session. The
 * {@link #getToken()} should used to obtain the token
 * value for the current session (and this should be the only way to obtain the
 * token value).
 * 
 * add by zhao 2015-09-06
 **/
public class CsrfTokenManager {

	private static final Logger logger = LoggerFactory.getLogger(CsrfTokenManager.class);

	/**
	 * The location on the session which stores the token
	 */
	public static final String CSRF_TOKEN_ATTR = "CSRFTokenManager.tokenval";

	/**
	 * 根据用户账号ID和最后访问时间生成CSRF的Token
	 * @return
	 */
	public static String getToken() {
		UserInfo userInfo = WebContext.getUserInfo();
		String token = getToken(userInfo.getAccountId(), userInfo.getLastAccessTime());
		return token;
	}

	/**
	 * 校验页面传来的Token是否由服务端算法生成
	 * @param webToken
	 * @return
	 */
	public static boolean chkToken(String webToken) {
		if (webToken == null || webToken.length() <= 32) {
			logger.debug("webToken:{},is invalid", webToken);
			return false;
		}
		// 页面上srcId的16进制字符串
		String srcIdHexStrWeb = webToken.substring(32);
		UserInfo userInfo = WebContext.getUserInfo();
		// srcId=用户ID和最后访问时间做算术相加
		long lastAccessTimeOld = Long.decode("0x" + srcIdHexStrWeb) - userInfo.getAccountId();
		String serverToken = getToken(userInfo.getAccountId(), lastAccessTimeOld);
		boolean result = serverToken.equals(webToken);
		return result;
	}

	/**
	 * 生成Token的算法
	 * @param userId
	 * @param lastAccessTime
	 * @return
	 */
	private static String getToken(int userId, long lastAccessTime) {
		// 用户ID和最后访问时间做算术相加得srcId
		long srcId = lastAccessTime + userId;
		String srcIdStr = String.valueOf(srcId);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			String md5Str = EncodeUtil.bytesToHexString(md.digest(srcIdStr.getBytes()));
			String srcIdHexStr = Long.toHexString(srcId);
			// srcId的MD5值和srcId的16进制字符串拼接
			String token = new StringBuilder(md5Str).append(srcIdHexStr).toString();
			return token;
		} catch (NoSuchAlgorithmException e) {
			LogUtil.errStack2Log4j(e);
			return srcIdStr;
		}
	}

	private CsrfTokenManager() {
	}

}
