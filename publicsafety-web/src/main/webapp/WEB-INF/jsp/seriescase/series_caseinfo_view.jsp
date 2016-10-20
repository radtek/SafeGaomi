<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
</style>
</head>
<body>
	<%@ include file="../common/meun.jsp" %>
	<div class="clearfix"></div>
	<div class="container">
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN SIDEBAR -->
			<!-- END SIDEBAR -->
			<!-- BEGIN CONTENT -->
			<div class="col-md-12 margin-top-10">
			</div>
			<div class="col-md-12">
				<!-- BEGIN SAMPLE TABLE PORTLET-->
				<div class="portlet">
					<div class="portlet-body">
						<div class="tabbable">
							<ul class="nav nav-tabs">
								<input type="hidden" id="entrustSer" value="${entrustData.entrustInfo.entrustSer}"/>
								<li><a href="#tab_1" data-toggle="tab">案件信息</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active form-horizontal form-bordered" id="tab_1">
									<div class="table-responsive">
										<div class="form-body">
											<div class="form-group">
												<div class="col-md-2 text-right gray">案件名称</div>
												<div class="col-md-4"> ${entrustData.caseInfo.caseName}</div>
												<div class="col-md-2 text-right gray">案件编号</div>
												<div class="col-md-4">${entrustData.caseInfo.caseNo}</div>
											</div>
											<div class="form-group">
												<div class="col-md-2 text-right gray">案发时间</div>
												<div class="col-md-4">${entrustData.caseInfo.caseOccurDate}</div>
												<div class="col-md-2 text-right gray">涉案品牌</div>
												<div class="col-md-4">${entrustData.caseInfo.brand}</div>
											</div>
											<div class="form-group">
												<div class="col-md-2 text-right gray">案件金额</div>
												<div class="col-md-4">${entrustData.caseInfo.caseMoneyAmout}</div>
												<div class="col-md-2 text-right gray">案件类型</div>
												<div class="col-md-4">
												  ${pdicName}
												</div>
											</div>
											<div class="form-group">
												<div class="col-md-2 text-right gray">简要案情</div>
												<div class="col-md-10"> <div class="autodiv">${entrustData.caseInfo.caseInfo}</div></div>
											</div>
											<div class="form-group">
												<div class="col-md-1 text-right gray"></div>
												<div class="col-md-10">
												    <div style="padding-left:8px;"><font style="font-weight:bold">受害人信息 </font></div>
													<div style="margin:0;padding:0; width:100%;height:1px;background-color:#999;overflow:hidden;"></div> 
													<table class="table table-hover">
														<thead>
															<tr>
																<th>序号</th>
																<th>姓名</th>
																<th>身份证</th>
																<th>手机</th>
																<th>淘宝账号</th>
																<th>支付宝账号</th>
																<th>所在地址</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${entrustData.victimInfo}" var="bean" varStatus="status">
					                                        <tr >
					                                        <td> <span>${status.index+1}</span></td>
					                                        <td> ${bean.victimName}</td>
					                                        <td> ${bean.victimCardNo}</td>
					                                        <td> ${bean.victimTelephone}</td>
					                                        <td> ${bean.victimTaobaoNo}</td>
					                                        <td> ${bean.victimAlipay}</td>
					                                        <td> ${bean.victimAddress} </td>
					                                        </tr>
					                                     </c:forEach>
														</tbody>
													</table>
												</div>
											</div>
											<div class="form-group last">
												<div class="col-md-1 text-right gray"> </div>
												<div class="col-md-10">
												    <div style="padding-left:8px;"><font style="font-weight:bold">嫌疑人信息</font> </div>
													<div style="margin:0;padding:0; width:100%;height:1px;background-color:#999;overflow:hidden;"></div> 
													<table class="table table-hover">
														<thead>
															<tr>
																<th>序号</th>
																<th>姓名</th>
																<th>身份证</th>
																<th>手机</th>
																<th>淘宝账号</th>
																<th>支付宝账号</th>
																<th>QQ</th>
																<th>电子邮件</th>
															</tr>
														</thead>
														<tbody>
														  <c:forEach items="${entrustData.suspectInfo}"  var="bean" varStatus="status">
						                                        <tr >
						                                        <td> <span>${status.index+1}</span></td>
						                                        <td> ${bean.suspectName}</td>
						                                        <td> ${bean.suspectCardNo}</td>
						                                        <td> ${bean.suspectTelephone} </td>
						                                        <td> ${bean.suspectTaobaoNo}</td>
						                                        <td> ${bean.suspectAlipay}</td>
						                                        <td> ${bean.suspectQq}</td>
						                                        <td> ${bean.suspectEmail}</td>
						                                        </tr>
						                                  </c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>