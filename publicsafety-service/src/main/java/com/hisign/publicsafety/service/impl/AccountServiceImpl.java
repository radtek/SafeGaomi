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
import com.hisign.publicsafety.mapper.AccountMapper;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;
import com.hisign.publicsafety.service.AccountService;
import com.hisign.publicsafety.service.RolesService;
import com.hisign.publicsafety.service.abs.GeneralService;


/**
 * 用户管理模块service实现类
 * @author hotdog
 * 上午10:09:23
 */
@Transactional
@Service("accountService")
public class AccountServiceImpl extends GeneralService<Account> implements AccountService {
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private RolesService roleService;

	public PageView query(PageView pageView, Account account) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", account);
		
		int count = accountMapper.getCount(map);
		pageView.setRowCount(count);
		String beginrow = String.valueOf((pageView.getPageNow() - 1) * pageView.getPageSize());
		map.put(Constant.SQL_PAGE_START_KEY, Integer.valueOf(beginrow));
		map.put(Constant.SQL_PAGE_SIZE_KEY, pageView.getPageSize());
		
		List<Account> list = accountMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}
	
	public List<Account> queryAll(Account t) {
		return accountMapper.queryAll(t);
	}

	public void delete(String id) throws Exception {
		
		//删除用户角色中间表
		roleService.deleteAccountRole(id);
		
		accountMapper.delete(id);
	}

	public void update(Account t) throws Exception {
		
		//删除用户角色并新建
		roleService.deleteAccountRole(String.valueOf(t.getId()));
		RoleAccount roleAccount = new RoleAccount();
		roleAccount.setRoleId(Integer.valueOf(t.getRoleIds()));
		roleAccount.setAccountId(t.getId());
		roleService.addAccRole(roleAccount);
		
		accountMapper.update(t);
	}

	public Account getById(String id) {
		return accountMapper.getById(id);
	}

	/**
	 * 用户新增
	 */
	public void add(Account t) throws Exception {
		
		//添加用户角色关系表
		RoleAccount roleAccount = new RoleAccount();
		roleAccount.setRoleId(Integer.valueOf(t.getRoleIds()));
		roleAccount.setAccountId(t.getId());
		roleService.addAccRole(roleAccount);
		
		accountMapper.add(t);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.AccountService#querySingleAccount(java.lang.String)
	 */
	public Account querySingleAccount(String accountName) {
		return accountMapper.querySingleAccount(accountName);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.AccountService#isExist(java.lang.String)
	 */
	public Account isExist(String accountName) {
		return accountMapper.isExist(accountName);
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.service.AccountService#isExistByPoliceNumber(java.lang.String)
	 */
	public Account isExistByPoliceNumber (String policeNumber) {
		return accountMapper.isExistByPoliceNumber(policeNumber);
	}

}
