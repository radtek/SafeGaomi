package com.hisign.publicsafety.service.ccp;

import java.util.Map;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.ccp.ClueProviderInfo;

/**
 * 线索征集平台-线索提供人（手机注册用户）服务接口
 * @author hotdog
 *
 */
public interface ClueProviderInfoService extends BaseService<ClueProviderInfo>{
	
	/**
	 * 手机号登录
	 * @param idCardNum
	 * @return
	 */
	public ClueProviderInfo findByPhoneNum(String phoneNum);
	
	/**
	 * 根据id 设置/修改头像
	 * @param obj
	 */
	public void setHeadPictureById (ClueProviderInfo obj);
}
