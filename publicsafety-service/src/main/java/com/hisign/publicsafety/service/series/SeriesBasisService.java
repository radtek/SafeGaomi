/**
 * 
 */
package com.hisign.publicsafety.service.series;

import java.util.List;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.AliRegisterInfo;

/**
 * @author chailiangzhi
 * @date 2015-9-1
 * 串并案依据服务
 */
public interface SeriesBasisService extends BaseService {
	
	/**
	 * 保存注册信息里包含的串并案依据
	 * @param entrustCaseId
	 * @param aliRegisterInfos
	 * @return
	 * @throws Exception
	 */
	public boolean saveBasis(String entrustCaseId, List<AliRegisterInfo> aliRegisterInfos) throws Exception;
}
