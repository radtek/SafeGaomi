package com.hisign.publicsafety.mapper;


import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.DicType;

/**
 * @author chailiangzhi
 * @date 2015-9-10
 * 字典类型表访问接口
 */
public interface DicTypeMapper extends BaseMapper<DicType>{
	public DicType isExist(DicType dicType);
	public DicType queryById(DicType dicType);
}
