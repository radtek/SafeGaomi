package com.hisign.publicsafety.mapper.ccp;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.ccp.PhoneLicense;

/**
 * 手机授权mapper
 * @author hotdog
 * 2015-11-24
 */
public interface PhoneLicenseMapper extends BaseMapper<PhoneLicense> {
	
	public PhoneLicense findByIMEI(String IMEI); 
}
