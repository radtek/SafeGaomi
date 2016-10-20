/**
 * 
 */
package com.hisign.publicsafety.service;

import java.util.List;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.EntrustCheckList;

/**
 * 委托审核
 * @author chailiangzhi
 * @date 2015-8-10
 * 
 */
public interface EntrustCheckService extends BaseService<EntrustCheckList> {
//	public void check(EntrustCheckList t) throws Exception;--不提供,请使用check(list的size=1)

	/**
	 * 委托审核,支持单条或者批量审核
	 * @param list
	 * @throws Exception
	 */
	public void check(List<EntrustCheckList> list) throws Exception;
}
