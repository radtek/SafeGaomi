package com.hisign.publicsafety.service;


import java.util.List;


import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.RoleAccount;
import com.hisign.publicsafety.entity.Roles;

/**
 * 角色模块service接口
 * @author zhao
 * 上午10:34:14
 */
public interface RolesService extends BaseService<Roles>{
	
	/**
	 * 验证账号是否存在
	 * @param name
	 * @return
	 */
	public Roles isExist(String name);
	
	/**
	 * 根据rolekey查询角色
	 * @param name
	 * @return
	 */
	public Roles isExistByRoleKey(String roleKey);
	
	/**
	 * 根据用户id查询角色
	 * @param accountId
	 * @return
	 */
	public Roles findbyAccountRole(String accountId);
	
	/**
	 * 添加用户角色关系表
	 * @param roleAccount
	 */
	public void addAccRole(RoleAccount roleAccount);

	/**
	 * 增加用户角色
	 * @param accountId
	 * @param ids
	 */
	public void addAccRole(String accountId, List<String> ids);
	
	/**
	 * 根据用户Id删除用户角色中间表
	 * @param accId
	 */
	public void deleteAccountRole (String accId);
}
