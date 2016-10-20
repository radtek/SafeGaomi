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
 * 串并案可选案件列表DTO
 * 
 * @author chailiangzhi
 * @date 2015-9-10
 */
public class CaseDataForSelect extends BaseEntity implements Serializable {

	/**
	 * 序列化id
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
	 * 委托案件信息
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
	 * @return the entrustInfo
	 */
	public EntrustBaseInfo getEntrustInfo() {
		return entrustInfo;
	}

	/**
	 * @param entrustInfo the entrustInfo to set
	 */
	public void setEntrustInfo(EntrustBaseInfo entrustInfo) {
		this.entrustInfo = entrustInfo;
	}

	public EntrustCaseInfo getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(EntrustCaseInfo caseInfo) {
		this.caseInfo = caseInfo;
	}

	public List<SeriesCaseAlipay> getClueAlipays() {
		return clueAlipays;
	}

	public void setClueAlipays(List<SeriesCaseAlipay> clueAlipays) {
		this.clueAlipays = clueAlipays;
	}

	public List<SeriesCaseTelephone> getClueTelephones() {
		return clueTelephones;
	}

	public void setClueTelephones(List<SeriesCaseTelephone> clueTelephones) {
		this.clueTelephones = clueTelephones;
	}

	public List<SeriesCaseCertificate> getClueCertificates() {
		return clueCertificates;
	}

	public void setClueCertificates(List<SeriesCaseCertificate> clueCertificates) {
		this.clueCertificates = clueCertificates;
	}

	public List<SeriesCaseBankcard> getClueBankcards() {
		return clueBankcards;
	}

	public void setClueBankcards(List<SeriesCaseBankcard> clueBankcards) {
		this.clueBankcards = clueBankcards;
	}

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

}
