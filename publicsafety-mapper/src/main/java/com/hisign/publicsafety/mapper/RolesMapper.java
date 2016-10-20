package com.hisign.publicsafety.mapper;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.RoleAccount;
import com.hisign.publicsafety.entity.Roles;

/**
 * 角色管理Mapper
 * @author hotdog
 * 上午10:46:28
 */
public interface RolesMapper extends BaseMapper<Roles>{
	public Roles isExist(String name);
	
	public Roles isExistByRoleKey(String roleKey);
	
	public Roles findbyAccountRole(String accountId);
	
	public void addAccRole(RoleAccount roleAccount);
	
	public void deleteAccountRole(String accountId);
}
