package com.hisign.publicsafety.service.impl.entrust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.entity.EntrusAttachment;
import com.hisign.publicsafety.mapper.entrust.EntrustAttachMapper;
import com.hisign.publicsafety.service.EntrustAttachService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * 
 */

/**
 * @author chailiangzhi
 * @date 2015-8-17
 * 委托附件服务实现
 */
@Transactional
@Service("entrustAttachService")
public class EntrustAttachServiceImpl extends GeneralService<EntrusAttachment> implements EntrustAttachService {

	@Autowired
	private EntrustAttachMapper entrustAttachMapper;
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.EntrustAttachService#queryContent(com.hisign.publicsafety.entity.EntrusAttachment)
	 */
	@Override
	public String queryContent(EntrusAttachment attachment) {
		return entrustAttachMapper.queryContent(attachment);
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.abs.GeneralService#getById(java.lang.String)
	 */
	@Override
	public EntrusAttachment getById(String id) {
		return entrustAttachMapper.getById(id);
	}

	

}
