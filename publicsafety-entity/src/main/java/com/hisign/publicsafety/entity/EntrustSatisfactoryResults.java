package com.hisign.publicsafety.entity;

import java.util.*;

/**
 * 委托反馈评价实体
 * @author fankai
 * @date 2015-8-15
 */
public class EntrustSatisfactoryResults implements java.io.Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -6516121580771947307L;
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 委托编号
	 */
	private String entrustNo;
	
	/**
	 * 反馈项目key
	 */
	private String satisfactoryKey;
	
	/**
	 * 反馈项结果
	 */
	private String satisfactoryResult;
	
	/**
	 * 项目类型
	 */
	private int type;
	
	/**
	 * 意见和建议
	 */
	private String advice;
	
	/**
	 * 创建人
	 */
	private int createUser;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 修改人
	 */
	private int updateUser;
	
	/**
	 * 修改时间
	 */
	private Date updateDate;
	
	
	/**
	 * 是否删除  0 未删除 1 已删除
	 */
	private String deleteFlag;
	
	/**
	 * 预留字段1
	 */
	private String rev1;
	
	/**
	 * 预留字段2
	 */
	private String rev2;
	
	/**
	 * 预留字段3
	 */
	private String rev3;
	
	/**
	 * 预留字段4
	 */
	private String rev4;
	
	/**
	 * 预留字段5
	 */
	private String rev5;
	
	/**
	 * 预留字段6
	 */
	private String rev6;
	
	/**
	 * 预留字段7
	 */
	private String rev7;


	public String getRev1() {
		return this.rev1;
	}

	public void setRev1(String rev1) {
		this.rev1 = rev1;
	}

	public String getRev2() {
		return this.rev2;
	}

	public void setRev2(String rev2) {
		this.rev2 = rev2;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSatisfactoryResult() {
		return this.satisfactoryResult;
	}

	public void setSatisfactoryResult(String satisfactoryResult) {
		this.satisfactoryResult = satisfactoryResult;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public String getSatisfactoryKey() {
		return this.satisfactoryKey;
	}

	public void setSatisfactoryKey(String satisfactoryKey) {
		this.satisfactoryKey = satisfactoryKey;
	}

	public String getRev7() {
		return this.rev7;
	}

	public void setRev7(String rev7) {
		this.rev7 = rev7;
	}

	public String getRev4() {
		return this.rev4;
	}

	public void setRev4(String rev4) {
		this.rev4 = rev4;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRev3() {
		return this.rev3;
	}

	public void setRev3(String rev3) {
		this.rev3 = rev3;
	}

	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getRev6() {
		return this.rev6;
	}

	public void setRev6(String rev6) {
		this.rev6 = rev6;
	}

	public String getRev5() {
		return this.rev5;
	}

	public void setRev5(String rev5) {
		this.rev5 = rev5;
	}

	public int getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(int updateUser) {
		this.updateUser = updateUser;
	}

	public String getEntrustNo() {
		return this.entrustNo;
	}

	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}
}