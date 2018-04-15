<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>

<div class="container col-xs-12">
	<div class="btn-group btn-group-justified" style="margin-top: -5%">
		<a href="#" id="goodBtn" class="btn btn-default active">商品</a>
		<a href="#" id="userBtn" class="btn btn-default">用户</a>
		<a href="#" id="orderBtn" class="btn btn-default">订单</a>
	</div>
	<hr>
	<div id="dataContainer">

	</div>
</div>

<script>
	/**
	 * 设定class ：active
	 * */
	function activeList(activeEle, disable1, disable2) {
		activeEle.addClass('active')
		disable1.removeClass('active')
		disable2.removeClass('active')
	}
	$(function () {
		var goodBtn = $('#goodBtn')
		var userBtn = $('#userBtn')
		var orderBtn = $('#orderBtn')
		goodBtn.on('click', function (e) {
			fetchData('good')
		})
		userBtn.on('click', function (e) {
			fetchData('user')
		})
		orderBtn.on('click', function (e) {
			fetchData('order')
		})


		fetchData('good')

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

		function fetchData(type) {
			var dataContainer = $('#dataContainer')
			//清除数据
			dataContainer.empty()
			//商品信息模板
			var goodTemplet = `
			<div class="panel panel-primary col-xs-12 col-md-6 col-lg-4">
			<div class="panel-heading">
				<h3 class="panel-title">{{name}}</h3>
			</div>
			<div class="panel-body">
				<p style="max-height:120px;overflow: hidden;">{{discription}}</p>
				<hr>
				<a data-id={{id}} class="btn btn-default viewGood">查看</a>
				<a data-id={{id}} class="btn btn-primary updateGood">修改</a>
				<a data-id={{id}} class="btn btn-danger delGood">删除</a>
			</div>
		</div>`

			var userTemplet = `
			<div class="container col-xs-12">
			<p>{{username}}</p>
			<p>{{email}}</p>
			<p>{{tel}}</p>
			<a href="#" data-userid={{id}} class="btn btn-danger btn-sm delUserBtn">删除用户</a>
			<hr>
		</div>`

			switch (type) {
				case 'good':
					activeList(goodBtn, orderBtn, userBtn);
					//请求数据
					$.ajax({
						url: '<%=request.getContextPath()%>/good?page=all',
						type: 'get',
						success: function (data) {
							console.log('get good list success')
							var list = JSON.parse(data)
							dataContainer.append('<a href="<%=request.getContextPath()%>/good?page=add" class="btn btn-info btn-block">新增商品</a><hr>')

							for (var i = 0; i < list.list.length; i++) {
								//填充数据
								dataContainer.append(fillTemplet(goodTemplet, list.list[i]))
							}
							//绑定删除按钮事件
							$(".delGood").each(function () {
								$(this).on('click', function () {
									var goodId = this.dataset.id
									$.ajax({
										url: '<%=request.getContextPath()%>/good?page=del&goodId=' + goodId,
										type: 'get',
										success: function (data) {
											if (JSON.parse(data).status == 1)
												alert("成功")
											fetchData('good')
										}
									})
								})
							})
							//绑定按钮更新事件
							$(".updateGood").each(function () {
								$(this).on('click', function () {
									var goodId = this.dataset.id
									window.location.href = '<%=request.getContextPath()%>/good?page=update&goodId=' + goodId
								})
							})
							//绑定按钮查看事件
							$(".viewGood").each(function () {
								$(this).on('click', function () {
									var goodId = this.dataset.id
									window.location.href = '<%=request.getContextPath()%>/good?page=' + goodId
								})
							})
						},
						error: function () {
							alert('error loading good list')
						}
					})
					break
				case 'user':
					activeList(userBtn, goodBtn, orderBtn);
					$.ajax({
						url: '<%=request.getContextPath()%>/admin?page=allUser',
						type: 'get',
						success: function (data) {
							var data = JSON.parse(data);
							console.log(data)
							if (data.status == 0) {
								for (var i = 0; i < data.list.length; i++) {
									//填充数据
									dataContainer.append(fillTemplet(userTemplet, data.list[i]))
								}
								<%--<%=request.getContextPath()%>/admin?page=delUser&userId={{id}}--%>
								//绑定按钮删除事件
								$(".delUserBtn").each(function () {
									$(this).on('click', function () {
										var userId = this.dataset.userid
										$.ajax({
											url: '<%=request.getContextPath()%>/admin?page=delUser&userId=' + userId,
											type: 'get',
											success: function (data) {
												fetchData("user")
											}
										})
									})
								})
							}
						}
					})
					break
				case 'order':
					activeList(orderBtn, goodBtn, userBtn);
					$.ajax({
						url: '<%=request.getContextPath()%>/admin?page=allOrder',
						type: 'get',
						success: function (data) {
							var data = JSON.parse(data);
							console.log(data)
							if (data.status == 0) {
								for (var i = 0; i < data.list.length; i++) {
									var templet = `<div class="container col-xs-12">
			<p>id：{{id}}</p>
			<p>用户名：{{username}}</p>
			<p>商品名：{{goodname}}</p>
			<p>状态：{{status}}</p>
			<p>收件电话：{{tel}}</p>
			<p>收件地址{{addr}}</p>
			<p>收件人{{getname}}</p>
			<a href="#" data-oid={{id}} data-status='1' class="btn btn-danger btn-sm changeStatus">订单确认1</a>
			<a href="#" data-oid={{id}} data-status='2' class="btn btn-danger btn-sm changeStatus">订单发货2</a>
			<hr>
		</div>`
									templet = templet.replace('{{username}}', data.list[i].user.username)
									templet = templet.replace('{{goodname}}', data.list[i].good.name)
									templet = templet.replace('{{status}}', data.list[i].order.status)
									templet = templet.replace('{{tel}}', data.list[i].order.tel)
									templet = templet.replace('{{addr}}', data.list[i].order.addr)
									templet = templet.replace('{{getname}}', data.list[i].order.name)
									templet = templet.replace('{{id}}', data.list[i].order.id)
									templet = templet.replace('{{id}}', data.list[i].order.id)
									templet = templet.replace('{{id}}', data.list[i].order.id)
									console.warn(templet)

									//填充数据
									dataContainer.append(templet)
								}
								$(".changeStatus").each(function () {
									$(this).on('click', function () {
										var orderId = this.dataset.oid
										var status = this.dataset.status
										$.ajax({
											url: '<%=request.getContextPath()%>/admin?page=changeOrderStatus&orderId=' + orderId +'&status=' + status,
											type: 'get',
											success: function (data) {
												fetchData("order")
											}
										})
									})
								})
							}
						}
					})
					break
				default:
					break
			}
		}
	})
</script>
<jsp:include page="../footer.jsp"/>