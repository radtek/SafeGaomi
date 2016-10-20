<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<meta name="current-res-id" content="RES_my_entrust"/>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<meta charset="utf-8"/>
<title>O2O数据侦查平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<meta name="MobileOptimized" content="320">
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed">
<%@ include file="../common/meun.jsp"%>
<!-- END HEADER -->
<div class="clearfix"> </div>
<div class="container"> 
  <!-- BEGIN CONTAINER -->
  <div class="page-container"> 
    <!-- BEGIN SIDEBAR --> 	
    <!-- END SIDEBAR --> 
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
      <div class="row">
        <form action="${ctx}/entrust/myApply/queryEntrust.html" class="form-horizontal" id="submit_form" novalidate>
		<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
          <div class="form-wizard">
            <div class="form-body">
            <!-- 
              <div class="form-group">
                <label class="col-md-offset-1 control-label col-md-2">案(事)件类型 </label>
                <div class="col-md-7">
                  <select id="caseType" name="caseType" class="form-control select2" multiple placeholder="案(事)件类型">
                     <c:forEach items="${caseTypeList}" var="bean">
	                      <c:if test="${bean.dicLevel=='1'}">
	                        <optgroup label="${bean.dicName}">
	                      </c:if>
	                     <c:if test="${bean.dicLevel=='2'}">
	                        <option  value="${bean.dicKey}">${bean.dicName}</option>
	                      </c:if>
			        </c:forEach>
			        </select>
                </div>
              </div>
               -->
                                        
              <div class="form-group">
	              <label class="col-md-offset-1 control-label col-md-2">案(事)件类型</label>
	              <div class="col-md-7 selected">
				    <input type="text" class="form-control hideinputLine"  id="caseTypetreeinputview"   value="">
	                <input type="hidden" name="caseType"  id="caseType" value="">
	               	<div id="caseTypetree"    class="selectstyle selected">
	              <div class="select2-search ">
	                 <input type="text" id="caseTypetreeinput-search" value=""></input>
	              </div>
				  <div id="caseTypetree-searchable" class="select2-results"></div>
				  <div class="treeview treeview-area" id="caseTypetreesearch-output">
					<ul class="select2-results"><li class="select2-no-results"></li></ul>
				  </div>
			    </div>
	              </div>
	             
			 </div>
                    
             
               <div class="form-group">
                <label class="col-md-offset-1 control-label col-md-2"> 委托编号</label>
                <div class="col-md-7">
                  <input type="text" class="form-control" id="entrustNo" name="entrustNo">
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-offset-1 control-label col-md-2"> 案(事)件名称 </label>
                <div class="col-md-7">
                  <input type="text" class="form-control" id="caseName" name="caseName">
                </div>
              </div>
            
              <div class="form-group">
                    <label class="control-label col-md-3">是否共享案件信息  </label>
                    <div class="col-md-6">
<!--                         <input type="checkbox" name="isShareCaseinfo"  checked class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否"> -->
                    	<div class="btn-group" data-toggle="buttons" id="isShareCaseinfoDiv">
							<label name="isShareCaseinfoLabel" id="isShareCaseinfoLabel_1" onclick="SelectForSingle('isShareCaseinfoLabel','1');" class="btn btn-default active">
                              <input type="radio" class="toggle" name="isShareCaseinfo"  value="1"> 是
                            </label>
                            <label name="isShareCaseinfoLabel" id="isShareCaseinfoLabel_2" onclick="SelectForSingle('isShareCaseinfoLabel','2');" class="btn btn-default active">
                              <input type="radio" class="toggle" name="isShareCaseinfo" value="0"> 否
                            </label>
						</div>
                    </div>
               </div>
               
               <div class="form-group">
                    <label class="control-label col-md-3">是否共享查询结果 </label>
                   	<div class="col-md-6">
<!--                         <input type="checkbox"  name="isShareQueryresult"  checked class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否"> -->
                   		<div class="btn-group" data-toggle="buttons" id="isShareQueryresultDiv">
							<label name="isShareQueryresultLabel" id="isShareQueryresultLabel_1" onclick="SelectForSingle('isShareQueryresultLabel','1');" class="btn btn-default active">
                              <input type="radio" class="toggle" name="isShareQueryresult"  value="1"> 是
                            </label>
                            <label name="isShareQueryresultLabel" id="isShareQueryresultLabel_2" onclick="SelectForSingle('isShareQueryresultLabel','2');" class="btn btn-default active">
                              <input type="radio" class="toggle" name="isShareQueryresult"  value="0"> 否
                            </label>
						</div>
                   	</div>
               </div>
               
                
              <div class="form-group">
                <label class="col-md-offset-1 control-label col-md-2"> 委托时间</label>
                <div class="col-md-7">
                  <div class="input-group">
                    <input type="text" id="entrustDateBegin" name="entrustDateBegin"  class="form-control date-set date form_datetime">
                    <span class="input-group-addon"> 至 </span>
                    <input type="text" id="entrustDateEnd" name="entrustDateEnd"  class="form-control date-set date form_datetime">
                  </div>
                </div>
              </div>
              
             <input type="hidden" id="isreset" name="1"/>
              <div class="form-group">
                <label class="col-md-offset-1 control-label col-md-2"> 委托状态</label>
                <div class="col-md-7">
                  <div class="btn-group" style="font-size: 10px" data-toggle="buttons" >
                   <c:forEach items="${processState}" var="bean" varStatus="status">
                         <label id="processState${status.index}" onclick="SelectStatus('processState','${status.index}');" name="processState" class="btn btn-default active"  > 
                         <input type="checkbox" id="processStateinput${status.index}"  value="${bean.dicKey}" checked  name="processState" class="toggle"></input>
                         ${bean.dicName}</label> 
                    </c:forEach>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <div class="col-md-offset-3 col-md-9"> 
                <a href="javascript:void(0)" onclick="queryEntrust()" class="btn btn-primary margin-right-10"> 查询 <i class="fa fa-search "></i> </a> 
                <a href="javascript:void(0)" onclick="resetQueryStr()" class="btn btn-default button-previous margin-right-10" > 重置 </a> </div>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="col-md-12"> 
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="portlet">
          <div class="portlet-title">
            <div class="caption"> <i class="fa fa-table"></i>委托列表 </div>
            <div class="tools"> </div>
          </div>
          
         <div class="portlet-body">
			<div class="table-responsive">
				 <!-- jqGrid table list4 -->
			     <table  id="list4"></table>
			     <div id="gridPager"></div>
		   </div>
          </div>
        </div>
      </div>
      <!-- END SAMPLE TABLE PORTLET--> 
    </div>
  </div>
</div>
<%@ include file="myentrust_apply_js.jsp"%>
</body>