package com.hisign.publicsafety.mapper.ccp;

import java.util.Map;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.ccp.ClueProviderInfo;

/**
 * 线索提供人Mapper
 * @author hotdog
 * 2015-11-24
 */
public interface ClueProviderInfoMapper extends BaseMapper<ClueProviderInfo> {
	
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
