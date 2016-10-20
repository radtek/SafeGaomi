<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript"> 
 //缓存依据串的案件编号
 var seriesMap = {};
 
 //缓存已经选择的案件编号
 var selectMap ={};
 
 //缓存委托编号信息
 var idEntstNoMap={};
 
 //缓存案件id和委托编号对应关系
 var entrustNoMap={};
 
 //计算已经串并的案件数量
 function getSeriesNum(){
     var size=0;
     for (var prop in seriesMap){
		 size=size+1;
	 }
	 $("#cluenum").text(size);
 }
 
 //点击串并和未串并触发的动作 单条
 function clickSeriesCase(caseId,seriesStatus){
     var ids=$("#seriesCaseWaittable").jqGrid("getDataIDs");
     var len=ids.length;
     var curRowData;
     var rowId;
     for(var i=0;i<len;i++){
        var rowDatas = $("#seriesCaseWaittable").jqGrid('getRowData', ids[i]);
        if(rowDatas.caseId==caseId){
           curRowData=rowDatas;
           rowId=ids[i];
           break;
        }
     }
     var caseId=curRowData.caseId;
     var no=curRowData.no;
     if(seriesStatus==undefined){
       var seriesStatus=curRowData.seriesStatus;
     }
     if(seriesStatus=="1"){
       curRowData.seriesStatus="0";
       curRowData.seriesStatusCn="未串并";
       $("#"+no).removeClass("seriesedcase");
       delete seriesMap[caseId];
     }else{
       curRowData.seriesStatus="1";
       curRowData.seriesStatusCn="已串并";
       seriesMap[caseId] = 1;
       $("#"+no).addClass("seriesedcase");
     }
     $("#seriesCaseWaittable").setRowData(rowId,rowDatas);
     getSeriesNum();
	 
 }
 
 //点击串并和未串并触发的动作 多条 
 function clickSeriesCaseMany(type){
    var ids = $("#seriesCaseWaittable").jqGrid('getGridParam','selarrrow');
    var arrids=ids.toString().split(",");
    if(arrids==''){
      alert("请选择要操作的记录!");
      return ;
    }
    var len=arrids.length;
    if(len>0){
      for(var i=0;i<len;i++){
         var rowDatas = $("#seriesCaseWaittable").jqGrid('getRowData', arrids[i]);
         clickSeriesCase(rowDatas.caseId,type);
      }
    }
 }

 $(document).ready(function(){
       //注册表单校验
		$("#seriesForm").validate({
			submitHandler: function (form) {
				$(form).ajaxSubmit({//验证新增是否成功
				    url:"${ctx}/seriesCase/myseries/saveSeriesCaseInfo.html?",  
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
        var seriesCaseIds="";
        var count=0;
        for (var prop in seriesMap){
		    seriesCaseIds=seriesCaseIds+prop+":"+entrustNoMap[prop]+",";
		    count=count+1;
	    }
	    $("#seriesCaseIds").attr("value",seriesCaseIds);
	    if(count>0){
           $("#seriesForm").submit();
        }else{
          alert("请选择需要串并的案件信息!");
        }
     });
     
     //获得案件编号 
     var caseId=$("#caseId").val();
     //初始化待串并案件列表
	 $("#seriesCaseWaittable").jqGrid({
	        url:"${ctx}/seriesCase/myseries/queryCaseInfoWaitList.html?caseId="+caseId, 
	        ajaxGridOptions:{       
	          type:'post',        
	          async:false,          
	          dataType: 'json'   
	        },
	        datatype:"json", //数据来源，本地数据
	        mtype:"POST",//提交方式
	        height:"300",//高度，表格高度。
	        autowidth:true,
	        multiselect: true, // 是否显示复选框 
            multiselectWidth:30,
	        viewrecords: true,
	        colNames:['序号','编号','案件id','案(事)件名称','案(事)件类别','线索信息','串并状态中文','串并状态','委托编号','操作'],
            colModel:[
            {key:true,name:'no',index:'no', width:'5%'},
            {name:'caseNo',index:'caseNo', width:'23%',formatter:function(cellvalue, options, rowObject){
               var entrustNo=rowObject.entrustNo;
               var caseNo=rowObject.caseNo;
               var html="";
               if(caseNo.indexOf("onclick")>0){
                  html=caseNo
               }else{
                  var did=MathRand();
                  idEntstNoMap[did]=entrustNo;
                  html="<a class='btn btn-xs btn-link tooltips'  onclick='showCaseInfo("+did+")' id='"+did+"' data-placement='bottom' data-original-title='查看案件详情' data-toggle='modal' href='#'>"+caseNo+"</a>";
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
            {name:'seriesStatus',index:'seriesStatus',hidden:true, width:'10%'},
            {name:'seriesStatusCn',index:'seriesStatusCn', width:'10%'},
            {name:'entrustNo',index:'entrustNo',hidden:true,width:'5%'},
            {name:'edit',index:'edit', width:'10%',formatter:function(cellvalue, options, rowObject){
                var caseId=rowObject.caseId;
                var entrustNo=rowObject.entrustNo;
                var seriesStatus=rowObject.seriesStatus;
                entrustNoMap[caseId]=entrustNo;
                var rs="";
                if(seriesStatus=='0'){
                   rs="确认串并";
                }else{
                   rs="<font color='red'>取消串并</font>";
                }
				var html="<a href='javascript:void(0)' onclick='clickSeriesCase("+caseId+")'>"+rs+"</a>";
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
            },
            onSelectRow:function(rowid,status){
              var rowDatas = $("#seriesCaseWaittable").jqGrid('getRowData',rowid);
              var caseId=rowDatas.caseId;
              if(status){
                 selectMap[caseId] = 1;
              }else{
                 delete selectMap[caseId];
              }
              getSeriesNum();
            },
            onSelectAll:function(aRowids,status){
              var ids=$("#seriesCaseWaittable").jqGrid("getDataIDs"); 
              var len=ids.length;
              for(var i=0;i<=len;i++){
                   var rowDatas = $("#seriesCaseWaittable").jqGrid('getRowData',ids[i]);
                   var caseId=rowDatas.caseId;
                   if(status){
                      selectMap[caseId] = 1;
		           }else{
		              delete selectMap[caseId];
		           }
                 }
	             getSeriesNum();
            },
            loadComplete:function(xhr){
              var ids=$("#seriesCaseWaittable").jqGrid("getDataIDs"); 
              var len=ids.length;
              for(var i=0;i<=len;i++){
                var rowId=ids[i];
                var rowDatas = $("#seriesCaseWaittable").jqGrid('getRowData',rowId);
                var seriesStatus=rowDatas.seriesStatus;
                var caseId=rowDatas.caseId;
                var isselect=selectMap[caseId];
                if(isselect==1){
                   $("#seriesCaseWaittable").jqGrid('setSelection',rowId,true);
                }
                var isSeries=seriesMap[caseId];
                if(isSeries==1){
                  seriesStatus="1";
                }
                if(seriesStatus=="1"){
                    rowDatas.seriesStatus="1";
                    rowDatas.seriesStatusCn="已串并";
                }else{
                    rowDatas.seriesStatus="0";
                    rowDatas.seriesStatusCn="未串并";
                }

                 $("#seriesCaseWaittable").setRowData(rowId,rowDatas);  
                 getSeriesNum();      
              }
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
</script>