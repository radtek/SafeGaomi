<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
 $(document).ready(function(){
     //初始化我的串并案列表
	 $("#seriesCasetable").jqGrid({
	        url:"${ctx}/seriesCase/myseries/queryMySeriesCaseList.html", 
	        ajaxGridOptions:{       
	          type:'post',        
	          async:false,          
	          dataType: 'json'   
	        },
	        datatype:"json", //数据来源，本地数据
	        mtype:"POST",//提交方式
	        height:"300",//高度，表格高度。
	        multiselect: true, // 是否显示复选框 
	        autowidth:true,
	        viewrecords: true,
	        colNames:['序号','主键','编号','串并案名称','串并依据','串并时间','案件数','操作'],
	        colModel:[
	            {name:'no',index:'no', width:'5%'},
	            {name:'seriesCaseInfo.id',index:'seriesCaseInfo.id',hidden:true,width:'5%'},
	            {name:'seriesCaseInfo.seriesCaseNo',index:'seriesCaseNo', width:'20%',formatter:queryCBAbyId},
	            {name:'seriesCaseInfo.seriesCaseName',index:'seriesCaseName', width:'10%'},
	            {name:'seriesCaseInfo.seriesReason',index:'seriesReason', width:'10%'},
	            {name:'seriesCaseInfo.rev1',index:'createDate', width:'8%'},
	            {name:'caseCount',index:'caseCount', width:'8%'},
	            {name:'edit',index:'edit', width:'10%',formatter:function(cellvalue, options, rowObject){
	                var sid=rowObject.seriesCaseInfo.id;
	                var html="";
	                html=html+"<a class='btn btn-default btn-xs black tooltips margin-right-10' data-placement='left' data-original-title='编辑' data-toggle='moda'  onclick='linkToEditSeriesCase("+sid+");'><i class='fa fa-edit'></i></a>";
	                html=html+"<a class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='删除' data-toggle='modal' href='#del' onclick='deleteSeriesCase("+sid+");' ><i class='fa fa-trash-o'></i></a>";
	                return html;
	            }}
	        ],
	        jsonReader : {
	          root:"rows",
	          page: "page",
	          total: "total",
	          records: "records",
	          repeatitems: false,
	          userdata: "userdata"
	         },
	        gridComplete:function(){
	            $("a.tooltips").tooltip();
		   		$("a.popovers").popover();
	        },
	        rowNum:10,//每页显示记录数
	        rowList:[10,15,20,25],//用于改变显示行数的下拉列表框的元素数组。
	        pager:$("#seriesCasegridPager")
	    });
	    
 
     //绑定查询方法
     $("#querybutton").click(function(){
          var seriesCaseNo=$("#seriesCaseNo").val();
          var seriesCaseName=$("#seriesCaseName").val();
          var seriesPerson="";
	      $("input[name='seriesperson']:checkbox").each(function(){ 
	      var ischeck= $(this).attr("checked");
	      if(ischeck!=undefined){
	          seriesPerson=seriesPerson+ $(this).val()+","; 
	        }            
	      })
	     var dateBegin=$("#dateBegin").val();
	     var dateEnd=$("#dateEnd").val();
	     $("#seriesCasetable").jqGrid("setGridParam", {
			    url:"${ctx}/seriesCase/myseries/queryMySeriesCaseList.html",
			    datatype:"json", //数据来源，本地数据
			    mtype:"POST",//提交方式
			    postData:{
			    'seriesCaseNo':seriesCaseNo,
			    'seriesCaseName':seriesCaseName,
			    'seriesPerson':seriesPerson,
			    'dateBegin':dateBegin,
			    'dateEnd':dateEnd
			    }
		    }).trigger("reloadGrid"); 
     });
     
     //绑定重置方法
     $("#resetbutton").click(function(){
	      $("#submit_form")[0].reset();
	      $("#seriesCaseNo").attr("value","");
	      $("#seriesCaseName").attr("value","");
	      $("#dateBegin").attr("value","");
	      $("#dateEnd").attr("value","");
	      $("label[name='seriesperson']").each(function (i,item) {
             var className=$("#seriesperson"+i).attr("class");
             if(className=="btn btn-success active" || className=="btn btn-success"){
		        $("#seriesperson"+i).find("i").remove();
				$(item).removeClass("btn btn-success active");
				$(item).removeClass("btn btn-success");
				$(item).addClass("btn btn-default active");
				$("#seriespersoninput"+i).attr("checked","");
		      }
	      })
     });
     
      //绑定点击新增按钮事件
   $("#addSeriesCaseButton").click(function(){
         document.location.href="${ctx}/seriesCase/myseries/showQueryCaseinfo.html";
    });
 });
 
 //设置串并案依据多选状态
  function selectStatus (name,index) {
		var obj = name+index;
		$("label[name='"+name+"']").each(function (i,item) {
			if (obj == $(item).attr("id")) {
			    var className=$("#"+obj).attr("class");
				    if(className=="btn btn-success active" || className=="btn btn-success"){
				        $("#"+obj).find("i").remove();
						$(item).removeClass("btn btn-success active");
						$(item).removeClass("btn btn-success");
						$(item).addClass("btn btn-default");
					}else{
					    $(item).removeClass("btn btn-default active");
					    $("#"+name+"input"+index).after("<i class='fa fa-check'></i>");
						$(item).addClass("btn btn-success").addClass(" active").addClass(" active");
					}
			}
		})
  }
 
 //跳转到查询单一串并案详细页面
 function queryCBAbyId(cellvalue, options, rowObject){
    var seriesId=rowObject.seriesCaseInfo.id;
    var html="";
    html=html+"<a href='javascript:void(0)' onclick='showSeriesCaseInfo("+seriesId+")'>"+cellvalue+"</a>";
    return html;
 }

 //查看串并案详细
 function showSeriesCaseInfo(seriesId){
    document.location.href="${ctx}/seriesCase/myseries/showSeriesCaseinfo.html?seriesId="+seriesId;
 }
	
 //删除我的串并案
 function deleteSeriesCase(sid){
   $.ajax({
     url:"${ctx}/seriesCase/myseries/deleteSeriesCases.html", //后台处理程序
     type:'post',         //数据发送方式
     dataType:'json',     //接受数据格式
     data:"seriesIds="+sid,         //要传递的数据
     success:function(data){
         $("#querybutton").click();
      }
     }) 
 }
 
 //批量删除我的串并案
  //删除我的串并案
 function deleteSeriesCases(){
   var ids = $("#seriesCasetable").jqGrid('getGridParam','selarrrow');
   var len=ids.length;
   var arrids=ids.toString().split(",");
   var sid="";
   if(len>0){
	   for(var i=0;i<len;i++){
	       var id=arrids[i];
	       var rowDatas = $("#seriesCasetable").jqGrid('getRowData', id); 
	      
	       sid=sid+rowDatas['seriesCaseInfo.id'];
	      
	       if(i!=len-1){
	         sid=sid+",";
	       }
	   }
	   $.ajax({
	     url:"${ctx}/seriesCase/myseries/deleteSeriesCases.html", //后台处理程序
	     type:'post',         //数据发送方式
	     dataType:'json',     //接受数据格式
	     data:"seriesIds="+sid,         //要传递的数据
	     success:function(data){
	          $("#querybutton").click();
	       }
	     }) 
    }else{
      alert("请选择需要删除的记录!");
    }
 }
 
 //点击编辑按钮，跳转到编辑串并案界面
 function linkToEditSeriesCase(seriesId){
    document.location.href="${ctx}/seriesCase/myseries/showSeriesCaseinfoToEdit.html?seriesId="+seriesId;
 }
 
</script>