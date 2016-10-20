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
<meta name="current-res-id" content="RES_account"/>
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
								<form action="#" class="form-horizontal" id="accountForm" novalidate>
								<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
										<div class="form-wizard">
												<div class="form-body">
														<div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2">用户账号 </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="accountName" id="accountName">
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2"> 用户姓名</label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="trueName" id="trueName">
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2"> 联系电话</label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="phoneNumber" id="phoneNumber">
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2">用户角色</label>
																<div class="col-md-6">
																		<div class="btn-group" data-toggle="buttons" id="search_role_div">
																										</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2">用户单位</label>
																<div class="col-md-6">
																	<select id="unitCode_search" class="form-control select2" placeholder="" name="unitCode">
																		<option value=""> </option>
													                </select>
<!-- 													                <input type="text" class="form-control" name="unitCode" id="unitCode">							 -->
																</div>
														</div>
														<div class="form-group">
																<div class="col-md-offset-3 col-md-9"> <a href="javascript:;" class="btn btn-primary margin-right-10" id="search"> 查询 <i class="fa fa-search "></i> </a> <a href="javascript:;" class="btn btn-default button-previous margin-right-10" id="reset" > 重置 </a> </div>
														</div>
												</div>
										</div>
								</form>
						</div>
						<div class="col-md-12"> 
								<!-- BEGIN SAMPLE TABLE PORTLET-->
								<div class="portlet">
										<div class="portlet-title">
												<div class="caption"> <i class="fa fa-table"></i> 用户列表 </div>
												<div class="tools add-tools"><a class="btn btn-xs btn-link" data-toggle="modal" href="#newUser" id="addAccount">新增</a><a href="#batchAdd" class="btn btn-xs btn-link" data-toggle="modal">批量导入 </a><a href="#" class="btn btn-xs btn-link" data-toggle="modal" onclick="batchDelete();" id="account_batch_del">批量删除</a></div>
										</div>
										<div class="portlet-body">
												<div class="table-responsive">
														<table  id="accountTable">
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
<div class="modal fade" id="newUser" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">用户注册</h4>
						</div>
						<form action="${ctx}/background/account/add.html" method="post" class="form-horizontal" id="accountAddForm" name="accountAddForm">
						<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
						<div class="modal-body">
								<div class="portlet-body form">
												<div class="form-body">
														<div class="form-group">
																<label class="control-label col-md-3">姓名 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="trueName" id="trueName_add">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">用户账号 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="accountName" id="accountName_add">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">密码 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="password" class="form-control" name="password" id="password_add">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">密码确认 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="password" class="form-control" name="confirm_password" id="confirm_password_add">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">所属角色 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-8">
																		<div class="btn-group" data-toggle="buttons" id="add_role_div">
																										</div>
																								
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
																<label class="control-label col-md-3">联系电话</label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="phoneNumber" id="phoneNumber_add">
																</div>
														</div>
														<div class="form-group" hidden id="add_policeno_div">
																<label class="control-label col-md-3">警员编号<span class="required" aria-required="true"> *</label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="policeNumber" id="policeNumber_add">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">职务</label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="duty" id="duty_add">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">是否启用 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="checkbox" name="state" value="1" checked class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">备注  </label>
																<div class="col-md-6">
																		<textarea id="maxlength_textarea" name="description" class="form-control" maxlength="225" rows="2" placeholder=""></textarea>
																</div>
														</div>
												</div>
										
								</div>
                                <div class="clearfix"> </div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="account_add_return">取消</button>
								<button type="button" class="btn btn-primary" onclick="checkAndSave();">保存</button>
						</div>
						</form>
				</div>
		</div>
</div>
<div class="modal fade" id="editUser" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">用户编辑</h4>
						</div>
						<form action="${ctx}/background/account/update.html" method="post" class="form-horizontal" id="accountEditForm" name="accountEditForm">
						<input type="hidden" name="CSRFToken" value="${csrf}" />
						<div class="modal-body">
								<div class="portlet-body form">
												<div class="form-body">
														<div class="form-group">
																<label class="control-label col-md-3">姓名 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="hidden" id="accountId" value="" name="id"/>
																		<input type="text" class="form-control" name="trueName" id="trueName_edit">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">用户账号 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="accountName" id="accountName_edit">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">密码 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="password" class="form-control" name="password" id="password_edit" />
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">密码确认 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="password" class="form-control" name="confirm_password" id="confirm_password_edit">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">所属角色 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-8">
																		<div class="btn-group" data-toggle="buttons" id="edit_role_div"></div>
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">用户单位 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																			<input type="hidden" name="" id="unitCode_edit_hidden" />
																			<select class="form-control select2" placeholder="请选择" name="unitCode" id="unitCode_edit">
														                		<option value=""> </option>
														                	</select>
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">联系电话</label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="phoneNumber" id="phoneNumber_edit">
																</div>
														</div>
														<div class="form-group" hidden id="edit_policeno_div">
																<label class="control-label col-md-3">警员编号<span class="required" aria-required="true"> *</label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="policeNumber" id="policeNumber_edit">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">职务</label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="duty" id="duty_edit">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">是否启用 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="checkbox" id="state_edit" name="state" value="1" class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">备注  </label>
																<div class="col-md-6">
																		<textarea id="description_edit" name="description" class="form-control" maxlength="225" rows="2" placeholder=""></textarea>
																</div>
														</div>
												</div>
										
								</div>
                                <div class="clearfix"> </div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="account_edit_return">取消</button>
								<button type="button" class="btn btn-primary" onclick="edit();">保存</button>
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
																<input type="hidden" id="accountId" value="" />
																<label class="control-label">是否删除所选用户？</label>
														</div>
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="del_button_return">取消</button>
								<button type="button" class="btn btn-primary" onclick="deleteForOneOrMore();">确定</button>
						</div>
				</div>
		</div>
</div>

<div class="modal fade" id="batchAdd" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">用户批量注册</h4>
						</div>
						<form action="#" method="post" class="form-horizontal" id="batch_add_form" name="batch_add_form" enctype="multipart/form-data">
						<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
						<div class="modal-body">
								<div class="portlet-body form">
												<div class="form-body">
														<div class="form-group">
																<label class="control-label col-md-3">附件<span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="file" class="form-control" name="file" id="fileUploadForAadd">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">所属角色 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-8">
																		<div class="btn-group" data-toggle="buttons" id="batch_add_role_div">
																										</div>
																								
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">单位 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="hidden" id="unitCode_batch_add_hidden" name="">
																		<select class="form-control select2" placeholder="请选择" name="unitCode" id="unitCode_batch_add">
														               		<option value=""> </option>
														                </select>
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">是否启用 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="checkbox" name="state" value="1" checked class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">备注  </label>
																<div class="col-md-6">
																		<textarea id="maxlength_textarea" name="description" class="form-control" maxlength="225" rows="2" placeholder=""></textarea>
																</div>
														</div>
												</div>
										
								</div>
                                <div class="clearfix"> </div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="batch_account_add_return">取消</button>
								<button type="button" class="btn btn-primary" onclick="batchAdd();">保存</button>
						</div>
						</form>
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
								<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
						</div>
				</div>
		</div>
</div>

<%@ include file="accountList_js.jsp"%>

</body>

<!-- Mirrored from www.keenthemes.com/preview/conquer/layout_boxed_page.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 09 Dec 2014 13:45:50 GMT -->
</html>