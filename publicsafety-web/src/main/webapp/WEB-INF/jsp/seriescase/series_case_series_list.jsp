<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<meta charset="utf-8" />
<title>O2O数据侦查平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">
<style type="text/css">
#seriesForm label.error 
{ 
color:Red; 
font-size:13px; 
margin-left:5px; 
padding-left:16px; 
background:url("error.png") left no-repeat; 
} 
</style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed">
<%@ include file="../common/meun.jsp"%>
<div class="clearfix"></div>
  <div class="container">
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN SIDEBAR -->
			<!-- END SIDEBAR -->
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="col-md-12  margin-top-10">
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-table"></i>串并依据
							</div>
							<div class="tools"></div>
						</div>
						<div class="portlet-body">
							<form action="${ctx}/seriesCase/myseries/saveSeriesCaseInfo.html" method="post" class="form-horizontal" id="seriesForm">
								<div class="form-body">
									<div class="form-group">
										<label class="control-label col-md-2"> 串并案名称 <span
											class="required" aria-required="true"> * </span> </label>
										<div class="col-md-9">
											<input type="text" class="form-control" id="seriesCaseName" name="seriesCaseName">
										    <input type="hidden" name="CSRFToken" value="${csrf}" />
										    <input type="hidden" id="caseId" name="caseId" value="${caseId}" />
										    <input type="hidden" id="seriesReason" name="seriesReason" value="${seriesTypes}">
										    <input type="hidden" id="seriesCaseIds" name="seriesCaseIds" value="">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-2"> 串并依据 <span
											class="required" aria-required="true"> * </span> </label>
										<div class="col-md-9">
											<div class="btn-group" data-toggle="buttons">
												 <c:forEach items="${cbatype}" var="bean" >
												  <c:forEach items="${tlist}" var="existBean" varStatus="status">
												     <c:if test="${existBean==bean.dicKey}">
												        <label class="btn btn-success"   id="seriesperson${status.index}" name="seriesperson" onclick="selectStatus('seriesperson',${status.index});">
													     <input type="checkbox" readonly="readonly"  id="seriespersoninput${status.index}"  value="${bean.dicKey}"  name="seriesperson"  class="toggle">
													     <i class="fa fa-check"></i> ${bean.dicName}
													    </label>
												     </c:if>
												  </c:forEach>
												 </c:forEach>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-2"> 串并描述 <span
											class="required" aria-required="true"> * </span> </label>
										<div class="col-md-9">
											<textarea id="maxlength_textarea" id="seriesDesc" name="seriesDesc" class="form-control"
												maxlength="200" rows="3" placeholder=""></textarea>
										</div>
									</div>
								</div>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-2 col-md-9">
											<a href="javascript:;" id="seriesCaseButton"  class="btn btn-primary margin-right-10">
											      确定串并 <i class="fa fa-sitemap"></i> 
										    </a>
											<a href="javascript:;" onclick="historyPage()"	class="btn btn-default button-previous margin-right-10">
												  取消 
											</a>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-table"></i> 待串并案件
							</div>
							<div class="tools add-tools">
							    <a href="javascript:void(0)" class="btn btn-xs btn-link">已串并数量:<span id="cluenum">0</span></a>
								<a href="javascript:void(0)" class="btn btn-xs btn-link" onclick="clickSeriesCaseMany('0')">批量确认串并</a>
								<a href="javascript:void(0)" class="btn btn-xs btn-link" onclick="clickSeriesCaseMany('1')">批量取消串并</a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-responsive">
	                            <table  id="seriesCaseWaittable"></table>
			                  
							</div>
						</div>
					</div>
				</div>
				<!-- END SAMPLE TABLE PORTLET-->
			</div>
		</div>
	</div>

	
		<div class="modal fade" id="display" tabindex="-1" role="basic"
		aria-hidden="true">
		<div class="modal-dialog modal-wide">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">案件信息</h4>
				</div>
				<div class="modal-body">
					<div class="tab-pane form-horizontal form-bordered" id="tab_1">
						<div class="table-responsive">
							<div class="form-body">
								<div class="form-group">
									<div class="col-md-2 text-right gray">案件名称</div>
									<div class="col-md-4" id="dcaseName"></div>
									<div class="col-md-2 text-right gray">案件编号</div>
									<div class="col-md-4" id="dcaseNo"></div>
								</div>
								<div class="form-group">
									<div class="col-md-2 text-right gray">案件时间</div>
									<div class="col-md-4" id="dcaseOccurDate"></div>
									<div class="col-md-2 text-right gray">涉案品牌</div>
									<div class="col-md-4" id="dbrand"></div>
								</div>
								<div class="form-group">
									<div class="col-md-2 text-right gray">案件金额</div>
									<div class="col-md-4" id="dcaseMoneyAmout"></div>
									<div class="col-md-2 text-right gray">案件类型</div>
									<div class="col-md-4" id="dcaseType"></div>
								</div>
								<div class="form-group">
									<div class="col-md-2 text-right gray">简要案情</div>
									<div class="col-md-10" id="dcaseInfo"></div>
								</div>
								<div class="form-group  last">
									<div style="padding-left:8px;"><font style="font-weight:bold">受害人信息 </font></div>
									<div style="margin:0;padding:0; width:100%;height:1px;background-color:#999;overflow:hidden;"></div> 
									<div style="margin:0;padding:0; width:100%;height:1px;background-color:#999;overflow:hidden;"></div>   
									<div class="col-md-10">
										<table class="table table-hover" id="dvictimInfo">
											
										</table>
									</div>
								</div>
								<div class="form-group last">
									<div style="padding-left:8px;"><font style="font-weight:bold">嫌疑人信息</font> </div>
									<div style="margin:0;padding:0; width:100%;height:1px;background-color:#999;overflow:hidden;"></div> 
									<div class="col-md-10">
										<table class="table table-hover" id="dsuspectInfo">
											<thead>
												<tr>
													<th>#</th>
													<th>姓名</th>
													<th>身份证</th>
													<th>手机</th>
													<th>淘宝、支付宝账号</th>
													<th>所在地址</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>张三</td>
													<td>110104198409022516</td>
													<td>13683189779</td>
													<td>293456789</td>
													<td>北京市朝阳区工体</td>
												</tr>
												<tr>
													<td>2</td>
													<td>张三</td>
													<td>110104198409022516</td>
													<td>13683189779</td>
													<td>293456789</td>
													<td>北京市朝阳区工体</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
<%@ include file="series_case_series_js.jsp"%>
</body>
</html>