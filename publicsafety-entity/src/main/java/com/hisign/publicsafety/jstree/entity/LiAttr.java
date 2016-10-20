/**
 * 
 */
package com.hisign.publicsafety.jstree.entity;

import java.io.Serializable;

/**
 * jstree控件对应实体类
 * @author chailiangzhi
 * @date 2015-8-19 
 */
public class LiAttr implements Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -2520594721664719965L;

	/**
	 * 资源id
	 */
	private String res_id;

	/**
	 * 资源
	 */
	private String res_key;

	public String getRes_id() {
		return res_id;
	}

	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}

	public String getRes_key() {
		return res_key;
	}

	public void setRes_key(String res_key) {
		this.res_key = res_key;
	}
}
