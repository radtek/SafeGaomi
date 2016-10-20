package com.hisign.publicsafety.entity.composite;

import java.io.Serializable;
import java.util.List;
import com.hisign.publicsafety.base.entity.BaseEntity;
import com.hisign.publicsafety.entity.EntrusAttachment;
import com.hisign.publicsafety.entity.EntrusInvestigationInfo;
import com.hisign.publicsafety.entity.EntrusInvestigationTypeinfo;
import com.hisign.publicsafety.entity.EntrustBaseInfo;
import com.hisign.publicsafety.entity.EntrustCaseInfo;
import com.hisign.publicsafety.entity.EntrustCheckList;
import com.hisign.publicsafety.entity.EntrustSuspectInfo;
import com.hisign.publicsafety.entity.EntrustVictimInfo;

/**
 * @author chailiangzhi
 * @date 2015-8-10
 * 委托申请DTO
 */
public class EntrustApplyData extends BaseEntity implements Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  1 新增 2 修改 3 再次委托
	 */
	private String actiontype;
	
	/**
	 * 提交类型 save 保存 submit 提交
	 */
	private String typeinfo;
	
	/**
	 * 上传的文件列表
	 */
	private String fileList;
	
	/**
	 * 上传的文件名称集合
	 */
	private String uploadfileName;
	
	/**
	 * 委托编号
	 */
	private String entrustNo;
	
	/**
	 * 委托基本信息实体
	 */
	private EntrustBaseInfo entrustInfo;
	
	/**
	 * 委托附件文书集合
	 */
	private List<EntrusAttachment> attachments;
	
	/**
	 * 委托案件信息实体
	 */
	private EntrustCaseInfo caseInfo;
	
	/**
	 * 受害人信息集合
	 */
	private List<EntrustVictimInfo> victimInfo;
	
	/**
	 * 嫌疑人信息集合
	 */
	private List<EntrustSuspectInfo> suspectInfo;
	
	/**
	 * 协查类型分类集合
	 */
	private List<EntrusInvestigationTypeinfo> investigateInfo;
	
	/**
	 * 协查信息集合
	 */
	private List<EntrusInvestigationInfo> queryInfo;
	
	/**
	 * 审核信息集合
	 */
	private List<EntrustCheckList> checkList;
	
	
	/**
	 * @return the entrustInfo
	 */
	public EntrustBaseInfo getEntrustInfo() {
		return entrustInfo;
	}
	
	public String getTypeinfo() {
		return typeinfo;
	}

	public void setTypeinfo(String typeinfo) {
		this.typeinfo = typeinfo;
	}

	public String getFileList() {
		return fileList;
	}

	public void setFileList(String fileList) {
		this.fileList = fileList;
	}

	public String getUploadfileName() {
		return uploadfileName;
	}

	public void setUploadfileName(String uploadfileName) {
		this.uploadfileName = uploadfileName;
	}

	/**
	 * @param entrustInfo the entrustInfo to set
	 */
	public void setEntrustInfo(EntrustBaseInfo entrustInfo) {
		this.entrustInfo = entrustInfo;
	}
	
	/**
	 * @return the attachments
	 */
	public List<EntrusAttachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<EntrusAttachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return the caseInfo
	 */
	public EntrustCaseInfo getCaseInfo() {
		return caseInfo;
	}
	/**
	 * @param caseInfo the caseInfo to set
	 */
	public void setCaseInfo(EntrustCaseInfo caseInfo) {
		this.caseInfo = caseInfo;
	}
	/**
	 * @return the victimInfo
	 */
	public List<EntrustVictimInfo> getVictimInfo() {
		return victimInfo;
	}
	/**
	 * @param victimInfo the victimInfo to set
	 */
	public void setVictimInfo(List<EntrustVictimInfo> victimInfo) {
		this.victimInfo = victimInfo;
	}
	/**
	 * @return the suspectInfo
	 */
	public List<EntrustSuspectInfo> getSuspectInfo() {
		return suspectInfo;
	}
	/**
	 * @param suspectInfo the suspectInfo to set
	 */
	public void setSuspectInfo(List<EntrustSuspectInfo> suspectInfo) {
		this.suspectInfo = suspectInfo;
	}
	/**
	 * @return the investigateInfo
	 */
	public List<EntrusInvestigationTypeinfo> getInvestigateInfo() {
		return investigateInfo;
	}
	/**
	 * @param investigateInfo the investigateInfo to set
	 */
	public void setInvestigateInfo(List<EntrusInvestigationTypeinfo> investigateInfo) {
		this.investigateInfo = investigateInfo;
	}
	/**
	 * @return the queryInfo
	 */
	public List<EntrusInvestigationInfo> getQueryInfo() {
		return queryInfo;
	}
	/**
	 * @param queryInfo the queryInfo to set
	 */
	public void setQueryInfo(List<EntrusInvestigationInfo> queryInfo) {
		this.queryInfo = queryInfo;
	}

	/**
	 * @return the checkList
	 */
	public List<EntrustCheckList> getCheckList() {
		return checkList;
	}

	/**
	 * @param checkList the checkList to set
	 */
	public void setCheckList(List<EntrustCheckList> checkList) {
		this.checkList = checkList;
	}

	public String getEntrustNo() {
		return entrustNo;
	}

	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}

	public String getActiontype() {
		return actiontype;
	}

	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	
	
}
