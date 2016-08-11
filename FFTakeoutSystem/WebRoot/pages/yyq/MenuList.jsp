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
<script type="text/javascript" src="<%=path%>/js/jquery.fly.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/requestAnimationFrame.js"></script>
<script type="text/javascript" src="<%=path%>/js/cartfly.js"></script>
<style type="text/css">
.img60 {
	width: 60px;
	height: 60px;
	background-position: center center;
	background-size: cover;
}
.img20{
	width: 20px;
	height: 20px;
	background-position: center center;
	background-size: cover;
}
</style>
</head>
<body onscroll="setMenuBar(document.documentElement.scrollTop)">
	<a id="infos" class="sr-only" rtid="${sessionScope.shopid}"
		userid="${sessionScope.user.userid}"></a>
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
				<a class="navbar-brand text-success" href="shop!ShopList.action"><span
					class="glyphicon glyphicon-globe text-success">&nbsp;</span>F.Flame 在线订餐系统</a> <a class="navbar-brand visible-xs"
					href="shop!ShopList.action"><span
					class="glyphicon glyphicon-circle-arrow-left text-danger"></span> </a>
			</div>
			<!--导航条实际内容 -->
			<div id="navbar" class="navbar-collapse collapse">
				<!--用于显示用户中心 -->
				<c:choose>
					<c:when test="${not empty sessionScope.user}">
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a id="userid"
								userid="${sessionScope.user.userid}"
								username="${sessionScope.user.username}" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><img src="<%=path%>/${sessionScope.user.photo}" class="img20 img-circle"/>&nbsp;&nbsp;${sessionScope.user.username}
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
					</c:when>
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
	<div class="clearfix" style="margin-top: 60px;"></div>
	<%--主体内容 --%>

	<%--菜单 --%>
	<div id="body" class="container">
		<div class="media panel panel-warning">
			<a class="pull-left" href="#"> <img
				src="<%=path%>/${shopById.rtpic}" width="67" height="67"
				style="margin:0px;padding:0px;" /> </a>
			<div class="media-body panel-heading">
				<h4 class="media-heading">${shopById.rtname}</h4>
				<label class="label label-primary">${shopById.rtaddr}</label>
			</div>
		</div>
		<ul class="nav nav-tabs">
			<li index="0" name="nav" class="active"><a href="#">菜单</a>
			</li>
			<li index="1" name="nav"><a href="#">评论</a>
			</li>
			<li index="2" name="nav"><a href="#">商店</a>
			</li>
		</ul>
		<div id="menu">
			<div class="col-xs-3 col-md-2" id="menubar"
				style="margin: 0px;padding: 0px;position: fixed;">
				<div class="sidebar-item categories">
					<ul class="nav navbar-stacked">
						<c:forEach items="${MenuType}" var="m" varStatus="s">
							<li class=""><c:if test="${s.first}">
									<div class="page-header"
										style="margin:5px 0px 5px 0px;padding: 0px;"></div>
								</c:if> <a href="#type${s.index}"> <span>${m.mutype}</span> </a> <c:if
									test="${s.last}">
									<div class="page-header"
										style="margin:5px 0px 5px 0px;padding: 0px;"></div>
								</c:if></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-xs-10 col-xs-push-2 col-md-10 col-md-push-2"
				style="margin: 0px;padding: 0px;">
				<ul class="">
					<c:set var="tindex" value="0" scope="page" />
					<c:forEach items="${MenuList}" var="m" varStatus="s">
						<%--找page域中有没有当前类 --%>
						<c:if test="${empty pageScope.type or pageScope.type != m.mutype}">
							<a name="type${tindex}" class="sr-only" style="margin-top: -60px;"></a>
							<div class="panel panel-success" style="margin: 4px 0px 4px 0px;">
								<div class="panel-heading" style="height: 20px;line-height: 20px;font-size: 12px;padding:0px 0px 0px 10px;margin:0px;">${m.mutype}</div>
							</div>
							<div class="page-header"
								style="margin:4px 0px 10px 0px;padding: 0px;"></div>
							<c:set var="type" value="${m.mutype}" scope="page" />
							<c:set var="tindex" value="${pageScope.tindex + 1}" scope="page" />
						</c:if>
						<li>
							<div class="media">
								<a href="shop!MenuMessage.action?muid=${m.muid}"><img
									class="img60 pull-left img-rounded" src="<%=path%>/${m.mupic}"
									border="0" /> </a>
								<div class="media-body">
									<a class="pull-left clearfix" style="margin-left: 10px;"
										href="shop!MenuMessage.action?muid=${m.muid}">${m.muname}</a>
									<label class="label label-danger pull-right">${m.mudesc}</label>
									<div style="margin: 0px;padding: 0px;">&nbsp;</div>
									<p class="info" style="margin: 0px 0px 0px 10px;padding: 0px;">月售
										${m.musale} 份</p>
									<h3 class="text-danger pull-left"
										style="margin:0px 0px 0px 10px;">
										<span class="glyphicon glyphicon-yen"></span>:
										<fmt:formatNumber value="${m.muprice}" pattern="0.00" />
									</h3>
									<div class="pull-right">
										<a index="${s.index}" muid="${m.muid}"
											class="glyphicon glyphicon-minus-sign btnMinus <c:if test="${empty m.ocount or m.ocount == 0}">sr-only</c:if>"
											style="cursor:pointer"></a> <a class=""><label id=""
											class="label label-info badge"><c:if test="${not empty m.ocount and m.ocount != 0}">${m.ocount}</c:if></label> </a> <a index="${s.index}" muid="${m.muid}"
											class="glyphicon glyphicon-plus-sign btnAdd"
											style="cursor:pointer"></a>
									</div>
								</div>
							</div></li>
						<div class="page-header clearfix"
							style="margin:10px 0px 10px 0px;padding: 0px;"></div>
						<c:if test="${s.last}">
							<div class="clearfix" style="margin-bottom: 60px;"></div>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
		<%--菜单结束 --%>
	</div>

	<%--底部 --%>
	<nav class="navbar navbar-default navbar-inverse navbar-fixed-bottom">
		<div class="container">
			<!--导航首部 -->
			<div class="navbar-header">
				<i class="visible-xs pull-left end"></i> <a
					class="pull-left visible-xs carts" style="width:60px;"
					role="button" data-placement="top" data-content="加入购物车成功"></a>
				<button id="cart" type="button"
					class="navbar-toggle collapsed pull-left visible-xs cartes"
					data-toggle="collapse" data-target="#cartbar" aria-expanded="false"
					aria-controls="navbar" style="background-color:#444;">
					<span class="sr-only">Toggle navigation</span> <span
						class="glyphicon glyphicon-shopping-cart" style="color: white;">&nbsp;</span>
					<span style="color: white;">Cart</span>
				</button>
				<a href="#" class="navbar-toggle collapsed pull-right span orderNow"
					data-toggle="collapse" data-target="" aria-expanded="false"
					aria-controls="navbar" style="background-color:#444;"> <span
					class="sr-only">Toggle navigation</span> <span
					class="glyphicon glyphicon-cutlery" style="color:#dab074;">&nbsp;</span>立即下单
				</a> <a href="#" type="button" class="navbar-toggle pull-right span"
					id="clearCart" style="background-color:#444;"> <span
					class="glyphicon glyphicon-remove-circle" style="color:#dab074;">&nbsp;</span>清空
				</a>
			</div>
			<!--导航条实际内容 -->
			<div id="footbar" class="navbar-collapse collapse">
				<!--站内导航 -->
				<ul class="nav navbar-nav navbar-left">
					<%--此项只为空一块出来 --%>
					<li><a href="shop!ShopList.action"><span
							class="glyphicon glyphicon-circle-arrow-left">&nbsp;</span>返回</a></li>
					<li><div class="col-md-1"></div></li>
					<li><a href="#" class="orderNow"><span
							class="glyphicon glyphicon-cutlery">&nbsp;</span>立即下单</a></li>
				</ul>
				<!--用于显示购物车 -->
				<ul class="nav navbar-nav navbar-right hidden-xs">
					<i class="hidden-xs pull-left end"></i>
					<a class="pull-left hidden-xs carts" style="width:60px;"
						role="button" data-placement="top" data-content="加入购物车成功"></a>
					<li><a href="#" id="showCart"><span
							class="glyphicon glyphicon-shopping-cart">&nbsp;</span>我的购物车</a></li>
					<li><a href="#" id="clearCart2"><span
							class="glyphicon glyphicon-remove-circle" style="color:#dab074;">&nbsp;</span>清空
					</a>
			</div>
			</li>
			</ul>
		</div>
		<%--商品详细列表 --%>
		<div id="cartbar" class="navbar-collapse collapse sr-only">
			<ul id="cartList" class="nav navbar-nav navbar-left">

			</ul>
		</div>
		</div>
	</nav>
	<%--模态框(小) --%>
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel" style="font-weight:bold;">页面提示</h4>
				</div>
				<div class="modal-body">
					<span id="s-modal-body-addon" class=""></span> <span
						id="s-modal-body" class="h4"></span>
				</div>
				<div class="modal-footer" style="padding:5px;">
					<a href="page!loginPage.action" id="logBtn" type="button" class="btn btn-info sr-only"
						>登陆</a>
					<button type="button" class="btn btn-info"
						data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>
	<%--模态框(中) --%>
	<div class="modal fade bs-example-modal" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2"
						style="font-weight:bold;">页面提示</h4>
				</div>
				<div class="modal-body">
					<div id="m-modal-body" class="h4"></div>
				</div>
				<div class="modal-footer" style="padding:5px;">
					<a href="#" class="btn btn-danger orderNow"><span
						class="glyphicon glyphicon-cutlery">&nbsp;</span>立即下单</a>
					<button type="button" class="btn btn-info" data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
