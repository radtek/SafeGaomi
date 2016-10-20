package com.hisign.publicsafety.service.impl.ccp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.entity.ccp.PhoneLicense;
import com.hisign.publicsafety.mapper.ccp.PhoneLicenseMapper;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.service.ccp.PhoneLicenseService;

/**
 * 授权服务实现类
 * @author hotdog
 *
 */
@Transactional
@Service("phoneLicenseService")
public class PhoneLicenseServiceImpl extends GeneralService<PhoneLicense> implements PhoneLicenseService {

	@Autowired
	private PhoneLicenseMapper phoneLicenseMapper;
	
	public void update(PhoneLicense t) throws Exception {
		phoneLicenseMapper.update(t);
	}

	public void add(PhoneLicense t) throws Exception {
		phoneLicenseMapper.add(t);
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.ccp.PhoneLicenseService#findByIMEI(java.lang.String)
	 */
	public PhoneLicense findByIMEI (String IMEI) throws Exception {
		return phoneLicenseMapper.findByIMEI(IMEI);
	}
}
