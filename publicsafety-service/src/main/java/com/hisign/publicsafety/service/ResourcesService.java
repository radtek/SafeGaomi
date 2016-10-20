package com.hisign.publicsafety.service;


import java.util.List;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.Resources;

/**
 * 模块管理service接口
 * @date 2015-9-10
 */
public interface ResourcesService extends BaseService<Resources>{
	//<!-- 根据账号Id获取该用户的权限-->
	public List<Resources> findAccountResourcess(String accountId);
	/**
	 * @author zhao
	 * date：2014-2-25
	 * @return
	 */
	public List<Resources> findRoleRes(String roleId);
	
	public List<Resources> queryByParentId(Resources resources);
	public void addRoleRes(String roleId, String resourcesIdArr);

	public Resources isExist(String menuName);
	public  int  getMaxLevel();
	
	/**
	 * 根据角色id删除角色权限表
	 * @param roleId
	 */
	public void deleteResourcesRole (String roleId);
}
