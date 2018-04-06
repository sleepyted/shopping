<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>

×
<div class="jumbotron">
	<label for="">用户名</label>
	<h2 class="display-3">${user.username}</h2>
	<label for="">邮箱</label>
	<p class="lead">${user.email}</p>
	<hr class="my-4">
	<label for="">电话</label>
	<p>${user.tel}</p>
	<label for="">性别</label>
	<c:if test="${user.gender == 1}">
		<p>男</p>
	</c:if>
	<c:if test="${user.gender == 0}">
		<p>女</p>
	</c:if>
	<p class="lead">
		<a class="btn btn-primary btn-md" href="#" role="button">修改</a>
	</p>
</div>

<script>

</script>
<jsp:include page="../footer.jsp"/>