/**
 * 
 */
package com.hisign.publicsafety.mapper.series;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.SeriesCaseInfo;

/**
 * @author chailiangzhi
 * @date 2015-8-11
 * 串并案信息表访问接口
 */
public interface SeriesCaseInfoMapper extends BaseMapper<SeriesCaseInfo> {
	
	/**
	 * 根据串并案编号删除串并案记录
	 * @param seriesCaseId
	 * @throws Exception
	 */
	public void deleteBySeriesNo(String seriesCaseNo) throws Exception;
}