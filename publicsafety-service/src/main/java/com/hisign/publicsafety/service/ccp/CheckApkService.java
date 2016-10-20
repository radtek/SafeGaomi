package com.hisign.publicsafety.service.ccp;

import com.hisign.publicsafety.entity.ccp.CheckApk;


/**
 * 版本更新服务接口
 * @author wangk
 *
 */
public interface CheckApkService {
	
	/**
	 * 版本更新
	 * @param apkno 版本号
	 * @return
	 */
	public CheckApk findByApk();
	
	
}
