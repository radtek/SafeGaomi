package com.hisign.publicsafety.entity;

import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.util.ExcelDataMapper;
import java.util.*;

/**
 * 阿里反馈-阿里登录历史记录实体
 * @author fankai
 * @date 2015-8-15
 */
public class AliLoginHistory extends BaseEntity implements java.io.Serializable {
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 851845018828713915L;
	
	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 委托编号
	 */
	private String entrustNo;
	
	/**
	 * 登录名
	 */
	private String loginId;
	
	/**
	 * 卡id
	 */
	private String userId;
	
    /**
     * 用户名字
     */
	private String name;
	
	/**
	 * 用户ip
	 */
	private String clientIp;
	
	/**
	 * 时间
	 */
	private String actionDt;
	
	/**
	 * 创建人
	 */
	private int createUser;
	
	/**
	 * 创建时间
	 */
	private Date createDate;

	@ExcelDataMapper(title = "登录名", order = 1)
	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@ExcelDataMapper(title = "时间", order = 2)
	public String getActionDt() {
		return this.actionDt;
	}

	public void setActionDt(String actionDt) {
		this.actionDt = actionDt;
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

	@ExcelDataMapper(title = "用户名字", order = 5)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ExcelDataMapper(title = "用户IP", order = 7)
	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
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
