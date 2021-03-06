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
<meta name="current-res-id" content="RES_role"/>
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
#role_edit_form label.error 
{ 
color:Red; 
font-size:13px; 
margin-left:5px; 
padding-left:16px; 
background:url("error.png") left no-repeat; 
} 
#role_add_form label.error 
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
								<form action="#" class="form-horizontal" id="rolesForm" novalidate>
								<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
										<div class="form-wizard">
												<div class="form-body">
														<div class="form-group">
											                    <label class="col-md-offset-1 control-label col-md-2">角色名 </label>
											                    <div class="col-md-6">
											                    	    <input type="text" class="form-control" name="name" id="name">
											                    </div>
											             </div>
														<div class="form-group">
											                	<label class="col-md-offset-1 control-label col-md-2"> 是否启用</label>
											                	<div class="col-md-6">
												                <input type="checkbox" name="enable" id="enable" checked class="make-switch"
												                  data-on-color="success"
												                  data-off-color="success" data-size="normal"
												                  data-on-text="<i class='fa fa-check'></i>是"
												                  data-off-text="<i class='fa fa-check'></i>否"
												                  />
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
								            	<div class="caption"> <i class="fa fa-table"></i> 角色列表 </div>
								            	<div class="tools add-tools"><a class="btn btn-xs btn-link" data-toggle="modal" href="#newCharacter" id="role_add_a">新增</a><a href="#" class="btn btn-xs btn-link" data-toggle="modal" onclick="batchDelete();" id="role_batch_del">批量删除</a></div>
								        </div>
							            <div class="portlet-body">
									            <div class="table-responsive">
									              		<table  id="rolesTable">
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

<div class="modal fade" id="newCharacter" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">角色新增</h4>
						</div>
						<form action="${ctx}/background/role/add.html" class="form-horizontal" name="role_add_form" id="role_add_form" method="post">
						<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
						<div class="modal-body">
								<div class="portlet-body form">
												<div class="form-body">
														<div class="form-group">
													             <label class="control-label col-md-3">角色名 <span class="required" aria-required="true"> * </span> </label>
													             <div class="col-md-6">
													              		<input type="hidden"  name="resourcesIds" id="resources_id_arr_add"/>
													                	<input type="text" class="form-control" name="name">
													             </div>
											            </div>
											            <div class="form-group">
											              		 <label class="control-label col-md-3">角色键值 <span class="required" aria-required="true"> * </span> </label>
													             <div class="col-md-6">
													                	<input type="text" class="form-control" name="roleKey">
													             </div>
											            </div>
											            <div class="form-group">
													             <label class="control-label col-md-3">角色描述 <span class="required" aria-required="true"> * </span> </label>
													             <div class="col-md-6">
													                	<textarea id="maxlength_textarea" name="description" class="form-control" maxlength="225" rows="3" placeholder=""></textarea>
													             </div>
											            </div>
											            <div class="form-group">
													             <label class="control-label col-md-3">是否启用 <span class="required" aria-required="true"> * </span> </label>
													             <div class="col-md-6">
													                	<input type="checkbox" class="make-switch" name="enable" checked value="1" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
													             </div>
											            </div>
											            <div class="form-group">
													             <label class="control-label col-md-3">选择模块 <span class="required" aria-required="true"> * </span> </label>
													             <div class="col-md-6">
													                	<div id="role" class="tree-demo" /> 
													             </div>
											            </div>
												</div>
										
								</div>
                                <div class="clearfix"> </div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="role_add_return">取消</button>
						        <button id="save_btn" type="button" class="btn btn-primary">保存</button>
						</div>
						</form>
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
																<input type="hidden" id="role_del_id" value="" />
                												<label class="control-label">是否删除所选角色？</label>
														</div>
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="del_button_return">取消</button>
       							<button type="button" class="btn btn-primary" onclick="deleteRole();">确定</button>
						</div>
				</div>
		</div>
</div>

<div class="modal fade" id="editCharacter" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">角色编辑</h4>
						</div>
						<form action="${ctx}/background/role/update.html" class="form-horizontal" name="role_edit_form" id="role_edit_form" metho="post">
						<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
						<div class="modal-body">
								<div class="portlet-body form">
												<div class="form-body">
														<div class="form-group">
													             <label class="control-label col-md-3">角色名 <span class="required" aria-required="true"> * </span> </label>
													             <div class="col-md-6">
													              		<input type="hidden"  name="resourcesIds" id="resources_id_arr_edit"/>
													                	<input type="hidden" id="roleId" name="id" value="" />
               															<input type="text" class="form-control" name="name" id="name_edit">
													             </div>
											            </div>
											            <div class="form-group">
											              		 <label class="control-label col-md-3">角色键值 <span class="required" aria-required="true"> * </span> </label>
													             <div class="col-md-6">
													                	<input type="text" class="form-control" name="roleKey" id="roleKey_edit" readOnly>
													             </div>
											            </div>
											            <div class="form-group">
													             <label class="control-label col-md-3">角色描述 <span class="required" aria-required="true"> * </span> </label>
													             <div class="col-md-6">
													                	<textarea id="description_edit" name="description" class="form-control" maxlength="225" rows="3" placeholder=""></textarea>
													             </div>
											            </div>
											            <div class="form-group">
													             <label class="control-label col-md-3">是否启用 <span class="required" aria-required="true"> * </span> </label>
													             <div class="col-md-6">
													                	<input type="checkbox" name="enable" id="enable_edit" value="1" class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
													             </div>
											            </div>
											            <div class="form-group">
													             <label class="control-label col-md-3">选择模块 <span class="required" aria-required="true"> * </span> </label>
													             <div class="col-md-6">
													                	<div id="role1" class="tree-demo" /> 
													             </div>
											            </div>
												</div>
										
								</div>
                                <div class="clearfix"> </div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" id="role_edit_return">取消</button>
        						<button type="button" class="btn btn-primary" id="update_btn">保存</button>
						</div>
						</form>
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


<%@ include file="roleList_js.jsp"%>

</body>

<!-- Mirrored from www.keenthemes.com/preview/conquer/layout_boxed_page.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 09 Dec 2014 13:45:50 GMT -->
</html>