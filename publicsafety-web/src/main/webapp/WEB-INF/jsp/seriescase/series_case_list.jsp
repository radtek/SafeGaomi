<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<meta http-equiv="content-type"  content="text/html;charset=UTF-8" />
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
<%@ include file="../common/meun.jsp"%>
<div class="clearfix"> </div>
	<div class="container">
		<div class="page-container">
			<div class="page-content-wrapper">
				<div class="row">
					<form action="#" class="form-horizontal" id="submit_form" novalidate>
						<div class="form-wizard">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-offset-1 control-label col-md-2">
										串并案编号 </label>
									<div class="col-md-6">
										<input type="text" class="form-control" id="seriesCaseNo" name="seriesCaseNo">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-offset-1 control-label col-md-2">
										串并案名称</label>
									<div class="col-md-6">
										<input type="text" class="form-control" id="seriesCaseName" name="seriesCaseName">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-offset-1 control-label col-md-2">
										串并案依据 </label>
									<div class="col-md-6">
										<div class="btn-group" data-toggle="buttons">
										 <c:forEach items="${cbatype}" var="bean" varStatus="status">
										  <label class="btn btn-success" id="seriesperson${status.index}" name="seriesperson" onclick="selectStatus('seriesperson',${status.index});">
											  <input type="checkbox"  id="seriespersoninput${status.index}"  value="${bean.dicKey}"  name="seriesperson"  class="toggle">
											  <i class="fa fa-check"></i> ${bean.dicName}
											</label>
										 </c:forEach>
										</div>
									</div>
								</div>
                                 <div class="form-group">
					                <label class="col-md-offset-1 control-label col-md-2"> 串并时间</label>
					                <div class="col-md-6">
					                  <div class="input-group">
					                    <input type="text" id="dateBegin" name="dateBegin"  class="form-control date-set date form_datetime">
					                    <span class="input-group-addon"> 至 </span>
					                    <input type="text" id="dateEnd" name="dateEnd"  class="form-control date-set date form_datetime">
					                    <input type="hidden" name="CSRFToken" value="${csrf}" />
					                  </div>
					                </div>	
					              </div>
					              
								<div class="form-group">
									<div class="col-md-offset-3 col-md-9">
										<a href="javascript:void(0)" id="querybutton" class="btn btn-primary margin-right-10">
											查询 <i class="fa fa-search "></i> 
									    </a> 
										<a href="javascript:void(0)" id="resetbutton" class="btn btn-default button-previous margin-right-10">
											 重置 
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
								<i class="fa fa-table"></i>串并案列表
							</div>
							<div class="tools add-tools">
								<a class="btn btn-xs btn-link" id="addSeriesCaseButton" data-toggle="modal" href="javascript:void(0)">新增串并案</a>
								<a href="javascript:void(0)" onclick="deleteSeriesCases()" class="btn btn-xs btn-link">批量删除</a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-responsive">
						       <table  id="seriesCasetable"></table>
			                   <div id="seriesCasegridPager"></div>
							</div>
						</div>
					</div>
				</div>
				<!-- END SAMPLE TABLE PORTLET-->

			</div>
		</div>
	</div>
	<%@ include file="series_case_list_js.jsp"%>
</body>
</html>