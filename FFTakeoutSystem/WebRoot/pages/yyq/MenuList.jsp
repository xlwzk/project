<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>菜单</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
h2 {
	margin: 0px;
	padding: 1px
}

;
h4 {
	margin: 0px;
	padding: 1px
}

;
h3 {
	margin: 0px;
	padding: 1px
}
;
</style>
</head>

<body>
	<a href="shop!ShopList.action">返回商店列表</a>
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
	<table width="600px" align="center">

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
					<c:forEach items="${MenuList}" var="m">
						<tr>
							<td width="25%" align="center"><a
								href="shop!MenuMessage.action?muid=${m.muid}"><img
									src="image/${m.mupic}" width="143" height="112" border="0" />
							</a></td>
							<td><h2>
									<a href="shop!MenuMessage.action?muid=${m.muid}">${m.muname}</a>
								</h2>
								<h4>${m.mudesc}</h4>
								<h3 style="color: red">￥${m.muprice}</h3></td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
