package com.hisign.publicsafety.mapper.ccp;

import java.util.List;
import java.util.Map;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.ccp.BlackPlatnum;

/**
 * 车牌号黑名单Mapper
 * @author wangk
 * 2016年9月19日10:15:43
 */
public interface BlackPlatInfoMapper extends BaseMapper<BlackPlatnum> {
	
	/**
	 * 新增车牌黑名单
	 * @param BlackPlatnum
	 * @return
	 */
	public void addBlackPlatnum(BlackPlatnum blackPlatnum);
	/**
	 * 根据车牌号查询黑名单信息
	 * @param platnum
	 * @return
	 */
	public BlackPlatnum findByPlatnum(String platnum);
	
	/**
	 * 返回所有数据
	 * @param t
	 * @return
	 */
	public List<BlackPlatnum> getAll(Map<String, Object> map);
}
