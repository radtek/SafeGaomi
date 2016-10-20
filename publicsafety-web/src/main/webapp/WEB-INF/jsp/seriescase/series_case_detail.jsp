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
							<div class="tools"></div>
						</div>
						<div class="portlet-body">
							<form action="#" class="form-horizontal form-bordered">
								<div class="form-body">
									<div class="form-group">
										<div class="col-md-2 text-right gray">编号</div>
										<div class="col-md-9">${Seriesdata.seriesCaseInfo.seriesCaseNo}
										<input type="hidden" id="seriesId" value="${Seriesdata.seriesCaseInfo.id}"/>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-2 text-right gray">串并案名称</div>
										<div class="col-md-9">
											${Seriesdata.seriesCaseInfo.seriesCaseName}</div>
									</div>
									<div class="form-group">
										<div class="col-md-2 text-right gray">串并依据</div>
										<div class="col-md-9">${Seriesdata.seriesCaseInfo.seriesReason}</div>
									</div>
									<div class="form-group last">
										<div class="col-md-2 text-right gray">串并描述</div>
										<div class="col-md-9">
										  ${Seriesdata.seriesCaseInfo.seriesDesc}
										</div>
									</div>
								</div>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-2 col-md-9">
											<a href='javascript:void(0)' onclick="historyPage()" class="btn btn-default button-previous margin-right-10">
												返回 </a>
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
							<div class="tools"></div>
						</div>
						<div class="portlet-body">
							<div class="table-responsive">
							   <table  id="seriesCaseDetailtable"></table>
			                   <div id="seriesCaseDetailgridPager"></div>
							<!--  
								<table class="table table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>编号</th>
											<th>案(事)件名称</th>
											<th>案(事)件类别</th>
											<th>串并依据</th>
											<th>所属区划</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>委托编号：WT201507000000002<br />
												案件编号：A1101056200002014020009</td>
											<td><a class="btn btn-xs btn-link tooltips"
												data-placement="bottom" data-original-title="查看案件详情"
												data-toggle="modal" href="#display">“20150725”A11010400020150798000协查</a>
											</td>
											<td>网络诈骗</td>
											<td>电话号码：13718933816<br /> 支付宝账号： 33897685</td>
											<td>北京朝阳区</td>
											<td><a href="#"
												class="btn btn-default btn-xs black tooltips"
												data-placement="left" data-original-title="查看"><i
													class="fa fa-chain"></i>
											</a>
											</td>
										</tr>
										<tr>
											<td>1</td>
											<td>委托编号：WT201507000000002<br />
												案件编号：A1101056200002014020009</td>
											<td><a class="btn btn-xs btn-link tooltips"
												data-placement="bottom" data-original-title="查看案件详情"
												data-toggle="modal" href="#display">“20150725”A11010400020150798000协查</a>
											</td>
											<td>网络诈骗</td>
											<td>电话号码：13718933816<br /> 支付宝账号： 33897685</td>
											<td>北京朝阳区</td>
											<td><a href="#"
												class="btn btn-default btn-xs black tooltips"
												data-placement="left" data-original-title="查看"><i
													class="fa fa-chain"></i>
											</a>
											</td>
										</tr>
										<tr>
											<td>1</td>
											<td>委托编号：WT201507000000002<br />
												案件编号：A1101056200002014020009</td>
											<td><a class="btn btn-xs btn-link tooltips"
												data-placement="bottom" data-original-title="查看案件详情"
												data-toggle="modal" href="#display">“20150725”A11010400020150798000协查</a>
											</td>
											<td>网络诈骗</td>
											<td>电话号码：13718933816<br /> 支付宝账号： 33897685</td>
											<td>北京朝阳区</td>
											<td><a href="#"
												class="btn btn-default btn-xs black tooltips"
												data-placement="left" data-original-title="查看"><i
													class="fa fa-chain"></i>
											</a>
											</td>
										</tr>
									</tbody>
								</table>
								-->
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
<%@ include file="series_case_detail_js.jsp"%>
</body>
</html>