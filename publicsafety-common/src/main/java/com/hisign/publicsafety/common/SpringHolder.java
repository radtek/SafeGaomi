/**
 * 
 */
package com.hisign.publicsafety.common;

import org.springframework.web.context.ContextLoader;

import com.hisign.publicsafety.common.api.CacheSessionService;
import com.hisign.publicsafety.common.api.ClusterAuth;
import com.hisign.publicsafety.common.api.ObjectToString;

/**
 * 持有Spring容器内的bean引用，方便容器外访问
 * 
 * @author chailiangzhi
 * @date 2015-9-18
 * 
 */
public class SpringHolder {
	/**
	 * 通过缓存方式替代session的bean实例引用
	 */
	public static final CacheSessionService CACHE_SESSION_SERVICE = ContextLoader.getCurrentWebApplicationContext()
			.getBean(CacheSessionService.class);
	/**
	 * 往cookie存取值时，对象和字符串转换的bean实例引用
	 */
	public static final ObjectToString OBJECT_TO_STRING = ContextLoader.getCurrentWebApplicationContext().getBean(
			ObjectToString.class);
	/**
	 * 集群下认证的bean实例引用
	 */
	public static final ClusterAuth CLUSTER_AUTH = ContextLoader.getCurrentWebApplicationContext().getBean(
			ClusterAuth.class);
}
