<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>购物 ${title}</title>
	<meta charset="UTF-8"/>
	<link rel="stylesheet" href="<%= request.getContextPath() + "/static/css/bootstrap.css"%>">
	<link rel="stylesheet" href="<%= request.getContextPath() + "/static/wangEditor.min.css"%>">
	<script src="<%= request.getContextPath() + "/static/js/jquery.js"%>"></script>
	<script src="<%= request.getContextPath() + "/static/js/bootstrap.js"%>"></script>
	<%--<script src="<%= request.getContextPath() + "/static/wangEditor.js"%>"></script>--%>
	<script src="<%= request.getContextPath() + "/static/wangEditor.min.js"%>"></script>
</head>
<style>
	@font-face
	{
		font-family: myfont;
		src: url('/static/fonts/CygnetRound.ttf'),
		url('/static/fonts/CygnetRound.ttf'); /* IE9 */
	}
	.mainPic{
		height:200px;
		max-width: 100%;
	}
	img:hover {
		transform: scale(1.1);
		transition:  all 1s ease;
	}
	body{
		min-height: 100%;
		font-family: myfont, '微软雅黑', Arial, sans-serif;
	}
	.btnImg:hover{
		transform: rotate(360deg);
	}
	/*a:hover {*/
		/*transform: scale(1.1);*/
		/*transition: all 1s ease;*/
	/*}*/
</style>
<body>
<div id="mainContainer" style="height: 100%;min-height: 100%;">
    <nav class="navbar navbar-default col-xs-8 col-xs-offset-2" style="background: orange">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
			        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<c:if test="${admin ==null}">
				<a class="navbar-brand" href="<%=request.getContextPath()%>/home" style="background: white;color: #000;">
					<img src="<%=request.getContextPath()%>/static/img/logo.jpg" alt="logo" style="height: 150%;display: inline-block;">
					<span>Family  家私</span>
			</a>
			</c:if>
			<c:if test="${admin !=null}">
			<a class="navbar-brand" href="<%=request.getContextPath()%>/admin?page=dashboard">管理员管理页面</a>
			</c:if>

		</div>
		<c:if test="${user!=null}">
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
					<%--<li><a href="#">Link</a></li>--%>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					   aria-expanded="false">我的 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<%=request.getContextPath()%>/cart?page=view">购物车</a></li>
						<li><a href="<%=request.getContextPath()%>/order?page=user">订单</a></li>
						<li><a href="<%=request.getContextPath()%>/user?page=account">账号</a></li>
						<li><a href="<%=request.getContextPath()%>/user?page=exit">退出系统</a></li>
					</ul>
				</li>
			</ul>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
	</c:if>
	<c:if test="${user == null && admin == null}">
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
					<%--<li><a href="#">Link</a></li>--%>
				<li>
					<a href="<%=request.getContextPath()%>/user?page=login">登录</a>
				</li>
				<li><a href="<%=request.getContextPath()%>/user?page=register">注册</a>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</c:if>
	<c:if test="${admin != null}">
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
					<%--<li><a href="#">Link</a></li>--%>
				<li><a href="<%=request.getContextPath()%>/user?page=exit">退出</a>
				</li>
			</ul>
		</div>
	</c:if>
</nav>

<div class="container col-xs-8 col-xs-offset-2" style="padding-top: 2%">