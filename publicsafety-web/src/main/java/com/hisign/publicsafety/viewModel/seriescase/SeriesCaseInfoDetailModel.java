package com.hisign.publicsafety.viewModel.seriescase;
/**
 * 串并案查看案件详情模型
 * @author fankai
 * @date   2015-09-25
 */
public class SeriesCaseInfoDetailModel {
   
	/**
	 * 序号
	 */
	private String no;
	
	/**
	 * 编号 委托编号+案件编号
	 */
	private String caseNo;
	
	
	/**
	 * 案件编号
	 */
	private String caseId;
	
	/**
	 * 串并状态
	 */
	private String seriesStatus;
	
	/**
	 * 串并状态中文
	 */
	private String seriesStatusCn;
	
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
	 * 所属区划
	 */
	private String area;
	
	/**
	 * 是否为待串并案件，已经加入串并，但是未保存的中间状态
	 */
	private String isAddCache;


	public String getIsAddCache() {
		return isAddCache;
	}


	public void setIsAddCache(String isAddCache) {
		this.isAddCache = isAddCache;
	}


	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getCaseNo() {
		return caseNo;
	}


	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}


	public String getEntrustNo() {
		return entrustNo;
	}


	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}


	public String getCaseName() {
		return caseName;
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


	public String getSeriesStatus() {
		return seriesStatus;
	}


	public void setSeriesStatus(String seriesStatus) {
		this.seriesStatus = seriesStatus;
	}


	public String getSeriesStatusCn() {
		return seriesStatusCn;
	}


	public void setSeriesStatusCn(String seriesStatusCn) {
		this.seriesStatusCn = seriesStatusCn;
	}
	
}
