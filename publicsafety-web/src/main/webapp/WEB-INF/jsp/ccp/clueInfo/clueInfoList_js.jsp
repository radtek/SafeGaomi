<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<script>
jQuery(document).ready(function() {       
   // initiate layout and plugins
   App.init();
});
</script> 
<script>
	var winWidth = 0;  //定义全局变量
	var winHeight = 0;
	var unitMap = {};
	var unitCode;
	var roleIds;
	var unitName;
	
	var html_unit = '';
	var html_unit_ali = '';
	
	function unit_init(dicJsonArr) {
		$.each(dicJsonArr,function(i,item){
			if (item.childrenFlag == '1') {
				html_unit += '<optgroup label="'+item.dictName+'" value="'+item.dictKey+'">';
				var key = item.dictName;
				unitMap[key] = item.dictKey;
				unit_init(item.childrenDicJsonArr);
				html_unit += '</optgroup>';
				return;
			}else {
				//增加阿里用户所属部门
				if (item.dictKey == '000000000000') {
					html_unit_ali += '<option value="'+item.dictKey+'">'+item.dictName+'</option>';
				} else {
					html_unit += '<option value="'+item.dictKey+'">'+item.dictName+'</option>';
				};
				return;
			}
		});
	}
	//重置
   function resetOnClickmine () {
   		var obj = document.getElementById("QueryForm");
		if(obj == undefined) {
			return;
		}
		
		for (var i = 0; i < obj.elements.length; i++) {
			if(obj.elements[i].type == "text") {
				obj.elements[i].value = "";
			}else if (obj.elements[i].type == "select-one") {
				$("#unitCode_search").text("");
				$("#unitCode_search").append('<option value=""> </option>');
				$("#unitCode_search").append(html_unit_ali);
				$("#unitCode_search").append(html_unit);
				var spans =obj.getElementsByTagName("span");
				spans[1].innerHTML = "";
			
			}
		}		
   }
	$(function () {
		//获取尺寸
		findDimensions();
		//当前菜单高亮显示
		addHightLightByMenu("clueInfo");
		//为a标签加载fancybox
		initFancyBox();
		//加载用户单位代码
		unit_init (${dicJsonArr});	
		$("#unitCode_search").append(html_unit);
		
		//加载线索信息
		$("#clueInfoTable").jqGrid({
	        url:"${ctx}/ccp/clueInfo/query.html",
	        ajaxGridOptions:{       
	         type:'post',        
	         async:false,          
	         dataType: 'json'   
	        },
	        datatype:"json", //数据来源，本地数据
	        type:"POST",//提交方式
	        height:'auto',//高度，表格高度。可为数值、百分比或'auto'
	        autowidth:true,//自动宽
	        viewrecords: true,  //显示总记录数 
	        colNames:['ID','序号', '采集账户', '采集地点', '采集时间', '上传时间','用户单位','车牌号', '经纬度','缩略图', ''],
	        colModel:[
	        	{
	            	name:'id', 
	            	index:'id', 
	            	hidden:true
	            },
	            {
	            	name:'no', 
	            	index:'no', 
	            	align:'left', 
	            	width:'4%',
	            	sortable:false
	            },
	            {
	            	name:'proViderPhoneNum', 
	            	index:'proViderPhoneNum', 
	            	align:'left', 
	            	width:'12%',
	            	sortable:false
	            },
	            {
	            	name:'collectPlace', 
	            	index:'collectPlace', 
	            	align:'left', 
	            	width:'10%',
	            	sortable:false
	            },
	            {
	            	name:'collectDate', 
	            	index:'collectDate', 
	            	align:'left', 
	            	width:'13%',
	            	formatter:'date',
					formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i'},
	            	sortable:false
	            },
	            {
	            	name:'createDate', 
	            	index:'createDate', 
	            	align:'left', 
	            	width:'13%',
	            	formatter:'date',
					formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i'},
	            	sortable:false
	            },
	            {
	            	name:'unitName', 
	            	index:'unitName', 
	            	align:'left', 
	            	width:'13%',
	            	sortable:false
	            },
	            {
	            	name:'platNum', 
	            	index:'platNum', 
	            	align:'left', 
	            	width:'8%',
	            	sortable:false
	            },
	            {
	            	name:'GPSInfo', 
	            	index:'GPSInfo', 
	            	align:'left', 
	            	width:'10%',
	            	formatter:function(cellvalue, options, rowObject){
	            		var content = '';
	            		var lon = rowObject.lon;
	            		var lat = rowObject.lat;
	            		content = '经:'+lon+'\r\n纬:'+lat;
	            		return content;
	            	},
	            	sortable:false
	            },
	            {
	            	name:'thumbnailPhoto', 
	            	index:'thumbnailPhoto', 
	            	align:'left', 
	            	width:'10%',
	            	formatter:function(cellvalue, options, rowObject){  
	            		var content = '';
	            		content = '<a class="thumbnail fancybox-button" data-rel="'+rowObject.id+'" href="${ctx}'+rowObject.firstPhotoSrc+'"  title="'+rowObject.collectNo+' 于 '+rowObject.createDate+' 上传" data-rel="fancybox-button"> <img class="img-responsive" src="${ctx}'+rowObject.firstPhotoSrc+'" onclick="myFancyBox(this);return false;"> <span class="badge badge-primary thumbnail-count" onclick="myFancyBox(this);return false;">'+rowObject.cluePhotoCount+' </span></a>'; 
	            		if (rowObject.cluePhotoCount > 1) {
	            			for (var i = 0; i < rowObject.cluePhotoCount; i ++) {
	            				var photoSrc = (rowObject.photoSrcList)[i];
	            				content += '<div class="thumbnail-extra">';
			            		content += '<a class="thumbnail fancybox-button" data-rel="'+rowObject.id+'" href="${ctx}'+photoSrc+'"  title="'+rowObject.collectNo+' 于 '+rowObject.createDate+' 上传" data-rel="fancybox-button"> <img class="img-responsive" src="${ctx}'+photoSrc+'" > </a>';
			            		content += '</div>';
	            			}
	            		}
	            		return content;
	            	}, 
	            	sortable:false
	            },
	            {
	            	name:'operMod',
	            	index:'operMod',
	            	align:'left',
	            	width:'8%', 
	            	formatter:function(cellvalue, options, rowObject){  
	            		var content = '<a class="btn btn-default btn-xs black margin-right-10" data-toggle="modal" id="view" href="#details" onclick="showDetails(\'' + rowObject.id + '\');return false;"><i class="fa fa-info"></i> 详情</a>';
	            		content += '<a class="btn btn-default btn-xs black" name="a_map" data-rel="BMap" href="#" onclick="showBMap(this,\''+rowObject.id+'\',\''+rowObject.lon+'\',\''+rowObject.lat+'\');" data-rel="fancybox-button"> <i class="fa fa-map"></i> 地图</a>'; 
	            		return content;
	            	}, 
	               	sortable:false     
	            }
	        ],
	        jsonReader : {id : "id", repeatitems : false, userdata : "userdata"},  
	        rowNum:10,//每页显示记录数
	        rowList:[10,15,20,25],//用于改变显示行数的下拉列表框的元素数组。
	        pager:$('#gridPager'),
	        gridComplete:function()
         	{
         		$("a.tooltips").tooltip();
		   		$("a.popovers").popover();
         	}
	    });
	});
	
	function myFancyBox(e) {
		$(e).parent().click();
	}
	/**
	* 查询
	*/
	function queryOnClick () {
		//采集账户
		var proViderPhoneNum = $("#proViderPhoneNum").val(); 
		//采集地点
		var collectPlace = $("#collectPlace").val(); 
		//采集时间
		var collectBegDatetime = $("#collectBegDatetime").val();
		var collectEndDatetime = $("#collectEndDatetime").val();
		//车牌号
		var platNum = $("#platNum").val();
		//用户单位
		unitCode = $("#unitCode_search").val();
		if(unitCode == '') {
			unitName = $("#select2-chosen-1").text();
			unitCode = unitMap[unitName];
		};
		if(unitCode==undefined){unitCode = ''};
			
		$("#clueInfoTable").jqGrid('setGridParam',{ 
            url:"${ctx}/ccp/clueInfo/query.html",
            type : "POST", 
            postData:{
            		 	'proViderPhoneNum' : proViderPhoneNum,
            		 	'collectPlace' : collectPlace,
            		 	'collectBegDatetime' : collectBegDatetime,
            		 	'collectEndDatetime' : collectEndDatetime,
            		 	'platNum' : platNum,
            		 	'unitCode' : unitCode
            		 }, //发送数据 
            page:1 
        }).trigger("reloadGrid"); //重新载入
	}
	
	
	/**
	* 查看线索信息
	*/
	function showDetails (id) {
		$.ajax({
			async : false,
			type : "POST",
			datatype:"json", //数据来源，本地数据
			url: "${ctx}/ccp/clueInfo/view.html",
            data: {'id' : id},
            success: function(data){
            	if (data.flag == 'success')  {
              		//填充表单
              		$("#collectNo_view").text(data.clueInfo.collectNo);
              		$("#proViderPhoneNum_view").text(data.clueInfo.proViderPhoneNum);
              		$("#collectPlace_view").text(data.clueInfo.collectPlace);
              		$("#collectDate_view").text(data.clueInfo.collectDate);
              		$("#createDate_view").text(data.clueInfo.createDate);
              		$("#platNum_view").text(data.clueInfo.platNum);
              		$("#GPSInfo").text(data.clueInfo.lon + "/" + data.clueInfo.lat);
              		$("#remark").text(data.clueInfo.remark);
              		$("#cluePhotoCount").text(data.clueInfo.cluePhotoCount);
              		$("#unitCode").text(data.clueInfo.unitName);
              		
              		//照片处理
              		cluePhotoHandler(data.clueInfo.photoSrcList,data.clueInfo.collectNo,data.clueInfo.createDate);
              	} else if (data.flag == 'false'){
              		$("a[id='view']").attr("href","");
              		alert(data.content);
              	}
            }
		});
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
		
		$("#viewDiv").append(photoHtml);
		
   		$("div[name='photoRow'] a").fancybox({
			   groupAttr: 'data-rel'
		});
	}
</script>

<script>
	/**
	* 加载百度地图 list
	*/
	var showBMapForList = function (e) {
		
		//采集账户
		var proViderPhoneNum = $("#proViderPhoneNum").val(); 
		//采集地点
		var collectPlace = $("#collectPlace").val(); 
		//采集时间
		var collectBegDatetime = $("#collectBegDatetime").val();
		var collectEndDatetime = $("#collectEndDatetime").val();
		//车牌号
		var platNum = $("#platNum").val();
		
		var href = '${ctx}/ccp/clueInfo/showBMapForList.html?proViderPhoneNum='+proViderPhoneNum+'&collectPlace='+collectPlace+'&collectBegDatetime='+collectBegDatetime+'&collectEndDatetime='+collectEndDatetime+'&platNum='+platNum;
		
		$(e).attr("href",href);
	}
	
	/**
	* 加载百度地图 
	*/
	var showBMap = function (e,id,lon,lat) {
		if (lon == '' || lat == '') {
			alert("该线索经纬度信息不完整，无法定位");
		}
		var href = "${ctx}/ccp/clueInfo/showBMap.html?id="+id;
		$(e).attr("href",href);
	}
</script>

<script>
	var findDimensions = function () { //获取浏览器高度和宽度
		//获取窗口宽度 
		if (window.innerWidth) 
		winWidth = window.innerWidth; 
		else if ((document.body) && (document.body.clientWidth)) 
		winWidth = document.body.clientWidth; 
		//获取窗口高度 
		if (window.innerHeight) 
		winHeight = window.innerHeight; 
		else if ((document.body) && (document.body.clientHeight)) 
		winHeight = document.body.clientHeight; 
		
		//通过深入Document内部对body进行检测，获取窗口大小 
		if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) 
		{ 
		winHeight = document.documentElement.clientHeight; 
		winWidth = document.documentElement.clientWidth; 
		} 
		
	}
</script>

<script>
	//为a标签加载fancybox属性
	var initFancyBox = function () {
	
		$("a.fancybox-button").fancybox({
			groupAttr: 'data-rel'
		});
		
		$("a[name='a_map']").fancybox({
			type: 'iframe',
            width: winWidth,
            height: winHeight
		});
	}
</script>
