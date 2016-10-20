package com.hisign.publicsafety.entity.ccp;

import com.hisign.publicsafety.base.entity.BaseEntity;

/**
 * 版本信息
 * @author wangk
 *
 */
public class CheckApk extends BaseEntity implements java.io.Serializable {

	/**
	 * 版本信息
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	public String id;
	/**
	 * 版本号
	 */
	public String apkno;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApkno() {
		return apkno;
	}
	public void setApkno(String apkno) {
		this.apkno = apkno;
	}
	
	
}
