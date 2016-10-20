/**
 * 
 */
package com.hisign.publicsafety.mapper.series;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.SeriesCaseDetail;

/**
 * @author chailiangzhi
 * @date 2015-8-11
 * 串并案详情表访问接口
 */
public interface SeriesCaseDetailMapper extends BaseMapper<SeriesCaseDetail> {

	/**
	 * 根据串并案id删除串并案详情记录
	 * @param seriesCaseId
	 * @throws Exception
	 */
	public void deleteBySeriesId(String seriesCaseId) throws Exception;
	
	/**
	 * 根据串并案编号删除串并案详情记录
	 * @param seriesCaseId
	 * @throws Exception
	 */
	public void deleteBySeriesNo(String seriesCaseNo) throws Exception;
}