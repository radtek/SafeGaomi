<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script  type="text/javascript" src="${ctx}/js/jquery/jqgrid/i18n/grid.locale-cn.js"></script>
<script  type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript">
  //办案单位缓存
  var unitMap = {};
  var html_unit = '<option value=""> </option>';
  //页面初始化
  $(document).ready(function(){

    //处理案件类型
	var caseTypeJson=${caseTypeJson};
    creatMuitSelect(caseTypeJson,"caseType","qcaseTypetree",true,"selected");
    //处理委托单位
	var unitTypejson=${unitTypejson};
    creatMuitSelect(unitTypejson,"unit","unittree",true,"selected1");
     $("#acceptdiv").attr("href","#");
     $("#acceptdiv").click(function(){
         $("#acceptdiv").attr("href","#");
		 var ids = $("#checkTable").jqGrid('getGridParam','selarrrow');
	     var len=ids.length;
	     var count=0;
	     var content = '';
	     if(len>0){
	     	  for (var j = 0; j < len; j ++) {
	     	  	   var rowData = $("#checkTable").jqGrid("getRowData",ids[j]);
	        	   var val= rowData.processState;
	        	   if (val == 'submit') { //批量审核时选择有待确认的委托信息，返回
	        	  
	        	   } else if (val == 'result') {
	        	   		content = '包含未确认反馈委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'processing') {
	        	   		content = '包含已审核通过委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'notpass') {
	        	   		content = '包含已审核未通过委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'feedback') {
	        	   		content = '包含已确认反馈委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'finish') {
	        	   		content = '包含已完成反馈委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   }
	     	  }
	     	  if(parseInt(count)==0){
	             $("#acceptdiv").attr("href","#accept");
	          }else{
	          	 $("#noConfirmContent").html(content);
	             $("#acceptdiv").attr("href","#noConfirm");
	          }
	       }else{
	       		$("#acceptdiv").attr("href","#noSelected");
	      }
      });
      
     $("#checkpassbutton").click(function(){
	      checkEntrustMany("pass");
     });
     
     $("#rejectdiv").attr("href","#");
     $("#rejectdiv").click(function(){
         $("#acceptdiv").attr("href","#");
		 var ids = $("#checkTable").jqGrid('getGridParam','selarrrow');
	     var len=ids.length;
	     var count=0;
	     var content = '';
	     if(len>0){
	          for (var j = 0; j < len; j ++) {
	     	  	   var rowData = $("#checkTable").jqGrid("getRowData",ids[j]);
	        	   var val= rowData.processState;
	        	   if (val == "submit") { //批量审核时选择有待确认的委托信息，返回
	        	     
	        	   } else if (val == 'result') {
	        	   		content = '包含未确认反馈委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'processing') {
	        	   		content = '包含已审核通过委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'notpass') {
	        	   		content = '包含已审核未通过委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'feedback') {
	        	   		content = '包含已确认反馈委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'finish') {
	        	   		content = '包含已完成反馈委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   }
	     	  }
	     	  
	     	   if(parseInt(count)==0){
	              $("#rejectdiv").attr("href","#reject");
	           }else{
	           	  $("#noConfirmContent").html(content);
	              $("#rejectdiv").attr("href","#noConfirm");
	           }
	       }else{
	       	$("#rejectdiv").attr("href","#noSelected")
	       	//   alert("请选择需要审核的委托信息!");
	      }
      });
      
     $("#checknotpassbutton").click(function(){
	      checkEntrustMany("notpass");
     });
     $("#confirmdiv").attr("href","#");
     $("#confirmdiv").click(function(){
         $("#confirmdiv").attr("href","#");
		 var ids = $("#checkTable").jqGrid('getGridParam','selarrrow');
	     var len=ids.length;
	     var count=0;
	     var content = '';
	     if(len>0){
	     	  for (var j = 0; j < len; j ++) {
	     	  	   var rowData = $("#checkTable").jqGrid("getRowData",ids[j]);
	        	   var val= rowData.processState;
	        	   if (val == 'result') { 
	        	      
	        	   } else if (val == 'submit') {
	        	   		content = '包含未审核委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'processing') {
	        	   		content = '包含已审核通过委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'notpass') {
	        	   		content = '包含已审核未通过委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'feedback') {
	        	   		content = '包含已确认反馈委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } else if (val == 'finish') {
	        	   		content = '包含已完成反馈委托信息，请重新选择!';
	        	   		count=parseInt(count)+1;
	        	        break;
	        	   } 
	     	  }
	     	  if(parseInt(count)==0){
	             $("#confirmdiv").attr("href","#confirm");
	          }else{
	             //alert("包含非未确认分开委托信息，请重新选择!");
	             $("#noPassContent").html(content);
	             $("#confirmdiv").attr("href","#noPass");
	          }
	       }else{
	       		$("#confirmdiv").attr("href","#noSelected");
	      }
      });
     $("#checkconfirmbutton").click(function(){
	      checkEntrustMany("feedback");
     });
     
     
     unit_init (${dicJsonArr});
     $("select[name='unit']").append(html_unit);
     var CSRFToken=$("#CSRFToken").val();
     $("#checkTable").jqGrid({
        url:"${ctx}/entrust/check/queryCheckEntrust.html?CSRFToken="+CSRFToken+"&isdefault=1",
        ajaxGridOptions:{       
         type:'post',        
         async:false,          
         dataType: 'json'   
        },
        datatype:"json", //数据来源，本地数据
        mtype:"POST",//提交方式
        height:"300",//高度，表格高度。
        autowidth:true,
        viewrecords: true,
        multiselect: true, // 是否显示复选框 
        multiselectWidth:30,
        colNames:['序号','委托编号','委托编号','案(事)件名称','状态','委托单位','委托人','委托时间','委托状态','编辑'],
        colModel:[
            {name:'no',index:'no', width:'6%'},
            {name:'entrustInfo.rev2',index:'entrustInfo.rev2',hidden:true, width:'5%'},
            {name:'entrustInfo.entrustNo',index:'entrustInfo.entrustNo', width:'28%',key:true,formatter:formatColEntrustNo},
            {name:'caseInfo.caseName',index:'caseInfo.caseName', width:'20%'},
            {name:'entrustInfo.processState',index:'entrustInfo.processState', width:'10%',formatter:function(cellvalue, options, rowObject){
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
            {name:'entrustInfo.unit',index:'entrustInfo.unit', width:'10%'},
            {name:'entrustInfo.personName',index:'entrustInfo.personName', width:'10%'},
            {name:'entrustInfo.rev1',index:'entrustInfo.rev1', width:'11%'},
            {name:'processState', index:'processState',
            	   formatter:function(cellvalue, options, rowObject){ 
            	   	 var dataStatus=rowObject.entrustInfo.processState;
            	   	 return dataStatus;
            	   },
            	   hidden:true},
            
            {name:'edit',index:'edit', width:'6%',formatter:function(cellvalue, options, rowObject){
                   var entrustNo=rowObject.entrustInfo.entrustNo;
                   var dataStatus=rowObject.entrustInfo.processState;
                   var html = '';
                   if(dataStatus=="submit"){//暂存
                   	   html="<a href='javascript:void(0)' onclick='linkToModel(\"submit\",\""+entrustNo+"\")' class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='审核'><i class='fa fa-edit'></i></a>";
				   } else if (dataStatus=="result") {
				   	   html="<a href='javascript:void(0)' onclick='linkToModel(\"result\",\""+entrustNo+"\")' class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='待确认'><i class='fa fa-chain'></i></a>";
				   }
				   return html;
				}
			}
        ],
        jsonReader : {
          root:"rows",
          page: "page",
          total: "total",
          records: "records",
          repeatitems: false,
          userdata: "userdata"
         },  
        rowNum:10,//每页显示记录数
        rowList:[10,15,20,25],//用于改变显示行数的下拉列表框的元素数组。
        pager:$("#checkgridPager"),
        gridComplete: function() {
			$("a.tooltips").tooltip();
		   	$("a.popovers").popover();
        }
    });
  });

 //格式化委托列表，委托编号列
 function formatColEntrustNo(cellvalue, options, rowObject){
		  var dataStatus=rowObject.entrustInfo.processState;
		  var temp ="";
		  var entrustNo=rowObject.entrustInfo.entrustNo;
		  temp = "<a href='javascript:void(0)' onclick='clickEntrustNo(\""+entrustNo+"\")'>"+entrustNo+"</a>";
		  return temp;
	}
	
 //点击委托编号，查询委托详情信息
 function clickEntrustNo(entrustNo){  //event
       	var iWidth = 1025;
		var iHeight = 700;
		var iTop = 0;
		var iLeft = 0;
		var win = window.open("${ctx}/entrust/view/index.html?entrustNo="+entrustNo, "弹出窗口", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	}

 //单条审核，审核详情
 function linkToModel(type,entrustNo){
        window.location.href="${ctx}/entrust/check/queryEntrustDetalCheck.html?entrustNo="+entrustNo+"&process="+type;
  }

  //查询审核委托
  function queryCheckList(){
   var casetype =$("#caseType").val();
   if(casetype==''){
      $("#caseTypetreeinput-search").attr("value","");
   }
   var caseName=$("#caseName").val();
   var entrustNo=$("#entrustNo").val();
   var personName=$("#personName").val();
   var unit=$("#unit").val();
   if(unit==""){
     $("#unittreeinput-search").attr("value","");
   }
   var entrustDateBegin=$("#entrustDateBegin").val();
   var entrustDateEnd=$("#entrustDateEnd").val();
   var CSRFToken=$("#CSRFToken").val();
   var processState="";
   $("input[name='processState']:checkbox").each(function(){ 
	      if(!$(this).parent().hasClass("active")){
	          processState=processState+ $(this).val()+",";
	      }            
	   });
   $("#checkTable").jqGrid("setGridParam", {
	    url:"${ctx}/entrust/check/queryCheckEntrust.html",
	    datatype:"json", //数据来源，本地数据
	    mtype:"POST",//提交方式
	    postData:{
	      'casetype':casetype,
	      'caseName':caseName,
	      'entrustNo':entrustNo,
	      'personName':personName,
	      'unit':unit,
	      'entrustDateBegin':entrustDateBegin,
	      'entrustDateEnd':entrustDateEnd,
	      'processState':processState,
	      'CSRFToken':CSRFToken
	    }
    }).trigger("reloadGrid");
  }
  
 //检查是否为空
 function isNullData(id){
      var val=$("#"+id).val();
      if(val=="" || val==undefined){
         return false;
      }else{
        var str=$.trim(val);
         if(str==""){
           return false;
         }
      }
      return true;
    }
    
  //批量审核
  function checkEntrustMany(type){
    if(type=="notpass"){
      $("#checknotpassbutton").attr("disabled",true); 
    }else if(type=="pass"){
      $("#checkpassbutton").attr("disabled",true); 
    }else{
      $("#checkconfirmbutton").attr("disabled",true);  
    }
    var caseLX =$("#select2_sample2").val();
    var casetype="";
    if(caseLX==null){
      casetype="";
    }else{
      casetype=caseLX.toString();
    }
    var caseName=$("#caseName").val();
    var entrustNo=$("#entrustNo").val();
    var personName=$("#personName").val();
    var unit=$("#unit").val();
    var entrustDateBegin=$("#entrustDateBegin").val();
    var entrustDateEnd=$("#entrustDateEnd").val();
    var ids = $("#checkTable").jqGrid('getGridParam','selarrrow');
    var len=ids.length;
    var entrustNos="";
    if(len>0){
      var checkMemo=$("#maxlength_textarea").val();
	   if(type=="notpass"){
	      if(!isNullData("maxlength_textarea")){
	         alert("请填写审核不通过的原因!");
	          if(type=="notpass"){
			     $("#checknotpassbutton").removeAttr("disabled");
			    }else if(type=="pass"){
			      $("#checkpassbutton").removeAttr("disabled");
			    }else{
			      $("#checkconfirmbutton").removeAttr("disabled");
			    }
	         return false;
	      }
	   }
       var arrids=ids.toString().split(",");
       for(var i=0;i<arrids.length;i++){
          var id=arrids[i];
          var props = "" ; 
          var rowDatas = $("#checkTable").jqGrid('getRowData', id); 
          if(i!=len-1){
              entrustNos=entrustNos+rowDatas['entrustInfo.rev2']+",";
          }else{
              entrustNos=entrustNos+rowDatas['entrustInfo.rev2'];
          }
      }
       $.ajax({
       url:'${ctx}/entrust/check/viewCheckentrust.html', //后台处理程序
       type:'post',         //数据发送方式
       dataType:'json',     //接受数据格式
       data:{'entrustNo':entrustNos,'checkType':type,'checkMemo':checkMemo},//要传递的数据
       success:function(data){
          if(data=='1'){
             if(type=="pass"){
                  $("#accept").click();
                  $("#checkpassbutton").removeAttr("disabled");
             }else if (type == 'feedback') {
                  $("#confirm").click();
                  $("#checkconfirmbutton").removeAttr("disabled");
             }else{
                  $("#reject").click();
                  $("#checknotpassbutton").removeAttr("disabled");
             }
            $("#maxlength_textarea").attr("value",'');
            $("#checkTable").jqGrid("setGridParam", {
			    url:"${ctx}/entrust/check/queryCheckEntrust.html",
			    datatype:"json", //数据来源，本地数据
			    mtype:"POST",//提交方式
			    postData:{
			      'casetype':casetype,
			      'caseName':caseName,
			      'entrustNo':entrustNo,
			      'personName':personName,
			      'unit':unit,
			      'entrustDateBegin':entrustDateBegin,
			      'entrustDateEnd':entrustDateEnd
			    }
		    }).trigger("reloadGrid");
          }
        } 
    });
    }else{
      alert("请选择需要审核的委托！");
    }
  }

//重置表单查询条件
 function resetFomrParam(){
    $('#submit_form')[0].reset();
    $("#caseName").attr("value","");
    $("#entrustNo").attr("value","");
    $("#personName").attr("value","");
    var caseTypes= $("#caseType").val();
    if(caseTypes!=''){
       var caseTypear=caseTypes.split(",");
       for(var i=0;i<caseTypear.length;i++){
           var bean =caseTypear[i];
           $("#qcaseTypetreeinputview").removeTag(bean.split(":")[1]);
       }
    }
    
    var unitval= $("#unit").val();
    if(unitval!=''){
       var caseTypear=unitval.split(",");
       for(var i=0;i<caseTypear.length;i++){
           var bean =caseTypear[i];
           $("#unittreeinputview").removeTag(bean.split(":")[1]);
       }
    }
    $("#unit").attr("value","");
    $("#unittreeinput-search").attr("value","");
    $("#entrustDateBegin").attr("value","");
    $("#entrustDateEnd").attr("value","");
    $("#caseType").attr("value","");
    $("#caseTypetreeinput-search").attr("value","");
    $("label[name='processState']").each(function (i,item) {
      var className=$("#processState"+i).attr("class");
      if(className=="btn btn-success active" || className=="btn btn-success"){
	        $("#processState"+i).find("i").remove();
			$(item).removeClass("btn btn-success active");
			$(item).removeClass("btn btn-success");
			$(item).addClass("btn btn-default active");
			$("#processStateinput"+i).attr("checked","");
		}
	})
 }

  //初始化办案单位下拉框
  function unit_init(dicJsonArr) {
		$.each(dicJsonArr,function(i,item){
			if (item.childrenFlag == '1') {
				html_unit += '<optgroup label="'+item.dictName+'">'
				unit_init(item.childrenDicJsonArr);
				html_unit += '</optgroup>';
				var key = item.dictName;
				unitMap[key] = item.dictKey;
				return;
			}else {
				html_unit += '<option value="'+item.dictKey+'">'+item.dictName+'</option>';
				return;
			}
		});
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
						$(item).removeClass("btn btn-success");
						$(item).addClass("btn btn-default");
					}else{
					    $(item).removeClass("btn btn-default active");
					    $("#"+name+"input"+index).after("<i class='fa fa-check'></i>");
						$(item).addClass("btn btn-success").addClass(" active").addClass(" active");
					}
			}
		});
		setTimeout("queryCheckList()", 100);
	}
</script>
