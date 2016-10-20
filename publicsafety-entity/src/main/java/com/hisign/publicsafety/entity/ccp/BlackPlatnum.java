package com.hisign.publicsafety.entity.ccp;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.util.JsonDateSerializer;

/**
 * 车牌号黑名单信息
 * @author wangk
 *
 */
public class BlackPlatnum extends BaseEntity implements java.io.Serializable {

	/**
	 * 车牌号黑名单信息
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	public String id;
	
	/**
	 * 车牌号
	 */
	public String platNum;
	
	/**
	 * 提供者手机号
	 */
	public String offerPho;
	/**
	 * 提供者手机号
	 */
	public String offerName;
	/**
	 * 通知人手机号
	 */
	public String informPho;
	/**
	 * 提供者单位
	 */
	public String unitCode;
	/**
	 * 提供者单位
	 */
	public String unitName;
	/**
	 * 备注
	 */
	public String remark;
	/**
	 * 录入时间
	 */
	public Date createTime;
	/**
	 * 录入时间（起始 查询用）
	 */
	private String createBegDatetime;

	/**
	 * 录入时间（截止 查询用）
	 */
	private String createEndDatetime;
	/**
	 * 是否比对
	 */
	public String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlatNum() {
		return platNum;
	}
	public void setPlatNum(String platNum) {
		this.platNum = platNum;
	}
	public String getOfferPho() {
		return offerPho;
	}
	public void setOfferPho(String offerPho) {
		this.offerPho = offerPho;
	}
	public String getInformPho() {
		return informPho;
	}
	public void setInformPho(String informPho) {
		this.informPho = informPho;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 时间格式化
	 * 
	 * @return
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public String getCreateBegDatetime() {
		return createBegDatetime;
	}
	public void setCreateBegDatetime(String createBegDatetime) {
		this.createBegDatetime = createBegDatetime;
	}
	public String getCreateEndDatetime() {
		return createEndDatetime;
	}
	public void setCreateEndDatetime(String createEndDatetime) {
		this.createEndDatetime = createEndDatetime;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}
