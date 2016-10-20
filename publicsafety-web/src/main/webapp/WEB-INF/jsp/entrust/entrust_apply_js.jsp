<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    //动态长度验证
    var namemap = {};
    var maxmap = {};
     //固定检验元素
    var nativenamemap={};
    var nativemap={};
    //动态元素手机号验证
    var mobileMap={};
    //动态联系方式验证
    var telMap={};
    //动态身份证验证
    var cardMap={};
    //动态QQ验证
    var qqMap={};
    //动态元素邮箱验证
    var emailMap={};
    //动态url验证
    var urlMap={};
    //金额验证
    var moneyMap={};
    moneyMap["caseMoneyAmout"] = "案件信息中:案件金额";
   
    //办案单位key和name映射关系
    var unitNameMap={};
    
    //保存校验错误信息
    var savemap = {};
    //提交校验错误信息  
    var submitmap = {}; 
    
    //固定元素长度验证
    nativemap["entrustInfopersonName"] = 50;
    nativenamemap["entrustInfopersonName"] = "委托信息中:办案人";
    
    nativemap["entrustInfotitle"] = 50;
    nativenamemap["entrustInfotitle"] = "委托信息中:办案人职务";
    
    nativemap["caseInfocaseName"] = 55;
    nativenamemap["caseInfocaseName"] = "案件信息中:案件名称";
    
    nativemap["caseInfocaseNo"] = 50;
    nativenamemap["caseInfocaseNo"] = "案件信息中:案件编号";
    
    nativemap["caseInfobrand"] = 50;
    nativenamemap["caseInfobrand"] = "案件信息中:涉案品牌";
    
    telMap["entrustInfotelephone"] = "案件信息中:办案人联系电话";

   //页面初始化
	$(function(){
	     //操作类型
	     var actiontype=$("#actiontype").val();
	     //处理简要案情默认提示
	     $("#caseInfocaseInfo").focus(function(){
           if(actiontype=="1" || actiontype=="2"){
            var caseInfo=$("#caseInfocaseInfo").val();
            caseInfo=caseInfo.replace(/(^\s*)|(\s*$)/g, "");
            var caseInfoDefualt= $("#caseInfocaseInfo").attr("placeholder");
            if(caseInfo=='' || caseInfo==null){
             $("#caseInfocaseInfo").attr("placeholder","");
            }
          }
        });
        
         $("#caseInfocaseInfo").blur(function(){
         if(actiontype=="1" || actiontype=="2"){
            var caseInfo=$("#caseInfocaseInfo").val();
            caseInfo=caseInfo.replace(/(^\s*)|(\s*$)/g, "");
            if(caseInfo==null || caseInfo==''){
              $("#caseInfocaseInfo").attr("placeholder","请填写报案时间、报案人、案件发生时间、案件相关方、案件基本情况");
            }
         }
      });
        
      //处理填写进度条
	  $("#litab1").click(function(){
			$("#progress-bar").css("width","33%");
           	$("#nextTabNo").attr("value","tab2");
           	$("#nextbutton").show();
           	$("#alert-warning").hide();
      	});
      
      $("#litab2").click(function(){
	     $("#progress-bar").css("width","66%");
         $("#nextTabNo").attr("value","tab3");
         $("#nextbutton").show();
         $("#alert-warning").hide();
      });
      
      $("#litab3").click(function(){
	     $("#progress-bar").css("width","100%");
         $("#nextTabNo").attr("value","tab1");
         $("#nextbutton").hide();
         $("#alert-warning").hide();
      });
      
      
      
	//绑定是否派人来取开关开启事件
	 $('input[name="entrustInfo.istake"]').on('switchChange.bootstrapSwitch', function(event, state) {
	     if(state==false){
	         $("#visitorinfo").hide();
	         removeItemStatus("entrustInfovisitorName","maxlength");
	         removeItemStatus("visitorUnit","maxlength");
	         removeItemStatus("visitorTitle","maxlength");
	         removeItemStatus("visitorTelephone","tel");
        }else{
             maxmap["entrustInfovisitorName"] = 50;
		     namemap["entrustInfovisitorName"] = "委托信息中:来访人姓名";
		     maxmap["visitorUnit"] = 50;
		     namemap["visitorUnit"] = "委托信息中:来访人单位";
		     maxmap["visitorTitle"] = 50;
		     namemap["visitorTitle"] = "委托信息中:来访人职务";
		     telMap["visitorTelephone"] = "委托信息中:办案人联系电话";
             $("#visitorinfo").show();
        }
	 });
	
	//获得受害人，嫌疑人，协查列表数量
	var victimInfosize=$("#victimInfonum").val();
	var suspectInfosize=$("#suspectInfonum").val();
	var investigateInfosize=$("#investigateInfonum").val();
	//初始化时，动态增加验证
	if(victimInfosize!=''){
	    for(var i=0;i<parseInt(victimInfosize);i++){
	       addItemValidate("victimInfo",i);
	    }
	}
	if(suspectInfosize!=''){
	    for(var i=0;i<parseInt(suspectInfosize);i++){
	       addItemValidate("suspectInfo",i);
	    }
	}
	if(investigateInfosize!=''){
	    for(var i=0;i<parseInt(investigateInfosize);i++){
	       addItemValidate("investigateInfo",i);
	    }
	}
	//初始化办案单位
	 unit_init (${dicJsonArr});
     
     //初始化时间控件
	 $(".form_datetime").datetimepicker({
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
     });
    
	
	    //初始化是否来人调取
	    var istakehidden=$("#istakehidden").val();
		if(istakehidden=='0' || istakehidden==''){
		   $("#visitorinfo").hide();
		}
        //初始化省、地市数据  -- start
		$("#select2_sample1").html('<option value="">--请选择--</option>');
		$("#select2_sample2").html('<option value="">--请选择--</option>');
		var provinceCodehiden=$("#provinceCodehiden").val();
		var cityCodehiden=$("#cityCodehiden").val();
        $.each(${provinceJson},function(i,item){
            if(provinceCodehiden==item.province_code){
              $('#select2-chosen-1').text(item.province_name);
              $('#select2_sample1').append('<option selected="selected" value="'+item.province_code+'">'+item.province_name+'</option>');
            }else{
              $('#select2_sample1').append('<option  value="'+item.province_code+'">'+item.province_name+'</option>');
            }
        });
        if(actiontype=='2' || actiontype=='3' ){
            $.each(${cityJson},function(i,item){
                if(provinceCodehiden==item.province_code){
                    if(cityCodehiden==item.city_code){
                        $('#select2-chosen-2').text(item.city_name);
                        $('#select2_sample2').append('<option selected="selected" value="'+item.city_code+'">'+item.city_name+'</option>');
                    }else{
                        $('#select2_sample2').append('<option value="'+item.city_code+'">'+item.city_name+'</option>');
                    }
                }
            })
        }
        //省市联动
        $("#select2_sample1").change(function(){
            $("#select2_sample2").empty();
            $("#select2-chosen-2").text("--请选择--");
            $("#treeviewinputview").attr("value","");
            $("#entrustInfounithidden").attr("value","");
            var unitData=[];
            creatMuitSelect(unitData,"entrustInfounithidden","treeview");
            $.each(${cityJson},function(i,item){
                if($("#select2_sample1").val()==item.province_code){
                    $("#select2_sample2").append('<option value="'+item.city_code+'">'+item.city_name+'</option>');
                }
            })
            $("#select2_sample2").attr("value","");
        });
        
    //办案单位所在地与办案单位名称联动
    $("#select2_sample2").change(function(){
         $("#entrustInfounithidden").attr("value","");
         var unitData =[];
         var city=$("#select2_sample2").val()
         unitData=getunitselect(city);
         creatMuitSelect(unitData,"entrustInfounithidden","treeview");
    });
        
    //处理办案单位
	if(actiontype=='2' || actiontype=='3'){
	       var city=$("#cityCodehiden").val();
	       var unitCode=$("#entrustInfounithidden").val();
	       if(city!=null){
	        var pro=unitNameMap[unitCode];
            var lunitName=pro.dictName;
	        var unitData=[];
	        unitData=getunitselect(city);
            creatMuitSelect(unitData,"entrustInfounithidden","treeview");
            $("#treeviewinputview").attr("value",lunitName);
	       }
	    }
	
	 //处理案件类型
	 var caseTypeJson=${caseTypeJson};
     creatMuitSelect(caseTypeJson,"caseTypeDid","addCaseTypetree",false,"selected1");
    });
   
  //根据城市代码获取办案单位名称
  function getunitselect(city){
          $("#treeviewinputview").attr("value","");
          var unitData=[];
          creatMuitSelect(unitData,"entrustInfounithidden","treeview");
          var unitStr="";
          if(city!=''){
               var html_unit="";
               //市
               var cityCode=city+"000000";
               var cityName=unitNameMap[cityCode].dictName;
               unitStr=unitStr+'[{text:"';
               unitStr=unitStr+cityName+'",';
               unitStr=unitStr+'id:"'+cityCode+'"';
               //区县
               var count=0;
               for(var prop in  unitNameMap){
                  var item=unitNameMap[prop];
			      if(item.dicParentKey==cityCode){
			       var exname=unitNameMap[prop].dictName;
			        if(count==0){
			          unitStr=unitStr+',nodes:[';
			         }else{
			          unitStr=unitStr+',';
			         }
			          unitStr=unitStr+'{text:"'+exname+'",';
			          unitStr=unitStr+'id:"'+prop+'"}';
			          count=count+1;
			      } 
			   }
			   if(count>0){
			    unitStr=unitStr+']';
			   }
               unitStr=unitStr+'}] ';
               var unitJsonObj = eval('(' + unitStr + ')');
               return  unitJsonObj;
            }
        }
        
   
   //点击委托下一页按钮出发的事件
   function clickNextButton(){
     var tabValue=$("#nextTabNo").val();
     var tab=$("[href='#"+tabValue+"']");
     tab.get(0).click();
     $("#alert-warning").hide();
   }
   
     //动态增加验证，当增加受害人，嫌疑人时
   function addItemValidate(type,num){
        if(type=="victimInfo"){
           var victimNameId=type+num+"victimName";
            maxmap[victimNameId] =30;
            namemap[victimNameId] = "案件信息中:受害人姓名";
            
            var victimTaobaoNoId=type+num+"victimTaobaoNo";
            maxmap[victimTaobaoNoId] =30;
            namemap[victimTaobaoNoId] = "案件信息中:受害人淘宝账号";
            
            var victimAlipayId=type+num+"victimAlipay";
            maxmap[victimAlipayId] =30;
            namemap[victimAlipayId] = "案件信息中:受害人支付宝账号";
            
            var victimAddressId=type+num+"victimAddress";
            maxmap[victimAddressId] =30;
            namemap[victimAddressId] = "案件信息中:受害人所在地址";
            
            var telephoneid=type+num+"victimTelephone";
            mobileMap[telephoneid]="案件信息中:受害人手机";
            
            var victimCardNoid=type+num+"victimCardNo";
            cardMap[victimCardNoid] ="案件信息中:受害人身份证";
        }else if(type=="suspectInfo"){
            var suspectNameId=type+num+"suspectName";
            maxmap[suspectNameId] =30;
            namemap[suspectNameId] = "案件信息中:嫌疑人姓名";
            
            var suspectTaobaoNoId=type+num+"suspectTaobaoNo";
            maxmap[suspectTaobaoNoId] =60;
            namemap[suspectTaobaoNoId] = "案件信息中:嫌疑人淘宝账号";
            
            var suspectAlipayId=type+num+"suspectAlipay";
            maxmap[suspectAlipayId] =60;
            namemap[suspectAlipayId] = "案件信息中:嫌疑人支付宝账号";
            
            var suspectTelephoneid=type+num+"suspectTelephone";
            mobileMap[suspectTelephoneid]="案件信息中:嫌疑人手机";
            
            var suspectCardNoid=type+num+"suspectCardNo";
            cardMap[suspectCardNoid] ="案件信息中:嫌疑人身份证";
            
            var suspectQqid="suspectInfo"+num+"suspectQq";
            qqMap[suspectQqid] = "案件信息中:嫌疑人qq";
            
            var suspectEmailid="suspectInfo"+num+"suspectEmail"; 
            emailMap[suspectEmailid] = "案件信息中:嫌疑人email";
        }else if(type=="investigateInfo"){
            var investDescId=type+num+"investDesc";
            maxmap[investDescId] =200;
            namemap[investDescId] = "需侦查信息中:说明";
        }
     }
   
     //动态增加受所害人信息
     function addItem(type){
        var no=$("#"+type+"no").val();
        var num=$("#"+type+"num").val();
        if(no==''){
           no=0;
        }
        if(num==''){
           num=0;
        }
        var itemid=type+no;
        var idno=type+num;
        var index=parseInt(no)+1;
        var html="";
        html=html+"<tr id='"+itemid+"'>";
	    html=html+" <td  width='50px'> <span>"+index+"</span></td>";
        if(type=="victimInfo"){
            //动态增加增加元素的验证
            addItemValidate(type,num);
	        html=html+" <td> <input type='text'  value='' class='form-control'  id='victimInfo"+num+"victimName' onblur='checkFormat(\"victimInfo"+num+"victimName\",\"maxlength\")'  name='victimInfo["+num+"].victimName'><span id='victimInfo"+num+"victimName_error' class='messageerror' ></span> </td>";
	        html=html+" <td> <input type='text'  value='' class='form-control tooltips'  id='victimInfo"+num+"victimCardNo' maxlength='20' onblur='checkFormat(\"victimInfo"+num+"victimCardNo\",\"card\")'  name='victimInfo["+num+"].victimCardNo' data-placement='top' data-original-title='javascript:this.value();'/><span id='victimInfo"+num+"victimCardNo_error' class='messageerror' ></span> </td>";
	        html=html+" <td> <input type='text'  value='' class='form-control'  id='victimInfo"+num+"victimTelephone'  maxlength='30' onblur='checkFormat(\"victimInfo"+num+"victimTelephone\",\"mobile\")'   name='victimInfo["+(num)+"].victimTelephone'> <span id='victimInfo"+num+"victimTelephone_error' class='messageerror'></span></td>";
	        html=html+" <td> <input type='text'  value='' class='form-control' onblur='checkFormat(\"victimInfo"+num+"victimTaobaoNo\",\"maxlength\")'   id='victimInfo"+num+"victimTaobaoNo' maxlength='50' name='victimInfo["+num+"].victimTaobaoNo'><span id='victimInfo"+num+"victimTaobaoNo_error' class='messageerror' ></span> </td>";
	        html=html+" <td> <input type='text'  value='' class='form-control' onblur='checkFormat(\"victimInfo"+num+"victimAlipay\",\"maxlength\")'  id='victimInfo"+num+"victimAlipay' maxlength='50' name='victimInfo["+num+"].victimAlipay'><span id='victimInfo"+num+"victimAlipay_error' class='messageerror' ></span> </td>";
	        html=html+" <td> <input type='text'  value='' class='form-control'  onblur='checkFormat(\"victimInfo"+num+"victimAddress\",\"maxlength\")'  id='victimInfo"+num+"victimAddress' maxlength='200' name='victimInfo["+num+"].victimAddress'><span id='victimInfo"+num+"victimAddress_error' class='messageerror' ></span>  </td>";
	        html=html+" <td> <a href='javascript:void(0)' onclick='deleteItem(\"victimInfo\",\""+itemid+"\",\""+idno+"\")' class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='删除'><i class='fa fa-trash-o'></i></a></td>";
        }else if(type=="suspectInfo"){
             //动态增加增加元素的验证
            addItemValidate(type,num);
	        html=html+" <td> <input type='text'  value='' class='form-control'   onblur='checkFormat(\"suspectInfo"+num+"suspectName\",\"maxlength\")'  id='suspectInfo"+num+"suspectName' name='suspectInfo["+num+"].suspectName'> <span id='suspectInfo"+num+"suspectName_error' class='messageerror' ></span></td>";
	        html=html+" <td> <input type='text'  value='' class='form-control'   id='suspectInfo"+num+"suspectCardNo' onblur='checkFormat(\"suspectInfo"+num+"suspectCardNo\",\"card\")'  name='suspectInfo["+num+"].suspectCardNo'><span id='suspectInfo"+num+"suspectCardNo_error' class='messageerror'></span> </td>";
	        html=html+" <td> <input type='text'  value='' class='form-control'   id='suspectInfo"+num+"suspectTelephone' onblur='checkFormat(\"suspectInfo"+num+"suspectTelephone\",\"mobile\")'   name='suspectInfo["+num+"].suspectTelephone'><span id='suspectInfo"+num+"suspectTelephone_error' class='messageerror'></span> </td>";
	        html=html+" <td> <input type='text'  value='' class='form-control'   onblur='checkFormat(\"suspectInfo"+num+"suspectTaobaoNo\",\"maxlength\")' id='suspectInfo"+num+"suspectTaobaoNo' name='suspectInfo["+num+"].suspectTaobaoNo'><span id='suspectInfo"+num+"suspectTaobaoNo_error' class='messageerror'></span> </td>";
	        html=html+" <td> <input type='text'  value='' class='form-control'   id='suspectInfo"+num+"suspectAlipay' name='suspectInfo["+num+"].suspectAlipay' onblur='checkFormat(\"suspectInfo"+num+"suspectAlipay\",\"maxlength\")'><span id='suspectInfo"+num+"suspectAlipay_error' class='messageerror'></span> </td>";
	        html=html+" <td> <input type='text'  value='' class='form-control'   id='suspectInfo"+num+"suspectQq' onblur='checkFormat(\"suspectInfo"+num+"suspectQq\",\"qq\")'  id='suspectInfo["+num+"]suspectQq' name='suspectInfo["+(num)+"].suspectQq'><span id='suspectInfo"+num+"suspectQq_error' class='messageerror'></span> </td>";
	        html=html+" <td> <input type='text'  value='' class='form-control'   id='suspectInfo"+num+"suspectEmail' onblur='checkFormat(\"suspectInfo"+num+"suspectEmail\",\"email\")'  name='suspectInfo["+num+"].suspectEmail'> <span id='suspectInfo"+num+"suspectEmail_error' class='messageerror'></span> </td>";
	        html=html+" <td> <a href='javascript:void(0)' onclick='deleteItem(\"suspectInfo\",\""+itemid+"\",\""+idno+"\")' class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='删除'><i class='fa fa-trash-o'></i></a></td>";
        }else if(type=="investigateInfo"){
            html=html+" <td>";
            html=html+"  <select  onchange='changeXcxxtype(\"investigateInfo\",\""+num+"\")'  class='form-control' id='investigateInfo"+num+"investType' name='investigateInfo["+num+"].investType'> ";
	         $.each(${xcxxJson},function(i,item){
                 html=html+'<option value="'+item.code+'">'+item.name+'</option>';
             });
	        html=html+"</select></td>";
	        html=html+" <td> <input type='text'  value='' onblur='checkxcxxList(\"investigateInfo\",\""+num+"\")'  class='form-control' id='investigateInfo"+num+"investContent' name='investigateInfo["+num+"].investContent'><span id='investigateInfo"+num+"investContent_error' class='messageerror'></span> </td>";
	        html=html+" <td> <input type='text'  value='' onblur='checkFormat(\"investigateInfo"+num+"investDesc\",\"maxlength\")'  class='form-control' id='investigateInfo"+num+"investDesc' name='investigateInfo["+num+"].investDesc'><span id='investigateInfo"+num+"investDesc_error' class='messageerror'></span> </td>";
	        html=html+" <td> <a href='javascript:void(0)'   onclick='deleteItem(\"investigateInfo\",\""+itemid+"\",\""+idno+"\")'  class='btn btn-default btn-xs black tooltips' data-placement='left' data-original-title='删除'><i class='fa fa-trash-o'></i></a></td>";
        }
        html=html+"</tr>";
        $("#"+type+"table").append(html);
        no=parseInt(no)+1;
        $("#"+type+"no").attr("value",no);
        num=parseInt(num)+1;
        $("#"+type+"num").attr("value",num);
        $("a.tooltips").tooltip();
     }
   

     //动态删除受害人信息 type:删除类型 victimInfo:受害人 ,suspectInfo:嫌疑人,investigateInfo:协查类型
     //itemid 记录编号，主要用于记录编号  idno用户记录元素id
     function deleteItem(type,itemid,idno){
        if(type=="victimInfo"){
          removeItemStatus(idno+"victimName","maxlength");
          removeItemStatus(idno+"victimTaobaoNo","maxlength");
          removeItemStatus(idno+"victimAlipay","maxlength");
          removeItemStatus(idno+"victimAddress","maxlength");
          removeItemStatus(idno+"victimTelephone","mobile");
          removeItemStatus(idno+"victimCardNo","card");
        }else if(type=="suspectInfo"){
          removeItemStatus(idno+"suspectName","maxlength");
          removeItemStatus(idno+"suspectTaobaoNo","maxlength");
          removeItemStatus(idno+"suspectAlipay","maxlength");
          removeItemStatus(idno+"suspectTelephone","mobile");
          removeItemStatus(idno+"suspectCardNo","card");
          removeItemStatus(idno+"suspectQq","qq");
          removeItemStatus(idno+"suspectEmail","email");
        }else if(type=="investigateInfo"){
          var lastype="";
          var itype=$("#"+idno+"investType").val();
          if(itype=="XCXX_EMAIL"){
	            lastype="email";
		   }else if(itype=="XCXX_RUL"){
		        lastype="url";  
		   }else if(itype=="XCXX_SFZ"){
		        lastype="card"; 
		  }else if(itype=="XCXX_MOBILE"){
		        lastype="mobile"; 
		  }else{
		        lastype="maxlength";  
		  }
		  removeItemStatus(idno+"investContent",lastype);
		  removeItemStatus(idno+"investDesc","maxlength");
        }
        $("#"+itemid).remove();
        //对删除的元素重新编号
        var num=0;
        $("#"+type+"table").find("tr").each(function (){
         var trobj=$(this); 
         trobj.children('td').eq(0).html(num);
         $("#"+type+"no").attr("value",num);
         num=num+1;
       })
     }
     
     
   //当节点删除后，清除验证信息和错误提示
   function removeItemStatus(id,type){
         if(type=="maxlength"){
	         delete maxmap[id]; 
	         delete namemap[id]; 
         }else if(type=="mobile"){
             delete mobileMap[id]; 
         }else if(type=="tel"){
             delete telMap[id]; 
         }else if(type=="card"){
             delete cardMap[id]; 
         }else if(type=="qq"){
             delete qqMap[id]; 
         }else if(type=="email"){
             delete emailMap[id];
         }else if(type=="url"){
             delete urlMap[id];
         }
         delete savemap[id];
         delete submitmap[id];
         $("#"+id+"_error").empty();
	     $("#"+id+"_error").closest('.form-group').removeClass('has-error');
	     $("#"+id+"_error").hide();  
   }
     
     
    //设置单选框选中状态
    function SelectStatus (name,index) {
		var obj = name+index;
		$("label[name='"+name+"']").each(function (i,item) {
			if (obj == $(item).attr("id")) {
				$(item).removeClass("btn btn btn-default");
				$(item).addClass("btn btn-success active");
				$(item).find("input").attr("checked","checked");
			} else {
				$(item).removeClass("btn btn-success active");
				$(item).addClass("btn btn btn-default");
			}
		})
	}
	
	
  //改变协查信息调用的方法
  function changeXcxxtype(name,num){
      var id=name+num+"investContent";
      $("#"+id).attr("value","");
      $("#"+id).focus();
  }   
     
  //核查协查列表输入格式
  function checkxcxxList(name,num){
     var id=name+num+"investContent";
     var type=$("#"+name+num+"investType").val();
     if(type=="XCXX_EMAIL"){
         checkFormat(id,"email");
     }else if(type=="XCXX_RUL"){
         checkFormat(id,"url");
     }else if(type=="XCXX_SFZ"){
         checkFormat(id,"card");
     }else if(type=="XCXX_MOBILE"){
         checkFormat(id,"mobile");
     }else{
         checkFormat(id,"pass");
     }
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
    
    //检查是否增加协查信息
    function checkxc(){
        var num=$("#investigateInfono").val();
        if(parseInt(num)>=1){
           return true;
        }else{
         return false ;
        }
     }
     
    //增加验证查询类型-- start
    function checkquery(){
         var rs=false;
	     var j=0;
	     for(var i=0;i<5;i++){
		    $("input[name='queryInfo["+i+"].queryType']:checkbox").each(function(){ 
                var ischeck= $(this).attr("checked");
                 if(ischeck!=undefined){
                 if($(this).attr("checked")){
                    j=j+1;
	             }
	           }
	        })
         }
         if(j>0){
           rs=true;
         }
	    return rs;
    }
    
    //增加验证 来访人 姓名
    function checktvisitorName(){
        var istake=value;
        if(istake == undefined){
          return false;
        }else{
           if(isNullData("visitorName")){
              return true;
          }
        }
	    return false;
    }
    //检查身份证是否合法 
    function validateIdCard(num){
     return idCardNoUtil.checkIdCardNo(num);
    }
   
   //检查用户数据的数据格式是否正确  id:页面元素的id;type:验证元素的类型 issave 是否改变状态   save 改变 check不改变
   function checkFormat(id,type,issave,lableName){
     if(lableName==undefined){
       lableName="";
     }
     if(issave==undefined){
        issave="check";
     }
     var rule="";
     var message=lableName;
     var mobile=$("#"+id).val();
     if(mobile==''){
       var ispass=true;
       if(issave=="submit" && (type=="maxlengthnative" ||  type=="money" || type=="maxlength" || type=="telephone")){
            if(isNullData(id)==false && id!="caseInfocaseNo"){
              var name="";
              if(type=="maxlengthnative"){
                 if(id!="caseInfobrand"){
                    ispass=false;
                    name=nativenamemap[id];
                    message=name.replace(":"," ")+"不能为空!";
                  }
              }else if(type=="money"){
                  ispass=false;
                  message="案件信息中  案件金额"+"不能为空!";
              }else if(type=="maxlength"){
                 if(id=="entrustInfovisitorName" || id=="visitorTelephone"|| id=="visitorUnit" || id=="visitorTitle"){
                    ispass=false;
                    name=namemap[id];
                    message=name.replace(":"," ")+"不能为空!";
                  }
              }else if(type=="telephone"){
                  if(id=="visitorTelephone" || id=="entrustInfotelephone"){
                    ispass=false;
                    name=telMap[id];
                    message=name.replace(":"," ")+"不能为空!";
                  }
              }
              
            }
         }else{
            ispass=true;  
        }
        setstatus(id,ispass,issave,message);
       return ispass;
     }
     var ispass=false;
     if(type=="mobile"){
        rule=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0-9]|170)\d{8}$/;
        ispass=rule.test(mobile);
        message=message+"手机号码错误!";
     }else if(type=="email"){
        rule=/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
        ispass=rule.test(mobile);
        message=message+"email格式错误!";
     }else if(type=="telephone"){
        rule=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
        var rule1=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0-9]|170)\d{8}$/;
        ispass=rule.test(mobile) || rule1.test(mobile);
        message=message+"电话号格式错误!";
     }else if(type=="number"){
        rule= /^[0-9]*[1-9][0-9]*$/;
        ispass=rule.test(mobile);
        message=message+"只能输入正整数!";
     }else if(type=="card"){
        ispass=validateIdCard(mobile);
        message=message+"身份证格式错误!";
     }else if(type=="qq"){
        rule= /^\d{5,10}$/;
        ispass=rule.test(mobile);
        message=message+"QQ格式错误!";
     }else if(type=="url"){
        var strRegex = "(http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?";        
        var re=new RegExp(strRegex); 
        ispass=re.test(mobile)
	    message=message+"URL格式错误!";
     }else if(type=="pass"){
        ispass=true;
        message="";
     }else if(type=="minlength"){
        var len =mobile.length;
        if(parseInt(len)<39){
           ispass=false;
           message=message+"请至少输入40个字来描述简要案情";
        }else{
           ispass=true;
        }
     }else if(type=="maxlength"){
          if(mobile==undefined){
              delete maxmap[id];
              delete namemap[id];
              ispass=true;
          }else{
              var len =mobile.length;
              var maxlen=maxmap[id];
              var name=namemap[id];
              if(parseInt(len)>parseInt(maxlen)){
              ispass=false;
              if(lableName==""){
                 var nameArr=name.split(":");
                 message=nameArr[1]+"不能超过"+maxlen+"个字符";
              }else{
                 message=message+"不能超过"+maxlen+"个字符";
              }
              }else{
                ispass=true;
              }
          }
          
     }else if(type=="maxlengthnative"){
          var len =mobile.length;
          var maxlen=nativemap[id];
          var name=nativenamemap[id];
          if(parseInt(len)>parseInt(maxlen)){
              ispass=false;
              if(lableName==""){
                var nameArr=name.split(":");
                message=nameArr[1]+"不能超过"+maxlen+"个字符";
              }else{
                message=message+"不能超过"+maxlen+"个字符";
              }
          }else{
              ispass=true;
          }
     }else if(type=="money"){
         rule= /^[0-9]*[1-9][0-9]*$/;
         ispass=rule.test(mobile);
         if(lableName==""){
            message="案件金额只能输入正整数!";
         }else{
            message=message+"只能输入正整数!";
         }
         if(ispass){
            var len=mobile.length;
            if(parseInt(len)>11){
               ispass=false;
                if(lableName==""){
                  message="案件金额长度不能超过11位!";
               }else{
                  message=message+"案件金额长度不能超过11位!";
               }
            }else{
               ispass=true;
            }
         }
     }
     setstatus(id,ispass,issave,message);
     return ispass;
   }
   
   // ispass:数据检验是否通过 ; type:检验类型 'save' 保存 'submit' 提交 ;
   // id:页面元素id;message:页面元素验证失败后的提示信息
   function setstatus(id,ispass,type,message){
       if(!ispass){
	       $("#"+id+"_error").empty();
	       $("#"+id+"_error").append(message);
	       $("#"+id+"_error").closest('.form-group').addClass('has-error');
	       $("#"+id+"_error").show();
	     }else{
	       $("#"+id+"_error").empty();
	       $("#"+id+"_error").closest('.form-group').removeClass('has-error');
	       $("#"+id+"_error").hide();  
	   }
       if(type=="save"){
         var saveispass=$("#saveispass").val();
	     if(!ispass){
           savemap[id] = message;
           $("#saveispass").attr("value","0");
	     }else{
	         delete savemap[id]; 
	         if(saveispass=="1"){
	            $("#saveispass").attr("value","1");
	         }else if(saveispass=="0"){
	            $("#saveispass").attr("value","0");
	         }
	     }
       }else if(type=="submit"){
         var passflag= $("#submitispass").val();
       // alert("元素id:"+id+"==通过状态为:"+ispass)
        if(!ispass){
            submitmap[id] = message;
	        $("#submitispass").attr("value","0");
	    }else{
	        delete submitmap[id]; 
	        if(passflag=="1"){
	           $("#submitispass").attr("value","1");
	        }else if(passflag=="0"){
	           $("#submitispass").attr("value","0");
	        }else{
	           if(ispass){
	             $("#submitispass").attr("value","1");
	           }else{
	             $("#submitispass").attr("value","0");
	           }
	        }
	     }
       }
   }
   
   //验证单个元素为空 id:页面元素id;title:页面元素名称;type:验证类型 1：请选择 2 请上传 3 请填写
   function checkSigleIsNull(id,title,type,tabName){
     
       var ifpass=isNullData(id);
       var message="";
       var action="";
        
       if(!ifpass){
          if(type=='1'){
             action="请选择";
          }else if(type=='2'){
             action="请上传";
          }else if(type=='3'){
             action="请填写";
          }
          message=tabName+action+title;
          submitmap[id] = message;
       }
       setstatus(id,ifpass,"submit",message);
       return ifpass;
   }
   
  //检验单个元素最大或者最小长度限制验证，提交时
  function checkSiglelength(id,type){
       var maxlen=maxmap[id];
       var name=namemap[id];
       var ifpass=checkTextLength(id,type);
       message=name+"不能超过"+maxlen+"个字符";
       setstatus(id,ifpass,"submit",message);
   }
   
   
   //判断页面元素能输入最大字符或者最小字符的判断 id 页面元素id  type 验证类型 max 最大  mix 最小  tsize最大或者最小长度  title 页面元素名称
   function checkTextLength(id,type){
      tsize=maxmap[id];
      title=namemap[id];
      var ispass=false;
      var val=$("#"+id).val();
      var len=val.length;
      if(type=="min"){
       tsize=40;
       if(parseInt(len)<parseInt(tsize)){
            message="请至少输入"+tsize+"个字来描述"+title;
         }else{
           ispass=true;
         }
       }else if(type=="max"){
          if(parseInt(len)>parseInt(tsize)){
            message=title+"不能超过"+tsize+"个字符";
         }else{
           ispass=true;
         }
       }
       return ispass;
   }
   
   //检查案件办理阶段是否为空
   function checkCaseStage(){
     var caseStage= $("input[name='entrustInfo.caseStage']:checked").val();
     if(caseStage==undefined){
       return false;
     }
     return true;
   }
   
   //数据提交时通用验证方法
   function checkSubmitBefore(){
        checkSigleIsNull("select2_sample1","办案单位所在地(省)","1","委托信息中 ");
        checkSigleIsNull("select2_sample2","办案单位所在地(市)","1","委托信息中 ");
        checkSigleIsNull("entrustInfounithidden","办案单位名称 ","3","委托信息中 ");
        checkSigleIsNull("entrustInfopersonName","办案人","3","委托信息中 ");
        checkSigleIsNull("entrustInfotelephone","办案人联系电话 ","3","委托信息中 ");
        checkSigleIsNull("entrustInfotitle","办案人职务 ","3","委托信息中 ");
        caseNameispass=checkSigleIsNull("caseInfocaseName","案件名称","3","案件信息中 ");
        checkSigleIsNull("caseInfocaseOccurDate","案发时间","3","案件信息中 ");
        checkSigleIsNull("caseMoneyAmout","案件金额 ","3","案件信息中 ");
        checkSigleIsNull("tags_1","附件文书","2","委托信息中 ");
        var caseStateispass= checkCaseStage();
        setstatus("entrustInfocaseStage",caseStateispass,"submit","委托信息中  请选择一个案件办理阶段");
        var queryispass=checkquery();
        setstatus("queryInfoqueryType",queryispass,"submit","查询类型：至少选择一种查询类型!");
        var xcispass=checkxc();
        setstatus("investigateInfolist",xcispass,"submit","需侦查信息中 请增加协查信息");
      //简要案情至少输入50个字个判断
        var minleng= checkTextLength("caseInfocaseInfo","min");
        setstatus("caseInfocaseInfo",minleng,"submit","案件信息中 请至少输入40个字来描述简要案情");
        var istake=$("input[name='entrustInfo.istake']:checked").val();
        if(istake=="on"){
           var visitorNameispass=checkSigleIsNull("entrustInfovisitorName","来访人姓名 ","3","委托信息中 ");
           if(visitorNameispass){
              checkSiglelength("entrustInfovisitorName","max");
           }
          checkSigleIsNull("visitorTelephone","来访人联系方式 ","3","委托信息中 ");
          checkSigleIsNull("visitorUnit","来访人单位 ","3","委托信息中 ");
          checkSigleIsNull("visitorTitle","来访人职务 ","3","委托信息中 ");
      }else{
          delete submitmap["entrustInfovisitorName"]; 
          delete submitmap["visitorTelephone"]; 
          delete submitmap["visitorUnit"]; 
          delete submitmap["visitorTitle"]; 
      }
      //验证动态部分校验 验证最大长度
      checkSaveBefor("submit");
   }
  
  //提交数据到后台，需要验证数据的数据的必须性和是否必填
  function saveSubmit() { 
      $("#submitbutton").attr("disabled",true); 
      $("#alert-warning").hide();
    //提交前校验办案单位是否选中父节点
    //  var unitvalue=$("#entrustInfounit").val();
      //$("#entrustInfounithidden").attr("value",unitvalue);
      //if(!isNullData("entrustInfounit")){
        //  var unitName=$("#select2-chosen-3").text();
         // if(unitName!='' && unitName!=undefined){
          //   var unitCode = unitMap[unitName];
          // //  $("#entrustInfounithidden").attr("value",unitCode);
          //}
     // }
     $("#typeinfo").attr("value","submit");
     $("#submitispass").attr("value","-1");
     checkSubmitBefore();
     checkUploadFile("submit"); 
	 var isSave=$("#submitispass").val();
      if(isSave!="1"){
         $("#alert-danger").empty();
		 $("#alert-danger").append("<button class='close' data-close='alert'></button>");
		 var num=1;
		 for(var prop in submitmap){
		    if(submitmap.hasOwnProperty(prop)){
		       var message=submitmap[prop];
		       $("#alert-danger").append(num+"."+message+"<br>");
		       num=num+1;
		    }
		  }
		 $("#alert-success").hide()
		 $("#alert-warning").hide();
         $("#alert-danger").show();
         App.scrollTo($("#alert-danger"), -100);
         $("#submitbutton").removeAttr("disabled"); 	
       }else{
            $("#alert-danger").empty();
            $("#alert-danger").hide();
           //验证通过后提交数据
           $("#savewaitdiv").click();
           $.ajax({
		       url:'${ctx}/entrust/apply/saveEntrustBaseInfo.html', //后台处理程序
		       type:'post',         //数据发送方式
		       dataType:'json',     //接受数据格式
		       data:$("#form").serialize(),         //要传递的数据
		       success:function(data){
		           $("#savewaitdiv").click();
		           if(data=="1"){
		               $("#alert-danger").hide();
		               $("#alert-success").hide();
			           $("#alert-warning").empty();
			           $("#alert-warning").append("<button class='close' data-close='alert'></button>");
			           $("#alert-warning").append("数据提交成功!");
			           //App.scrollTo($("#alert-success"), -100);	
			          $("#alert-warning").show();
			          setTimeout('linkToMyentrust()',1000);
			          
		           }else if(data=="0"){
		             $("#alert-danger").hide();
		             $("#alert-success").hide();
		             $("#alert-warning").empty();
			         $("#alert-warning").append("<button class='close' data-close='alert'></button>");
			         $("#alert-warning").append("数据提交失败!");
			        // App.scrollTo($("#alert-success"), -100);	
			         $("#alert-warning").show();
		         }else if(data=="-1"){
		            alert("存在超过一个星期已反馈但未完成的委托，请先将委托完成后在提交数据!");
		            $("#submitbutton").removeAttr("disabled"); 
		         }
		       } 
	        });
       }
	}
	
  function linkToMyentrust(){
    window.location.href="${ctx}/entrust/myApply/index.html";
  }	
	
  //保存前的验证
  function checkSaveBefor(type){
      //首先处理协查信息列表中，动态验证的元素
      var investigateInfosize=$("#investigateInfonum").val();
      if(investigateInfosize!=''){
         for(var i=0;i<parseInt(investigateInfosize);i++){
            var dtype=$("#investigateInfo"+i+"investType").val();
            var investContent="investigateInfo"+i+"investContent"; 
            var investDesc="investigateInfo"+i+"investDesc"
            maxmap[investDesc] =200;
            namemap[investDesc] = "需侦查信息:说明";
            if(dtype=="XCXX_EMAIL"){
               emailMap[investContent] = "需侦查信息:内容";
               delete urlMap[investContent];
               delete cardMap[investContent];
               delete mobileMap[investContent];
               delete maxmap[investContent];
               delete namemap[investContent];
            }else if(dtype=="XCXX_RUL"){
               urlMap[investContent] = "需侦查信息:内容";
               delete emailMap[investContent];
               delete cardMap[investContent];
               delete mobileMap[investContent];
               delete maxmap[investContent];
               delete namemap[investContent];
            }else if(dtype=="XCXX_SFZ"){
               cardMap[investContent] = "需侦查信息:内容";
               delete emailMap[investContent];
               delete urlMap[investContent];
               delete mobileMap[investContent];
               delete maxmap[investContent];
               delete namemap[investContent];
            }else if(dtype=="XCXX_MOBILE"){
               mobileMap[investContent] = "需侦查信息:手机号";
               delete emailMap[investContent];
               delete urlMap[investContent];
               delete cardMap[investContent];
               delete maxmap[investContent];
               delete namemap[investContent];
            }else{
               var contentValue=$("#"+investContent).val();
               if(contentValue!=''){
                  maxmap[investContent] =100;
                  namemap[investContent] = "需侦查信息:内容";
                  delete mobileMap[investContent];
                  delete emailMap[investContent];
	              delete urlMap[investContent];
	              delete cardMap[investContent];
               }else{
                  checkFormat(investContent,"pass");
               }
            }
         }
      }
      //固定元素验证 如果为保存时

       for(var prop in nativemap){
            var lableName=nativenamemap[prop].replace(":"," ");
		    checkFormat(prop,"maxlengthnative",type,lableName);
	     }
	   
	   //验证金额
	   for(var prop in  moneyMap){
	       var lableName=moneyMap[prop].replace(":"," ");
		   checkFormat(prop,"money",type,lableName);
		   
	   }
    
      //动态输入最大长度长度验证
      for(var prop in maxmap){
           var lableName=namemap[prop].replace(":"," ");
           
		   checkFormat(prop,"maxlength",type,lableName);
	  }
	  
	  //手机号码验证
	  for(var prop in mobileMap){
	       var lableName=mobileMap[prop].replace(":"," ");
		   checkFormat(prop,"mobile",type,lableName);
	  }
	  //手机加固话验证
	  for(var prop in telMap){
	      
	       var lableName=telMap[prop].replace(":"," ");
		   checkFormat(prop,"telephone",type,lableName);
	  }
	  //动态身份证号验证
	  for(var prop in cardMap){
	       var lableName=cardMap[prop].replace(":"," ");
		   checkFormat(prop,"card",type,lableName);
	  }
	  //动态qq验证
	  for(var prop in qqMap){
	       var lableName=qqMap[prop].replace(":"," ");
		   checkFormat(prop,"qq",type,lableName);
	  }
	  //动态验证email
	  for(var prop in emailMap){
	       var lableName=emailMap[prop].replace(":"," ");
		   checkFormat(prop,"email",type,lableName);
	  }
	  
	  //动态验证url
	  for(var prop in urlMap){
	       var lableName=urlMap[prop].replace(":"," ");
		   checkFormat(prop,"url",type,lableName);
	  }
  }
	
  //保存数据，只是校验数据输入的合法性，页面元素可以不填写
  function saveData() {
      $("#alert-warning").hide();
      $("#saveispass").attr("value","1");
      //提交前校验办案单位是否选中父节点
       //var unitvalue=$("#entrustInfounit").val();
      // $("#entrustInfounithidden").attr("value",unitvalue);
       //if(!isNullData("entrustInfounit")){
           //var unitName=$("#select2-chosen-3").text();
          // if(unitName!='' && unitName!=undefined){
             // var unitCode = unitMap[unitName];
            //  $("#entrustInfounithidden").attr("value",unitCode);
           //}
     //  }
      //保存前先清空上次提交留下的校验信息
      for(var prop in submitmap){
		  if(submitmap.hasOwnProperty(prop)){
		       $("#"+prop+"_error").closest('.form-group').removeClass('has-error');
		       $("#"+prop+"_error").empty();
		    }
       }
      $("#typeinfo").attr("value","save");
      var isSave="1";
      checkSaveBefor("save");
      checkUploadFile("save"); 
      isSave=$("#saveispass").val();
      if(isSave!="1"){
         $("#alert-success").hide()
         $("#alert-danger").empty();
		 $("#alert-danger").append("<button class='close' data-close='alert'></button>");
		 var num=1;
		 for(var prop in savemap){
		    if(savemap.hasOwnProperty(prop)){
		       var message=savemap[prop];
		       $("#alert-danger").append(num+"."+message+"<br>");
		       num=num+1;
		    }
		  }
		 $("#alert-danger").hide();
		 $("#alert-warning").hide();
         $("#alert-danger").show();
         App.scrollTo($("#alert-danger"), -100);
      }else{
            $("#savewaitdiv").click();
            $("#alert-danger").empty();
            $("#alert-danger").hide();
           //验证通过后提交数据
           $.ajax({
		       url:'${ctx}/entrust/apply/saveEntrustBaseInfo.html', //后台处理程序
		       type:'post',         //数据发送方式
		       dataType:'json',     //接受数据格式
		       data:$("#form").serialize(),         //要传递的数据
		       success:function(data){
		        $("#savewaitdiv").click();
		        if(data=="1"){
		             $("#alert-success").hide();
		             $("#alert-danger").hide();
			         $("#alert-warning").empty();
			         $("#alert-warning").append("<button class='close' data-close='alert'></button>");
			         $("#alert-warning").append("数据保存成功!");
			        // App.scrollTo($("#alert-success"), -100);	
			         $("#alert-warning").show();
		         }else if(data=="0"){
		             $("#alert-success").hide();
		             $("#alert-danger").hide();
		             $("#alert-warning").empty();
			         $("#alert-warning").append("<button class='close' data-close='alert'></button>");
			         $("#alert-warning").append("数据保存失败!");
			         //App.scrollTo($("#alert-success"), -100);	
			         $("#alert-warning").show();
		         }else if(data=="-1"){
		            alert("存在超过一个星期已反馈但未完成的委托，请先将委托完成后在保存数据!");
		         }
		      } 
	     });
      }
	}
	
	
	//提交或者保存的时候，判断文件附件是否存在
	function checkUploadFile(isSave){
      var fileMap={};
	  var uploadFileNames=$("#tags_1").val();
	  var fileListStr=$("#fileList").val();
	  var isNext=true;
	  var count=0;
	  var message="案件信息中 附件 ";
	  if(isSave=="submit"){
	    if(uploadFileNames==''){
	       setstatus("tags_1",false,"submit",message+" 不能为空");
	       return;
	    }
	  }
	  if(fileListStr!=''){
	    var fss=fileListStr.split("#");
	    for(var j=0;j<fss.length;j++){
	       var bean=fss[j];
	       var title=bean.split("|");
	       fileMap[title[0]]=title[1];
	    }
	  }
	  if(uploadFileNames!=''){
	     var ufns=uploadFileNames.split(",");
	     for(var i=0;i<ufns.length;i++){
	        var fn=ufns[i];
	        var base64=fileMap[fn];
	        if(base64=='' || base64==undefined){
	           count=count+1;
	           message=message+"["+fn+"],";
	        }
	     }
	  }
      if(count>0){
         isNext=false;
      }
      setstatus("tags_1",isNext,isSave,message+"内容不存在");
	}
	
	//判断上传文件是否已经存在
	function checkFileExit(flist,fileName){
	   var fs=flist.split(",");
		for(var i=0;i<fs.length;i++){
		 if(fs[i]==fileName){
		    return true;
		  }
		}
		return false;
	}
	
	//上传文件
	function showPreview(source) {
	    var fstr='${ftypes}';
	    var farrs=fstr.split(",");
		var file = source.files[0];
		var count=0;
		//alert(file.type);
		//修改，增加附件上限 5m
		if(file.size>5*1024*1024){
			alert("文件大小超过5M，请压缩后再上传！");
			return;
		}
		if(file.name!=''){
		  var fname=file.name;
		  if(fname.indexOf(",")>0){
		    alert("文件名中包含特殊字符!");
		    return;
		  }
		  ftype=fname.split(".")[1].toLowerCase();
          for(var i=0;i<farrs.length;i++){
             var ft=farrs[i];
             if(ft==ftype){
               count=count+1;
               break ;
             }
          }
		  if(count == 0 ){
		     alert("请上传正确的文件类型("+fstr+")!");
		     return;
		  }
		}
	    var flist=$("#tags_1").val();
	    var ffarrs=flist.split(",");
		if(window.FileReader) {
			var reader = new FileReader();
			 //异步方式，不会影响主线程
			 reader.readAsDataURL(file);
			 reader.onload = function(e) {
			 var dataBase64 = this.result;
			 if(flist==''){
			   	$("#tags_1").addTag(file.name);
 				$("#fileList").attr("value",file.name+"|"+dataBase64);
			 }else{
			   var isexit= checkFileExit(flist,file.name);
			   if(!isexit){
			     if(ffarrs.length<=4){
			       $("#tags_1").addTag(file.name);
			       $("#fileList").attr("value",$("#fileList").val()+"#"+file.name+"|"+dataBase64);
			     }else{
			       alert("最多能上传5个附件!");
			     }
			     
			   }else{
			     alert("文件已经上传！");
			   }
			 }
			};
		}
		$("#fnamelist").attr("value","");
	}

	$("#my-dropzone").dropzone({
		url: null,
        addRemoveLinks: true,
        dictRemoveLinks: "x",
        dictCancelUpload: "x",
        maxFiles: 5,
        maxFilesize: 10,
        acceptedFiles: null,
        dictDefaultMessage:"请把文件拖放到这个区域 <br><br>或在此点击，选择需要上传的文件...",
        dictFileTooBig: "文件太大 ({{filesize}}MiB). 最大只能: {{maxFilesize}}MiB.",
    	init: function() {
        	this.on("addedfile", function(file) {
                    	//alert(file);
                    	var reader = new FileReader();
                    	reader.readAsDataURL(file);
                    	reader.onload = function(e){
                    		var dataBase64 = this.result;
                    		//alert(dataBase64);
                    		var flist=$("#uploadfileName").val();
                    		if(flist==''){
			   					$("#uploadfileName").attr("value",file.name);
 								$("#fileList").attr("value",file.name+"|"+dataBase64);
			 				}else{
			 					$("#uploadfileName").attr("value",$("#uploadfileName").val()+","+file.name);
			 					$("#fileList").attr("value",$("#fileList").val()+"#"+file.name+"|"+dataBase64);
			 				}
                    		
                    	};
                    });
              this.on("removedfile",function(file){
              	//移除监听
              	//alert(file.name);
              	var flist=$("#uploadfileName").val();
              	flist = removedfileFordropzone(flist,file.name);
              	$("#uploadfileName").attr("value",flist);
              });
    	}
	});
	
	//移除
	function removedfileFordropzone(flist,fileName){
		//判断移除文件是否是第一个文件
		var replaceStr = "";
		if(flist.indexOf(fileName)==0){
			replaceStr = fileName+",";
		}else{
			replaceStr=","+fileName;
		}
		return flist.replace(replaceStr,"");
	}
	
 
  function getSelectItme(dicJsonArr,dicKey){
     $.each(dicJsonArr,function(i,item){
         
     });
  }	
	
  //初始化办案单位下拉框
  function unit_init(dicJsonArr) {
		$.each(dicJsonArr,function(i,item){
		    unitNameMap[item.dictKey]=item;
			if (item.childrenFlag == '1') {
				unit_init(item.childrenDicJsonArr);
				return;
			}
		});
	}
	
	//身份证验证相关方法
	var idCardNoUtil = {
    provinceAndCitys: {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"},
        powers: ["7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2"],parityBit: ["1","0","X","9","8","7","6","5","4","3","2"],genders: {male:"男",female:"女"},
        checkAddressCode: function(addressCode) {
            var check = /^[1-9]\d{5}$/.test(addressCode);
            if(!check) return false;
            if(idCardNoUtil.provinceAndCitys[parseInt(addressCode.substring(0,2))]){
                return true;
            } else {
                return false;
            }
        },
    checkBirthDayCode: function(birDayCode){
        var check = /^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/.test(birDayCode);
        if(!check) return false;
        var yyyy = parseInt(birDayCode.substring(0,4),10);
        var mm = parseInt(birDayCode.substring(4,6),10);
        var dd = parseInt(birDayCode.substring(6),10);
        var xdata = new Date(yyyy,mm-1,dd);
        if(xdata > new Date()){
            return false;//生日不能大于当前日期
        }else if ( ( xdata.getFullYear() == yyyy ) && ( xdata.getMonth () == mm - 1 ) && ( xdata.getDate() == dd ) ){
            return true;
        }else{
            return false;
        }
    },
    getParityBit: function(idCardNo){
        var id17 = idCardNo.substring(0,17);
        var power = 0;
        for(var i=0;i<17;i++){
            power += parseInt(id17.charAt(i),10) * parseInt(idCardNoUtil.powers[i]);
        }
        var mod = power % 11;
        return idCardNoUtil.parityBit[mod];
    },
    checkParityBit: function(idCardNo){
        var parityBit = idCardNo.charAt(17).toUpperCase();
        if(idCardNoUtil.getParityBit(idCardNo) == parityBit){
            return true;
        }else{
            return false;
        }
    },
    checkIdCardNo: function(idCardNo){
        //15位和18位身份证号码的基本校验
        var check = /^[0-9]{15}|([0-9]{17}([0-9]|x|X))$/.test(idCardNo);
        if(!check) return false;
        //判断长度为15位或18位
        if(idCardNo.length==15){
            return idCardNoUtil.check15IdCardNo(idCardNo);
        }else if(idCardNo.length==18){
            return idCardNoUtil.check18IdCardNo(idCardNo);
        }else{
            return false;
        }
    },
    //校验15位的身份证号码
    check15IdCardNo: function(idCardNo){
        //15位身份证号码的基本校验
        //在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
        var check = /^[1-9][0-9]{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))[0-9]{3}$/.test(idCardNo);
        if(!check) return false;
        //校验地址码
        var addressCode = idCardNo.substring(0,6);
        check = idCardNoUtil.checkAddressCode(addressCode);
        if(!check) return false;
            var birDayCode = '19' + idCardNo.substring(6,12);
        //校验日期码
        return idCardNoUtil.checkBirthDayCode(birDayCode);
    },
    //校验18位的身份证号码
    check18IdCardNo: function(idCardNo){
        //18位身份证号码的基本格式校验
        //在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
        var objReg = /^[1-9][0-9]{5}[1-9][0-9]{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))[0-9]{3}([0-9]|x|X)$/i;
        var check = objReg.test(idCardNo);
        if(!check) return false;
        //校验地址码
        var addressCode = idCardNo.substring(0,6);
        check = idCardNoUtil.checkAddressCode(addressCode);
        if(!check) return false;
        //校验日期码
        var birDayCode = idCardNo.substring(6,14);
        check = idCardNoUtil.checkBirthDayCode(birDayCode);
        if(!check) return false;
        //验证校检码
        return idCardNoUtil.checkParityBit(idCardNo);
    },
    formateDateCN: function(day){
        var yyyy =day.substring(0,4);
        var mm = day.substring(4,6);
        var dd = day.substring(6);
        return yyyy + '-' + mm +'-' + dd;
    },
    //获取信息
    getIdCardInfo: function(idCardNo){
        var idCardInfo = {
            gender:"", //性别
            birthday:"" // 出生日期(yyyy-mm-dd)
        };
        if(idCardNo.length==15){
            var aday = '19' + idCardNo.substring(6,12);
            idCardInfo.birthday=idCardNoUtil.formateDateCN(aday);
            if(parseInt(idCardNo.charAt(14))%2==0){
                idCardInfo.gender=idCardNoUtil.genders.female;
            }else{
                idCardInfo.gender=idCardNoUtil.genders.male;
            }
        }else if(idCardNo.length==18){
            var aday = idCardNo.substring(6,14);
            idCardInfo.birthday=idCardNoUtil.formateDateCN(aday);
            if(parseInt(idCardNo.charAt(16))%2==0){
                idCardInfo.gender=idCardNoUtil.genders.female;
            }else{
                idCardInfo.gender=idCardNoUtil.genders.male;
            }
        }
        return idCardInfo;
    },
    getId15:function(idCardNo){
        if(idCardNo.length==15){
            return idCardNo;
        }else if(idCardNo.length==18){
            return idCardNo.substring(0,6) + idCardNo.substring(8,17);
        }else{
            return null;
        }
    },
    getId18: function(idCardNo){
        if(idCardNo.length==15){
            var id17 = idCardNo.substring(0,6) + '19' + idCardNo.substring(6);
            var parityBit = idCardNoUtil.getParityBit(id17);
            return id17 + parityBit;
        }else if(idCardNo.length==18){
            return idCardNo;
        }else{
            return null;
        }
    }
 };
 
 
 //--------------------弹出层------------------- 
//popDivId:弹出层div的ID 
//dragDivId:用于拖动div的ID 
//isShowMask:是否显示遮罩层 
function popDivShow(popDivId, dragDivId, isShowMask) { 
if (isShowMask) { 
creatMask(popDivId); 
} 
var oWins = document.getElementById(popDivId); 
var oWins_title = document.getElementById(dragDivId); 
var bDrag = false; 
var disX = disY = 0; 
oWins.style.display = "block"; 
oWins_title.onmousedown = function(event) { 
var event = event || window.event; 
bDrag = true; 
disX = event.clientX - oWins.offsetLeft; 
disY = event.clientY - oWins.offsetTop; 
this.setCapture && this.setCapture(); 
return false; 
}; 
document.onmousemove = function(event) { 
if (!bDrag) 
return; 
var event = event || window.event; 
var iL = event.clientX - disX; 
var iT = event.clientY - disY; 
var maxL = document.documentElement.clientWidth - oWins.offsetWidth; 
var maxT = document.documentElement.clientHeight - oWins.offsetHeight; 
iL = iL < 0 ? 0 : iL; 
iL = iL > maxL ? maxL : iL; 
iT = iT < 0 ? 0 : iT; 
iT = iT > maxT ? maxT : iT; 
oWins.style.marginTop = oWins.style.marginLeft = 0; 
oWins.style.left = iL + "px"; 
oWins.style.top = iT + "px"; 
return false; 
}; 
document.onmouseup = window.onblur = oWins_title.onlosecapture = function() { 
bDrag = false; 
oWins_title.releaseCapture && oWins_title.releaseCapture(); 
}; 
} 
// 隐藏弹出层 
function popDivHidden(popDivId) { 
var oWins = document.getElementById(popDivId); 
oWins.style.display = "none"; 
window.parent.document.body.removeChild(window.parent.document.getElementById("maskDiv")); 
} 
// 获取弹出层的zIndex 
function getZindex(popDivId) { 
var popDiv = document.getElementById(popDivId); 
var popDivZindex = popDiv.style.zIndex; 
return popDivZindex; 

} 
// 创建遮罩层 
function creatMask(popDivId) { 
// 参数w为弹出页面的宽度,参数h为弹出页面的高度,参数s为弹出页面的路径 
var maskDiv = window.parent.document.createElement("div"); 
maskDiv.id = "maskDiv"; 
maskDiv.style.position = "fixed"; 
maskDiv.style.top = "0"; 
maskDiv.style.left = "0"; 
maskDiv.style.zIndex = getZindex(popDivId) - 1; 
maskDiv.style.backgroundColor = "#333"; 
maskDiv.style.filter = "alpha(opacity=70)"; 
maskDiv.style.opacity = "0.7"; 
maskDiv.style.width = "100%"; 
maskDiv.style.height = (window.parent.document.body.scrollHeight + 50) + "px"; 
window.parent.document.body.appendChild(maskDiv); 
maskDiv.onmousedown = function() { 
window.parent.document.body.removeChild(window.parent.document.getElementById("maskDiv")); 
}; 
} 
</script>