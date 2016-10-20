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
<meta name="current-res-id" content="RES_dic"/>
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
<%@ include file="../../common/meun.jsp"%>
<style>
#dic_add_form label.error 
{ 
color:Red; 
font-size:13px; 
margin-left:5px; 
padding-left:16px; 
background:url("error.png") left no-repeat; 
} 
#dic_edit_form label.error 
{ 
color:Red; 
font-size:13px; 
margin-left:5px; 
padding-left:16px; 
background:url("error.png") left no-repeat; 
} 
#dic_type_add_form label.error 
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

<div class="clearfix"> </div>
<div class="container"> 
		<!-- BEGIN CONTAINER -->
		<div class="page-container"> 
				<!-- BEGIN SIDEBAR --> 
				<!-- END SIDEBAR --> 
				<!-- BEGIN CONTENT -->
				<div class="page-content-wrapper">
						<div class="row">
								<form action="#" class="form-horizontal" id="dic_search_form" novalidate>
								<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
										<div class="form-wizard">
												<div class="form-body">
														<div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2">字典值 </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="dicName" id="dicName_search">
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2"> 字典代码</label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="dicKey" id="dicKey_search">
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-offset-1 control-label col-md-2"> 字典类型</label>
																<div class="col-md-6">
																	<select id="select2_sample2" class="form-control select2" placeholder="请选择" name="dicTypeId">
													                	
													                </select>
																</div>
														</div>
														<div class="form-group">
																<div class="col-md-offset-3 col-md-9"> 
																	<a href="javascript:;" class="btn btn-primary margin-right-10" id="search"> 查询 <i class="fa fa-search "></i> </a> 
																	<a href="javascript:;" class="btn btn-default button-previous margin-right-10 " id="reset"> 重置 </a> 
																	<a data-toggle="modal" href="#newDictType" class="btn btn-default button-previous margin-right-10" id="add_dic_type"> 添加字典类型 </a>
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
												<div class="caption"> <i class="fa fa-table"></i> 字典列表 </div>
												<div class="tools add-tools"><a class="btn btn-xs btn-link" href="#" id="back_parent_dic">返回上一级</a><a class="btn btn-xs btn-link" data-toggle="modal" href="#" onclick="addNewDicBySameLevel();" id="newDicA">新增同级字典</a><a class="btn btn-xs btn-link" data-toggle="modal" href="#" onclick="addNewChildrenDict();" id="newDicB">新增下级字典</a><a href="#" class="btn btn-xs btn-link" onclick="batchDelete();" data-toggle="modal" id="dic_batch_del">批量删除</a></div>
										</div>
										<div class="portlet-body">
												<div class="table-responsive">
														<table id="dicTable">
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
<div class="modal fade" id="newDictType" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">新增字典类型</h4>
						</div>
						<div class="modal-body">
								<div class="portlet-body form">
										<form action="${ctx}/background/dicType/add.html" class="form-horizontal" id="dic_type_add_form" method="post">
										<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
												<div class="form-body">
														<div class="form-group">
																<label class="control-label col-md-3">字典类型 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="dicTypeName" id="dicTypeName_add">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">字典类型代码<span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="dicTypeKey" id="dicTypeKey_add">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">备注</label>
																<div class="col-md-6">
																		<textarea id="dic_type_description" name="description" class="form-control" maxlength="225" rows="3" placeholder=""></textarea>
																</div>
														</div>
														
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="dic_type_add_return">取消</button>
								<button type="button" class="btn btn-primary" onclick="dicTypeAdd();">保存</button>
						</div>
				</div>
		</div>
</div>
<div class="modal fade" id="newDict" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">新增字典代码</h4>
						</div>
						<div class="modal-body">
								<div class="portlet-body form">
										<form action="${ctx}/background/dic/add.html" class="form-horizontal" id="dic_add_form" method="post">
										<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
												<div class="form-body">
														<div class="form-group">
																<label class="control-label col-md-3">字典值 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="dicName" >
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">字典代码 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="dicKey">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">字典类型 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<select id="dicTypeId_add" class="form-control select2" placeholder="请选择" name="dicTypeId">
														                </select>
																</div>
														</div>
														<div class="form-group" id="dic_parent_div" hidden>
																<label class="control-label col-md-3">父字典代码 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																	<input type="hidden" id="dicLevel_add" name="dicLevel" value=""/>
																	<input type="text" class="form-control" name="dicParentKey" id="dicParentKey_add" readonly>
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">是否启用 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="checkbox" name="status" value="1" checked class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">备注</label>
																<div class="col-md-6">
																		<textarea id="maxlength_textarea" name="description" class="form-control" maxlength="225" rows="3" placeholder=""></textarea>
																</div>
														</div>
														
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="dic_add_return">取消</button>
								<button type="button" class="btn btn-primary" onclick="dicAdd();">保存</button>
						</div>
				</div>
		</div>
</div>
<div class="modal fade" id="editDic" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">编辑字典代码</h4>
						</div>
						<div class="modal-body">
								<div class="portlet-body form">
										<form action="${ctx}/background/dic/update.html" class="form-horizontal" id="dic_edit_form" method="post">
										<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
												<div class="form-body">
														<div class="form-group">
																<label class="control-label col-md-3">字典值 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="hidden" value="" id="dic_edit_id" name="id"/>
																		<input type="text" class="form-control" name="dicName" id="dicName_edit">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">字典代码 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="text" class="form-control" name="dicKey" id="dicKey_edit" readonly>
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">字典类型 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<select id="dicTypeId_edit" class="form-control select2" placeholder="请选择" name="dicTypeId">
														                </select>
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">是否启用 <span class="required" aria-required="true"> * </span> </label>
																<div class="col-md-6">
																		<input type="checkbox" id="status_edit" name="status" value="1" checked class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
																</div>
														</div>
														<div class="form-group">
																<label class="control-label col-md-3">备注</label>
																<div class="col-md-6">
																		<textarea id="description_edit" name="description" class="form-control" maxlength="225" rows="3" placeholder=""></textarea>
																</div>
														</div>
														
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="dic_edit_return">取消</button>
								<button type="button" class="btn btn-primary" onclick="dicEdit();">保存</button>
						</div>
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
																<input type="hidden" value="" id="dicId"/>
																<label class="control-label">此操作会删除级联的子字典，是否删除所选字典？</label>
														</div>
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="dic_del_return">取消</button>
								<button type="button" class="btn btn-primary" onclick="dicDelete();">确定</button>
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

<%@ include file="dicList_js.jsp"%>

</body>

<!-- Mirrored from www.keenthemes.com/preview/conquer/layout_boxed_page.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 09 Dec 2014 13:45:50 GMT -->
</html>