package com.hisign.publicsafety.service.impl.ccp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.ccp.ClueInfo;
import com.hisign.publicsafety.mapper.ccp.ClueInfoMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.service.ccp.ClueInfoService;

/**
 * 线索征集平台-线索信息服务接口实现类
 * @author hotdog
 * 2015-11-24
 */
@Transactional
@Service("clueInfoService")
public class ClueInfoServiceImpl extends GeneralService<ClueInfo> implements ClueInfoService {
	
	@Autowired
	private ClueInfoMapper clueInfoMapper;

	/**
	 * 分页查询
	 */
	public PageView query(PageView pageView, ClueInfo t) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", t);
		
		int count = clueInfoMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<ClueInfo> list = clueInfoMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	/**
	 * 查询线索总数(首页用)
	 */
	public int queryCount (ClueInfo t) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("t", t);
		return clueInfoMapper.getCount(map);
	}
	
	/**
	 * 查询线索总数(首页用)
	 */
	public List<ClueInfo> queryAll (ClueInfo t) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("t", t);
		//时间倒序 100条以内
		map.put(Constant.SQL_PAGE_START_KEY, 0);
		map.put(Constant.SQL_PAGE_SIZE_KEY, 100);
		return clueInfoMapper.getAll(map);
	}
	
	/**
	 * 根据id查询线索信息
	 */
	public ClueInfo getById(String id) {
		return clueInfoMapper.getById(id);
	}

	@Override
	/**
	 * 新增线索信息
	 */
	public void insertClueInfo(ClueInfo clueInfo) {
		if (clueInfo!=null){
			clueInfoMapper.addClueInfo(clueInfo);
		}
		
	}
	
	@Override
	/**
	 * 查询用户单位编码
	 */
	public String queryByProviderId(String providerId) {
		if (StringUtils.isNotEmpty(providerId)){
			return (String)clueInfoMapper.queryByProviderId(providerId);
		}
		return "";
	}
}
