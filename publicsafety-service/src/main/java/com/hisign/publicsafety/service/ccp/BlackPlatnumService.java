package com.hisign.publicsafety.service.ccp;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.ccp.BlackPlatnum;

/**
 * 车牌号黑名单信息管理服务接口
 * @author wangk
 *
 */
public interface BlackPlatnumService extends BaseService<BlackPlatnum>{

	/**
	 * 查询车牌号黑名单信息
	 * @param platNum
	 * @return
	 */
	public BlackPlatnum findByPlatnum(String platNum);
	
	/**
	 * 新增车牌黑名单信息
	 */
	public void addBlackPlatnum(BlackPlatnum blackPlatnum);
	
}
