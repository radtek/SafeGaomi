package com.hisign.publicsafety.entity;

import java.util.ArrayList;
import java.util.List;

import com.hisign.publicsafety.base.entity.BaseEntity;

/**
 * 模块实体类
 * @author hotdog
 * 下午2:21:24
 */
@SuppressWarnings("serial")
public class Resources extends BaseEntity implements java.io.Serializable {

	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 模块名
	 */
	private String name;
	
	/**
	 * 父模块id
	 */
	private Integer parentId; 
	
	/**
	 * 父模块名
	 */
	private String parentName;
	
	/**
	 * 模块键值 （唯一）
	 */
	private String resKey;
	
	/**
	 * 模块URL．例如：/videoType/query　　不需要项目名和http://xxx:8080
	 */
	private String resUrl;
	
	/**
	 * 模块级别
	 */
	private Integer level;
	
	/**
	 * 类型，目录　菜单  按扭．．在spring security3安全权限中，涉及精确到按扭控制
	 */
	private String type;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 下级子模块List
	 */
	private List<Resources> children = new ArrayList<Resources>();

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getResKey() {
		return resKey;
	}

	public void setResKey(String resKey) {
		this.resKey = resKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<Resources> getChildren() {
		return children;
	}

	public void setChildren(List<Resources> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", parentId=" + parentId + ", parentName=" + parentName + ", resKey=" + resKey + ", resUrl=" + resUrl + ", level=" + level + ", type=" + type + ", description=" + description + ", children=" + children + "]";
	}

}
