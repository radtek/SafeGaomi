package com.hisign.publicsafety.mapper.ccp;

import java.util.List;
import java.util.Map;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.ccp.ClueInfo;

/**
 * 线索信息Mapper
 * @author hotdog
 * 2015-11-24
 */
public interface ClueInfoMapper extends BaseMapper<ClueInfo> {
	
	/**
	 * 新增线索
	 * @param clueInfo
	 * @return
	 */
	public void addClueInfo(ClueInfo clueInfo);
	/**
	 * 版本更新
	 * @param apkno
	 * @return
	 */
	public String findByApk();
	
	/**
	 * 查询用户单位编码
	 * @param apkno
	 * @return
	 */
	public String queryByProviderId(String providerId);
	
	/**
	 * 返回所有数据
	 * @param t
	 * @return
	 */
	public List<ClueInfo> getAll(Map<String, Object> map);
}
