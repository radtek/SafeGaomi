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
.myclass{font-size:12px; line-height:22px;}
#container{height:100%}  
</style> 

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
	var lon = '${lon}';
	var lat = '${lat}';
	var collectDate = '${collectDate}';
	
	var map = new BMap.Map("container");          // 创建地图实例
	map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	
	if (lon == '' || lat == '') { //经纬度信息不完整时
		map.centerAndZoom("高密",15);
	} else {
		var point = new BMap.Point(lon, lat);  // 创建点坐标  
		map.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别
		
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
		
		//map.addControl(new BMap.ScaleControl());    //比例尺控件，默认位于地图左下方，显示地图的比例关系。
		//map.addControl(new BMap.OverviewMapControl());  //缩略地图控件，默认位于地图右下方，是一个可折叠的缩略地图。  
		//map.addControl(new BMap.MapTypeControl());    //地图类型控件，默认位于地图右上方。
		//map.setCurrentCity("北京"); // 仅当设置城市信息时，MapTypeControl的切换功能才能可用
		
		var marker = new BMap.Marker(point);        // 创建标注    
		map.addOverlay(marker); 					// 将标注添加到地图中
		marker.setAnimation(BMAP_ANIMATION_DROP); //坠落的动画
		
		var opts = {    
		 width : 250,     // 信息窗口宽度    
		 height: 200,     // 信息窗口高度    
		 title : "线索基本信息"  // 信息窗口标题   
		}    
		var sContent ="<div class='myclass'>拍摄时间："+collectDate+"<br><img id='cluePhoto' src=\"data:image/png;base64,${photoBase64Str}\" width=\"150px\" height=\"100px\" ></div>";
		
		var infoWindow = new BMap.InfoWindow(sContent,opts);  // 创建信息窗口对象    
		//marker.openInfoWindow(infoWindow, point);      // 打开信息窗口
		
		infoWindow.addEventListener("close",function () { //信息窗口关闭时出发此事件
			marker.setAnimation(null); //取消动画效果；
		});
		
		marker.addEventListener("click", function(){    //监听标注事件
			this.openInfoWindow(infoWindow, point); 
			//图片加载完毕重绘infowindow
		    document.getElementById('cluePhoto').onload = function (){
		        infoWindow.redraw();
		    }
			
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //添加跳动的动画
		});
		
	}
	
	//折线
	/* var polyline = new BMap.Polyline([    
	   new BMap.Point(116.399, 39.910),    
	   new BMap.Point(116.405, 39.920)    
	 ],    
	 {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5}    
	);    
	map.addOverlay(polyline); */
	
	</script> 
</body>
</html>