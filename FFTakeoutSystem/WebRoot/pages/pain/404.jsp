<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
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
<link rel="stylesheet" href="<%=path%>/css/main.css"></link>
<link rel="stylesheet" href="<%=path%>/css/error.css" type="text/css"></link>
<script type="text/javascript" src="<%=path%>/js/jquery-1.12.0.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
</head>

<body>
	<!--此处是导航条 -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!--导航首部 -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!--品牌logo -->
				<a class="navbar-brand" href="#"><span
					class="glyphicon glyphicon-globe">&nbsp;</span>Flowing Flame 在线订餐系统</a>
			</div>
			<!--导航条实际内容 -->
			<div id="navbar" class="navbar-collapse collapse">
				<!--站内导航 -->
				<ul class="nav navbar-nav navbar-left">
					<%--此项只为空一块出来 --%>
					<li><div class="col-md-1"></div></li>
					<li><a href="<c:url value='/page?method=goodsList' />"><span
							class="glyphicon glyphicon-list">&nbsp;</span>Default</a></li>
				</ul>
				<!--用于显示用户中心 -->
				<c:choose>
					<c:when test="${not empty sessionScope.user}">
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a id="userid"
								userid="${sessionScope.user.userid}"
								username="${sessionScope.user.username}" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">${sessionScope.user.username}
									&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret">&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</a>
								<ul class="dropdown-menu">
									<li><a
										href="<c:url value='/page?method=userCenter&userid=${sessionScope.userid}'/>">用户中心</a>
									</li>
									<c:if test="${sessionScope.user.authority eq 3}">
										<li><a href="page!restaurantMain.action">店铺管理</a>
										</li>
									</c:if>
									<c:if test="${sessionScope.user.authority >= 4}">
										<li><a href="page!adminMain.action">后台管理</a>
										</li>
									</c:if>
									<li role="separator" class="divider"></li>
									<li><a href="user!logout.action">退出登录</a>
									</li>
								</ul>
							</li>
						</ul>
						<%--<ul class="nav navbar-nav navbar-right">
							<li><a
								href="<c:url value='/page?method=shoppingCart&userid=${sessionScope.userid}'/>"><span
									class="glyphicon glyphicon-shopping-cart"></span>我的购物车</a></li>
							<li><div class="col-md-1"></div>
							</li>
						</ul>
					--%></c:when>
					<c:otherwise>
						<!-- 显示登录 -->
						<ul class="nav navbar-nav navbar-right">
							<li><a id="logMsg" href="user!willLog.action">
									点击登录&nbsp;&nbsp;&nbsp;&nbsp;<span
									class="glyphicon glyphicon-log-in"></span> </a></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
	<!--404-->
	<div class="four-four">
		<div class="container">
			<h3>
				40<span class="hlf">4</span>
			</h3>
			<p>数据意外消失在了宇宙的某个角落.....!</p>
			<p>
				<a href="javascript:history.back();" class="btn btn-danger" role="button">返回</a>
			</p>
		</div>
	</div>
	<!--404-->
	<footer class="navbar navbar-fixed-bottom"
		style="background-color:#f1f1f1;">
		<div class="container" style="padding-top:5px;">
			<div class="nvabar-nav navbar-right">
				<ul>
					<li><a href="<c:url value='/page?method=homePage'/>">返回主页</a>
					</li>
				</ul>
			</div>
			<p>
				&copy; 2016 Company, Inc. &middot; <a href="#">Pain</a> &middot; <a
					href="#">DuoMai</a>
			</p>
		</div>
	</footer>
</body>
</html>
