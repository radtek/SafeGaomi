package com.hisign.publicsafety.base;

import java.util.List;

/**
 * 数据层和业务层基础接口
 * @author chailiangzhi
 * @date 2015-8-19
 * 
 * @param <T>
 */
public interface Base<T> {
	
	/**
	 * 返回所有数据
	 * @param t
	 * @return
	 */
	public List<T> queryAll(T t);
	
	/**
	 * 返回条数
	 * @param t
	 * @return
	 */
	public int queryCount(T t);
	
	/**
	 * 根据id删除记录
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
	/**
	 * 更新记录
	 * @param t
	 * @throws Exception
	 */
	public void update(T t) throws Exception;
	
	/**
	 * 根据ID或者唯一条件查询
	 * @param id
	 * @return
	 */
	public T getById(String id);
	
	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public void add(T t) throws Exception;
	
	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	public void addBatch(List<T> list) throws Exception;
}
