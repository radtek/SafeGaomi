package com.hisign.publicsafety.controller.entrust;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.util.StringUtils;
import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.AliAccountDetail;
import com.hisign.publicsafety.entity.AliLoginHistory;
import com.hisign.publicsafety.entity.AliRegisterInfo;
import com.hisign.publicsafety.entity.AliTransactionRecords;
import com.hisign.publicsafety.entity.AliTransferDetail;
import com.hisign.publicsafety.entity.Dic;
import com.hisign.publicsafety.entity.EntrusAttachment;
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
@RequestMapping("/entrust/view/")
public class EntrustViewController extends BaseController {
	
	/**
	 * 反馈评价服务类
	 */
	@Inject
	private EntrustSatisfactoryService entrustSatisfactoryService;
	
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
	@RequestMapping("index")
	public String feedBackIndex() throws Exception{
		String csrfToken = CsrfTokenManager.getToken();
		this.getHttpServletRequest().setAttribute("csrf", csrfToken);
		HttpServletRequest request=this.getHttpServletRequest();
		String entrustNo=request.getParameter("entrustNo");
		request.setAttribute("entrustNo", entrustNo);
		//查询委托信息
		EntrustApplyData data=entrustApplyService.getByEntrustNo(entrustNo);
		String process=data.getEntrustInfo().getProcessState();
		//查询流程状态信息
		request.setAttribute("process", process);
		//初始化字典表信息
		//省份字典
		Dic dicProv = new Dic();
		dicProv.setDicTypeKey("province");
		List<Dic> dicListProv = dicService.queryAll(dicProv);
		//地市字典
		Dic dicCity = new Dic();
		dicCity.setDicTypeKey("city");
		List<Dic> dicListCity = dicService.queryAll(dicCity);
		//案件处理阶段
		Dic dicCaseStage = new Dic();
		dicCaseStage.setDicTypeKey("case_stage");
		List<Dic> CaseStageProv = dicService.queryAll(dicCaseStage);
		//案件类型
		Dic dicCaseType = new Dic();
		dicCaseType.setDicTypeKey("case_type");
		List<Dic> CaseTypeProv = dicService.queryAll(dicCaseType);
		//协查信息
		Dic xcxxCity = new Dic();
		xcxxCity.setDicTypeKey("xcxx");
		List<Dic> xcxxList = dicService.queryAll(xcxxCity);
		//查询类型
		if(data!=null){
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
			//查询委托评价
			Dic dicFkpj = new Dic();
			dicFkpj.setDicTypeKey(Containts.DIC_FKPJ);
			List<Dic> dicFkpjList = dicService.queryAll(dicFkpj);
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
			request.setAttribute("queryStr", qStr.toString());
			request.setAttribute("entrustData", data);
			if(data.getAttachments()!=null){
				List<EntrusAttachment> mlist=data.getAttachments();
				int fsize=mlist.size();
				if(fsize>0){
					request.setAttribute("fsize","1");
					for(EntrusAttachment ment:mlist){
						if(ment.getFileContent()==null){
							ment.setRev1("0");
						}
					}
				}
			}
			
			request.setAttribute("province", dicListProv);
			request.setAttribute("caseStage", CaseStageProv);
			request.setAttribute("city", dicListCity);
			//request.setAttribute("casetype", CaseTypeProv);
			if(data!=null){
				if(data.getCaseInfo()!=null){
					String caseType=this.findCaseTypeZn(CaseTypeProv, data.getCaseInfo().getCaseType(),"");
					request.setAttribute("pdicName",caseType);
				}
			}
			request.setAttribute("xcxxList", xcxxList);
			request.setAttribute("showMemu","0");
		}
		return Common.JSP_PATH+"/entrust/entrust_view";
	}
	
	/**
	 * 拼接当前字典值和上一级字典值
	 * @param dlist 字典集合
	 * @param dickey 字典key
	 * @return
	 */
	private String findCaseTypeZn(List<Dic> dlist,String dickey,String caseType){
		String curCaseType="";
		String pcode="";
		if(dlist!=null){
			for(Dic dic:dlist){
				if(dickey.equals(dic.getDicKey())){
					pcode=dic.getDicParentKey();
					curCaseType=dic.getDicName();
					if(!StringUtils.isEmpty(pcode)){
					   caseType="-"+curCaseType+caseType;
					}else{
					   caseType=curCaseType+caseType;
					}
					
					if(!StringUtils.isEmpty(pcode)){
						caseType=this.findCaseTypeZn(dlist, pcode, caseType);
					}
					break;
				}
			}
		}
		return caseType;
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
    
}
