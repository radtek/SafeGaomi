<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta name="_csrfToken" content="${csrf}"/>
<head>
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
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/bootstrap-treeview/css/bootstrap-treeview.css">
<!-- <link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/jsgrid/jquery-ui-custom.css"> -->

<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/jsgrid/jquery-ui-custom.css">
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/fancybox/source/jquery.fancybox.css" />

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
<link href="${ctx}/ui/assets/css/home.css" rel="stylesheet" type="text/css"/>
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
<script src="${ctx}/ui/assets/plugins/bootstrap-treeview/js/bootstrap-treeview.js" type="text/javascript"></script> 
<!-- BEGIN JQGRID SCRIPTS --> 
<script  type="text/javascript" src="${ctx}/js/jquery/jqgrid/i18n/grid.locale-cn.js"></script>
<script  type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<!-- BEGIN JQGRID SCRIPTS --> 
<!-- END PAGE LEVEL PLUGINS --> 
<!-- BEGIN PAGE LEVEL SCRIPTS --> 
<script src="${ctx}/ui/assets/scripts/form-components.js"></script> 
<script src="${ctx}/ui/assets/scripts/ui-tree.js"></script> 
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/jquery.metadata.js"></script>

<script type="text/javascript" src="${ctx}/ui/assets/plugins/fancybox/source/jquery.fancybox.pack.js"></script> 

<script src="${ctx}/ui/assets/scripts/app.js"></script> 
</head>
<!-- BEGIN HEADER -->
<script type="text/javascript">
  
   function historyPage(){
      history.go(-1);
   }

   function linkMeun(url){
      document.location.href=url;
   }
   
   //注销
   function logout () {
   	   //document.location.href = '${ctx}/j_spring_security_logout';
   	   $("#logoutForm").submit();
   }
   
   $(function () {
   		$.ajaxSetup({
	   		headers: {
				'CSRFToken': $('meta[name="_csrfToken"]').attr('content')
			},
			complete: function(xmlHttp) {
				if(xmlHttp.status==403){
					alert("系统超时,请重新登录");
		        	location.href= '${ctx}/login.html'; //跳转到登陆页面
		        } else if(xmlHttp.status==601) {
		        	// 跳转到检查cookie是否启用的页面
					location.href= '${ctx}/login.html?sys_check_page=cookie_check'; 
		        } else if(xmlHttp.status==602) {
		        	// 跳转到cookie超时的页面
					location.href= '${ctx}/login.html?sys_check_page=cookie_timeout'; 
		        } else if(xmlHttp.status==603) {
		        	// 检测到CSRF黑客攻击
					location.href= '${ctx}/login.html'; 
		        }
	        }
		});
   })
   
   //当前模块高亮显示
   function addHightLightByMenu(menuId) {
   		$("#"+menuId+"").addClass("active");
   }
   
   //重置（通用）
   function resetOnClick () {
   		var obj = document.getElementById("QueryForm");
		if(obj == undefined) {
			return;
		}
		
		for (var i = 0; i < obj.elements.length; i++) {
			if(obj.elements[i].type == "text") {
				obj.elements[i].value = "";
			}
		}
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
<div class="header navbar  navbar-fixed-top"> 
  <!-- BEGIN TOP NAVIGATION BAR -->
  <div class="header-inner container" id="menuDiv"> 
    <!-- BEGIN LOGO -->
    <div class="page-logo"> <img src="${ctx}/ui/assets/img/logo3.png" alt="logo"/> </div>
    <!-- END LOGO --> 
  
    <!-- BEGIN RESPONSIVE MENU TOGGLER --> 
    <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <img src="${ctx}/ui/assets/img/menu-toggler.png" alt=""/> </a> 
    <!-- END RESPONSIVE MENU TOGGLER --> 
    <!-- BEGIN TOP NAVIGATION MENU -->
    <ul class="nav navbar-nav pull-right">
    
      <li class="dropdown" id="main"> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/ccp/main/index.html')" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <i class="fa fa-bar-chart"></i> 首页 </a> </li>
      <li class="dropdown" id="clueInfo"> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/ccp/clueInfo/index.html')" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <i class="fa fa-table"></i> 数据查询 </a></li>
      <li class="dropdown" id="clueProvider"> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/ccp/clueProvider/index.html')"  class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <i class="fa fa-user"></i>用户管理 </a> </li>
      
      <li class="devider"> &nbsp; </li>
      <!-- BEGIN USER LOGIN DROPDOWN -->
       <li class="dropdown" id="system"> <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <i class="icon-settings"></i> </a> 
         <ul class="dropdown-menu"> 
           <li> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/background/account/list.html')"><i class="fa fa-user"></i> 系统用户管理</a> </li> 
           <li> <a href="javascript:void(0)" onclick="linkMeun('${ctx}/ccp/blackPlat/index.html')"><i class="fa fa-user"></i> 车牌预警管理 </a> </li>         
         </ul> 
       </li> 
      <li  class="dropdown" > 
      	<a id="a_log_out" href="#logout" class="dropdown-toggle tooltips" data-toggle="modal" data-placement="bottom" data-original-title=""> 注销 <i class="fa fa-sign-out"></i></a> 
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
						<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
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
