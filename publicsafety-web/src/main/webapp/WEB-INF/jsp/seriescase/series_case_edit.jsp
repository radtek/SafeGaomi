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
							<div class="tools add-tools">
							  <a href="javascript:void(0)" class="btn btn-xs button-link margin-right-10" onclick="historyPage()"> <i class="fa fa-mail-reply"></i>返回 </a>
							  <a href="javascript:void(0)" class="btn btn-xs button-link margin-right-10" id="seriesCaseButton" > <i class="fa fa-save"></i> 保存 </a> 
							</div>
						</div>
						<div class="portlet-body">
							<form action="${ctx}/seriesCase/myseries/updateSeriesCaseInfo.html" method="post" class="form-horizontal form-bordered" id="updateform">
								<div class="form-body">
									<div class="form-group">
										<div class="col-md-2 text-right gray">编号</div>
										<div class="col-md-9">${Seriesdata.seriesCaseInfo.seriesCaseNo}
										<input type="hidden" id="seriesId" name="seriesId" value="${Seriesdata.seriesCaseInfo.id}"/>
										<input type="hidden" id="seriesCaseNum" value="${seriesCaseNum}"/>
										<input type="hidden" id="seriesCaseIds" name="seriesCaseIds" value="">
										<input type="hidden" name="CSRFToken" value="${csrf}" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-2 text-right gray control-label">串并案名称
										<span
											class="required" aria-required="true"> * </span> 
										</div>
										<div class="col-md-9">
										  <input type="text" class="form-control" id="seriesCaseName" value="${Seriesdata.seriesCaseInfo.seriesCaseName}" name="seriesCaseName"/>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-2 text-right gray">串并依据
										</div>
										<div class="col-md-9">${Seriesdata.seriesCaseInfo.seriesReason}</div>
									</div>
									<div class="form-group last">
										<div class="col-md-2 text-right gray control-label">串并描述
										 <span
											class="required" aria-required="true"> *
									     </span>
										</div>
										<div class="col-md-9">
										<textarea id="maxlength_textarea" id="seriesDesc" name="seriesDesc" class="form-control"
											      maxlength="200" rows="3" placeholder="">${Seriesdata.seriesCaseInfo.seriesDesc}</textarea>
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
								<i class="fa fa-table"></i>案件信息列表
							</div>
							<div class="tools">
							  <div class="tools add-tools">
							   <a href="javascript:void(0)" class="btn btn-xs btn-link">已串并数量:<span id="cluenum">0</span></a>
							   <a href="javascript:void(0)" onclick="clickSeriesCaseMany('1','seriesCaseDetailtable')" class="btn btn-xs btn-link">批量删除</a>
							  
							  </div>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-responsive">
							   <table  id="seriesCaseDetailtable"></table>
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
	
	<div class="modal fade" id="expandDialog" tabindex="-1" role="basic" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-wide">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">案件信息</h4>
						</div>
						<div class="modal-body">
								<div class="col-md-12"> 
								<!-- BEGIN SAMPLE TABLE PORTLET-->
								<div class="portlet">
										<div class="portlet-title">
												<div class="caption"> <i class="fa fa-table"></i> 案件列表 </div>
												<div class="tools add-tools">
												  <a href="javascript:void(0)" class="btn btn-xs btn-link">待串并数量:<span id="selectnum">0</span></a>
												  <a href="javascript:void(0)" class="btn btn-xs btn-link" onclick="clickSeriesCaseMany('0','seriesCaseWaittable')">批量确认串并</a>
								                  <a href="javascript:void(0)" class="btn btn-xs btn-link" onclick="clickSeriesCaseMany('1','seriesCaseWaittable')">批量取消串并</a>
												</div>
										</div>
										<div class="portlet-body">
												<div class="table-responsive">
                                                  <table  id="seriesCaseWaittable"></table>
			                                      <div id="seriesCaseWaitgridPager"></div>
												</div>
										</div>
								</div>
						</div
						</div>
						<div class="modal-footer">
								<button type="button" id="cacheButton" onclick="addSeriesCaseToList()" class="btn btn-default" data-dismiss="modal">确定</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								
						</div>
				</div>
		</div>
</div>

	
<%@ include file="series_case_edit_js.jsp"%>
</body>
</html>