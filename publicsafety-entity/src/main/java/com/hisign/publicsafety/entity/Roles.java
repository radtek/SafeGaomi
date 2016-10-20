package com.hisign.publicsafety.entity;

import java.util.HashSet;
import java.util.Set;

import com.hisign.publicsafety.base.entity.BaseEntity;

/**
 * 角色实体类
 * @author hotdog
 * 上午10:47:10
 */
@SuppressWarnings("serial")
public class Roles extends BaseEntity implements java.io.Serializable {

	/**
	 * 唯一主键
	 */
	private Integer id;
	
	/**
	 * 是否禁用角色　1　表示禁用　2　表示不禁用
	 */
	private String enable;
	
	/**
	 * 角色名
	 */
	private String name; 
	
	/**
	 * 唯一,新增时,需要判断
	 */
	private String roleKey;
	
	/**
	 * 角色描述
	 */
	private String description;

	/**
	 * 权限id
	 */
	private String resourcesIds; 
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public String getResourcesIds() {
		return resourcesIds;
	}

	public void setResourcesIds(String resourcesIds) {
		this.resourcesIds = resourcesIds;
	}
}
