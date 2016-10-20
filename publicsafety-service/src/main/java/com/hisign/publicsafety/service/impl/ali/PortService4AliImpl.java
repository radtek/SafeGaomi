/**
 * 
 */
package com.hisign.publicsafety.service.impl.ali;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.AliAccountDetail;
import com.hisign.publicsafety.entity.AliLoginHistory;
import com.hisign.publicsafety.entity.AliRegisterInfo;
import com.hisign.publicsafety.entity.AliTransactionRecords;
import com.hisign.publicsafety.entity.AliTransferDetail;
import com.hisign.publicsafety.entity.EntrustCheckList;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.entity.composite.EntrustFeedBackData;
import com.hisign.publicsafety.mapper.ali.AliAccountMapper;
import com.hisign.publicsafety.mapper.ali.AliLoginMapper;
import com.hisign.publicsafety.mapper.ali.AliRegistMapper;
import com.hisign.publicsafety.mapper.ali.AliTransactionMapper;
import com.hisign.publicsafety.mapper.ali.AliTransferMapper;
import com.hisign.publicsafety.mapper.entrust.EntrustCheckMapper;
import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.service.EntrustBaseService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.service.PortService4Ali;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.service.series.SeriesBasisService;

/**
 * @author chailiangzhi
 * @date 2015-8-12
 * 接口服务for阿里-实现
 */
@Transactional
@Service("portService4Ali")
public class PortService4AliImpl extends GeneralService<EntrustFeedBackData> implements PortService4Ali {

	@Autowired
	private EntrustBaseService entrustBaseService;
	@Autowired
	private AliRegistMapper aliRegistMapper;
	@Autowired
	private AliLoginMapper aliLoginMapper;
	@Autowired
	private AliTransactionMapper aliTransactionMapper;
	@Autowired
	private AliAccountMapper aliAccountMapper;
	@Autowired
	private AliTransferMapper aliTransferMapper;
	@Autowired
	private EntrustCheckMapper entrustCheckMapper;
	@Autowired
	private IdService idService;
	@Autowired
	private EntrustApplyService entrustApplyService;
	@Autowired
	private SeriesBasisService seriesBasisService;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#add(java.lang.Object)
	 */
	@Override
	public void add(EntrustFeedBackData entrustFeedBack) throws Exception {
		String entrustNo = entrustFeedBack.getEntrustNo();
		//修改委托状态为"已收到结果"
		entrustBaseService.updateEntrustStatus(entrustNo, Constant.PROCESS_STATE_RESULT, new Date(), 0);
		//反馈数据入库
		EntrustApplyData entrustApplyData = entrustApplyService.getByEntrustNo(entrustNo);
		List<AliRegisterInfo> aliRegisterInfos = entrustFeedBack.getAliRegistInfos();
		if (aliRegisterInfos != null && !aliRegisterInfos.isEmpty()) {
			aliRegistMapper.addBatch(aliRegisterInfos);
			seriesBasisService.saveBasis(entrustApplyData.getCaseInfo().getId(), aliRegisterInfos);
		}
		List<AliLoginHistory> aliLoginLogs = entrustFeedBack.getAliLoginLogs();
		if (aliLoginLogs != null && !aliLoginLogs.isEmpty()) {
			aliLoginMapper.addBatch(aliLoginLogs);
		}
		List<AliTransactionRecords> tradeLogs = entrustFeedBack.getAliTransactionRecords();
		if (tradeLogs != null && !tradeLogs.isEmpty()) {
			aliTransactionMapper.addBatch(tradeLogs);
		}
		List<AliAccountDetail> accountLogs = entrustFeedBack.getAliAccountLogs();
		if (accountLogs != null && !accountLogs.isEmpty()) {
			aliAccountMapper.addBatch(accountLogs);
		}
		List<AliTransferDetail> trasferLogs = entrustFeedBack.getAliTransferLogs();
		if (trasferLogs != null && !trasferLogs.isEmpty()) {
			aliTransferMapper.addBatch(trasferLogs);
		}
		//记录委托状态变更日志
		EntrustCheckList entrustCheckLog = new EntrustCheckList();
		entrustCheckLog.setId(idService.getId() + "");
		entrustCheckLog.setEntrustNo(entrustNo);
		entrustCheckLog.setCheckKey(Constant.PROCESS_STATE_RESULT);
		entrustCheckLog.setCheckUser(0);
		entrustCheckLog.setCheckDate(new Date());
		entrustCheckLog.setCheckIspass(1);
		entrustCheckMapper.add(entrustCheckLog);

	}

}
