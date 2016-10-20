<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
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
<!--<![endif]-->
<!-- BEGIN HEAD -->

<!-- Mirrored from www.keenthemes.com/preview/conquer/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 09 Dec 2014 13:45:46 GMT -->
<!-- Added by HTTrack --><meta http-equiv="content-type" content="text/html;charset=UTF-8" /><!-- /Added by HTTrack -->
<head>
<meta charset="utf-8"/>
<title>平安高密线索征集平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<meta name="MobileOptimized" content="320">
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${ctx}/ui/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/select2/select2.css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="${ctx}/ui/assets/css/style-conquer.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${ctx}/ui/assets/css/pages/login.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.html"/>

</head>
<!-- BEGIN BODY -->
<body class="login">
<div class="header navbar  navbar-fixed-top"> 
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="header-inner container"> 
		<!-- BEGIN LOGO -->
		<div class="page-logo"> <img src="${ctx}/ui/assets/img/logo3.png" alt="logo"/> </div>
		
		<!-- END LOGO --> 
		<!-- BEGIN RESPONSIVE MENU TOGGLER --> 
				<!-- END TOP NAVIGATION MENU --> 
	</div>
	<!-- END TOP NAVIGATION BAR --> 
</div>

<!-- BEGIN LOGIN -->
<div class="content" >
	<!-- BEGIN LOGIN FORM -->
    <div style="float:left; margin-left:10%; margin-top:100px; padding:15px; "><img src="${ctx}/ui/assets/img/3.png" width="450" height="430"></div>
	<div style="float:right;  margin-top:150px;">
    <form class="login-form" style="float:right;margin-right:30%"; id="loginForm" name="loginForm"
    	action="${ctx}/submitlogin.html" method="post">
    	<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
		<h3 class="form-title" style="padding-bottom:15px;">用户登录</h3>
		<div class="alert alert-danger display-hide">
			<button class="close" data-close="alert"></button>
			<span id="alert-danger-span">
			 </span>
		</div>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">用户名</label>
			<div class="input-icon">
				<i class="fa fa-user"></i>
				<input id="username" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="username"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<div class="input-icon">
				<i class="fa fa-lock"></i>
				<input id="password" class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password"/>
			</div>
		</div>
		
		<div class="form-actions">
        	<button type="submit" class="btn btn-info btn-block pull-right" id="loginButton">登 录 </button>
		</div>
		
		
	</form>
	<!-- END LOGIN FORM -->
	
	</div>
</div>
<!-- END LOGIN -->

<!-- BEGIN COPYRIGHT -->
<div class="copyright">
	 北京海鑫科金高科技股份有限公司
</div>
<!-- END COPYRIGHT -->

<div hidden>
	<a id="showOrHideCommonAlert" href="#commonDiv" class="dropdown-toggle tooltips" data-toggle="modal"  data-placement="bottom" data-original-title="通用" > </a>
</div>

<div class="modal fade" id="commonDiv" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title"></h4>
						</div>
						<div class="modal-body">
								<div class="portlet-body form">
										<form action="#" class="form-horizontal">
												<div class="form-body">
														<div class="form-group" >
																<label class="control-label" style="text-align: center;"><h4 class="modal-title" id="contentForAlert"></h4></label>
														</div>
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
						</div>
				</div>
		</div>
</div>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="${ctx}/ui/assets/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${ctx}/ui/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${ctx}/ui/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${ctx}/ui/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/ui/assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${ctx}/ui/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/ui/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctx}/ui/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${ctx}/ui/assets/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/ui/assets/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/ui/assets/scripts/app.js" type="text/javascript"></script>
<script src="${ctx}/ui/assets/scripts/login.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {     
  	App.init();
 	Login.init();
  
  	checkBrowser();
    
   	//校验cookie是否开启
   	if(!CookieEnable()) {
      	 $("#showOrHideCommonAlert").click();
      	 $("#contentForAlert").html("对不起，您的浏览器的Cookie功能被禁用，请开启");
      }
});
</script>
<!-- END JAVASCRIPTS -->
<script>
if ("${error}" != "") {
// 	alert("${error}");
	$('#alert-danger-span').html("${error}");
	$('.alert-danger').show();
};

	
  function CookieEnable()
      {
        var result=false;
        if(navigator.cookiesEnabled)
          return true;
        document.cookie = "testcookie=yes;";
        var cookieSet = document.cookie;
        if (cookieSet.indexOf("testcookie=yes") > -1)
          result=true;
        document.cookie = "";
        return result;
      }
      
  function checkBrowser() 
   	  {
   	   	var isShow = false; 
	  	//页面初始化后，判断浏览器类型，若不符合，弹出提示
	  	ua = navigator.userAgent;
	    ua = ua.toLocaleLowerCase();
	 
	 	var browserType;
	 	if (Object.hasOwnProperty.call(window, "ActiveXObject") && !window.ActiveXObject) {  //is IE11
	 		browserType = "IE11";
	 		isShow = true;
	 	}
	    else if (ua.match(/msie/) != null ) { //IE
	    	browserType = "IE";
	        browserType += ua.match(/msie ([\d.]+)/)[1];
	    } else if (ua.match(/firefox/) != null) { //firefox
	    	browserType = "火狐"
	        isShow = true;
	    } else if (ua.match(/chrome/) != null) { //chrome
	    	browserType = "Chrome"
	        isShow = true;
	    } else if (ua.match(/opera/) != null) {
	        browserType = "opera";
	    } else if (ua.match(/safari/) != null) {
	        browserType = "Safari";
	    }
	    
	    if (!isShow) {
	    	 $("#showOrHideCommonAlert").click();
      		 $("#contentForAlert").html("平安高密线索征集平台推荐使用的浏览器为Chrome、火狐或ie11以上版本,您正在使用的是"+browserType+"浏览器，可能会出现兼容性问题！");
	    }
	    
   	   }
</script>

</body>

<!-- END BODY -->

<!-- Mirrored from www.keenthemes.com/preview/conquer/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 09 Dec 2014 13:45:50 GMT -->
</html>