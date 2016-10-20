package com.hisign.publicsafety.service.impl.ccp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.entity.ccp.CheckApk;
import com.hisign.publicsafety.mapper.ccp.CheckApkInfoMapper;
import com.hisign.publicsafety.service.ccp.CheckApkService;

/**
 * 版本更新服务接口实现类
 * @author wangk
 *
 */
@Transactional
@Service("checkApkService")
public class CheckApkServiceImpl  implements CheckApkService {

	@Autowired
	private CheckApkInfoMapper checkApkInfoMapper;
	
	
	public CheckApk findByApk() {
		return checkApkInfoMapper.findByApk();
	}
	
}
