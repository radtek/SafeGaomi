<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

 //缓存依据串的案件编号
 var seriesMap = {};
 
 //缓存委托编号信息
 var idEntstNoMap={};
 
  //缓存新增待串的案件信息
 var cacheSeriesMap = {};
 
 //页面初始化
 $(document).ready(function(){
   //页面验证初始化
   $("#updateform").validate({
   	   submitHandler: function (form) {
			$(form).ajaxSubmit({//验证新增是否成功
			    url:"${ctx}/seriesCase/myseries/updateSeriesCaseInfo.html",  
				type : "post",
				dataType:"json",
				success : function(data) {
				    if(data=="1"){
				       document.location.href="${ctx}/seriesCase/myseries/index.html";
				    }
				}
			});
		},
		rules:{
			seriesCaseName:{
	           	required: true,
	           	rangelength: [1,60]
	           },
	           seriesDesc:{
	           	required: true,
	           	rangelength: [1,200]
	           }
	       },
	       messages: {
	       	seriesCaseName: {
	            required: "请填写串并案名称",
	            rangelength: "串并案名称必须在1-60个字符之间" 
	        },
	        seriesDesc:{
	           	required: "请填写串并描述",
	           	rangelength: "串并描述必须在1-200个字符之间"
	           }
	       }
	       
	});
	    
   //绑定点击确定串并按钮方法
   $("#seriesCaseButton").click(function(){
        var entrustNoMap ={};
        var ids=$("#seriesCaseDetailtable").jqGrid("getDataIDs");
        var len=ids.length;
        for(var i=0;i<len;i++){
           var rowDatas = $("#seriesCaseDetailtable").jqGrid('getRowData',ids[i]);
           var caseId=rowDatas.caseId;
           var entrustNo=rowDatas.entrustNo;
           entrustNoMap[caseId]=entrustNo;
        }
        var seriesCaseIds="";
        var count=0;
        for (var prop in seriesMap){
		    seriesCaseIds=seriesCaseIds+prop+":"+entrustNoMap[prop]+",";
		    count=count+1;
	    }
	    $("#seriesCaseIds").attr("value",seriesCaseIds);
	    if(count>0){
           $("#updateform").submit();
           
        }else{
          alert("请选择需要串并的案件信息!");
        }
    });
    
    var seriesId=$("#seriesId").val();
    //初始化串并案件详情列表
	$("#seriesCaseDetailtable").jqGrid({
	        url:"${ctx}/seriesCase/myseries/queryCaseInfoDetailInforToEdit.html?seriesId="+seriesId, 
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
	        colNames:['序号','编号','案件id','案(事)件名称','案(事)件类别','串并依据','所属区划','串并状态中文','串并状态','是否新增','委托编号','操作'],
	        colModel:[
	            {name:'no',index:'no', width:'6%'},
	            {name:'caseNo',index:'caseNo',width:'30%',formatter:function(cellvalue, options, rowObject){
	               var entrustNo=rowObject.entrustNo;
	               var caseNo=rowObject.caseNo;
	               var html="";
	               var did=MathRand();
	               idEntstNoMap[did]=entrustNo;
	               
	               if(caseNo.indexOf("showCaseInfo")>0){
	                 html=caseNo
	              }else{
	                 var did=MathRand();
	                 idEntstNoMap[did]=entrustNo;
	                 html="<a class='btn btn-xs btn-link tooltips'  onclick='showCaseInfo("+did+")' id='"+did+"' data-placement='bottom' data-original-title='查看案件详情' data-toggle='modal' href='#'>"+caseNo+"</a>";
	               }
				  return html;					
	            }},
	            {name:'caseId',index:'caseId',hidden:true,width:'1%'},
	            {name:'caseName',index:'caseName', width:'10%'},
	            {name:'caseType',index:'caseType', width:'10%'},
	            {name:'seriesClueList',index:'seriesClueList', width:'10%'},
	            {name:'area',index:'area', width:'10%'},
	            {name:'seriesStatusCn',index:'seriesStatusCn', width:'10%'},
	            {name:'seriesStatus',index:'seriesStatus',hidden:true, width:'10%'},
	            {name:'isAddCache',index:'isAddCache',hidden:true, width:'5%'},
	            {key:true,name:'entrustNo',index:'entrustNo',hidden:true,width:'1%'},
	            {name:'edit',index:'edit',width:'10%',formatter:function(cellvalue, options, rowObject){
	                var isAddCache=rowObject.isAddCache;
	                var entrustNo=rowObject.entrustNo;
	                if(isAddCache==1){
	                   $("#"+entrustNo).addClass("seriesedcase");
	                }
	                var caseId=rowObject.caseId;
	                seriesMap[caseId]=1;
	                var seriesStatus=rowObject.seriesStatus;
	                var html="";
	                var faStr="";
	                var tableid="seriesCaseDetailtable";
	                if(seriesStatus=="1"){
	                   faStr=" <i class='fa fa-trash-o'></i>";
	                }else{
	                   faStr=" <i class='fa fa-plus'></i>";
	                }
	                html=html+"<a class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='删除' data-toggle='modal' href='#del' onclick='clickSeriesCase(\""+caseId+"\",\""+seriesStatus+"\",\""+tableid+"\")' >"+faStr+"</a>&nbsp;&nbsp;";
	                html=html+"<a class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='扩串' data-toggle='modal' onclick='querySeriesCaseInfo("+caseId+")' id='"+caseId+"'  href='#'><i class='fa fa-sitemap'></i></a>";
	                return html;
	            }}
	        ],
            gridComplete: function() {
		      	$("a.tooltips").tooltip();
		   		$("a.popovers").popover();
            },
	        jsonReader : {
	          root:"rows",
	          page: "page",
	          total: "total",
	          records: "records",
	          repeatitems: false,
	          userdata: "userdata"
	         },  
	        rowNum:10000//每页显示记录数
	        
	    });
	   
	   var seriesCaseNum=$("#seriesCaseNum").val();
       $("#cluenum").text(seriesCaseNum);
       
    //初始化待串并案件列表
	$("#seriesCaseWaittable").jqGrid({
        url:"${ctx}/seriesCase/myseries/queryCaseInfoWaitList.html", 
        ajaxGridOptions:{       
          type:'post',        
          async:false,          
          dataType: 'json'   
        },
        datatype:"json", //数据来源，本地数据
        mtype:"POST",//提交方式
        height:"300",//高度，表格高度。
        width:"1000",
        multiselect: true, // 是否显示复选框 
           multiselectWidth:30,
        viewrecords: true,
        colNames:['序号','编号','案件id','案(事)件名称','案(事)件类别','线索信息','所属区划','串并状态中文','串并状态','委托编号','操作'],
           colModel:[
           {name:'no',index:'no', width:'5%'},
           {name:'caseNo',index:'caseNo', width:'25%',formatter:function(cellvalue, options, rowObject){
              var entrustNo=rowObject.entrustNo;
              var caseNo=rowObject.caseNo;
              var html="";
              if(caseNo.indexOf("onclick")>0){
                 html=caseNo
              }else{
                 var did=MathRand();
                 idEntstNoMap[did]=entrustNo;
                 html="<a class='btn btn-xs btn-link tooltips'  onclick='clickEntrustNo("+did+")'  id='"+did+"' data-placement='bottom' data-original-title='查看案件详情' data-toggle='modal' href='#'>"+caseNo+"</a>";
               }
		  return html;					
           }},
           {name:'caseId',index:'caseId',hidden:true,width:'5%'},
           {name:'caseName',index:'caseName', width:'10%'},
           {name:'caseType',index:'caseType', width:'10%',formatter:function(cellvalue, options, rowObject){
			 var rs=cellvalue;
			 $.each(${caseTypeJson},function(i,item){
			  if(cellvalue==item.castType_code){
			      rs= item.castType_name;
			   }
                });
                if(rs==null){
                  rs="";
                }
			return rs;
		}},
           {name:'seriesClueList',index:'seriesClueList', width:'12%'},
           {name:'area',index:'area', width:'10%'},
           {name:'seriesStatus',index:'seriesStatus',hidden:true, width:'10%'},
           {name:'seriesStatusCn',index:'seriesStatusCn', width:'10%'},
           {key:true,name:'entrustNo',index:'entrustNo',hidden:true,width:'5%'},
           {name:'edit',index:'edit', width:'10%',formatter:function(cellvalue, options, rowObject){
               var caseId=rowObject.caseId;
               var entrustNo=rowObject.entrustNo;
               var seriesStatus=rowObject.seriesStatus;
               var tableId="seriesCaseWaittable";
               var rs="";
               if(seriesStatus=='0'){
                  rs="确认串并";
               }else{
                  rs="<font color='red'>取消串并</font>";
               }
			var html="<a href='javascript:void(0)' onclick='clickSeriesCase(\""+caseId+"\",\""+seriesStatus+"\",\""+tableId+"\")'>"+rs+"</a>";
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
	       rowNum:10000//每页显示记录数,
           ,
           gridComplete: function() {
	      	$("a.tooltips").tooltip();
	   		$("a.popovers").popover();
           }
    });
 });
 
  //随机数
  function MathRand() { 
	var num=""; 
	for(var i=0;i<6;i++) { 
	  num+=Math.floor(Math.random()*10); 
	 } 
	return num;
  }
 
 //根据委托编号，查询案件详细信息
 function showCaseInfo(rid){
   $("#dcaseName").empty();
   $("#dcaseNo").empty();
   $("#dcaseOccurDate").empty();
   $("#dbrand").empty();
   $("#dcaseMoneyAmout").empty();
   $("#dcaseType").empty();
   $("#dcaseInfo").empty();
   var entrutsNo=idEntstNoMap[rid];
   if(entrutsNo!=''){
    $.ajax({
      url:"${ctx}/seriesCase/myseries/showCaseinfo.html", //后台处理程序
      type:'post',         //数据发送方式
      dataType:'json',     //接受数据格式
      data:"entrustNo="+entrutsNo,         //要传递的数据
      success:function(data){
          if(data.caseInfo==null){
             alert("找不到相关的案件信息!");
             return ;
          }
          $("#dcaseName").append(data.caseInfo.caseName);
          $("#dcaseNo").append(data.caseInfo.caseNo);
          $("#dcaseOccurDate").append(data.caseInfo.caseOccurDate);
          $("#dbrand").append(data.caseInfo.brand);
          $("#dcaseMoneyAmout").append(data.caseInfo.caseMoneyAmout);
          $("#dcaseType").append(data.caseInfo.caseType);
          $("#dcaseInfo").append(data.caseInfo.caseInfo);
          $("#dvictimInfo").empty();
          var vhtml="";
          vhtml=vhtml+"<thead>";
          vhtml=vhtml+"<tr>";
          vhtml=vhtml+"<th>序号</th>";
          vhtml=vhtml+"<th>姓名</th>";
          vhtml=vhtml+"<th>身份证</th>";
          vhtml=vhtml+"<th>手机</th>";
          vhtml=vhtml+"<th>淘宝账号</th>";
          vhtml=vhtml+"<th>支付宝账号</th>";
          vhtml=vhtml+"<th>所在地址</th>";
          vhtml=vhtml+"</tr>";
          vhtml=vhtml+"</thead>";
          vhtml=vhtml+"<tbody>";
          var vlen=data.victimInfo.length;
          if(vlen>0){
             for(var i=0;i<vlen;i++){
               var bean=data.victimInfo[i];
               vhtml=vhtml+"<tr>";
               vhtml=vhtml+"<td>"+(i+1)+"</td>";
               vhtml=vhtml+"<td>"+bean.victimName+"</td>";
               vhtml=vhtml+"<td>"+bean.victimCardNo+"</td>";
               vhtml=vhtml+"<td>"+bean.victimTelephone+"</td>";
               var victimTaobaoNo="";
                if(bean.victimTaobaoNo!=null){
                 victimTaobaoNo=bean.victimTaobaoNo;
               }
               vhtml=vhtml+"<td>"+victimTaobaoNo+"</td>";
               var victimAlipay="";
               if(bean.victimAlipay!=null){
                 victimAlipay=bean.victimAlipay;
               }
               vhtml=vhtml+"<td>"+victimAlipay+"</td>";
               vhtml=vhtml+"<td>"+bean.victimAddress+"</td>";
               vhtml=vhtml+"</tr>";
             }
          }
          vhtml=vhtml+"<tbody/>";
          $("#dvictimInfo").append(vhtml);
          $("#dsuspectInfo").empty();
          var shtml="";
          shtml=shtml+"<thead>";
          shtml=shtml+"<tr>";
          shtml=shtml+"<th>序号</th>";
          shtml=shtml+"<th>姓名</th>";
          shtml=shtml+"<th>身份证</th>";
          shtml=shtml+"<th>手机</th>";
          shtml=shtml+"<th>淘宝账号</th>";
          shtml=shtml+"<th>支付宝账号</th>";
          shtml=shtml+"<th>QQ</th>";
          shtml=shtml+"<th>电子邮件</th>";
          shtml=shtml+"</tr>";
          shtml=shtml+"</thead>";
          shtml=shtml+"<tbody>";
          var slen=data.suspectInfo.length;
          if(slen>0){
             for(var i=0;i<slen;i++){
               var bean=data.suspectInfo[i];
               shtml=shtml+"<tr>";
               shtml=shtml+"<td>"+(i+1)+"</td>";
               shtml=shtml+"<td>"+bean.suspectName+"</td>";
               shtml=shtml+"<td>"+bean.suspectCardNo+"</td>";
               shtml=shtml+"<td>"+bean.suspectTelephone+"</td>";
               var suspectTaobaoNo="";
                if(bean.suspectTaobaoNo!=null){
                 suspectTaobaoNo=bean.suspectTaobaoNo;
               }
               shtml=shtml+"<td>"+victimTaobaoNo+"</td>";
               var suspectAlipay="";
               if(bean.suspectAlipay!=null){
                 suspectAlipay=bean.suspectAlipay;
               }
               shtml=shtml+"<td>"+suspectAlipay+"</td>";
               shtml=shtml+"<td>"+bean.suspectQq+"</td>";
               shtml=shtml+"<td>"+bean.suspectEmail+"</td>";
               shtml=shtml+"</tr>";
             }
          }
          shtml=shtml+"<tbody/>";
          $("#dsuspectInfo").append(shtml);
          
          $("#"+rid).attr("href","#display");
       } 
      });
   }
 }
 
 //点击串并和未串并触发的动作 单条
 function clickSeriesCase(caseId,seriesStatus,tableId){
     var ids=$("#"+tableId).jqGrid("getDataIDs");
     var len=ids.length;
     var curRowData;
     var rowId;
     for(var i=0;i<len;i++){
        var rowDatas = $("#"+tableId).jqGrid('getRowData', ids[i]);
        if(rowDatas.caseId==caseId){
           curRowData=rowDatas;
           rowId=ids[i];
           break;
        }
     }
     var seriesStatus;
     if(seriesStatus==undefined){
        seriesStatus=curRowData.seriesStatus;
     }
     //如果为已经串并的增加案子，点击删除直接删除掉
     if(tableId=='seriesCaseDetailtable'){//修改已经串并的案件
       //$("#"+tableId).jqGrid("addRowData", "1", rowDatas, "first");  
       var entrustNo=curRowData.entrustNo;
       $("#"+tableId).jqGrid("delRowData",entrustNo); 
       sumTableNo(tableId); 
       getSeriesNum(caseId,seriesStatus);
     }else{//新增串并的案件
       var isExit=seriesMap[caseId];
       if(isExit!=1){
           var eNo=curRowData.entrustNo;
	       if(seriesStatus=="1"){
		       curRowData.seriesStatus="0";
		       curRowData.seriesStatusCn="未串并";
		       $("#"+eNo).removeClass("seriesedcase");
		       delete cacheSeriesMap[caseId];
	       }else{
		       curRowData.seriesStatus="1";
		       curRowData.seriesStatusCn="已串并";
		       $("#"+eNo).addClass("seriesedcase");
		       curRowData.isAddCache=1;
		       cacheSeriesMap[caseId] =curRowData; 
	       }
	       $("#"+tableId).setRowData(rowId,rowDatas);
	       getCacheSeriesNum();
       }else{
           alert("该案件已经串并，不能重复添加!");
       }
     }
    
 }
 
 //删除和增加表格数据后，重新计算编号
 function sumTableNo(tableId){
     var ids=$("#"+tableId).jqGrid("getDataIDs");
     var len=ids.length;
     for(var i=0;i<len;i++){
        var rowDatas = $("#"+tableId).jqGrid('getRowData',ids[i]);
        rowDatas.no=i+1;
        $("#"+tableId).setRowData(rowDatas.entrustNo,rowDatas);
     }
 }
 
 //点击串并和未串并触发的动作 多条 
 function clickSeriesCaseMany(type,tableId){
    var ids = $("#"+tableId).jqGrid('getGridParam','selarrrow');
    var arrids=ids.toString().split(",");
    if(arrids==''){
      alert("请选择要操作的记录!");
      return ;
    }
    var len=arrids.length;
    if(len>0){
      for(var i=0;i<len;i++){
         var rowDatas = $("#"+tableId).jqGrid('getRowData', arrids[i]);
         clickSeriesCase(rowDatas.caseId,type,tableId);
      }
    }
 }
 
 //计算已经串并的案件数量
 function getSeriesNum(caseId,seriesStatus){
     var size=0;
     for (var prop in seriesMap){
       if(seriesStatus=="1"){
         if(caseId==prop){
           delete seriesMap[prop];
         }else{
           size=size+1;
         }
		}else{
		   size=size+1;
		}
	 }
	 $("#cluenum").text(size);
 }
 
 //计算新增待串并的案件数量
 function getCacheSeriesNum(){
   var size=0;
   for (var prop in cacheSeriesMap){
       size=size+1;
	}
	$("#selectnum").text(size);
 }
 
 //根据案件id查询相关的串并案件信息
 function querySeriesCaseInfo(caseId){
      cacheSeriesMap = {};
      getCacheSeriesNum();
      $("#seriesCaseWaittable").jqGrid("setGridParam", {
	    url:"${ctx}/seriesCase/myseries/queryCaseInfoWaitList.html",
	    datatype:"json", //数据来源，本地数据
	    mtype:"POST",//提交方式
	    ajaxGridOptions:{       
          type:'post',        
          async:false,          
          dataType: 'json'   
        },
	    postData:{
	      'caseId':caseId
	    }
    }).trigger("reloadGrid");
    
    $("#"+caseId).attr("href","#expandDialog");
    
 }

//将缓存的数据加入待串并列表中
  function addSeriesCaseToList(){
    var ids=$("#seriesCaseDetailtable").jqGrid("getDataIDs");
    var count=ids.length;
    for (var prop in cacheSeriesMap){
       var obj=cacheSeriesMap[prop];
       var caseId=obj.caseId;
       var entrustNo=obj.entrustNo;
       var editHtml=obj.edit;
       seriesMap[caseId]=1;
       getSeriesNum(caseId,"0");
       count=count+1;
       obj.no=count;
       editHtml=editHtml.replace("seriesCaseWaittable","seriesCaseDetailtable");
       obj.edit=editHtml;
       var caseNo=obj.caseNo;
       caseNo=caseNo.replace("clickEntrustNo","showCaseInfo");
       obj.caseNo=caseNo;
       $("#seriesCaseDetailtable").jqGrid("addRowData",entrustNo, obj, "first");  
       $("#seriesCaseDetailtable").setRowData(entrustNo,obj);
	}
	  sumTableNo("seriesCaseDetailtable"); 
  }
  
  //点击案件编号，查看案件详细信息
  function clickEntrustNo(did){ 
        var entrustNo=idEntstNoMap[did];
       	var iWidth = 1000;
		var iHeight = 500;
		var iTop = 100;
		var iLeft = 150;
		var win = window.open("${ctx}/seriesCase/myseries/queryCaseInfo.html?entrustNo="+entrustNo, "弹出窗口", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	}
</script>