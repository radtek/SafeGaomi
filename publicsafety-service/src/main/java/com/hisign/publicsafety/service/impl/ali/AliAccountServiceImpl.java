/**
 * 
 */
package com.hisign.publicsafety.service.impl.ali;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.AliAccountDetail;
import com.hisign.publicsafety.entity.AliRegisterInfo;
import com.hisign.publicsafety.mapper.ali.AliAccountMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.AliAccountService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * @author chailiangzhi
 * @date 2015-8-12
 * 阿里账户明细服务实现
 */
@Transactional
@Service("aliAccountService")
public class AliAccountServiceImpl extends GeneralService<AliAccountDetail> implements
		AliAccountService {

	@Autowired
	private AliAccountMapper aliAccountMapper;
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#queryAll(java.lang.Object)
	 */
	@Override
	public List<AliAccountDetail> queryAll(AliAccountDetail para) {
		return aliAccountMapper.queryAll(para);
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#addBatch(java.util.List)
	 */
	@Override
	public void addBatch(List<AliAccountDetail> list) throws Exception {
		aliAccountMapper.addBatch(list);

	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.BaseService#query(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.lang.Object)
	 */
	@Override
	public PageView query(PageView pageView, AliAccountDetail para) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("para", para);
		
		int count = aliAccountMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<AliAccountDetail> list = aliAccountMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

}
