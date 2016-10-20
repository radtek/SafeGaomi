<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
	var dicName = '';
	var dicKey = '';
	var dicTypeId = '';
	
	var dicParentKeyArr = new Array(); //用于返回上一级查询时取出查询条件
	var dicLevelArr = new Array(); //同上
	dicParentKeyArr[0] = '';//数组第一个位置存放无父节点的字典值
	dicLevelArr[0] = '';
	var checkDep = 0;//记录下级节点查询深度
	
	$(function () {
	
		//字典类型
		var html='';
		html += '<option value=""></option>';
		$.each(${ja},function(i,item){
			html += '<option value="'+item.dicTypeId+'">'+item.dicTypeName+'</option>';
		});
		$("select[name='dicTypeId']").append(html);
	
		$("#reset").click("click", function () { //查询重置
			var obj = document.getElementById("dic_search_form");
			if (obj == undefined) {
				return;
			}
			var spans =obj.getElementsByTagName("span");
			spans[0].innerHTML = "";
			for (var i = 0; i < obj.elements.length; i++) {
				
				if (obj.elements[i].type == "text") {
				obj.elements[i].value = "";
				}
				else if (obj.elements[i].type == "select-one") {
				obj.elements[i].options[0].selected = true;
				}
			}
		});
	
		$("#search").click("click",function () { //点击查询
			
			
			dicName = $("#dicName_search").val();
			dicKey = $("#dicKey_search").val();
			dicTypeId = $("#select2_sample2").val();
			
			$("#dicTable").jqGrid('setGridParam',{ 
	            url:"${ctx}/background/dic/query.html", 
	            postData:{
	            			'dicName':dicName, 
	            			'dicKey':dicKey,
	            			'dicTypeId':dicTypeId
	            		 }, //发送数据 
	            page:1 
	        }).trigger("reloadGrid"); //重新载入 
		});
		
		$("#dicTable").jqGrid({
	        url:"${ctx}/background/dic/query.html",
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
	        colNames:['ID', '序号', '字典值', '字典代码', '父字典代码值', '字典类型','字典级别', '状态', '操作'],
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
	            	name:'dicName', 
	            	index:'dicName', 
	            	align:'left', 
	            	width:'10%',
	            	sortable:false,
	            	formatter:function(cellvalue, options, rowObject){
						return "<p style='cursor:pointer;' title=\"双击查看下级字典\">"+cellvalue+"</p>";
					}
	            },
	            {
	            	name:'dicKey', 
	            	index:'dicKey', 
	            	align:'left', 
	            	width:'15%',
	            	sortable:false
	            },
	            {
	            	name:'dicParentKey', 
	            	index:'dicParentKey', 
	            	hidden:true
	            },
	            {
	            	name:'dicTypeName', 
	            	index:'dicTypeName', 
	            	align:'left', 
	            	width:'15%',
	            	sortable:false
	            },
	            {
	            	name:'dicLevel', 
	            	index:'dicLevel', 
	            	align:'left', 
	            	width:'8%',
	            	sortable:false
	            },
	            {
	            	name:'status', 
	            	index:'status', 
	            	align:'left', 
	            	width:'10%',
	            	formatter:function(cellvalue, options, rowObject){  
	            		var status = ''; 
	            		if(cellvalue == 1){ //字典状态启用
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
	            	width:'10%',  
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
			    return (!(cm[i].name == 'operMod'));  
			},
			ondblClickRow:function (rowid,iRow,iCol,e) {
		         var rowData = $("#dicTable").jqGrid("getRowData",rowid);//根据上面的id获得本行的所有数据
		         //获得制定列的值 （auditStatus 为colModel的name）
		         checkDep += 1; //查询深度加1,数组0位置为无上级节点, 故从1开始存放
		         dicParentKeyArr[checkDep] = rowData.dicKey; //查询条件放入数组中保存
		         dicLevelArr[checkDep] = parseInt(rowData.dicLevel) + 1;
		         
		         
		         $("#dicTable").jqGrid('setGridParam',{ 
		            url:"${ctx}/background/dic/query.html", 
		            postData:{
		            			'dicParentKey':rowData.dicKey,
		            			'dicLevel':parseInt(rowData.dicLevel)+1,
		            			'dicName':'',
		            			'dicKey':'',
		            			'dicTypeId':''
		            		 }, //发送数据 
		            page:1 
		        }).trigger("reloadGrid"); //重新载入 
			},
	        gridComplete:function()
         	{
         		var ids=$("#dicTable").jqGrid('getDataIDs');
         		for(var i=0; i<ids.length; i++){
	                 var id=ids[i];   
	                 method = "<a class=\"btn btn-default btn-xs black tooltips margin-right-10\" data-placement=\"left\" data-original-title=\"编辑\" data-toggle=\"modal\" href=\"#editDic\" onclick=\"showEditLabel(" + id + ");\"><i class=\"fa fa-edit\"></i></a><a class=\"btn btn-default btn-xs black tooltips\" data-placement=\"left\" data-original-title=\"删除\" data-toggle=\"modal\" href=\"#del\" onclick=\"showDelLabel(" + id + ");\" id=\"showDelButton\"><i class=\"fa fa-trash-o\"></i></a>";
	                 jQuery("#dicTable").jqGrid('setRowData', ids[i], { operMod: method});
	             }
	             $("a.tooltips").tooltip();
		   		$("a.popovers").popover();
         	}
	    });
	    
	    $("#back_parent_dic").click('click',function () { //返回上一级
	    	//如果查询深度为0，即当前为最顶级查询，则提示
	    	if(checkDep == 0){
	    		alert("已返回至最上级");
	    	} else {
	    		checkDep -= 1;//查询深度减1
	    		var dicParentKeyTemp = dicParentKeyArr[checkDep];
	    		var dicLevelTemp = dicLevelArr[checkDep];
	    		dicParentKeyArr.pop(); //数组减一  
	    		dicLevelArr.pop(); //数组减一
	    		
	    		//alert(dicParentKeyTemp+"#####"+dicLevelTemp);
	    		$("#dicTable").jqGrid('setGridParam',{ 
		            url:"${ctx}/background/dic/query.html", 
		            postData:{
		            			'dicParentKey':dicParentKeyTemp,
		            			'dicLevel':dicLevelTemp
		            		 }, //发送数据 
		            page:1 
		        }).trigger("reloadGrid"); //重新载入 
	    	}
			
		});
	    
	    //字典新增表单校验
		$("#dic_add_form").validate({
			submitHandler: function (form) {
				$(form).ajaxSubmit({//验证新增是否成功
					type : "post",
					dataType:"json",
					success : function(data) {
						if (data.flag == 'true') {
							//关闭弹窗，刷新表格, 重置新增表单
							$("#dic_add_return").click();
							$("#dicTable").jqGrid('setGridParam',{ 
					            url:"${ctx}/background/dic/query.html", 
					            postData:{
					            		 }, //发送数据 
					            page:1 
					        }).trigger("reloadGrid"); //重新载入 
					        
					        //$("#dic_add_form")[0].reset();
					        //$("#select2-chosen-3").text("");
					        var obj = document.getElementById("dic_add_form");
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
				dicName:{
	            	required: true,
	            	maxlength: 50
	            },
	            dicKey:{
	            	required: true,
	            	maxlength: 20
	            },
	            dicTypeId:{
	            	required: true
	            },
	            description:{
	            	maxlength:250
	            }
	        },
	        messages: {
	        	dicName:{
	            	required: "请填写字典值",
	            	maxlength: "字典值不超过50个字符"
	            },
	            dicKey:{
	            	required: "请填写字典代码",
	            	maxlength: "字典代码不超过20个字符"
	            },
	            dicTypeId:{
	            	required: "请填写字典类型"
	            },
	            description:{
	            	maxlength:"描述内容不超过250个字符"
	            }
	        }
	        
		});
		
		//字典新增表单校验
		$("#dic_edit_form").validate({
			submitHandler: function (form) {
				$(form).ajaxSubmit({//验证新增是否成功
					type : "post",
					dataType:"json",
					success : function(data) {
						if (data.flag == 'true') {
							//关闭弹窗，刷新表格, 重置新增表单
							$("#dic_edit_return").click();
							$("#dicTable").jqGrid('setGridParam',{ 
					            url:"${ctx}/background/dic/query.html", 
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
				dicName:{
	            	required: true,
	            	maxlength: 50
	            },
	            dicKey:{
	            	required: true,
	            	maxlength: 20
	            },
	            dicTypeId:{
	            	required: true
	            },
	            description:{
	            	maxlength:250
	            }
	        },
	        messages: {
	        	dicName:{
	            	required: "请填写字典值",
	            	maxlength: "字典值不超过50个字符"
	            },
	            dicKey:{
	            	required: "请填写字典代码",
	            	maxlength: "字典代码不超过20个字符"
	            },
	            dicTypeId:{
	            	required: "请填写字典类型"
	            },
	            description:{
	            	maxlength:"描述内容不超过250个字符"
	            }
	        }
	        
		});
	})
	
	 //字典代码新增
    function dicAdd () {
    	$("#dic_add_form").submit();
    }
	
	function showDelLabel(id) { //打开删除弹窗时传值
		$("#dicId").val(id);
	}
	
	function batchDelete () { //批量删除
		$("#dic_batch_del").attr("href","#");
		//打开删除对话框，并传值，确认删除
		var a=$("#dicTable").jqGrid('getGridParam','selarrrow');
		if(a.length == 0) {
			$("#dic_batch_del").attr("href","#noSelected");
		} else {
			$("#showDelButton").click();
			var ids = a.join(","); //数组转为字符串
			$("#dicId").val(ids);
		}
	}
	
	function dicDelete () { //用户删除
		var ids = $("#dicId").val();
		$.ajax({
			type : "POST",
			url: "${ctx}/background/dic/deleteById.html",
            data: {'ids' : ids},
            success: function(obj){
            	if (obj.flag == 'true') {
            		//关闭弹窗，刷新页面
	            	$("#dic_del_return").click();
	            	$("#dicTable").jqGrid('setGridParam',{ 
			            url:"${ctx}/background/dic/query.html", 
			            postData:{
			            		 }, //发送数据 
			            page:1 
			        }).trigger("reloadGrid"); //重新载入 
            	} else if (obj.flag =='false') {
            			alert(data.content);
            	} 
            }
		});
	}
	
	function showEditLabel (id) { //打开字典编辑弹窗时，加载用户信息
		$.ajax({
			async : false,
			type : "POST",
			datatype:"json", //数据来源，本地数据
			url: "${ctx}/background/dic/findDetailById.html",
            data: {'dicId' : id},
            success: function(obj){
              	if (obj != null) {
              		//填充编辑表单
              		$("#dic_edit_id").val(obj.dic.id);
              		$("#dicName_edit").val(obj.dic.dicName);
              		$("#dicKey_edit").val(obj.dic.dicKey);
              		
              		//字典类型
              		selectByDicTypeId(obj.dic.dicTypeId,obj.dic.dicTypeName);
              		
              		if (obj.dic.status == 1) {
              			if(!$("#status_edit").is(':checked')) {
              				$("#status_edit").bootstrapSwitch('toggleState');
              			} 
              		} else {
              			if($("#status_edit").is(':checked')) {
              				$("#status_edit").bootstrapSwitch('toggleState');
              			}
              		}
              		$("#description_edit").val(obj.dic.description);
              	}
            }
		});
	}
	
	function selectByDicTypeId (id,name) {//字典编辑时选中字典类型
		$("#dicTypeId_edit").find("option[value='"+id+"']").attr("selected",true);
		$("#select2-chosen-2").text(name);
	}
	
	function dicEdit() {
		$("#dic_edit_form").submit();
	}
	
	//新增同级字典，如果存在父节点则显示
	function addNewDicBySameLevel () {
		 $("#newDicA").attr("href","#");
		 var id = $("#dicTable").jqGrid('getGridParam','selrow');//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
          if (id == null) {
          	  $("#newDicA").attr("href","#noSelected");
          } else {
          	  $("#newDicA").attr("href","#newDict");
          	  var rowData = $("#dicTable").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
	          //获得制定列的值 （auditStatus 为colModel的name）
	          var dicParentKeyCurrent= rowData.dicParentKey; 
	          var dicLevelCurrent = rowData.dicLevel;
	          if (dicParentKeyCurrent == '') {
					$("#dic_parent_div").hide();
			  } else {
					$("#dic_parent_div").show();
					$("#dicParentKey_add").val(dicParentKeyCurrent);
					$("#dicLevel_add").val(dicLevelCurrent);
			  }
		  }
		  
	}
	
	//新增下级节点，把当前选择的节点作为父节点
	function addNewChildrenDict () {
		  $("#newDicB").attr("href","#");
		  
		  var id = $("#dicTable").jqGrid('getGridParam','selrow');//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
          if (id == null) {
          	 $("#newDicB").attr("href","#noSelected");
          } else {
          	  $("#newDicB").attr("href","#newDict");
	          var rowData = $("#dicTable").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
	          //获得制定列的值 （auditStatus 为colModel的name）
	          var dicParentKeyCurrent= rowData.dicKey;  
	          var dicLevelCurrent = parseInt(rowData.dicLevel) + 1;
	          $("#dic_parent_div").show();
		  	  $("#dicParentKey_add").val(dicParentKeyCurrent);
		  	  $("#dicLevel_add").val(dicLevelCurrent);
          }
	}
</script>

<script>
//字典类型添加
$(function () {
	//字典新增表单校验
		$("#dic_type_add_form").validate({
			submitHandler: function (form) {
				$(form).ajaxSubmit({//验证新增是否成功
					type : "post",
					dataType:"json",
					success : function(data) {
						if (data.flag == 'true') {
							//关闭弹窗，刷新表格, 重置新增表单
							$("#dic_type_add_return").click();
							//刷新字典框
							$.ajax({
								url : "${ctx}/background/dicType/findDicType.html",
								async : false,
								type : "get",
								dataType:"json",
								success : function(data) {
									$("select[name='dicTypeId']").html("");
									$(data).each(function (i,item) {
									var htmlTemp = '';
										htmlTemp += '<option value="'+item.id+'">'+item.dicTypeName+'</option>';
										$("select[name='dicTypeId']").append(htmlTemp);
									});
								}
							});
							
						} else if (data.flag == 'false'){
							alert(data.content);
						}
					}
				});
			},
			rules:{
				dicTypeName:{
	            	required: true,
	            	maxlength: 50
	            },
	            dicTypeKey:{
	            	required: true,
	            	maxlength: 20
	            },
	            description:{
	            	maxlength:250
	            }
	        },
	        messages: {
	        	dicTypeName:{
	            	required: "请填写字典值",
	            	maxlength: "字典值不超过50个字符"
	            },
	            dicTypeKey:{
	            	required: "请填写字典类型代码",
	            	maxlength: "字典代码不超过20个字符"
	            },
	            description:{
	            	maxlength:"描述内容不超过250个字符"
	            }
	        }
	        
		});
});

function dicTypeAdd () {
	$("#dic_type_add_form").submit();
}
</script>