<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<script>
	var accountName;
	var trueName;
	var phoneNumber;
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
	
	$(function () {
	
		//当前菜单高亮显示
		addHightLightByMenu("system");
	
		unit_init (${dicJsonArr});
		$("#unitCode_search").append(html_unit_ali);
		$("#unitCode_search").append(html_unit);
		$("#unitCode_add").append(html_unit);
		$("#unitCode_edit").append(html_unit);
		$("#unitCode_batch_add").append(html_unit);
		
		//拼写角色html
		var html_add = '';
		var html_edit = '';
		var html_batch_add = '';
		var html_search = '';
		//加载角色
		$.each(${ja},function(i,item){
			if (i == 0) {
				html_add += "<label id=\"roleCheck_add"+item.id+"\" name=\"roleCheck_add\" onclick=\"SelectRole('roleCheck_add','"+item.id+"');\" class=\"btn btn-success active\"><input type=\"radio\" checked class=\"toggle\" name=\"roleIds\" value=\""+item.id+"\">"+item.name+"</label>";
				html_edit += "<label id=\"roleCheck_edit"+item.id+"\" name=\"roleCheck_edit\" onclick=\"SelectRole('roleCheck_edit','"+item.id+"');\" class=\"btn btn-success active\"><input type=\"radio\" checked class=\"toggle\" name=\"roleIds\" value=\""+item.id+"\">"+item.name+"</label>";
				html_batch_add += "<label id=\"roleCheck_batch_add"+item.id+"\" name=\"roleCheck_batch_add\" onclick=\"SelectRole('roleCheck_batch_add','"+item.id+"');\" class=\"btn btn-success active\"><input type=\"radio\" checked class=\"toggle\" name=\"roleIds\" value=\""+item.id+"\">"+item.name+"</label>";
			} else {
				html_add += "<label id=\"roleCheck_add"+item.id+"\" name=\"roleCheck_add\" onclick=\"SelectRole('roleCheck_add','"+item.id+"');\" class=\"btn btn btn-default\"><input type=\"radio\" class=\"toggle\" name=\"roleIds\" value=\""+item.id+"\">"+item.name+"</label>";
				html_edit += "<label id=\"roleCheck_edit"+item.id+"\" name=\"roleCheck_edit\" onclick=\"SelectRole('roleCheck_edit','"+item.id+"');\" class=\"btn btn btn-default\"><input type=\"radio\" class=\"toggle\" name=\"roleIds\" value=\""+item.id+"\">"+item.name+"</label>";
				html_batch_add += "<label id=\"roleCheck_batch_add"+item.id+"\" name=\"roleCheck_batch_add\" onclick=\"SelectRole('roleCheck_batch_add','"+item.id+"');\" class=\"btn btn btn-default\"><input type=\"radio\" class=\"toggle\" name=\"roleIds\" value=\""+item.id+"\">"+item.name+"</label>";
			}
			html_search += "<label id=\"roleCheck_search"+item.id+"\" name=\"roleCheck_search\" onclick=\"SelectRole('roleCheck_search','"+item.id+"');\" class=\"btn btn btn-default\"><input type=\"radio\" class=\"toggle\" name=\"roleIds\" value=\""+item.id+"\">"+item.name+"</label>";
		});
		$("#add_role_div").append(html_add);
		$("#edit_role_div").append(html_edit);
		$("#batch_add_role_div").append(html_batch_add);
		$("#search_role_div").append(html_search);
	
		//选择公安用户时，显示警员编号一栏
		$("label[name^='roleCheck']").each(function (i,item) {
			 var obj = $(this).find("input:radio");
			 if($(obj).is(":checked")) {
			 	 if(($(item).html().indexOf("公安")>=0) ) {
				 	$("[id$='policeno_div']").show();
				 } else {
				 	$("[id$='policeno_div']").hide();
				 }
			 }
			
		});
	
		$("#reset").click("click", function () { //查询重置
			$("#unitCode_search").text("");
			$("#unitCode_search").append('<option value=""> </option>');
			$("#unitCode_search").append(html_unit_ali);
			$("#unitCode_search").append(html_unit);
		
			var obj = document.getElementById("accountForm");
			if(obj == undefined) {
				return;
			}
			var spans =obj.getElementsByTagName("span");
			spans[0].innerHTML = "";
			for (var i = 0; i < obj.elements.length; i++) {
				if(obj.elements[i].type == "text") {
					obj.elements[i].value = "";
				} else if (obj.elements[i].type == "select-one") {
					obj.elements[i].options[0].selected = true;
				}
			 }
			 var labels = obj.getElementsByTagName("label");
			 for (var i = 0; i < labels.length; i++) {
			 	if(i >= 4){
			 		if($(labels[i]).find("input:radio").is(':checked')){
			 			$(labels[i]).removeClass("btn btn-success active");
						$(labels[i]).addClass("btn btn btn-default");
						$(labels[i]).find("input:radio").attr("checked",false);
			 		} 
			 	}
			 }
		});
	
		$("#search").click("click",function () { //点击查询
			
			accountName = $("#accountName").val();
			trueName = $("#trueName").val();
			phoneNumber = $("#phoneNumber").val();
			unitCode = $("#unitCode_search").val();
			roleIds = $("#search_role_div").find("input:radio:checked").val();
			if(unitCode == '') {
				 unitName = $("#select2-chosen-1").text();
				 unitCode = unitMap[unitName];
			};
			if(unitCode==undefined){unitCode = ''};
			if(roleIds==undefined){roleIds = ''};
			$("#accountTable").jqGrid('setGridParam',{ 
	            url:"${ctx}/background/account/query.html", 
	            postData:{
	            			'accountName':accountName, 
	            			'trueName':trueName,
	            			'phoneNumber':phoneNumber,
	            		 	'unitCode':unitCode,
	            		 	'roleIds':roleIds
	            		 }, //发送数据 
	            page:1 
	        }).trigger("reloadGrid"); //重新载入 
		});
	
		$("#accountTable").jqGrid({
	        url:"${ctx}/background/account/query.html",
	        ajaxGridOptions:{       
	         type:'post',        
	         async:false,          
	         dataType: 'json'   
	        },
	        datatype:"json", //数据来源，本地数据
	        type:"POST",//提交方式
	        height:'auto',//高度，表格高度。可为数值、百分比或'auto'
	        autowidth:true,//自动宽
	        multiselect: true, // 是否显示复选框 
	        viewrecords: true,  //显示总记录数 
	        colNames:['ID','序号', '用户姓名', '用户账号', '单位', '所属角色', '状态', '操作'],
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
	            	name:'trueName', 
	            	index:'trueName', 
	            	align:'left', 
	            	width:'10%',
	            	sortable:false
	            },
	            {
	            	name:'accountName', 
	            	index:'accountName', 
	            	align:'left', 
	            	width:'15%',
	            	sortable:false
	            },
	            {
	            	name:'unitName', 
	            	index:'unitName', 
	            	align:'left', 
	            	width:'20%',
	            	sortable:false
	            },
	            {
	            	name:'roleName', 
	            	index:'roleName', 
	            	align:'left', 
	            	width:'20%',
	            	sortable:false
	            },
	            {
	            	name:'state', 
	            	index:'state', 
	            	align:'left', 
	            	width:'10%',
	            	formatter:function(cellvalue, options, rowObject){  
	            		var status = ''; 
	            		if(cellvalue == 1){ //角色状态启用
	            			status = "启用";
	            		} else if (cellvalue == 0) { //禁用
	            			status = "禁用";
	            		}
	            		return status;
	            	},
	            	sortable:false
	            },
	            {
	            	name:'operMod',
	            	index:'operMod',
	            	align:"left",
	            	width:'15%',  
	               	sortable:false     
	            }
	        ],
	        jsonReader : {id : "id", repeatitems : false, userdata : "userdata"},  
	        rowNum:10,//每页显示记录数
	        rowList:[10,15,20,25],//用于改变显示行数的下拉列表框的元素数组。
	        pager:$('#gridPager'),
	        beforeSelectRow: function (rowid, e) {  
			    var $myGrid = $(this),  
			        i = $.jgrid.getCellIndex($(e.target).closest('td')[0]),  
			        cm = $myGrid.jqGrid('getGridParam', 'colModel');  
			    return (cm[i].name === 'cb');  
			},  
	        gridComplete:function()
         	{
         		var ids=$("#accountTable").jqGrid('getDataIDs');
         		for(var i=0; i<ids.length; i++){
	                 var id=ids[i];   
	                 method = "<a class=\"btn btn-default btn-xs black tooltips margin-right-10\" data-placement=\"left\" data-original-title=\"编辑\" data-toggle=\"modal\" href=\"#editUser\" onclick=\"showEditLabel(" + id + ");\"><i class=\"fa fa-edit\"></i></a><a class=\"btn btn-default btn-xs black tooltips\" data-placement=\"left\" data-original-title=\"删除\" data-toggle=\"modal\" href=\"#del\" onclick=\"showDelLabel(" + id + ");\" id=\"showDelButton\"><i class=\"fa fa-trash-o\"></i></a>";
	                 jQuery("#accountTable").jqGrid('setRowData', ids[i], { operMod: method});
	             }
         		$("a.tooltips").tooltip();
		   		$("a.popovers").popover();
         	}
	    });
	    
	     //增加验证手机号码、和固定电话的方法  -- start
		 jQuery.validator.addMethod("checktelephone", function(value, element) {
	        var mobileRule = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0-9]|170)\d{8}$/;
	        var telephoneRule = /^\d{3}-\d{8}|\d{4}-\d{7}$/;
		    return this.optional(element) || (mobileRule.test(value)) || (telephoneRule.test(value));
	       }, "电话号码格式错误!");
	
		 jQuery.validator.addMethod("checkXlsxOrXls", function(value, element) {
		    var filepath=$("#fileUploadForAadd").val();
		    //获得上传文件名
		    var fileArr=filepath.split("\\");
		    var fileTArr=fileArr[fileArr.length-1].toLowerCase().split(".");
		    var filetype=fileTArr[fileTArr.length-1];
		    //切割出后缀文件名
		    if(filetype == "xlsx" || filetype == "xls"){
		        return true;
		    }else{
		        return false;
		    }
		}, "文件格式不正确");
	
		//注册表单校验
		$("#accountAddForm").validate({
			submitHandler: function (form) {
				$(form).ajaxSubmit({//验证新增是否成功
					type : "post",
					dataType:"json",
					success : function(data) {
						if (data.flag == 'true')  {
							//关闭弹窗，刷新表格, 重置新增表单
							$("#account_add_return").click();
							$("#accountTable").jqGrid('setGridParam',{ 
					            url:"${ctx}/background/account/query.html", 
					            postData:{
					            		 }, //发送数据 
					            page:1 
					        }).trigger("reloadGrid"); //重新载入 
					        
					        $("#accountAddForm")[0].reset();
					        var obj = document.getElementById("accountAddForm");
							if (obj == undefined) {
								return;
							}
							var spans =obj.getElementsByTagName("span");			
							spans[0].innerHTML = "";
							
							for (var i = 0; i < obj.elements.length; i++) {
								if (obj.elements[i].type == "text") {
									obj.elements[i].value = "";
								}
								if (obj.elements[i].type == "password") {
									obj.elements[i].value = "";
								}
							}
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
	            accountName:{
	            	required: true,
	            	rangelength: [3,20]
	            },
	            unitCode:{
	            	required: true,
	            	maxlength: 20
	            },
	            password:{
	            	required: true,
	            	minlength: 6 
	            },
	            confirm_password: {
	                required: true,
	                minlength: 6,
	                equalTo: "#password_add"
	            },
	            phoneNumber:{
	            	checktelephone:true
	            },
	            policeNumber:{
	            	required: true,
	            	rangelength: [6,6],
	            	digits:true
	            },
	            duty:{
	            	rangelength: [2,20]
	            },
	            description:{
	            	maxlength:250
	            }
	        },
	        messages: {
	        	trueName: {
		            required: "请填写真实姓名",
		            rangelength: "真实姓名必须在2-10个字符之间" 
		        },
		        accountName:{
	            	required: "请填写用户名",
	            	rangelength: "用户名必须在3-20个字符之间",
	            	digits:"请输入数字"
	            },
	            unitCode:{
	            	required: "请填写用户单位",
	            	maxlength: "用户单位不超过20个字符"
	            },
	            password: {
	                required: "请填写密码",
	                minlength: "密码不能小于6个字符"
	            },
	            confirm_password: {
	                required: "请确认密码",
	                minlength: "确认密码不能小于6个字符",
	                equalTo: "两次输入密码不一致"
	            },
	            phoneNumber:{
	            	checktelephone: "联系电话格式错误"
	            },
	            policeNumber:{
	            	required: "请填写警员编号",
	            	rangelength: "警员编号为6位数字",
	            	digits:"请输入数字"
	            },
	            duty:{
	            	rangelength: "职务必须在2-20个字符之间",
	            },
	            description:{
	            	maxlength:"描述内容不超过250个字符"
	            }
	        }
	        
		});
		
		
		//用户批量注册表单校验
		$("#batch_add_form").validate({
			submitHandler: function (form) {
				$(form).ajaxSubmit({//验证新增是否成功
					url:"${ctx}/background/account/batchAdd.html?",  
					type : "post",
					dataType:"json",
					success : function(data) {
						if (data.flag == 'true')  {
							if(data.content==''){
							}else {
								alert(data.content);
							}
							//关闭弹窗，刷新表格, 重置新增表单
							$("#batch_account_add_return").click();
							$("#accountTable").jqGrid('setGridParam',{ 
					            url:"${ctx}/background/account/query.html", 
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
				file:{
	            	required: true,
	            	checkXlsxOrXls: true
	            },
	            unitCode:{
	            	required: true,
	            	maxlength: 20
	            },
	            description:{
	            	maxlength:250
	            }
	        },
	        messages: {
	        	file: {
		            required: "请上传附件",
		            checkXlsxOrXls:"附件格式不正确，应为excel"
		        },
		        unitCode:{
	            	required: "请填写用户单位",
	            	maxlength: "用户单位不超过20个字符"
	            },
	            description:{
	            	maxlength:"描述内容不超过250个字符"
	            }
	        }
		});
		
		//编辑表单校验
		$("#accountEditForm").validate({
			submitHandler: function (form) {
				$(form).ajaxSubmit({//验证新增是否成功
					type : "post",
					dataType:"json",
					success : function(data) {
						if (data.flag == 'true')  {
							//关闭弹窗，刷新表格, 重置新增表单
							$("#account_edit_return").click();
							$("#accountTable").jqGrid('setGridParam',{ 
					            url:"${ctx}/background/account/query.html", 
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
	            accountName:{
	            	required: true,
	            	rangelength: [3,20]
	            },
	            unitCode:{
	            	required: true,
	            	maxlength: 20
	            },
	            password:{
	            	required: true,
	            	minlength: 6 
	            },
	            confirm_password: {
	                required: true,
	                minlength: 6,
	                equalTo: "#password_edit"
	            },
	            phoneNumber:{
	            	checktelephone:true
	            },
	            policeNumber:{
	            	required: true,
	            	rangelength: [6,6],
	            	digits:true
	            },
	            duty:{
	            	rangelength: [2,20]
	            },
	            description:{
	            	maxlength:250
	            }
	        },
	        messages: {
	        	trueName: {
		            required: "请填写真实姓名",
		            rangelength: "真实姓名必须在2-10个字符之间" 
		        },
		        accountName:{
	            	required: "请填写用户名",
	            	rangelength: "用户名必须在3-20个字符之间",
	            	digits:"请输入数字"
	            },
	            unitCode:{
	            	required: "请填写用户单位",
	            	maxlength: "用户单位不超过20个字符"
	            },
	            password: {
	                required: "请填写密码",
	                minlength: "密码不能小于6个字符"
	            },
	            confirm_password: {
	                required: "请确认密码",
	                minlength: "确认密码不能小于6个字符",
	                equalTo: "两次输入密码不一致"
	            },
	            phoneNumber:{
	            	checktelephone: "联系电话格式错误"
	            },
	            policeNumber:{
	            	required: "请填写警员编号",
	            	rangelength: "警员编号为6位数字",
	            	digits:"请输入数字"
	            },
	            duty:{
	            	rangelength: "职务必须在2-20个字符之间",
	            },
	            description:{
	            	maxlength:"描述内容不超过250个字符"
	            }
	        }
	        
		});
	});
	
	function checkAndSave () { //表单验证 用户注册
		$("#accountAddForm").submit();
		var obj = document.getElementById("accountAddForm");
		var spans =obj.getElementsByTagName("span");
		spans[6].innerHTML = "";
	}
	
	function showDelLabel(id) { //打开删除弹窗时传值
		$("#accountId").val(id);
	}
	
	function batchDelete () { //批量删除
		$("#account_batch_del").attr("href","#");
		//打开删除对话框，并传值，确认删除
		var a=$("#accountTable").jqGrid('getGridParam','selarrrow');
		if(a.length == 0) {
			$("#account_batch_del").attr("href","#noSelected")
			return;
		} else {
			$("#showDelButton").click();
			var ids = a.join(","); //数组转为字符串
			$("#accountId").val(ids);
		}
	}
	
	function deleteForOneOrMore () { //用户删除
		var ids = $("#accountId").val();
		$.ajax({
			type : "POST",
			url: "${ctx}/background/account/deleteById.html",
            data: {'ids' : ids},
            success: function(data){
            	if (data.flag == 'true')  {
            		//关闭弹窗，刷新页面
	            	$("#del_button_return").click();
	            	$("#accountTable").jqGrid('setGridParam',{ 
			            url:"${ctx}/background/account/query.html", 
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
	
	function showEditLabel (id) { //打开用户编辑弹窗时，加载用户信息
	
		$("div[id='editUser']").find("label[class='error']").hide();
	
		$.ajax({
			async : false,
			type : "POST",
			datatype:"json", //数据来源，本地数据
			url: "${ctx}/background/account/findDetailById.html",
            data: {'accountId' : id},
            success: function(obj){
              	if (obj != null) {
              		//填充编辑表单
              		$("#accountId").val(obj.account.id);
              		$("#accountName_edit").val(obj.account.accountName);
              		$("#trueName_edit").val(obj.account.trueName);
              		$("#password_edit").val(obj.account.password);
              		$("#confirm_password_edit").val(obj.account.password);
              		
              		$("#phoneNumber_edit").val(obj.account.phoneNumber);
              		$("#policeNumber_edit").val(obj.account.policeNumber);
              		$("#duty_edit").val(obj.account.duty);
              		
              		SelectRole('roleCheck_edit',obj.account.roleIds);
              		
              		//字典类型
              		selectByUnitCode(obj.account.unitCode,obj.account.unitName);
              		
              		if (obj.account.state == 1) {
              			if(!$("#state_edit").is(':checked')) {
              				$("#state_edit").bootstrapSwitch('toggleState');
              			} 
              		} else {
              			if($("#state_edit").is(':checked')) {
              				$("#state_edit").bootstrapSwitch('toggleState');
              			}
              		}
              		$("#description_edit").val(obj.account.description);
              	}
            }
		});
	}
	
	function selectByUnitCode (id,name) {
		$("#unitCode_edit").find("option[value='"+id+"']").attr("selected",true);
		$("#select2-chosen-3").text(name);
	}
	
	function edit () { //用户编辑
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
		$("#accountEditForm").submit();
	}
	
	function SelectRole (name,id) {
		if (name == 'roleCheck_add') {
			$("#select2-chosen-2").html("");
		} else if (name == 'roleCheck_edit') {
			$("#select2-chosen-3").html("");
		} else if (name == 'roleCheck_search') {
			$("#select2-chosen-1").html("");
		}
		
		var obj = name+id;
		$("label[name='"+name+"']").each(function (i,item) {
			if (obj == $(item).attr("id")) {
				if ($(item).html().indexOf("公安") >= 0) {
					$("[id$='policeno_div']").show();
					$(this).parent().parent().parent().siblings().find("select[name='unitCode']").text("");
					$(this).parent().parent().parent().siblings().find("select[name='unitCode']").append('<option value=""> </option>');
					$(this).parent().parent().parent().siblings().find("select[name='unitCode']").append(html_unit);
				}else if ($(item).html().indexOf("管理员") >= 0) {
					$(this).parent().parent().parent().siblings().find("select[name='unitCode']").text("");
					$(this).parent().parent().parent().siblings().find("select[name='unitCode']").append('<option value=""> </option>');
					$(this).parent().parent().parent().siblings().find("select[name='unitCode']").append(html_unit_ali);
					$(this).parent().parent().parent().siblings().find("select[name='unitCode']").append(html_unit);
				}else {
					if($(item).parent().find("label").attr("name") != 'roleCheck_search') {
						$("[id$='policeno_div']").hide();
					}
					$(this).parent().parent().parent().siblings().find("select[name='unitCode']").text("");
					$(this).parent().parent().parent().siblings().find("select[name='unitCode']").append('<option value=""> </option>');
					$(this).parent().parent().parent().siblings().find("select[name='unitCode']").append(html_unit_ali);
				}
				$(item).removeClass("btn btn btn-default");
				$(item).addClass("btn btn-success active");
				$(item).find("input").attr("checked","checked");
			} else {
				$(item).removeClass("btn btn-success active");
				$(item).addClass("btn btn btn-default");
				$(item).find("input").attr("checked",false);
			}
		})
		
	}
	
	//用户批量注册
	function batchAdd() {
			$("#unitCode_batch_add").attr("name","unitCode");
			$("#unitCode_batch_add_hidden").attr("name","");
			unitCode = $("#unitCode_batch_add").val();
			if(unitCode == '') {
				 unitName = $("#select2-chosen-4").text();
				 unitCode = unitMap[unitName];
				 if(!unitCode=='')
				 {
				 	$("#unitCode_batch_add").attr("name","");
				 	$("#unitCode_batch_add_hidden").attr("name","unitCode");
				 	$("#unitCode_batch_add_hidden").val(unitCode);
				 }
			};
			$("#batch_add_form").submit();
	}
	
</script>
