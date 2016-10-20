/**
 * 
 */
package com.hisign.publicsafety.service.comm;

/**
 * O2o服务层异常类,便于调用者区分业务异常来源
 * @author chailiangzhi
 * @date 2015-9-29
 * 
 */
public class O2oServiceLayerException extends RuntimeException {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造函数
	 * @param message
	 * @param cause
	 */
	public O2oServiceLayerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
