/**
 * 
 */
package com.hisign.publicsafety.service.impl.series;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.series.CaseDataForSelect;
import com.hisign.publicsafety.mapper.series.SeriesSearchListMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.service.series.SeriesSearchListService;

/**
 * @author chailiangzhi
 * @date 2015-9-11
 * 串并案串并列表服务实现
 */
@Transactional
@Service("seriesSearchListService")
public class SeriesSearchListServiceImpl extends GeneralService<Map<String, Object>> implements SeriesSearchListService {
	@Autowired
	private SeriesSearchListMapper seriesSearchListMapper;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.BaseService#query(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.lang.Object)
	 */
	@Override
	public PageView query(PageView pageView, Map<String, Object> mapCondition) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : mapCondition.entrySet()) {
			map.put(entry.getKey().trim(), entry.getValue());
		}
		int count = seriesSearchListMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		List<CaseDataForSelect> list = seriesSearchListMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

}
