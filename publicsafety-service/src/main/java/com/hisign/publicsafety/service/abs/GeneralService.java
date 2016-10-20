/**
 * 
 */
package com.hisign.publicsafety.service.abs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.Log;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;

/**
 * 通用服务,提供空实现
 * @author chailiangzhi
 * @date 2015-8-7
 * 
 */
public abstract class GeneralService<T> extends BaseBean implements BaseService<T> {


	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#queryAll(java.lang.Object)
	 */
	@Override
	public List<T> queryAll(T t) {
		throw new RuntimeException("not implemented!");
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws Exception {
		throw new RuntimeException("not implemented!");
		
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#update(java.lang.Object)
	 */
	@Override
	public void update(T t) throws Exception {
		throw new RuntimeException("not implemented!");
		
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#getById(java.lang.String)
	 */
	@Override
	public T getById(String id) {
		throw new RuntimeException("not implemented!");
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#add(java.lang.Object)
	 */
	@Override
	public void add(T t) throws Exception {
		throw new RuntimeException("not implemented!");
		
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.BaseService#query(com.hisign.publicsafety.pulgin.mybatis.plugin.PageView, java.lang.Object)
	 */
	@Override
	public PageView query(PageView pageView, T t) {
		throw new RuntimeException("not implemented!");
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#addBatch(java.util.List)
	 */
	@Override
	public void addBatch(List<T> list) throws Exception {
		throw new RuntimeException("not implemented!");
		
	}

	/* (non-Javadoc)
	 * @see com.hisign.publicsafety.base.Base#queryCount(java.lang.Object)
	 */
	@Override
	public int queryCount(T t) {
		throw new RuntimeException("not implemented!");
	}

}
