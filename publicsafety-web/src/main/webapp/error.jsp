<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta name="_csrfToken" content="${csrf}"/>
<head>
<style type="text/css">
.autodiv{
  word-wrap:break-word; 
  word-break:break-all;
  display:block;
  width:100%;
}
.ui-jqgrid tr.jqgrow td   
{  
    white-space: normal !important;  
        height :auto;  
}  
  
th.ui-th-column div  
{  
    white-space:normal !important;  
    height:auto !important;  
    padding:0px;  
}  

li.showOrHide  
{ 
	display: none !important;
} 
#menuDiv
{
	width: 1100px !important;
}
</style>
<link href="${ctx}/ui/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/gritter/css/jquery.gritter.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/clockface/css/clockface.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/datepicker/bootstrap-datetimepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/bootstrap-colorpicker/css/colorpicker.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/jquery-multi-select/css/multi-select.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/bootstrap-switch/css/bootstrap-switch.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/jquery-tags-input/jquery.tagsinput.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/typeahead/typeahead.css">
<!-- <link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/jsgrid/jquery-ui-custom.css"> -->

<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${ctx}/ui/assets/plugins/jsgrid/ui.multiselect.css" rel="stylesheet">
<link href="${ctx}/ui/assets/plugins/jsgrid/ui.jqgrid.css" rel="stylesheet">
<link href="${ctx}/ui/assets/css/style-conquer.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${ctx}/ui/assets/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/plugins/dropzone/css/dropzone.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/jsgrid/jquery-ui-custom.css">

<!-- END THEME STYLES -->
<script src="${ctx}/js/jquery-1.8.3.min.js" type="text/javascript"></script> 
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
<script type="text/javascript" src="${ctx}/ui/assets/plugins/fuelux/js/spinner.min.js"></script> 
 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script> 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/select2/select2.min.js"></script> 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script> 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script> 
<script type="text/javascript" src="${ctx}/ui/datepicker/bootstrap-datetimepicker.js"></script> 
<script type="text/javascript" src="${ctx}/ui/datepicker/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/ui/assets/plugins/clockface/js/clockface.js"></script> 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/bootstrap-daterangepicker/moment.min.js"></script> 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script> 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script> 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script> 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js"></script> 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/jquery.input-ip-address-control-1.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/jquery-multi-select/js/jquery.multi-select.js"></script> 
<script src="${ctx}/ui/assets/plugins/bootstrap-pwstrength/pwstrength-bootstrap.min.js" type="text/javascript"></script> 
<script src="${ctx}/ui/assets/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script> 
<script src="${ctx}/ui/assets/plugins/jquery-tags-input/jquery.tagsinput.min.js" type="text/javascript"></script> 
<script src="${ctx}/ui/assets/plugins/bootstrap-markdown/js/bootstrap-markdown.js" type="text/javascript"></script> 
<script src="${ctx}/ui/assets/plugins/bootstrap-markdown/lib/markdown.js" type="text/javascript"></script> 
<script src="${ctx}/ui/assets/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js" type="text/javascript"></script> 
<script src="${ctx}/ui/assets/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script> 
<script src="${ctx}/ui/assets/plugins/typeahead/handlebars.min.js" type="text/javascript"></script> 
<script src="${ctx}/ui/assets/plugins/typeahead/typeahead.bundle.min.js" type="text/javascript"></script> 
<script src="${ctx}/ui/assets/plugins/jstree/dist/jstree.min.js"></script>
<!-- BEGIN JQGRID SCRIPTS --> 
<script  type="text/javascript" src="${ctx}/js/jquery/jqgrid/i18n/grid.locale-cn.js"></script>
<script  type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<!-- BEGIN JQGRID SCRIPTS --> 
<!-- END PAGE LEVEL PLUGINS --> 
<!-- BEGIN PAGE LEVEL SCRIPTS --> 
<script src="${ctx}/ui/assets/scripts/app.js"></script> 
<script src="${ctx}/ui/assets/scripts/form-components.js"></script> 
<script src="${ctx}/ui/assets/scripts/ui-tree.js"></script> 
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/jquery.metadata.js"></script>

<!-- dropzone begin -->
<script src="${ctx}/ui/assets/plugins/dropzone/dropzone.js"></script>
<script src="${ctx}/ui/assets/scripts/form-dropzone.js"></script>
<!-- dropzone end -->
</head>
<!-- BEGIN HEADER -->
<script type="text/javascript">
  
   function historyPage(){
      history.go(-1);
   }

   function linkMeun(url){
      document.location.href=url;
   }
   
   function logout () {
   	   //document.location.href = '${ctx}/j_spring_security_logout';
   	   $("#logoutForm").submit();
   }
   
   
</script>

<script>
jQuery(document).ready(function() {       
   // initiate layout and plugins
   App.init();
   FormComponents.init();
  // FormDropzone.init();
   
});
</script> 
<!-- BEGIN GOOGLE RECAPTCHA --> 
<script type="text/javascript">
var RecaptchaOptions = {
   theme : 'custom',
   custom_theme_widget: 'recaptcha_widget'
};
</script> 
<body class="page-header-fixed page-sidebar-closed">
<div class="header navbar  navbar-fixed-top"> 
  <!-- BEGIN TOP NAVIGATION BAR -->
  <div class="header-inner container" id="menuDiv"> 
    <!-- BEGIN LOGO -->
    <div class="page-logo"> <img src="${ctx}/ui/assets/img/logo1.png" alt="logo"/> </div>
    <!-- END LOGO --> 
    <!-- BEGIN RESPONSIVE MENU TOGGLER --> 
    <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <img src="${ctx}/ui/assets/img/menu-toggler.png" alt=""/> </a> 
    <!-- END RESPONSIVE MENU TOGGLER --> 
    <!-- BEGIN TOP NAVIGATION MENU -->
    <ul class="nav navbar-nav pull-right">
    
      <li class="dropdown" > <a href="#NONE" class="dropdown-toggle" data-toggle="modal" data-hover="dropdown" data-close-others="true"> <i class="icon-home"></i> </a> </li>
      <li class="dropdown showOrHide"  id="RES_entrust_apply"> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/entrust/apply/index.html')" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <i class="icon-note"></i> 委托申请 </a> </li>
      <li class="dropdown showOrHide"  id="RES_my_entrust"> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/entrust/myApply/index.html')" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <i class="icon-briefcase"></i> 我的委托 </a> </li>
      <li class="dropdown showOrHide"  id="RES_entrust_check"> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/entrust/check/index.html')" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <i class="icon-shuffle"></i>委托审核 </a> </li>
      <li class="dropdown showOrHide"  id="RES_myCba"> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/seriesCase/myseries/index.html')"  class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <i class="icon-shuffle"></i>我的串并案 </a> </li>
      
      <li class="dropdown showOrHide"  id="RES_search"> <a href="#NONE" class="dropdown-toggle" data-toggle="modal" data-hover="dropdown" data-close-others="true"> <i class="icon-magnifier"></i> 一键搜 </a> </li>
      <li class="dropdown showOrHide"  id="RES_statistics"> <a href="#NONE" class="dropdown-toggle" data-toggle="modal" data-hover="dropdown" data-close-others="true"> <i class="icon-bar-chart"></i> 统计 </a> </li>
      <li class="devider"> &nbsp; </li>
      <!-- BEGIN USER LOGIN DROPDOWN -->
      <li class="dropdown" id="RES_system"> <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <i class="icon-settings"></i> </a>
        <ul class="dropdown-menu">
          <li id="RES_account" class="showOrHide" > <a href="javascript:void(0)" onclick="linkMeun('${ctx}/background/account/list.html')"><i class="fa fa-user"></i> 用户管理</a> </li>
          <li id="RES_role" class="showOrHide"> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/background/role/list.html')"><i class="fa fa-group"></i> 角色管理</a> </li>
          <li  class="divider" name="noHide"> </li>
<!--           <li> <a href="javascript:void(0)"><i class="fa fa-cubes"></i> 模块管理</a> </li> -->
          <li id="RES_dic" class="showOrHide"> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/background/dic/list.html')"><i class="fa fa-book"></i> 字典管理</a> </li>
          <li id="RES_log" class="showOrHide"> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/background/log/list.html')"><i class="fa fa-file-code-o"></i> 日志管理</a> </li>
        </ul>
      </li>
      
      <!-- END USER LOGIN DROPDOWN -->
    </ul>
    <!-- END TOP NAVIGATION MENU --> 
  </div>
  <!-- END TOP NAVIGATION BAR --> 
</div>
<!-- END HEADER -->
<div class="modal fade" id="logout" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">注销</h4>
						</div>
						<form action="${ctx}/loginout.html" class="form-horizontal" method="post" id="logoutForm">
						<div class="modal-body">
								<div class="portlet-body form">
										
												<div class="form-body">
														<div class="form-group">
																<label class="control-label">确定要退出登录吗？</label>
														</div>
												</div>
										
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
								<button type="submit" class="btn btn-primary" >确定</button>
						</div>
						</form>
				</div>
		</div>
</div>

<div class="modal fade" id="sessionOut" tabindex="-1" role="basic" aria-hidden="true">
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
														<div class="form-group">
																<label class="control-label"><h4 class="modal-title">系统超时，请重新登录</h4></label>
														</div>
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-primary"  onclick="logout();">确定</button>
						</div>
				</div>
		</div>
</div>
<div class="modal fade" id="onBuilding" tabindex="-1" role="basic" aria-hidden="true">
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
														<div class="form-group">
																<label class="control-label"><h4 class="modal-title">该模块正在建设中</h4></label>
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
<div class="clearfix"> </div>
	<div class="container">
		<div  style="padding-top:200px" align="center">
                              系统出现异常，请联系系统管理员! 
		</div>
</div>
</body>
</html>