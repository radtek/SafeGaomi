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
<meta name="current-res-id" content="dataQuery"/>
<!--<![endif]-->
<!-- BEGIN HEAD -->
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
								<label class="control-label col-md-3">采集账户：</label>
								<div class="col-md-9">
									<input type="text" class="form-control" placeholder="" id="proViderPhoneNum" name="proViderPhoneNum" />
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="control-label col-md-3">采集地点：</label>
								<div class="col-md-9">
									<input type="text" class="form-control" placeholder="" id="collectPlace" name="collectPlace">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-offset-1 col-md-5">
							<div class="form-group">
								<label class="control-label col-md-3">采集时间：</label>
								<div class="col-md-9">
									<div class="input-group">
					                    <input type="text" name="collectBegDatetime" id="collectBegDatetime"  class="form-control date form_datetime">
					                    <span class="input-group-addon"> 至 </span>
					                    <input type="text" name="collectEndDatetime" id="collectEndDatetime"  class="form-control date-set date form_datetime">
					                </div>
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="control-label col-md-3">车牌号：</label>
								<div class="col-md-9">
									<!-- <div class="input-group">
					                    <input type="text" name="createBegDatetime" id="createBegDatetime"  class="form-control date form_datetime">
					                    <span class="input-group-addon"> 至 </span>
					                    <input type="text" name="createEndDatetime" id="createEndDatetime"  class="form-control date-set date form_datetime">
					                </div> -->
					                <input type="text" class="form-control" placeholder="" id="platNum" name="platNum">
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
								<div class="col-md-9"> <a href="javascript:;" class="btn btn-primary margin-right-10" onclick="queryOnClick();return false;"> 查询 <i class="fa fa-search "></i> </a> <a href="javascript:;" class="btn btn-default button-previous margin-right-10" onClick="resetOnClickmine(); return false;"> 重置 </a> </div>
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
						<div class="caption"> <i class="fa fa-table"></i>数据列表 </div>
						<a name="a_map" class="caption" style="float: right; cursor: pointer;text-decoration: none;" href="#" onclick="showBMapForList(this);return false;"><i class="fa fa-map"></i>查看地图</a>
					</div>
					<div class="portlet-body">
							<div class="table-responsive">
									<table  id="clueInfoTable">
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
				<h4 class="modal-title">详细信息</h4>
			</div>
			<div class="modal-body">
				<div class="scroller" style="height:500px" data-always-visible="1" data-rail-visible1="1">
					<div class="portlet-body form">
						<form action="#" class="form-horizontal">
							<div class="form-body" id="viewDiv">
								<div class="form-group">
									<label class="control-label col-md-3">采集编号：</label>
									<div class="col-md-8">
										<p class="form-control-static" id="collectNo_view"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">采集人手机号： </label>
									<div class="col-md-8">
										<p class="form-control-static" id="proViderPhoneNum_view"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">采集地点： </label>
									<div class="col-md-8">
										<p class="form-control-static" id="collectPlace_view"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">采集时间： </label>
									<div class="col-md-8">
										<p class="form-control-static" id="collectDate_view"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">上传时间：</label>
									<div class="col-md-8">
										<p class="form-control-static" id="createDate_view"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">用户单位：</label>
									<div class="col-md-8">
										<p class="form-control-static" id="unitCode"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">车牌号：</label>
									<div class="col-md-8">
										<p class="form-control-static" id="platNum_view"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">GPS信息：</label>
									<div class="col-md-8">
										<p class="form-control-static" id="GPSInfo"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">备注：</label>
									<div class="col-md-8">
										<p class="form-control-static" id="remark"></p>
									</div>
								</div>
								<h5 class="form-section">照片<span class="badge badge-info" id="cluePhotoCount">  </span></h5>
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
</div>

<%@ include file="clueInfoList_js.jsp"%>
</body>
</html>