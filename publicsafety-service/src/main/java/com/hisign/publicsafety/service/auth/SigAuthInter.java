package com.hisign.publicsafety.service.auth;

/**
 * @author chailiangzhi
 * @date 2015-9-10
 * 验证码服务
 */
public interface SigAuthInter {

	/**
	 * 得到token
	 * @return token
	 */
	public String getToken();

	/**
	 * 验证是否通过
	 * @param sig
	 * @param sessionId
	 * @param token
	 * @return
	 */
	public boolean validateCode(String sig, String sessionId, String token);
}