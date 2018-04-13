<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>
<c:if test="${good != null}">
			<div class="col col-xs-6">
				<h3 class="display-3">${good.name}</h3>
				<p class="lead">${good.discription}.</p>
				<hr class="my-4">
				<p>价格：</p>
				<h4>￥${good.price}</h4>
				<hr>
				<p class="lead">
					<a class="btn btn-primary btn-md" href="#" role="button">立即购买</a>
				</p>
			</div>
			<div class="col col-xs-6" >
				<img style="width: 100%;" src="<%=request.getContextPath()%>/down?imgId=${good.picId}" alt="商品图片">
			</div>
</c:if>
<c:if test="${good == null}">
	<div class="col col-xs-6">
		<h3 class="display-3">商品竟然消失了∑( 口 ||</h3>
	</div>
	</c:if>
<script>
	$(function () {
	})
</script>
<jsp:include page="../footer.jsp"/>