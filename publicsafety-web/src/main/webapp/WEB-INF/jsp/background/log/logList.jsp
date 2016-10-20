<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!-- 
Template Name: Conquer - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.2.0
Version: 2.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/conquer-responsive-admin-dashboard-template/3716838?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<meta name="current-res-id" content="RES_log"/>
<!--<![endif]-->
<!-- BEGIN HEAD -->

<!-- Mirrored from www.keenthemes.com/preview/conquer/form_component.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 09 Dec 2014 13:46:23 GMT -->
<!-- Added by HTTrack -->
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<!-- /Added by HTTrack -->
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
<%@ include file="../../common/meun.jsp"%>
<div class="clearfix"> </div>
<div class="container"> 
		<!-- BEGIN CONTAINER -->
		<div class="page-container"> 
				<!-- BEGIN SIDEBAR --> 
				<!-- END SIDEBAR --> 
				<!-- BEGIN CONTENT -->
				<div class="page-content-wrapper">
						<div class="row">
								<form action="#" class="form-horizontal" id="logFrom" novalidate>
								<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
										<div class="form-wizard">
												<div class="form-body">
														<div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2">操作人 </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="username" id="username">
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2"> IP地址</label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="userIP" id="userIP">
																</div>
														</div>
                                                        <div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2">唯一标识 </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="rev1" id="rev1">
																</div>
														</div>
														<!-- <div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2"> 操作时间</label>
																<div class="col-md-6">
																		<div class="input-group date-picker input-daterange" data-date="2012-11-10" data-date-format="yyyy-mm-dd">
																				<input type="text" class="form-control" name="operTimeBeg" id="operTimeBeg">
																				<span class="input-group-addon"> 至 </span>
																				<input type="text" class="form-control" name="operTimeEnd" id="operTimeEnd">
																		</div>
																</div>
														</div> -->
														
														 <div class="form-group">
											                <label class="col-md-offset-1 control-label col-md-2"> 操作时间 </label>
											                <div class="col-md-6">
											                  <div class="input-group">
											                    <input type="text" name="operTimeBeg" id="operTimeBeg"  class="form-control date form_datetime">
											                    <span class="input-group-addon"> 至 </span>
											                    <input type="text" name="operTimeEnd" id="operTimeEnd"  class="form-control date-set date form_datetime">
											                  </div>
											                </div>
											             </div>
														
														<div class="form-group">
																<div class="col-md-offset-3 col-md-9"> <a href="javascript:;" class="btn btn-primary margin-right-10" id="search"> 查询 <i class="fa fa-search "></i> </a> <a href="javascript:;" class="btn btn-default button-previous margin-right-10" id="reset"> 重置 </a> </div>
														</div>
												</div>
										</div>
								</form>
						</div>
						<div class="col-md-12"> 
								<!-- BEGIN SAMPLE TABLE PORTLET-->
								<div class="portlet">
										<div class="portlet-title">
												<div class="caption"> <i class="fa fa-table"></i> 日志管理 </div>
												<div class="tools add-tools"><a data-toggle="modal" href="#" id="del_href" class="btn btn-xs btn-link"  onclick="batchDelete();">批量删除</a></div>
										</div>
										<div class="portlet-body">
												<div class="table-responsive">
														<table  id="logTable">
																<!-- jqGrid 分页 div gridPager -->
     															<div id="gridPager"></div>
														</table>
												</div>
										</div>
								</div>
						</div>
						<!-- END SAMPLE TABLE PORTLET--> 
						
				</div>
		</div>
</div>

<div class="modal fade" id="del" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">删除</h4>
						</div>
						<div class="modal-body">
								<div class="portlet-body form">
										<form action="#" class="form-horizontal">
										<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
												<div class="form-body">
														<div class="form-group">
																<input type="hidden" id="logId" value="" />
																<label class="control-label">是否删除所选日志信息？</label>
														</div>
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="del_button_return">取消</button>
								<button type="button" class="btn btn-primary" onclick="deleteLogByIds();">确定</button>
						</div>
				</div>
		</div>
</div>

<div class="modal fade" id="noSelected" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title"></h4>
						</div>
						<div class="modal-body">
								<div class="portlet-body form">
										<form action="#" class="form-horizontal">
										<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
												<div class="form-body">
														<div class="form-group" >
																<input type="hidden" id="accountId" value="" />
																<label class="control-label">
																	<h4 class="modal-title">未选中</h4>
																</label>
														</div>
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal">返回</button>
						</div>
				</div>
		</div>
</div>

<%@ include file="logList_js.jsp"%>
</body>

<!-- Mirrored from www.keenthemes.com/preview/conquer/layout_boxed_page.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 09 Dec 2014 13:45:50 GMT -->
</html>