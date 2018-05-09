<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

<jsp:include page="../header.jsp"/>
<c:if test="${fn:length(orders) == 0}">
	<h2>还没有订单~</h2>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
</c:if>
<c:forEach items="${orders}" var="order">
	<div class=" col-xs-12">
		<p>名称：${order.good.name}</p>
		<p>数量：${order.order.num}</p>
		<p>收货人：${order.order.name}</p>
		<p>地址：${order.order.addr}</p>
		<p>电话：${order.order.tel}</p>
		<p>状态:<span class="statusSpan">${order.order.status}</span></p>
		<c:if test="${order.order.status != '3'}">
			<a data-id="" href="<%=request.getContextPath()%>/order?page=confirm&orderId=${order.order.id}" class="btn btn-sm btn-primary delBtn">确认收货</a>
		</c:if>
		<hr>
	</div>
</c:forEach>

<script>
	$(function () {
		var statusSpan = $(".statusSpan")
		statusSpan.each(function () {
			var status = $(this).text()
			if (status == 0) {
				$(this).text("已付款")
			} else if (status == 1) {
				$(this).text("商家已确认")
			} else if (status == 2) {
				$(this).text("已发货")
			} else if (status == 3) {
				$(this).text("已完成")
			}
		})
	})
</script>
<jsp:include page="../footer.jsp"/>