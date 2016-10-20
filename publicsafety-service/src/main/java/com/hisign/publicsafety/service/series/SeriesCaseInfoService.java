/**
 * 
 */
package com.hisign.publicsafety.service.series;

import java.util.List;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.SeriesCaseInfo;

/**
 * 串并案信息服务接口
 * @author chailiangzhi
 * 
 */
public interface SeriesCaseInfoService extends BaseService<SeriesCaseInfo> {
	/**
	 * 批量删除串并案
	 * @param idList
	 * @throws Exception
	 */
	public void deleteBatch(List<String> idList) throws Exception;
}
