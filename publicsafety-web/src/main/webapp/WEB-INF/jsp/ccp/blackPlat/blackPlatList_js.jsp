<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<script>
	var offerName;
	var offerPho;
	var informPho;
	var unitCode;
	var remark;
	var unitName;
	var unitCode;
	var createBegDatetime;
	var createEndDatetime;
	
	
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
		
		//拼写角色html
		var html_add = '';
		var html_edit = '';
		var html_search = '';
	
		$("#reset").click("click", function () { //查询重置
			$("#unitCode_search").text("");
			$("#unitCode_search").append('<option value=""> </option>');
			$("#unitCode_search").append(html_unit_ali);
			$("#unitCode_search").append(html_unit);
			
			var obj = document.getElementById("blackPlatForm");
			if(obj == undefined) {
				return;
			}
			
			var spans =obj.getElementsByTagName("span");			
			spans[1].innerHTML = "";
			
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
			
			platNum = $("#platNum").val();
			offerName = $("#offerName").val();
			createBegDatetime = $("#createBegDatetime").val();
			createEndDatetime = $("#createEndDatetime").val();
			unitCode = $("#unitCode_search").val();
			if(unitCode == '') {
				 unitName = $("#select2-chosen-1").text();
				 unitCode = unitMap[unitName];
			};
			if(unitCode==undefined){unitCode = ''};
			$("#blackPlatTable").jqGrid('setGridParam',{ 
	            url:"${ctx}/ccp/blackPlat/query.html", 
	            postData:{
	            			'platNum':platNum, 
	            			'offerName':offerName,
	            			'createBegDatetime':createBegDatetime,
	            		 	'unitCode':unitCode,
	            		 	'createEndDatetime':createEndDatetime
	            		 }, //发送数据 
	            page:1 
	        }).trigger("reloadGrid"); //重新载入 
		});

		$("#blackPlatTable").jqGrid({
	        url:"${ctx}/ccp/blackPlat/query.html",
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
	        colNames:['ID','序号', '车牌号','提供者姓名', '提供者手机号', '提供者所属单位', '通知手机号', '录入时间','备注','比中','操作'],
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
	            	name:'platNum', 
	            	index:'platNum', 
	            	align:'left', 
	            	width:'15%',
	            	sortable:false
	            },
	            {
	            	name:'offerName', 
	            	index:'offerName', 
	            	align:'left', 
	            	width:'15%',
	            	sortable:false
	            },
	            {
	            	name:'offerPho', 
	            	index:'offerPho', 
	            	align:'left', 
	            	width:'18%',
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
	            	name:'informPho', 
	            	index:'informPho', 
	            	align:'left', 
	            	width:'18%',
	            	sortable:false
	            },
	            {
	            	name:'createTime', 
	            	index:'createTime', 
	            	align:'left', 
	            	width:'20%',
	            	formatter:'date',
					formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d '},
	            	sortable:false
	            },
	            {
	            	name:'remark', 
	            	index:'remark', 
	            	align:'left', 
	            	width:'20%',
	            	sortable:false
	            },
	            {
	            	name:'status', 
	            	index:'status', 
	            	align:'left', 
	            	width:'10%',
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
         		var ids=$("#blackPlatTable").jqGrid('getDataIDs');
         		for(var i=0; i<ids.length; i++){
	                 var id=ids[i];   
	                 method = "<a class=\"btn btn-default btn-xs black tooltips\" data-placement=\"left\" data-original-title=\"删除\" data-toggle=\"modal\" href=\"#del\" onclick=\"showDelLabel(" + id + ");\" id=\"showDelButton\"><i class=\"fa fa-trash-o\"></i></a>";
	                 jQuery("#blackPlatTable").jqGrid('setRowData', ids[i], { operMod: method});
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
		
		//注册表单校验
		$("#blackPlatAddForm").validate({
			submitHandler: function (form) {
				$(form).ajaxSubmit({//验证新增是否成功
					type : "post",
					dataType:"json",
					success : function(data) {
						if (data.flag == 'true')  {
							//关闭弹窗，刷新表格, 重置新增表单
							$("#blackPlat_add_return").click();
							$("#blackPlatTable").jqGrid('setGridParam',{ 
					            url:"${ctx}/ccp/blackPlat/query.html", 
					            postData:{
					            		 }, //发送数据 
					            page:1 
					        }).trigger("reloadGrid"); //重新载入 
					        
					        $("#blackPlatAddForm")[0].reset();
					        var obj = document.getElementById("blackPlatAddForm");
							if (obj == undefined) {
								return;
							}
							for (var i = 0; i < obj.elements.length; i++) {
								if (obj.elements[i].type == "text") {
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
				offerPho:{
	            	checktelephone:true
	            },
	            informPho:{
	            	checktelephone:true
	            },
	            unitCode:{
	            	required: true,
	            	maxlength: 20
	            }
	        },
	        messages: {
	        	offerPho:{
	            	checktelephone: "联系电话格式错误"
	            },
	            informPho:{
	            	checktelephone: "联系电话格式错误"
	            },
	            unitCode:{
	            	required: "请填写用户单位",
	            	maxlength: "用户单位不超过20个字符"
	            }
	        }
	        
		});
	});
	
	function checkAndSave () { //表单验证 用户注册
		$("#blackPlatAddForm").submit();
		var obj = document.getElementById("blackPlatAddForm");
		var spans =obj.getElementsByTagName("span");
		spans[5].innerHTML = "";
		
	}
	
	function showDelLabel(id) { //打开删除弹窗时传值
		$("#blackPlatId").val(id);
	}
	
	
	function deleteForOneOrMore () { //用户删除
		var id = $("#blackPlatId").val();
		$.ajax({
			type : "POST",
			url: "${ctx}/ccp/blackPlat/deleteById.html",
            data: {'id' : id},
            success: function(data){
            	if (data.flag == 'true')  {
            		//关闭弹窗，刷新页面
	            	$("#del_button_return").click();
	            	$("#blackPlatTable").jqGrid('setGridParam',{ 
			            url:"${ctx}/ccp/blackPlat/query.html", 
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
	
	
	function selectByUnitCode (id,name) {
		$("#unitCode_edit").find("option[value='"+id+"']").attr("selected",true);
		$("#select2-chosen-3").text(name);
	}
		
</script>
