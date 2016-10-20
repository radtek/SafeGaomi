package com.hisign.publicsafety.common.excel.entity;

import java.util.List;

/**
 * @author chailiangzhi
 * @date 2015-8-18
 * 导出EXCEL工具类的入参实体
 */
public class ExcelData<T> {
	
	/**
	 * 实体class
	 */
	public Class<T> clazz;
	
	/**
	 * 实体集合
	 */
	public List<T> objs;
	
	/**
	 * sheet名称
	 */
	public String sheetName;
}
