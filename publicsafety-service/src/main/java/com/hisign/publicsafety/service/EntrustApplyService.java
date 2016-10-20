/**
 * 
 */
package com.hisign.publicsafety.service;

import java.util.Map;

import com.hisign.publicsafety.base.BaseService;
import com.hisign.publicsafety.entity.EntrustBaseInfo;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.pulgin.mybatis.plugin.PageView;

/**
 * 委托申请
 * @author chailiangzhi
 * @date 2015-8-10
 * 
 */
public interface EntrustApplyService extends BaseService<EntrustApplyData> {
	/**
	 * 委托申请保存(暂存或提交由前台参数决定)
	 * @param entrustApplyData
	 * @return 保存是否成功
	 * @throws Exception
	 */
	public boolean save(EntrustApplyData entrustApplyData) throws Exception;

	/**
	 * 分页查询,我的委托和委托审核界面使用
	 * @param paraMap
	 * paraMap.createUser 委托人
	 * paraMap.entrustNo 委托号
	 * paraMap.caseName 案件名称(模糊查询)
	 * paraMap.caseType 案件类型
	 * paraMap.processState 案件状态
	 * paraMap.unit 单位
	 * paraMap.entrustDateBegin 起始时间
	 * paraMap.entrustDateEnd 截止时间
	 * @return
	 * @throws Exception
	 */
	public DataResponse<EntrustApplyData> query(PageView pageView, Map<String, Object> paraMap) throws Exception;

	/**
	 * 根据委托号查询委托申请信息
	 * @param entrustNo 委托号
	 * @return 
	 * @throws Exception
	 */
	public EntrustApplyData getByEntrustNo(String entrustNo) throws Exception;
	
	/**
	 * 根据委托序号(第一次委托的ID)查询最后一次的委托申请信息
	 * @param para需包含entrustSer和createUser字段
	 * @return 
	 * @throws Exception
	 */
	public EntrustApplyData getLastByEntrustSer(EntrustBaseInfo para) throws Exception;
	
	/**
	 * 查询用户指定时间之前已反馈状态的委托数量
	 * @param time
	 * @param createUser
	 * @return
	 * @throws Exception
	 */
	public int getUnfinishCount(long time, int createUser) throws Exception;
}
