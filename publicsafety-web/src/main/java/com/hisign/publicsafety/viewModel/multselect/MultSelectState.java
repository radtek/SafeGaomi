package com.hisign.publicsafety.viewModel.multselect;

/**
 * 
 * @author fankai
 *
 */
public class MultSelectState {

	
	private boolean checked=false;
	
	private boolean disabled=false;
	
	private boolean expanded=true;
	
	private boolean selected=false;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
