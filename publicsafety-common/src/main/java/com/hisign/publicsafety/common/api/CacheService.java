/**
 * 
 */
package com.hisign.publicsafety.common.api;

/**
 * 缓存访问接口
 * @author chailiangzhi
 * @date 2015-9-19
 * 
 */
public interface CacheService {
	/**
	 * 存放到缓存
	 * @param key
	 * @param value
	 * @return 
	 */
	public boolean put(String key, Object value);

	/**
	 * 从缓存取数据
	 * @param key
	 * @return 
	 */
	public Object get(String key);
}
