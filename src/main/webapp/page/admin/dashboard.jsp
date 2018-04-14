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
		goodBtn.on('click',function(e){
			fetchData('good')
		})
		userBtn.on('click',function(e){
			fetchData('user')
		})
		orderBtn.on('click',function(e){
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
				var regx = new RegExp(str,'g')
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
							$(".delGood").each(function(){
								$(this).on('click',function(){
									var goodId = this.dataset.id
									$.ajax({
										url:'<%=request.getContextPath()%>/good?page=del&goodId='+ goodId,
										type:'get',
										success:function(data){
											if(JSON.parse(data).status == 1)
												alert("成功")
											fetchData('good')
										}
									})
								})
							})
							//绑定按钮更新事件
							$(".updateGood").each(function(){
								$(this).on('click',function(){
									var goodId = this.dataset.id
									window.location.href = '<%=request.getContextPath()%>/good?page=update&goodId='+ goodId
								})
							})
							//绑定按钮查看事件
							$(".viewGood").each(function(){
								$(this).on('click',function(){
									var goodId = this.dataset.id
									window.location.href = '<%=request.getContextPath()%>/good?page='+ goodId
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
					break
				case 'order':
					activeList(orderBtn, goodBtn, userBtn);
					break
				default:
					break
			}
		}
	})
</script>
<jsp:include page="../footer.jsp"/>