package com.hisign.publicsafety.controller.entrust;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import com.hisign.publicsafety.common.api.CacheSessionService;
import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.Dic;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.DateUtils;
import com.hisign.publicsafety.util.StringUtil;
import com.hisign.publicsafety.viewModel.multselect.MultSelectDataModel;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;
/**
 * @author fankai
 * @date 2015-8-7
 * 我的委托查询
 */
@Controller
@RequestMapping("/entrust/myApply/")
public class MyEntrustApplyController extends BaseController {
	
	private static Logger logger = Logger.getLogger(MyEntrustApplyController.class);  
	/**
	 * 字典表服务类
	 */
	@Inject
	private DicService dicService;
	
	/**
	 * 委托服务类
	 */
	@Inject
	private EntrustApplyService entrustApplyService;
	@Autowired
	private CacheSessionService cacheSessionService;
	
	/**
	 * 案件名称
	 */
	private String caseName;
	
	/**
	 * 案件类型0
	 *   
	 */
	private String caseType;
	
	/**
	 * 委托状态
	 */
	private String processState;
	
	/**
	 * 委托开始时间
	 */
	private String entrustDateBegin;
	
	/**
	 * 委托结束时间
	 */
	private String entrustDateEnd;
	
	/**
	 * 是否共享案件信息
	 */
	private String isShareCaseinfo;
	
	/**
	 * 是否共享查询信息
	 */
	private String isShareQueryresult;
	
    /**
     * 进入委托页面，新增，或者修改
     * @param model
     * @return
     */
	@RequestMapping("index")
	public String index(Model model) {
		String csrfToken = CsrfTokenManager.getToken();
		model.addAttribute("csrf", csrfToken);
		//处理字典表信息
		JSONArray jarrCaseType = new JSONArray();
		//案件类型字典
		Dic dicCaseType = new Dic();
		dicCaseType.setDicTypeKey("case_type");
		List<Dic> CaseTypeProv = dicService.queryAll(dicCaseType);
		Collections.sort(CaseTypeProv);
		List<MultSelectDataModel> alist=new ArrayList<MultSelectDataModel>();
		for(Dic dic:CaseTypeProv){
			if(dic.getDicLevel().equals("0")){
				MultSelectDataModel mnode=new MultSelectDataModel();
				mnode.setId(dic.getDicKey());
				mnode.setText(dic.getDicName());
				mnode=ControllerUtil.getAllChildrenNode(CaseTypeProv, dic, mnode);
				alist.add(mnode);
			}
		}
		Gson gson = new Gson();
		String caseTypejson=gson.toJson(alist);
		JSONArray jarrCaseStage = new JSONArray();
		Dic dicCaseStage = new Dic();
		dicCaseStage.setDicTypeKey("case_stage");
		List<Dic> CaseStageProv = dicService.queryAll(dicCaseStage);
		for (Dic res : CaseStageProv) {//
			JSONObject jobj = new JSONObject();
			jobj.put("stage_code", res.getDicKey());
			jobj.put("stage_name", res.getDicName());
			jarrCaseStage.add(jobj);
		}
		JSONArray jarrProcessState = new JSONArray();
		Dic dicProcessState = new Dic();
		dicProcessState.setDicTypeKey("process_state");
		List<Dic> processStateProv = dicService.queryAll(dicProcessState);
		Collections.sort(processStateProv);
		/**
		 * update
		 * @author hotdog
		 * 2015-08-31
		 * 将result(待确认),processing(处理中)都放入处理中
		 */
		StringBuffer processStateSb = new StringBuffer();
		//将需要删除的对象暂存在list中
		List<Dic> tempSaveForDel = new ArrayList<Dic>();
		for (Dic res : processStateProv) {//
			JSONObject jobj = new JSONObject();
			if("processing".equals(res.getDicKey())||"result".equals(res.getDicKey())) {
				processStateSb.append(res.getDicKey()).append(",");
				tempSaveForDel.add(res);
				jobj.put("processState_code", res.getDicKey());
				jobj.put("processState_name", "处理中");
			} else {
				jobj.put("processState_code", res.getDicKey());
				jobj.put("processState_name", res.getDicName());
			}
				jarrProcessState.add(jobj);
		}
		processStateProv.removeAll(tempSaveForDel);
		
		if (processStateSb.length() > 0) {
			String processStateStr = processStateSb.toString();
			if (processStateStr.endsWith(",")) {
				processStateStr = processStateStr.substring(0,processStateStr.length() - 1);
			}
			Dic dicTemp = new Dic();
			dicTemp.setDicKey(processStateStr);
			dicTemp.setDicName("处理中");
			processStateProv.add(2, dicTemp);

		}
		
		model.addAttribute("caseTypeJson", jarrCaseType.toString());
		model.addAttribute("caseStageJson", jarrCaseStage.toString());
		model.addAttribute("processStateJson", jarrProcessState.toString());
		model.addAttribute("caseTypeJson",caseTypejson);
		getHttpServletRequest().setAttribute("processState",processStateProv);
		getHttpServletRequest().setAttribute("caseStageList",CaseStageProv);
		return Common.JSP_PATH + "entrust/myentrust_apply";
	}
	

	/**
	 * 查询委托信息
	 * @param dataRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryEntrust")
	public  DataResponse<EntrustApplyData> queryEntrust(DataRequest dataRequest){
		HttpServletRequest request=this.getHttpServletRequest();
		String entrustNo=request.getParameter("entrustNo");
	    this.caseName=request.getParameter("caseName");
	    this.caseType =request.getParameter("caseLX");
	    this.processState=request.getParameter("processState");
	    this.entrustDateBegin=request.getParameter("entrustDateBegin");
	    this.entrustDateEnd=request.getParameter("entrustDateEnd");
	    this.isShareCaseinfo=request.getParameter("isShareCaseinfo");
	    this.isShareQueryresult=request.getParameter("isShareQueryresult");
	    String userId = cacheSessionService.getAttribute("userSessionId").toString();
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		//案件类型
		Dic dicCaseType = new Dic();
		dicCaseType.setDicTypeKey("case_type");
		List<Dic> CaseTypeList = dicService.queryAll(dicCaseType);
		Dic dicProcessState = new Dic();
		dicProcessState.setDicTypeKey("process_state");
		List<Dic> CaseStageProv = dicService.queryAll(dicProcessState);
		if(!StringUtils.isEmpty(processState)){
			processState=processState.substring(0, processState.length()-1);
		}else{
			StringBuffer sta=new StringBuffer("");
			if(StringUtil.isEmpty(processState)){
				for(Dic dic:CaseStageProv){
					 sta.append(dic.getDicKey()).append(",");
				}
				processState=sta.substring(0, sta.length()-1);
			}
		}
		StringBuffer caseTypeCode=new StringBuffer("");
		if(!StringUtils.isEmpty(caseType)){
			String caseTypeArr[]=caseType.split(",");
			for(String ctype:caseTypeArr){
				String type[]=ctype.split(":");
				caseTypeCode.append(ControllerUtil.findAllChildrenCode(CaseTypeList,type[0]).append(type[0])).append(",");
			}
		}
		if(!StringUtils.isEmpty(caseTypeCode.toString())){
			caseType=caseTypeCode.subSequence(0, caseTypeCode.length()-1).toString();
		}
		logger.info("查询的流程状态标识为:"+processState);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("caseName",caseName);
		map.put("caseType",caseType);
		map.put("processState",processState);
		map.put("entrustDateBegin",entrustDateBegin);
		map.put("entrustDateEnd",entrustDateEnd);
		map.put("createUser", userId);
		map.put("entrustNo", entrustNo);
		map.put("isShareCaseinfo", isShareCaseinfo);
		map.put("isShareQueryresult", isShareQueryresult);
		try {
			entrustApplyService.query(getPageView(String.valueOf(page),String.valueOf(rows)), map);
		} catch (Exception e) {
			logger.error("查询我的委托出错,"+e.getLocalizedMessage());
		}
        //转换创建时间格式
		List<EntrustApplyData> list=pageView.getDataResponse().getRows();
		if(list!=null){
			for(EntrustApplyData data:list){
				if(data.getEntrustInfo().getCreateDate()!=null){
				    data.getEntrustInfo().setRev1(DateUtils.parse2String(data.getEntrustInfo().getCreateDate(), DateUtils.YMDHMS));
				}
				if(data.getCaseInfo()!=null){
				    String caseType=data.getCaseInfo().getCaseType();
				    String caseTypeCn=ControllerUtil.findCaseTypeZn(CaseTypeList, caseType,"");
				    data.getCaseInfo().setRev1(caseTypeCn);
				}
			}
		}
		@SuppressWarnings("rawtypes")
		DataResponse dataResponse=pageView.getDataResponse();
		return dataResponse;
	}
	

	public DicService getDicService() {
		return dicService;
	}

	public void setDicService(DicService dicService) {
		this.dicService = dicService;
	}

	public EntrustApplyService getEntrustApplyService() {
		return entrustApplyService;
	}

	public void setEntrustApplyService(EntrustApplyService entrustApplyService) {
		this.entrustApplyService = entrustApplyService;
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

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public String getEntrustDateBegin() {
		return entrustDateBegin;
	}

	public void setEntrustDateBegin(String entrustDateBegin) {
		this.entrustDateBegin = entrustDateBegin;
	}

	public String getEntrustDateEnd() {
		return entrustDateEnd;
	}

	public void setEntrustDateEnd(String entrustDateEnd) {
		this.entrustDateEnd = entrustDateEnd;
	}


	public String getIsShareCaseinfo() {
		return isShareCaseinfo;
	}


	public void setIsShareCaseinfo(String isShareCaseinfo) {
		this.isShareCaseinfo = isShareCaseinfo;
	}


	public String getIsShareQueryresult() {
		return isShareQueryresult;
	}


	public void setIsShareQueryresult(String isShareQueryresult) {
		this.isShareQueryresult = isShareQueryresult;
	}
	
}
