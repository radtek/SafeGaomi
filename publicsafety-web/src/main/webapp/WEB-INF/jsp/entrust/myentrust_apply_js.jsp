<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script  type="text/javascript" src="${ctx}/js/jquery/jqgrid/i18n/grid.locale-cn.js"></script>
<script  type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
    //初始化是先清空查询条件
    $("#submit_form")[0].reset();
    //处理案件类型
	var caseTypeJson=${caseTypeJson};
    creatMuitSelect(caseTypeJson,"caseType","caseTypetree",true,"selected");
    $("#list4").jqGrid({
        url:"${ctx}/entrust/myApply/queryEntrust.html",
        ajaxGridOptions:{       
         type:'post',        
         async:false,          
         dataType: 'json'   
        },
        datatype:"json", //数据来源，本地数据
        mtype:"POST",//提交方式
        height:"300",//高度，表格高度。
        autowidth:true,//自动宽
        viewrecords: true,
        colNames:['序号','委托编号','案(事)件名称','案(事)件类别','状态','共享案件信息','共享查询结果','委托时间','编辑'],
        colModel:[
            {name:'no',index:'no', width:'6%'},
            {name:'entrustInfo.entrustNo',index:'entrustInfo.entrustNo', width:'28%',formatter:formatColEntrustNo},
            {name:'caseInfo.caseName',index:'caseInfo.caseName', width:'15%'},
            {name:'caseInfo.rev1',index:'caseInfo.caseType', width:'13%'},
            {name:'entrustInfo.processState',index:'entrustInfo.processState', width:'6%',formatter:function(cellvalue, options, rowObject){
				  var rs=cellvalue;
				  $.each(${processStateJson},function(i,item){
				   if(cellvalue==item.processState_code){
				       rs= item.processState_name;
				    }
                 });
                 if(rs==null){
                   rs="";
                 }
				return rs;   
			}},
            {name:'entrustInfo.isShareCaseinfo',index:'entrustInfo.isShareCaseinfo', width:'13%',formatter:function(cellvalue, options, rowObject){
				var rs=cellvalue;
				if(rs=='1'){
				  rs="已共享";
				}else if(rs=='0'){
				  rs='未共享';
				}else{
				  rs="";
				}
				return rs;   
			}},
            {name:'entrustInfo.isShareQueryresult',index:'entrustInfo.isShareQueryresult', width:'13%',formatter:function(cellvalue, options, rowObject){
				var rs=cellvalue;
				if(rs=='1'){
				  rs="已共享";
				}else if(rs=='0'){
				  rs='未共享';
				}else{
				  rs="";
				}
				return rs;   
			}},
            {name:'entrustInfo.rev1',index:'entrustInfo.createDate', width:'12%'},
            {name:'editinfo',index:'editinfo', width:'6%',formatter:function(cellvalue, options, rowObject){
				  var dataStatus=rowObject.entrustInfo.processState;
				  var temp ="";
				  var entrustNo=rowObject.entrustInfo.entrustNo;
				  if(dataStatus=="save"){//暂存
				      temp = "<a href='javascript:void(0)' onclick='linkToModel(\"save\",\""+entrustNo+"\")' class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='继续编辑'><i class='fa fa-edit'></i></a>";
				  }else if(dataStatus=="submit"){//审核中
				      temp="<a href='javascript:void(0)' class='btn btn-default btn-xs black popovers' data-trigger='hover' data-container='body' data-placement='left' data-content='您的委托正在审核中，请耐心等待。' data-original-title='审核中'><i class='fa fa-spinner'></i></a>";
				  }else if(dataStatus=="finish"){//已完成
				      temp="<a href='javascript:void(0)' onclick='linkToModel(\"finish\",\""+entrustNo+"\")' class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='查看详细信息'><i class='fa fa-chain'></i></a>";
				  }else if(dataStatus=="feedback"){//已反馈
				      temp="<a href='javascript:void(0)' onclick='linkToModel(\"feedback\",\""+entrustNo+"\")' class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='查看详细信息'><i class='fa fa-chain'></i></a>";
				  }else if(dataStatus=="notpass"){
				      temp = "<a href='javascript:void(0)' onclick='linkToModel(\"save\",\""+entrustNo+"\")'  class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='继续编辑'><i class='fa fa-edit'></i></a>";
				  }else if(dataStatus=="result"){ //阿里已返回查询数据，还未做确认，公安用户无法查看，仍显示处理中
				      temp="<a href='javascript:void(0)' class='btn btn-default btn-xs black popovers' data-trigger='hover' data-container='body' data-placement='left' data-content='您的委托正在处理中，请耐心等待。' data-original-title='处理中'><i class='fa fa-spinner'></i></a>";
				  }else if(dataStatus=="processing"){
				      temp="<a href='javascript:void(0)' class='btn btn-default btn-xs black popovers' data-trigger='hover' data-container='body' data-placement='left' data-content='您的委托正在处理中，请耐心等待。' data-original-title='处理中'><i class='fa fa-spinner'></i></a>";
				  }
				  return temp;
				 }
			}
        ],
        jsonReader : {id : "0", repeatitems : false, userdata : "userdata"},  
        rowNum:10,//每页显示记录数
        rowList:[10,15,20,25],//用于改变显示行数的下拉列表框的元素数组。
        pager:$('#gridPager'),
        gridComplete: function() {
		   	$("a.popovers").popover();
			$("a.tooltips").tooltip();
        }
    });
    
});
  
	function formatColEntrustNo(cellvalue, options, rowObject){
		  var dataStatus=rowObject.entrustInfo.processState;
		  var temp ="";
		  var entrustNo=rowObject.entrustInfo.entrustNo;
		  temp = "<a href='javascript:void(0)' onclick='clickEntrustNo(\""+entrustNo+"\")'>"+entrustNo+"</a>";
		  return temp;
	}

  function clickEntrustNo(entrustNo){ 
       	var iWidth = 1025;
		var iHeight = 700;
		var iTop = 0;
		var iLeft = 0;
		var win = window.open("${ctx}/entrust/view/index.html?entrustNo="+entrustNo, "弹出窗口", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	}
  
  function linkToModel(type,entrustNo){
     if(type=="save"){
        window.location.href="${ctx}/entrust/apply/index.html?entrustNo="+entrustNo+"&type=2";
     }else if(type=="feedback"){
        window.location.href="${ctx}/entrust/feedback/index.html?entrustNo="+entrustNo+"&process=feedback";
     }else if(type=="finish"){
         window.location.href="${ctx}/entrust/feedback/index.html?entrustNo="+entrustNo+"&process=finish";
     }
  }

//查询我的委托信息
function queryEntrust(){
   var isreset=$("#isreset").val();
   var isShareCaseinfo = $("#isShareCaseinfoDiv").find("input:radio:checked").val();
   var isShareQueryresult = $("#isShareQueryresultDiv").find("input:radio:checked").val();
   if(isShareCaseinfo==undefined){isShareCaseinfo = ''};
   if(isShareQueryresult==undefined){isShareQueryresult = ''};
   var entrustNo=$("#entrustNo").val();
   var caseName=$("#caseName").val();
   var casetype =$("#caseType").val();
   if(casetype==''){
     $("#caseTypetreeinput-search").attr("value","");
   }
   var entrustDateBegin= $("#entrustDateBegin").val();
   var entrustDateEnd= $("#entrustDateEnd").val();
   var processState="";
   if(isreset!='1'){
	   $("input[name='processState']:checkbox").each(function(){ 
	      if(!$(this).parent().hasClass("active")){
	         processState=processState+ $(this).val()+",";
	      }              
	   })
   }
   $("#list4").jqGrid("setGridParam", {
	    url:"${ctx}/entrust/myApply/queryEntrust.html",
	    datatype:"json", //数据来源，本地数据
	    mtype:"POST",//提交方式
	    postData:{
	    'caseLX':casetype,
	    'caseName':caseName,
	    'entrustDateBegin':entrustDateBegin,
	    'entrustDateEnd':entrustDateEnd,
	    'processState':processState,
	    'entrustNo':entrustNo,
	    'isShareCaseinfo':isShareCaseinfo,
	    'isShareQueryresult':isShareQueryresult
	    }
    }).trigger("reloadGrid");
 }
    
    //设置 多选选中状态
    function SelectStatus (name,index) {
        $("#isreset").attr("value","0");
		var obj = name+index;
		$("label[name='"+name+"']").each(function (i,item) {
			if (obj == $(item).attr("id")) {
			    var className=$("#"+obj).attr("class");
				    if(className=="btn btn-success active" || className=="btn btn-success"){
				        $("#"+obj).find("i").remove();
						$(item).removeClass("btn btn-success active");
						$(item).addClass("btn btn-default");
					}else{
					    $(item).removeClass("btn btn-default active");
					    $("#"+name+"input"+index).after("<i class='fa fa-check'></i>");
						$(item).addClass("btn btn-success").addClass(" active").addClass(" active");
					}
			}
		})
		setTimeout("queryEntrust()", 100);
	}
	
	//设置单选状态
	function SelectForSingle (name,index) {
		var obj = name + "_" + index;
		$("label[name='"+name+"']").each(function (i,item) {
			if (obj == $(item).attr("id")) {
					if(!$(this).find("input").is(':checked')) {
						$(this).removeClass("btn-default");
						$(this).addClass("btn-success");
						$(this).find("input").attr("checked","checked");
						$(this).find("input").after("<i class='fa fa-check'></i>");
					} 
			} else {
					$(this).removeClass("btn-success");
					$(this).addClass("btn-default");
					$(this).find("i").remove();
					$(this).find("input").attr("checked",false);
			}
		})
	}
	
 //重置查询条件
  function resetQueryStr(){
    $("#isreset").attr("value","1");
    var caseTypes=$("#caseType").val();
    if(caseTypes!=''){
       var caseTypear=caseTypes.split(",");
       for(var i=0;i<caseTypear.length;i++){
           var bean =caseTypear[i];
           $("#caseTypetreeinputview").removeTag(bean.split(":")[1]);
       }
    }
    $("#caseType").attr("value",'');
    $("#caseTypetreeinput-search").attr("value",'');
    $("label[name='processState']").each(function (i,item) {
      var className=$("#processState"+i).attr("class");
      if(className=="btn btn-success active" || className=="btn btn-success"){
	        $("#processState"+i).find("i").remove();
			$(item).removeClass("btn btn-success active");
			$(item).removeClass("btn btn-success");
			$(item).addClass("btn btn-default active");
			$("#processStateinput"+i).attr("checked","");
		}
	});
	
	 $("label[name='isShareQueryresultLabel']").each(function (i,item) {
	 	if($(this).find("input:radio").is(':checked')){
	 		$(this).removeClass("btn-success")
			$(this).addClass("btn-default");
			$(this).find("i").remove();
			$(this).find("input:radio").attr("checked",false);
 		} 
	 });
	
	 $("label[name='isShareCaseinfoLabel']").each(function (i,item) {
	 	if($(this).find("input:radio").is(':checked')){
	 		$(this).removeClass("btn-success")
			$(this).addClass("btn-default");
			$(this).find("i").remove();
			$(this).find("input:radio").attr("checked",false);
 		} 
	 });
  }
 </script>