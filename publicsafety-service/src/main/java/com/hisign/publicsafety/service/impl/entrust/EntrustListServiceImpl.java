/**
 * 
 */
package com.hisign.publicsafety.service.impl.entrust;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.mapper.entrust.EntrustListMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.EntrustListService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * @author chailiangzhi
 * @date 2015-8-10
 * 委托列表服务实现
 */
@Transactional
@Service("entrustInfoService")
public class EntrustListServiceImpl extends GeneralService<Map<String, Object>> implements EntrustListService {
	@Autowired
	private EntrustListMapper entrustInfoMapper;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.abs.GeneralService#query(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.lang.Object)
	 */
	@Override
	public PageView query(PageView pageView, Map<String, Object> mapCondition) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : mapCondition.entrySet()) {
			map.put(entry.getKey().trim(), entry.getValue());
		}
		int count = entrustInfoMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		List<EntrustApplyData> list = entrustInfoMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.abs.GeneralService#queryAll(java.lang.Object)
	 */
//	public List<Map<String, Object>> queryAll(Map<String, Object> paraMap) {
//		return entrustInfoMapper.queryAll(paraMap);
//	}
}
