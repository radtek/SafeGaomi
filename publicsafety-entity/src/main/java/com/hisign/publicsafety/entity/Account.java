package com.hisign.publicsafety.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.util.ExcelDataMapper;
import com.hisign.publicsafety.util.JsonDateSerializer;


/**
 * 用户实体类
 */
@SuppressWarnings("serial")
public class Account extends BaseEntity implements java.io.Serializable {
	
	/**
	 * 唯一主键
	 */
	private int id;

	/**
	 * 用户名
	 */
	private String accountName;
	
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 说明
	 */
	private String description;

	/**
	 * 账号状态  0 表示停用  1表示启用 
	 */
	private String state;

	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 真实姓名
	 */
	private String trueName;

	/**
	 * 联系电话
	 */
	private String phoneNumber;
	
	/**
	 * 单位代码
	 */
	private String unitCode;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 角色ID
	 */
	private String roleIds; 
	
	/**
	 * 角色名
	 */
	private String roleName; 
	
	/**
	 * 警员编号
	 */
	private String policeNumber; 
	
	/**
	 * 职责
	 */
	private String duty;
	
	/**
	 * 单位名称
	 */
	private String unitName;
	
	@ExcelDataMapper(title="id",order=1)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ExcelDataMapper(title="账号名称",order=2)
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@ExcelDataMapper(title="账号状态",order=3)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 时间格式化
	 * @author zhao
	 * @return
	 */
	@ExcelDataMapper(title="创建时间",order=4)
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountName=" + accountName + ", password=" + password + ",description=" + description + ", state=" + state + ", createTime=" + createTime + "]";
	}
	@ExcelDataMapper(title="描述",order=5)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPoliceNumber() {
		return policeNumber;
	}

	public void setPoliceNumber(String policeNumber) {
		this.policeNumber = policeNumber;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

}
