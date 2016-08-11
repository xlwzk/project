<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--这个部分用于将页面重置为登录状态 --%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css" />
<link href="<%=path%>/css/login.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/css/main2.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/css/cover.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="<%=path%>/css/fileinput.css" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.12.0.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/fileinput.js"></script>
<script type="text/javascript" src="<%=path%>/js/login-ajax.js"></script>
<title>用户登录</title>
</head>

<body>
	<div class="site-wrapper">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="masthead clearfix">
					<div class="inner">
						<h3 class="masthead-brand">Flowing Flame 订餐系统</h3>
						<nav>
							<ul class="nav masthead-nav">
								<li
									class="<c:if test="${param.purpose eq 'login' or empty param.purpose}">active</c:if>"><a
									href="page!loginPage.action">登录</a></li>
								<li
									class="<c:if test="${param.purpose eq 'regist'}">active</c:if>"><a
									href="page!registPage.action">注册</a></li>
								<li><a href="<%=path%>/index.jsp">主页</a>
								</li>
							</ul>
						</nav>
					</div>
				</div>

				<div class="inner cover">
					<c:choose>
						<%--选择是登陆还是注册 --%>
						<c:when test="${param.purpose eq 'login' or empty param.purpose}">
							<!-- 登录表单 -->
							<form class="form-signin" id="form-login"
								action="user!login.action" method="post">
								<h2 class="form-signin-heading" style="color: white">欢迎用户登录</h2>
								<div class="form-group">
									<label for="input-top" class="sr-only">UserName</label> <input
										type="text" id="input-top" name="user.username"
										class="form-control" value="${cookie.username.value }"
										placeholder="用户名" required autofocus>
								</div>
								<div class="form-group">
									<label for="inputPassword" class="sr-only">Password</label> <input
										type="password" id="inputPassword" name="user.pwd"
										class="form-control" placeholder="密码" required />
								</div>
								<c:if test="${not empty requestScope.msg}">
									<div class="form-group text-center">
										<code id="msg">用户名或密码错误</code>
									</div>
								</c:if>
								<div class="form-group">
									<div class="checkbox">
										<label> <input type="checkbox" name="rememberMe"
											value="true" />&nbsp;&nbsp;记住我</label>
									</div>
								</div>
								<div class="form-group">
									<button class="btn btn-lg btn-danger btn-block" id="loginBtn"
										type="submit">登录</button>
								</div>
							</form>
							<!-- /登录表单 -->
						</c:when>

						<c:otherwise>
							<!-- 注册表单 -->
							<form class="form-regist form-horizontal" id="form-regist"
								method='post' action="user!regist.action" enctype='multipart/form-data'>
								<%--标题 --%>
								<h2 class="form-regist-heading" style="color: white;">Flowing Flame 用户注册</h2>
								<div class="form-group text-center sr-only" id="name_not_pass">
									<div>
										<code>用户已存在</code>
									</div>
								</div>
								<%--用户名 --%>
								<div id="d-top" class="form-group">
									<input type="text" id="input-top1" name="user.username"
										class="form-control" placeholder="用户名(3~16位英文或数字)" required
										autofocus><span id="fb-price"
										class="glyphicon glyphicon-remove form-control-feedback sr-only"
										aria-hidden="true"></span>
								</div>
								<%--密码框 --%>
								<div class="form-group">
									<input type="password" id="inputPassword1-body" name="user.pwd"
										class="form-control" placeholder="密码(6~18位英文或数字)" required />
								</div>
								<div class="form-group text-center sr-only" id="pwd_not_pass">
									<div>
										<code>密码不符合规范</code>
									</div>
								</div>
								<%--确认密码框 --%>
								<div class="form-group">
									<input type="password" id="inputPassword2-body"
										name="password2" class="form-control" placeholder="确认密码"
										required />
								</div>
								<div class="form-group text-center sr-only"
									id="pass_not_confirm">
									<div>
										<code>两次输入的密码不一致</code>
									</div>
								</div>
								<div class="form-group">
									<input id="update" type="file" name="photo"
										class="file" multiple data-min-file-count="1"/>
								</div>
								<div class="form-group text-center sr-only" id="photoupload">
									<div>
										<code>请选择一张图片</code>
									</div>
								</div>
								<%--是否自动登录 --%>
								<div class="form-group">
									<div class="checkbox">
										<label><input type="checkbox" name="login-auto"
											value="true" />&nbsp;&nbsp;自动登录</label>
									</div>
								</div>
								<div class="form-group">
									<button class="btn btn-lg btn-warning btn-block" id="regist"
										type="submit">注册</button>
								</div>
							</form>
							<!-- /注册表单 -->
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>

	<%--底部 --%>
	<div class="container">
		<div class="mastfoot col-md-offset-3">
			<div class="inner">
				<p class="center-block">
					&copy; 2016 Company, Inc. &middot; <a href="#">Blade</a> &middot; <a
						href="#">Pain</a>
				</p>
			</div>
		</div>
	</div>

</body>
</html>