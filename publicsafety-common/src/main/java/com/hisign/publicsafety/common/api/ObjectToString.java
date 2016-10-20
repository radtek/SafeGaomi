/**
 * 
 */
package com.hisign.publicsafety.common.api;

/**
 * 对象和字符串的转换接口
 * @author chailiangzhi
 * @date 2015-9-18
 * 
 */
public interface ObjectToString {
	/**
	 * 对象转字符串
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String serial(Object obj) throws Exception;

	/**
	 * 字符串转对象
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public <T> T unserial(String str, Class<T> clazz) throws Exception;
}
