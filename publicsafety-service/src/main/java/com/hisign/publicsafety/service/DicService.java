package com.hisign.publicsafety.service;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.Dic;


/**
 * 字典管理service接口
 * @author hotdog
 * 上午10:59:19
 */
public interface DicService extends BaseService<Dic>{
	
	public Dic isExist(Dic dic);
	
	/**
	 * 获取单位的JSON
	 * @return
	 * @throws Exception
	 */
	public String getUnitJson() throws Exception;
}
