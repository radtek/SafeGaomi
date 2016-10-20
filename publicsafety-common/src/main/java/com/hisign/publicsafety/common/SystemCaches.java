package com.hisign.publicsafety.common;

import com.google.common.cache.Cache;

/**
 * 
 * 系统全局缓存
 * 2015-4-13 下午01:28:25
 * 
 *
 */
public class SystemCaches {

	/**
	 * 管辖所单位缓存
	 */
	private Cache<String, Object> unitCache;

	/**
	 * @param maxNum
	 * @param expire
	 */
	public SystemCaches(int maxNum, int expire) {
		this.unitCache = GuavaCache.callableCached(maxNum, expire);
	}

	/**
	 * @return the unitCache
	 */
	public Cache<String, Object> getUnitCache() {
		return unitCache;
	}

	/**
	 * @param unitCache the unitCache to set
	 */
	public void setUnitCache(Cache<String, Object> unitCache) {
		this.unitCache = unitCache;
	}

}
