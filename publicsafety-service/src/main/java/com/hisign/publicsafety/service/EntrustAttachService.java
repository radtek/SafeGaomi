/**
 * 
 */
package com.hisign.publicsafety.service;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.EntrusAttachment;

/**
 * 委托的附件文书
 * @author chailiangzhi
 * @date 2015-8-10
 * 
 */
public interface EntrustAttachService extends BaseService<EntrusAttachment> {
	/**
	 * 查询附件内容
	 * @param attachment需包含entrustNo和fileName
	 * @return
	 */
	public String queryContent(EntrusAttachment attachment);
}
