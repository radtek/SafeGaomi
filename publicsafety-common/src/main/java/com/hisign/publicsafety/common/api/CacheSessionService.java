/**
 * 
 */
package com.hisign.publicsafety.common.api;

/**
 * 通过缓存方式存放会话信息的接口
 * @author chailiangzhi
 * @date 2015-9-19
 * 
 */
public interface CacheSessionService {
	/**
	 * 获取自定义会话Id
	 * @return
	 */
	public String getId();

	/**
	 * 获取属性
	 * @param name
	 * @return
	 */
	public Object getAttribute(String name);

	/**
	 * 设置属性
	 * @param name
	 * @param value
	 */
	public void setAttribute(String name, Object value);
}
