/**
 * 
 */
package com.hisign.publicsafety.common.bean.cookie;

import java.io.Serializable;

/**
 * 存储在cookie中的用户信息
 * @author chailiangzhi
 * 
 */
public class UserInfo implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 账户ID，默认-1，代表匿名用户
	 */
	private int accountId = -1;

	/**
	 * 是否登录，默认否
	 */
	private boolean isLogin = false;

	/**
	 * 最后访问时间，每次请求时更新，格式System.currentTimeMillis()
	 */
	private long lastAccessTime = 1;

	/**
	 * @return the accountId
	 */
	public int getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the isLogin
	 */
	public boolean isLogin() {
		return isLogin;
	}

	/**
	 * @param isLogin the isLogin to set
	 */
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	/**
	 * @return the lastAccessTime
	 */
	public long getLastAccessTime() {
		return lastAccessTime;
	}

	/**
	 * @param lastAccessTime the lastAccessTime to set
	 */
	public void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

}
