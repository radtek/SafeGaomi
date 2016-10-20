package com.hisign.publicsafety.controller.entrust;
import java.util.ArrayList;
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
import com.hisign.publicsafety.common.api.CacheSessionService;
import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.Account;
import com.hisign.publicsafety.entity.Dic;
import com.hisign.publicsafety.entity.EntrusAttachment;
import com.hisign.publicsafety.entity.EntrusInvestigationInfo;
import com.hisign.publicsafety.entity.EntrusInvestigationTypeinfo;
import com.hisign.publicsafety.entity.EntrustBaseInfo;
import com.hisign.publicsafety.entity.EntrustCaseInfo;
import com.hisign.publicsafety.entity.EntrustCheckList;
import com.hisign.publicsafety.entity.EntrustSuspectInfo;
import com.hisign.publicsafety.entity.EntrustVictimInfo;
import com.hisign.publicsafety.entity.composite.EntrustApplyData;
import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.service.EntrustApplyService;
import com.hisign.publicsafety.service.IdService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.PropertiesUtils;
import com.hisign.publicsafety.viewModel.multselect.MultSelectDataModel;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;
/**
 * @author fankai
 * @date 2015-8-7
 * 委托申请相关项目操作
 */
@Controller
@RequestMapping("/entrust/apply/")
public class EntrustApplyController extends BaseController {
	
	private static Logger logger = Logger.getLogger(EntrustApplyController.class);  

	/**
	 * 字典表相关服务类
	 */
	@Inject
	private DicService dicService;
	
	/**
	 * id生成相关服务类
	 */
	@Inject
	private IdService idService;
	
	/**
	 * 委托相关服务类
	 */
	@Inject
	private EntrustApplyService entrustApplyService;
	@Autowired
	private CacheSessionService cacheSessionService;
	
	/**
	 * 跳转到委托添加页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("index")
	public String index(Model model) throws Exception {
		String ftypes=PropertiesUtils.findPropertiesKey("includeFileTypes");
		model.addAttribute("ftypes", ftypes);
		HttpServletRequest request=this.getHttpServletRequest();
		//标识是否是公安用户登录，如果为公安用户首次登录，委托新增页面隐藏返回按钮
		String isFlag=(String) request.getParameter("isFlag");
		request.setAttribute("isFlag", isFlag);
		String entrustNo=request.getParameter("entrustNo");
		//操作类型 1 新增 2 修改 3 再次委托
		String type=request.getParameter("type");
		//获得登录用户单位编码
		String userUnitCode=(String) cacheSessionService.getAttribute("userUnitCode");
		Account account=(Account) cacheSessionService.getAttribute("userSession");
		this.getHttpServletRequest().setAttribute("userSession",account);
		Dic dicQueryType = new Dic();
		dicQueryType.setDicTypeKey("query_type");
		List<Dic> dicQueryProv = dicService.queryAll(dicQueryType);
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
		
		//type=1 为新增
		if(StringUtils.isEmpty(type)){
			type="1";
		}
		String selectCaseType="";
		if(type.equals("1")){
			//根据用户单位编码，生成委托编号
		    entrustNo=idService.getEntrustNo(userUnitCode);
		    request.setAttribute("actionType", "1");
		    logger.info("新增委托信息，委托编号为:"+entrustNo);
		}else if(type.equals("2")){//2 为编辑
			logger.info("修改委托信息，委托编号为:"+entrustNo);
			EntrustApplyData data=entrustApplyService.getByEntrustNo(entrustNo);
			//处理审核不通过的信息，最后一次审核不通过的信息高亮显示
			if(data.getCheckList()!=null && data.getCheckList().size()>0){
				List<EntrustCheckList> clist=data.getCheckList();
				//获得审核不通过的次数
				if(clist!=null && clist.size()>0){
					int num=0;
					for(EntrustCheckList check:clist){
						if(check.getCheckIspass()==0){
							num++;
						}
					}
					int cnum=num;
					//如果审核不通过的，最后一次审核信息需要在最上面显示
					for(int i=0;i<clist.size();i++){
						EntrustCheckList check=clist.get(i);
						if(check.getCheckIspass()==0){
							check.setRev1(String.valueOf(num));
							if(cnum==num){
								 //显示在最前面标识
							     check.setRev2("1");
							}else{
								 check.setRev2("0");
							}
							num--;
						}
					}
				}
				 //标识是否现实审核信息
				 request.setAttribute("isshowProcess","1");
			}else{
				request.setAttribute("isshowProcess","0");
			}
			//处理附件文书信息，同一个附件文件名和文件base64码用|分割，不同的附件用#分割
			StringBuffer attNames=new StringBuffer("");
			StringBuffer att=new StringBuffer("");
			if(data.getAttachments()!=null){
				for(int i=0;i<data.getAttachments().size();i++){
					EntrusAttachment bean=data.getAttachments().get(i);
					if(data.getAttachments().size()==1){
					    attNames.append(bean.getFileName()).append("|").append(bean.getFileContent());
					    att.append(bean.getFileName());
					}
					else{
						if(i!=data.getAttachments().size()-1){
							att.append(bean.getFileName()).append(",");
						   attNames.append(bean.getFileName()).append("|").append(bean.getFileContent()).append("#");
						}else{
						   attNames.append(bean.getFileName()).append("|").append(bean.getFileContent());
						   att.append(bean.getFileName());
						}
					}
				}
			}
			request.setAttribute("filename", att.toString());
			request.setAttribute("filenames", attNames.toString());
			//计算受害人、嫌疑人、协查信息数量
			if(data.getVictimInfo()!=null)
			    request.setAttribute("victimsize", data.getVictimInfo().size());
			if(data.getSuspectInfo()!=null){
				request.setAttribute("suspectsize", data.getSuspectInfo().size());
			}
			if(data.getInvestigateInfo()!=null){
				request.setAttribute("investigatesize", data.getInvestigateInfo().size());
			}
			//回显查询类型信息
			List<EntrusInvestigationInfo> qlist=data.getQueryInfo();
			if(qlist!=null && qlist.size()>0){
				for(Dic dic:dicQueryProv){
	        		for(EntrusInvestigationInfo info:qlist){
	        			if(dic.getDicKey().equals(info.getQueryType())){
	        				dic.setRev1("1");
	        			}
	        		}
	        	}
			}
			if(data!=null){
			   if(data.getCaseInfo()!=null){
				   selectCaseType=data.getCaseInfo().getCaseType();
			   }
			   request.setAttribute("entrustData", data);
			}
		}else if(type.equals("3")){//3位再次委托
			//获得委托序号，再次委托的委托序号与前几次委托序号相同
			String entrustSer=request.getParameter("entrustSer");
			logger.info("再次委托，委托需要为："+entrustSer+",重新生成的委托编号为:"+entrustNo);
			if(!StringUtils.isEmpty(entrustSer)){
				request.setAttribute("entrustSer", entrustSer);
				String userId = cacheSessionService.getAttribute("userSessionId").toString();
				EntrustBaseInfo para=new EntrustBaseInfo();
				para.setEntrustSer(entrustSer);
				para.setCreateUser(Integer.parseInt(userId));
				//获得上一次委托信息
				EntrustApplyData data=this.entrustApplyService.getLastByEntrustSer(para);
				if(data!=null){
					//委托次数增加一次
					request.setAttribute("entrustNumber", data.getEntrustInfo().getEntrustNumber()+1);
					//计算受害人、嫌疑人、协查信息数量
					if(data.getVictimInfo()!=null)
					    request.setAttribute("victimsize", data.getVictimInfo().size());
					if(data.getSuspectInfo()!=null){
						request.setAttribute("suspectsize", data.getSuspectInfo().size());
					}
					if(data.getInvestigateInfo()!=null){
						request.setAttribute("investigatesize", data.getInvestigateInfo().size());
					}
					//回显查询类型信息
					List<EntrusInvestigationInfo> qlist=data.getQueryInfo();
					if(qlist!=null && qlist.size()>0){
						for(Dic dic:dicQueryProv){
			        		for(EntrusInvestigationInfo info:qlist){
			        			if(dic.getDicKey().equals(info.getQueryType())){
			        				dic.setRev1("1");
			        			}
			        		}
			        	}
					}
					StringBuffer attNames=new StringBuffer("");
					StringBuffer att=new StringBuffer("");
					//处理附件文书信息
					if(data.getAttachments()!=null){
						logger.info("委托编号为:"+entrustNo+"存在"+data.getAttachments().size()+"个附件文书。");
						for(int i=0;i<data.getAttachments().size();i++){
							EntrusAttachment bean=data.getAttachments().get(i);
							if(data.getAttachments().size()==1){
							    attNames.append(bean.getFileName()).append("|").append(bean.getFileContent());
							    att.append(bean.getFileName());
							}
							else{
								if(i!=data.getAttachments().size()-1){
									att.append(bean.getFileName()).append(",");
								   attNames.append(bean.getFileName()).append("|").append(bean.getFileContent()).append("#");
								}else{
								   attNames.append(bean.getFileName()).append("|").append(bean.getFileContent());
								   att.append(bean.getFileName());
								}
							}
						}
					}
					request.setAttribute("filename", att.toString());
					request.setAttribute("filenames", attNames.toString());
					request.setAttribute("entrustData", data);
					if(data.getCaseInfo()!=null){
					  selectCaseType=data.getCaseInfo().getCaseType();
				   }
				}
			}
		}
		if (!StringUtils.isEmpty(selectCaseType)){
			for(Dic dic:CaseTypeProv){
				if(dic.getDicKey().equals(selectCaseType)){
					request.setAttribute("selectedCaseType", dic.getDicName());
					break;
				}
			}
		}
		//处理字典表相关信息
		JSONArray jarrProv = new JSONArray(); 
		//省份字典
		Dic dicProv = new Dic();
		dicProv.setDicTypeKey("province");
		List<Dic> dicListProv = dicService.queryAll(dicProv);
		for (Dic res : dicListProv) {//
			JSONObject jobj = new JSONObject();
			jobj.put("province_code", res.getDicKey());
			jobj.put("province_name", res.getDicName());
			jarrProv.add(jobj);
		}
		//地市字典
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
       
		//案件阶段字典
		Dic dicCaseStage = new Dic();
		dicCaseStage.setDicTypeKey("case_stage");
		List<Dic> CaseStageProv = dicService.queryAll(dicCaseStage);
		JSONArray jarrXCXX = new JSONArray();
		//协查信息字典
		Dic xcxxCity = new Dic();
		xcxxCity.setDicTypeKey("xcxx");
		List<Dic> xcxxList = dicService.queryAll(xcxxCity);
		for (Dic res : xcxxList) {
			JSONObject jobj = new JSONObject();
			jobj.put("code", res.getDicKey());
			jobj.put("name", res.getDicName());
			jarrXCXX.add(jobj);
		}
		
		model.addAttribute("dicJsonArr",dicService.getUnitJson());
		model.addAttribute("xcxxJson", jarrXCXX.toString());
		model.addAttribute("provinceJson", jarrProv.toString());
		model.addAttribute("cityJson", jarrCity.toString());
		model.addAttribute("caseTypeJson",caseTypejson);
		this.getHttpServletRequest().setAttribute("entrustNo", entrustNo);
		this.getHttpServletRequest().setAttribute("case_stageList",CaseStageProv);
		this.getHttpServletRequest().setAttribute("dicQueryList",dicQueryProv);
		this.getHttpServletRequest().setAttribute("dicQuerysize",dicQueryProv.size());
		this.getHttpServletRequest().setAttribute("xcxxList",xcxxList);
		request.setAttribute("actionType", type);
		String csrfToken = CsrfTokenManager.getToken();
		model.addAttribute("csrf", csrfToken);
		return Common.JSP_PATH + "/entrust/entrust_apply";
	}
	
	
	
	
	/**
	 * 保存或新增委托信息
	 * @param entrustApplyData
	 * @return
	 * @throws NumberFormatException 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("saveEntrustBaseInfo")
	public String saveEntrustBaseInfo(EntrustApplyData entrustApplyData) throws Exception{
		//用户id
		String userId = cacheSessionService.getAttribute("userSessionId").toString();
		//默认委托类型为 "取证"
		try{
		if(entrustApplyData!=null){
				String actionType=entrustApplyData.getActiontype();
				String typeInfo=entrustApplyData.getTypeinfo();
				if(typeInfo.equals("submit")){
					//保存之前判断是否存在已经反馈，但是未完成的时间超过一个星期委托，如果存在这不然保存和提交新建委托数据
					Date curDate=new Date();
					long curLongDate=curDate.getTime();
					String entrustTimeLimits=PropertiesUtils.findPropertiesKey("entrustTimeLimits");
					long day=7;
					try{
					    day=Long.parseLong(entrustTimeLimits);
					}catch(Exception  e){
						logger.error("转化最大限制天数时出现异常,"+e.getLocalizedMessage());
					}
					long limitTime=curLongDate-1000*60*60*24*day;
					int count = entrustApplyService.getUnfinishCount(limitTime,Integer.parseInt(userId));
					logger.info("当前用户userid="+userId+",已反馈但超过一个星期未完成的委托数量为:"+count);
					if(count>0){//返回-1 标示存在超过一星期未完成的委托
						return "-1";
					}
				}
				if(entrustApplyData.getEntrustInfo()!=null){
					String isTake=entrustApplyData.getEntrustInfo().getIstake();
					if(!StringUtils.isEmpty(isTake)){
						   isTake="1";
					}else{
						   isTake="0";
					}
					entrustApplyData.getEntrustInfo().setIstake(isTake);
					String isReply=entrustApplyData.getEntrustInfo().getIsReply();
					if(!StringUtils.isEmpty(isReply)){
						   isReply="1";
					}else{
						   isReply="0";
					}
					entrustApplyData.getEntrustInfo().setIsReply(isReply);
					String isShareCaseinfo=entrustApplyData.getEntrustInfo().getIsShareCaseinfo();
					if(!StringUtils.isEmpty(isShareCaseinfo)){
						   isShareCaseinfo="1";
					}else{
						   isShareCaseinfo="0";
					 }
					entrustApplyData.getEntrustInfo().setIsShareCaseinfo(isShareCaseinfo);
					String isShareQueryresult=entrustApplyData.getEntrustInfo().getIsShareQueryresult();
					if(!StringUtils.isEmpty(isShareQueryresult)){
						isShareQueryresult="1";
					}else{
						isShareQueryresult="0";
					}
					 entrustApplyData.getEntrustInfo().setIsShareQueryresult(isShareQueryresult);
			}
			//获得委托信息
			EntrustBaseInfo entrustInfo =entrustApplyData.getEntrustInfo();
			//委托编号
			String entrustNo=entrustApplyData.getEntrustNo();
			if(StringUtils.isEmpty(entrustNo)){
				String userUnitCode=cacheSessionService.getAttribute("userUnitCode").toString();
				entrustNo=idService.getEntrustNo(userUnitCode);
				logger.info("生成新的委托编号为:"+entrustNo);
			}
			
			//删除标识
			String deleteFlag="0";
			if(StringUtils.isEmpty(userId)){
				userId="0";
			}
			Date createDate=new Date();
			//处理委托信息
			if(entrustInfo!=null){
				int entrustId= idService.getId();
				entrustInfo.setId(String.valueOf(entrustId));
				entrustInfo.setCreateDate(createDate);
				entrustInfo.setCreateUser(Integer.parseInt(userId));
				entrustInfo.setDeleteFlag(deleteFlag);
				entrustInfo.setEntrustNo(entrustNo);
				if(actionType.equals("1")){
					entrustInfo.setEntrustNumber(1);
					entrustInfo.setEntrustType("QZ");
					entrustInfo.setEntrustSer(String.valueOf(entrustId));
				}
				entrustInfo.setUpdateDate(new Date());
				entrustInfo.setUpdateUser(Integer.parseInt(userId));
				entrustInfo.setProcessState(entrustApplyData.getTypeinfo());
			}
			logger.info("委托基本信息处理完毕.");
			//处理案件信息
			EntrustCaseInfo caseInfo=entrustApplyData.getCaseInfo();
			if(caseInfo!=null){
				int caseId= idService.getId();
				caseInfo.setId(String.valueOf(caseId));
				caseInfo.setEntrustNo(entrustNo);
				caseInfo.setCreateDate(createDate);
				caseInfo.setCreateUser(Integer.parseInt(userId));
				caseInfo.setDeleteFlag(deleteFlag);
			}
			logger.info("案件信息处理完毕.");
			//受害人信息
			List<EntrustVictimInfo> pageVictimInfo=entrustApplyData.getVictimInfo();
			if(pageVictimInfo!=null && pageVictimInfo.size()>0){
				List<EntrustVictimInfo> victimInfo=new ArrayList<EntrustVictimInfo>();
				for(EntrustVictimInfo info:pageVictimInfo){
					//去掉 受害人姓名、身份证、手机号、淘宝号、支付宝号、地址都为空的记录
					if(!(StringUtils.isEmpty(info.getVictimName()) 
							&&StringUtils.isEmpty(info.getVictimCardNo())
							&&StringUtils.isEmpty(info.getVictimTelephone())
							&&StringUtils.isEmpty(info.getVictimTaobaoNo())
							&&StringUtils.isEmpty(info.getVictimAlipay())
							&&StringUtils.isEmpty(info.getVictimAddress()))){
						    int victimId= idService.getId();
						    info.setId(String.valueOf(victimId));
						    info.setEntrustNo(entrustNo);
						    info.setCreateDate(createDate);
						    info.setCreateUser(Integer.parseInt(userId));
						    info.setDeleteFlag(deleteFlag);
						    victimInfo.add(info);
					}
				}
				entrustApplyData.setVictimInfo(victimInfo);
			}
			logger.info("受害人信息处理完毕.");
			//嫌疑人信息
			List<EntrustSuspectInfo> pageSuspectInfo=entrustApplyData.getSuspectInfo();
			if(pageSuspectInfo!=null && pageSuspectInfo.size()>0){
				List<EntrustSuspectInfo> suspectInfo=new ArrayList<EntrustSuspectInfo>();
				for(EntrustSuspectInfo info:pageSuspectInfo){
					//去掉嫌疑人人姓名、身份证、手机号、淘宝号、支付宝号、QQ、地址都为空的记录
					if(!(StringUtils.isEmpty(info.getSuspectAlipay()) 
							&&StringUtils.isEmpty(info.getSuspectCardNo())
							&&StringUtils.isEmpty(info.getSuspectEmail())
							&&StringUtils.isEmpty(info.getSuspectName())
							&&StringUtils.isEmpty(info.getSuspectQq())
							&&StringUtils.isEmpty(info.getSuspectTaobaoNo())
							&&StringUtils.isEmpty(info.getSuspectTelephone()))){
						    int suspectId= idService.getId();
						    info.setEntrustNo(entrustNo);
						    info.setId(String.valueOf(suspectId));
						    info.setCreateDate(createDate);
						    info.setCreateUser(Integer.parseInt(userId));
						    info.setDeleteFlag(deleteFlag);
						    suspectInfo.add(info);
					}
				}
				entrustApplyData.setSuspectInfo(suspectInfo);
			}
			logger.info("嫌疑人信息处理完毕.");
			//处理委托查询信息
			List<EntrusInvestigationInfo> pageQueryInfo=entrustApplyData.getQueryInfo();
			if(pageQueryInfo!=null && pageQueryInfo.size()>0){
				List<EntrusInvestigationInfo> queryInfo=new ArrayList<EntrusInvestigationInfo>();
				for(EntrusInvestigationInfo info:pageQueryInfo){
					if(!StringUtils.isEmpty(info.getQueryType())){
						 String queryTypes[]=info.getQueryType().split(",");
						 for(String type:queryTypes){
							 int queryId= idService.getId();
							 EntrusInvestigationInfo infoNew=new EntrusInvestigationInfo();
							 infoNew.setId(String.valueOf(queryId));
							 infoNew.setCreateDate(createDate);
							 infoNew.setCreateUser(Integer.parseInt(userId));
							 infoNew.setDeleteFlag(deleteFlag);
							 infoNew.setEntrustNo(entrustNo);
							 infoNew.setQueryType(type);
							 queryInfo.add(infoNew);
						 }
					}
				}
				entrustApplyData.setQueryInfo(queryInfo);
			}
			
			//处理委托协查信息
			List<EntrusAttachment> attachments=new ArrayList<EntrusAttachment>();
			List<EntrusInvestigationTypeinfo> pageInvestigateInfo=entrustApplyData.getInvestigateInfo();
			if(pageInvestigateInfo!=null && pageInvestigateInfo.size()>0){
				 List<EntrusInvestigationTypeinfo> investigateInfo=new ArrayList<EntrusInvestigationTypeinfo>();
			     for(EntrusInvestigationTypeinfo info:pageInvestigateInfo){
			    	 if(!(StringUtils.isEmpty(info.getInvestType())
			    		     &&StringUtils.isEmpty(info.getInvestContent())
			    		     &&StringUtils.isEmpty(info.getInvestDesc()))
			    			 ){
			    		     int investigateId= idService.getId();
			    		     info.setId(String.valueOf(investigateId));
			    		     info.setCreateDate(createDate);
			    		     info.setCreateUser(Integer.parseInt(userId));
			    		     info.setDeleteFlag(deleteFlag);
			    		     info.setEntrustNo(entrustNo);
			    		     investigateInfo.add(info);
			    	 }
			     }
			     entrustApplyData.setInvestigateInfo(investigateInfo);
			}
			logger.info("委托协查信息处理完毕.");
			//处理上传的文件附件
			Map<String,String>fmap=new HashMap<String,String>();
			String fileList=entrustApplyData.getFileList();
			String uploadfileName=entrustApplyData.getUploadfileName();
			if(!StringUtils.isEmpty(fileList)){
				String fs[]=fileList.split("#");
				if(fs!=null && fs.length>0){
					for(String bean:fs){
						if(!StringUtils.isEmpty(bean)){
						String fsd[]=bean.split("\\|");
						 if(fsd!=null && fsd.length>0){
						 	fmap.put(fsd[0], fsd[1]);
						 }
						}
					}
				}
			}
			if(!StringUtils.isEmpty(uploadfileName)){
				String ufs[]=uploadfileName.split(",");
				if(ufs!=null && ufs.length>0){
					for(String fn:ufs){
						EntrusAttachment atta=new EntrusAttachment();
						atta.setDeleteFlag("0");
						atta.setEntrustNo(entrustNo);
						String content=fmap.get(fn);
						atta.setFileContent(content);
						atta.setFileName(fn);
						atta.setId(String.valueOf(idService.getId()));
						atta.setUploadTime(createDate);
						atta.setUploadUser(Integer.parseInt(userId));
						if(content!=null){
						  attachments.add(atta);
						}
					}
					//entrustApplyData.setAttachments(attachments);
				}
			}
			logger.info("附件文书处理完毕.");
			entrustApplyService.save(entrustApplyData);
		}else{
			logger.error("委托信息为空。");
		}
		 }catch(Exception e){
			logger.error("保存或者修改委托信息失败,"+e.getLocalizedMessage());
			return "0";
		}
		return "1";
	}
}
