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
import com.hisign.publicsafety.entity.Account;
import com.hisign.publicsafety.entity.AliRegisterInfo;
import com.hisign.publicsafety.mapper.ali.AliRegistMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.AliRegistService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * @author chailiangzhi
 * @date 2015-8-12
 * 阿里注册信息服务实现
 */
@Transactional
@Service("aliRegistService")
public class AliRegistServiceImpl extends GeneralService<AliRegisterInfo> implements
		AliRegistService {

	@Autowired
	private AliRegistMapper aliRegistMapper;
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#queryAll(java.lang.Object)
	 */
	@Override
	public List<AliRegisterInfo> queryAll(AliRegisterInfo para) {
		return aliRegistMapper.queryAll(para);
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#addBatch(java.util.List)
	 */
	@Override
	public void addBatch(List<AliRegisterInfo> list) throws Exception {
		aliRegistMapper.addBatch(list);

	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.BaseService#query(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.lang.Object)
	 */
	@Override
	public PageView query(PageView pageView, AliRegisterInfo para) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("para", para);
		
		int count = aliRegistMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<AliRegisterInfo> list = aliRegistMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

}
