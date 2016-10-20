/**
 * 
 */
package com.hisign.publicsafety.web.comm;

/**
 * O2o展现层异常类,便于区分业务异常来源
 * @author chailiangzhi
 * @date 2015-9-29
 * 
 */
public class O2oWebLayerException extends RuntimeException {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造函数
	 * @param message
	 * @param cause
	 */
	public O2oWebLayerException(String message, Throwable cause) {
		super(message, cause);
	}

}
