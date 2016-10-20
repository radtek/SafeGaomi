package com.hisign.publicsafety.service.impl.ccp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.ccp.BlackPlatnum;
import com.hisign.publicsafety.mapper.ccp.BlackPlatInfoMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.service.ccp.BlackPlatnumService;

/**
 * 车牌黑名单信息管理接口实现类
 * @author wangk
 * 2016年9月19日10:10:04
 */
@Transactional
@Service("blackPlatnumService")
public class BlackPlatnumServiceImpl extends GeneralService<BlackPlatnum> implements BlackPlatnumService {
	
	
	@Autowired
	private BlackPlatInfoMapper blackPlatInfoMapper;
	
	/**
	 * 分页查询
	 */
	public PageView query(PageView pageView, BlackPlatnum t) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", t);
		
		int count = blackPlatInfoMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<BlackPlatnum> list = blackPlatInfoMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	/**
	 * 删除车牌黑名单信息
	 */
	public void delete(String id) throws Exception{ 
		blackPlatInfoMapper.delete(id);
	}	

	/**
	 * 新增车牌黑名单信息
	 */
	@Override
	public void addBlackPlatnum(BlackPlatnum blackPlatnum) {
		if (blackPlatnum!=null){
			try {
				blackPlatInfoMapper.addBlackPlatnum(blackPlatnum);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 查询车牌号黑名单信息
	 * @param platNum
	 * @return
	 */
	@Override
	public BlackPlatnum findByPlatnum(String platNum){
		if(StringUtils.isNotEmpty(platNum)){
			return blackPlatInfoMapper.findByPlatnum(platNum);
		}
		return null;
	}
	
	public void update(BlackPlatnum blackPlatnum) throws Exception{
		if(blackPlatnum != null){
			blackPlatInfoMapper.update(blackPlatnum);
		}
	}
	
	
}
