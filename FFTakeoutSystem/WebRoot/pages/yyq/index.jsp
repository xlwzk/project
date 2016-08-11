<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
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
   <jsp:forward page="shop!ShopList.action"></jsp:forward>
   	<table align="center">

		<tr>
			<td colspan="3">
				<h5>公告：${shopById.rtcontent}</h5>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<table>
					<c:forEach items="${MenuType}" var="t">
						<tr>
							<td>${t.mutype}</td>
						</tr>
					</c:forEach>
				</table>
			</td>
			<td>
				<table>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="shwx!searchCar.action?sid=${shopById.rtid}"
							style="text-decoration: none">查看我的订单</a>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
							style="color:red;font-size:22px"><a
								href="shwx!getDetail.action?sid=${shopById.rtid}"
								style="text-decoration: none">去结算</a> </span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<table bgcolor="#gray" width="600px" align="center">
		<tr>
			<td width="25%" align="center"><img
				src="image/${shopById.rtpic}" width="67" height="60" border="0" />
			</td>
			<td colspan="2"><h2>${shopById.rtname}</h2> <br>
				<h5>${shopById.rtaddr}</h5></td>
		</tr>
		<tr>
			<td colspan="3"><h5>公告：${shopById.rtcontent}</h5></td>
		</tr>
		<tr align="center">
			<td width="25%" align="center"><a
				href="shop!MenuList.action?rtid=${shopById.rtid}">菜单</a></td>
			<td align="center"><a
				href="shop!ShopMessage.action?rtid=${shopById.rtid}">评论</a></td>
			<td align="center"><a
				href="shop!shangdian.action?rtid=${shopById.rtid}">商店</a></td>
		</tr>
	</table>
  </body>
</html>
