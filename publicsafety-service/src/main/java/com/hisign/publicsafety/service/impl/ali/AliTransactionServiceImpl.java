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
import com.hisign.publicsafety.entity.AliTransactionRecords;
import com.hisign.publicsafety.mapper.ali.AliTransactionMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.AliTransactionService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * @author chailiangzhi
 * @date 2015-8-12
 * 阿里交易记录服务实现
 */
@Transactional
@Service("aliTransactionService")
public class AliTransactionServiceImpl extends GeneralService<AliTransactionRecords> implements
		AliTransactionService {

	@Autowired
	private AliTransactionMapper aliTransactionMapper;
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#queryAll(java.lang.Object)
	 */
	@Override
	public List<AliTransactionRecords> queryAll(AliTransactionRecords para) {
		return aliTransactionMapper.queryAll(para);
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#addBatch(java.util.List)
	 */
	@Override
	public void addBatch(List<AliTransactionRecords> list) throws Exception {
		aliTransactionMapper.addBatch(list);

	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.BaseService#query(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.lang.Object)
	 */
	@Override
	public PageView query(PageView pageView, AliTransactionRecords para) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("para", para);
		
		int count = aliTransactionMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<AliTransactionRecords> list = aliTransactionMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

}
