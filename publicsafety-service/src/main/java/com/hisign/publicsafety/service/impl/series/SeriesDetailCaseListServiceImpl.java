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
import com.hisign.publicsafety.entity.series.CaseInfoInSeries;
import com.hisign.publicsafety.entity.series.SeriesData;
import com.hisign.publicsafety.mapper.series.SeriesDetailCaseListMapper;
import com.hisign.publicsafety.mapper.series.SeriesInfoListMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.service.series.SeriesDetailCaseListService;
import com.hisign.publicsafety.util.EncodeUtil;

/**
 * 单个串并案详情里案件列表查询服务实现
 * @author chailiangzhi
 *
 */
@Transactional
@Service("seriesDetailCaseListService")
public class SeriesDetailCaseListServiceImpl extends GeneralService<Map<String, Object>> implements SeriesDetailCaseListService {

	@Autowired
	private SeriesDetailCaseListMapper seriesDetailCaseListMapper;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.BaseService#query(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.lang.Object)
	 */
	@Override
	public PageView query(PageView pageView, Map<String, Object> mapCondition) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : mapCondition.entrySet()) {
			map.put(entry.getKey().trim(), entry.getValue());
		}
		int count = seriesDetailCaseListMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		List<CaseInfoInSeries> list = seriesDetailCaseListMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

}
