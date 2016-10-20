package com.hisign.publicsafety.controller.seriescase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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
import com.hisign.publicsafety.common.Constant;
import com.hisign.publicsafety.common.SpringHolder;
import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.controller.entrust.ControllerUtil;
import com.hisign.publicsafety.entity.Dic;
import com.hisign.publicsafety.entity.SeriesCaseAlipay;
import com.hisign.publicsafety.entity.SeriesCaseBankcard;
import com.hisign.publicsafety.entity.SeriesCaseCertificate;
import com.hisign.publicsafety.entity.SeriesCaseDetail;
import com.hisign.publicsafety.entity.SeriesCaseInfo;
import com.hisign.publicsafety.entity.SeriesCaseTelephone;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.entity.series.CaseDataForSelect;
import com.hisign.publicsafety.entity.series.CaseInfoInSeries;
import com.hisign.publicsafety.entity.series.SeriesData;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.service.series.SeriesCaseInfoService;
import com.hisign.publicsafety.service.series.SeriesService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.DateUtils;
import com.hisign.publicsafety.viewModel.multselect.MultSelectDataModel;
import com.hisign.publicsafety.viewModel.seriescase.CaseInforForSelectModel;
import com.hisign.publicsafety.viewModel.seriescase.SeriesCaseInfoDetailModel;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;
/**
 * 
 * @author fankai
 * @date   2015-09-06
 * 串并案相关controller
 */
@Controller
@RequestMapping("/seriesCase/myseries/")
public class SeriesCaseController extends BaseController {
	private static Logger logger = Logger.getLogger(SeriesCaseController.class);  
	
	/**
	 * 字典服务service
	 */
	@Inject
	private DicService dicService;
	
	/**
	 * 串并案服务service
	 */
	@Inject
	private SeriesService seriesService;
	
	/**
	 * id生成相关服务类
	 */
	@Inject
	private IdService idService;
	
	
	@Autowired
	private SeriesCaseInfoService seriesCaseInfoService;

	
	/**
	 * 委托相关服务类
	 */
	@Inject
	private EntrustApplyService entrustApplyService;
	
    /***
     * 跳转到我的串并案首页
     * @param model
     * @return 跳转到我的串并案首页
     */
	@RequestMapping("index") 
	public String index(Model model){
		String csrfToken = CsrfTokenManager.getToken();
		model.addAttribute("csrf", csrfToken);
		//初始化串并案依据字典数据
		Dic cbaType = new Dic();
		cbaType.setDicTypeKey("CBAYJ");
		List<Dic> cbaTypeList = dicService.queryAll(cbaType);
		this.getHttpServletRequest().setAttribute("cbatype",cbaTypeList);
		return Common.JSP_PATH + "/seriescase/series_case_list";
	}
	
	/***
	 * 查询我的串并案
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryMySeriesCaseList")
	public DataResponse<SeriesCaseInfo> queryMySeriesCaseList(DataRequest dataRequest) throws Exception{
		HttpServletRequest request=this.getHttpServletRequest();
		String seriesCaseNo=request.getParameter("seriesCaseNo");
		String seriesCaseName=request.getParameter("seriesCaseName");
		String seriesPerson=request.getParameter("seriesPerson");
		String dateBegin=request.getParameter("dateBegin");
		String dateEnd=request.getParameter("dateEnd");
		//获得当前页码和每一页展示的记录条数
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		StringBuffer person=new StringBuffer("");
		//处理串并案依据查询参数
		Dic cbaType = new Dic();
		cbaType.setDicTypeKey("CBAYJ");
		List<Dic> cbaTypeList = dicService.queryAll(cbaType);
		if (!StringUtils.isEmpty(seriesPerson)){
		   for(int i=0;i<cbaTypeList.size();i++){
				Dic bean=cbaTypeList.get(i);
				if(StringUtils.isEmpty(seriesPerson)){
					    person.append(bean.getDicKey()).append(",");
				}else{
					if(seriesPerson.indexOf(bean.getDicKey())==-1){
						person.append(bean.getDicKey()).append(",");
					}
				}
			}
		}
		Map<String, Object> paraMap=new HashMap<String, Object>();	
		paraMap.put("seriesCaseNo", seriesCaseNo);
		paraMap.put("seriesCaseName", seriesCaseName);
		if(person.length()>0){
	    	paraMap.put("seriesReason", person.toString().substring(0,person.length()-1));
		}
		paraMap.put("createDateBegin", dateBegin);
		paraMap.put("createDateEnd", dateEnd);
		seriesService.querySeries(getPageView(String.valueOf(page),String.valueOf(rows)), paraMap);
		List<SeriesData>dlist=pageView.getRecords();
		if (dlist!=null){
			for (SeriesData data:dlist){
				 SeriesCaseInfo caseInfo=data.getSeriesCaseInfo();
				 if (caseInfo!=null){
					String seriesReason=caseInfo.getSeriesReason();
					caseInfo.setRev1(DateUtils.parse2String(caseInfo.getCreateDate(), DateUtils.YMDHMS));
					if (!StringUtils.isEmpty(seriesReason)){
						String srArr[]=seriesReason.split(",");
						StringBuffer sersper=new StringBuffer();
						for (String ser:srArr){
						   String dicName=this.getDictName(cbaTypeList, ser);
						   sersper.append(dicName).append(",");
						}
						caseInfo.setSeriesReason(sersper.subSequence(0, sersper.length()-1).toString());
					}
				}
			}
		}
		return pageView.getDataResponse();
	}
	
	/**
	 * 串并案点击新增，跳转到串并案件查询界面
	 * @return
	 */
	@RequestMapping("showQueryCaseinfo")
	public String showQueryCaseinfo(Model model){
		String csrfToken = CsrfTokenManager.getToken();
		model.addAttribute("csrf", csrfToken);
		//处理案件类型字典
		Dic dicCaseType = new Dic();
		dicCaseType.setDicTypeKey("case_type");
		List<Dic> CaseTypeProv = dicService.queryAll(dicCaseType);
		List<Dic> caseTypeList=new ArrayList<Dic>();
        if(CaseTypeProv!=null && CaseTypeProv.size()>0){
        	for(Dic dic:CaseTypeProv){
        		if(!StringUtils.isEmpty(dic.getDicLevel())){
        		if(dic.getDicLevel().equals("1")){
        			caseTypeList.add(dic);
        			caseTypeList.addAll(this.getSelectList(CaseTypeProv, dic.getDicKey()));
        		 }
        		}
        	}
        }
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
        model.addAttribute("caseTypeJson",caseTypejson);
      //处理省份字典表信息
  		JSONArray jarrProv = new JSONArray(); 
  		Dic dicProv = new Dic();
  		dicProv.setDicTypeKey("province");
  		List<Dic> dicListProv = dicService.queryAll(dicProv);
  		for (Dic res : dicListProv) {
  			JSONObject jobj = new JSONObject();
  			jobj.put("province_code", res.getDicKey());
  			jobj.put("province_name", res.getDicName());
  			jarrProv.add(jobj);
  		}
  		//处理市字典表信息
  		JSONArray jarrCity = new JSONArray();
  		Dic dicCity = new Dic();
  		dicCity.setDicTypeKey("city");
  		List<Dic> dicListCity = dicService.queryAll(dicCity);
  		for (Dic res : dicListCity) {
  			JSONObject jobj = new JSONObject();
  			jobj.put("city_code", res.getDicKey());
  			jobj.put("province_code", res.getDicParentKey());
  			jobj.put("city_name", res.getDicName());
  			jarrCity.add(jobj);
  		}
  		model.addAttribute("provinceJson", jarrProv.toString());
		model.addAttribute("cityJson", jarrCity.toString());
        this.getHttpServletRequest().setAttribute("caseTypeList",caseTypeList);
		return Common.JSP_PATH + "/seriescase/series_case_caseinfo_list";
	}
	
	
	
	/***
	 * 查询我的待串并案件信息列表
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryCaseInfoList")
	public DataResponse<SeriesCaseInfo> queryCaseInfoList(DataRequest dataRequest) throws Exception{
		HttpServletRequest request=this.getHttpServletRequest();
		//获得当前登录用户信息
		String userId = SpringHolder.CACHE_SESSION_SERVICE.getAttribute("userSessionId").toString();
		//获得当前页码和每一页展示的记录条数
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		//获得页面查询参数
		String caseType=request.getParameter("caseType");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String caseName=request.getParameter("caseName");
		String dateBegin=request.getParameter("dateBegin");
		String dateEnd=request.getParameter("dateEnd");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("createUser", userId);
		paraMap.put("caseName", caseName);
		paraMap.put("caseType", caseType);
		paraMap.put("provinceCode", province);
		paraMap.put("cityCode", city);
		paraMap.put("caseOccurDateBegin", dateBegin);
		paraMap.put("caseOccurDateEnd", dateEnd);
		//根据查询条件，查询出符合条件的记录
		seriesService.queryCase(getPageView(String.valueOf(page),String.valueOf(rows)), paraMap);
		//根据查询结果，封装前台页面展现的数据
		List<CaseDataForSelect> list=pageView.getRecords();
		List<CaseInforForSelectModel> vlist=new ArrayList<CaseInforForSelectModel>();
		if (list!=null && list.size()>0){
			for (CaseDataForSelect select:list){
				//封装页面展现模型
				CaseInforForSelectModel model=new CaseInforForSelectModel();
				//设置记录编号
				model.setNo(select.getNo());
				 if (select.getCaseInfo()!=null){
					//设置案件id
					model.setCaseId(select.getCaseId());
					//设置案件编号和委托编号
					model.setCaseNo(select.getCaseInfo().getCaseNo()+"<br>"+select.getCaseInfo().getEntrustNo());
				    //设置案件名称
					model.setCaseName(select.getCaseInfo().getCaseName());
					//设置委托编号
					model.setEntrustNo(select.getCaseInfo().getEntrustNo());
					String caseTypeCn="";
					if(select.getCaseInfo()!=null){
						//案件类型
						Dic dicCaseType = new Dic();
						dicCaseType.setDicTypeKey("case_type");
						List<Dic> CaseTypeList = dicService.queryAll(dicCaseType);
					    String caseTypeEn=select.getCaseInfo().getCaseType();
					    caseTypeCn=ControllerUtil.findCaseTypeZn(CaseTypeList, caseTypeEn,"");
					  
					}
					//设置案件类型
					model.setCaseType(caseTypeCn);
					//设置串并案依据
				    StringBuffer clues=new StringBuffer("");
				    this.setSeriesClueString(select, clues,model);
				    model.setSeriesClueList(clues.toString());
				    String isSeries=select.getCaseInfo().getIsSeries();
				     if (StringUtils.isEmpty(isSeries)){
				    	isSeries="0";
				    }
				    model.setSeriesStatus(isSeries);
				     if (isSeries.equals("1")){
				    	model.setSeriesStatusCn("已串并");
				     }else{
				    	model.setSeriesStatusCn("未串并");
				     }
				    vlist.add(model);
				}
			}
		}
		pageView.setRecords(vlist);
		return pageView.getDataResponse();
	}
	
	
	/**
	 * 跳转到查询到待串的案件列表
	 * @param model
	 * @return
	 */
	@RequestMapping("showQueryCaseInfoSelect")
	public String showQueryCaseInfoSelect(Model model){
		String csrfToken = CsrfTokenManager.getToken();
		model.addAttribute("csrf", csrfToken);
		HttpServletRequest request=this.getHttpServletRequest();
		//初始化串并案依据字典数据
		Dic cbaType = new Dic();
		cbaType.setDicTypeKey("CBAYJ");
		List<Dic> cbaTypeList = dicService.queryAll(cbaType);
		Dic dicCaseType = new Dic();
		//初始化案件类型字典数据
		dicCaseType.setDicTypeKey("case_type");
		List<Dic> CaseTypeProv = dicService.queryAll(dicCaseType);
		JSONArray jarrCaseType = new JSONArray();
	     for (Dic res : CaseTypeProv) {
				JSONObject jobj = new JSONObject();
				jobj.put("castType_code", res.getDicKey());
				jobj.put("castType_name", res.getDicName());
				jarrCaseType.add(jobj);
		  }
	    //处理串并依据类型
	    String seriesTypes= this.getHttpServletRequest().getParameter("seriesTypes");
	     if (!StringUtils.isEmpty(seriesTypes)){
	    	 String stArr[]=seriesTypes.split(",");
	    	 List<String> tlist=Arrays.asList(stArr);
	    	 request.setAttribute("seriesTypes",seriesTypes);
	    	 request.setAttribute("tlist",tlist);
	     }
		String caseId=this.getHttpServletRequest().getParameter("caseId");
		request.setAttribute("caseId", caseId);
		request.setAttribute("cbatype",cbaTypeList);
		model.addAttribute("caseTypeJson", jarrCaseType.toString());
		return Common.JSP_PATH + "/seriescase/series_case_series_list";
	}
	
	/***
	 * 查询待串并案件列表
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryCaseInfoWaitList")
	public   DataResponse<SeriesCaseInfo> queryCaseInfoWaitList(DataRequest dataRequest){
		HttpServletRequest request=this.getHttpServletRequest();
		//获得当前页码和每一页展示的记录条数
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		String caseId=request.getParameter("caseId");
		Map<String, Object> paraMap=new HashMap<String, Object>();
		String userId = SpringHolder.CACHE_SESSION_SERVICE.getAttribute("userSessionId").toString();
		paraMap.put("entrustCaseId", caseId);
		paraMap.put("createUser", userId);
		//地市字典
		Dic dicCity = new Dic();
		dicCity.setDicTypeKey("city");
		List<Dic> dicListCity = dicService.queryAll(dicCity);
		try {
			seriesService.seriesSearch(getPageView(String.valueOf(page),String.valueOf(rows)), paraMap);
		} catch (Exception e) {
			logger.error("查询待串并案件失败,"+e.getLocalizedMessage());
		}
		//根据查询结果，封装前台页面展现的数据
		List<CaseDataForSelect> slist=pageView.getRecords();
		List<CaseInforForSelectModel> vlist=new ArrayList<CaseInforForSelectModel>();
		if (slist!=null && slist.size()>0){
			for (CaseDataForSelect select:slist){
				//封装页面展现模型
				CaseInforForSelectModel model=new CaseInforForSelectModel();
				//设置记录编号
				model.setNo(select.getNo());
				if(select.getEntrustInfo()!=null){
	    			String city=this.getDictName(dicListCity, select.getEntrustInfo().getCityCode());
	    			model.setArea(city);
	    		}
				 if (select.getCaseInfo()!=null){
					//设置案件id
					model.setCaseId(select.getCaseId());
					//设置案件编号和委托编号
					StringBuffer noStr=new StringBuffer();
					if (select.getCaseInfo()!=null){
						if (!StringUtils.isEmpty(select.getCaseInfo().getCaseNo())){
							noStr.append("案件编号:");
							noStr.append(select.getCaseInfo().getCaseNo());
							noStr.append("<br>");
						}
						noStr.append("委托编号:");
						noStr.append(select.getCaseInfo().getEntrustNo());
					}
					model.setCaseNo(noStr.toString());
				    //设置委托编号
					model.setEntrustNo(select.getCaseInfo().getEntrustNo());
					//设置案件名称
					model.setCaseName(select.getCaseInfo().getCaseName());
					String caseTypeCn="";
					if(select.getCaseInfo()!=null){
						//案件类型
						Dic dicCaseType = new Dic();
						dicCaseType.setDicTypeKey("case_type");
						List<Dic> CaseTypeList = dicService.queryAll(dicCaseType);
					    String caseTypeEn=select.getCaseInfo().getCaseType();
					    caseTypeCn=ControllerUtil.findCaseTypeZn(CaseTypeList, caseTypeEn,"");
					}
					//设置案件类型
					model.setCaseType(caseTypeCn);
					//设置串并案依据
				    StringBuffer clues=new StringBuffer("");
				    this.setSeriesClueString(select, clues,model);
				    model.setSeriesClueList(clues.toString());
				    String isSeries=select.getCaseInfo().getIsSeries();
				     if (StringUtils.isEmpty(isSeries)){
				    	isSeries="0";
				    }
				    model.setSeriesStatus(isSeries);
				     if (isSeries.equals("1")){
				    	model.setSeriesStatusCn("已串并");
				     }else{
				    	model.setSeriesStatusCn("未串并");
				     }
				    vlist.add(model);
				}
			}
		}
		pageView.setRecords(vlist);
		return pageView.getDataResponse();
	}
	
	/**
	 * 保存我的串并案
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("saveSeriesCaseInfo")
	public String saveSeriesCaseInfo() throws Exception{
		
		//获得登录用户单位编码
		String userUnitCode=(String) SpringHolder.CACHE_SESSION_SERVICE.getAttribute("userUnitCode");
		String seriesCaseNo="";
		if (!StringUtils.isEmpty(userUnitCode)){
			seriesCaseNo=idService.getSeriesCaseNo(userUnitCode);
		}else{
			logger.error("自动生成串并案编码失败!");
			return "自动生成串并案编码失败!";
		}
		//用户id
		String userId = SpringHolder.CACHE_SESSION_SERVICE.getAttribute("userSessionId").toString();
		//新增日期
		Date date=new Date();
		HttpServletRequest request=this.getHttpServletRequest();
		//串并案名称
		String seriesCaseName=request.getParameter("seriesCaseName");
		//串并依据
		String seriesReason=request.getParameter("seriesReason");
		//串并描述
		String seriesDesc=request.getParameter("seriesDesc");
		//串并案件id列表
		String seriesCaseIds=request.getParameter("seriesCaseIds");
		//获得串并案id
		String seriesCaseId=String.valueOf(idService.getId());
		SeriesData seriesData=new SeriesData();
		//设置串并案信息
		SeriesCaseInfo caseinfo=new SeriesCaseInfo();
		caseinfo.setId(seriesCaseId);
		caseinfo.setSeriesCaseNo(seriesCaseNo);
		caseinfo.setDeleteFlag("0");
		caseinfo.setCreateDate(date);
		caseinfo.setCreateUser(Integer.parseInt(userId));
		caseinfo.setSeriesCaseName(seriesCaseName);
		caseinfo.setSeriesDesc(seriesDesc);
		caseinfo.setSeriesReason(seriesReason);
		caseinfo.setUpdateDate(date);
		caseinfo.setUpdateUser(Integer.parseInt(userId));
		seriesData.setSeriesCaseInfo(caseinfo);
		int caseCount=0;
		List<SeriesCaseDetail> sdlist=new ArrayList<SeriesCaseDetail>();
		//设置串并案案件详细信息
		if(!StringUtils.isEmpty(seriesCaseIds)){
		  seriesCaseIds=seriesCaseIds.substring(0,seriesCaseIds.length()-1);
		  String caseInfos[]=seriesCaseIds.split(",");
		  caseCount=caseInfos.length;
		  seriesData.setCaseCount(caseCount);
		  for(String info:caseInfos){
			  String cinfo[]=info.split(":");
			  SeriesCaseDetail caseDetail=new SeriesCaseDetail();
			  caseDetail.setCaseId(cinfo[0]);
			  caseDetail.setCreateDate(date);
			  caseDetail.setCreateUser(Integer.parseInt(userId));
			  caseDetail.setDeleteFlag("0");
			  caseDetail.setEntrustNo(cinfo[1]);
			  caseDetail.setId(String.valueOf(idService.getId()));
			  caseDetail.setSeriesCaseId(seriesCaseId);
			  caseDetail.setSeriesPerson(seriesReason);
			  sdlist.add(caseDetail);
		  }
		  //保存串并案信息
		  try{
		      seriesData.setSeriesCaseDetails(sdlist);
		      this.seriesService.seriesSave(seriesData);
		  }catch(Exception e){
			  logger.error("保存串并案信息出错,"+e.toString());
			  return "0";
		  }
		}
		return "1";
	}
	
	/**
	 * 点击串并案编号，跳转到我的串并案详情
	 * @return
	 */
	@RequestMapping("showSeriesCaseinfo")
	public String showSeriesCaseinfo(Model model){
		String csrfToken = CsrfTokenManager.getToken();
		model.addAttribute("csrf", csrfToken);
		String seriesId=request.getParameter("seriesId");
		//处理串并案依据查询参数
		Dic cbaType = new Dic();
		cbaType.setDicTypeKey("CBAYJ");
		List<Dic> cbaTypeList = dicService.queryAll(cbaType);
		try {
			SeriesData data=seriesService.getSeriesDetail(seriesId);
			if(data!=null){
				if(data.getSeriesCaseInfo()!=null){
					String seriesReason=data.getSeriesCaseInfo().getSeriesReason();
					if (!StringUtils.isEmpty(seriesReason)){
						String srArr[]=seriesReason.split(",");
						StringBuffer sersper=new StringBuffer();
						for (String ser:srArr){
						   String dicName=this.getDictName(cbaTypeList, ser);
						   sersper.append(dicName).append(",");
						}
						data.getSeriesCaseInfo().setSeriesReason(sersper.subSequence(0, sersper.length()-1).toString());
					}
					request.setAttribute("Seriesdata",data);
				}
			}
			
		} catch (Exception e) {
			logger.error("查询串并案详情出错,"+e.getLocalizedMessage());
		}
		return Common.JSP_PATH + "/seriescase/series_case_detail";
	}
	
	
	/***
	 * 查询我的串并案，案件详情列表
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryCaseInfoDetailInfor")
	public   DataResponse<SeriesCaseInfo> queryCaseInfoDetailInfor(DataRequest dataRequest){
		//获得当前页码和每一页展示的记录条数
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		String seriesId=request.getParameter("seriesId");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("seriesCaseId",seriesId);
		//地市字典
		Dic dicCity = new Dic();
		dicCity.setDicTypeKey("city");
		List<Dic> dicListCity = dicService.queryAll(dicCity);
		//案件类型字典
		Dic dicCaseType = new Dic();
		dicCaseType.setDicTypeKey("case_type");
		List<Dic> CaseTypeProv = dicService.queryAll(dicCaseType);
		 List<SeriesCaseInfoDetailModel> slist=new ArrayList<SeriesCaseInfoDetailModel>();
		try {
			@SuppressWarnings("rawtypes")
			DataResponse dataResponse = seriesService.queryCaseInSeri(getPageView(String.valueOf(page),String.valueOf(rows)), paraMap);
		    List<CaseInfoInSeries> rlist=dataResponse.getRows();
		    if (rlist!=null && rlist.size()>0){
		    	for(CaseInfoInSeries ser:rlist){
		    		SeriesCaseInfoDetailModel model=new SeriesCaseInfoDetailModel();
		    		model.setNo(ser.getNo());
		    		StringBuffer caseNo=new StringBuffer("");
		    		if(ser.getCaseInfo()!=null){
		    			String eNo=ser.getCaseInfo().getEntrustNo();
		    			if(!StringUtils.isEmpty(eNo)){
		    				model.setEntrustNo(eNo);
		    				caseNo.append("委托编号:").append(eNo).append("<br>");
		    			}
		    			String cNo=ser.getCaseInfo().getCaseNo();
		    			if(!StringUtils.isEmpty(cNo)){
		    			  caseNo.append("案件编号:").append(cNo);
		    			}
		    			String caseTypeCn=this.findCaseTypeZn(CaseTypeProv, ser.getCaseInfo().getCaseType(),"");
		    			model.setCaseName(ser.getCaseInfo().getCaseName());
		    			
		    			model.setCaseType(caseTypeCn);
		    		}
		    		if(ser.getEntrustInfo()!=null){
		    			String city=this.getDictName(dicListCity, ser.getEntrustInfo().getCityCode());
		    			model.setArea(city);
		    		}
		    		model.setCaseNo(caseNo.toString());
		    		StringBuffer seriesClue=new StringBuffer("");
		    		List<SeriesCaseAlipay> alist=ser.getClueAlipays();
		    		if(alist!=null && alist.size()>0){
		    		 int asize=alist.size();
		   	    	 for (int i=0;i<asize;i++){
		   	    		SeriesCaseAlipay apay=alist.get(i);
		   	    		 if (i==0){
		   	    			seriesClue.append("阿里账号,");
		   	    		 }
		   	    		seriesClue.append(apay.getAlipayCard()).append("<br>");
		   	    	 }
		    		}
		    		
		    		List<SeriesCaseCertificate> clist=ser.getClueCertificates();
		    		if(clist!=null && clist.size()>0){
		    		 int csize=clist.size();
		   	    	 for (int i=0;i<csize;i++){
		   	    		SeriesCaseCertificate cert=clist.get(i);
		   	    		if (i==0){
		   	    			seriesClue.append("证件号,");
		   	    		 }
		   	    		seriesClue.append(cert.getCertificateNo()).append("<br>");
		   	    	 }
		    		}
		    		
		    		List<SeriesCaseBankcard> blist=ser.getClueBankcards();
		    		if(blist!=null && blist.size()>0){
		    		 int bsize=blist.size();
		   	    	 for (int i=0;i<bsize;i++){
		   	    		SeriesCaseBankcard bank=blist.get(i);
		   	    		if (i==0){
		   	    			seriesClue.append("银行卡号,");
		   	    		 }
		   	    		seriesClue.append(bank.getBankcard()).append("<br>");
		   	    	 }
		    		}
		    		List<SeriesCaseTelephone> tlist=ser.getClueTelephones();
		    		if(tlist!=null && tlist.size()>0){
		    		 int tsize=tlist.size();
		   	    	 for (int i=0;i<tsize;i++){
		   	    		SeriesCaseTelephone tel=tlist.get(i);
		   	    		if (i==0){
		   	    			seriesClue.append("电话号码,");
		   	    		 }
		   	    		seriesClue.append(tel.getTelephone()).append("<br>");
		   	    	 }
		    		}
		    		model.setSeriesClueList(seriesClue.toString());
		    		slist.add(model);
		    	}
		    }
		} catch (Exception e) {
			logger.error("查询我的串并案案件详情失败,"+e.toString());
		}
		pageView.setRecords(slist);
		return pageView.getDataResponse();
	}
	
	/**
	 * 查看案件详细信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("showCaseinfo")
	public EntrustApplyData showCaseinfo(Model model){
		String csrfToken = CsrfTokenManager.getToken();
		model.addAttribute("csrf", csrfToken);
		String entrustNo=request.getParameter("entrustNo");
		EntrustApplyData data=null;
		if(!StringUtils.isEmpty(entrustNo)){
			try {
				data = entrustApplyService.getByEntrustNo(entrustNo);
				//案件类型
				Dic dicCaseType = new Dic();
				dicCaseType.setDicTypeKey("case_type");
				List<Dic> clist = dicService.queryAll(dicCaseType);
				if(data!=null){
					if(data.getCaseInfo()!=null){
						String caseTypeCn=this.findCaseTypeZn(clist, data.getCaseInfo().getCaseType(),"");
						data.getCaseInfo().setCaseType(caseTypeCn);
					}
				}
			} catch (Exception e) {
				logger.error("根据委托编号查询案件信息失败,"+e.toString());
			}
			return data;
		}
		
		return data;
	}
	
	
	
	/**
	 * 删除我的串并案，支持单条和批量
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteSeriesCases")
	public boolean deleteSeriesCases(Model model){
		String csrfToken = CsrfTokenManager.getToken();
		model.addAttribute("csrf", csrfToken);
		String seriesIds=request.getParameter("seriesIds");
		if(!StringUtils.isEmpty(seriesIds)){
			String ids[]=seriesIds.split(",");
			if(ids.length==1){
			  try {
				     seriesCaseInfoService.delete(ids[0]);
			      } catch (Exception e) {
				     logger.error("删除串并案失败，id为:"+ids[0]+e.toString());
			      }
			}else{
				 List<String> idList=Arrays.asList(ids);
				 try {
					seriesCaseInfoService.deleteBatch(idList);
				} catch (Exception e) {
					logger.error("批量删除删除串并案失败"+e.toString());
				}
			}
		}else{
			logger.info("需要删除的串并案id为空!");
		}
		return true;
	}
	
	/**
	 * 点击编辑按钮，跳转到编辑我的串并案
	 * @return
	 */
	@RequestMapping("showSeriesCaseinfoToEdit")
	public String showSeriesCaseinfoToEdit(Model model){
		String csrfToken = CsrfTokenManager.getToken();
		model.addAttribute("csrf", csrfToken);
		String seriesId=request.getParameter("seriesId");
		//处理串并案依据查询参数
		Dic cbaType = new Dic();
		cbaType.setDicTypeKey("CBAYJ");
		List<Dic> cbaTypeList = dicService.queryAll(cbaType);
		try {
			SeriesData data=seriesService.getSeriesDetail(seriesId);
			StringBuffer sersper=new StringBuffer();
			if(data!=null){
				if(data.getSeriesCaseInfo()!=null){
					String seriesReason=data.getSeriesCaseInfo().getSeriesReason();
					if (!StringUtils.isEmpty(seriesReason)){
						for (Dic dic:cbaTypeList){
							String fsString="";
							String className=Constant.SERIES_REASON_DEFAULT_CLASSNAME;
							if (seriesReason.indexOf(dic.getDicKey())>=0){
								className=Constant.SERIES_REASON_SUCESS_CLASSNAME;
								fsString="<i class='fa fa-check'></i>";
							}
							sersper.append("<label class='").append(className).append("'");	
							sersper.append("<input type='checkbox' class='toggle'>");
							sersper.append(fsString);
							sersper.append(dic.getDicName());
							sersper.append("</label>");
						}
					}
					data.getSeriesCaseInfo().setSeriesReason(sersper.toString());
				}
				request.setAttribute("Seriesdata",data);
				request.setAttribute("seriesCaseNum",data.getCaseCount());
				//初始化案件类型字典数据
				Dic dicCaseType = new Dic();
				dicCaseType.setDicTypeKey("case_type");
				List<Dic> CaseTypeProv = dicService.queryAll(dicCaseType);
				JSONArray jarrCaseType = new JSONArray();
			     for (Dic res : CaseTypeProv) {
						JSONObject jobj = new JSONObject();
						jobj.put("castType_code", res.getDicKey());
						jobj.put("castType_name", res.getDicName());
						jarrCaseType.add(jobj);
				  }
			     model.addAttribute("caseTypeJson", jarrCaseType.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询串并案详情出错,"+e.getLocalizedMessage());
		}
		return Common.JSP_PATH + "/seriescase/series_case_edit";
	}
	
	/***
	 * 点击编辑按钮，跳转到我的串并案，案件详情列表
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("queryCaseInfoDetailInforToEdit")
	public   DataResponse<SeriesCaseInfo> queryCaseInfoDetailInforToEdit(DataRequest dataRequest){
		//获得当前页码和每一页展示的记录条数
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		String seriesId=request.getParameter("seriesId");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("seriesCaseId",seriesId);
		//地市字典
		Dic dicCity = new Dic();
		dicCity.setDicTypeKey("city");
		List<Dic> dicListCity = dicService.queryAll(dicCity);
		//案件类型字典
		Dic dicCaseType = new Dic();
		dicCaseType.setDicTypeKey("case_type");
		List<Dic> CaseTypeProv = dicService.queryAll(dicCaseType);
		List<SeriesCaseInfoDetailModel> slist=new ArrayList<SeriesCaseInfoDetailModel>();
		try {
			@SuppressWarnings("rawtypes")
			DataResponse dataResponse = seriesService.queryCaseInSeri(getPageView(String.valueOf(page),String.valueOf(rows)), paraMap);
		    List<CaseInfoInSeries> rlist=dataResponse.getRows();
		    if (rlist!=null && rlist.size()>0){
		    	for(CaseInfoInSeries ser:rlist){
		    		SeriesCaseInfoDetailModel model=new SeriesCaseInfoDetailModel();
		    		model.setNo(ser.getNo());
		    		StringBuffer caseNo=new StringBuffer("");
		    		if(ser.getCaseInfo()!=null){
		    			String eNo=ser.getCaseInfo().getEntrustNo();
		    			if(!StringUtils.isEmpty(eNo)){
		    				model.setEntrustNo(eNo);
		    				caseNo.append("委托编号:").append(eNo).append("<br>");
		    			}
		    			String cNo=ser.getCaseInfo().getCaseNo();
		    			if(!StringUtils.isEmpty(cNo)){
		    			  caseNo.append("案件编号:").append(cNo);
		    			}
		    			String caseTypeCn=this.findCaseTypeZn(CaseTypeProv, ser.getCaseInfo().getCaseType(),"");
		    			model.setCaseName(ser.getCaseInfo().getCaseName());
		    			model.setCaseType(caseTypeCn);
		    		}
		    		if(ser.getEntrustInfo()!=null){
		    			String city=this.getDictName(dicListCity, ser.getEntrustInfo().getCityCode());
		    			model.setArea(city);
		    		}
		    		model.setCaseNo(caseNo.toString());
		    		StringBuffer seriesClue=new StringBuffer("");
		    		List<SeriesCaseAlipay> alist=ser.getClueAlipays();
		    		if(alist!=null && alist.size()>0){
		    		 int asize=alist.size();
		   	    	 for (int i=0;i<asize;i++){
		   	    		SeriesCaseAlipay apay=alist.get(i);
		   	    		 if (i==0){
		   	    			seriesClue.append("阿里账号,");
		   	    		 }
		   	    		seriesClue.append(apay.getAlipayCard()).append("<br>");
		   	    	 }
		    		}
		    		
		    		List<SeriesCaseCertificate> clist=ser.getClueCertificates();
		    		if(clist!=null && clist.size()>0){
		    		 int csize=clist.size();
		   	    	 for (int i=0;i<csize;i++){
		   	    		SeriesCaseCertificate cert=clist.get(i);
		   	    		if (i==0){
		   	    			seriesClue.append("证件号,");
		   	    		 }
		   	    		seriesClue.append(cert.getCertificateNo()).append("<br>");
		   	    	 }
		    		}
		    		
		    		List<SeriesCaseBankcard> blist=ser.getClueBankcards();
		    		if(blist!=null && blist.size()>0){
		    		 int bsize=blist.size();
		   	    	 for (int i=0;i<bsize;i++){
		   	    		SeriesCaseBankcard bank=blist.get(i);
		   	    		if (i==0){
		   	    			seriesClue.append("银行卡号,");
		   	    		 }
		   	    		seriesClue.append(bank.getBankcard()).append("<br>");
		   	    	 }
		    		}
		    		List<SeriesCaseTelephone> tlist=ser.getClueTelephones();
		    		if(tlist!=null && tlist.size()>0){
		    		 int tsize=tlist.size();
		   	    	 for (int i=0;i<tsize;i++){
		   	    		SeriesCaseTelephone tel=tlist.get(i);
		   	    		if (i==0){
		   	    			seriesClue.append("电话号码,");
		   	    		 }
		   	    		seriesClue.append(tel.getTelephone()).append("<br>");
		   	    	 }
		    		}
		    		model.setSeriesClueList(seriesClue.toString());
		    		model.setSeriesStatus("1");
		    		model.setCaseId(ser.getCaseId());
		    		model.setSeriesStatusCn("已串并");
		    		slist.add(model);
		    	}
		    }
		} catch (Exception e) {
			logger.error("查询我的串并案案件详情失败,"+e.toString());
		}
		pageView.setRecords(slist);
		return pageView.getDataResponse();
	}
	
	/**
	 * 修改我的串并案
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("updateSeriesCaseInfo")
	public String updateSeriesCaseInfo() throws Exception{
		//用户id
		String userId = SpringHolder.CACHE_SESSION_SERVICE.getAttribute("userSessionId").toString();
		//新增日期
		Date date=new Date();
		HttpServletRequest request=this.getHttpServletRequest();
		//串并案名称
		String seriesCaseName=request.getParameter("seriesCaseName");
		//串并描述
		String seriesDesc=request.getParameter("seriesDesc");
		//串并案件id列表
		String seriesCaseIds=request.getParameter("seriesCaseIds");
		//串并案id
		String seriesId=request.getParameter("seriesId");
		
		String newSeriesId=String.valueOf(idService.getId());
		//设置串并案信息
		SeriesData seriesData=seriesService.getSeriesDetail(seriesId);
		if (seriesData!=null){
			SeriesCaseInfo caseinfo=seriesData.getSeriesCaseInfo();
			caseinfo.setId(newSeriesId);
			caseinfo.setSeriesCaseName(seriesCaseName);
			caseinfo.setSeriesDesc(seriesDesc);
			caseinfo.setUpdateDate(date);
			caseinfo.setUpdateUser(Integer.parseInt(userId));
			seriesData.setSeriesCaseInfo(caseinfo);
			int caseCount=0;
			List<SeriesCaseDetail> sdlist=new ArrayList<SeriesCaseDetail>();
			//设置串并案案件详细信息
			if (!StringUtils.isEmpty(seriesCaseIds)){
			  seriesCaseIds=seriesCaseIds.substring(0,seriesCaseIds.length()-1);
			  String caseInfos[]=seriesCaseIds.split(",");
			  caseCount=caseInfos.length;
			  seriesData.setCaseCount(caseCount);
			  for (String info:caseInfos){
				  String cinfo[]=info.split(":");
				  SeriesCaseDetail caseDetail=new SeriesCaseDetail();
				  caseDetail.setCaseId(cinfo[0]);
				  caseDetail.setCreateDate(date);
				  caseDetail.setCreateUser(Integer.parseInt(userId));
				  caseDetail.setDeleteFlag("0");
				  caseDetail.setEntrustNo(cinfo[1]);
				  caseDetail.setId(String.valueOf(idService.getId()));
				  caseDetail.setSeriesCaseId(newSeriesId);
				  caseDetail.setSeriesPerson(caseinfo.getSeriesReason());
				  sdlist.add(caseDetail);
			  }
			  //保存串并案信息
			  try{
			      seriesData.setSeriesCaseDetails(sdlist);
			      if(sdlist.size()>0){
			        this.seriesService.seriesSave(seriesData);
			      }else{
			    	  List<String> idlist=new ArrayList<String>();
			    	  idlist.add(seriesId);
			    	  seriesCaseInfoService.deleteBatch(idlist);
			      }
			  }catch(Exception e){
				  e.printStackTrace();
				  logger.error("修改串并案信息出错,"+e.toString());
				  return "0";
			  }
			}
			 return "1";
		}else{
			return "0";
		}
	}
	
	
	@RequestMapping("queryCaseInfo")
	public String queryCaseInfo() throws Exception{
		String csrfToken = CsrfTokenManager.getToken();
		this.getHttpServletRequest().setAttribute("csrf", csrfToken);
		HttpServletRequest request=this.getHttpServletRequest();
		String entrustNo=request.getParameter("entrustNo");
		request.setAttribute("entrustNo", entrustNo);
		//查询委托信息
		EntrustApplyData data=entrustApplyService.getByEntrustNo(entrustNo);
		request.setAttribute("entrustData", data);
		request.setAttribute("showMemu","0");
		return Common.JSP_PATH+"/seriescase/series_caseinfo_view";
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
	 * 根据串并案依据字典代码，查询出字典名称
	 * @param list 串并案依据集合
	 * @param dictKey 字典码值
	 * @return
	 */
	private String getDictName(List<Dic> list,String dictKey){
		if (!StringUtils.isEmpty(dictKey)){
			for (Dic dic:list){
				if (dic.getDicKey().equals(dictKey)){
					return dic.getDicName();
				}
			}
		}
		return "";
	}
	
	/**
	 * 获得当前节点的所有子节点
	 * @param list
	 * @param dicroot 节点id
	 * @return
	 */
	private List<Dic> getSelectList(List<Dic> list,String dicroot){
		List<Dic> rlist=new ArrayList<Dic>();
		if(list!=null && list.size()>0){
			for(Dic dic:list){
				if(!StringUtils.isEmpty(dic.getDicParentKey())){
				 if (dic.getDicParentKey().equals(dicroot)){
					rlist.add(dic);
				 }
				}
			}
		}
		return rlist;
	}
	
	/**
	 * 设置串并案依据列表
	 * @param select 带串并记录
	 * @param clues  串并依据字符串
	 * @param typeList  串并依据类型
	 */
	private void setSeriesClueString(CaseDataForSelect select, StringBuffer clues,CaseInforForSelectModel model){
	    //设置串并类型
		StringBuffer cluesTypes=new StringBuffer("");
		 //串并依据-阿里账号
	    List<SeriesCaseAlipay> slist=select.getClueAlipays();
	     if (slist!=null && slist.size()>0){
	    	cluesTypes.append("alipayno,");
	    	int ssize=slist.size();
	    	 if (ssize>0){
	    		for (int i=0;i<ssize;i++){
	    			SeriesCaseAlipay alipay=slist.get(i);
	    			 if (i==0){
	    			   clues.append("阿里账号,");
	    			 }
	    			 clues.append(alipay.getAlipayCard()).append("<br>");
		    	}
	    	 }
	     }
	    //串并依据-银行卡号
	    List<SeriesCaseBankcard> bklist= select.getClueBankcards();
	    if (bklist!=null && bklist.size()>0){
	    	cluesTypes.append("bankcard,");
	    	int bsize=bklist.size();
	    	 for (int i=0;i<bsize;i++){
	    		SeriesCaseBankcard bank=bklist.get(i);
	    		 if (i==0){
	    			clues.append("银行卡号,");
	    		 }
	    		 clues.append(bank.getBankcard()).append("<br>");
	    	 }
	    }
	   //串并案依据-证件号
	   List<SeriesCaseCertificate> clist=select.getClueCertificates();
	   if (clist!=null && clist.size()>0){
		   cluesTypes.append("cardno,");
		   int csize=clist.size();
		    for (int i=0;i<csize;i++){
			   SeriesCaseCertificate certificate=clist.get(i);
			    if (i==0){
				   clues.append("证件号,");
			    }
			    clues.append(certificate.getCertificateNo()).append("<br>");
		    }
	    }
	   //串并案依据
	   List<SeriesCaseTelephone> tlist=select.getClueTelephones();
	    if (tlist!=null && tlist.size()>0){
	       cluesTypes.append("mobile,");
	       int tsize=tlist.size();
	        for (int i=0;i<tsize;i++){
	            SeriesCaseTelephone tel=tlist.get(i);
	             if(i==0){
	            	 clues.append("电话号码,");
	             }
	             clues.append(tel.getTelephone()).append("<br>");
	        }
	    }
	    if(!StringUtils.isEmpty(cluesTypes.toString())){
	      model.setSeriesTypes(cluesTypes.toString().substring(0,cluesTypes.length()-1));
	    }
	}
}
