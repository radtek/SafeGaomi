package com.hisign.publicsafety.entity;

import java.util.*;

/**
 * 委托审核信息实体
 * @author fankai
 * @date 2015-8-15
 */
public class EntrustCheckList implements java.io.Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -2244248771994226179L;
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 委托编号
	 */
	private String entrustNo;
	
	/**
	 * 审核状态编码
	 */
	private String checkKey;
	
	/**
	 * 审核人
	 */
	private int checkUser;
	
	/**
	 * 审核时间
	 */
	private Date checkDate;
	
	/**
	 * 审核是否通过
	 */
	private int checkIspass;
	
	/**
	 * 审核说明
	 */
	private String checkDesdc;
	
	/**
	 * 是否执行
	 */
	private String isExec;
	
	/**
	 * 执行序号
	 */
	private int execNo;
	
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

	public int getCheckIspass() {
		return this.checkIspass;
	}

	public void setCheckIspass(int checkIspass) {
		this.checkIspass = checkIspass;
	}

	public String getRev2() {
		return this.rev2;
	}

	public void setRev2(String rev2) {
		this.rev2 = rev2;
	}

	public int getCheckUser() {
		return this.checkUser;
	}

	public void setCheckUser(int checkUser) {
		this.checkUser = checkUser;
	}

	public String getCheckKey() {
		return this.checkKey;
	}

	public void setCheckKey(String checkKey) {
		this.checkKey = checkKey;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsExec() {
		return this.isExec;
	}

	public void setIsExec(String isExec) {
		this.isExec = isExec;
	}

	public String getCheckDesdc() {
		return this.checkDesdc;
	}

	public void setCheckDesdc(String checkDesdc) {
		this.checkDesdc = checkDesdc;
	}

	public String getRev7() {
		return this.rev7;
	}

	public void setRev7(String rev7) {
		this.rev7 = rev7;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public int getExecNo() {
		return this.execNo;
	}

	public void setExecNo(int execNo) {
		this.execNo = execNo;
	}

	public String getRev4() {
		return this.rev4;
	}

	public void setRev4(String rev4) {
		this.rev4 = rev4;
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

	public String getEntrustNo() {
		return this.entrustNo;
	}

	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}
}