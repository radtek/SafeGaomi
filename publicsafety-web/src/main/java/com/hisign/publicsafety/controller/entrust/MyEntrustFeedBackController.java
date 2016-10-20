package com.hisign.publicsafety.controller.entrust;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.util.StringUtils;
import com.hisign.publicsafety.common.api.CacheSessionService;
import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.AliAccountDetail;
import com.hisign.publicsafety.entity.AliLoginHistory;
import com.hisign.publicsafety.entity.AliRegisterInfo;
import com.hisign.publicsafety.entity.AliTransactionRecords;
import com.hisign.publicsafety.entity.AliTransferDetail;
import com.hisign.publicsafety.entity.Dic;
import com.hisign.publicsafety.entity.EntrusInvestigationInfo;
import com.hisign.publicsafety.entity.EntrustSatisfactoryResults;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.service.AliAccountService;
import com.hisign.publicsafety.service.AliLoginService;
import com.hisign.publicsafety.service.AliRegistService;
import com.hisign.publicsafety.service.AliTransactionService;
import com.hisign.publicsafety.service.AliTransferService;
import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.service.EntrustSatisfactoryService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.Containts;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;
/**
 * 已反馈委托
 * @author fankai
 * @date 2015-8-7
 * 
 */
@Controller
@RequestMapping("/entrust/feedback/")
public class MyEntrustFeedBackController extends BaseController {
	
	/**
	 * 反馈评价服务类
	 */
	@Inject
	private EntrustSatisfactoryService entrustSatisfactoryService;
	
	/**
	 * 生成id服务类
	 */
	@Inject
	private IdService idService;
	
    /**
     * 阿里反馈注册信息服务类
     */
	@Inject
	private AliRegistService aliRegistService;
	
	/**
     * 阿里反馈登录历史服务类
     */
	@Inject
	private AliLoginService aliLoginService;
	
	/**
     * 阿里反馈登录历史服务类
     */
	@Inject
	private AliTransactionService aliTransactionService;
	
	/**
     * 阿里反馈账户明细服务类
     */
	@Inject
	private AliAccountService aliAccountService;
	
	/**
     * 阿里反馈转账明细服务类
     */
	@Inject
	private AliTransferService aliTransferService;
	
	@Inject
	private EntrustApplyService entrustApplyService;
	
	@Inject
	private DicService dicService;
	@Autowired
	private CacheSessionService cacheSessionService;
	
	@RequestMapping("index")
	public String feedBackIndex() throws Exception{
		String csrfToken = CsrfTokenManager.getToken();
		this.getHttpServletRequest().setAttribute("csrf", csrfToken);
		HttpServletRequest request=this.getHttpServletRequest();
		String entrustNo=request.getParameter("entrustNo");
		//获得委托所属阶段 finish 已完成 feedback已反馈
		String process=request.getParameter("process");
		EntrustApplyData data=entrustApplyService.getByEntrustNo(entrustNo);
		//处理反馈评价
		if(!StringUtils.isEmpty(process)){
			//查询委托评价
			Dic dicFkpj = new Dic();
			dicFkpj.setDicTypeKey(Containts.DIC_FKPJ);
			List<Dic> dicFkpjList = dicService.queryAll(dicFkpj);
			//委托为完成状态的时候需要显示委托评价信息
			if(process.equals("finish")){
				 EntrustSatisfactoryResults result=new EntrustSatisfactoryResults();
				 result.setEntrustNo(entrustNo);
				 List<EntrustSatisfactoryResults> slist=this.entrustSatisfactoryService.queryAll(result);
				 if(slist!=null){
					 for(EntrustSatisfactoryResults bean:slist){
						if(bean.getType()==1){
							String name= ControllerUtil.getName(dicFkpjList, bean.getSatisfactoryKey());
							bean.setSatisfactoryKey(name);
							if(bean.getSatisfactoryResult().equals("0")){
								bean.setSatisfactoryResult("否");
							}else{
								bean.setSatisfactoryResult("是");
							}
						}
					 }
				 }
				 request.setAttribute("fklist", slist);
				 
			}else if(process.equals("feedback")){//委托为已反馈时，需要点击完成时，需要填写委托评价信息
				
				request.setAttribute("fkpjlist", dicFkpjList);
			}
		}
		//查询省份字典
		Dic dicProv = new Dic();
		dicProv.setDicTypeKey("province");
		List<Dic> dicListProv = dicService.queryAll(dicProv);
		
		//查询地市字典
		Dic dicCity = new Dic();
		dicCity.setDicTypeKey("city");
		List<Dic> dicListCity = dicService.queryAll(dicCity);
		Dic dicCaseStage = new Dic();
		
		//查询案件办理阶段字典
		dicCaseStage.setDicTypeKey("case_stage");
		List<Dic> CaseStageProv = dicService.queryAll(dicCaseStage);
		Dic dicCaseType = new Dic();
		
		//查询案件类型字典
		dicCaseType.setDicTypeKey("case_type");
		List<Dic> CaseTypeProv = dicService.queryAll(dicCaseType);
		
		//查询协查信息字典
		Dic xcxxCity = new Dic();
		xcxxCity.setDicTypeKey("xcxx");
		List<Dic> xcxxList = dicService.queryAll(xcxxCity);
		
		if(data!=null){
			//查询类型
			StringBuffer qStr=new StringBuffer("");
			List<EntrusInvestigationInfo>qlist=data.getQueryInfo();
			if(qlist!=null && qlist.size()>0){
				Dic dicQueryType = new Dic();
				dicQueryType.setDicTypeKey("query_type");
				List<Dic> dicQueryProv = dicService.queryAll(dicQueryType);
				Map<String,String> qmap=new HashMap<String,String>();
				for(Dic dic:dicQueryProv){
				    qmap.put(dic.getDicKey(), dic.getDicName());
				}
				for(int i=0;i<qlist.size();i++){
					EntrusInvestigationInfo info=qlist.get(i);
					if(i!=qlist.size()-1){
						qStr.append(qmap.get(info.getQueryType())).append(",");
					}else{
						qStr.append(qmap.get(info.getQueryType()));
					}
				}
			}
			request.setAttribute("queryStr", qStr.toString());
			request.setAttribute("entrustData", data);
			request.setAttribute("province", dicListProv);
			request.setAttribute("caseStage", CaseStageProv);
			request.setAttribute("city", dicListCity);
			//request.setAttribute("casetype", CaseTypeProv);
			if(data!=null){
				if(data.getCaseInfo()!=null){
					String caseType=ControllerUtil.findCaseTypeZn(CaseTypeProv, data.getCaseInfo().getCaseType(),"");
					request.setAttribute("pdicName",caseType);
				}
			}
			request.setAttribute("xcxxList", xcxxList);
		}
		request.setAttribute("process", process);
		request.setAttribute("entrustNo", entrustNo);
		return Common.JSP_PATH+"/entrust/entrust_feedback";
	}
	
	/**
	 * 查询阿里反馈注册信息
	 * @param dataRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryAliRegisterInfo")
	public  DataResponse<AliRegisterInfo> queryAliRegisterInfo(DataRequest dataRequest){
	    String entrustNo=this.getHttpServletRequest().getParameter("entrustNo");
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		AliRegisterInfo para=new AliRegisterInfo();
		para.setEntrustNo(entrustNo);
		aliRegistService.query(getPageView(String.valueOf(page),String.valueOf(rows)), para);
		return pageView.getDataResponse();
	}
	
	/**
	 * 查询阿里反馈登录信息
	 * @param dataRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryAliLoginHistory")
	public  DataResponse<AliLoginHistory> queryAliLoginHistory(DataRequest dataRequest){
	    String entrustNo=this.getHttpServletRequest().getParameter("entrustNo");
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		AliLoginHistory para=new AliLoginHistory();
		para.setEntrustNo(entrustNo);
		aliLoginService.query(getPageView(String.valueOf(page),String.valueOf(rows)), para);
		return pageView.getDataResponse();
	}
	
	/**
	 * 查询阿里反馈交易记录
	 * @param dataRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryAliTransactionRecords")
	public  DataResponse<AliTransactionRecords> queryAliTransactionRecords(DataRequest dataRequest){
	    String entrustNo=this.getHttpServletRequest().getParameter("entrustNo");
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		AliTransactionRecords para=new AliTransactionRecords();
		para.setEntrustNo(entrustNo);
		aliTransactionService.query(getPageView(String.valueOf(page),String.valueOf(rows)), para);
		return pageView.getDataResponse();
	}
	
	/**
	 * 查询阿里反馈账户明细
	 * @param dataRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryAliAccountDetail")
	public  DataResponse<AliAccountDetail> queryAliAccountDetail(DataRequest dataRequest){
	    String entrustNo=this.getHttpServletRequest().getParameter("entrustNo");
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		AliAccountDetail para=new AliAccountDetail();
		para.setEntrustNo(entrustNo);
		aliAccountService.query(getPageView(String.valueOf(page),String.valueOf(rows)), para);
		return pageView.getDataResponse();
	}
	
	/**
	 * 查询阿里反馈转账明细
	 * @param dataRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryAliTransferDetail")
	public  DataResponse<AliTransferDetail> queryAliTransferDetail(DataRequest dataRequest){
	    String entrustNo=this.getHttpServletRequest().getParameter("entrustNo");
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		AliTransferDetail para=new AliTransferDetail();
		para.setEntrustNo(entrustNo);
		aliTransferService.query(getPageView(String.valueOf(page),String.valueOf(rows)), para);
		return pageView.getDataResponse();
	}
	
	
	/**
	 * 修改是否共享案件信息和查询结果
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("updateIsShareInfo")
	public String updateIsShareInfo() throws Exception{
		HttpServletRequest request=this.getHttpServletRequest();
		String entrustNo=request.getParameter("entrustNo");
		String isShareCaseinfo=request.getParameter("isShareCaseinfo");
		String type=request.getParameter("type");
		String userId = cacheSessionService.getAttribute("userSessionId").toString();
		if(!StringUtils.isEmpty(entrustNo)){
			EntrustApplyData data=entrustApplyService.getByEntrustNo(entrustNo);
			if(data!=null){
				if(type.equals("1")){
					data.getEntrustInfo().setIsShareCaseinfo(isShareCaseinfo);
				}else{
					data.getEntrustInfo().setIsShareQueryresult(isShareCaseinfo);
				}
				data.getEntrustInfo().setUpdateDate(new Date());
				data.getEntrustInfo().setUpdateUser(Integer.parseInt(userId));
				entrustApplyService.save(data);
			}
		}
		return null;
	}
	
	
	/**
	 * 修改反馈评价信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("updateFgpjInfor")
	public String updateFgpjInfor() throws Exception{
		String userId = cacheSessionService.getAttribute("userSessionId").toString();
		HttpServletRequest request=this.getHttpServletRequest();
		String entrustNo=request.getParameter("entrustNo");
		String memocontent=request.getParameter("memocontent");
		Dic dicFkpj = new Dic();
		dicFkpj.setDicTypeKey(Containts.DIC_FKPJ);
		List<Dic> dicFkpjList = dicService.queryAll(dicFkpj);
		List<EntrustSatisfactoryResults> list=new ArrayList<EntrustSatisfactoryResults>();
		if(!StringUtils.isEmpty(entrustNo)){
			for(Dic dic:dicFkpjList){
				EntrustSatisfactoryResults result=new EntrustSatisfactoryResults();
				result.setId(String.valueOf(this.idService.getId()));
				result.setDeleteFlag("0");
				result.setCreateDate(new Date());
				result.setCreateUser(Integer.parseInt(userId));
				result.setSatisfactoryKey(dic.getDicKey());
				String dicValue=request.getParameter(dic.getDicKey());
				result.setSatisfactoryResult(dicValue);
				result.setType(1);
				result.setEntrustNo(entrustNo);
				list.add(result);
			}
				EntrustSatisfactoryResults result=new EntrustSatisfactoryResults();
				result.setId(String.valueOf(this.idService.getId()));
				result.setDeleteFlag("0");
				result.setCreateDate(new Date());
				result.setCreateUser(Integer.parseInt(userId));
				result.setAdvice(memocontent);
				result.setType(2);
				result.setEntrustNo(entrustNo);
				list.add(result);
				try{
				  this.entrustSatisfactoryService.addBatch(list);
				}catch(Exception e){
					logger.error("提交委托评价反馈出错,"+e.getLocalizedMessage());
					return "0";
				}
		}
		return "1";
	}
	public AliRegistService getAliRegistService() {
		return aliRegistService;
	}

	public void setAliRegistService(AliRegistService aliRegistService) {
		this.aliRegistService = aliRegistService;
	}

	public AliLoginService getAliLoginService() {
		return aliLoginService;
	}

	public void setAliLoginService(AliLoginService aliLoginService) {
		this.aliLoginService = aliLoginService;
	}

	public AliTransactionService getAliTransactionService() {
		return aliTransactionService;
	}

	public void setAliTransactionService(AliTransactionService aliTransactionService) {
		this.aliTransactionService = aliTransactionService;
	}

	public AliAccountService getAliAccountService() {
		return aliAccountService;
	}

	public void setAliAccountService(AliAccountService aliAccountService) {
		this.aliAccountService = aliAccountService;
	}

	public AliTransferService getAliTransferService() {
		return aliTransferService;
	}

	public void setAliTransferService(AliTransferService aliTransferService) {
		this.aliTransferService = aliTransferService;
	}

	public EntrustApplyService getEntrustApplyService() {
		return entrustApplyService;
	}

	public void setEntrustApplyService(EntrustApplyService entrustApplyService) {
		this.entrustApplyService = entrustApplyService;
	}

	public DicService getDicService() {
		return dicService;
	}

	public void setDicService(DicService dicService) {
		this.dicService = dicService;
	}

	public IdService getIdService() {
		return idService;
	}

	public void setIdService(IdService idService) {
		this.idService = idService;
	}
    
}
