<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>
<c:if test="${good != null}">
	<div class="col-xs-12">
		<div class="col col-xs-6">
			<input type="hidden" id="goodId" value="${good.id}">
			<h3 class="display-3">${good.name}</h3>
			<hr class="my-4">
			<p>价格：</p>
			<h4>￥${good.price}</h4>
			<hr>

			<form class="form-inline">
				<div class="form-group">
					<label for="numIpt">购买数量(剩余库存${good.count}件)</label><br>
					<input type="number" class="form-control" id="numIpt" value="1" placeholder=""/>

					<a class="btn btn-sm btn-info" id="addBtn" href="#">+</a>
					<a class="btn btn-sm btn-info" id="minusBtn" href="#">-</a>

				</div>
				<br>
				<br>
				<a class="btn btn-primary btn-md" href="#" id="addCartBtn" role="button">加入购物车</a>
			</form>
		</div>
		<div class="col col-xs-6">
			<img style="max-width: 100%;max-height: 400px;" src="<%=request.getContextPath()%>/down?imgId=${good.picId}"
			     alt="商品图片">
		</div>
	</div>
	<div class="col-xs-12">
		<hr>


		<ul class="nav nav-tabs">
			<li class="active"><a href="#good_discription" data-toggle="tab" aria-expanded="true">商品描述</a></li>
			<li class=""><a href="#good_comment" data-toggle="tab" aria-expanded="false">评价</a></li>
		</ul>

		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade active in" id="good_discription">
				<div class="col-xs-12" style="word-break:break-all;"><p>${good.discription}</p></div>
			</div>
			<div class="tab-pane fade" id="good_comment">
				<c:forEach items="${comments}" var="comment">
					<br>
					<p>用户：${comment.username}</p>
					<p>评价：${comment.content}</p>
					<p>时间：${comment.createDate}</p>
					<hr>
				</c:forEach>
			</div>
		</div>


	</div>
</c:if>
<c:if test="${good == null}">
	<div class="col col-xs-6">
		<h3 class="display-3">商品竟然消失了∑( 口 ||</h3>
	</div>
</c:if>
<script>

	var totalCount = ${good.count}
			$(function () {
				var goodId = $("#goodId").val()
				var addCartBtn = $("#addCartBtn")

				var addBtn = $("#addBtn")
				var minusBtn = $("#minusBtn")
				addBtn.on('click', function (ev) {
					ev.preventDefault()
                    var num = $("#numIpt")
					if(parseInt(num.val()) < totalCount){
					    num.val(parseInt(num.val()) + 1)
					}else {
					    alert("库存不足")
					}
                })
                minusBtn.on('click', function (ev) {
                    ev.preventDefault()
                    var num = $("#numIpt")
                    if(parseInt(num.val()) > 0){
                        num.val(parseInt(num.val())-1)
                    }else {
                    }
                })
				addCartBtn.on('click', function (e) {
					e.preventDefault()
					var numIpt = $("#numIpt").val() ? $("#numIpt").val() : 1
					if (numIpt > totalCount) {
						alert('库存不足！剩余' + totalCount + '件')
					} else {
						$.ajax({
							url: '<%=request.getContextPath()%>/cart?page=add&goodId=' + goodId + '&num=' + numIpt,
							type: 'get',
							success: function (data) {
								var rtn = JSON.parse(data)
								if (rtn.status == 1) {
									alert(rtn.msg)
								} else {
									alert(rtn.msg)
								}
							}
						})
					}
				})

			})
</script>
<jsp:include page="../footer.jsp"/>