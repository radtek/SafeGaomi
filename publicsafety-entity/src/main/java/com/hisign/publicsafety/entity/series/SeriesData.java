/**
 * 
 */
package com.hisign.publicsafety.entity.series;

import java.io.Serializable;
import java.util.List;

import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.entity.SeriesCaseDetail;
import com.hisign.publicsafety.entity.SeriesCaseInfo;

/**
 * @author chailiangzhi
 * @date 2015-9-14
 * 串并案信息DTO
 */
public class SeriesData extends BaseEntity implements Serializable {
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 串并案编号
	 */
	private String seriesCaseNo;
	/**
	 * 串并案基本信息
	 */
	private SeriesCaseInfo seriesCaseInfo;
	/**
	 * 案件数量
	 */
	private int caseCount;
	/**
	 * 串并案详情表
	 */
	private List<SeriesCaseDetail> seriesCaseDetails;
	/**
	 * 串并案详情(含委托信息和案件信息)
	 */
	private List<CaseInfoInSeries> caseInfos;

	public String getSeriesCaseNo() {
		return seriesCaseNo;
	}

	public void setSeriesCaseNo(String seriesCaseNo) {
		this.seriesCaseNo = seriesCaseNo;
	}

	/**
	 * @return the seriesCaseInfo
	 */
	public SeriesCaseInfo getSeriesCaseInfo() {
		return seriesCaseInfo;
	}

	/**
	 * @param seriesCaseInfo the seriesCaseInfo to set
	 */
	public void setSeriesCaseInfo(SeriesCaseInfo seriesCaseInfo) {
		this.seriesCaseInfo = seriesCaseInfo;
	}

	/**
	 * @return the caseCount
	 */
	public int getCaseCount() {
		return caseCount;
	}

	/**
	 * @param caseCount the caseCount to set
	 */
	public void setCaseCount(int caseCount) {
		this.caseCount = caseCount;
	}

	/**
	 * @return the seriesCaseDetails
	 */
	public List<SeriesCaseDetail> getSeriesCaseDetails() {
		return seriesCaseDetails;
	}

	/**
	 * @param seriesCaseDetails the seriesCaseDetails to set
	 */
	public void setSeriesCaseDetails(List<SeriesCaseDetail> seriesCaseDetails) {
		this.seriesCaseDetails = seriesCaseDetails;
	}

	public List<CaseInfoInSeries> getCaseInfos() {
		return caseInfos;
	}

	public void setCaseInfos(List<CaseInfoInSeries> caseInfos) {
		this.caseInfos = caseInfos;
	}

}
