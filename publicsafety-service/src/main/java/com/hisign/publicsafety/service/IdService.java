/**
 * 
 */
package com.hisign.publicsafety.service;

/**
 * 编号生成
 * @author chailiangzhi
 * @date 2015-8-11
 * 
 */
public interface IdService {
	/**
	 * 获取唯一ID
	 * @return
	 * @throws Exception
	 */
	public int getId() throws Exception;

	/**
	 * 根据单位生成委托号
	 * @param unitNo
	 * @return
	 * @throws Exception
	 */
	public String getEntrustNo(String unitNo) throws Exception;

	/**
	 * 根据单位生成串并案编号
	 * @param unitNo
	 * @return
	 * @throws Exception
	 */
	public String getSeriesCaseNo(String unitNo) throws Exception;
	
	/**
	 * 生成API流水
	 * @return
	 * @throws Exception
	 */
	public String getApiSn() throws Exception;
}
