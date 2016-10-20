/**
 * 
 */
package com.hisign.publicsafety.web.entity;

/**
 * @author chailiangzhi
 * @date 2015-9-9
 * 验证码参数DTO
 */
public class ChkCodeEntity {
	private String chkCodeSig;
	private String chkCodeSessionId;
	private String chkCodeToken;

	/**
	 * @return the chkCodeSig
	 */
	public String getChkCodeSig() {
		return chkCodeSig;
	}

	/**
	 * @param chkCodeSig the chkCodeSig to set
	 */
	public void setChkCodeSig(String chkCodeSig) {
		this.chkCodeSig = chkCodeSig;
	}

	/**
	 * @return the chkCodeSessionId
	 */
	public String getChkCodeSessionId() {
		return chkCodeSessionId;
	}

	/**
	 * @param chkCodeSessionId the chkCodeSessionId to set
	 */
	public void setChkCodeSessionId(String chkCodeSessionId) {
		this.chkCodeSessionId = chkCodeSessionId;
	}

	/**
	 * @return the chkCodeToken
	 */
	public String getChkCodeToken() {
		return chkCodeToken;
	}

	/**
	 * @param chkCodeToken the chkCodeToken to set
	 */
	public void setChkCodeToken(String chkCodeToken) {
		this.chkCodeToken = chkCodeToken;
	}

}
