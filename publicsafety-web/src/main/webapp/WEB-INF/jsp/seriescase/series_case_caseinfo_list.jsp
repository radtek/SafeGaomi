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
			<div class="row">
				<form action="#" class="form-horizontal" id="submit_form"
					novalidate>
					<div class="form-wizard">
						<div class="form-body">
						
							<div class="form-group selected">
								<label class="col-md-offset-1 control-label col-md-2">案(事)件类型
								</label>
								<div class="col-md-6">
								  <input type="text"  class="form-control"  id="scaseTypeTreeinputview" name="querycastTypename"  value=""></input>
	                              <input type="hidden" name="caseType"  id="querycastType" value=""></input>
								</div>
						
							  <div id="scaseTypeTree"  style="width:470px;border:1px solid #999;border-radius:0 0 4px 4px;border-top:0;overflow-y:auto;position:absolute;left:244px;top:52px;bottom:auto;display:none;z-index:20;background-color:#fff;">
							    <div class="select2-search selected ">
				                     <input type="text" id="scaseTypeTreeinput-search" value=""></input>
				                </div>
							    <div id="scaseTypeTree-searchable" class="select2-results"></div>
							    <div class="treeview" id="scaseTypeTreesearch-output">
								  <ul class="select2-results"><li class="select2-no-results">No matches found</li></ul>
							    </div>
						      </div>
							</div>
							
							<div class="form-group">
	                          <label class="control-label col-md-3">所属区划  </label>
	                          <div class="col-md-3">
	                            <select id="select2_sample1" name="province"   class="form-control select2">
	                              <option value="1" >--请选择--</option>
	                            </select>
	                          </div>
	                          <div class="col-md-3">
	                            <select id="select2_sample2" name="city"  class="form-control select2">
	                              <option  value="1">--请选择--</option>
	                            </select>              
	                          </div>
	                        </div>
                        
							<div class="form-group">
								<label class="col-md-offset-1 control-label col-md-2">
									案件名称 </label>
								<div class="col-md-6">
									<input type="text" class="form-control" id="caseName" name="caseName">
									<input type="hidden" name="CSRFToken" value="${csrf}" />
									<input type="hidden" name="userid" id="userid" value="${userSessionId}">
								</div>
							</div>
							 <div class="form-group">
					                <label class="col-md-offset-1 control-label col-md-2">案发时间</label>
					                <div class="col-md-6">
					                  <div class="input-group">
					                    <input type="text" id="dateBegin" name="dateBegin"  class="form-control date-set date form_datetime">
					                    <span class="input-group-addon"> 至 </span>
					                    <input type="text" id="dateEnd" name="dateEnd"  class="form-control date-set date form_datetime">
					                  </div>
					                </div>	
					              </div>

							<div class="form-group">
								<div class="col-md-offset-3 col-md-9">
									<a href="javascript:void(0)" id="queryCaseinfobutton" class="btn btn-primary margin-right-10">
									          查询 <i class="fa fa-search "></i>
								    </a> 
									<a href="javascript:void(0)" id="resetQueryCaseinfobutton" class="btn btn-default button-previous margin-right-10">
									         重置 
									</a>
									<a href="javascript:void(0)" onclick="historyPage()" class="btn btn-default button-previous margin-right-10"> <i class="fa fa-angle-left"></i>
									         返回 
									</a> 
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
						<div class="caption">
							<i class="fa fa-table"></i> 待选案件列表
						</div>
						<div class="tools add-tools"></div>
					</div>
					<div class="portlet-body">
						<div class="table-responsive">
							 <table  id="seriesCaseCaseInfotable"></table>
			                 <div id="seriesCaseCaseInfogridPager"></div>
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
<%@ include file="series_case_caseinfo_list_js.jsp"%>
</body>
</html>