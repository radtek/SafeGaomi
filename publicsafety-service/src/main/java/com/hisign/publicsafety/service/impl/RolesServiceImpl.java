package com.hisign.publicsafety.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.entity.Account;
import com.hisign.publicsafety.entity.RoleAccount;
import com.hisign.publicsafety.entity.Roles;
import com.hisign.publicsafety.mapper.RolesMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.ResourcesService;
import com.hisign.publicsafety.service.RolesService;
import com.hisign.publicsafety.service.abs.GeneralService;
import com.hisign.publicsafety.util.Common;

/**
 * 角色service实现类
 * @author hotdog
 * 上午10:42:02
 */
@Transactional
@Service("RolesService")
public class RolesServiceImpl extends GeneralService<Roles> implements RolesService {
	@Autowired
	private RolesMapper roleMapper;
	@Inject
	private ResourcesService resourcesService;
	
	public PageView query(PageView pageView, Roles roles) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", roles);
		
		int count = roleMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<Roles> list = roleMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	public List<Roles> queryAll(Roles t) {
		return roleMapper.queryAll(t);
	}

	public void delete(String id) throws Exception {
		roleMapper.delete(id);
		
	}
	
	/**
	 * 根据用户Id删除用户角色中间表
	 * @param accId
	 */
	public void deleteAccountRole (String accId) {
		//删除角色权限中间表
		resourcesService.deleteResourcesRole(accId);
		roleMapper.deleteAccountRole(accId);
	}
	
	public void update(Roles t) throws Exception {
		//新增角色权限中间表
		resourcesService.addRoleRes(String.valueOf(t.getId()),t.getResourcesIds());
		roleMapper.update(t);
	}

	public Roles getById(String id) {
		return roleMapper.getById(id);
	}

	public void add(Roles t) throws Exception {
		//新增角色权限中间表
		resourcesService.addRoleRes(String.valueOf(t.getId()),t.getResourcesIds());
		roleMapper.add(t);
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.RolesService#isExist(java.lang.String)
	 */
	public Roles isExist(String name) {
		return roleMapper.isExist(name);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.RolesService#isExistByRoleKey(java.lang.String)
	 */
	public Roles isExistByRoleKey(String roleKey) {
		return roleMapper.isExistByRoleKey(roleKey);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.RolesService#findbyAccountRole(java.lang.String)
	 */
	public Roles findbyAccountRole(String accountId) {
		return roleMapper.findbyAccountRole(accountId);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.RolesService#addAccRole(com.hisign.publicsafety.entity.RoleAccount)
	 */
	public void addAccRole(RoleAccount roleAccount) {
		roleMapper.deleteAccountRole(roleAccount.getAccountId().toString());
		roleMapper.addAccRole(roleAccount);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.RolesService#addAccRole(java.lang.String, java.util.List)
	 */
	public void addAccRole(String accountId, List<String> ids) {
		roleMapper.deleteAccountRole(accountId);
		for (String roleId : ids) {
			if(!Common.isEmpty(roleId)){
				RoleAccount roleAccount = new RoleAccount();
				roleAccount.setAccountId(Integer.parseInt(accountId) );
				roleAccount.setRoleId(Integer.parseInt(roleId) );
				roleMapper.addAccRole(roleAccount);
			}
			
		}
	}

}
