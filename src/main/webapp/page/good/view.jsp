<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>
<c:if test="${good != null}">
	<div class="col-xs-12">
			<div class="col col-xs-6">
				<h3 class="display-3">${good.name}</h3>
				<hr class="my-4">
				<p>价格：</p>
				<h4>￥${good.price}</h4>
				<hr>

				<form class="form-inline">
					<div class="form-group">
						<label for="exampleInputName2">数量</label>
						<input type="number" class="form-control" id="exampleInputName2" placeholder="剩余库存${good.count}件" >
					</div>
					<a class="btn btn-primary btn-md" href="#" role="button">加入购物车</a>
				</form>
			</div>
			<div class="col col-xs-6" >
				<img style="max-width: 100%;max-height: 400px;" src="<%=request.getContextPath()%>/down?imgId=${good.picId}" alt="商品图片">
			</div>
	</div>
	<div class="col-xs-12">
		<hr>
		<p class="lead">${good.discription}.</p>

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