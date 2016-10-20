var UITree = function () {

    var handleSample1 = function () {

        $('#tree_1').jstree({
            "core" : {
                "themes" : {
                    "responsive": false
                }            
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-state-warning icon-lg"
                }
            },
            "plugins": ["types"]
        });

        // handle link clicks in tree nodes(support target="_blank" as well)
        $('#tree_1').on('select_node.jstree', function(e,data) { 
            var link = $('#' + data.selected).find('a');
            if (link.attr("href") != "#" && link.attr("href") != "javascript:;" && link.attr("href") != "") {
                if (link.attr("target") == "_blank") {
                    link.attr("href").target = "_blank";
                }
                document.location.href = link.attr("href");
                return false;
            }
        });
    }

    var handleSample2 = function () {
       
		$("#role").jstree({
			'plugins': ["wholerow", "checkbox", "types"],
            "core" : {
                "themes" : {
                    "responsive": false
                }, 
                // so that create works
                "check_callback" : true,
                'data': [{
                        "text": "系统",
                        "children": [{
                            "text": "我的串并案",
							"icon" : "fa fa-file icon-state-warning",
                            "state": {
                                "selected": false
                            }
                        },
						{
                            "text": "一键搜",
							"icon" : "fa fa-file icon-state-warning",
                            "state": {
                                "selected": true
                            }
                        }, {
                            "text": "委托管理",
                            "icon" : "fa fa-folder icon-state-warning",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {"text": "委托申请", "icon" : "fa fa-file icon-state-warning"},
								{"text": "我的委托", "icon" : "fa fa-file icon-state-warning"}
                            ]
                        }, {
                            "text": "统计",
                            "icon" : "fa fa-folder icon-state-warning",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {"text": "个人委托信息查询", "icon" : "fa fa-file icon-state-warning"},
								{"text": "组织委托信息查询", "icon" : "fa fa-file icon-state-warning"}
                            ]
                        }, {
                            "text": "系统管理",
                            "icon" : "fa fa-folder icon-state-warning",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {"text": "用户管理", "icon" : "fa fa-file icon-state-warning"},
								{"text": "角色管理", "icon" : "fa fa-file icon-state-warning"},
								{"text": "模块管理", "icon" : "fa fa-file icon-state-warning"},
								{"text": "字典管理", "icon" : "fa fa-file icon-state-warning"},
								{"text": "日志管理", "icon" : "fa fa-file icon-state-warning"}
                            ]
                        
                        }]
                    }
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
    }

    var contextualMenuSample = function() {

        $("#model").jstree({
            "core" : {
                "themes" : {
                    "responsive": false
                }, 
                // so that create works
                "check_callback" : true,
                'data': [{
                        "text": "系统",
                        "children": [{
                            "text": "我的串并案",
							"icon" : "fa fa-file icon-state-warning",
                            "state": {
                                "selected": false
                            }
                        },
						{
                            "text": "一键搜",
							"icon" : "fa fa-file icon-state-warning",
                            "state": {
                                "selected": true
                            }
                        }, {
                            "text": "委托管理",
                            "icon" : "fa fa-folder icon-state-warning",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {"text": "委托申请", "icon" : "fa fa-file icon-state-warning"},
								{"text": "我的委托", "icon" : "fa fa-file icon-state-warning"}
                            ]
                        }, {
                            "text": "统计",
                            "icon" : "fa fa-folder icon-state-warning",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {"text": "个人委托信息查询", "icon" : "fa fa-file icon-state-warning"},
								{"text": "组织委托信息查询", "icon" : "fa fa-file icon-state-warning"}
                            ]
                        }, {
                            "text": "系统管理",
                            "icon" : "fa fa-folder icon-state-warning",
                            "state": {
                                "opened": true
                            },
                            "children": [
                                {"text": "用户管理", "icon" : "fa fa-file icon-state-warning"},
								{"text": "角色管理", "icon" : "fa fa-file icon-state-warning"},
								{"text": "模块管理", "icon" : "fa fa-file icon-state-warning"},
								{"text": "字典管理", "icon" : "fa fa-file icon-state-warning"},
								{"text": "日志管理", "icon" : "fa fa-file icon-state-warning"}
                            ]
                        
                        }]
                    }
                ]
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-state-warning icon-lg"
                }
            },
            "state" : { "key" : "demo2" },
            "plugins" : [ "contextmenu", "dnd", "state", "types" ]
        });
    
    }

     var ajaxTreeSample = function() {

        $("#tree_4").jstree({
            "core" : {
                "themes" : {
                    "responsive": false
                }, 
                // so that create works
                "check_callback" : true,
                'data' : {
                    'url' : function (node) {
                      return 'demo/jstree_ajax_data.php';
                    },
                    'data' : function (node) {
                      return { 'parent' : node.id };
                    }
                }
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-state-warning icon-lg"
                }
            },
            "state" : { "key" : "demo3" },
            "plugins" : [ "dnd", "state", "types" ]
        });
    
    }


    return {
        //main function to initiate the module
        init: function () {

            handleSample1();
            handleSample2();
            contextualMenuSample();
            ajaxTreeSample();

        }

    };

}();