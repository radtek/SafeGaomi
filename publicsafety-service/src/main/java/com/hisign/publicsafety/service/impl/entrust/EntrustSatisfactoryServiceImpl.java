/**
 * 
 */
package com.hisign.publicsafety.service.impl.entrust;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.EntrustCheckList;
import com.hisign.publicsafety.entity.EntrustSatisfactoryResults;
import com.hisign.publicsafety.mapper.entrust.EntrustCheckMapper;
import com.hisign.publicsafety.mapper.entrust.EntrustSatisfactoryMapper;
import com.hisign.publicsafety.service.EntrustBaseService;
import com.hisign.publicsafety.service.EntrustSatisfactoryService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * @author chailiangzhi
 * @date 2015-8-12
 * 委托满意度服务实现
 */
@Transactional
@Service("entrustSatisfactoryService")
public class EntrustSatisfactoryServiceImpl extends GeneralService<EntrustSatisfactoryResults> implements
		EntrustSatisfactoryService {

	@Autowired
	private EntrustSatisfactoryMapper entrustSatisfactoryMapper;
	@Autowired
	private EntrustBaseService entrustBaseService;
	@Autowired
	private EntrustCheckMapper entrustCheckMapper;
	@Autowired
	private IdService idService;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#addBatch(java.util.List)
	 */
	@Override
	public void addBatch(List<EntrustSatisfactoryResults> list) throws Exception {
		EntrustSatisfactoryResults satisfy=list.get(0);
		String entrustNo = satisfy.getEntrustNo();
		//修改委托状态为"已完成"
		Date updateDate = satisfy.getUpdateDate();
		int updateUser = satisfy.getUpdateUser();
		entrustBaseService.updateEntrustStatus(entrustNo, Constant.PROCESS_STATE_FINISH, updateDate, updateUser);
		//评价数据入库
		entrustSatisfactoryMapper.addBatch(list);
		//记录委托状态变更日志
		EntrustCheckList entrustCheckLog = new EntrustCheckList();
		entrustCheckLog.setId(idService.getId() + "");
		entrustCheckLog.setEntrustNo(entrustNo);
		entrustCheckLog.setCheckKey(Constant.PROCESS_STATE_FINISH);
		int checkUser = satisfy.getCreateUser();
		if (updateUser != 0) {
			checkUser = updateUser;
		}
		entrustCheckLog.setCheckUser(checkUser);
		entrustCheckLog.setCheckDate(updateDate);
		entrustCheckLog.setCheckIspass(1);
		entrustCheckMapper.add(entrustCheckLog);

	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#queryAll(java.lang.Object)
	 */
	@Override
	public List<EntrustSatisfactoryResults> queryAll(EntrustSatisfactoryResults t) {
		return entrustSatisfactoryMapper.queryAll(t);
	}

}
