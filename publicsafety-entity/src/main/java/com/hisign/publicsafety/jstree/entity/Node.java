package com.hisign.publicsafety.jstree.entity;

import java.util.List;

/**
 * jstree控件对应实体类
 * @author chailiangzhi
 * @date 2015-9-10 
 */
public class Node {

	/**
	 * id
	 */
	private String id;
	
	/**
	 * 文本说明
	 */
	private String text;
	
	/**
	 * 图片
	 */
	private String icon;
	
	/**
	 * 状态
	 */
	private State state;
	
	/**
	 * 子节点集合
	 */
	private List<Node> children;
	
	/**
	 * li 属性
	 */
	private LiAttr li_attr;
	
	/**
	 * a 熟悉
	 */
	private Aattr a_attr;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public LiAttr getLi_attr() {
		return li_attr;
	}

	public void setLi_attr(LiAttr li_attr) {
		this.li_attr = li_attr;
	}

	public Aattr getA_attr() {
		return a_attr;
	}

	public void setA_attr(Aattr a_attr) {
		this.a_attr = a_attr;
	}

}