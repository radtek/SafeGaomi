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
<meta name="current-res-id" content="blackPlat"/>
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
<style type="text/css"> 
* 
{ 
font-size: 14px; 
} 
#accountAddForm label.error 
{ 
color:Red; 
font-size:13px; 
margin-left:5px; 
padding-left:16px; 
background:url("error.png") left no-repeat; 
} 
#accountEditForm label.error 
{ 
color:Red; 
font-size:13px; 
margin-left:5px; 
padding-left:16px; 
background:url("error.png") left no-repeat; 
} 
#batch_add_form label.error 
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
								<form action="#" class="form-horizontal" id="blackPlatForm" novalidate>
								<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
										<div class="form-wizard">
												<div class="form-body">
														<div class="row">
															<div class="col-md-offset-1 col-md-5">
																<div class="form-group">
																	<label class="control-label col-md-3">录入时间：</label>
																	<div class="col-md-9">
																		<div class="input-group">
														                    <input type="text" name="createBegDatetime" id="createBegDatetime"  class="form-control date form_datetime">
														                    <span class="input-group-addon"> 至 </span>
														                    <input type="text" name="createEndDatetime" id="createEndDatetime"  class="form-control date-set date form_datetime">
														                </div>
																	</div>
																</div>
															</div>
															<div class="col-md-5">
																<div class="form-group">
																	<label class="control-label col-md-3">车牌号：</label>
																	<div class="col-md-9">
														                <input type="text" class="form-control" placeholder="" id="platNum" name="platNum">
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-offset-1 col-md-5">
																<div class="form-group">
																	<label class="control-label col-md-3 ">提供单位：</label>
																	<div class="col-md-9">
																		<select id="unitCode_search" class="form-control select2" placeholder="" type="text" name="unitCode">
																			<option value=""> </option>
																		</select>
																	</div>
																</div>
															</div>
															<div class="col-md-5">
																<div class="form-group">
																	<label class="control-label col-md-3">提供者：</label>
																	<div class="col-md-9">
														                <input type="text" class="form-control" placeholder="" id="offerName" name="offerName">
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-offset-9 col-md-12"> 
																<a href="javascript:;" class="btn btn-primary margin-right-10" id="search"> 查询 <i class="fa fa-search "></i> </a> 
																<a href="javascript:;" class="btn btn-default button-previous margin-right-10" id="reset" > 重置 </a>
															</div>
														</div>
												</div>
										</div>
								</form>
						</div>
						<div class="col-md-12"> 
								<!-- BEGIN SAMPLE TABLE PORTLET-->
								<div class="portlet">
										<div class="portlet-title">
												<div class="caption"> <i class="fa fa-table"></i> 车牌预警管理列表 </div>
												<div class="tools add-tools"><a class="btn btn-xs btn-link" data-toggle="modal" href="#newBlackPlat" id="addBlackPlat">新增</a></div>
										</div>
										<div class="portlet-body">
												<div class="table-responsive">
														<table  id="blackPlatTable">
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
<div class="modal fade" id="newBlackPlat" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">车牌号预警添加</h4>
						</div>
						<form action="${ctx}/ccp/blackPlat/add.html" method="post" class="form-horizontal" id="blackPlatAddForm" name="blackPlatAddForm">
						<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
						<div class="modal-body">
								<div class="portlet-body form">
									<div class="form-body">
										<div class="form-group">
											<label class="control-label col-md-3">提供者姓名 <span class="required" aria-required="true"> * </span> </label>
											<div class="col-md-6">
												<input type="text" class="form-control" name="offerName" id="offerName_add">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">提供者手机号 <span class="required" aria-required="true"> * </span> </label>
											<div class="col-md-6">
												<input type="text" class="form-control" name="offerPho" id="offerPho_add">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">通知者手机号 <span class="required" aria-required="true"> * </span> </label>
											<div class="col-md-6">
												<input type="text" class="form-control" name="informPho" id="informPho_add">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">车牌号 <span class="required" aria-required="true"> * </span> </label>
											<div class="col-md-6">
												<input type="text" class="form-control" name="platNum" id="platNum_add">
											</div>
										</div>
														
										<div class="form-group">
											<label class="control-label col-md-3">用户单位 <span class="required" aria-required="true"> * </span> </label>
											<div class="col-md-6">
												<input type="hidden" name="" id="unitCode_add_hidden">
												<select id="unitCode_add" class="form-control select2" placeholder="请选择" name="unitCode">
													<option value=""> </option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">备注  </label>
											<div class="col-md-6">
												<textarea id="remark_add" name="remark" class="form-control" maxlength="225" rows="2" placeholder=""></textarea>
											</div>
										</div>
									</div>				
								</div>
                                <div class="clearfix"> </div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="blackPlat_add_return">取消</button>
								<button type="button" class="btn btn-primary" onclick="checkAndSave();">保存</button>
						</div>
						</form>
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
																<label class="control-label">是否删除所选数据？</label>
														</div>
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<input type="hidden" id="blackPlatId" value="" />
								<button type="button" class="btn btn-default" data-dismiss="modal" id="del_button_return">取消</button>
								<button type="button" class="btn btn-primary" onclick="deleteForOneOrMore();">确定</button>
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
																<input type="hidden" id="blackPlatId" value="" />
																<label class="control-label">
																	<h4 class="modal-title">未选中</h4>
																</label>
														</div>
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
						</div>
				</div>
		</div>
</div>

<%@ include file="blackPlatList_js.jsp"%>

</body>

<!-- Mirrored from www.keenthemes.com/preview/conquer/layout_boxed_page.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 09 Dec 2014 13:45:50 GMT -->
</html>