package com.hisign.publicsafety.entity;

import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.util.ExcelDataMapper;
import java.util.*;

/**
 * 阿里反馈-阿里转账明细
 * @author fankai
 * @date 2015-8-15
 */
public class AliTransferDetail extends BaseEntity implements java.io.Serializable {
    
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 6846911876083083181L;
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 委托编号
	 */
	private String entrustNo;
	
	/**
	 * 流水号
	 */
	private String transferNo;
	
	/**
	 * 付款方
	 */
	private String senderUserid;
	
	/**
	 * 收款方
	 */
	private String receiverUserid;
	
	/**
	 * 收款机构
	 */
	private String receiverInst;
	
	/**
	 * 到账时间
	 */
	private String transSuccDate;
	
	/**
	 * 转账金额
	 */
	private String transAmount;
	
	/**
	 * 业务类型
	 */
	private String productName;
	
	/**
	 * 交易发生地
	 */
	private String tradeArea;
	
	/**
	 * 体现流水号
	 */
	private String withdrawNo;
	
	/**
	 * 创建人
	 */
	private int createUser;
	
	/**
	 * 创建时间
	 */
	private Date createDate;

	@ExcelDataMapper(title = "转账金额", order = 1)
	public String getTransAmount() {
		return this.transAmount;
	}

	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

	@ExcelDataMapper(title = "到账时间", order = 2)
	public String getTransSuccDate() {
		return this.transSuccDate;
	}

	public void setTransSuccDate(String transSuccDate) {
		this.transSuccDate = transSuccDate;
	}

	@ExcelDataMapper(title = "收款方", order = 3)
	public String getReceiverUserid() {
		return this.receiverUserid;
	}

	public void setReceiverUserid(String receiverUserid) {
		this.receiverUserid = receiverUserid;
	}

	@ExcelDataMapper(title = "流水号", order = 4)
	public String getTransferNo() {
		return this.transferNo;
	}

	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo;
	}

	@ExcelDataMapper(title = "交易发生地", order = 5)
	public String getTradeArea() {
		return this.tradeArea;
	}

	public void setTradeArea(String tradeArea) {
		this.tradeArea = tradeArea;
	}

	@ExcelDataMapper(title = "收款机构", order = 6)
	public String getReceiverInst() {
		return this.receiverInst;
	}

	public void setReceiverInst(String receiverInst) {
		this.receiverInst = receiverInst;
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

	@ExcelDataMapper(title = "提现流水号", order = 9)
	public String getWithdrawNo() {
		return this.withdrawNo;
	}

	public void setWithdrawNo(String withdrawNo) {
		this.withdrawNo = withdrawNo;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ExcelDataMapper(title = "业务类型", order = 11)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@ExcelDataMapper(title = "付款方", order = 12)
	public String getSenderUserid() {
		return this.senderUserid;
	}

	public void setSenderUserid(String senderUserid) {
		this.senderUserid = senderUserid;
	}

	public String getEntrustNo() {
		return this.entrustNo;
	}

	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}
}
