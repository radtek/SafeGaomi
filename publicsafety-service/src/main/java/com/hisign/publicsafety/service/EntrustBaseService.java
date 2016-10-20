/**
 * 
 */
package com.hisign.publicsafety.service;

import java.util.Date;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.EntrustBaseInfo;

/**
 * @author chailiangzhi
 * @date 2015-9-10
 * 委托基本信息
 */
public interface EntrustBaseService extends BaseService<EntrustBaseInfo> {
	/**
	 * @param entrustNo
	 * @param status
	 * @param updateDate
	 * @param updateUser
	 * @throws Exception
	 */
	public void updateEntrustStatus(String entrustNo, String status, Date updateDate, int updateUser) throws Exception;
}
