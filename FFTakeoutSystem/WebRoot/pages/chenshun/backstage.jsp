<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'backstage.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/js/ChenShun/jquery-easyui-1.2.6/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/js/ChenShun/jquery-easyui-1.2.6/themes/icon.css">
			<script type="text/javascript" src="<%=path%>/js/ChenShun/jquery-1.5.2.js"></script>
			<script type="text/javascript" src="<%=path%>/js/ChenShun/echarts.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/ChenShun/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/ChenShun/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/ChenShun/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>

		
		<script type="text/javascript" src="<%=path%>/js/ChenShun/script.js"></script>
		<style type="text/css">
span {
	font-size: 15px;
	color: blue;
	span: hover {       text-decoration :       underline;
	color: red;
	cursor: pointer;
}

</style>
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate">

	</head>

	<body class="easyui-layout">

		<div region="west" split="true" title="后台管理" style="width: 200px;">
			<div id="aa" class="easyui-accordion"
				style="width: 300px; height: 200px;">

				<!-- <div title="用户管理" iconCls="icon-save"
					style="overflow: auto; padding: 10px; margin: auto; width: 300px; height: 50px;">

					<ul style="list-style-type: none">
						<li >
							<span onclick='ts_user()'>[我的资料]</span>
						</li>

					</ul>
				</div> -->


				<div title="菜单管理" iconCls="icon-reload" selected="true"
					style="padding: 10px; width: 300px; height: 50px;">
					<ul style="list-style-type: none">
						<li >
							<span onclick='ts_menu()'>[我的菜单]</span>
		
						</li>
						
						     <li >
							<a href="<%=path%>/pages/chenshun/dataview.jsp">查看数据视图</a>
							</li>
							
							   <li >
							 <a href='cs!ExportMenu.action'>[导出电子表]</a>
							</li>
					</ul>
				</div>


				<div title="订单管理" iconCls="icon-reload" selected="true"
					style="padding: 10px; width: 300px; height: 50px;">
					<ul style="list-style-type: none">
						<li >
							<span onclick='ts_order()'>[我的订单]</span>
							
						</li>
						<li>
						
						 <a href='cs!ExportTable.action'>[导出电子表]</a>s
						</li>
						
					</ul>
				</div>





			</div>




		</div>


		<div region="center" id="cen" title="主窗体"
			style="padding: 5px; background: #eee;">
			<!--      条件部分  -->
			<div id="searchDiv" class="easyui-panel" title="查询"
				collapsible="true" collapsed=true iconCls="icon-search">
			</div>
         
			<table id="myTable"></table>
			
          
		</div>
		
		
		
		
		<!-- 特效     ------------------------------------------ -->

		

	</body>

</html>
