package com.hisign.publicsafety.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.entity.DicType;
import com.hisign.publicsafety.mapper.DicTypeMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.DicTypeService;
import com.hisign.publicsafety.service.abs.GeneralService;

/**
 * 字典类型service实现类
 * @date 2015-9-10
 * 字典类型服务的实现
 */
@Transactional
@Service("dicTypeService")
public class DicTypeServiceImpl extends GeneralService<DicType> implements DicTypeService {
	@Autowired
	private DicTypeMapper dicTypeMapper;

	public PageView query(PageView pageView, DicType dicType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", dicType);
		List<DicType> list = dicTypeMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	public void add(DicType dicType) throws Exception {
		dicTypeMapper.add(dicType);
	}

	public void delete(String id) throws Exception {
		dicTypeMapper.delete(id);
	}

	public DicType getById(String id) {
		return dicTypeMapper.getById(id);
	}

	public void update(DicType dicType) throws Exception {
		dicTypeMapper.update(dicType);
	}

	public List<DicType> queryAll(DicType dicType) {
		return dicTypeMapper.queryAll(dicType);
	}

	public DicType queryById(DicType dicType) {
		return dicTypeMapper.queryById(dicType);
	}
	public DicType isExist(DicType dicType) {
		return dicTypeMapper.isExist(dicType);
	}

}
