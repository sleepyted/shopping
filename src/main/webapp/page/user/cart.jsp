<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>
<div>
	<h5 class="col-xs-6 ">商品名称</h5>
	<h5 class="col-xs-2">单价</h5>
	<h5 class="col-xs-2">数量</h5>
</div>
<hr>

	<div class="container col-xs-12" id="main">
	</div>

	<br>
	<legend>
		总价：￥<span id="totalPrice"></span>
	</legend>
	<a href="<%=request.getContextPath()%>/cart?page=buy" id="buyBtn" class="btn btn-info ">结算</a>
	<a href="#" id="clearBtn" class="btn btn-default ">清空购物车</a>

<script>
	$(function () {
		/**
		 * 填充模板
		 * @param templet 模板
		 * @param data 数据
		 * @returns {*} 填充过数据的模板
		 */
		function fillTemplet(templet, data) {
			for (var _key in data) {
				var str = '{{' + _key + '}}'
				var regx = new RegExp(str, 'g')
				templet = templet.replace(regx, data[_key])
			}
			console.log(templet)
			return templet
		}

		var main = $("#main")
		var buyBtn = $("#buyBtn")
		var clearBtn = $("#clearBtn")
		var totalPrice = $("#totalPrice")

		clearBtn.on('click',function(){
			$.ajax({
				url:'<%=request.getContextPath()%>/cart?page=clear',
				type:'get',
				success:function(data){
					alert(JSON.parse(data).msg)
					window.location.reload()
				}
			})
		})

		var itemTemplet = `
			<div class="col-xs-6">{{name}}</div>
			<div class="col-xs-2">{{price}}</div>
			<div class="col-xs-2">{{num}}</div>
			<a data-goodid="{{goodId}}" href="#" class="btn btn-sm btn-primary delBtn">删除</a>
			<hr>
			`

		fetchCart()

		function fetchCart() {
			$.ajax({
				url: '<%=request.getContextPath()%>/cart?page=all',
				type: 'get',
				success: function (data) {
					var list = JSON.parse(data)
					console.log(list)
					if (list.status == 1) {
						var totalPri = 0
						var cartList = list.list
						for (var i = 0; i < cartList.length; i++) {
							var cartItem = cartList[i].good
							cartItem.num = cartList[i].num
							totalPri += cartItem.price * cartItem.num
							cartItem.goodId = cartList[i].good.id
							cartItem = fillTemplet(itemTemplet, cartItem)
							main.append(cartItem)
						}
						totalPrice.text(totalPri)
						//添加删除事件
						//绑定按钮更新事件
						$(".delBtn").each(function () {
							$(this).on('click', function () {
								var goodId = this.dataset.goodid
								$.ajax({
									url: '<%=request.getContextPath()%>/cart?page=del&goodId=' + goodId,
									type: 'get',
									success: function (data) {
										if (JSON.parse(data).status == 1) {
											alert(JSON.parse(data).msg)
											window.location.reload()
										}
									}
								})
							})
						})

					} else {
						totalPrice.text("0")
						buyBtn.addClass("disabled")
						clearBtn.addClass("disabled")
						console.warn(0)
					}

				}
			})

		}
	})
</script>
<jsp:include page="../footer.jsp"/>