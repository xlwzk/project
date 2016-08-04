$.extend($.fn.datagrid.defaults.editors, {   
	image: {   
 init: function(container, options){   
			var str="<form action='shwk!inio.action' method='post' enctype='multipart/form-data'><input type='file' name='abc' id='abc'/><input type='submit' value='上传'/><form>";
             var input = $(str).appendTo(container);
             return input;   
          },   
 getValue: function(target){   
             return $(target).val();   
         },   
 setValue: function(target, value){   
             $(target).val(value);   
        },   
 resize: function(target, width){   
 
             var input = $(target);   
             if ($.boxModel == true){   
                 input.width(width - (input.outerWidth() - input.width()));   
             } else {   
                 input.width(width);   
             }   
         }   
     }   
 }); 


$(function() {
	/* 用户管理 */
	$("#rest").click(function() {
		var flag = true;
		var type = '';
		var index = 0;
		$('#mydiv').html("<Table id='mytab'></Table>");
		$("#mytab").datagrid( {
			toolbar : [ {
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					if (flag) {
						flag = false;
						type = 'add';
						$("#mytab").datagrid("appendRow", {});
						index = $("#mytab").datagrid("getRows").length - 1;
						$("#mytab").datagrid("beginEdit", index);
					}
				}
			}, {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					var rows = $("#mytab").datagrid("getSelections");
					if (rows.length == 0) {
						$.messager.show( {
							title : "消息",
							msg : "请选择要删除的行!"
						});
					} else {
						$.each(rows, function(index, r) {
							$.get("shwk!deleUser.action?v=" + Math.random(),{"id":r.userid});
							});
						$.messager.show( {
							showType : 'slide',
							showSpeed : 600,
							msg : '删除成功',
							title : '删除',
							timeout : 1000
						});
						$("#mytab").datagrid("reload")
					}
				}
			}, {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					var rows = $("#mytab").datagrid("getSelections");
					if (rows.length == 0) {
						$.messager.show( {
							showType : 'slide',
							showSpeed : 600,
							title : '提示',
							msg : '请选择要修改的行',
							timeout : 3000
						})
					} else if (rows.length > 1) {
						$.messager.show( {
							showType : 'slide',
							showSpeed : 600,
							title : '提示',
							msg : '只能选择一行修改',
							timeout : 3000
						})
					} else {
						if (flag) {
							falg = false;
							type = 'update';
							r = rows[0];
							index = $("#mytab").datagrid("getRowIndex", r);
							$("#mytab").datagrid("beginEdit", index);
						}
					}
				}
			}, {
				text : "保存",
				iconCls : "icon-save",
				handler : function() {
					$("#mytab").datagrid("endEdit", index);
					flag = true;
				}
			}, {
				text : "取消",
				iconCls : "icon-cancel",
				handler : function() {
					$("#mytab").datagrid("rejectChanges");
					flag = true;
				}
			} ],
			onAfterEdit : function(index, r) {
				if (type == "add") {
					//向数据表中，添加数据
					$.post("shwk!addUser.action?v=" + Math.random(),r,function(data){
						$.messager.show({
							showType:'slide',
							showSpeed:600,
							title:'提示',
							msg:'商品添加成功',
							timeout:3000
						});
						 $("#mytable").datagrid("load");
					});
				} else if (type == "update") {					
					$.post("shwk!updaUser.action?v=" + Math.random(),r,function(data){
						$.messager.show({
							showType:'slide',
							showSpeed:600,
							title:'提示',
							msg:'商品修改成功',
							timeout:3000
						});
						 $("#mytable").datagrid("load");
					});
				}
				},
			idField:'userid',//标识id字段
			url : "shwk!getRestaurantList.action?v=" + Math.random(),
			fitColumns : true,
			method : 'get',
			striped : true,// 奇偶行变行
			pagination : true,// 显示分页
			pageList : [ 1, 5, 10, 20, 40 ],
			pageSize : 5,// 默认显示5条
			rownumbers : true,// 显示行号
			frozenColumns : [ 
			 [ {field : 'rtid',checkbox : true,title : '编号',width : 100,align : 'center'
			}, {field : 'rtname',title : '商店名',width : 100,align : 'center',sortable : true,editor : {
				type : "validatebox",
				options : {
					required : true,
					missingMessage : '商店名 不能为空'
				}
			}
			} ] ],
			columns : [ [
			  {field : 'rtowner',title : '地址',width : 100,align : 'center',sortable : true,
				  editor : {
					type : "validatebox",
					options : {
						required : true,
						missingMessage : '地址不能为空'
					}
				}
			},{field : 'username',title : '店主名字',width : 100,align : 'center',sortable : true,editor : {
				type : "validatebox",
				options : {
					required : true,
					missingMessage : '店主不能为空'
				}
			}	
			},{field : 'rtpic',title : '图片',width : 100,align : 'center',sortable : true,
				formatter:function(value,row){
					return "<img src='"+value+"' width='50px' height='50px'>"    	
			},editor:'image'	
				
			},{field : 'rtcontent',title : '公告',width : 100,align : 'center',sortable : true,editor : {
				type : "validatebox",
				options : {
					
				}
			}	
			},{field : 'rtdate',title : '开店时间',width : 100,align : 'center',sortable : true,editor : {
				type : "datetimebox",
				options : {
					required : true,
					missingMessage : '开店时间不能为空'
				}
			}
			},{field : 'rtonbuz',title : '营业时间',width : 100,align : 'center',sortable : true,editor : {
				type : "validatebox",
				options : {
					required : true,
					missingMessage : '营业时间不能为空'
				}
			}
			},{field : 'rtstatus',title : '营业状态',width : 100,align : 'center',
			formatter : function(value, row, index) {
					if (row.rtstatus == 0) {
						return "<span style='color:green'><b>正在营业</b></span>";
					} else if (row.rtstatus == 1) {
						return "<span style='color:blue'><b>未营业</b></span>";
					} 
				},editor : {
					type : "combobox",
					options : {
						data : [ {
							txt : '正在营业',
							val : '0'
						}, {
							txt : '未营业',
							val : '1'
						} ],
						textField : "txt",
						valueField : "val",
						editable : false,
						required : true,
						missingMessage : '营业状态不能为空'
					}
				}
			} ] ]
		})
	})
})
