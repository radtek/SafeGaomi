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

import com.hisign.publicsafety.entity.EntrustCaseInfo;
import com.hisign.publicsafety.mapper.entrust.EntrustCaseMapper;
import com.hisign.publicsafety.service.EntrustCaseService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * @author chailiangzhi
 * @date 2015-9-10
 * 委托案件信息服务实现
 */
@Transactional
@Service("entrustCaseService")
public class EntrustCaseServiceImpl extends GeneralService<EntrustCaseInfo> implements EntrustCaseService {
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(EntrustCaseServiceImpl.class);
	@Autowired
	private EntrustCaseMapper entrustCaseMapper;

	/* 修改案件串并状态公共方法
	 * (non-Javadoc)
	 * @see com.hisign.publicsafety.service.EntrustCaseService#updateSeriesStatus(java.lang.String, java.lang.String, java.util.Date, int)
	 */
	@Override
	public void updateSeriesStatus(String id, String isSeries, Date seriesDate, int seriesUser) throws Exception {
		EntrustCaseInfo caseSeriesStatus = new EntrustCaseInfo();
		caseSeriesStatus.setId(id);
		caseSeriesStatus.setIsSeries(isSeries);
		caseSeriesStatus.setSeriesDate(seriesDate);
		caseSeriesStatus.setSeriesUser(seriesUser);
		update(caseSeriesStatus);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#update(java.lang.Object)
	 */
	@Override
	public void update(EntrustCaseInfo entrustCase) throws Exception {
		logger.debug(entrustCase.getIsSeries());
		entrustCaseMapper.update(entrustCase);
	}

	/** 
	 * 根据委托案件ID查询案件信息
	 */
	@Override
	public EntrustCaseInfo getById(String id) {
		return entrustCaseMapper.getById(id);
	}

}
