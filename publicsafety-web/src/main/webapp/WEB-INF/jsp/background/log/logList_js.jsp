<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
	var username;
	var userIp;
	var rev1;
	var operTimeBeg;
	var operTimeEnd;
	$(function () {
	
		$("#search").click("click",function () { //点击查询
			
			username = $("#username").val();
			userIP = $("#userIP").val();
			rev1 = $("#rev1").val();
			operTimeBeg =$("#operTimeBeg").val();
			operTimeEnd = $("#operTimeEnd").val();
			$("#logTable").jqGrid('setGridParam',{ 
	            url:"${ctx}/background/log/query.html",
	            type : "POST",
	            postData:{
	            			'username':username,
	            			'userIP':userIP,
	            			'rev1':rev1,
	            			'operTimeBeg':operTimeBeg,
	            			'operTimeEnd':operTimeEnd
	            		 }, //发送数据 
	            page:1 
	        }).trigger("reloadGrid"); //重新载入 
		});
		
		$("#logTable").jqGrid({
			url:"${ctx}/background/log/query.html",
			ajaxGridOptions:{       
	         type:'post',        
	         async:false,          
	         dataType: 'json'   
	        },
	        datatype:"json", //数据来源，本地数据
	        height:'auto',//高度，表格高度。可为数值、百分比或'auto'
	        autowidth:true,//自动宽
	        multiselect: true, // 是否显示复选框 
	        viewrecords: true,  //显示总记录数 
	        colNames:['ID', '序号', '操作人', '操作类型', '唯一标识', 'IP地址', '操作时间'],
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
	            	width:'8%',
	            	sortable:false
	            },
	            {
	            	name:'username', 
	            	index:'username', 
	            	align:'left', 
	            	width:'10%',
	            	sortable:false
	            },
	            {
	            	name:'module', 
	            	index:'module', 
	            	align:'left', 
	            	width:'10%',
	            	sortable:false
	            },
	            {
	            	name:'rev1', 
	            	index:'rev1', 
	            	align:'left', 
	            	width:'30%',
	            	sortable:false
	            },
	            {
	            	name:'userIP', 
	            	index:'useIP', 
	            	align:'left', 
	            	width:'10%',
	            	sortable:false
	            },
	            {
	            	name:'operTime', 
	            	index:'operTime', 
	            	align:'left', 
	            	width:'15%',
	            	sortable:false
	            }
	            
	        ],
	        jsonReader : {id : "id", repeatitems : false, userdata : "userdata"},  
	        rowNum:10,//每页显示记录数
	        rowList:[10,15,20,25],//用于改变显示行数的下拉列表框的元素数组。
	        pager:$('#gridPager')
		});
		
		$("#reset").click("click", function () { //查询重置
			$("#logFrom")[0].reset();
		});
		
	});
	
	function batchDelete () { //批量删除
		$("#del_href").attr("href","#");
		//打开删除对话框，并传值，确认删除
		var a=$("#logTable").jqGrid('getGridParam','selarrrow');
		if(a.length == 0) {
			$("#del_href").attr("href","#noSelected");
			return;
		} else {
			$("#del_href").attr("href","#del");
			var ids = a.join(","); //数组转为字符串
			$("#logId").val(ids);
		}
	};
	
	function deleteLogByIds() { //日志删除
		var ids = $("#logId").val();
		$.ajax({
			type : "POST",
			url: "${ctx}/background/log/deleteById.html",
            data: {'ids' : ids},
            success: function(msg){
            	if(msg = 'success') {
            		//关闭弹窗，刷新页面
	            	$("#del_button_return").click();
	            	
	            	username = $("#username").val();
					userIP = $("#userIP").val();
					rev1 = $("#rev1").val();
					operTimeBeg =$("#operTimeBeg").val();
					operTimeEnd = $("#operTimeEnd").val();
	            	$("#logTable").jqGrid('setGridParam',{ 
			            url:"${ctx}/background/log/query.html",
			            type : "POST",
			            postData:{
			            			'username':username,
			            			'userIP':userIP,
			            			'rev1':rev1,
			            			'operTimeBeg':operTimeBeg,
			            			'operTimeEnd':operTimeEnd
			            		 }, //发送数据 
			            page:1 
			        }).trigger("reloadGrid"); //重新载入 
			        
            	} else if (msg='false') {
            			
            	} 
            }
		});
	};
</script>