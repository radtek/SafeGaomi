<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<script>
jQuery(document).ready(function() {       
   // initiate layout and plugins
   App.init();

    
});
</script> 
<script>
	var unitCode;
	var roleIds;
	var unitName;
	var unitMap = {};
	
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
		
		//当前菜单高亮显示
		addHightLightByMenu("clueProvider");
		
		//加载用户单位代码
		unit_init (${dicJsonArr});	
		$("#unitCode_search").append(html_unit_ali);
		$("#unitCode_search").append(html_unit);
		$("#unitCode_edit").append(html_unit);
		
		$("#clueProviderInfoTable").jqGrid({
	        url:"${ctx}/ccp/clueProvider/query.html",
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
	        colNames:['ID','序号', '姓名', '手机号','用户单位', '身份证号', '注册时间', '用户积分', ''],
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
	            	name:'trueName', 
	            	index:'trueName', 
	            	align:'left', 
	            	width:'8%',
	            	sortable:false
	            },
	            {
	            	name:'phoneNum', 
	            	index:'phoneNum', 
	            	align:'left', 
	            	width:'10%',
	            	sortable:false
	            },
	            {
	            	name:'unitName', 
	            	index:'unitName', 
	            	align:'left', 
	            	width:'10%',
	            	sortable:false
	            },
	            {
	            	name:'idCardNum', 
	            	index:'idCardNum', 
	            	align:'left', 
	            	width:'15%',
	            	sortable:false
	            },
	            {
	            	name:'createDate', 
	            	index:'createDate', 
	            	align:'left', 
	            	width:'15%',
	            	sortable:false
	            },
	            {
	            	name:'score', 
	            	index:'score', 
	            	align:'left', 
	            	width:'6%',
	            	sortable:false
	            },
	            {
	            	name:'operMod',
	            	index:'operMod',
	            	align:"left",
	            	width:'15%',
	            	formatter:function(cellvalue, options, rowObject){  
	            		var content = '<a class="btn btn-default btn-xs black tooltips margin-right-10" data-toggle="modal" id="view" href="#details" onclick="showDetails(\'' + rowObject.id + '\');"><i class="fa fa-info"></i> 详情</a><a class="btn btn-default btn-xs black tooltips margin-right-10" data-toggle="modal" id="edit" href="#editUser" onclick="showEditLabel(\'' + rowObject.id + '\');"><i class="fa fa-edit"></i> 修改</a><a class="btn btn-default btn-xs black tooltips" data-toggle="modal" href="#del" onclick="showDelLabel('+rowObject.id+')"><i class="fa fa-trash-o"></i> 删除</a>'; 
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
	    
	    // 手机号码验证    
	    jQuery.validator.addMethod("isMobile", function(value, element) {    
	      var length = value.length;    
	      return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));    
	    }, "请正确填写您的手机号码。");
	    
	    // 身份证号码验证
	    jQuery.validator.addMethod("isIdCardNo", function(value, element) { 
	      //var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;   
	      return this.optional(element) || isIdCardNo(value);    
	    }, "请输入正确的身份证号码。"); 
	    
	    //编辑表单校验
		$("#providerEditForm").validate({
			submitHandler: function (form) {
				$(form).ajaxSubmit({
					type : "post",
					dataType:"json",
					success : function(data) {
						if (data.flag == 'true')  {
							//关闭弹窗，刷新表格, 重置新增表单
							$("#edit_button_return").click();
							$("#clueProviderInfoTable").jqGrid('setGridParam',{ 
					            url:"${ctx}/ccp/clueProvider/query.html", 
					            postData:{
					            		 }, //发送数据 
					            page:1 
					        }).trigger("reloadGrid"); //重新载入 
					        
						} else if (data.flag == 'false'){
							alert(data.content);
						}
					}
				});
			},
			rules:{
				trueName:{
	            	required: true,
	            	rangelength: [2,10]
	            },
	            phoneNum:{
	            	required: true,
	            	isMobile:true
	            },
	            idCardNum:{
	            	required: true,
	            	isIdCardNo: true
	            },
	            score:{
	            	digits:true
	            }
	        },
	        messages: {
	        	trueName: {
		            required: "请填写真实姓名",
		            rangelength: "真实姓名必须在2-10个字符之间" 
		        },
		        phoneNum:{
	            	required: "请填写手机号",
	            	isMobile: "手机号格式错误"
	            },
	            idCardNum:{
	            	required: "请输入身份证号",
	            	isIdCardNo: "身份证号码格式错误"
	            },
	            score:{
	            	digits:"请输入数字"
	            }
	        }
		});
	    
	});
	
	/**
	* 查询
	*/
	function queryOnClick () {
		//姓名
		var trueName = $("#trueName").val(); 
		//手机号
		var phoneNum = $("#phoneNum").val();
		//身份证号
		var idCardNum = $("#idCardNum").val();
		//注册时间
		var createBegDatetime = $("#createBegDatetime").val();
		var createEndDatetime = $("#createEndDatetime").val();
		//用户单位
		unitCode = $("#unitCode_search").val();
		if(unitCode == '') {
			unitName = $("#select2-chosen-1").text();
			unitCode = unitMap[unitName];
		};
		if(unitCode==undefined){unitCode = ''};
			
		$("#clueProviderInfoTable").jqGrid('setGridParam',{ 
            url:"${ctx}/ccp/clueProvider/query.html",
            type : "POST", 
            postData:{
            			'trueName' : trueName,
            			'phoneNum' : phoneNum,
            			'idCardNum' : idCardNum,
            			'createBegDatetime' : createBegDatetime,
            		 	'createEndDatetime' : createEndDatetime,
            		 	'unitCode' : unitCode
            		 }, //发送数据 
            page:1 
        }).trigger("reloadGrid"); //重新载入
	}
	
	/**
	* 查看手机用户信息
	*/
	function showDetails (id) {
		$.ajax({
			async : false,
			type : "POST",
			datatype:"json", //数据来源，本地数据
			url: "${ctx}/ccp/clueProvider/view.html",
            data: {'id' : id},
            success: function(data){
            	if (data.flag == 'success')  {
              		//填充表单
              		$("#trueName_view").text(data.clueProviderInfo.trueName);
              		$("#phoneNum_view").text(data.clueProviderInfo.phoneNum);
              		$("#idCardNum_view").text(data.clueProviderInfo.idCardNum);
              		$("#createDate_view").text(data.clueProviderInfo.createDate);
              		$("#score_view").text(data.clueProviderInfo.score);
              		$("#unitCode").text(data.clueProviderInfo.unitName);
              		
              		if (data.clueProviderInfo.openFlag == 1) {
              			if(!$("#openFlag_view").is(':checked')) {
              				$("#openFlag_view").bootstrapSwitch('toggleState');
              			} 
              		} else {
              			if($("#openFlag_view").is(':checked')) {
              				$("#openFlag_view").bootstrapSwitch('toggleState');
              			}
              		}
              		$("img[name='frontPhoto']").attr("src","data:image/png;base64,"+data.clueProviderInfo.idCardPhotoFrontStr);
              		$("img[name='backPhoto']").attr("src","data:image/png;base64,"+data.clueProviderInfo.idCardPhotoBackStr);
              		$("a[name='backPhotoBig']").attr("href","data:image/png;base64,"+data.clueProviderInfo.idCardPhotoBackStr);
              	} else if (data.flag == 'false'){
              		$("a[id='view']").attr("href","");
              		alert(data.content);
              	}
            }
		});
	}
	
	/**
	* 打开用户编辑弹窗时，加载用户信息
	*/
	function showEditLabel (id) {
		//隐藏提示信息
		$("div[id='editUser']").find("label[class='error']").hide();
		$("#providerId").val(id);
		
		$.ajax({
			async : false,
			type : "POST",
			datatype:"json", //数据来源，本地数据
			url: "${ctx}/ccp/clueProvider/view.html",
            data: {'id' : id},
            success: function(data){
            	if (data.flag == 'success')  {
              		//填充表单
              		$("#trueName_edit").val(data.clueProviderInfo.trueName);
              		$("#phoneNum_edit").val(data.clueProviderInfo.phoneNum);
              		$("#idCardNum_edit").val(data.clueProviderInfo.idCardNum);
              		$("#createDate_edit").val(data.clueProviderInfo.createDate);
              		$("#score_edit").val(data.clueProviderInfo.score);
              		//字典类型用户单位
              		selectByUnitCode(data.clueProviderInfo.unitCode,data.clueProviderInfo.unitName);
              		
              		if (data.clueProviderInfo.openFlag == 1) {
              			if(!$("#openFlag_edit").is(':checked')) {
              				$("#openFlag_edit").bootstrapSwitch('toggleState');
              			} 
              		} else {
              			if($("#openFlag_edit").is(':checked')) {
              				$("#openFlag_edit").bootstrapSwitch('toggleState');
              			}
              		}
              		$("img[name='frontPhoto']").attr("src","data:image/png;base64,"+data.clueProviderInfo.idCardPhotoFrontStr);
              		$("img[name='backPhoto']").attr("src","data:image/png;base64,"+data.clueProviderInfo.idCardPhotoBackStr);
              		$("a[name='frontPhotoBig']").attr("href","data:image/png;base64,"+data.clueProviderInfo.idCardPhotoFrontStr);
              		$("a[name='backPhotoBig']").attr("href","data:image/png;base64,"+data.clueProviderInfo.idCardPhotoBackStr);
              	} else if (data.flag == 'false'){
              		$("a[id='edit']").attr("href","");
              		alert(data.content);
              	}
            }
		});
	}
	function selectByUnitCode (id,name) {
		$("#unitCode_edit").find("option[value='"+id+"']").attr("selected",true);
		$("#select2-chosen-2").text(name);
	}
	/**
	* 编辑
	*/
	function edit () {
		$("#unitCode_edit").attr("name","unitCode");
		$("#unitCode_edit_hidden").attr("name","");
		unitCode = $("#unitCode_edit").val();
		if(unitCode == '') {
			 unitName = $("#select2-chosen-3").text();
			 unitCode = unitMap[unitName];
			 if(!unitCode=='')
			 {
			 	$("#unitCode_edit").attr("name","");
			 	$("#unitCode_edit_hidden").attr("name","unitCode");
			 	$("#unitCode_edit_hidden").val(unitCode);
			 }
		};
		if(unitCode==undefined){unitCode = ''};
		$("#providerEditForm").submit();
	}
	
	/**
	* 打开删除弹窗时传值
	*/
	function showDelLabel(id) { 
		$("#delId").val(id);
	}
	
	/**
	* 删除
	*/
	function deleteById () {
		var id = $("#delId").val();
        $.ajax({
			type : "POST",
			url: "${ctx}/ccp/clueProvider/deleteById.html",
            data: {'id' : id},
            success: function(data){
            	if (data.flag == 'true')  {
            		//关闭弹窗，刷新页面
	            	$("#del_button_return").click();
	            	$("#clueProviderInfoTable").jqGrid('setGridParam',{ 
			            url:"${ctx}/ccp/clueProvider/query.html", 
			            postData:{
			            		 }, //发送数据 
			            page:1 
			        }).trigger("reloadGrid"); //重新载入 
            	} else if (data.flag == 'false'){
					alert(data.content);
				}
            }
		});
	}
</script>

<script>
	//身份证号码的验证规则
    function isIdCardNo(num){ 
    　   //if (isNaN(num)) {alert("输入的不是数字！"); return false;} 
    　　 var len = num.length, re; 
    　　 if (len == 15) 
    　　 re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{2})(\w)$/); 
    　　 else if (len == 18) 
    　　 re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/); 
    　　 else {
            //alert("输入的数字位数不对。"); 
            return false;
        } 
    　　 var a = num.match(re); 
    　　 if (a != null) 
    　　 { 
    　　 if (len==15) 
    　　 { 
    　　 var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]); 
    　　 var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
    　　 } 
    　　 else 
    　　 { 
    　　 var D = new Date(a[3]+"/"+a[4]+"/"+a[5]); 
    　　 var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
    　　 } 
    　　 if (!B) {
            //alert("输入的身份证号 "+ a[0] +" 里出生日期不对。"); 
            return false;
        } 
    　　 } 
    　　 if(!re.test(num)){
            //alert("身份证最后一位只能是数字和字母。");
            return false;
        }
    　　 return true; 
    }
</script 
