/**
 * 
 */
package com.hisign.publicsafety.service.impl.ali;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dio2o.domain.request.CaseInfo;
import com.alibaba.dio2o.domain.request.EntrustInfo;
import com.alibaba.dio2o.domain.request.InvestigateInfo;
import com.alibaba.dio2o.domain.request.RequestData;
import com.alibaba.dio2o.domain.request.RequestEntity;
import com.alibaba.dio2o.domain.request.SuspectInfo;
import com.alibaba.dio2o.domain.request.VictimInfo;
import com.alibaba.dio2o.domain.response.AccountLog;
import com.alibaba.dio2o.domain.response.LoginLog;
import com.alibaba.dio2o.domain.response.RegistInfo;
import com.alibaba.dio2o.domain.response.ResponseData;
import com.alibaba.dio2o.domain.response.ResponseEntity;
import com.alibaba.dio2o.domain.response.TradeLog;
import com.alibaba.dio2o.domain.response.TransferLog;
import com.alibaba.dio2o.result.Dio2oResult;
import com.alibaba.dio2o.service.Dio2oService;
import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.AliAccountDetail;
import com.hisign.publicsafety.entity.AliLoginHistory;
import com.hisign.publicsafety.entity.AliRegisterInfo;
import com.hisign.publicsafety.entity.AliTransactionRecords;
import com.hisign.publicsafety.entity.AliTransferDetail;
import com.hisign.publicsafety.entity.EntrusInvestigationInfo;
import com.hisign.publicsafety.entity.EntrusInvestigationTypeinfo;
import com.hisign.publicsafety.entity.EntrustSuspectInfo;
import com.hisign.publicsafety.entity.EntrustVictimInfo;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.entity.composite.EntrustFeedBackData;
import com.hisign.publicsafety.service.AliPortService;
import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.service.PortService4Ali;
import com.hisign.publicsafety.util.DateUtils;
import com.hisign.publicsafety.util.DateUtils.DateFormat;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.util.PropertiesUtils;

/**
 * @author chailiangzhi
 * @date 2015-8-21
 * 阿里接口服务实现
 */
@Service("aliPortService")
public class AliPortServiceImpl implements AliPortService {
	private static Logger logger = LoggerFactory.getLogger(AliPortServiceImpl.class);

	@Autowired
	private EntrustApplyService entrustApplyService;
	@Autowired
	private IdService idService;
	@Autowired
	private org.dozer.Mapper mapper;
	@Autowired
	private Dio2oService dio2oService;
	@Autowired
	private PortService4Ali portService4Ali;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.AliPortService#callAli(java.lang.String)
	 */
	@Override
	public void callAli(String entrustNo) throws Exception {
		EntrustApplyData entrustApplyData = entrustApplyService.getByEntrustNo(entrustNo);
		RequestEntity req = new RequestEntity();
		Date reqDate = new Date();
		String reqTime = DateUtils.parse2String(reqDate, DateFormat.YMDHMS_SHORT);
		String reqSn = Constant.SYS_CODE_ALIB + reqTime + idService.getApiSn();
		req.setReqSn(reqSn);
		req.setSysCode(Constant.SYS_CODE_ALIB);
		req.setApiName(Constant.API_NAME_SUBMIT);
		req.setReqTime(reqDate);
		RequestData reqData = new RequestData();
		req.setData(reqData);
		reqData.setEntrustNo(entrustNo);
		//委托基本信息
		EntrustInfo entrustInfoToAli = new EntrustInfo();
		mapper.map(entrustApplyData.getEntrustInfo(), entrustInfoToAli);
		reqData.setEntrustInfo(entrustInfoToAli);
		//查询类型信息
		StringBuilder sb = new StringBuilder();
		for (EntrusInvestigationInfo queryInfo : entrustApplyData.getQueryInfo()) {
			sb.append(queryInfo.getQueryType());
			sb.append(",");
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		entrustInfoToAli.setQueryType(sb.toString());
		//案件信息
		CaseInfo caseInfoToAli = new CaseInfo();
		mapper.map(entrustApplyData.getCaseInfo(), caseInfoToAli);
		reqData.setCaseInfo(caseInfoToAli);
		//嫌疑人信息
		List<SuspectInfo> suspectInfoToAlis = new ArrayList<SuspectInfo>();
		for (EntrustSuspectInfo entrustSuspectInfo : entrustApplyData.getSuspectInfo()) {
			SuspectInfo suspectInfoToAli = new SuspectInfo();
			mapper.map(entrustSuspectInfo, suspectInfoToAli);
			suspectInfoToAlis.add(suspectInfoToAli);
		}
		caseInfoToAli.setSuspectInfo(suspectInfoToAlis);
		//受害人信息
		List<VictimInfo> victimInfoToAlis = new ArrayList<VictimInfo>();
		for (EntrustVictimInfo entrustVictimInfo : entrustApplyData.getVictimInfo()) {
			VictimInfo victimInfoToAli = new VictimInfo();
			mapper.map(entrustVictimInfo, victimInfoToAli);
			victimInfoToAlis.add(victimInfoToAli);
		}
		caseInfoToAli.setVictimInfo(victimInfoToAlis);
		//调查依据信息
		List<InvestigateInfo> investigateInfoToAlis = new ArrayList<InvestigateInfo>();
		for (EntrusInvestigationTypeinfo investigateInfo : entrustApplyData.getInvestigateInfo()) {
			InvestigateInfo investigateInfoToAli = new InvestigateInfo();
			mapper.map(investigateInfo, investigateInfoToAli);
			investigateInfoToAlis.add(investigateInfoToAli);
		}
		reqData.setInvestigateInfo(investigateInfoToAlis);
		//
		String callAli = PropertiesUtils.findPropertiesKey("callAli");
		if (!"true".equals(callAli)) {
			logger.info("callAli not true,ret");
			return;
		}
		Dio2oResult<ResponseEntity> result = dio2oService.queryInformation(req);
		ResponseEntity responseEntity = result.getData();
		responseEntity.getData().setEntrustNo(entrustNo);
		processRes(responseEntity);
	}

	private void processRes(ResponseEntity responseEntity) {//
		try {
			EntrustFeedBackData entrustFeedBack = new EntrustFeedBackData();
			ResponseData dataAli = responseEntity.getData();
			String entrustNo = dataAli.getEntrustNo();
			//注册信息
			List<AliRegisterInfo> aliRegistInfos = new ArrayList<AliRegisterInfo>();
			List<RegistInfo> regist_infos = dataAli.getRegistInfo();
			for (RegistInfo regist : regist_infos) {
				AliRegisterInfo aliRegisterInfo = new AliRegisterInfo();
				aliRegisterInfo.setId(idService.getId() + "");
				aliRegisterInfo.setEntrustNo(entrustNo);
				mapper.map(regist, aliRegisterInfo);
				aliRegistInfos.add(aliRegisterInfo);
			}
			entrustFeedBack.setAliRegistInfos(aliRegistInfos);
			//登录日志
			List<AliLoginHistory> aliLoginHistorys = new ArrayList<AliLoginHistory>();
			List<LoginLog> login_logs = dataAli.getLoginLog();
			for (LoginLog login : login_logs) {
				AliLoginHistory aliLoginHistory = new AliLoginHistory();
				aliLoginHistory.setId(idService.getId() + "");
				aliLoginHistory.setEntrustNo(entrustNo);
				mapper.map(login, aliLoginHistory);
				aliLoginHistorys.add(aliLoginHistory);
			}
			entrustFeedBack.setAliLoginLogs(aliLoginHistorys);
			//交易记录
			List<AliTransactionRecords> aliTransactionRecords = new ArrayList<AliTransactionRecords>();
			List<TradeLog> trade_logs = dataAli.getTradeLog();
			if (trade_logs != null) {
				for (TradeLog trade : trade_logs) {
					AliTransactionRecords aliTransactionRecord = new AliTransactionRecords();
					aliTransactionRecord.setId(idService.getId() + "");
					aliTransactionRecord.setEntrustNo(entrustNo);
					mapper.map(trade, aliTransactionRecord);
					aliTransactionRecords.add(aliTransactionRecord);
				}
				entrustFeedBack.setAliTransactionRecords(aliTransactionRecords);
			}
			//账户明细
			List<AliAccountDetail> aliAccountDetails = new ArrayList<AliAccountDetail>();
			List<AccountLog> account_logs = dataAli.getAccountLog();
			if (account_logs != null) {
				for (AccountLog account : account_logs) {
					AliAccountDetail aliAccountDetail = new AliAccountDetail();
					aliAccountDetail.setId(idService.getId() + "");
					aliAccountDetail.setEntrustNo(entrustNo);
					mapper.map(account, aliAccountDetail);
					aliAccountDetails.add(aliAccountDetail);
				}
				entrustFeedBack.setAliAccountLogs(aliAccountDetails);
			}
			//转账明细
			List<AliTransferDetail> aliTransferDetails = new ArrayList<AliTransferDetail>();
			List<TransferLog> transfer_logs = dataAli.getTransferLog();
			if (transfer_logs != null) {
				for (TransferLog transfer : transfer_logs) {
					AliTransferDetail aliTransferDetail = new AliTransferDetail();
					aliTransferDetail.setId(idService.getId() + "");
					aliTransferDetail.setEntrustNo(entrustNo);
					mapper.map(transfer, aliTransferDetail);
					aliTransferDetails.add(aliTransferDetail);
				}
				entrustFeedBack.setAliTransferLogs(aliTransferDetails);
			}
			entrustFeedBack.setEntrustNo(entrustNo);
			portService4Ali.add(entrustFeedBack);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
		}
	}

}
