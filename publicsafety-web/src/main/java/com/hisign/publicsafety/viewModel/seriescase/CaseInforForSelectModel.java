package com.hisign.publicsafety.viewModel.seriescase;

import java.io.Serializable;

/**
 * 待选择案件信息
 * @author fankai
 * @date   2015-09-11
 */
public class CaseInforForSelectModel implements Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -4696481359266708338L;
	
	/**
	 * 序号
	 */
	private String no;

	/**
	 * 案件id
	 */
	private String caseId;
	
	/**
	 * 编号 委托编号+案件编号
	 */
	private String caseNo;
	
	/**
	 * 委托编号
	 */
	private String entrustNo;
	
	/**
	 * 案件名称
	 */
	private String caseName;
	
	/**
	 * 案件类型
	 */
	private String caseType;
	
	/**
	 *  线索信息
	 */
	private String seriesClueList;
	
	/**
	 * 串并线索类型
	 */
	private String seriesTypes;
	
	/**
	 * 串并状态
	 */
	private String seriesStatus;
	
	/**
	 * 是否串并中文
	 */
	private String seriesStatusCn;
	
	/**
	 * 区划
	 * @return
	 */
	private String area;
	

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
    
    
	public String getSeriesTypes() {
		return seriesTypes;
	}

	public void setSeriesTypes(String seriesTypes) {
		this.seriesTypes = seriesTypes;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getCaseName() {
		return caseName;
	}

	public String getSeriesStatusCn() {
		return seriesStatusCn;
	}

	public void setSeriesStatusCn(String seriesStatusCn) {
		this.seriesStatusCn = seriesStatusCn;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getSeriesClueList() {
		return seriesClueList;
	}

	public void setSeriesClueList(String seriesClueList) {
		this.seriesClueList = seriesClueList;
	}

	public String getSeriesStatus() {
		return seriesStatus;
	}

	public void setSeriesStatus(String seriesStatus) {
		this.seriesStatus = seriesStatus;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getEntrustNo() {
		return entrustNo;
	}

	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}
	
	
}
