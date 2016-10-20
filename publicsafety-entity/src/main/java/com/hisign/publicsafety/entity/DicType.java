package com.hisign.publicsafety.entity;

/**
 * 字典类型
 * @author lanyuan
 * Email：mmm333zzz520@163.com
 * date：2014-4-8
 */
@SuppressWarnings("serial")
public class DicType implements java.io.Serializable{
	
	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 字典类型键值
	 */
	private String dicTypeKey;
	
	/**
	 * 字典类型名
	 */
	private String dicTypeName;
	
	/**
	 * 说明
	 */
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDicTypeKey() {
		return dicTypeKey;
	}
	public void setDicTypeKey(String dicTypeKey) {
		this.dicTypeKey = dicTypeKey;
	}
	public String getDicTypeName() {
		return dicTypeName;
	}
	public void setDicTypeName(String dicTypeName) {
		this.dicTypeName = dicTypeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
