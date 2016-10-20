package com.hisign.publicsafety.service.ccp;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.ccp.PhoneLicense;

/**
 * 线索征集平台-手机授权服务接口
 * @author hotdog
 *
 */
public interface PhoneLicenseService extends BaseService<PhoneLicense>{

	/**
	 * 根据序列号查询授权记录
	 * @param serialNum
	 * @return
	 */
	public PhoneLicense findByIMEI (String IMEI) throws Exception;
	
}
