<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>
<div container col-xs-12>
	<h5 class="col-xs-6 ">商品名称</h5>
	<h5 class="col-xs-2">数量</h5>
	<h5 class="col-xs-2">状态</h5>
</div>
<hr>
<c:forEach items="${orders}" var="order">
	<div class="container col-xs-12">
		<div class="col-xs-6"></div>
		<div class="col-xs-2">{{price}}</div>
		<div class="col-xs-2">{{num}}</div>
		<a data-goodid="{{goodId}}" href="#" class="btn btn-sm btn-primary delBtn">删除</a>
		<hr>
	</div>
</c:forEach>

<script>
	$(function(){
	})
</script>
<jsp:include page="../footer.jsp"/>