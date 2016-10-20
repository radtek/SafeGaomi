/**
 * 
 */
package com.hisign.publicsafety.mapper.entrust;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.EntrustCaseInfo;

/**
 * @author chailiangzhi
 * @date 2015-8-11
 * 委托案件信息表访问接口
 */
public interface EntrustCaseMapper extends BaseMapper<EntrustCaseInfo> {
	/**
	 * 根据委托号查询案件信息
	 * @param entrustNo
	 * @return
	 */
	public EntrustCaseInfo getByEntrustNo(String entrustNo);
}
