$(function() {
	/* 用户管理 */
	$("#user").click(function() {
		var flag = true;
		var type = '';
		var index = 0;
		$('#mydiv').html("<Table id='mytab'></Table>");
		$("#mytab").datagrid( {
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
			}, {field : 'username',title : '用户名',width : 100,align : 'center',sortable : true,
			} ] ],
			columns : [ [
			  {field : 'pwd',title : '密码',width : 100,align : 'center',sortable : true,		
			},{field : 'tel',title : '电话',width : 100,align : 'center',sortable : true,	
			},{field : 'email',title : '邮件',width : 100,align : 'center',sortable : true,
			},{field : 'address',title : '地址',width : 100,align : 'center',sortable : true,
			},{field : 'realname',title : '真实姓名',width : 100,align : 'center',sortable : true,
			},{field : 'balance',title : '余额',width : 100,align : 'center',sortable : true,
			},{field : 'score',title : '积分',width : 100,align : 'center',sortable : true,
			},{field : 'gender',title : '性别',width : 100,align : 'center',sortable : true,
			},{field : 'regdate',title : '注册时间',width : 100,align : 'center',sortable : true,
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
				}
			} ] ]
		})
	})
})