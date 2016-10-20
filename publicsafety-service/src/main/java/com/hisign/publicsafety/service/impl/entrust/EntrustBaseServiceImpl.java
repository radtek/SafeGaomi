/**
 * 
 */
package com.hisign.publicsafety.service.impl.entrust;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.entity.EntrustBaseInfo;
import com.hisign.publicsafety.mapper.entrust.EntrustBaseMapper;
import com.hisign.publicsafety.service.EntrustBaseService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * @author chailiangzhi
 * @date 2015-9-10
 * 委托基本信息服务实现
 */
@Transactional
@Service("entrustBaseService")
public class EntrustBaseServiceImpl extends GeneralService<EntrustBaseInfo> implements EntrustBaseService {
	private static Logger logger = LoggerFactory.getLogger(EntrustBaseServiceImpl.class);
	@Autowired
	private EntrustBaseMapper entrustBaseMapper;

	/**
	 * 修改委托状态公共方法
	 * @param entrustNo
	 * @param status
	 */
	@Override
	public void updateEntrustStatus(String entrustNo, String status, Date updateDate, int updateUser) throws Exception {
		EntrustBaseInfo entrustBaseStatus = new EntrustBaseInfo();
		entrustBaseStatus.setEntrustNo(entrustNo);
		entrustBaseStatus.setProcessState(status);
		entrustBaseStatus.setUpdateDate(updateDate);
		entrustBaseStatus.setUpdateUser(updateUser);
		update(entrustBaseStatus);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#update(java.lang.Object)
	 */
	@Override
	public void update(EntrustBaseInfo entrustBase) throws Exception {
		entrustBaseMapper.update(entrustBase);
	}

}
