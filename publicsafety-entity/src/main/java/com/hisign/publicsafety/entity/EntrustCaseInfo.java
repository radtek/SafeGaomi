package com.hisign.publicsafety.entity;

import java.util.*;

/**
 * 委托案件信息实体
 * @author fankai
 * @date 2015-8-15
 */
public class EntrustCaseInfo implements java.io.Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 5041755621941940307L;
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 委托编号
	 */
	private String entrustNo;
	
	/**
	 * 案件名称
	 */
	private String caseName;
	
	/**
	 * 案发时间
	 */
	private String caseOccurDate;
	
	/**
	 * 案件编号
	 */
	private String caseNo;
	
	/**
	 * 案件金额
	 */
	private String caseMoneyAmout;
	
	/**
	 * 涉案品牌
	 */
	private String brand;
	
	/**
	 * 案件类型
	 */
	private String caseType;
	
	/**
	 * 简要案情
	 */
	private String caseInfo;
	
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
	
	/**
	 * 是否结案
	 */
	private String isEnd;
	/**
	 * 是否已串并
	 */
	private String   isSeries;
	/**
	 * 串并人
	 */
	private int   seriesUser;
	/**
	 * 串并日期
	 */
	private Date   seriesDate;

	public String getCaseType() {
		return this.caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

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

	public String getIsEnd() {
		return this.isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public String getCaseInfo() {
		return this.caseInfo;
	}

	public void setCaseInfo(String caseInfo) {
		this.caseInfo = caseInfo;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCaseOccurDate() {
		return this.caseOccurDate;
	}

	public void setCaseOccurDate(String caseOccurDate) {
		this.caseOccurDate = caseOccurDate;
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

	public String getRev7() {
		return this.rev7;
	}

	public void setRev7(String rev7) {
		this.rev7 = rev7;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCaseMoneyAmout() {
		return this.caseMoneyAmout;
	}

	public void setCaseMoneyAmout(String caseMoneyAmout) {
		this.caseMoneyAmout = caseMoneyAmout;
	}

	public String getCaseNo() {
		return this.caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
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

	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getEntrustNo() {
		return this.entrustNo;
	}

	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}

	/**
	 * @return the isSeries
	 */
	public String getIsSeries() {
		return isSeries;
	}

	/**
	 * @param isSeries the isSeries to set
	 */
	public void setIsSeries(String isSeries) {
		this.isSeries = isSeries;
	}

	/**
	 * @return the seriesUser
	 */
	public int getSeriesUser() {
		return seriesUser;
	}

	/**
	 * @param seriesUser the seriesUser to set
	 */
	public void setSeriesUser(int seriesUser) {
		this.seriesUser = seriesUser;
	}

	/**
	 * @return the seriesDate
	 */
	public Date getSeriesDate() {
		return seriesDate;
	}

	/**
	 * @param seriesDate the seriesDate to set
	 */
	public void setSeriesDate(Date seriesDate) {
		this.seriesDate = seriesDate;
	}
	
}