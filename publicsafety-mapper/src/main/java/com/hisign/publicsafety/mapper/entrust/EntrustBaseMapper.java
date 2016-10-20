/**
 * 
 */
package com.hisign.publicsafety.mapper.entrust;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.EntrustBaseInfo;

/**
 * @author chailiangzhi
 * @date 2015-8-11
 * 委托基本信息表访问接口
 */
public interface EntrustBaseMapper extends BaseMapper<EntrustBaseInfo> {
	/**
	 * 返回同一批委托的最后一个
	 * @param para
	 * @return
	 */
	public EntrustBaseInfo getLastByEntrustSer(EntrustBaseInfo para);
}
