package com.hisign.publicsafety.jstree.entity;

/**
 * jstree控件对应实体类
 * @author chailiangzhi
 * @date 2015-9-10 
 */
public class State {

	/**
	 * 是否选中
	 */
	private boolean selected;

	/**
	 * 是否可用
	 */
	private boolean disabled;

	/**
	 * 是否展开
	 */
	private boolean opened;

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean getSelected() {
		return this.selected;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
