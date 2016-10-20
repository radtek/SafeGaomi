package com.hisign.publicsafety.entity;

import java.util.Date;
import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.util.ExcelDataMapper;

/**
 * 阿里反馈-阿里注册信息
 * @author fankai
 * @date 2015-8-15
 */
public class AliRegisterInfo extends BaseEntity implements java.io.Serializable {
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -6725005724418466849L;
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 委托编号
	 */
	private String entrustNo;
	
	/**
	 * 卡id
	 */
	private String iwUserId;
	
	/**
	 * 真是姓名
	 */
	private String realName;
	
	/**
	 * 性别
	 */
	private String gender;
	
	/**
	 * 证件号码
	 */
	private String certNo;
	
	/**
	 * 证件类型
	 */
	private String certType;
	
	/**
	 * 公司名称
	 */
	private String firmName;
	
	/**
	 * 卡别名(邮箱)
	 */
	private String loginEmail;
	
	/**
	 * 卡别名(手机)
	 */
	private String loginMobile;
	
	/**
	 * 银行名称
	 */
	private String instId;
	
	/**
	 * 银行卡号
	 */
	private String instAccountNo;
	
	/**
	 * 余额
	 */
	private String balance;
	
	/**
	 * 创建人
	 */
	private int createUser;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 会员名
	 */
	private String nick;
	
	/**
	 * 绑定手机
	 */
	private String mobile;
	
	/**
	 * 支付宝id
	 */
	private String alipayId;
	
	/**
	 * 注册时间 注册时间yyyymmddhh24miss
	 */
	private String registerTime;
	
	/**
	 * 绑定邮箱
	 */
	private String bindEmail;
	
	/**
	 * 最后登录时间yyyymmddhh24miss
	 */
	private String lastLogin;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntrustNo() {
		return this.entrustNo;
	}

	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}

	public String getIwUserId() {
		return this.iwUserId;
	}

	public void setIwUserId(String iwUserId) {
		this.iwUserId = iwUserId;
	}

	@ExcelDataMapper(title = "真实姓名", order = 4)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCertNo() {
		return this.certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCertType() {
		return this.certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getFirmName() {
		return this.firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getLoginEmail() {
		return this.loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getLoginMobile() {
		return this.loginMobile;
	}

	public void setLoginMobile(String loginMobile) {
		this.loginMobile = loginMobile;
	}

	public String getInstId() {
		return this.instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getInstAccountNo() {
		return this.instAccountNo;
	}

	public void setInstAccountNo(String instAccountNo) {
		this.instAccountNo = instAccountNo;
	}

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

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ExcelDataMapper(title = "会员名", order = 16)
	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@ExcelDataMapper(title = "绑定手机", order = 17)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ExcelDataMapper(title = "支付宝id", order = 18)
	public String getAlipayId() {
		return this.alipayId;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}

	@ExcelDataMapper(title = "注册时间", order = 19)
	public String getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@ExcelDataMapper(title = "绑定邮箱", order = 20)
	public String getBindEmail() {
		return this.bindEmail;
	}

	public void setBindEmail(String bindEmail) {
		this.bindEmail = bindEmail;
	}

	@ExcelDataMapper(title = "最后登录时间", order = 21)
	public String getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
}
