package com.hisign.publicsafety.entity;

import java.io.Serializable;

/**
 * @author chailiangzhi
 * @date 2015-8-11 序列实体
 */
public class SeqId implements Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -111255002006591384L;

	/**
	 * 主键id
	 */
	private int id;

	/**
	 * 序列值
	 */
	private String val;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the val
	 */
	public String getVal() {
		return val;
	}

	/**
	 * @param val
	 *            the val to set
	 */
	public void setVal(String val) {
		this.val = val;
	}

}
