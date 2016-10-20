package com.hisign.publicsafety.controller.entrust;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.api.CacheSessionService;
import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.Dic;
import com.hisign.publicsafety.entity.EntrusAttachment;
import com.hisign.publicsafety.entity.EntrusInvestigationInfo;
import com.hisign.publicsafety.entity.EntrustCheckList;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.service.EntrustCheckService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.DateUtils;
import com.hisign.publicsafety.util.StringUtil;
import com.hisign.publicsafety.viewModel.multselect.MultSelectDataModel;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;
/**
 * 审核委托
 * @author fankai
 * @date 2015-8-7
 * 
 */
@Controller
@RequestMapping("/entrust/check/")
public class MyEntrustCheckController extends BaseController {
	
	private static Logger logger = Logger.getLogger(MyEntrustCheckController.class);  
	/**
     *委托服务sercie 
     */
	@Inject
	private EntrustApplyService entrustApplyService;
	
	/**
	 * 字典服务service
	 */
	@Inject
	private DicService dicService;
	
	/**
	 * 委托审核service
	 */
	@Inject
	private EntrustCheckService entrustCheckService;
	
	/**
	 * 自动生成id service
	 */
	@Inject
	private IdService idService;
	@Autowired
	private CacheSessionService cacheSessionService;
	
	/**
	 * 进入审核委托首页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("index")
	public String feedBackIndex(Model model) throws Exception{
		String csrfToken = CsrfTokenManager.getToken();
		model.addAttribute("csrf", csrfToken);
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
		
		//处理委托单位
		//案件类型字典
		Dic dicUnitType = new Dic();
		dicUnitType.setDicTypeKey("GXSDM");
		List<Dic> UnitTypeProv = dicService.queryAll(dicUnitType);
		Collections.sort(UnitTypeProv);
		List<MultSelectDataModel> ulist=new ArrayList<MultSelectDataModel>();
		for(Dic dic:UnitTypeProv){
			if(dic.getDicLevel().equals("1")){
				MultSelectDataModel mnode=new MultSelectDataModel();
				mnode.setId(dic.getDicKey());
				mnode.setText(dic.getDicName());
				mnode=ControllerUtil.getAllChildrenNode(UnitTypeProv, dic, mnode);
				ulist.add(mnode);
			}
		}
		Gson ugson = new Gson();
		String unitTypejson=ugson.toJson(ulist);
		
		/**
		 * update by zhao
		 * 2015-09-02
		 * 增加委托状态查询条件
		 */
		Dic dicProcessState = new Dic();
		dicProcessState.setDicTypeKey("process_state");
		List<Dic> processStateAll = dicService.queryAll(dicProcessState);
		//将需要展示的状态暂存在list中
		Map<String, String> processStateMapAli = new LinkedHashMap<String, String>();
		processStateMapAli.put(Constant.PROCESS_STATE_SUBMIT, "未审核");
		processStateMapAli.put(Constant.PROCESS_STATE_CHECKPASS, "已审核通过");
		processStateMapAli.put(Constant.PROCESS_STATE_CHECKNOTPASS, "已审核未通过");
		processStateMapAli.put(Constant.PROCESS_STATE_FEEDBACK, "已确认反馈");
		processStateMapAli.put(Constant.PROCESS_STATE_RESULT, "未确认反馈");
		processStateMapAli.put(Constant.PROCESS_STATE_FINISH, "已完成");
		JSONArray jarrProcessState = new JSONArray();
		List<Dic> processStateShowList = new ArrayList<Dic>();
		for (Entry<String, String> entry : processStateMapAli.entrySet()) {//
			for (Dic res : processStateAll) {//
				String dicKey = res.getDicKey();
				if (entry.getKey().equals(dicKey)) {
					res.setDicName(entry.getValue());
					processStateShowList.add(res);
					JSONObject jobj = new JSONObject();
					jobj.put("processState_code", dicKey);
					jobj.put("processState_name", entry.getValue());
					jarrProcessState.add(jobj);
				}
			}
		}
		model.addAttribute("caseTypeJson",caseTypejson);
		model.addAttribute("processStateJson", jarrProcessState.toString());
		model.addAttribute("dicJsonArr",dicService.getUnitJson());
		model.addAttribute("unitTypejson",unitTypejson);
		getHttpServletRequest().setAttribute("processState",processStateShowList);
		return Common.JSP_PATH+"/entrust/entrust_check";
	}
	
	/**
	 * 查询审核委托信息
	 * @param dataRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryCheckEntrust") 
	public  DataResponse<EntrustApplyData> queryCheckEntrust(DataRequest dataRequest){
	    String caseType=this.getHttpServletRequest().getParameter("casetype");
	    String caseName =this.getHttpServletRequest().getParameter("caseName");
	    String entrustNo=this.getHttpServletRequest().getParameter("entrustNo");
	    String personName=this.getHttpServletRequest().getParameter("personName");
	    String unit=this.getHttpServletRequest().getParameter("unit");
	    String entrustDateBegin=this.getHttpServletRequest().getParameter("entrustDateBegin");
	    String entrustDateEnd=this.getHttpServletRequest().getParameter("entrustDateEnd");
	    String processState=this.getHttpServletRequest().getParameter("processState");
	    String isdefault=this.getHttpServletRequest().getParameter("isdefault");
	    if(!Common.isEmpty(processState)){
	    	if (processState.endsWith(",")) {
	    		processState=processState.substring(0, processState.length()-1);
	    	}
		}
	    StringBuffer caseTypeCode=new StringBuffer("");
		if(!StringUtils.isEmpty(caseType)){
			//案件类型
			Dic dicCaseType = new Dic();
			dicCaseType.setDicTypeKey("case_type");
			List<Dic> CaseTypeList = dicService.queryAll(dicCaseType);
			String caseTypeArr[]=caseType.split(",");
			for(String ctype:caseTypeArr){
				String type[]=ctype.split(":");
				caseTypeCode.append(ControllerUtil.findAllChildrenCode(CaseTypeList,type[0]).append(type[0])).append(",");
			}
		}
		if(!StringUtils.isEmpty(caseTypeCode.toString())){
			caseType=caseTypeCode.subSequence(0, caseTypeCode.length()-1).toString();
		}
		
	    StringBuffer unitTypeCode=new StringBuffer("");
		 if (!StringUtils.isEmpty(unit)){
		   //案件类型字典
			Dic dicUnitType = new Dic();
			dicUnitType.setDicTypeKey("GXSDM");
			List<Dic> UnitTypeProv = dicService.queryAll(dicUnitType);
			String unitTypeArr[]=unit.split(",");
			for (String utype:unitTypeArr){
				String type[]=utype.split(":");
				unitTypeCode.append(ControllerUtil.findAllChildrenCode(UnitTypeProv,type[0]).append(type[0])).append(",");
			 }
		}
		 if (!StringUtils.isEmpty(unitTypeCode.toString())){
			unit=unitTypeCode.subSequence(0, unitTypeCode.length()-1).toString();
		}
		
	    logger.info("查询的委托状态为:"+processState);
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("caseName",caseName);
		map.put("caseType",caseType);
		map.put("entrustNo",entrustNo);
		map.put("entrustDateBegin",entrustDateBegin);
		map.put("entrustDateEnd",entrustDateEnd);
		map.put("personName", personName);
		map.put("unit", unit);
		//阿里返回查询数据以后，状态更改为带确认（result）,公安用户仍显示为处理中，待阿里用户再次确认以后，公安用户显示为已审核
		if(!StringUtil.isEmpty(isdefault)){
			if(isdefault.equals("1")){
				processState="submit,result";
			}
		}
		StringBuffer sta=new StringBuffer("");
		if(StringUtil.isEmpty(processState)){
			Dic dicProcessState = new Dic();
			dicProcessState.setDicTypeKey("process_state");
			List<Dic> processStateAll = dicService.queryAll(dicProcessState);
			for(Dic dic:processStateAll){
				if(!dic.getDicKey().equals(Constant.PROCESS_STATE_SAVE)){
				  sta.append(dic.getDicKey()).append(",");
				}
			}
			processState=sta.substring(0, sta.length()-1);
		}
		  map.put("processState", processState);
	
		try {
			entrustApplyService.query(getPageView(String.valueOf(page),String.valueOf(rows)), map);
		} catch (Exception e) {
			logger.error("查询委托审核发生异常,"+e.getLocalizedMessage());
		}
		List<EntrustApplyData> list=pageView.getDataResponse().getRows();
		if(list!=null){
			for(EntrustApplyData data:list){
				data.getEntrustInfo().setRev2(data.getEntrustInfo().getEntrustNo());
				if(data.getEntrustInfo().getCreateDate()!=null)
				data.getEntrustInfo().setRev1(DateUtils.parse2String(data.getEntrustInfo().getCreateDate(), DateUtils.YMDHMS));
			}
		}
		return pageView.getDataResponse();
	}
	
	/**
	 * 查询委托审核的详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryEntrustDetalCheck")
	public String queryEntrustDetalCheck() throws Exception{
		String csrfToken = CsrfTokenManager.getToken();
		this.getHttpServletRequest().setAttribute("csrf", csrfToken);
		HttpServletRequest request=this.getHttpServletRequest();
		String process=request.getParameter("process");
		String entrustNo=request.getParameter("entrustNo");
		request.setAttribute("process", process);
		request.setAttribute("entrustNo", entrustNo);
		EntrustApplyData data=entrustApplyService.getByEntrustNo(entrustNo);
		Dic dicProv = new Dic();
		dicProv.setDicTypeKey("province");
		List<Dic> dicListProv = dicService.queryAll(dicProv);
		Dic dicCity = new Dic();
		dicCity.setDicTypeKey("city");
		List<Dic> dicListCity = dicService.queryAll(dicCity);
		Dic dicCaseStage = new Dic();
		dicCaseStage.setDicTypeKey("case_stage");
		List<Dic> CaseStageProv = dicService.queryAll(dicCaseStage);
		Dic dicCaseType = new Dic();
		dicCaseType.setDicTypeKey("case_type");
		List<Dic> CaseTypeProv = dicService.queryAll(dicCaseType);
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
			Dic xcxxCity = new Dic();
			xcxxCity.setDicTypeKey("xcxx");
			List<Dic> xcxxList = dicService.queryAll(xcxxCity);
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
					String caseType=ControllerUtil.findCaseTypeZn(CaseTypeProv, data.getCaseInfo().getCaseType(),"");
					request.setAttribute("pdicName",caseType);
				}
			}
			request.setAttribute("xcxxList", xcxxList);
			
		}else{
			logger.info("委托编号:"+entrustNo+"数据库中没有检索到相关信息");
		}
		return Common.JSP_PATH+"entrust/entrust_checkdetail";
	}
	
	/**
	 * 审核委托详细
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("viewCheckentrust")
	public String viewCheckentrust() throws Exception{
		HttpServletRequest request=this.getHttpServletRequest();
		String entrustNo=request.getParameter("entrustNo");
		String checkType=request.getParameter("checkType");
		String checkMemo=request.getParameter("checkMemo");
		String userId = cacheSessionService.getAttribute("userSessionId").toString();
		if(!StringUtils.isEmpty(entrustNo) && !StringUtils.isEmpty(checkType)){
			List<EntrustCheckList> list = new ArrayList<EntrustCheckList>();
			String ens[]=entrustNo.split(",");
			for(String no:ens){
				EntrustCheckList entrustCheck=new EntrustCheckList();
				entrustCheck.setId(idService.getId()+"");
				entrustCheck.setEntrustNo(no);
				if(checkType.equals("pass")){
				   entrustCheck.setCheckKey("processing");
				   entrustCheck.setCheckIspass(1);
				   logger.info("用户userid="+userId+",待审核委托编号为:"+no+"动作为审核通过。");
				}else if(checkType.equals("notpass")){
				   entrustCheck.setCheckKey("notpass");
				   entrustCheck.setCheckIspass(0);
				   entrustCheck.setCheckDesdc(checkMemo);
				   logger.info("用户userid="+userId+",待审核委托编号为:"+no+"动作为审核不通过。");
				}else if (checkType.equals("feedback")) { //委托反馈后确认
				   entrustCheck.setCheckKey("feedback");
				   entrustCheck.setCheckIspass(1);
				   logger.info("用户userid="+userId+",待审核委托编号为:"+no+"动作为委托反馈。");
				}else{
				   entrustCheck.setCheckKey("finish");
				   entrustCheck.setCheckIspass(1);
				   logger.info("用户userid="+userId+",待审核委托编号为:"+no+"动作为委托完成。");
				}
				entrustCheck.setCheckUser(Integer.parseInt(userId));
				entrustCheck.setCheckDate(new Date());
				entrustCheck.setIsExec("1");
				list.add(entrustCheck);
			}
			try{
			   entrustCheckService.check(list);
			}catch(Exception e){
				logger.error("审核委托失败，"+e.toString());
				return "0";
			}
			
		}
		//返回1 成功
		return "1";
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


	public EntrustCheckService getEntrustCheckService() {
		return entrustCheckService;
	}


	public void setEntrustCheckService(EntrustCheckService entrustCheckService) {
		this.entrustCheckService = entrustCheckService;
	}


	public IdService getIdService() {
		return idService;
	}


	public void setIdService(IdService idService) {
		this.idService = idService;
	}
	
}
