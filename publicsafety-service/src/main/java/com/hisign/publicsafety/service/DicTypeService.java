package com.hisign.publicsafety.service;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.DicType;



/**
 * 字典类型service接口
 * @date 2015-9-10
 * 字典类型服务
 */
public interface DicTypeService extends BaseService<DicType>{
	
	public DicType isExist(DicType dicType);
	
	public DicType queryById(DicType dicType);
}
