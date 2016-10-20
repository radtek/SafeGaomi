/**
 * 
 */
package com.hisign.publicsafety.service;

import java.util.Date;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.EntrustCaseInfo;

/**
 * @author chailiangzhi
 * @date 2015-9-10
 * 委托案件信息服务接口
 */
public interface EntrustCaseService extends BaseService<EntrustCaseInfo> {
	/**
	 * 修改案件串并状态
	 * @param id
	 * @param isSeries
	 * @param seriesDate
	 * @param seriesUser
	 * @throws Exception
	 */
	public void updateSeriesStatus(String id, String isSeries, Date seriesDate, int seriesUser) throws Exception;
}
