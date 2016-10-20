package com.hisign.publicsafety.entity.ccp;

import java.util.Date;

import com.hisign.publicsafety.base.entity.BaseEntity;

/**
 * 线索手机平台-日志信息实体
 * @author hotdog
 * 2015-11-24
 */
public class CcpLog extends BaseEntity implements java.io.Serializable {
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 操作时间
	 */
	private Date operationDate;
	
	/**
	 * 操作类型
	 */
	private String operationType;
	
	/**
	 * 操作模块
	 */
	private String module;
	
	/**
	 * 操作Ip
	 */
	private String operationIp;
	
	/**
	 * 操作描述
	 */
	private String operationDesc;
	
	/**
	 * 操作人ID
	 */
	private String operatorId;
	
	/**
	 * 操作人
	 */
	private String operator;
	
	/**
	 * 操作来源
	 */
	private String operationResource;

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getOperationIp() {
		return operationIp;
	}

	public void setOperationIp(String operationIp) {
		this.operationIp = operationIp;
	}

	public String getOperationDesc() {
		return operationDesc;
	}

	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperationResource() {
		return operationResource;
	}

	public void setOperationResource(String operationResource) {
		this.operationResource = operationResource;
	}
}
