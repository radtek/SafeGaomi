package com.hisign.publicsafety.entity;

import java.util.*;

/**
 * 委托基本信息实体
 * @author fankai
 * @date 2015-8-15
 */
public class EntrustBaseInfo implements java.io.Serializable {
    
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1070480208465196283L;
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 委托编号
	 */
	private String entrustNo;
	
	/**
	 * 委托序号
	 */
	private String entrustSer;
	
	/**
	 * 委托次数
	 */
	private int entrustNumber;
	
	/**
	 * 委托类型
	 */
	private String entrustType;
	
	/**
	 * 办案单位省
	 */
	private String provinceCode;
	
	/**
	 * 办案单位市
	 */
	private String cityCode;
	
	/**
	 * 办案单位
	 */
	private String unit;
	
	/**
	 * 办案人
	 */
	private String personName;
	
	/**
	 * 办案人联系电话
	 */
	private String telephone;
	
	/**
	 * 办案人职务
	 */
	private String title;
	
	/**
	 * 是否来人调取
	 */
	private String istake;
	
	/**
	 * 来访人姓名
	 */
	private String visitorName;
	
	/**
	 * 来访人联系方式
	 */
	private String visitorTelephone;
	
	/**
	 * 来访人单位
	 */
	private String visitorUnit;
	
	/**
	 * 来访人职务
	 */
	private String visitorTitle;
	
	/**
	 * 案件办理阶段
	 */
	private String caseStage;
	
	/**
	 * 是否需要回函
	 */
	private String isReply;
	
	/**
	 * 备注
	 */
	private String memo;
	
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
	 * 删除标识  0 未删除 1 已删除
	 */
	private String deleteFlag;
	
	/**
	 * 数据来源
	 */
	private String dataResource;
	
	/**
	 * 流程状态
	 */
	private String processState;
	
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
	 * 是否共享案件信息
	 */
	private String isShareCaseinfo;
	
	/**
	 * 是否共享查询信息
	 */
	private String isShareQueryresult;

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

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getVisitorUnit() {
		return this.visitorUnit;
	}

	public void setVisitorUnit(String visitorUnit) {
		this.visitorUnit = visitorUnit;
	}

	public String getIsShareCaseinfo() {
		return this.isShareCaseinfo;
	}

	public void setIsShareCaseinfo(String isShareCaseinfo) {
		this.isShareCaseinfo = isShareCaseinfo;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getIstake() {
		return this.istake;
	}

	public void setIstake(String istake) {
		this.istake = istake;
	}

	public String getEntrustType() {
		return this.entrustType;
	}

	public void setEntrustType(String entrustType) {
		this.entrustType = entrustType;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEntrustSer() {
		return this.entrustSer;
	}

	public void setEntrustSer(String entrustSer) {
		this.entrustSer = entrustSer;
	}

	public String getVisitorTitle() {
		return this.visitorTitle;
	}

	public void setVisitorTitle(String visitorTitle) {
		this.visitorTitle = visitorTitle;
	}

	public int getEntrustNumber() {
		return this.entrustNumber;
	}

	public void setEntrustNumber(int entrustNumber) {
		this.entrustNumber = entrustNumber;
	}

	public String getCaseStage() {
		return this.caseStage;
	}

	public void setCaseStage(String caseStage) {
		this.caseStage = caseStage;
	}

	public String getIsShareQueryresult() {
		return this.isShareQueryresult;
	}

	public void setIsShareQueryresult(String isShareQueryresult) {
		this.isShareQueryresult = isShareQueryresult;
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

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getRev3() {
		return this.rev3;
	}

	public void setRev3(String rev3) {
		this.rev3 = rev3;
	}

	public String getProcessState() {
		return this.processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
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

	public String getVisitorName() {
		return this.visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getVisitorTelephone() {
		return this.visitorTelephone;
	}

	public void setVisitorTelephone(String visitorTelephone) {
		this.visitorTelephone = visitorTelephone;
	}

	public String getDataResource() {
		return this.dataResource;
	}

	public void setDataResource(String dataResource) {
		this.dataResource = dataResource;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIsReply() {
		return this.isReply;
	}

	public void setIsReply(String isReply) {
		this.isReply = isReply;
	}

	public String getEntrustNo() {
		return this.entrustNo;
	}

	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}

	public int getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(int updateUser) {
		this.updateUser = updateUser;
	}
}