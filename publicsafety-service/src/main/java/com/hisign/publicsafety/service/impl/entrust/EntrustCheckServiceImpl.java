/**
 * 
 */
package com.hisign.publicsafety.service.impl.entrust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.EntrustCheckList;
import com.hisign.publicsafety.entity.Log;
import com.hisign.publicsafety.mapper.entrust.EntrustCheckMapper;
import com.hisign.publicsafety.service.AliPortService;
import com.hisign.publicsafety.service.EntrustBaseService;
import com.hisign.publicsafety.service.EntrustCheckService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * @author chailiangzhi
 * @date 2015-8-16
 * 委托审核服务实现
 */
@Transactional
@Service("entrustCheckService")
public class EntrustCheckServiceImpl extends GeneralService<EntrustCheckList> implements EntrustCheckService {

	private static Logger logger = LoggerFactory.getLogger(EntrustCheckServiceImpl.class);
	@Autowired
	private EntrustBaseService entrustBaseService;
	@Autowired
	private EntrustCheckMapper entrustCheckMapper;
	@Autowired
	private AliPortService aliPortService;

	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#queryAll(java.lang.Object)
	 */
	@Override
	public List<EntrustCheckList> queryAll(EntrustCheckList para) {
		return entrustCheckMapper.queryAll(para);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#addBatch(java.util.List)
	 */
	@Override
	public void check(List<EntrustCheckList> list) throws Exception {
		Map<String,List<String>> checkIds=new HashMap<String,List<String>>();
		//记录审核信息
		entrustCheckMapper.addBatch(list);
		for (EntrustCheckList entrustCheck : list) {
			String entrustNo = entrustCheck.getEntrustNo();
			String checkKey = entrustCheck.getCheckKey();
			List<String> entrustNos=checkIds.get(checkKey);
			if(entrustNos==null){
				entrustNos=new ArrayList<String>();
				checkIds.put(checkKey, entrustNos);
			}
			entrustNos.add(entrustNo);
			//修改委托状态
			entrustBaseService.updateEntrustStatus(entrustNo, checkKey, entrustCheck.getCheckDate(), entrustCheck.getCheckUser());
			logger.debug("checkKey="+checkKey);
			//审核通过则调用阿里接口
			if (Constant.PROCESS_STATE_CHECKPASS.equals(checkKey)) {
				aliPortService.callAli(entrustNo);
			}
		}
		//业务日志
		Log log = new Log();
		StringBuilder moduleActionSb = new StringBuilder();
		StringBuilder dataIdSb = new StringBuilder();
		moduleActionSb.append("委托审核");
		if (checkIds.size() == 1) {
			String checkKey = checkIds.keySet().iterator().next();
			moduleActionSb.append("-").append(checkKey);
			dataIdSb.append(checkIds.get(checkKey).toString());
		} else {
			dataIdSb.append("通过:");
			dataIdSb.append(checkIds.get(Constant.PROCESS_STATE_CHECKPASS).toString());
			dataIdSb.append(";\n");
			dataIdSb.append("不通过:");
			dataIdSb.append(checkIds.get(Constant.PROCESS_STATE_CHECKNOTPASS).toString());
		}
		log.setModule(moduleActionSb.toString());
		log.setRev1(dataIdSb.toString());
		putLog("check", log);
	}

	/**
	 * 调用阿里接口
	 * @param entrustNo
	 * @throws Exception
	 */
//	private void callAli(String entrustNo) throws Exception {}

}
