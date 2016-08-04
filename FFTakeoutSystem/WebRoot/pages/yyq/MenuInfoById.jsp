<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MenuInfoById.jsp' starting page</title>
    
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
   <table align="center" width="600px">
   <tr>
   <td colspan="2"><img src="image/${menuInfo.mupic}"width="600" height="200" border="0" /></td>
   </tr>
   <tr>
   <td colspan="2">${menuInfo.muname}</td>
   </tr>
   <tr>
   <td style="color: red" width="200px">￥${menuInfo.muprice}</td>
   <td align="center"><a href="#">加入购物车</a></td>
   </tr>
   <tr>
   <td colspan="2"><hr/></td>
   </tr>
   </table>
   
   
   <table align="center" width="600px">
   <tr>
   <td colspan="2"><h3>商品评价</h3><hr/></td>
   </tr>
   <s:iterator value="#session.menuMess" var="m">
   <tr>
   <td width="200px">${m.mmdate}</td>
   <td align="right">${m.mm}</td>
   </tr>
   <tr>
   <td colspan="2">${m.mmcontent}<hr/></td>
   </tr>
   </s:iterator>
   </table>
  </body>
</html>
