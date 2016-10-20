<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<!DOCTYPE html>
<html lang="en" class="no-js">
<meta name="current-res-id" content="RES_entrust_apply"/>
<meta http-equiv="content-type"  content="text/html;charset=UTF-8" />
<head>
<meta charset="utf-8"/>
<title>O2O数据侦查平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<meta name="MobileOptimized" content="320">
<style type="text/css"> 
.messageerror 
{ 
  color:#a94442
} 
.centerdiv{
  position:absolute; 
  top:50%;
}
</style> 

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed">
<%@ include file="../common/meun.jsp"%>
<div class="clearfix"> </div>
<div class="container"> 
  <!-- BEGIN CONTAINER -->
  <div class="page-container"> 
    <!-- BEGIN SIDEBAR --> 
    <!-- END SIDEBAR --> 
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
       <div class="col-md-12 margin-top-10"">
        <c:if test="${isshowProcess=='1'}">
          <c:forEach items="${entrustData.checkList}" var="bean"  varStatus="status">
            <c:if test="${bean.checkIspass==0}">
              <c:if test="${bean.rev2=='1'}">
               <div class="note note-warning">
              </c:if>
              <c:if test="${bean.rev2!='1'}">
                 <div class="note note-normal">
              </c:if>
               <h4 class="block">第${bean.rev1}次审核不通过</h4>
               <p>${bean.checkDesdc}</p>
            </c:if>
           </div>
          </c:forEach>
        </c:if> 
          <div class="portlet margin-top-10" id="form_wizard_1">
            <div class="portlet-body form">
                <div class="form-wizard">
                  <div class="form-body">
                    <ul class="nav nav-pills nav-justified steps">
                      <li id="litab1"  class="active">
                        <a href="#tab1" data-toggle="tab" class="step"> 
                          <span class="number"> 1 </span>
                          <span class="desc"> <i class="fa fa-check"></i> 委托信息 </span>
                          <input type="hidden" id="nextTabNo" value="tab2"/>
                        </a>
                      </li>
                      <li id="litab2"> 
                        <a href="#tab2" data-toggle="tab" class="step">
                         <span class="number"> 2 </span>
                         <span class="desc"> <i class="fa fa-check"></i> 案件信息 </span> 
                        </a>
                      </li>
                     <li id="litab3"> 
                       <a href="#tab3" data-toggle="tab" class="step active"> 
                        <span class="number"> 3 </span>
                        <span class="desc"> <i class="fa fa-check"></i> 需侦查信息 </span>
                       </a>
                     </li>
                    </ul>
                    <div id="bar" class="progress progress-striped" role="progressbar">
                       <div id="progress-bar" class="progress-bar progress-bar-success" style="width: 33%;"> </div>
                    </div>
                    <div id="alert-danger" class="alert alert-danger display-hide">
						<button class="close" data-close="alert"></button>
					</div>
				    <div id="alert-success" class="alert alert-success display-hide">
						<button class="close" data-close="alert"></button>
					</div>
					<form:form action="${ctx}/entrust/apply/saveEntrustBaseInfo.html" method="post"  id="form" name="form"   class="form-horizontal">

                    <div class="tab-content">
                      <div class="tab-pane active" id="tab1">
                      <c:if test="${actionType=='2'}">
                       <div class="form-group">
                          <label class="control-label col-md-3">委托编号 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                            <input type="text" class="form-control" value="${entrustData.entrustInfo.entrustNo}" disabled="disabled"/>
                          </div>
                        </div>
                        </c:if>
                        <div class="form-group">
                          <label class="control-label col-md-3">办案单位所在地 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-3">
                            <select id="select2_sample1"  name="entrustInfo.provinceCode" class="form-control select2">
                              <option value="1" >--请选择--</option>
                            </select>
                             <input type="hidden" id="provinceCodehiden" value="${entrustData.entrustInfo.provinceCode}">
                             <c:if test="${actionType=='3'}">
                               <input type="hidden" value="${entrustSer}" name="entrustInfo.entrustSer"/>
                               <input type="hidden" value="${entrustNumber}" name="entrustInfo.entrustNumber"/>
                             </c:if>
                          </div>
                          
                         
                         
                          <div class="col-md-3">
                            <select id="select2_sample2" name="entrustInfo.cityCode" class="form-control select2">
                              <option  value="1">--请选择--</option>
                            </select>
                             <input type="hidden" id="cityCodehiden" value="${entrustData.entrustInfo.cityCode}">
                          </div>
                          <div class="col-md-3">
                            <span id="select2_sample1_error" class="messageerror"></span><br>
                            <span id="select2_sample2_error" class="messageerror"></span>
                          </div>
                        </div>
                        
                        <div class="form-group">
			              <label class="col-md-offset-1 control-label col-md-2">办案单位名称<span class="required" aria-required="true"> * </span></label>
			              <div class="col-md-6 selected" >
						    <input type="input" class="form-control" id="treeviewinputview"   readonly="readonly" value="">
			                <input type="hidden" name="entrustInfo.unit" class="form-control" value="${entrustData.entrustInfo.unit}" id="entrustInfounithidden"/>
			                <input type="hidden" name="typeinfo" id="typeinfo" value="save">
                            <input type="hidden" name=entrustNo id="entrustNo" value="${entrustNo}">
                            <input type="hidden" name="submitispass" value="-1" id="submitispass">
                            <input type="hidden" name="saveispass" value="1" id="saveispass">
                            <input type="hidden" name="actiontype" id="actiontype" value="${actionType}">
                            <input type="hidden" name="CSRFToken" value="${csrf}" />
			              </div>
						 
						  <div class="col-md-3">
                            <span id="entrustInfounithidden_error" class="messageerror "></span>
                          </div>
                          <c:if test="${actionType=='2'}">
                           <div id="treeview"  style="width:439px;border:1px solid #428bca;border-radius:0 0 4px 4px;border-top:0;overflow-y:auto;position:absolute;left:260px;top:302px;bottom:auto;display:none;z-index:20;background-color:#fff;">
                          </c:if>
                          <c:if test="${actionType!='2'}">
                           <div id="treeview"  style="width:439px;border:1px solid #428bca;border-radius:0 0 4px 4px;border-top:0;overflow-y:auto;position:absolute;left:260px;top:252px;bottom:auto;display:none;z-index:20;background-color:#fff;">
                          </c:if>
                          <div class="select2-search selected">
	                          <input type="text" id="treeviewinput-search" value=""></input>
	                        </div>
							<div id="treeview-searchable" class="select2-results"></div>
							<div class="treeview" id="treeviewsearch-output">
							<ul class="select2-results"><li class="select2-no-results"></li></ul>
						  </div>
						  </div>
						  </div>
                    
                        <div class="form-group">
                          <label class="control-label col-md-3">办案人 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                            <c:if test="${actionType=='2'}">
                              <input type="text"   onblur="checkFormat('entrustInfopersonName','maxlengthnative')" class="form-control" value="${entrustData.entrustInfo.personName}"   id="entrustInfopersonName" name="entrustInfo.personName"/>
                            </c:if>
                            <c:if test="${actionType!='2'}">
                               <input type="text"  onblur="checkFormat('entrustInfopersonName','maxlengthnative')"  class="form-control" value="${userSession.trueName}"   id="entrustInfopersonName" name="entrustInfo.personName"/>
                            </c:if>
                          </div>
                          <div class="col-md-3">
                            <span id="entrustInfopersonName_error" class="messageerror"></span>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3"> 办案人联系电话 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                           <c:if test="${actionType=='2'}">
                             <input type="text" class="form-control"   value="${entrustData.entrustInfo.telephone}" id="entrustInfotelephone"  name="entrustInfo.telephone" onblur="checkFormat('entrustInfotelephone','telephone')" >
                           </c:if>
                           <c:if test="${actionType!='2'}">
                             <input type="text" class="form-control"   value="${userSession.phoneNumber}" id="entrustInfotelephone"  name="entrustInfo.telephone" onblur="checkFormat('entrustInfotelephone','telephone')" >
                           </c:if>
                          </div>
                          <div class="col-md-3">
                             <span id="entrustInfotelephone_error" class="messageerror "></span>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3"> 办案人职务 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                            <c:if test="${actionType=='2'}">
                              <input type="text" class="form-control" onblur="checkFormat('entrustInfotitle','maxlengthnative')"  value="${entrustData.entrustInfo.title}" id="entrustInfotitle" name="entrustInfo.title">
                            </c:if>
                            <c:if test="${actionType!='2'}">
                              <input type="text" class="form-control" onblur="checkFormat('entrustInfotitle','maxlengthnative')"  value="${userSession.duty}" id="entrustInfotitle" name="entrustInfo.title">
                            </c:if>
                          </div>
                          <div class="col-md-3">
                            <span id="entrustInfotitle_error" class="messageerror "></span>
                          </div>
                        </div>
                      
                        <div class="form-group">
                          <label class="control-label col-md-3" >是否来人调取 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                          <c:if test="${entrustData.entrustInfo.istake=='1'}">
                            <input type=checkbox   checked  name="entrustInfo.istake"  class="make-switch"
                             data-on-color="success"
                             data-off-color="success" 
                             data-size="normal"
                             data-on-text="<i class='fa fa-check'></i>是"
                             data-off-text="<i class='fa fa-check'></i>否"
                             >
                          </c:if>
                          <c:if test="${entrustData.entrustInfo.istake!='1'}">
                            <input type="checkbox"    name="entrustInfo.istake"  class="make-switch"
                             data-on-color="success"
                             data-off-color="success"
                             data-size="normal"
                             data-on-text="<i class='fa fa-check'></i>是"
                             data-off-text="<i class='fa fa-check'></i>否"
                             >
                          </c:if>
                          </div>
                        </div>
                        <div id="visitorinfo">
                        <div class="form-group">
                          <label class="control-label col-md-3">来访人姓名 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                            <input type="text" class="form-control" onblur="checkFormat('entrustInfovisitorName','maxlength')"  value="${entrustData.entrustInfo.visitorName}" id="entrustInfovisitorName" name="entrustInfo.visitorName">
                            <input type="hidden" id="istakehidden" value="${entrustData.entrustInfo.istake}"/>
                          </div>
                         <div class="col-md-3">
                           <span id="entrustInfovisitorName_error" class="messageerror "></span>
                         </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3">来访人联系方式 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                            <input type="text" class="form-control" maxlength="32" onblur="checkFormat('visitorTelephone','telephone')" value="${entrustData.entrustInfo.visitorTelephone}" id="visitorTelephone" name="entrustInfo.visitorTelephone">
                          </div>
                          <div class="col-md-3">
                            <span id="visitorTelephone_error" class="messageerror "></span>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3"> 来访人单位<span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                            <input type="text" class="form-control" onblur="checkFormat('visitorUnit','maxlength')" maxlength="100" value="${entrustData.entrustInfo.visitorUnit}" id="visitorUnit" name="entrustInfo.visitorUnit">
                          </div>
                          <div class="col-md-3">
                            <span id="visitorUnit_error" class="messageerror "></span>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3"> 来访人职务<span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                            <input type="text" class="form-control" onblur="checkFormat('visitorTitle','maxlength')"  value="${entrustData.entrustInfo.visitorTitle}" id="visitorTitle" name="entrustInfo.visitorTitle">
                          </div>
                          <div class="col-md-3">
                            <span id="visitorTitle_error" class="messageerror "></span>
                          </div>
                        </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3"> 案件办理阶段 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                            <div class="btn-group" data-toggle="buttons">
                             <c:forEach items="${case_stageList}" var="bean" varStatus="status">
                             <c:if test="${actionType=='1' }">
                                   <label id="entrustInfocaseStage${status.index}" name="entrustInfocaseStage" class="btn btn btn-default" onclick="SelectStatus('entrustInfocaseStage','${status.index}')"  >
                                   <input type="radio"  value="${bean.dicKey}"   name="entrustInfo.caseStage" class="toggle" >
                                      ${bean.dicName}
                             </c:if>
                             <c:if test="${actionType=='2' || actionType=='3'}">
                                 <c:if test="${entrustData.entrustInfo.caseStage==bean.dicKey}">
                                    <label id="entrustInfocaseStage${status.index}" name="entrustInfocaseStage" class="btn btn-success active" onclick="SelectStatus('entrustInfocaseStage','${status.index}')"  >
                                     <input type="radio"  value="${bean.dicKey}" checked="checked"   name="entrustInfo.caseStage" class="toggle" >
                                      ${bean.dicName}
                                 </c:if>
                                 <c:if test="${entrustData.entrustInfo.caseStage!=bean.dicKey}">
                                    <label id="entrustInfocaseStage${status.index}" name="entrustInfocaseStage" class="btn btn btn-default" onclick="SelectStatus('entrustInfocaseStage','${status.index}')"  >
                                <input type="radio"  value="${bean.dicKey}"   name="entrustInfo.caseStage" class="toggle" >
                                      ${bean.dicName}
                                 </c:if>
                             </c:if>
                              </label>
                             </c:forEach>
                            </div>
                          </div>
                         <div class="col-md-3">
                          <span id="entrustInfocaseStage_error" class="messageerror "></span>
                         </div>
                        </div>
                     
                      <div class="form-group">
                        <label class="control-label col-md-3"> 附件文书 <span class="required" aria-required="true"> * </span> </label>
                        <div class="col-md-5">
                          <input id="tags_1" type="text" name="uploadfileName" class="form-control" value="${filename}"/>
                          <input type="hidden" id="fileList" name=fileList value="${filenames}">
                        </div>
                        <div class="col-md-1">
                          <div class="fileinput fileinput-new" data-provides="fileinput">
                            <div class="fileinput fileinput-new" data-provides="fileinput">
                              <span class="btn btn-default btn-file">
                                <input type="file" id="fnamelist"  name="file" onchange="showPreview(this)" />+
                            </div>
                          </div>
                        </div>
                         <div class="col-md-3">
                          <span id="tags_1_error" class="messageerror "></span>
                         </div>
                        
                      </div>
                       <!-- 新控件 dropzone -->
                       <!--  
                      <div class="form-group">
                        <label class="control-label col-md-3"> 附件文书 <span class="required" aria-required="true"> * </span> </label>
                         <div class="col-md-6">
                            <div action="#" class="dropzone" id="my-dropzone">
							</div>
							<input type="hidden" id="fileList" name=fileList value="${filenames}">
							<input type="hidden" id="uploadfileName" name="uploadfileName" value="${filename}"/>
                        </div>
                      </div>
                      -->
                      <!-- 新控件 dropzone end --> 
                      <!-- 测试，使用新控件 -->

                        <div class="form-group">
                          <label class="control-label col-md-3">是否需要回函 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                            <c:if test="${entrustData.entrustInfo.isReply==1}">
                              <input type="checkbox" name="entrustInfo.isReply"  checked class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
                            </c:if>
                            <c:if test="${entrustData.entrustInfo.isReply!=1}">
                                 <input type="checkbox" name="entrustInfo.isReply"  class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
                            </c:if>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3"> 是否共享案件信息 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                            <c:if test="${entrustData.entrustInfo.isShareCaseinfo=='1'}">
                              <input type="checkbox" name="entrustInfo.isShareCaseinfo" checked class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
                            </c:if>
                            <c:if test="${entrustData.entrustInfo.isShareCaseinfo!='1'}">
                              <input type="checkbox" name="entrustInfo.isShareCaseinfo"   class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
                            </c:if>
                            <a href='javascript:void(0)' class='btn btn-default btn-xs black popovers' data-trigger='hover' data-container='body' data-placement='right' data-content='如果您选择是，你将共享案件信息给其他公安用户，同时，您也可以看到其他跟公安用户共享的案件信息。'><i class='fa fa-question'></i></a>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3"> 是否共享查询结果 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                          <c:if test="${entrustData.entrustInfo.isShareQueryresult=='1'}">
                             <input type="checkbox"  name="entrustInfo.isShareQueryresult"  checked   class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
                          </c:if>
                          <c:if test="${entrustData.entrustInfo.isShareQueryresult!='1'}">
                             <input type="checkbox"  name="entrustInfo.isShareQueryresult"  class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
                          </c:if>
                          <a href='javascript:void(0)' class='btn btn-default btn-xs black popovers' data-trigger='hover' data-container='body' data-placement='right' data-content='如果您选择是，你将共享结果信息给其他公安用户，同时，您也可以看到其他跟公安用户共享的结果信息。'><i class='fa fa-question'></i></a>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3"> 备注 </label>
                          <div class="col-md-6">
                            <textarea class="form-control"  name="entrustInfo.memo" rows="5" cols="3">${entrustData.entrustInfo.memo}</textarea>
                          </div>
                        </div>
                      </div>
                      
                      <div class="tab-pane" id="tab2">
                        <div class="form-group">
                          <label class="control-label col-md-3">案件名称 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                           <c:if test="${actionType=='3'}">
                              <input type="text" class="form-control" onblur="checkFormat('caseInfocaseName','maxlengthnative')"  readonly="readonly" value="${entrustData.caseInfo.caseName}" id="caseInfocaseName" name="caseInfo.caseName">
                           </c:if>
                           <c:if test="${actionType!='3'}">
                              <input type="text" class="form-control" onblur="checkFormat('caseInfocaseName','maxlengthnative')"  value="${entrustData.caseInfo.caseName}" id="caseInfocaseName" name="caseInfo.caseName">
                           </c:if>
                          </div>
                          <div class="col-md-3">
                            <span id="caseInfocaseName_error" class="messageerror "></span>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label class="control-label col-md-3"> 案件编号 </label>
                          <div class="col-md-6">
                           <c:if test="${actionType=='3'}">
                             <input type="text" class="form-control"  onblur="checkFormat('caseInfocaseNo','maxlengthnative')"  readonly="readonly" value="${entrustData.caseInfo.caseNo}" id="caseInfocaseNo" name="caseInfo.caseNo">
                           </c:if>
                           <c:if test="${actionType!='3'}">
                             <input type="text" class="form-control"  onblur="checkFormat('caseInfocaseNo','maxlengthnative')"  value="${entrustData.caseInfo.caseNo}" id="caseInfocaseNo" name="caseInfo.caseNo">
                           </c:if>
                          </div>
                          <div class="col-md-3">
                            <span id="caseInfocaseNo_error" class="messageerror "></span>
                          </div>
                        </div>
                        
                        <c:if test="${actionType!='3'}">
                          <div class="form-group">
			                <label class="control-label col-md-3">案发时间<span class="required" aria-required="true"> * </span> </label>
			                <div class="col-md-6">
			                    <input type="text"value="${entrustData.caseInfo.caseOccurDate}" maxlength="20" id="caseInfocaseOccurDate" name="caseInfo.caseOccurDate"  class="form-control date-set date form_datetime">
			                </div>
			                <div class="col-md-3">
			                  <span id="caseInfocaseOccurDate_error" class="messageerror "></span>
			                </div>
			              </div>
                        </c:if>
                        
                         <c:if test="${actionType=='3'}">
	                        <div class="form-group"  >
	                          <label class="control-label col-md-3">案发时间<span class="required" aria-required="true"> * </span> </label>
	                          <div class="col-md-6">
	                               <input type="text" class="form-control" maxlength="20" readonly="readonly" value="${entrustData.caseInfo.caseOccurDate}" id="caseInfocaseOccurDate" name="caseInfo.caseOccurDate">
	                          </div>
	                          <div class="col-md-3">
	                            <span id="caseInfocaseOccurDate_error" class="messageerror "></span>
	                          </div>
	                        </div>
                        </c:if>
 
                       
                        <div class="form-group">
                          <label class="control-label col-md-3"> 涉案品牌  </label>
                          <div class="col-md-6">
                           <c:if test="${actionType=='3'}">
                            <input type="text" class="form-control"   onblur="checkFormat('caseInfobrand','maxlengthnative')"  readonly="readonly" value="${entrustData.caseInfo.brand}" id="caseInfobrand" name="caseInfo.brand">
                           </c:if>
                           <c:if test="${actionType!='3'}">
                            <input type="text" class="form-control"  onblur="checkFormat('caseInfobrand','maxlengthnative')"    value="${entrustData.caseInfo.brand}" id="caseInfobrand" name="caseInfo.brand">
                           </c:if>
                          </div>
                          <div class="col-md-3">
                            <span id="caseInfobrand_error" class="messageerror "></span>
                          </div>
                        </div>
                        
                        
                        <div class="form-group">
                          <label class="control-label col-md-3"> 案件金额(人民币)<span class="required" aria-required="true"> * </span></label>
                          <div class="col-md-6">
                            <c:if test="${actionType=='3'}">
                               <input type="text" readonly="readonly"  id="caseMoneyAmout" value="${entrustData.caseInfo.caseMoneyAmout}" name="caseInfo.caseMoneyAmout" class="form-control" onblur="checkFormat('caseMoneyAmout','number')" />
                            </c:if>
                            <c:if test="${actionType!='3'}">
                               <input type="text"  id="caseMoneyAmout"  value="${entrustData.caseInfo.caseMoneyAmout}" name="caseInfo.caseMoneyAmout" class="form-control" onblur="checkFormat('caseMoneyAmout','money');" />
                            </c:if>
                          </div>
                          <div class="col-md-3">
                           <span id="caseMoneyAmout_error" class="messageerror "></span>
                          </div>
                        </div>
                        
                                              
                        <div class="form-group">
			              <label class="col-md-offset-1 control-label col-md-2">案(事)件类型</label>
			              <div class="col-md-6 selected1">
						    <input type="input" class="form-control" id="addCaseTypetreeinputview" readonly="readonly"  placeholder="" value="${selectedCaseType}">
			                <input type="hidden" name="caseInfo.caseType"  id="caseTypeDid" value="${entrustData.caseInfo.caseType}">
			              </div>
						  <div class="col-md-3">
			                <span id="select2_sample2_error" class="messageerror "></span>
			               </div>
						</div>
                       
                         <div id="addCaseTypetree"  style="width:439px;border:1px solid #428bca;border-radius:0 0 4px 4px;border-top:0;overflow-y:auto;position:absolute;left:260px;top:450px;bottom:auto;display:none;z-index:20;background-color:#fff;">
							<div class="select2-search selected1 ">
	                           <input type="text" id="addCaseTypetreeinput-search" value=""></input>
	                        </div>
							<div id="addCaseTypetree-searchable" class="select2-results"></div>
							<div class="treeview" id="addCaseTypetreesearch-output">
								<ul class="select2-results"><li class="select2-no-results">No matches found</li></ul>
							</div>
						</div>
                        
                       <!--  
                         <div class="form-group">
			                <label class="col-md-offset-1 control-label col-md-2">案(事)件类型 <span class="required" aria-required="true"> * </span> </label>
			                <div class="col-md-6">
			                <c:if test="${actionType!='3'}">
			                  <select id="select3_sample3"  name="caseInfo.caseType" class="form-control select2"  placeholder="案(事)件类型">
			                    <c:forEach items="${caseTypeList}" var="bean">
			                      <c:if test="${bean.dicLevel=='1'}">
			                        <optgroup label="${bean.dicName}">
			                      </c:if>
			                     <c:if test="${bean.dicLevel=='2'}">
			                        <c:if test="${entrustData.caseInfo.caseType==bean.dicKey}">
			                            <option selected="selected" value="${bean.dicKey}">${bean.dicName}</option>
			                        </c:if>
			                       	<c:if test="${entrustData.caseInfo.caseType!=bean.dicKey}">
			                            <option  value="${bean.dicKey}">${bean.dicName}</option>
			                        </c:if>
			                      </c:if>
			                    </c:forEach>
			                  </select>
			                 </c:if>
			                 <c:if test="${actionType=='3'}">
			                   <c:forEach items="${caseTypeList}" var="bean">
			                        <c:if test="${entrustData.caseInfo.caseType==bean.dicKey}">
			                          <input type="text"  class="form-control" readonly="readonly" value=" ${bean.dicName}"/>
			                          <input type="hidden"   name="caseInfo.caseType" value="${bean.dicKey}"/>
			                        </c:if>
			                    </c:forEach>
			                 </c:if>
			                </div>
			               <div class="col-md-3">
			                <span id="select2_sample2_error" class="messageerror "></span>
			               </div>
			              </div>
			            
			              -->
			              
                        <div class="form-group">
                          <label class="control-label col-md-3"> 简要案情 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6">
                           <c:if test="${actionType=='3'}">
                             <textarea class="form-control" readonly="readonly"    id="caseInfocaseInfo" name="caseInfo.caseInfo"  rows="5" cols="3">${entrustData.caseInfo.caseInfo}</textarea>
                           </c:if>
                           <c:if test="${actionType!='3'}">
                             <textarea class="form-control"   id="caseInfocaseInfo" placeholder="请填写报案时间、报案人、案件发生时间、案件相关方、案件基本情况"   name="caseInfo.caseInfo"  rows="5" cols="3">${entrustData.caseInfo.caseInfo}</textarea>
                           </c:if>
                          </div>
                         <div class="col-md-3">
                          <span id="caseInfocaseInfo_error" class="messageerror "></span>
                         </div>
                        </div>
                        <div class="form-group">
                         <div class="control-label col-md-1"></div>
                          <div class="col-md-10">
                            <div class="portlet-body">
                              <div class="table-responsive">
                                <label style="padding-left:8px;" ><font style="font-weight:bold">受害人信息</font><span class="required" aria-required="true"> &nbsp; </span> </label>
                                <div style="margin:0;padding:0; width:100%;height:1px;background-color:#999;overflow:hidden;"></div> 
                                <table class="table table-hover" id="victimInfotable">
                                  <thead>
                                    <tr>
                                      <th> 序号</th>
                                      <th> 姓名 </th>
                                      <th> 身份证 </th>
                                      <th> 手机 </th>
                                      <th> 淘宝账号 </th>
                                      <th> 支付宝账号 </th>
                                      <th> 所在地址</th>
                                      <th> 
                                       <input type="hidden" value="${victimsize}" id="victimInfono" name="victimInfono"/> 
                                       <input type="hidden" value="${victimsize}" id="victimInfonum" name="victimInfonum"/> 
                                       </th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                  <c:if test="${actionType=='3'}">
                                    <c:forEach items="${entrustData.victimInfo}" var="bean" varStatus="status">
                                        <tr id='victimInfo${status.index}'>
                                        <td> <span>${status.index+1}</span></td>
                                        <td> <input readonly="readonly" type='text'  value='${bean.victimName}' class='form-control'   id='victimInfo${status.index}victimName' name='victimInfo[${status.index}].victimName'> </td>
                                        <td> <input readonly="readonly" type='text'  value='${bean.victimCardNo}' class='form-control'  id='victimInfo${status.index}victimCardNo' onblur='checkFormat("victimInfo${status.index}victimCardNo","card")'  name='victimInfo[${status.index}].victimCardNo'><span id='victimInfo${status.index}victimCardNo_error' class='messageerror'></span> </td>
                                        <td> <input readonly="readonly" type='text'  value='${bean.victimTelephone}' class='form-control'  id='victimInfo${status.index}victimTelephone' onblur='checkFormat("victimInfo${status.index}victimTelephone","mobile")'   name='victimInfo[${status.index}].victimTelephone'> <span id='victimInfo${status.index}victimTelephone_error' class='messageerror'></span></td>
                                        <td> <input readonly="readonly" type='text'  value='${bean.victimTaobaoNo}' class='form-control'  id='victimInfo${status.index}victimTaobaoNo' name='victimInfo[${status.index}].victimTaobaoNo'> </td>
                                        <td> <input readonly="readonly" type='text'  value='${bean.victimAlipay}' class='form-control'  id='victimInfo${status.index}victimAlipay' name='victimInfo${status.index}.victimAlipay'> </td>
                                        <td> <input readonly="readonly" type='text'  value='${bean.victimAddress}' class='form-control'  id='victimInfo${status.index}victimAddress' name='victimInfo[${status.index}].victimAddress'> </td>
                                        </tr>
                                     </c:forEach>
                                  </c:if>
                                   <c:if test="${actionType!='3'}">
                                    <c:forEach items="${entrustData.victimInfo}" var="bean" varStatus="status">
                                        <tr id='victimInfo${status.index}'>
                                        <td> <span>${status.index+1}</span></td>
                                        <td> <input  type='text'  value='${bean.victimName}' class='form-control'   id='victimInfo${status.index}victimName' onblur='checkFormat("victimInfo${status.index}victimName","maxlength")' name='victimInfo[${status.index}].victimName'><span id='victimInfo${status.index}victimName_error' class='messageerror'></span> </td>
                                        <td> <input  type='text'  value='${bean.victimCardNo}' class='form-control'  id='victimInfo${status.index}victimCardNo' onblur='checkFormat("victimInfo${status.index}victimCardNo","card")'  name='victimInfo[${status.index}].victimCardNo'><span id='victimInfo${status.index}victimCardNo_error' class='messageerror'></span> </td>
                                        <td> <input  type='text'  value='${bean.victimTelephone}' class='form-control'  id='victimInfo${status.index}victimTelephone' onblur='checkFormat("victimInfo${status.index}victimTelephone","mobile")'   name='victimInfo[${status.index}].victimTelephone'> <span id='victimInfo${status.index}victimTelephone_error' class='messageerror'></span></td>
                                        <td> <input  type='text'  value='${bean.victimTaobaoNo}' class='form-control'  id='victimInfo${status.index}victimTaobaoNo' name='victimInfo[${status.index}].victimTaobaoNo' onblur='checkFormat("victimInfo${status.index}victimTaobaoNo","maxlength")'><span id='victimInfo${status.index}victimTaobaoNo_error' class='messageerror'></span> </td>
                                        <td> <input  type='text'  value='${bean.victimAlipay}' class='form-control'  id='victimInfo${status.index}victimAlipay' name='victimInfo${status.index}.victimAlipay'  onblur='checkFormat("victimInfo${status.index}victimAlipay","maxlength")'><span id='victimInfo${status.index}victimAlipay_error' class='messageerror'></span> </td>
                                        <td> <input  type='text'  value='${bean.victimAddress}' class='form-control'  id='victimInfo${status.index}victimAddress' name='victimInfo[${status.index}].victimAddress' onblur='checkFormat("victimInfo${status.index}victimAddress","maxlength")'><span id='victimInfo${status.index}victimAddress_error' class='messageerror'></span> </td>
                                        <td> <a href='javascript:void(0)' onclick='deleteItem("victimInfo","victimInfo${status.index}","victimInfo${status.index}")' class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='删除'><i class='fa fa-trash-o'></i></a></td>
                                        </tr>
                                     </c:forEach>
                                  </c:if>
                                  </tbody>
                                </table>
                                <c:if test="${actionType!='3'}">
                                <div align="right">
                                   <a href="javascript:void(0)" id="addvictimInfo" onclick="addItem('victimInfo')" class="btn btn-default btn-xs black tooltips" data-placement="left" data-original-title="新增"><i class="fa fa-plus-square-o"></i></a>
                                </div>
                                </c:if>
                              </div>
                            </div>
                          </div>
                          
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-1"><span class="required" aria-required="true"> &nbsp; </span> </label>
                          <div class="col-md-10">
                            <div class="portlet-body">
                              <div class="table-responsive">
                                <label  style="padding-left:8px;" ><font style="font-weight:bold">嫌疑人信息</font> <span class="required" aria-required="true"> &nbsp; </span> </label>
                                <div style="margin:0;padding:0; width:100%;height:1px;background-color:#999;overflow:hidden;"></div> 
                                <table class="table table-hover" id="suspectInfotable">
                                  <thead>
                                    <tr>
                                      <th> 序号 </th>
                                      <th> 姓名 </th>
                                      <th> 身份证 </th>
                                      <th> 手机 </th>
                                      <th> 淘宝账号 </th>
                                      <th> 支付宝账号</th>
                                      <th> QQ </th>
                                      <th> 电子邮件 </th>
                                      <th>
                                        <input type="hidden" value="${suspectsize}" id="suspectInfono" name="suspectInfono"/> 
                                        <input type="hidden" value="${suspectsize}"  id="suspectInfonum" name="suspectInfonum"/>
                                      </th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                   <c:if test="${actionType!='3'}">
	                                  <c:forEach items="${entrustData.suspectInfo}"  var="bean" varStatus="status">
	                                        <tr id='suspectInfo${status.index}'>
	                                        <td> <span>${status.index+1}</span></td>
	                                        <td> <input type='text'  value='${bean.suspectName}' class='form-control'  id='suspectInfo${status.index}suspectName' name='suspectInfo[${status.index}].suspectName' onblur='checkFormat("suspectInfo${status.index}suspectName","maxlength")'><span id='suspectInfo${status.index}suspectName_error' class='messageerror'></span> </td>
	                                        <td> <input type='text'  value='${bean.suspectCardNo}' class='form-control'  id='suspectInfo${status.index}suspectCardNo' onblur='checkFormat("suspectInfo${status.index}suspectCardNo","card")'  name='suspectInfo[${status.index}].suspectCardNo'><span id='suspectInfo${status.index}suspectCardNo_error' class='messageerror'></span> </td>
	                                        <td> <input type='text'  value='${bean.suspectTelephone}' class='form-control'  id='suspectInfo${status.index}suspectTelephone' onblur='checkFormat("suspectInfo${status.index}suspectTelephone","mobile")'   name='suspectInfo[${status.index}].suspectTelephone'><span id='suspectInfo${status.index}suspectTelephone_error' class='messageerror'></span> </td>
	                                        <td> <input type='text'  value='${bean.suspectTaobaoNo}' class='form-control'  id='suspectInfo${status.index}suspectTaobaoNo' name='suspectInfo[${status.index}].suspectTaobaoNo' onblur='checkFormat("suspectInfo${status.index}suspectTaobaoNo","maxlength")'><span id='suspectInfo${status.index}suspectTaobaoNo_error' class='messageerror'></span> </td>
	                                        <td> <input type='text'  value='${bean.suspectAlipay}' class='form-control'  id='suspectInfo${status.index}suspectAlipay' name='suspectInfo[${status.index}].suspectAlipay' onblur='checkFormat("suspectInfo${status.index}suspectAlipay","maxlength")'><span id='suspectInfo${status.index}suspectAlipay_error' class='messageerror'></span> </td>
	                                        <td> <input type='text'  value='${bean.suspectQq}' class='form-control'  id='suspectInfo${status.index}suspectQq' onblur='checkFormat("suspectInfo${status.index}suspectQq","qq")'  id='suspectInfo[${status.index}]suspectQq' name='suspectInfo[${status.index}].suspectQq'><span id='suspectInfo${status.index}suspectQq_error' class='messageerror'></span> </td>
	                                        <td> <input type='text'  value='${bean.suspectEmail}' class='form-control'  id='suspectInfo${status.index}suspectEmail' onblur='checkFormat("suspectInfo${status.index}suspectEmail","email")'  name='suspectInfo[${status.index}].suspectEmail'> <span id='suspectInfo${status.index}suspectEmail_error' class='messageerror'></span> </td>
	                                        <td> <a href='javascript:void(0)' onclick='deleteItem("suspectInfo","suspectInfo${status.index}","suspectInfo${status.index}")' class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='删除'><i class='fa fa-trash-o'></i></a></td>
	                                        </tr>
	                                  </c:forEach>
                                  </c:if>
                                  <c:if test="${actionType=='3'}">
	                                  <c:forEach items="${entrustData.suspectInfo}"  var="bean" varStatus="status">
	                                        <tr id='suspectInfo${status.index}'>
	                                        <td> <span>${status.index+1}</span></td>
	                                        <td> <input type='text' readonly="readonly"  value='${bean.suspectName}' class='form-control'  id='suspectInfo${status.index}suspectName' name='suspectInfo[${status.index}].suspectName'> </td>
	                                        <td> <input type='text' readonly="readonly" value='${bean.suspectCardNo}' class='form-control'  id='suspectInfo${status.index}suspectCardNo' onblur='checkFormat("suspectInfo${status.index}suspectCardNo","card")'  name='suspectInfo[${status.index}].suspectCardNo'><span id='suspectInfo${status.index}suspectCardNo_error' class='messageerror'></span> </td>
	                                        <td> <input type='text' readonly="readonly" value='${bean.suspectTelephone}' class='form-control'  id='suspectInfo${status.index}suspectTelephone' onblur='checkFormat("suspectInfo${status.index}suspectTelephone","mobile")'   name='suspectInfo[${status.index}].suspectTelephone'><span id='suspectInfo${status.index}suspectTelephone_error' class='messageerror'></span> </td>
	                                        <td> <input type='text' readonly="readonly" value='${bean.suspectTaobaoNo}' class='form-control'  id='suspectInfo${status.index}suspectTaobaoNo' name='suspectInfo[${status.index}].suspectTaobaoNo'> </td>
	                                        <td> <input type='text' readonly="readonly" value='${bean.suspectAlipay}' class='form-control'  id='suspectInfo${status.index}suspectAlipay' name='suspectInfo[${status.index}].suspectAlipay'> </td>
	                                        <td> <input type='text' readonly="readonly" value='${bean.suspectQq}' class='form-control'  id='suspectInfo${status.index}suspectQq' onblur='checkFormat("suspectInfo${status.index}suspectQq","qq")'  id='suspectInfo[${status.index}]suspectQq' name='suspectInfo[${status.index}].suspectQq'><span id='suspectInfo${status.index}suspectQq_error' class='messageerror'></span> </td>
	                                        <td> <input type='text' readonly="readonly" value='${bean.suspectEmail}' class='form-control'  id='suspectInfo${status.index}suspectEmail' onblur='checkFormat("suspectInfo${status.index}suspectEmail","email")'  name='suspectInfo[${status.index}].suspectEmail'> <span id='suspectInfo${status.index}suspectEmail_error' class='messageerror'></span> </td>
	                                        </tr>
	                                  </c:forEach>
                                  </c:if>
                                  </tbody>
                                </table>
                               <c:if test="${actionType!='3'}">
                                <div align="right">
                                   <a href="javascript:void(0)"  onclick="addItem('suspectInfo')" class="btn btn-default btn-xs black tooltips" data-placement="left" data-original-title="新增"><i class="fa fa-plus-square-o"></i></a>
                                </div>
                                </c:if>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="tab-pane" id="tab3">
                        <div class="form-group">
                          <label class="control-label col-md-3">查询类型 <span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-6" >
                            <c:forEach items="${dicQueryList}" var="bean"  varStatus="status">
                               <c:if test="${status.index%3==0}">
                                 <div class="checkbox-list">
                               </c:if>
                               <label class="checkbox-inline">
	                               <c:if test="${actionType=='1' && bean.dicKey!='account_log'}">
		                           		<c:set var="isCheckQueryType" value="checked"/>
	                               </c:if>
	                               <c:if test="${actionType!='1'}">
		                                <c:if test="${bean.rev1==1}">
		                                    <c:set var="isCheckQueryType" value="checked"/>
		                                </c:if>
	                               </c:if>
	                               <c:set var="isDisableQueryType" value=""/>
	                               <c:if test="${bean.dicKey=='trade_log' || bean.dicKey=='transfer_log' || bean.dicKey=='account_log'}">
	                               		<c:set var="isCheckQueryType" value=""/>
	                               		<c:set var="isDisableQueryType" value="disabled"/>
	                               </c:if>
                               		<input type="checkbox" ${isCheckQueryType} ${isDisableQueryType} name="queryInfo[0].queryType" id="queryInfo[${status.index}].queryType" value="${bean.dicKey}">
                                 	${bean.dicName}
                                </label>
                                 <c:if test="${status.index%3==2}">
                                   </div>
                                 </c:if>
                                  <c:if test="${status.index==(dicQuerysize-1)}">
                                   </div>
                                 </c:if>
                                 
                            </c:forEach>
                           <div class="col-md-3">
                              <span id="queryInfoqueryType_error" class="messageerror "></span>
                           </div>
                         </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-md-3"> 协查信息列表<span class="required" aria-required="true"> * </span> </label>
                          <div class="col-md-9">
                            <div class="portlet-body">
                              <div class="table-responsive">
                                <table class="table table-hover" id="investigateInfotable">
                                  <thead>
                                    <tr>
                                      <th> 序号 </th>
                                      <th> 类型 </th>
                                      <th> 内容 </th>
                                      <th> 说明 </th>
                                      <th>
                                       <input type="hidden" value="${investigatesize}" id="investigateInfono"  name="investigateInfono"/> 
                                       <input type="hidden" value="${investigatesize}" id="investigateInfonum" name="investigateInfonum"/>
                                      </th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    <c:forEach items="${entrustData.investigateInfo}"  var="bean" varStatus="status">
                                        <tr id='investigateInfo${status.index}'>
                                        <td> <span>${status.index+1}</span></td>
                                        <td>
                                           <select   class='form-control' id='investigateInfo${status.index}investType' name='investigateInfo[${status.index}].investType'>
	                                        <c:forEach items="${xcxxList}" var="bean1">
	                                           <c:if test="${bean.investType==bean1.dicKey}">
	                                            <option selected="selected" value="${bean1.dicKey}">${bean1.dicName}</option>
	                                           </c:if>
	                                           <c:if test="${bean.investType!=bean1.dicKey}">
	                                            <option value="${bean1.dicKey}">${bean1.dicName}</option>
	                                           </c:if>
                                            </c:forEach> 
                                           </select>
                                          </td>
                                         <td> <input type='text'  value='${bean.investContent}' class='form-control' id='investigateInfo${status.index}investContent' onblur='checkxcxxList("investigateInfo",${status.index})' name='investigateInfo[${status.index}].investContent'><span id='investigateInfo${status.index}investContent_error' class='messageerror'></span> </td>
                                         <td> <input type='text'  value='${bean.investDesc}' class='form-control' id='investigateInfo${status.index}investDesc' name='investigateInfo[${status.index}].investDesc' onblur='checkFormat("investigateInfo${status.index}investDesc","maxlength")'><span id='investigateInfo${status.index}investDesc_error' class='messageerror'></span> </td>
                                         <td> <a href='javascript:void(0)' onclick='deleteItem("investigateInfo","investigateInfo${status.index}","investigateInfo${status.index}")' class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='删除'><i class='fa fa-trash-o'></i></a></td>
                                        </tr>
                                    </c:forEach>
                                  </tbody>
                                </table>
                                <div align="right">
                                   <span id="investigateInfolist_error" class="messageerror "></span>
                                   <a href="javascript:void(0)"   onclick="addItem('investigateInfo')" class="btn btn-default btn-xs black tooltips" data-placement="left" data-original-title="新增"><i class="fa fa-plus-square-o"></i></a>
                                </div>
                                <div>
                               </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                 </form:form>
                  </div>
                   <a  class="btn btn-xs btn-link margin-right-10" id="savewaitdiv" data-toggle="modal" href="#savewait"></a>
                    <div class="form-actions fluid">
                    	<div id="alert-warning" class="alert alert-success text-center display-hide margin-top-10">保存失败 </div>
                      <div class="form-group">
                         <div class="col-md-offset-3 col-md-9">
                             <a href="javascript:void(0)" id="savebutton" onclick="saveData()" class="btn btn-primary margin-right-10"> 保存 <i class="fa fa-save "></i> </a>
                             <a href="javascript:void(0)" id="submitbutton" onclick="saveSubmit()"  class="btn btn-primary margin-right-10"> 提交 <i class="fa fa-cloud-upload"></i> </a>
                             <a href="javascript:void(0)" id="nextbutton" onclick="clickNextButton()" class="btn btn-primary margin-right-10"> 下一页<i  class="fa fa-arrow-right"> </i> </a>
                         </div>
                      </div>
                    </div>
                    
                  </div>
                </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    

   <div class="modal fade" id="savewait" data-backdrop="static" data-keyboard="false"  tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
					<div style="height:150px;width:800px;text-align:left;">
					 <i  class="fa fa-spinner fa-pulse fa-3x fa-fw margin-bottom"></i>
					          数据正在保存中，请稍后.........
					</div>
				</div>
		</div>
   </div>
  <!-- END CONTENT --> 
</div>
<%@ include file="entrust_apply_js.jsp"%>
</body>
</html>