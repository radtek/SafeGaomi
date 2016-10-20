package com.hisign.publicsafety.service.ccp;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.ccp.ClueInfo;

/**
 * 线索征集平台-线索信息服务接口
 * @author hotdog
 *
 */
public interface ClueInfoService extends BaseService<ClueInfo>{

	/**
	 * 新增线索信息
	 * @param clueInfo
	 * @return
	 */
	public void insertClueInfo(ClueInfo clueInfo);
	/**
	 * 上传时增加用户单位的注册
	 * @param clueInfo
	 * @return
	 */
	public String queryByProviderId(String providerId);
	
}
