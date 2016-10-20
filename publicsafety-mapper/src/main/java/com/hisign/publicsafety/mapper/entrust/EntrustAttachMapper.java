/**
 * 
 */
package com.hisign.publicsafety.mapper.entrust;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.EntrusAttachment;

/**
 * @author chailiangzhi
 * @date 2015-8-11
 * 委托附件表访问接口
 */
public interface EntrustAttachMapper extends BaseMapper<EntrusAttachment> {
	/**
	 * 返回文件内容
	 * @param attachment
	 * @return
	 */
	public String queryContent(EntrusAttachment attachment);
}
