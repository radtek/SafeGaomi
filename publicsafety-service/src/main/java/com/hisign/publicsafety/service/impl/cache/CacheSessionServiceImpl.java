/**
 * 
 */
package com.hisign.publicsafety.service.impl.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisign.publicsafety.common.api.CacheService;
import com.hisign.publicsafety.common.api.CacheSessionService;
import com.hisign.publicsafety.util.WebContext;

/**
 * 通过缓存方式存放会话信息的实现
 * @author chailiangzhi
 * @date 2015-9-19
 * 
 */
@Service("cacheSessionService")
public class CacheSessionServiceImpl implements CacheSessionService {
	private static final Logger logger = LoggerFactory.getLogger(CacheSessionServiceImpl.class);

	@Autowired
	private CacheService cacheService;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.common.api.CacheSessionService#getId()
	 */
	@Override
	public String getId() {
		return WebContext.getSessionId();
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.common.api.CacheSessionService#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String name) {
		if (getId() == null) {
			logger.error("mSessionId is null, can't getAttribute, name={}", name);
			return null;
		}
		Map<String, Object> sessionMap = (Map<String, Object>) cacheService.get(getId());
		if (sessionMap == null) {
			logger.warn("sessionMap is null,create new:{}", getId());
			return null;
		}
		return sessionMap.get(name);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.common.api.CacheSessionService#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String name, Object value) {
		if (getId() == null) {
			logger.error("mSessionId is null, can not setAttribute, name={}", name);
			return;
		}
		Map<String, Object> sessionMap = (Map<String, Object>) cacheService.get(getId());
		if (sessionMap == null) {
			sessionMap = new HashMap<String, Object>();
			cacheService.put(getId(), sessionMap);
		}
		sessionMap.put(name, value);
	}
}
