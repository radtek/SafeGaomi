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
import com.hisign.publicsafety.entity.series.SeriesData;
import com.hisign.publicsafety.mapper.series.SeriesInfoListMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.service.series.SeriesInfoListService;
import com.hisign.publicsafety.util.EncodeUtil;

/**
 * 我的串并案信息列表查询服务实现
 * @author chailiangzhi
 * @date 2015-9-11
 * 
 */
@Transactional
@Service("seriesInfoListService")
public class SeriesInfoListServiceImpl extends GeneralService<Map<String, Object>> implements SeriesInfoListService {
	@Autowired
	private SeriesInfoListMapper seriesInfoListMapper;

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.BaseService#query(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.lang.Object)
	 */
	@Override
	public PageView query(PageView pageView, Map<String, Object> mapCondition) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : mapCondition.entrySet()) {
			map.put(entry.getKey().trim(), entry.getValue());
		}
		String seriesReasonKey = "seriesReason";
		if (map.containsKey(seriesReasonKey)) {
			map.put("seriesReasonInt", EncodeUtil.encodeSeriesReason(map.get(seriesReasonKey).toString()));
		}
		int count = seriesInfoListMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		List<SeriesData> list = seriesInfoListMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

}
