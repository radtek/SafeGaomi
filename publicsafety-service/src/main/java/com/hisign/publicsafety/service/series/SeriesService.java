/**
 * 
 */
package com.hisign.publicsafety.service.series;

import java.util.Map;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.series.CaseDataForSelect;
import com.hisign.publicsafety.entity.series.CaseInfoInSeries;
import com.hisign.publicsafety.entity.series.SeriesData;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;

/**
 * @author chailiangzhi
 * 串并案服务
 */
public interface SeriesService extends BaseService {

	/**
	 * 查询可选案件列表
	 * @param pageView 分页参数
	 * @param paraMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public DataResponse<CaseDataForSelect> queryCase(PageView pageView, Map<String, Object> paraMap) throws Exception;

	/**
	 * 根据某个案件串并其他案件
	 * @param pageView 分页条件
	 * @param paraMap 查询条件(
	 * 	entrustCaseId:案件ID,
	 * 	createUser:委托案件信息的创建用户,
	 * )
	 * @return
	 * @throws Exception
	 */
	public DataResponse<CaseDataForSelect> seriesSearch(PageView pageView, Map<String, Object> paraMap)
			throws Exception;

	/**
	 * 保存串并案结果
	 * @param seriesData
	 * @return
	 * @throws Exception
	 */
	public boolean seriesSave(SeriesData seriesData) throws Exception;

	/**
	 * 查询我的串并案列表
	 * @param pageView 分页参数
	 * @param paraMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public DataResponse<SeriesData> querySeries(PageView pageView, Map<String, Object> paraMap) throws Exception;

	/**
	 * 查询串并案详细信息
	 * @param seriesInfoId 串并案信息表ID
	 * @return 串并案信息实体
	 * @throws Exception
	 */
	public SeriesData getSeriesDetail(String seriesInfoId) throws Exception;

	/**
	 * 查询串并案详细信息中的案件列表
	 * @param pageView 分页参数
	 * @param paraMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public DataResponse<CaseInfoInSeries> queryCaseInSeri(PageView pageView, Map<String, Object> paraMap)
			throws Exception;
}
