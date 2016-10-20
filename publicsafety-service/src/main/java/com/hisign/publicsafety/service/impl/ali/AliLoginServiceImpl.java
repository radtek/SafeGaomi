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
import com.hisign.publicsafety.entity.AliLoginHistory;
import com.hisign.publicsafety.mapper.ali.AliLoginMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.AliLoginService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * @author chailiangzhi
 * @date 2015-8-12
 * 阿里登录日志服务实现
 */
@Transactional
@Service("aliLoginService")
public class AliLoginServiceImpl extends GeneralService<AliLoginHistory> implements
		AliLoginService {

	@Autowired
	private AliLoginMapper aliLoginMapper;
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#queryAll(java.lang.Object)
	 */
	@Override
	public List<AliLoginHistory> queryAll(AliLoginHistory para) {
		return aliLoginMapper.queryAll(para);
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#addBatch(java.util.List)
	 */
	@Override
	public void addBatch(List<AliLoginHistory> list) throws Exception {
		aliLoginMapper.addBatch(list);

	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.BaseService#query(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.lang.Object)
	 */
	@Override
	public PageView query(PageView pageView, AliLoginHistory para) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("para", para);
		
		int count = aliLoginMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<AliLoginHistory> list = aliLoginMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

}
