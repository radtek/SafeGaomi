package com.hisign.publicsafety.entity;

import java.util.Date;
import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.util.ExcelDataMapper;

/**
 * 阿里反馈-阿里账户明细实体
 * @author fankai
 * @date 2015-8-15
 */
public class AliAccountDetail extends BaseEntity implements java.io.Serializable {
    
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -5564534576497205499L;
	
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
	private String transOutOrderNo;
	
	/**
	 * 交易账户
	 */
	private String transAccount;
	
	/**
	 * 对方账户
	 */
	private String otherAccount;
	
	/**
	 * 交易发生日期
	 */
	private String transDate;
	
	/**
	 * 交易发生时间
	 */
	private String transDt;
	
	/**
	 * 收入金额(+)
	 */
	private String transAmountD;
	
	/**
	 * 支出金额(-)
	 */
	private String transAmountC;
	
	/**
	 * 余额
	 */
	private String balance;
	
	/**
	 * 业务类型
	 */
	private String subTransCodeDesc;
	
	/**
	 * 交易发生地
	 */
	private String tradeArea;
	
	/**
	 * 银行名称
	 */
	private String showName;
	
	/**
	 * 备注
	 */
	private String transMemo;
	
	/**
	 * 创建人
	 */
	private int createUser;
	
	/**
	 * 创建时间
	 */
	private Date createDate;

	@ExcelDataMapper(title = "支出金额(-)", order = 1)
	public String getTransAmountC() {
		return this.transAmountC;
	}

	public void setTransAmountC(String transAmountC) {
		this.transAmountC = transAmountC;
	}

	@ExcelDataMapper(title = "收入金额(+)", order = 2)
	public String getTransAmountD() {
		return this.transAmountD;
	}

	public void setTransAmountD(String transAmountD) {
		this.transAmountD = transAmountD;
	}

	@ExcelDataMapper(title = "交易发生时间", order = 3)
	public String getTransDt() {
		return this.transDt;
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}

	@ExcelDataMapper(title = "备注", order = 4)
	public String getTransMemo() {
		return this.transMemo;
	}

	public void setTransMemo(String transMemo) {
		this.transMemo = transMemo;
	}

	@ExcelDataMapper(title = "交易账户", order = 5)
	public String getTransAccount() {
		return this.transAccount;
	}

	public void setTransAccount(String transAccount) {
		this.transAccount = transAccount;
	}

	@ExcelDataMapper(title = "交易发生地", order = 6)
	public String getTradeArea() {
		return this.tradeArea;
	}

	public void setTradeArea(String tradeArea) {
		this.tradeArea = tradeArea;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ExcelDataMapper(title = "交易发生日期", order = 8)
	public String getTransDate() {
		return this.transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	@ExcelDataMapper(title = "余额", order = 9)
	public String getBalance() {
		return this.balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public int getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	@ExcelDataMapper(title = "流水号（包含提现流水号", order = 11)
	public String getTransOutOrderNo() {
		return this.transOutOrderNo;
	}

	public void setTransOutOrderNo(String transOutOrderNo) {
		this.transOutOrderNo = transOutOrderNo;
	}

	@ExcelDataMapper(title = "对方帐户", order = 12)
	public String getOtherAccount() {
		return this.otherAccount;
	}

	public void setOtherAccount(String otherAccount) {
		this.otherAccount = otherAccount;
	}

	@ExcelDataMapper(title = "银行名称", order = 13)
	public String getShowName() {
		return this.showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	@ExcelDataMapper(title = "业务类型", order = 14)
	public String getSubTransCodeDesc() {
		return this.subTransCodeDesc;
	}

	public void setSubTransCodeDesc(String subTransCodeDesc) {
		this.subTransCodeDesc = subTransCodeDesc;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getEntrustNo() {
		return this.entrustNo;
	}

	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}
}
