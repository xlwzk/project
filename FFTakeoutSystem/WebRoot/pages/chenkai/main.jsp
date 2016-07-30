<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/icon.css">
	
	<script type="text/javascript" src="<%=path %>/js/jquery-1.12.0.js"></script>
	<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=path %>/js/chenkai/manage.js"></script>
	
	
  </head>
  
<body class="easyui-layout">  
    <div region="north" title="North Title" style="height:100px;">
    		<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
  				<tr align="center">
    				<td>
    					<c:if test="${sessionScope.user!=null}">欢迎您,
    					<c:out value="${sessionScope.user.username}"></c:out>
    					</c:if>
    				</td>
  				</tr>
			</table>
	</div>  
      	<div region="west" title="West" style="width:200px;"> 
      		<div class="easyui-accordion" style="width:200px;">  
     		<div id="Title1" title="用户管理" iconCls="icon-save"  style="overflow:auto;padding:10px;">
     			<a id="user">用户管理</a>
     		</div>  
			<div id="Title2" title="店铺管理" iconCls="icon-reload" style="padding:10px;">
     			<a id="rest">店铺管理</a><br/>
     			&nbsp;<br/>
     			<a id="message">店铺评论</a>
     		</div>  
			<div id="Title3" title="菜单管理" style="padding:10px;">
     			<a id="menu">菜单管理</a><br/>
     			&nbsp;<br/>
     			<a id="menumsg">菜单评论</a>
  			</div>   	
  			<div id="Title4" title="订单管理" style="padding:10px;">
     			<a id="order">订单管理</a> 
  			</div>  
  			<div id="Title5" title="支付表管理" style="padding:10px;">
     			<a id="pay">支付表管理</a> 
  			</div>  
  			<div id="Title6" title="积分表管理" style="padding:10px;">
     			<a id="gift">礼品表管理</a><br/>
     			&nbsp;<br/>
     			<a id="giftrecord">兑换表管理</a>  
  			</div>  
  		</div> 
    </div>  
    <div region="center" title="center title" style="padding:5px;background:#eee;">
    	<div id="mydiv"></div>  
    </div>
  </body>  
</html>
