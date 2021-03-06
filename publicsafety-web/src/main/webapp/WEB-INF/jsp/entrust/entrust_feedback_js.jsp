<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script  type="text/javascript" src="${ctx}/js/jquery/jqgrid/i18n/grid.locale-cn.js"></script>
<script  type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
 
   //type=='1'共享案件信息   ,2 共享查询信息
   //绑定是否共享查询保存事件
    var entrustNo=$("#entrustNo").val();
	$('input[name="isShareQueryresult"]').on('switchChange.bootstrapSwitch', function(event, state) {
	    var isShareQuery="0";
	    if(state==true){
	       isShareQuery="1";
	    }
	    submitShare("2",entrustNo,isShareQuery);
	});
	
	//绑定是否共享案件信息事件
	$('input[name="isShareCaseinfo"]').on('switchChange.bootstrapSwitch', function(event, state) {
	    var isShareCaseinfo="0";
	    if(state==true){
	       isShareCaseinfo="1";
	    }
	    submitShare("1",entrustNo,isShareCaseinfo);
	});
 
      //绑定不提交反馈评价返回按钮事件
	 $("#litab1").click(function(){
	     
     });
     
     //绑定委托评价保存信息
     $("#savepingjia").click(function(){
         $("#savepingjia").attr("disabled",true); 
	     var FKJGSFMY=$("input[name='FKJGSFMY']:checked").val();
	     var FKJGSFYBZ=$("input[name='FKJGSFYBZ']:checked").val();
	     var FKSJSFMY=$("input[name='FKSJSFMY']:checked").val();
	     var memocontent=$("#memocontent").val();
	     var entrustNo=$("#entrustNo").val();
	     if(isNullData("memocontent")){
	     $.ajax({
		       url:'${ctx}/entrust/feedback/updateFgpjInfor.html', //后台处理程序
		       type:'post',         //数据发送方式
		       dataType:'json',     //接受数据格式
		       data:{
		            'FKJGSFMY':FKJGSFMY,
		            'FKJGSFYBZ':FKJGSFYBZ,
		            'FKSJSFMY':FKSJSFMY,
		            'entrustNo':entrustNo,
		            'memocontent':memocontent
		            },         //要传递的数据
		       success:function(data){
		            $("#basic").click();
		            document.location.href="${ctx}/entrust/myApply/index.html";
		      } 
	     });
	     }else{
	       alert("请填写意见和建议!");
	       $("#savepingjia").removeAttr("disabled"); 
	      
	     }
     });
 
    var entrustNo=$("#entrustNo").val();
    //登录信息
    $("#loginHistoryTable").jqGrid({
        url:"${ctx}/entrust/feedback/queryAliLoginHistory.html?entrustNo="+entrustNo+"&time="+new Date(),
        ajaxGridOptions:{       
         type:'post',        
         async:false,          
         dataType: 'json'   
        },
        datatype:"json", //数据来源，本地数据
        mtype:"POST",//提交方式
        height:"300",//高度，表格高度。
        width:"1000",
        viewrecords: true,
        colNames:['序号','登录名','用户名字','用户IP','时间'],
        colModel:[
            {name:'no',index:'no', width:'5%'},
            {name:'loginId',index:'loginId', width:'10%'},
            {name:'name',index:'name', width:'5%'},
            {name:'clientIp',index:'clientIp', width:'10%'},
            {name:'actionDt',index:'actionDt', width:'10%'}
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
        pager:$("#logingridPager")
    });
    
   //注册信息
    $("#registerTable").jqGrid({
        url:"${ctx}/entrust/feedback/queryAliRegisterInfo.html?entrustNo="+entrustNo+"&time="+new Date(),
        ajaxGridOptions:{       
         type:'post',        
         async:false,          
         dataType: 'json'   
        },
        datatype:"json", //数据来源，本地数据
        mtype:"POST",//提交方式
        height:"300",//高度，表格高度。
        width:"1000",//自动宽
        viewrecords: true,
        colNames:['序号','真实姓名','会员名','绑定手机','支付宝id','注册时间','绑定邮箱','最后登录时间'],
        colModel:[
            {name:'no',index:'no', width:'5%'},
            {name:'realName',index:'realName', width:'8%'},
            {name:'nick',index:'nick', width:'6%'},
            {name:'mobile',index:'mobile', width:'8%'},
            {name:'alipayId',index:'alipayId', width:'16%'},
            {name:'registerTime',index:'registerTime', width:'10%'},
            {name:'bindEmail',index:'bindEmail', width:'10%'},
            {name:'lastLogin',index:'lastLogin', width:'10%'}
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
        pager:$("#registergridPager")
    });
    
    //交易记录
    $("#transactionRecordsTable").jqGrid({
        url:"${ctx}/entrust/feedback/queryAliTransactionRecords.html?entrustNo="+entrustNo+"&time="+new Date(),
        ajaxGridOptions:{       
         type:'post',        
         async:false,          
         dataType: 'json'   
        },
        datatype:"json", //数据来源，本地数据
        mtype:"POST",//提交方式
        height:"300",//高度，表格高度。
        width:"1000",//自动宽
        viewrecords: true,
        colNames:['序号','交易号','外部交易号 ','交易状态','合作ID ','买家卡ID ','买家信息','卖家卡ID','卖家信息','交易金额','收款时间 ','最后修改时间','创建时间','交易类型','来源地','商品名称','收货人地址'],
        colModel:[
            {name:'no',index:'no', width:'5%'},
            {name:'tradeNo',index:'tradeNo', width:'8%'},
            {name:'outTradeNo',index:'outTradeNo', width:'10%'},
            {name:'tradeStatusDesc',index:'gender', width:'8%'},
            {name:'partnerId',index:'partnerId', width:'8%'},
            {name:'tradeBuyerId',index:'tradeBuyerId', width:'8%'},
            {name:'buyerInfo',index:'buyerInfo', width:'8%'},
            {name:'tradeSellerId',index:'tradeSellerId', width:'8%'},
            {name:'sellerInfo',index:'sellerInfo', width:'8%'},
            {name:'tradeTotalAmt',index:'tradeTotalAmt', width:'8%'},
            {name:'tradeReceivePayDate',index:'tradeReceivePayDate', width:'8%'},
            {name:'gmtModified',index:'gmtModified', width:'8%'},
            {name:'tradeCreateDate',index:'tradeCreateDate', width:'8%'},
            {name:'tradeTypeDesc',index:'tradeTypeDesc', width:'8%'},
            {name:'tradeFromDesc',index:'tradeFromDesc', width:'8%'},
            {name:'goodsTitle',index:'goodsTitle', width:'8%'},
            {name:'receiveAddress',index:'receiveAddress', width:'8%'}
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
        pager:$("#transactionRecordsgridPager")
    });
    
    
     //账户明细
    $("#accountDetailTable").jqGrid({
        url:"${ctx}/entrust/feedback/queryAliAccountDetail.html?entrustNo="+entrustNo+"&time="+new Date(),
        ajaxGridOptions:{       
         type:'post',        
         async:false,          
         dataType: 'json'   
        },            
        datatype:"json", //数据来源，本地数据
        mtype:"POST",//提交方式
        height:"300",//高度，表格高度。
        width:"1000",//自动宽
        viewrecords: true,
        colNames:['序号','流水号','交易账户','对方账户','交易发生日期','交易发生时间','收益金额（+）','支出金额（-）','余额','业务类型','交易发生地 ','银行名称','备注'],
        colModel:[
            {name:'no',index:'no', width:'5%'},
            {name:'transOutOrderNo',index:'transOutOrderNo', width:'7%'},
            {name:'transAccount',index:'transAccount', width:'10%'},
            {name:'otherAccount',index:'otherAccount', width:'10%'},
            {name:'transDate',index:'transDate', width:'12%'},
            {name:'transDt',index:'transDt', width:'12%'},
            {name:'transAmountD',index:'transAmountD', width:'10%'},
            {name:'transAmountC',index:'transAmountC', width:'10%'},
            {name:'balance',index:'balance', width:'6%'},
            {name:'subTransCodeDesc',index:'subTransCodeDesc', width:'8%'},
            {name:'tradeReceivePayDate',index:'tradeReceivePayDate', width:'8%'},
            {name:'tradeArea',index:'tradeArea', width:'8%'},
            {name:'showName',index:'showName', width:'6%'}
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
        pager:$("#accountDetailgridPager")
    });
    
    
     //转账明细
    $("#transferDetailTable").jqGrid({
        url:"${ctx}/entrust/feedback/queryAliTransferDetail.html?entrustNo="+entrustNo+"&time="+new Date(),
        ajaxGridOptions:{       
         type:'post',        
         async:false,          
         dataType: 'json'   
        },  
        datatype:"json", //数据来源，本地数据
        mtype:"POST",//提交方式
        height:"300",//高度，表格高度。
        width:"1000",//自动宽
        viewrecords: true,
        colNames:['序号','流水号','付款方','收款方','收款机构','到账时间 ','转账金额','业务类型','交易发生地','提现流水号'],
        colModel:[
            {name:'no',index:'no', width:'5%'},
            {name:'transferNo',index:'transferNo', width:'10%'},
            {name:'senderUserid',index:'senderUserid', width:'10%'},
            {name:'receiverUserid',index:'receiverUserid', width:'10%'},
            {name:'receiverInst',index:'receiverInst', width:'10%'},
            {name:'transSuccDate',index:'transSuccDate', width:'10%'},
            {name:'transAmount',index:'transAmount', width:'10%'},
            {name:'productName',index:'productName', width:'10%'},
            {name:'tradeArea',index:'tradeArea', width:'10%'},
            {name:'withdrawNo',index:'withdrawNo', width:'10%'}
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
        pager:$("#transferDetailgridPager")
    });
});

//导出反馈信息到excel
function exportExcel(){
   var entrustNo=$("#entrustNo").val();
   window.open("${ctx}/entrust/file/ali_result_all.html?entrustNo="+entrustNo);
}


 //返回我的委托页面
 function backToMyEntrust(){
  window.location.href="${ctx}/entrust/myApply/index.html";
 }

 //保存反馈结果评价信息
 function saveFeedbooK(){
   var entrustNo=$("#entrustNo").val();
     $.ajax({
       url:'${ctx}/entrust/check/viewCheckentrust.html', //后台处理程序
       type:'post',         //数据发送方式
       dataType:'json',     //接受数据格式
       data:{'entrustNo':entrustNo,'checkType':"finish"},//要传递的数据
       success:function(data){
          $("#basic").click();
          alert("数据保存成功!");
        } 
    });
 }

 //再次委托
 function linkToModel(){
     var entrustSer=$("#entrustSer").val();
     window.location.href="${ctx}/entrust/apply/index.html?entrustSer="+entrustSer+"&type=3";
  }

  // 提交是否共享案件信息、是否查询条件
  function submitShare(type,entrustNo,isShareCaseinfo){
      $.ajax({
	       url:'${ctx}/entrust/feedback/updateIsShareInfo.html', //后台处理程序
	       type:'post',//数据发送方式
	       dataType:'json', //接受数据格式
	       data:{'entrustNo':entrustNo,'isShareCaseinfo':isShareCaseinfo,'type':type},//要传递的数据
	   });
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
    
    //ID下载文件
  function downFile(attachId){
     window.location.href="${ctx}/entrust/file/official_doc.html?attachId="+attachId;
  }
</script>
