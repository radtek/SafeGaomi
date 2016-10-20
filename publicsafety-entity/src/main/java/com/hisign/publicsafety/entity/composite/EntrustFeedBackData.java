package com.hisign.publicsafety.entity.composite;

import java.io.Serializable;
import java.util.List;
import com.hisign.publicsafety.entity.AliAccountDetail;
import com.hisign.publicsafety.entity.AliLoginHistory;
import com.hisign.publicsafety.entity.AliRegisterInfo;
import com.hisign.publicsafety.entity.AliTransactionRecords;
import com.hisign.publicsafety.entity.AliTransferDetail;

/**
 * @author chailiangzhi
 * @date 2015-8-10
 * 委托反馈DTO
 */
public class EntrustFeedBackData implements Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  委托编号
	 */
	private String entrustNo;
	
	/**
	 * 阿里反馈注册信息集合
	 */
	private List<AliRegisterInfo> aliRegistInfos;
	
	/**
	 * 阿里反馈登录历史记录集合
	 */
	private List<AliLoginHistory> aliLoginLogs;
	
	/**
	 * 阿里反馈转账明细集合
	 */
	private List<AliTransactionRecords> aliTransactionRecords;
	
	/**
	 * 阿里反馈阿里账户明细集合
	 */
	private List<AliAccountDetail> aliAccountLogs;
	
	/**
	 * 阿里反馈阿里交易记录集合
	 */
	private List<AliTransferDetail> aliTransferLogs;
	
	
	/**
	 * @return the entrustNo
	 */
	public String getEntrustNo() {
		return entrustNo;
	}
	/**
	 * @param entrustNo the entrustNo to set
	 */
	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}
	/**
	 * @return the aliRegistInfos
	 */
	public List<AliRegisterInfo> getAliRegistInfos() {
		return aliRegistInfos;
	}
	/**
	 * @param aliRegistInfos the aliRegistInfos to set
	 */
	public void setAliRegistInfos(List<AliRegisterInfo> aliRegistInfos) {
		this.aliRegistInfos = aliRegistInfos;
	}
	/**
	 * @return the aliLoginLogs
	 */
	public List<AliLoginHistory> getAliLoginLogs() {
		return aliLoginLogs;
	}
	/**
	 * @param aliLoginLogs the aliLoginLogs to set
	 */
	public void setAliLoginLogs(List<AliLoginHistory> aliLoginLogs) {
		this.aliLoginLogs = aliLoginLogs;
	}
	/**
	 * @return the aliTransactionRecords
	 */
	public List<AliTransactionRecords> getAliTransactionRecords() {
		return aliTransactionRecords;
	}
	/**
	 * @param aliTransactionRecords the aliTransactionRecords to set
	 */
	public void setAliTransactionRecords(List<AliTransactionRecords> aliTransactionRecords) {
		this.aliTransactionRecords = aliTransactionRecords;
	}
	/**
	 * @return the aliAccountLogs
	 */
	public List<AliAccountDetail> getAliAccountLogs() {
		return aliAccountLogs;
	}
	/**
	 * @param aliAccountLogs the aliAccountLogs to set
	 */
	public void setAliAccountLogs(List<AliAccountDetail> aliAccountLogs) {
		this.aliAccountLogs = aliAccountLogs;
	}
	/**
	 * @return the aliTransferLogs
	 */
	public List<AliTransferDetail> getAliTransferLogs() {
		return aliTransferLogs;
	}
	/**
	 * @param aliTransferLogs the aliTransferLogs to set
	 */
	public void setAliTransferLogs(List<AliTransferDetail> aliTransferLogs) {
		this.aliTransferLogs = aliTransferLogs;
	}

}
