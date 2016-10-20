package com.hisign.publicsafety.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.util.JsonDateSerializer;

/**
 * 字典实体类
 * @author zhao
 * 上午11:31:58
 */
@SuppressWarnings("serial")
public class Log extends BaseEntity implements java.io.Serializable {
	
	/**
	 * 主键
	 */
	private int id; 
	
	/**
	 * 操作人
	 */
	private String username;
	
	/**
	 * 操作类型
	 */
	private String module; 
	
	/**
	 * 操作耗时
	 */
	private String actionTime; 
	
	/**
	 * 操作ip
	 */
	private String userIP; 
	
	/**
	 * 操作时间
	 */
	private Date operTime; 
	
	/**
	 * 唯一标识（数据主键）
	 */
	private String rev1; 
	
	/**
	 * 操作时间起始
	 */
	private String operTimeBeg; 
	
	/**
	 * 操作时间截止
	 */
	private String operTimeEnd; 
	
	/**
	 * 操作
	 */
	private String action;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	/**
	 * 时间格式化
	 * @return
	 */
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	public String getRev1() {
		return rev1;
	}
	public void setRev1(String rev1) {
		this.rev1 = rev1;
	}
	public String getOperTimeBeg() {
		return operTimeBeg;
	}
	public void setOperTimeBeg(String operTimeBeg) {
		this.operTimeBeg = operTimeBeg;
	}
	public String getOperTimeEnd() {
		return operTimeEnd;
	}
	public void setOperTimeEnd(String operTimeEnd) {
		this.operTimeEnd = operTimeEnd;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
