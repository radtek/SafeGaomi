/**
 * 
 */
package com.hisign.publicsafety.service;

/**
 * @author chailiangzhi
 * @date 2015-8-21
 * 阿里接口服务
 */
public interface AliPortService {
	/**
	 * 调用阿里接口传输委托请求信息
	 * @param entrustNo
	 * @throws Exception
	 */
	public void callAli(String entrustNo) throws Exception;
}
