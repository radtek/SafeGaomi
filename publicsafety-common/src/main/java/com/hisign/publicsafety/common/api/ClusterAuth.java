/**
 * 
 */
package com.hisign.publicsafety.common.api;

import com.hisign.publicsafety.common.bean.cookie.UserInfo;

/**
 * 集群下的认证接口
 * @author chailiangzhi
 * 
 */
public interface ClusterAuth {
	/**
	 * 集群下通过Cookie进行认证
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	public boolean authByCookie(UserInfo userInfo) throws Exception;
}
