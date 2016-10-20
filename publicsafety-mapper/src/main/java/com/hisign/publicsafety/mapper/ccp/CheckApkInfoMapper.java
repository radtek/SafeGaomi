package com.hisign.publicsafety.mapper.ccp;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.ccp.CheckApk;


/**
 * 版本更新Mapper
 * @author wangk
 * 2016年9月13日19:07:20
 */
public interface CheckApkInfoMapper extends BaseMapper<CheckApk>{
	
	/**
	 * 版本更新
	 * @param apkno
	 * @return
	 */
	public CheckApk findByApk();
	
}
