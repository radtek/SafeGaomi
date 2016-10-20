/**
 * 
 */
package com.hisign.publicsafety.service.impl.cache;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.hisign.publicsafety.common.api.CacheService;

/**
 * 本地模拟缓存
 * @author chailiangzhi
 * @date 2015-9-19
 * 
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {
	private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);
	/**
	 * 本地缓存
	 */
	private Cache<String, Object> cacheLocal = CacheBuilder.newBuilder()
			.maximumSize(2000)
			.expireAfterAccess(1, TimeUnit.HOURS)
			.removalListener(new RemovalListener<String, Object>() {
				@Override
				public void onRemoval(RemovalNotification<String, Object> kv) {
					logger.info("{} was removed from loading cache...", kv.getKey());
				}
			}).build();

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.common.api.CacheService#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean put(String key, Object value) {
		cacheLocal.put(key, value);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.common.api.CacheService#get(java.lang.String)
	 */
	@Override
	public Object get(String key) {
		return cacheLocal.getIfPresent(key);
	}

}
