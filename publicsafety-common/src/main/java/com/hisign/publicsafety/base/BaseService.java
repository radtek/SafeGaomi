package com.hisign.publicsafety.base;

import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;

/**
 * 所有服务接口都要继承这个
 * @date 2014-2-11
 * @Email: 
 * @version 1.0v
 * @param <T>
 */
public interface BaseService<T> extends Base<T> {
	
	/**
	 * 返回分页后的数据
	 * @param pageView
	 * @param t
	 * @return
	 */
	public PageView query(PageView pageView, T t);
	
	
}
