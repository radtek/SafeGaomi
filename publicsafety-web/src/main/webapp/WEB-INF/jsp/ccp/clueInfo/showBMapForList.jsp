<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta name="_csrfToken" content="${csrf}"/>
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

<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
</style>

<link href="${ctx}/ui/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui/assets/plugins/fancybox/source/jquery.fancybox.css" />

<link href="${ctx}/ui/assets/css/style-conquer.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${ctx}/ui/assets/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/ui/assets/css/home.css" rel="stylesheet" type="text/css"/>

<script src="${ctx}/js/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script type="text/javascript" src="${ctx}/ui/assets/plugins/fancybox/source/jquery.fancybox.pack.js"></script> 
<script src="${ctx}/ui/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="${ctx}/ui/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script> 

<script src="${ctx}/ui/assets/scripts/app.js"></script>

</script>
<!-- 异步加载地图解决了由于bootstrap与fancybox的未知原因导致的fancybox内嵌百度地图初始化bug（地图未能将坐标点放置在中心位置） -->
</head>
<body>
	<div id="allmap"></div>
	<div class="modal fade" id="details_map" tabindex="-1" data-backdrop="static" data-keyboard="false" aria-hidden="false" >
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
								<div class="form-body" id="viewDiv_map">
									<div class="form-group">
										<label class="control-label col-md-3">采集编号：</label>
										<div class="col-md-8">
											<p class="form-control-static" id="collectNo_view_map"></p>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">采集人手机号： </label>
										<div class="col-md-8">
											<p class="form-control-static" id="proViderPhoneNum_view_map"></p>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">采集地点： </label>
										<div class="col-md-8">
											<p class="form-control-static" id="collectPlace_view_map"></p>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">采集时间： </label>
										<div class="col-md-8">
											<p class="form-control-static" id="collectDate_view_map"></p>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">上传时间：</label>
										<div class="col-md-8">
											<p class="form-control-static" id="createDate_view_map"></p>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">车牌号：</label>
										<div class="col-md-8">
											<p class="form-control-static" id="platNum_view_map"></p>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">GPS信息：</label>
										<div class="col-md-8">
											<p class="form-control-static" id="GPSInfo_map"></p>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">备注：</label>
										<div class="col-md-8">
											<p class="form-control-static" id="remark_map"></p>
										</div>
									</div>
									<h5 class="form-section">照片<span class="badge badge-info" id="cluePhotoCount_map">  </span></h5>
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
</body>
</html>

<script>
jQuery(document).ready(function() {       
   // initiate layout and plugins
   App.init();
  // FormDropzone.init();
});
</script> 

<script type="text/javascript">
	function loadJScript() {
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = "http://api.map.baidu.com/api?v=2.0&ak=GWW9aVRUCWHcXdIz82MMlAAf&callback=init";
		document.body.appendChild(script);
	}
	function init() {
		initBMapForList(); 
	}  
	window.onload = loadJScript;  //异步加载地图
</script>
<script>
	var initBMapForList = function () {
		var pointArr = new Array();
		var pointArrIndex = 0;
	
		var map = new BMap.Map("allmap"); 
		map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		
		var opts = {type: BMAP_NAVIGATION_CONTROL_LARGE}    //表示显示完整的平移缩放控件。
		map.addControl(new BMap.NavigationControl(opts)); //地图平移缩放控件，PC端默认位于地图左上方，它包含控制地图的平移和缩放的功能
		
		/**
		* 增加全景图控件
		*/
		var stCtrl = new BMap.PanoramaControl();  
		stCtrl.setOffset(new BMap.Size(20, 40));  
		map.addControl(stCtrl);
		
		/**
		* 地图类型控件，去除三维
		*/
		var optMapType = {mapTypes: [BMAP_NORMAL_MAP,BMAP_SATELLITE_MAP,BMAP_HYBRID_MAP]};
		var mapTypeCtrl = new BMap.MapTypeControl(optMapType);
		map.addControl(mapTypeCtrl);
		
		$.each(${listJsonArr},function(i,item){
			var lon = item.lon;
			var lat = item.lat;
			if (lon == '' || lat == '') { //经纬度信息不完整时
				alert(lon+"*************"+lat);
				return true; //跳出当前循环
			}
			var point = new BMap.Point(lon,lat);
			
			pointArr[pointArrIndex] = point;
			pointArrIndex ++;
			
			map.centerAndZoom(point, 15);
			
			var marker = new BMap.Marker(point);        // 创建标注    
			map.addOverlay(marker); 	
			
			var opts = {    
			 	width : 250,     // 信息窗口宽度    
			 	height: 180,     // 信息窗口高度    
			 	title : "线索基本信息"  // 信息窗口标题   
			}    
			var opts1 = {title : '<span style="font-size:14px;color:#0A8021">线索基本信息</span> <a  href="#" style="font-size:10px;text-decoration:none;color:#2679BA;float:right" onclick="showDetail(\''+item.id+'\');return false;">详情>></a>'};
			
			var sContent ="<div class='myclass'>拍摄时间："+item.collectDate+"<br>线索人电话："+item.proViderPhoneNum+"<br><img id='cluePhoto' src=\"${ctx}"+item.firstPhotoSrc+"\" width=\"150px\" height=\"100px\" ></div>";
			var content1 = "<div style='line-height:1.8em;font-size:12px;'><b>采集时间:</b>"+item.collectDate+"</br><b>线索人电话：</b>"+item.proViderPhoneNum+"</br><img id='cluePhoto' src=\"${ctx}"+item.firstPhotoSrc+"\" width=\"150px\" height=\"100px\" ></div>";
			
			var infoWindow = new BMap.InfoWindow(content1,opts1);  // 创建信息窗口对象    
			
			if(i == 0) {
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //添加跳动的动画
				marker.openInfoWindow(infoWindow, point);      // 打开信息窗口
			}
			
			infoWindow.addEventListener("close",function () { //信息窗口关闭时出发此事件
				marker.setAnimation(null); //取消动画效果；
			});
			
			marker.addEventListener("click", function(){    //监听标注事件
				marker.openInfoWindow(infoWindow, point); 
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //添加跳动的动画
			}); 
			
		}); 
		
		//折线
		var polyline = new BMap.Polyline(    
		   pointArr,    
		 {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5}    
		);    
		map.addOverlay(polyline);
	}
</script>
<script>
	var showDetail = function (id) {
		$.ajax({
			headers: {
				'CSRFToken': $('meta[name="_csrfToken"]').attr('content')
			},
			async : false,
			type : "POST",
			datatype:"json", //数据来源，本地数据
			url: "${ctx}/ccp/clueInfo/view.html",
            data: {'id' : id},
            success: function(data){
            	if (data.flag == 'success')  {
              		//填充表单
              		$("#collectNo_view_map").text(data.clueInfo.collectNo);
              		$("#proViderPhoneNum_view_map").text(data.clueInfo.proViderPhoneNum);
              		$("#collectPlace_view_map").text(data.clueInfo.collectPlace);
              		$("#collectDate_view_map").text(data.clueInfo.collectDate);
              		$("#createDate_view_map").text(data.clueInfo.createDate);
              		$("#platNum_view_map").text(data.clueInfo.platNum);
              		$("#GPSInfo_map").text(data.clueInfo.lon + "/" + data.clueInfo.lat);
              		$("#remark_map").text(data.clueInfo.remark);
              		$("#cluePhotoCount_map").text(data.clueInfo.cluePhotoCount);
              		
              		//照片处理
              		cluePhotoHandler(data.clueInfo.photoSrcList,data.clueInfo.collectNo,data.clueInfo.createDate);
              	} else if (data.flag == 'false'){
              		alert(data.content);
              	}
            }
		});
		$('#details_map').modal('show');
	}
	
	function cluePhotoHandler (photoSrcList,collectNo,createDate) {
		$("div[name='photoRow']").remove(); //去除之前创建的div
		
		var rownum = '';
		if ((photoSrcList.length % 2) == 0) { //可以被整除
			rownum = photoSrcList.length / 2;
		} else {
			rownum = parseInt(photoSrcList.length / 2) + 1;
		}
		var photoHtml = '';
		for (var i = 0; i < rownum; i ++) {
			var src1 = photoSrcList[i*2];
			var src2 = photoSrcList[i*2 + 1];
			photoHtml += '<div class="row" name="photoRow">';
			photoHtml += '<div class="col-md-6"> <a class="image-list fancybox-button" data-rel="458451211" href="${ctx}' + src1 + '"  title="'+collectNo+' 于 '+createDate+' 上传" data-rel="fancybox-button"> <img class="img-responsive" src="${ctx}' + src1 + '"></a> </div>';
			if ((i*2 + 1) <= (photoSrcList.length-1)) {
				photoHtml += '<div class="col-md-6"> <a class="image-list fancybox-button" data-rel="458451211" href="${ctx}' + src2 + '"  title="'+collectNo+' 于 '+createDate+' 上传" data-rel="fancybox-button"> <img class="img-responsive" src="${ctx}' + src2 + '"></a> </div>';
			}
			photoHtml += '</div>';
		}
		
		$("#viewDiv_map").append(photoHtml);
		
   		$("div[name='photoRow'] a").fancybox({
			   groupAttr: 'data-rel'
		});
	}
</script>