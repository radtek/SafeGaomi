/**
 * 
 */
package com.hisign.publicsafety.entity.series;

import java.io.Serializable;
import java.util.List;

import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.entity.EntrustBaseInfo;
import com.hisign.publicsafety.entity.EntrustCaseInfo;
import com.hisign.publicsafety.entity.SeriesCaseAlipay;
import com.hisign.publicsafety.entity.SeriesCaseBankcard;
import com.hisign.publicsafety.entity.SeriesCaseCertificate;
import com.hisign.publicsafety.entity.SeriesCaseTelephone;

/**
 * @author chailiangzhi
 * 我的串并案详情界面对应的案件信息列表实体
 */
public class CaseInfoInSeries extends BaseEntity implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 案件id
	 */
	private String caseId;
	
	/**
	 * 委托基本信息实体
	 */
	private EntrustBaseInfo entrustInfo;

	/**
	 * 委托案件信息实体
	 */
	private EntrustCaseInfo caseInfo;
	
	/**
	 * 串并案依据-支付宝集合
	 */
	private List<SeriesCaseAlipay> clueAlipays;

	/**
	 * 串并案依据-手机号集合
	 */
	private List<SeriesCaseTelephone> clueTelephones;

	/**
	 * 串并案依据-证件号集合
	 */
	private List<SeriesCaseCertificate> clueCertificates;

	/**
	 * 串并案依据-银行卡号集合
	 */
	private List<SeriesCaseBankcard> clueBankcards;

	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public EntrustBaseInfo getEntrustInfo() {
		return entrustInfo;
	}

	public void setEntrustInfo(EntrustBaseInfo entrustInfo) {
		this.entrustInfo = entrustInfo;
	}

	public EntrustCaseInfo getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(EntrustCaseInfo caseInfo) {
		this.caseInfo = caseInfo;
	}

	/**
	 * @return the clueAlipays
	 */
	public List<SeriesCaseAlipay> getClueAlipays() {
		return clueAlipays;
	}

	/**
	 * @param clueAlipays the clueAlipays to set
	 */
	public void setClueAlipays(List<SeriesCaseAlipay> clueAlipays) {
		this.clueAlipays = clueAlipays;
	}

	/**
	 * @return the clueTelephones
	 */
	public List<SeriesCaseTelephone> getClueTelephones() {
		return clueTelephones;
	}

	/**
	 * @param clueTelephones the clueTelephones to set
	 */
	public void setClueTelephones(List<SeriesCaseTelephone> clueTelephones) {
		this.clueTelephones = clueTelephones;
	}

	/**
	 * @return the clueCertificates
	 */
	public List<SeriesCaseCertificate> getClueCertificates() {
		return clueCertificates;
	}

	/**
	 * @param clueCertificates the clueCertificates to set
	 */
	public void setClueCertificates(List<SeriesCaseCertificate> clueCertificates) {
		this.clueCertificates = clueCertificates;
	}

	/**
	 * @return the clueBankcards
	 */
	public List<SeriesCaseBankcard> getClueBankcards() {
		return clueBankcards;
	}

	/**
	 * @param clueBankcards the clueBankcards to set
	 */
	public void setClueBankcards(List<SeriesCaseBankcard> clueBankcards) {
		this.clueBankcards = clueBankcards;
	}

}
