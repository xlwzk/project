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
	$("#user").click(function() {
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
			url : "shwk!getUserList.action?v=" + Math.random(),
			fitColumns : true,
			method : 'get',
			striped : true,// 奇偶行变行
			pagination : true,// 显示分页
			pageList : [ 1, 5, 10, 20, 40 ],
			pageSize : 5,// 默认显示5条
			rownumbers : true,// 显示行号
			frozenColumns : [ 
			 [ {field : 'userid',checkbox : true,title : '编号',width : 100,align : 'center'
			}, {field : 'username',title : '用户名',width : 100,align : 'center',sortable : true,editor : {
				type : "validatebox",
				options : {
					required : true,
					missingMessage : '用户名              不能为空'
				}
			}
			} ] ],
			columns : [ [
			  {field : 'pwd',title : '密码',width : 100,align : 'center',sortable : true,
				  editor : {
					type : "validatebox",
					options : {
						required : true,
						missingMessage : '密码不能为空'
					}
				}
			},{field : 'photo',title : '图片',width : 100,align : 'center',sortable : true,
				formatter:function(value,row){
					return "<img src='"+value+"' width='50px' height='50px'>"    	
			},editor:'image'	
				
			},{field : 'tel',title : '电话',width : 100,align : 'center',sortable : true,editor : {
				type : "validatebox",
				options : {
					required : true,
					missingMessage : '电话不能为空'
				}
			}	
			},{field : 'email',title : '邮件',width : 100,align : 'center',sortable : true,editor : {
				type : "validatebox",
				options : {
					required : true,
					missingMessage : '邮件不能为空'
				}
			}
			},{field : 'address',title : '地址',width : 100,align : 'center',sortable : true,editor : {
				type : "validatebox",
				options : {
					required : true,
					missingMessage : '地址不能为空'
				}
			}
			},{field : 'realname',title : '真实姓名',width : 100,align : 'center',sortable : true,editor : {
				type : "validatebox",
				options : {
					required : true,
					missingMessage : '姓名不能为空'
				}
			}
			},{field : 'balance',title : '余额',width : 100,align : 'center',sortable : true,editor : {
				type : "numberbox",
				options : {
					required : true,
				}
			}
			},{field : 'score',title : '积分',width : 100,align : 'center',sortable : true,editor : {
				type : "numberbox",
				options : {
					required : true,
				}
			}
			},{field : 'gender',title : '性别',width : 100,align : 'center',sortable : true,editor : {
				type : "combobox",
				options : {
					data : [ {
						txt : '男',
						val : '男'
					}, {
						txt : '女',
						val : '女'
					}],
					textField : "txt",
					valueField : "val",
					editable : false,
					required : true,
					missingMessage : '权限不能为空'
				}
			}
			},{field : 'regdate',title : '注册时间',width : 100,align : 'center',sortable : true,
				editor : {
				type : "datetimebox",
				options : {
					required : true,
					missingMessage : '注册时间不能为空'
				}
			}
			},{field : 'authority',title : '权限',width : 100,align : 'center',
			formatter : function(value, row, index) {
					if (row.authority == 1) {
						return "<span style='color:green'><b>普通用户</b></span>";
					} else if (row.authority == 2) {
						return "<span style='color:blue'><b>配送人员</b></span>";
					} else if (row.authority == 3) {
						return "<span style='color:gray'><b>店主</b></span>";
					}else if (row.authority == 4) {
						return "<span style='color:gray'><b>普通管理员</b></span>";
					}else if (row.authority == 5) {
						return "<span style='color:gray'><b>系统管理员</b></span>";
					}
				},editor : {
					type : "combobox",
					options : {
						data : [ {
							txt : '普通用户',
							val : '1'
						}, {
							txt : '配送人员',
							val : '2'
						}, {
							txt : '店主',
							val : '3'
						}, {
							txt : '普通管理员',
							val : '4'
						}, {
							txt : '系统管理员',
							val : '5'
						} ],
						textField : "txt",
						valueField : "val",
						editable : false,
						required : true,
						missingMessage : '权限不能为空'
					}
				}
			} ] ]
		})
	})
})
