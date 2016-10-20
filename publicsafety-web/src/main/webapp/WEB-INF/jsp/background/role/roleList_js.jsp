<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
	var roleName = '';
	var enable = '';
	
	$(function () {
		
		$("#search").click("click", function() {//绑定查询按扭
		
			roleName = $("#name").val();
			enable = '';
			if ($("#enable").is(':checked')) {
				enable = 1;
			} else {
				enable = 0;
			}
			$("#rolesTable").jqGrid('setGridParam',{ 
	            url:"${ctx}/background/role/query.html", 
	            postData:{'name':roleName, 'enable':enable}, //发送数据 
	            page:1 
	        }).trigger("reloadGrid"); //重新载入 
		});
	
		$("#rolesTable").jqGrid({
	        url:"${ctx}/background/role/query.html",
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
	        colNames:['ID', '序号', '角色名', '角色描述', '状态', '操作'],
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
	            	name:'name', 
	            	index:'name', 
	            	align:'left', 
	            	width:'20%',
	            	sortable:false 
	            },
	            {
	            	name:'description', 
	            	index:'description', 
	            	align:'left', 
	            	width:'35%',
	            	sortable:false
	            },
	            {
	            	name:'enable', 
	            	index:'enable', 
	            	align:'left', 
	            	width:'20%',
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
	            	width:'25%', 
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
         		var ids=$("#rolesTable").jqGrid('getDataIDs');
         		for(var i=0; i<ids.length; i++){
	                 var id=ids[i];   
	                 method = "<a class=\"btn btn-default btn-xs black tooltips margin-right-10\" data-placement=\"left\" data-original-title=\"编辑\" data-toggle=\"modal\" href=\"#editCharacter\" onclick=\"showEditLabel(" + id + ");\"><i class=\"fa fa-edit\"></i></a><a class=\"btn btn-default btn-xs black tooltips\" data-placement=\"left\" data-original-title=\"删除\" data-toggle=\"modal\" href=\"#del\" onclick=\"showDelLabel(" + id + ");\" id=\"showDelButton\"><i class=\"fa fa-trash-o\"></i></a>";
	                 jQuery("#rolesTable").jqGrid('setRowData', ids[i], { operMod: method});
	             }
	             $("a.tooltips").tooltip();
				$("a.popovers").popover();
         	}
	    });
	    
	    $("#reset").click("click", function () { //重置
			$("#rolesForm")[0].reset();
		});
		
		$("#role_edit_form").validate({ //角色编辑校验
			submitHandler: function (form) {
				$(form).ajaxSubmit({//验证新增是否成功
					type : "post",
					dataType:"json",
					success : function(data) {
						if (data.flag = 'true') {
							//关闭弹窗，刷新表格, 重置新增表单
							$("#role_edit_return").click();
							$("#rolesTable").jqGrid('setGridParam',{ 
					            url:"${ctx}/background/role/query.html", 
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
	            name:{
	            	required: true,
	            	minlength:3
	            },
	            roleKey:{
	            	required: true,
	            	maxlength:20
	            },
	            description:{
	            	required: true,
	            	maxlength:250
	            }
	        },
	        messages: {
	        	name: {
		            required: "请填写角色名",
		            rangelength: "用户名不少于三个字符" 
		        },
		        roleKey: {
		            required: "请填写角色键值",
		            rangelength: "角色键值超过20个字符" 
		        },
	            description:{
	            	required: "请填写角色描述",
	            	maxlength:"描述内容不超过250个字符"
	            }
	        }
		});
		
		$("#role_add_form").validate({ //新增编辑校验
			submitHandler: function (form) {
				$(form).ajaxSubmit({//验证新增是否成功
					type : "post",
					dataType:"json",
					success : function(data) {
						if (data.flag == 'true') {
							//关闭弹窗，刷新表格, 重置新增表单
							$("#role_add_return").click();
							$("#rolesTable").jqGrid('setGridParam',{ 
					            url:"${ctx}/background/role/query.html", 
					            postData:{
					            		 }, //发送数据 
					            page:1 
					        }).trigger("reloadGrid"); //重新载入 
					        $("#role_add_form")[0].reset(); // 角色新增表单重置
						} else if (data.flag == 'false'){
							alert(data.content);
						}
					}
				});
			},
			rules:{
	            name:{
	            	required: true,
	            	minlength:3
	            },
	            roleKey:{
	            	required: true,
	            	maxlength:20
	            },
	            description:{
	            	required: true,
	            	maxlength:250
	            }
	        },
	        messages: {
	        	name: {
		            required: "请填写角色名",
		            rangelength: "用户名不少于3个字符" 
		        },
		        roleKey: {
		            required: "请填写角色键值",
		            rangelength: "角色键值超过20个字符" 
		        },
	            description:{
	            	required: "请填写角色描述",
	            	maxlength:"描述内容不超过250个字符"
	            }
	        }
		});
	})
	
	function showEditLabel (id) { //编辑时加载角色信息
		//清楚之前缓存的渲染效果
		var nodes = $('#role1').jstree().get_selected();
		var str = nodes.join(",");
		var arr = str.split(",");
		for (var i=0;i<arr.length ;)   
	    {  
			$('#role1').jstree().deselect_node('#'+arr[i]); 
			i ++ ;
	    }  
	
		$.ajax({
			async : false,
			type : "POST",
			datatype:"json", //数据来源，本地数据
			url: "${ctx}/background/role/findDetailById.html",
            data: {'roleId' : id},
            success: function(obj){
              	if (obj != null) {
              		//填充编辑表单
              		$("#roleId").val(obj.role.id);
              		$("#name_edit").val(obj.role.name);
              		$("#roleKey_edit").val(obj.role.roleKey);
              		$("#description_edit").val(obj.role.description);
              		
              		if (obj.role.enable == 1) {
              			if(!$("#enable_edit").is(':checked')) {
              				$("#enable_edit").bootstrapSwitch('toggleState');
              			} 
              		} else {
              			if($("#enable_edit").is(':checked')) {
              				$("#enable_edit").bootstrapSwitch('toggleState');
              			}
              		}
              		//根据拥有的权限渲染树
              		$(obj.resList).each(function (i,item) {
              			var a = $("#role1").find("[res_id='"+item.id+"']");
              			$('#role1').jstree().select_node(a, false, null);
              		});
              	}
            }
		});
	}
	
	function showDelLabel (id) { //打开角色删除弹窗时传值
		$("#role_del_id").val(id);
	}
	
	function batchDelete() {  //角色批量删除
		$("#role_batch_del").attr("href","#")
		//打开删除对话框，并传值，确认删除
		var a=$("#rolesTable").jqGrid('getGridParam','selarrrow');
		if(a.length == 0) {
			$("#role_batch_del").attr("href","#noSelected");
		} else {
			$("#showDelButton").click();
			var ids = a.join(","); //数组转为字符串
			$("#role_del_id").val(ids);
		}
	}
	
	function deleteRole () {
		var ids = $("#role_del_id").val();
		$.ajax({
			type : "POST",
			url: "${ctx}/background/role/deleteById.html",
            data: {'ids' : ids},
            success: function(obj){
            	if(obj.flag == 'true') {
            		//关闭弹窗，刷新页面
	            	$("#del_button_return").click();
	            	$("#rolesTable").jqGrid('setGridParam',{ 
			            url:"${ctx}/background/role/query.html", 
			            postData:{
			            		 }, //发送数据 
			            page:1 
			        }).trigger("reloadGrid"); //重新载入 
            	} else if (obj.flag == 'false'){
							alert(obj.content);
				}
            }
		});
	}
</script>

<script>
$(function () {
        $("#role").jstree({
			'plugins': ["wholerow", "checkbox", "types"],
            "core" : {
                "themes" : {
                    "responsive": false
                }, 
                // so that create works
                "check_callback" : true,
                'data': 
	                [
	                	${jstreeJson}
	                ]
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-state-warning icon-lg"
                }
            }
        });
        
        $("#role1").jstree({
			'plugins': ["wholerow", "checkbox", "types"],
            "core" : {
                "themes" : {
                    "responsive": false
                }, 
                // so that create works
                "check_callback" : true,
                'data': 
	                [
	                	${jstreeJson}
	                ]
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-state-warning icon-lg"
                }
            }
        });
        
        
        $("#save_btn").click(function(){  //角色保存新增
			var nodes = $('#role').jstree().get_selected();  
			var resourcesIdArr = new Array();
			var num = 0;
			$.each(nodes, function(idx, id) {  
				selectedItem=$('#'+id);
				//alert(selectedItem.attr('res_id')); 
				//alert(selectedItem.attr('res_key')); 
				resourcesIdArr[num] = selectedItem.attr('res_id');
				num ++;
			});
			$("#resources_id_arr_add").val(resourcesIdArr);
			
			$("#role_add_form").submit();
		}); 
		
		$("#update_btn").click(function(){ //角色编辑修改 
			var nodes = $('#role1').jstree().get_selected();  
			var resourcesIdArr = new Array();
			var num = 0;
			$.each(nodes, function(idx, id) {  
				selectedItem=$('#'+id);
				//alert(selectedItem.attr('res_id')); 
				//alert(selectedItem.attr('res_key')); 
				resourcesIdArr[num] = selectedItem.attr('res_id');
				num ++;
			});
			$("#resources_id_arr_edit").val(resourcesIdArr);
			
			$("#role_edit_form").submit();
		});
})

</script>