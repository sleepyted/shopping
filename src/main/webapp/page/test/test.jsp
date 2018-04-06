<%--
  Created by IntelliJ IDEA.
  Date: 2018/3/25
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>顺序练习</title>
	<link rel="stylesheet" href="<%= request.getContextPath() + "/static/css/bootstrap.css"%>">
	<script src="<%= request.getContextPath() + "/static/js/jquery.js"%>"></script>
	<script src="<%= request.getContextPath() + "/static/js/bootstrap.js"%>"></script>
</head>
<body>
<nav class="navbar navbar-default col-xs-8 col-xs-offset-2">
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
			<a class="navbar-brand" href="#">驾考网</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					   aria-expanded="false">练习<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">随机练习</a></li>
						<li><a href="#">分类练习</a></li>
						<%--<li role="separator" class="divider"></li>--%>
						<%--<li><a href="#">Separated link</a></li>--%>
						<%--<li role="separator" class="divider"></li>--%>
						<%--<li><a href="#">One more separated link</a></li>--%>
					</ul>
				</li>
				<li><a href="#">模拟考试 <span class="sr-only">(current)</span></a></li>
				<%--<li><a href="#">Link</a></li>--%>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<%--<li><a href="#">Link</a></li>--%>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					   aria-expanded="false">我的 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">标记题库</a></li>
						<li><a href="#">错题库</a></li>
						<li><a href="#">账号</a></li>
						<li><a href="#">退出系统</a></li>
					</ul>
				</li>
			</ul>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>
	<div class="container col-md-6 col-md-offset-3" style="height: 100%;">
		<br>
		<br>
		<br>
		<br>
		<form>
			<legend>
				第1题
			</legend>
			1+1 = ？
			<hr>
			<div class="checkbox">
				<label>
					<input type="checkbox"> 1
				</label>
			</div>
			<div class="checkbox">
				<label>
					<input type="checkbox"> 5
				</label>
			</div>
			<div class="checkbox">
				<label>
					<input type="checkbox">333
				</label>
			</div>
			<div class="checkbox">
				<label>
					<input type="checkbox"> dont konw
				</label>
			</div>
		</form>

		<hr>
		<legend>回答错误！</legend>
		解析：
		<br>
		本题答案为2
	</div>

<script>
	console.log($)
</script>
</body>
</html>
