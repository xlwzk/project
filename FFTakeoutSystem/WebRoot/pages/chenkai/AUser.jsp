<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AddUser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
    		<s:form action="shwk!addUser.action" method='post' enctype='multipart/form-data' >
    			<table align='center' >
    				<tr><td ><s:textfield name="username" label="姓名 "></s:textfield></td></tr>
    				<tr><td ><s:password name="pwd" label="密码"></s:password></td></tr>
    				<tr><td ><s:textfield name="tel" label="电话"></s:textfield></td></tr>
    				<tr><td ><s:textfield name="email" label="邮件 "></s:textfield></td></tr>
    				<tr><td ><s:textfield name="address" label="地址 "></s:textfield></td></tr>
    				<tr><td ><s:textfield name="realname" label="真实姓名 "></s:textfield></td></tr>
    				<tr><td ><s:textfield name="balance" label="余额"></s:textfield></td></tr>
    				<tr><td ><s:radio list="{'男','女'} "  value="'男'"
    					name="f.gender"
    					label="性别"
    				></s:radio></td></tr>
    				<tr><td ><s:select list="#{'1':'普通用户','2':'配送员','3':'店主','4':'普通管理员','5':'系统管理员'}"
    						listKey="key"
    					  	listValue="value"
    						name="authority"
    						label="权限"
    				></s:select></td></tr>			
    				<tr><td ><s:file id='photo' name='photo' label="图片"></s:file></td></tr>
    				
    				<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit value="提交" theme="simple"></s:submit></td>
    				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:reset value="取消" theme="simple"></s:reset>	</td></tr>
    				</table>
    		</s:form>
  </body>
</html>
