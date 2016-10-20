/**
 * 
 */
package com.hisign.publicsafety.service.impl.auth;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hisign.publicsafety.service.auth.SigAuthInter;

/**
 * @author chailiangzhi
 * @date 2015-9-9
 * 验证码服务实现
 */

@Service("sigAuth")
public class SigAuthImpl implements SigAuthInter {

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.auth.SigAuthInter#getSessionId()
	 */
	@Override
	public String getToken() {
		return UUID.randomUUID().toString();
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.auth.SigAuthInter#validateCode(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateCode(String sig, String sessionId, String token) {
		return true;
	}

}
