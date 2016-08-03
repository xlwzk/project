<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ShopInfoById.jsp' starting page</title>
    
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
  <table bgcolor="#gray" width="600px" align="center">
  <tr>
     <td width="25%" align="center"><img src="image/${shopById.rtpic}"width="67" height="60" border="0" /></td>
    <td colspan="2"><h2>${shopById.rtname}</h2><br><h5>${shopById.rtaddr}</h5></td>
    </tr>
    <tr>
    <td colspan="3"><h5>公告：${shopById.rtcontent}</h5></td>
    </tr>
    <tr align="center">
    <td width="25%" align="center"><a href="shop!MenuList.action?rtid=${shopById.rtid}">菜单</a></td>
    <td align="center"><a href="shop!ShopMessage.action?rtid=${shopById.rtid}">评论</a></td>
    <td align="center"><a href="shop!shangdian.action?rtid=${shopById.rtid}">商店</a></td>
    </tr>
  </table>
  <table width="600px" align="center">
  <tr> 
  <td><h3>${sd.rtname}</h3><hr/></td>
  </tr>
  <tr>
  <td><h4>公告与活动</h4></td>
  </tr>
  <tr>
  <td>${sd.rtcontent}<hr/></td>
  </tr>
  <tr>
  <td><h4>商家实景</h4></td>
  </tr>
  <tr>
  <td><img src="image/defaults/shop1.jpg" width="67" height="60" border="0" />
  <img src="image/defaults/shop3.jpg" width="67" height="60" border="0" />
  <img src="image/defaults/shop2.jpg" width="67" height="60" border="0" /><hr/>
  </td>
  </tr>
  <tr>
  <td><h4>商店信息</h4></td>
  </tr>
  <tr><td>暂无简介<hr/></td>
  </tr>
  <tr>
  <td>地址：${sd.rtaddr}<hr></td>
  </tr>
  <tr><td>营业时间：${sd.rtonbuz}<hr></td></tr>
  </table>
  </body>
</html>
