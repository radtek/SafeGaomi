package com.hisign.publicsafety.service;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.Account;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;


/**
 * 用户管理模块service接口
 * @author zhao
 * 上午10:04:18
 */
public interface AccountService extends BaseService<Account>{
	
	/**
	 * |根据用户名查询用户
	 * @param accountName
	 * @return
	 */
	public Account querySingleAccount(String accountName);
	
	/**
	 * 根据用户账号查询用户是否存在
	 * @param accountName
	 * @return
	 */
	public Account isExist(String accountName);
	
	/**
	 * 根据警员编号查询用户是否存在
	 * @param policeNumber
	 * @return
	 */
	public Account isExistByPoliceNumber (String policeNumber);
	
}
