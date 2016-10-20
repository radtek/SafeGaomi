<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
  //点击串并按钮执行的操作
 function clickSeriesButton(caseId,seriesTypes){
      document.location.href="${ctx}/seriesCase/myseries/showQueryCaseInfoSelect.html?caseId="+caseId+"&seriesTypes="+seriesTypes;
 }
 //缓存委托编号信息
 var idEntstNoMap={};
 
 $(document).ready(function(){
   
     //处理案件类型
	var caseTypeJson=${caseTypeJson};
    creatMuitSelect(caseTypeJson,"querycastType","scaseTypeTree",false,"selected");
    //初始化我的待串并案件列表
    $("#seriesCaseCaseInfotable").jqGrid({
        url:"${ctx}/seriesCase/myseries/queryCaseInfoList.html", 
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
        colNames:['序号','编号','案件id','串并类型','案(事)件名称','案(事)件类别','线索信息','串并状态中文','串并状态','委托编号','操作'],
        colModel:[
            {name:'no',index:'no', width:'5%'},
            {name:'caseNo',index:'caseNo', width:'20%',formatter:function(cellvalue, options, rowObject){
               var entrustNo=rowObject.entrustNo;
               var caseNo=rowObject.caseNo;
               var html="";
               var did=MathRand();
               idEntstNoMap[did]=entrustNo;
               html="<a class='btn btn-xs btn-link tooltips'  onclick='showCaseInfo("+did+")' id='"+did+"' data-placement='bottom' data-original-title='查看案件详情' data-toggle='modal' href='#'>"+caseNo+"</a>";
			  return html;					
            }},
            {name:'caseId',index:'caseId',hidden:true,width:'1%'},
            {name:'seriesTypes',index:'seriesTypes',hidden:true,width:'1%'},
            {name:'caseName',index:'caseName', width:'10%'},
            {name:'caseType',index:'caseType', width:'8%',formatter:function(cellvalue, options, rowObject){
				 var rs=cellvalue;
				 $.each(${caseTypeJson},function(i,item){
				  if(cellvalue==item.castType_code){
				      rs= item.castType_name;
				   }
                 });
                 if(rs==null){
                   rs="";
                 }
				return rs;      //<a href="javascript:void(0)" id="seriesbutton" >串并</a>
			}},
            {name:'seriesClueList',index:'seriesClueList', width:'10%'},
            {name:'seriesStatus',index:'seriesStatus',hidden:true, width:'1%'},
            {name:'seriesStatusCn',index:'seriesStatusCn', width:'8%'},
            {name:'entrustNo',index:'entrustNo',hidden:true,width:'1%'},
            {name:'edit',index:'edit', width:'8%',formatter:function(cellvalue, options, rowObject){
                var caseId=rowObject.caseId;
                var seriesTypes=rowObject.seriesTypes;
                var seriesStatus=rowObject.seriesStatus;
                var html="";
                if(seriesStatus=='0'){
                   html=html+"<a href='javascript:void(0)' id='seriesbutton' onclick='clickSeriesButton("+caseId+",\""+seriesTypes+"\")' >开始串并</a>";
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
        pager:$("#seriesCaseCaseInfogridPager")
    });
    //初始化省、地市联动选择框
    $("#select2_sample1").html("<option value=''>--请选择--</option>");
    $("#select2_sample2").html("<option value=''>--请选择--</option>");
    var provinceStr=${provinceJson} ;
    var cityStr    =${cityJson};
    $.each(provinceStr,function(i,item){
        $("#select2_sample1").append('<option  value="'+item.province_code+'">'+item.province_name+'</option>');
    });
	//初始化省、地市联动onchange事件
    $('#select2_sample1').change(function(){
    $('#select2_sample2').empty();
    $('#select2-chosen-2').text('--请选择--');
	    $.each(cityStr,function(i,item){
	        if($('#select2_sample1').val()==item.province_code){
	            $('#select2_sample2').append('<option value="'+item.city_code+'">'+item.city_name+'</option>');
	        }
	    });
    });
   //初始化省、地市联动相关结束
   
   //绑定点击查询按钮事件
    $("#queryCaseinfobutton").click(function(){
       //获得查询参数
       var caseType=$("#querycastType").val();
       if(caseType==null){
	      caseType="";
	   }else{
	      caseType=caseType.toString();
	   }
       var province=$("#select2_sample1").val();
       var city    =$("#select2_sample2").val();
       var caseName=$("#caseName").val();
       var dateBegin=$("#dateBegin").val();
       var dateEnd  =$("#dateEnd").val();
       //重新加载待串并案件表格数据
       $("#seriesCaseCaseInfotable").jqGrid("setGridParam", {
			    url:"${ctx}/seriesCase/myseries/queryCaseInfoList.html",
			    datatype:"json", //数据来源，本地数据
			    mtype:"POST",//提交方式
			    postData:{
				    'caseType':caseType,
				    'province':province,
				    'city':city,
				    'caseName':caseName,
				    'dateBegin':dateBegin,
				    'dateEnd':dateEnd
			    }
	   }).trigger("reloadGrid"); 
    });
    
    //绑定点击重置按钮事件
    $("#resetQueryCaseinfobutton").click(function(){
        $("#submit_form")[0].reset();
        $("#select2_sample1").attr("value",'');
        $("#select2_sample2").attr("value",'');
        $('#select2-chosen-1').text('');
        $('#select2-chosen-2').text('');
        $("#scaseTypeTreeinputview").attr("value","");
        $("#querycastType").attr("value","");
    
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