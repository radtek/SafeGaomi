/**
 * 
 */
package com.hisign.publicsafety.service.abs;

import java.util.HashMap;
import java.util.Map;

import com.hisign.publicsafety.entity.Log;

/**
 * @author chailiangzhi
 * @date 2015-8-22
 * spring bean的基类
 */
public abstract class BaseBean {
	/**
	 * 存放日志内容的线程局部变量,日志的业务内容需要业务实现类填写,方法的执行时间由拦截器实现
	 */
	private final ThreadLocal<Map<String, Log>> threadLogHolder = new ThreadLocal<Map<String, Log>>();

	/**
	 * 业务实现类调用此方法存放日志业务内容
	 * @param methodName
	 * @param log
	 */
	public void putLog(String methodName, Log log) {
		Map<String, Log> map = threadLogHolder.get();
		if (map == null) {
			map = new HashMap<String, Log>();
			threadLogHolder.set(map);
		}
		map.put(methodName, log);
	}

	/**
	 * 拦截器调用此方法获取业务实现类中生成的日志,并进行后续操作(填写方法执行时间,存入数据库等)
	 * @param methodName
	 * @return
	 */
	public Log getLog(String methodName) {
		Map<String, Log> map = threadLogHolder.get();
		if (map == null) {
			return null;
		}
		Log log = map.get(methodName);
		map.remove(methodName);
		return log;
	}
}
