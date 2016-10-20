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

<style type="text/css">  
html{height:100%}  
body{height:100%;margin:0px;padding:0px}  
#container{height:100%}  
</style> 

<script src="${ctx}/js/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GWW9aVRUCWHcXdIz82MMlAAf">
//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<div id="container"></div> 
	<script type="text/javascript"> 
	var pointArr = new Array();
	
	var map = new BMap.Map("container"); 
	
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
	
	$(function () {
		$.each(${listJsonArr},function(i,item){
			var lon = item.lon;
			var lat = item.lat;
			if (lon == '' || lat == '') { //经纬度信息不完整时
				return true; //跳出当前循环
			}
			var point = new BMap.Point(lon,lat);
			
			pointArr[i] = point;
			
			map.centerAndZoom(point, 15);
			
			var marker = new BMap.Marker(point);        // 创建标注    
			map.addOverlay(marker); 	
			
			var opts = {    
			 	width : 250,     // 信息窗口宽度    
			 	height: 150,     // 信息窗口高度    
			 	title : "车辆照片"  // 信息窗口标题   
			}    
			var infoWindow = new BMap.InfoWindow("拍摄于："+item.collectDate+"<br><img src=\"data:image/png;base64,"+item.photoBase64Str+"\" width=\"150px\" height=\"100px\" >", opts);  // 创建信息窗口对象    
			
			if(i == 0) {
				map.openInfoWindow(infoWindow, point);      // 打开信息窗口
			}
			
			marker.addEventListener("click", function(){    //监听标注事件
				map.openInfoWindow(infoWindow, point); 
			}); 
			
		}); 
		
		//折线
		var polyline = new BMap.Polyline(    
		   pointArr,    
		 {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5}    
		);    
		map.addOverlay(polyline);
	});
	
	</script> 
</body>
</html>