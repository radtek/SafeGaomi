package com.hisign.publicsafety.base.entity;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hisign.publicsafety.util.JsonDateSerializer;

/**
 * @author chailiangzhi
 * @date 2015-8-22
 * 数据库表对应的实体基类
 */
public class BaseEntity implements Serializable {
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 表格记录序号
	 */
	private String no;
	
	/**
	 * 创建时间（线索上传时间，用户注册时间共用）；
	 */
	private Date createDate;
	
	/**
	 * 创建时间（起始 查询用）
	 */
	private String createBegDatetime;
	
	/**
	 * 创建时间（截止 查询用）
	 */
	private String createEndDatetime;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;
	
	/**
	 * 更新人
	 */
	private String updateUser;
	
	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 时间格式化
	 * @return
	 */
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

}
