/**
 * 
 */
package com.hisign.publicsafety.service.impl.entrust;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.gson.StringConverter;
import com.hisign.publicsafety.entity.EntrusAttachment;
import com.hisign.publicsafety.entity.EntrusInvestigationInfo;
import com.hisign.publicsafety.entity.EntrusInvestigationTypeinfo;
import com.hisign.publicsafety.entity.EntrustBaseInfo;
import com.hisign.publicsafety.entity.EntrustCaseInfo;
import com.hisign.publicsafety.entity.EntrustCheckList;
import com.hisign.publicsafety.entity.EntrustSuspectInfo;
import com.hisign.publicsafety.entity.EntrustVictimInfo;
import com.hisign.publicsafety.entity.Log;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.mapper.entrust.EntrustAttachMapper;
import com.hisign.publicsafety.mapper.entrust.EntrustBaseMapper;
import com.hisign.publicsafety.mapper.entrust.EntrustCaseMapper;
import com.hisign.publicsafety.mapper.entrust.EntrustCheckMapper;
import com.hisign.publicsafety.mapper.entrust.EntrustInvestigateMapper;
import com.hisign.publicsafety.mapper.entrust.EntrustQueryMapper;
import com.hisign.publicsafety.mapper.entrust.EntrustSuspectMapper;
import com.hisign.publicsafety.mapper.entrust.EntrustVictimMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.service.EntrustListService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.util.DateUtils;

/**
 * @author chailiangzhi
 * @date 2015-8-10
 * 委托申请服务实现
 */
@Transactional
@Service("entrustApplyService")
public class EntrustApplyServiceImpl extends GeneralService<EntrustApplyData> implements EntrustApplyService {
	private static Logger logger = LoggerFactory.getLogger(EntrustApplyServiceImpl.class);
	@Autowired
	private EntrustBaseMapper entrustBaseMapper;
	@Autowired
	private EntrustCaseMapper entrustCaseMapper;
	@Autowired
	private EntrustVictimMapper entrustVictimMapper;
	@Autowired
	private EntrustSuspectMapper entrustSuspectMapper;
	@Autowired
	private EntrustInvestigateMapper entrustInvestigateMapper;
	@Autowired
	private EntrustQueryMapper entrustQueryMapper;
	@Autowired
	private EntrustAttachMapper entrustAttachMapper;
	@Autowired
	private EntrustCheckMapper entrustCheckMapper;

	@Autowired
	private IdService idService;
	@Autowired
	private EntrustListService entrustListService;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.EntrustApplyService#save(com.hisign.publicsafety.entity.composite.EntrustApplyData)
	 */
	@Override
	public boolean save(EntrustApplyData entrustApplyData) throws Exception {
		EntrustBaseInfo entrustBase = entrustApplyData.getEntrustInfo();
		EntrustCaseInfo caseInfo = entrustApplyData.getCaseInfo();
		List<EntrustVictimInfo> victimInfos = entrustApplyData.getVictimInfo();
		List<EntrustSuspectInfo> suspectInfos = entrustApplyData.getSuspectInfo();
		List<EntrusInvestigationTypeinfo> investigateInfos = entrustApplyData.getInvestigateInfo();
		List<EntrusInvestigationInfo> queryInfos = entrustApplyData.getQueryInfo();
		List<EntrusAttachment> attachments = entrustApplyData.getAttachments();

		boolean isUpdate = false;
		boolean isSubmit = false;
		if (Constant.PROCESS_STATE_SUBMIT.equals(entrustBase.getProcessState())) {
			isSubmit = true;
		}
		logger.debug("isSubmit=" + isSubmit);
		String entrustNo = entrustBase.getEntrustNo();
		//保存委托基本信息
		EntrustBaseInfo entrustBaseOld = entrustBaseMapper.getById(entrustNo);
		if (entrustBaseOld != null) {
			isUpdate = true;
			entrustBaseMapper.update(entrustBase);
		} else {
			entrustBaseMapper.add(entrustBase);
		}
		logger.debug("isUpdate=" + isUpdate);
		//保存附件文书信息
		entrustAttachMapper.delete(entrustNo);
		if (attachments != null && attachments.size() > 0) {
			entrustAttachMapper.addBatch(attachments);
		}
		//保存案件信息
		try {
			Integer.parseInt(caseInfo.getCaseMoneyAmout());
		} catch (NumberFormatException e) {
			caseInfo.setCaseMoneyAmout("0");
		}
		EntrustCaseInfo caseInfoOld = entrustCaseMapper.getByEntrustNo(entrustNo);
		if (caseInfoOld != null) {
			entrustCaseMapper.update(caseInfo);
		} else {
			entrustCaseMapper.add(caseInfo);
		}
		//保存受害人信息
		entrustVictimMapper.delete(entrustNo);
		if (victimInfos != null && victimInfos.size() > 0) {
			entrustVictimMapper.addBatch(victimInfos);
		}
		//保存嫌疑人信息
		entrustSuspectMapper.delete(entrustNo);
		if (suspectInfos != null && suspectInfos.size() > 0) {
			entrustSuspectMapper.addBatch(suspectInfos);
		}
		//保存调查信息(公安给阿里提供的字段)
		entrustInvestigateMapper.delete(entrustNo);
		if (investigateInfos != null && investigateInfos.size() > 0) {
			entrustInvestigateMapper.addBatch(investigateInfos);
		}
		//保存查询类型信息(阿里给公安返回的内容类型)
		entrustQueryMapper.delete(entrustNo);
		if (queryInfos != null && queryInfos.size() > 0) {
			entrustQueryMapper.addBatch(queryInfos);
		}
		//记录委托状态变更日志
		EntrustCheckList entrustCheckLog = new EntrustCheckList();
		entrustCheckLog.setId(idService.getId() + "");
		entrustCheckLog.setEntrustNo(entrustNo);
		entrustCheckLog.setCheckKey(entrustBase.getProcessState());
		int checkUser = entrustBase.getCreateUser();
		int updateUser = entrustBase.getUpdateUser();
		if (updateUser != 0) {
			checkUser = updateUser;
		}
		entrustCheckLog.setCheckUser(checkUser);
		entrustCheckLog.setCheckDate(new Date());
		entrustCheckLog.setCheckIspass(0);
		entrustCheckMapper.add(entrustCheckLog);
		//业务日志
		Log log = new Log();
		StringBuilder moduleActionSb = new StringBuilder();
		moduleActionSb.append("委托申请");
		if (isUpdate) {
			moduleActionSb.append("-修改");
		} else {
			moduleActionSb.append("-新增");
		}
		if (isSubmit) {
			moduleActionSb.append("-提交");
		} else {
			moduleActionSb.append("-暂存");
		}
		log.setModule(moduleActionSb.toString());
		log.setRev1(entrustNo);
		putLog("save", log);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.EntrustApplyService#query(java.util.Map)
	 */
	@Override
	public DataResponse<EntrustApplyData> query(PageView pageView, Map<String, Object> paraMap) {
		//多条件分页查询委托
		PageView entrustListPageView = entrustListService.query(pageView, paraMap);//
		//业务日志
		Log log = new Log();
		StringBuilder moduleActionSb = new StringBuilder();
		moduleActionSb.append("委托查询");
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(String.class, new StringConverter()).setPrettyPrinting();
		Gson gson = gb.create();
		String json = gson.toJson(paraMap);
		log.setModule(moduleActionSb.toString());
		log.setRev1(json);
		putLog("query", log);
		return pageView.getDataResponse();
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.EntrustApplyService#getByEntrustNo(java.lang.String)
	 */
	@Override
	public EntrustApplyData getByEntrustNo(String entrustNo) throws Exception {
		//根据委托号查询委托基本信息
		EntrustBaseInfo entrustBase = entrustBaseMapper.getById(entrustNo);
		//根据委托号查询附件文书信息
		EntrusAttachment para = new EntrusAttachment();
		para.setEntrustNo(entrustNo);
		List<EntrusAttachment> entrusAttachs = entrustAttachMapper.queryAll(para);
		//根据委托号查询案件信息
		EntrustCaseInfo caseInfo = entrustCaseMapper.getByEntrustNo(entrustNo);
		//根据委托号查询受害人信息列表
		EntrustVictimInfo victimInfoPara = new EntrustVictimInfo();
		victimInfoPara.setEntrustNo(entrustNo);
		List<EntrustVictimInfo> victimInfos = entrustVictimMapper.queryAll(victimInfoPara);
		//根据委托号查询嫌疑人信息列表
		EntrustSuspectInfo suspectInfoPara = new EntrustSuspectInfo();
		suspectInfoPara.setEntrustNo(entrustNo);
		List<EntrustSuspectInfo> suspectInfos = entrustSuspectMapper.queryAll(suspectInfoPara);
		//根据委托号查询调查字段信息列表
		EntrusInvestigationTypeinfo investigateInfoPara = new EntrusInvestigationTypeinfo();
		investigateInfoPara.setEntrustNo(entrustNo);
		List<EntrusInvestigationTypeinfo> investigateInfos = entrustInvestigateMapper.queryAll(investigateInfoPara);
		//根据委托号查询需协查信息列表
		EntrusInvestigationInfo queryInfoPara = new EntrusInvestigationInfo();
		queryInfoPara.setEntrustNo(entrustNo);
		List<EntrusInvestigationInfo> queryInfos = entrustQueryMapper.queryAll(queryInfoPara);
		//历史审核记录
		EntrustCheckList checkPara = new EntrustCheckList();
		checkPara.setEntrustNo(entrustNo);
		List<EntrustCheckList> checkList = entrustCheckMapper.queryAll(checkPara);
		//组装结果
		EntrustApplyData entrustApplyData = new EntrustApplyData();
		entrustApplyData.setEntrustInfo(entrustBase);
		entrustApplyData.setAttachments(entrusAttachs);
		entrustApplyData.setCaseInfo(caseInfo);
		entrustApplyData.setVictimInfo(victimInfos);
		entrustApplyData.setSuspectInfo(suspectInfos);
		entrustApplyData.setInvestigateInfo(investigateInfos);
		entrustApplyData.setQueryInfo(queryInfos);
		entrustApplyData.setCheckList(checkList);
		return entrustApplyData;
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.EntrustApplyService#getLastByEntrustSer(com.hisign.publicsafety.entity.EntrustBaseInfo)
	 */
	public EntrustApplyData getLastByEntrustSer(EntrustBaseInfo para) throws Exception{
		EntrustBaseInfo entrustBase = entrustBaseMapper.getLastByEntrustSer(para);
		String entrustNo=entrustBase.getEntrustNo();
		return getByEntrustNo(entrustNo);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.EntrustApplyService#getUnfinishCount(long, int)
	 */
	@Override
	public int getUnfinishCount(long time, int createUser) throws Exception {
		EntrustBaseInfo para = new EntrustBaseInfo();
		para.setProcessState(Constant.PROCESS_STATE_FEEDBACK);
		para.setCreateUser(createUser);
		para.setCreateDate(new Date(time));
		int count = entrustBaseMapper.queryCount(para);
		return count;
	}


}
