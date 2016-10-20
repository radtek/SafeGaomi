package com.hisign.publicsafety.service.impl.ccp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.ccp.ClueInfo;
import com.hisign.publicsafety.entity.ccp.ClueProviderInfo;
import com.hisign.publicsafety.mapper.ccp.ClueProviderInfoMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.service.ccp.ClueProviderInfoService;

/**
 * 线索征集平台-线索提供人服务接口实现类
 * @author hotdog
 *
 */
@Transactional
@Service("clueProviderInfoService")
public class ClueProviderInfoServiceImpl extends GeneralService<ClueProviderInfo> implements ClueProviderInfoService {

	@Autowired
	private ClueProviderInfoMapper clueProviderInfoMapper;
	
	/**
	 * 分页查询
	 */
	public PageView query(PageView pageView, ClueProviderInfo t) {
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", t);
		
		int count = clueProviderInfoMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<ClueProviderInfo> list = clueProviderInfoMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}
	
	/**
	 * 查询注册用户总数(首页用)
	 */
	public int queryCount (ClueProviderInfo t) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("t", t);
		return clueProviderInfoMapper.getCount(map);
	}

	/**
	 * 根据id查询手机用户信息
	 */
	public ClueProviderInfo getById(String id) {
		return clueProviderInfoMapper.getById(id);
	}
	
	/**
	 * 删除注册用户
	 */
	public void delete(String id) throws Exception{ 
		clueProviderInfoMapper.delete(id);
	}
	
	/**
	 * 修改注册用户
	 */
	public void update(ClueProviderInfo obj) throws Exception{ 
		clueProviderInfoMapper.update(obj);
	}
	
	/**
	 * 移动端用户注册
	 * @throws Exception
	 */
	public void add(ClueProviderInfo obj) throws Exception {
		clueProviderInfoMapper.add(obj);
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.ccp.ClueProviderInfoService#findByPhoneNum(java.lang.String)
	 */
	public ClueProviderInfo findByPhoneNum(String phoneNum) {
		ClueProviderInfo obj = clueProviderInfoMapper.findByPhoneNum(phoneNum);
		return obj;
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.ccp.ClueProviderInfoService#setHeadPictureById(com.hisign.publicsafety.entity.ccp.ClueProviderInfo)
	 */
	public void setHeadPictureById (ClueProviderInfo obj) {
		clueProviderInfoMapper.setHeadPictureById(obj);
	}
}
