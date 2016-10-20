/**
 * 多级下拉选择控件模型
 */
package com.hisign.publicsafety.viewModel.multselect;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * 多级下拉选择控件模型
 * @author  fankai
 * @date    2015-10-12
 * @version 1.0
 */
public class MultSelectDataModel {
	
  
	 /**
	  * 数据id
	  */
	 private String id;
	 
	 /**
	  *数据名称
	  */
	 private String text;
	 
	 private MultSelectState state=new MultSelectState();
	 
	 public MultSelectState getState() {
		return state;
	}

	public void setState(MultSelectState state) {
		this.state = state;
	}

	/**
	  * 子节点集合
	  */
	 private List<MultSelectDataModel> nodes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<MultSelectDataModel> getNodes() {
		return nodes;
	}

	public void setNodes(List<MultSelectDataModel> nodes) {
		this.nodes = nodes;
	}

	public static void main(String args[]){
		List<MultSelectDataModel> list=new ArrayList<MultSelectDataModel>();
		MultSelectDataModel model=new MultSelectDataModel();
		model.setId("1");
		model.setText("北京市");
		List<MultSelectDataModel> nodes=new ArrayList<MultSelectDataModel>();
		MultSelectDataModel model1=new MultSelectDataModel();
		model1.setId("11");
		model1.setText("东城区");
		nodes.add(model1);
		MultSelectDataModel model2=new MultSelectDataModel();
		model2.setId("12");
		model2.setText("西城区");
		nodes.add(model2);
		model.setNodes(nodes);
		List<MultSelectDataModel> nodes1=new ArrayList<MultSelectDataModel>();
		MultSelectDataModel model11=new MultSelectDataModel();
		model11.setId("121");
		model11.setText("西直门派出所");
		nodes1.add(model11);
		model2.setNodes(nodes1);
		list.add(model);
		Gson gson = new Gson();
		String json=gson.toJson(list);
		System.out.println(json);
	}
}
