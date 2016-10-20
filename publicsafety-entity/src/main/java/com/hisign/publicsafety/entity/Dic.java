package com.hisign.publicsafety.entity;

import com.hisign.publicsafety.base.entity.BaseEntity;

/**
 * 字典实体类
 * @author zhao
 */
@SuppressWarnings("serial")
public class Dic extends BaseEntity implements java.io.Serializable,Comparable<Dic> {
	
	/**
	 * 唯一主键
	 */
	private Integer id; 
	
	/**
	 * 字典类型ID
	 */
	private Integer dicTypeId;
	
	/**
	 * 字典键值
	 */
	private String dicKey;
	
	/**
	 * 字典名
	 */
	private String dicName;
	
	/**
	 * 父节点字典值
	 */
	private String dicParentKey;
	
	/**
	 * 字典类型名
	 */
	private String dicTypeName;
	
	/**
	 * 字典类型key
	 */
	private String dicTypeKey;
	
	/**
	 * 说明
	 */
	private String description;
	
	/**
	 * 备用字段
	 */
	private String rev1; 
	
	/**
	 * 字典级别
	 */
	private String dicLevel; 
	
	/**
	 * 启用状态 1启用 0 停用
	 */
	private String status;
	
	public String getRev1() {
		return rev1;
	}
	public void setRev1(String rev1) {
		this.rev1 = rev1;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDicTypeId() {
		return dicTypeId;
	}
	public void setDicTypeId(Integer dicTypeId) {
		this.dicTypeId = dicTypeId;
	}
	public String getDicKey() {
		return dicKey;
	}
	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}
	public String getDicName() {
		return dicName;
	}
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	
	/**
	 * @return the dicParentKey
	 */
	public String getDicParentKey() {
		return dicParentKey;
	}
	/**
	 * @param dicParentKey the dicParentKey to set
	 */
	public void setDicParentKey(String dicParentKey) {
		this.dicParentKey = dicParentKey;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDicTypeName() {
		return dicTypeName;
	}
	public void setDicTypeName(String dicTypeName) {
		this.dicTypeName = dicTypeName;
	}
	public String getDicTypeKey() {
		return dicTypeKey;
	}
	public void setDicTypeKey(String dicTypeKey) {
		this.dicTypeKey = dicTypeKey;
	}
	public String getDicLevel() {
		return dicLevel;
	}
	public void setDicLevel(String dicLevel) {
		this.dicLevel = dicLevel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int compareTo(Dic o) {
		if (this.id>o.getId()){
			return 1;
		} else if(this.id<o.getId()){
			return -1;
		}
		return 0;
	}
}
