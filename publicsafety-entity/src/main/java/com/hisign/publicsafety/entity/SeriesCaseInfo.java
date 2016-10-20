package com.hisign.publicsafety.entity;

import java.util.*;

/**
 * 串并案信息实体
 * @author fankai
 * @date 2015-8-15
 */
public class SeriesCaseInfo implements java.io.Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 3194755443590420941L;
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 串并案编号
	 */
	private String seriesCaseNo;
	
	/**
	 * 串并案名称
	 */
	private String seriesCaseName;
	
	/**
	 * 串并依据
	 */
	private String seriesReason;
	
	/**
	 * 串并案描述
	 */
	private String seriesDesc;
	
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
	 * 整型串并依据取值范围0-15
	 */
	private int   seriesReasonInt;
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSeriesCaseNo() {
		return this.seriesCaseNo;
	}

	public void setSeriesCaseNo(String seriesCaseNo) {
		this.seriesCaseNo = seriesCaseNo;
	}

	public String getSeriesCaseName() {
		return this.seriesCaseName;
	}

	public void setSeriesCaseName(String seriesCaseName) {
		this.seriesCaseName = seriesCaseName;
	}

	public String getSeriesReason() {
		return this.seriesReason;
	}

	public void setSeriesReason(String seriesReason) {
		this.seriesReason = seriesReason;
	}

	public String getSeriesDesc() {
		return this.seriesDesc;
	}

	public void setSeriesDesc(String seriesDesc) {
		this.seriesDesc = seriesDesc;
	}

	public int getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(int updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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

	public String getRev3() {
		return this.rev3;
	}

	public void setRev3(String rev3) {
		this.rev3 = rev3;
	}

	public String getRev4() {
		return this.rev4;
	}

	public void setRev4(String rev4) {
		this.rev4 = rev4;
	}

	public String getRev5() {
		return this.rev5;
	}

	public void setRev5(String rev5) {
		this.rev5 = rev5;
	}

	public String getRev6() {
		return this.rev6;
	}

	public void setRev6(String rev6) {
		this.rev6 = rev6;
	}

	public String getRev7() {
		return this.rev7;
	}

	public void setRev7(String rev7) {
		this.rev7 = rev7;
	}

	public int getSeriesReasonInt() {
		return this.seriesReasonInt;
	}

	public void setSeriesReasonInt(int seriesReasonInt) {
		this.seriesReasonInt = seriesReasonInt;
	}
}