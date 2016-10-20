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
import com.hisign.publicsafety.entity.AliTransferDetail;
import com.hisign.publicsafety.mapper.ali.AliTransferMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.AliTransferService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * @author chailiangzhi
 * @date 2015-8-12
 * 阿里转账明细服务实现
 */
@Transactional
@Service("aliTransferService")
public class AliTransferServiceImpl extends GeneralService<AliTransferDetail> implements
		AliTransferService {

	@Autowired
	private AliTransferMapper aliTransferMapper;
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#queryAll(java.lang.Object)
	 */
	@Override
	public List<AliTransferDetail> queryAll(AliTransferDetail para) {
		return aliTransferMapper.queryAll(para);
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#addBatch(java.util.List)
	 */
	@Override
	public void addBatch(List<AliTransferDetail> list) throws Exception {
		aliTransferMapper.addBatch(list);

	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.BaseService#query(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.lang.Object)
	 */
	@Override
	public PageView query(PageView pageView, AliTransferDetail para) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("para", para);
		
		int count = aliTransferMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<AliTransferDetail> list = aliTransferMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

}
