<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<meta name="current-res-id" content="clueProvider"/>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<style type="text/css"> 
#providerEditForm label.error 
{ 
color:Red; 
font-size:13px; 
margin-left:5px; 
padding-left:16px; 
background:url("error.png") left no-repeat; 
} 
</style>
<!-- Added by HTTrack -->
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<!-- /Added by HTTrack -->
<head>
<meta charset="utf-8"/>
<title>平安高密线索征集管理平台</title>
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
			<form action="#" class="form-horizontal" id="QueryForm" novalidate>
			<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
				<div class="form-body">
					<div class="row">
						<div class="col-md-offset-1 col-md-5">
							<div class="form-group">
								<label class="control-label col-md-3">姓名：</label>
								<div class="col-md-9">
									<input type="text" class="form-control" placeholder="" id="trueName"/>
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="control-label col-md-3">手机号：</label>
								<div class="col-md-9">
									<input type="text" class="form-control" placeholder="" id="phoneNum">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-offset-1 col-md-5">
							<div class="form-group">
								<label class="control-label col-md-3">身份证号：</label>
								<div class="col-md-9">
									<input type="text" class="form-control" placeholder="" id="idCardNum">
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="control-label col-md-3">注册时间：</label>
								<div class="col-md-9">
									<div class="input-group">
					                    <input type="text" id="createBegDatetime"  class="form-control date form_datetime">
					                    <span class="input-group-addon"> 至 </span>
					                    <input type="text" id="createEndDatetime"  class="form-control date-set date form_datetime">
					                </div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-offset-1 col-md-5">
							<div class="form-group">
								<label class="control-label col-md-3 ">用户单位：</label>
								<div class="col-md-9">
									<select id="unitCode_search" class="form-control select2" placeholder="" type="text" name="unitCode">
										<option value=""> </option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="control-label col-md-3">&nbsp;</label>
								<div class="col-md-9"> 
									<a href="javascript:;" class="btn btn-primary margin-right-10" onclick="queryOnClick();return false;"> 查询 <i class="fa fa-search "></i> </a>
									<a href="javascript:;" class="btn btn-default button-previous margin-right-10" onClick="resetOnClickmine(); return false;"> 重置 </a> 
								</div>
							</div>
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
						<div class="caption"> <i class="fa fa-table"></i>用户列表 </div>
						<div class="tools add-tools"></div>
					</div>
					<div class="portlet-body">
							<div class="table-responsive">
									<table  id="clueProviderInfoTable">
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
<div class="modal fade" id="details" tabindex="-1" data-backdrop="static" data-keyboard="false" aria-hidden="false" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">用户信息</h4>
			</div>
			<div class="modal-body">
				<div class="portlet-body form">
					<form action="#" class="form-horizontal">
						<div class="form-body">
							<div class="form-group">
								<label class="control-label col-md-3"> 姓名：</label>
								<div class="col-md-8">
									<p class="form-control-static" name="trueName" id="trueName_view"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">用户单位：</label>
								<div class="col-md-8">
									<p class="form-control-static" id="unitCode"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">手机号： </label>
								<div class="col-md-6">
									<p class="form-control-static" name="phoneNum" id="phoneNum_view"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">身份证号： </label>
								<div class="col-md-6">
									<p class="form-control-static" name="idCardNum" id="idCardNum_view"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">注册时间：</label>
								<div class="col-md-6">
									<p class="form-control-static" name="createDate" id="createDate_view"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">用户积分：</label>
								<div class="col-md-6">
									<p class="form-control-static" name="score" id="score_view"></p>
								</div>
							</div>
							<div class="form-group">
									<label class="control-label col-md-3">是否启用 ：</label>
									<div class="col-md-6">
											<input type="checkbox" id="openFlag_view" name="openFlag" class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
									</div>
							</div>
							<h5 class="form-section">身份证正反面照片</h5>
							<div class="row">
								<div class="col-md-6">
									<a class="image-list fancybox-button" name="frontPhotoBig" data-rel="458451211" href=""  title="正面" data-rel="fancybox-button">
										<img class="img-responsive" name="frontPhoto" src=""/> 
									</a>
								</div>
								<div class="col-md-6">
									<a class="image-list fancybox-button" name="backPhotoBig" data-rel="458451211" href=""  title="反面" data-rel="fancybox-button">
										<img class="img-responsive" name="backPhoto" src=""/> 
									</a> 
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="editUser" tabindex="-1" data-backdrop="static" data-keyboard="false" aria-hidden="false" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">修改用户信息</h4>
			</div>
			<div class="modal-body">
				<div class="portlet-body form">
					<form action="${ctx}/ccp/clueProvider/update.html" method="post" class="form-horizontal" id="providerEditForm" name="providerEditForm">
						<input type="hidden" id="providerId" name="id"/>
						<div class="form-body">
							<div class="form-group">
								<label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>姓名： </label>
								<div class="col-md-6">
									<input type="text" class="form-control" name="trueName" id="trueName_edit">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>手机号： </label>
								<div class="col-md-6">
									<input type="text" class="form-control" name="phoneNum" id="phoneNum_edit" readonly>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>用户单位242： </label>
								<div class="col-md-6">
									<input type="hidden" name="" id="unitCode_edit_hidden" />
									<select class="form-control select2" placeholder="请选择" name="unitCode" id="unitCode_edit">
										<option value=""> </option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"><span class="required" aria-required="true"> * </span>身份证号：</label>
								<div class="col-md-6">
									<input type="text" class="form-control" name="idCardNum" id="idCardNum_edit">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">注册时间：</label>
								<div class="col-md-6">
									<input type="text" class="form-control" readonly id="createDate_edit">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">用户积分：</label>
								<div class="col-md-6">
									<input type="text" class="form-control" name="score" id="score_edit">
								</div>
							</div>
							<div class="form-group">
									<label class="control-label col-md-3">是否启用 <span class="required" aria-required="true"> * </span> </label>
									<div class="col-md-6">
											<input type="checkbox" id="openFlag_edit" name="openFlag" class="make-switch" data-on-color="success" data-off-color="success" data-size="normal" data-on-text="<i class='fa fa-check'></i> 是" data-off-text="<i class='fa fa-check'></i> 否">
									</div>
							</div>
							<h5 class="form-section">身份证正反面照片</h5>
							<div class="row">
								<div class="col-md-6">
									<a class="image-list fancybox-button" name="frontPhotoBig" data-rel="458451211" href=""  title="正面" data-rel="fancybox-button">
										<img class="img-responsive" name="frontPhoto" src=""/> 
									</a>
								</div>
								<div class="col-md-6">
									<a class="image-list fancybox-button" name="backPhotoBig" data-rel="458451211" href=""  title="反面" data-rel="fancybox-button">
										<img class="img-responsive" name="backPhoto" src=""/> 
									</a> 
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="edit_button_return">返回</button>
				<button type="button" class="btn btn-primary" onclick="edit();return false;">保存</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="del" tabindex="-1" data-backdrop="static" data-keyboard="false" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">删除</h4>
			</div>
			<div class="modal-body">
				<div class="portlet-body form">
					<form action="#" class="form-horizontal">
						<div class="form-body">
							<div class="form-group">
								<label class="control-label">是否删除所选用户？</label>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<input type="hidden" id="delId" />
				<button type="button" class="btn btn-default" data-dismiss="modal" id="del_button_return">返回</button>
				<button type="button" class="btn btn-primary" onclick="deleteById();return false;">确定</button>
			</div>
		</div>
	</div>
</div>
<%@ include file="clueProviderList_js.jsp"%>
</body>
</html>