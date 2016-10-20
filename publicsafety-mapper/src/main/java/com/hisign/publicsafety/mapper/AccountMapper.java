package com.hisign.publicsafety.mapper;

import java.util.List;
import java.util.Map;

import com.hisign.publicsafety.base.BaseMapper;
import com.hisign.publicsafety.entity.Account;

/**
 * 用户管理Mapper
 * @author zhao
 * 上午10:17:16
 */
public interface AccountMapper extends BaseMapper<Account>{
	
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
	
	/**
	 * 验证用户登陆
	 * @author lanyuan
	 * Email：mmm333zzz520@163.com
	 * date：2014-2-25
	 * @param Account account
	 * @return
	 */
	public Account countAccount(Account account);
	
}
