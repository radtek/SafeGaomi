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
<!-- Added by HTTrack -->
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<!-- /Added by HTTrack -->

<style>
	#chartjs-tooltip {
        opacity: 1;
        position: absolute;
        background: rgba(0, 0, 0, .7);
        color: white;
        padding: 3px;
        border-radius: 3px;
        -webkit-transition: all .1s ease;
        transition: all .1s ease;
        pointer-events: none;
        -webkit-transform: translate(-50%, 0);
        transform: translate(-50%, 0);
    }
   	.chartjs-tooltip-key{
   		display:inline-block;
   		width:10px;
   		height:10px;
   	}
</style>

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
<%@ include file="../common/meun.jsp"%>
<div class="clearfix"> </div>
<div class="container"> 
	<!-- BEGIN CONTAINER -->
	<div class="page-container"> 
		<!-- BEGIN SIDEBAR --> 
		<!-- END SIDEBAR --> 
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="col-md-12">
				<div class="margin-top-10">
					<div class="row stats-overview-cont">
						<div class="col-md-4" style="cursor: pointer;">
							<div class="dashbox panel panel-default">
								<div class="panel-body" onclick="intoModule('clue');">
									<div class="panel-left red"> <i class="fa fa-flash fa-3x"></i> </div>
									<div class="panel-right">
										<div class="number" id="clueCount">0</div>
										<div class="title">征集的线索</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4" style="cursor: pointer;">
							<div class="dashbox panel panel-default">
								<div class="panel-body" onclick="intoModule('clue');">
									<div class="panel-left green"> <i class="fa fa-file-image-o fa-3x"></i> </div>
									<div class="panel-right">
										<div class="number" id="photoCount">0</div>
										<div class="title">上传的图片</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4" style="cursor: pointer;">
							<div class="dashbox panel panel-default">
								<div class="panel-body" onclick="intoModule('clueProvider');">
									<div class="panel-left blue"> <i class="fa fa-user fa-3x"></i> </div>
									<div class="panel-right">
										<div class="number" id="providerCount">0</div>
										<div class="title">注册的用户</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

  <div class="col-md-12"> 
    
    <!-- BEGIN PORTLET-->
    <div class="portlet">
      <div class="portlet-title">
        <div class="caption"> <i class="fa fa-reorder"></i>统计表 </div>
        <div class="tools"> </div>
      </div>
      <div class="portlet-body">
        <div id="chart_5" style="height:480px;">
        	<canvas id="canvas" height="450" width="900px;"></canvas>
        </div>
        <div id="chartjs-tooltip"></div>
        <div class="btn-toolbar">
          <div class="btn-group stackControls"></div>
          <div class="space5"></div>
          <div class="btn-group graphControls">
<!--             <input type="button" class="btn" value="柱状图"/> -->
            <input type="button" class="btn" id="buttonContent" value=""/>
          </div>
        </div>
      </div>
    </div>
    <!-- END PORTLET--> 
    
    <!-- END CONTENT --> 
  </div>
</div>

<script type="text/javascript" src="${ctx}/ui/charts/Chart.js"></script>

<%@ include file="main_js.jsp"%>
</body>
</html>