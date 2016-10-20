package com.hisign.publicsafety.mapper;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.Dic;

/**
 * 字典管理Mapper
 * @author zhao
 * 上午11:25:18
 */
public interface DicMapper extends BaseMapper<Dic>{
	
	public Dic isExist(Dic dic);
	
}
