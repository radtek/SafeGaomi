package com.hisign.publicsafety.entity.ccp;

import java.sql.Blob;

import com.hisign.publicsafety.base.entity.BaseEntity;

/**
 * 手机授权注册实体
 * @author hotdog
 *
 */
public class PhoneLicense extends BaseEntity implements java.io.Serializable {

	/**
	 * 主键
	 */
	public String id;
	
	/**
	 * 电话号码
	 */
	public String phoneNum;
	
	/**
	 * 序列号
	 */
	public String serialNum;
	
	/**
	 * 授权次数
	 */
	public int authorCount;
	
	/**
	 * 授权文件
	 */
	public byte[] authorFile;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public int getAuthorCount() {
		return authorCount;
	}

	public void setAuthorCount(int authorCount) {
		this.authorCount = authorCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getAuthorFile() {
		return authorFile;
	}

	public void setAuthorFile(byte[] authorFile) {
		this.authorFile = authorFile;
	}

}
