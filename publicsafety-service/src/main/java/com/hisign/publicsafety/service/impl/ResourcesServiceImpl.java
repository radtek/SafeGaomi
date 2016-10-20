package com.hisign.publicsafety.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.entity.Resources;
import com.hisign.publicsafety.entity.ResourcesRole;
import com.hisign.publicsafety.mapper.ResourcesMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.ResourcesService;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.util.Common;

/**
 * 模块管理service实现类
 * @date 2015-9-10
 */
@Transactional
@Service("resourcesService")
public class ResourcesServiceImpl extends GeneralService<Resources> implements ResourcesService {
	@Autowired
	private ResourcesMapper resourcesMapper;

	public PageView query(PageView pageView, Resources resources) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", resources);
		List<Resources> list = resourcesMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	
	public List<Resources> queryAll(Resources t) {
		return resourcesMapper.queryAll(t);
	}

	
	public void delete(String id) throws Exception {
		this.resourcesMapper.delete(id);
	}

	
	public void update(Resources t) throws Exception {
			this.resourcesMapper.update(t);
	}

	
	public Resources getById(String id) {
		return this.resourcesMapper.getById(id);
	}

	public List<Resources> queryByParentId(Resources resources)
	{
		return resourcesMapper.queryByParentId(resources);
	}
	
	public void add(Resources t) throws Exception {
		this.resourcesMapper.add(t);
	}

	
	public void updateSortOrder(List<Resources> resourcess) {
		for (Resources m : resourcess) {
			resourcesMapper.updateSortOrder(m);
		}
	}

	
	public List<Resources> findAccountResourcess(String accountId) {
		return resourcesMapper.findAccountResourcess(accountId);
	}

	
	public List<Resources> findRoleRes(String roleId) {
		return resourcesMapper.findRoleRes(roleId);
	}

	
	public void addRoleRes(String roleId,String resourcesIds) {
		resourcesMapper.deleteResourcesRole(roleId);
		if (!Common.isEmpty(resourcesIds)) {
			String[] resourcesIdArr = resourcesIds.split(",");
			for (int i = 0; i < resourcesIdArr.length; i ++) {
				if (Common.isEmpty(resourcesIdArr[i])){
					continue;
				}
				ResourcesRole rr = new ResourcesRole();
				rr.setRoleId(roleId);
				rr.setResId(resourcesIdArr[i]);
				resourcesMapper.addRoleRes(rr);
			}
		}
	}

	
	public int getMaxLevel() {
		return resourcesMapper.getMaxLevel();
	}

	
	public Resources isExist(String resourcesName) {
		return resourcesMapper.isExist(resourcesName);
	}

	/**
	 * 根据角色id删除角色权限表
	 * @param roleId
	 */
	public void deleteResourcesRole (String roleId) {
		resourcesMapper.deleteResourcesRole(roleId);
	}

//	@Override
//	public DataResponse<Resources> search(DataRequest request,
//			Class<Resources> cls) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
