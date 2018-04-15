<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>

<form action="<%=request.getContextPath()%>/user?action=update" method="post">
	<div class="form-group">
		<label for="username">用户名</label>
		<input type="username" class="form-control" id="username" name="username" placeholder="username" required>
	</div>
	<div class="form-group">
		<label for="tel">电话</label>
		<input type="tel" class="form-control" id="tel" name="tel" placeholder="tel" required>
	</div>
	<hr>
	<button type="submit" id="LoginBtn"  class="btn btn-primary">修改</button>
	<hr>
</form>

<script>
	$(function(){
	})
</script>
<jsp:include page="../footer.jsp"/>