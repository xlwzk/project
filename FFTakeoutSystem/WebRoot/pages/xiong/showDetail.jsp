<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title></title>
<%--bootstrap相关 --%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%--参数相关 --%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1">
<meta http-equiv="description" content="">
<%--引入外部文件 --%>
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css"
	type="text/css"></link>
<%--与本页相关的css --%>
<link href="<%=path%>/css/font-awesome.min.css" rel="stylesheet"
	type="text/css"></link>
<link href="<%=path%>/css/animate.min.css" rel="stylesheet"
	type="text/css"></link>
<link href="<%=path%>/css/lightbox.css" rel="stylesheet" type="text/css"></link>
<link href="<%=path%>/css/responsive.css" rel="stylesheet"
	type="text/css"></link>
<%--通用样式 --%>
<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css"></link>
<link href="<%=path%>/css/page.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="<%=path%>/js/jquery-1.12.0.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/lightbox.min.js"></script>
</head>

<body>
	<table width="50%" align="center">
		<tr>
			<td colspan="3" align="center">&nbsp;${mapdetail.username } <c:if
					test="${mapdetail.gender=='女'}">
                  &nbsp;女士&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               </c:if> <c:if test="${mapdetail.gender=='男'}">
                  &nbsp;先生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               </c:if> ${mapdetail.tel }</td>
		</tr>
		<tr>
			<td colspan="3">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				${mapdetail.address }</td>
		</tr>
		<tr>
			<td colspan="3" align="center">&nbsp;&nbsp;&nbsp;&nbsp;支付方式&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;在线支付&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" align="center">&nbsp;&nbsp;&nbsp;&nbsp;${mapdetail.rtname
				}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;由商家配送&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<c:forEach items="${requestScope.list}" var="car">
			<tr>
				<td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${car.muname
					}</td>
				<td align="center">x<span style="color:red">${car.ocount}</span>
				</td>
				<td align="center"><span style="color:red">￥${car.ocount*car.muprice}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="center">&nbsp;&nbsp;&nbsp;&nbsp;待支付：<span
				style="color:red;font-size:22px">￥${sumMoney }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span style="color:red;font-size:22px"><a
					href="shwx!getShop.action?sid=${mapdetail.rtid }&money=${sumMoney }"
					style="text-decoration: none">提交订单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</span>
			</td>
		</tr>
	</table>
</body>
</html>
