<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>
<div class="container col-xs-2" id="leftbar">
	<div class="list-group">
		<a href="<%=request.getContextPath()%>/good?page=partView&type=0" class="list-group-item ">
			今日折扣
		</a>
		<a href="<%=request.getContextPath()%>/good?page=partView&type=1" class="list-group-item ">
			家具
		</a>
		<a href="<%=request.getContextPath()%>/good?page=partView&type=2" class="list-group-item active">
			厨卫
		</a>
		<a href="<%=request.getContextPath()%>/good?page=partView&type=3" class="list-group-item">
			其他
		</a>
	</div>
</div>
<div class="container col-xs-10" id="main">
	<legend>
		今日折扣
	</legend>
</div>

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

		<c:if test="${user!=null}">
		var goodTemplet = `
			<div class="panel panel-warning col-xs-12 col-md-6 col-lg-6" >
			<div class="panel-heading">
				<h3 class="panel-title">{{name}}</h3>
			</div>
			<div class="panel-body">
			<a data-id={{id}} class="viewGood">
				<img src="<%=request.getContextPath()%>/down?imgId={{picId}}" class='mainPic' title="{{name}}">
				<hr>
				￥{{price}} | 查看详情
			</div></a>
		</div>`
		</c:if>
		<c:if test="${user==null}">
		var goodTemplet = `
			<div class="panel panel-warning col-xs-12 col-md-6 col-lg-6" >
			<div class="panel-heading">
				<h3 class="panel-title">{{name}}</h3>
			</div>
			<div class="panel-body">
				<img src="<%=request.getContextPath()%>/down?imgId={{picId}}" class='mainPic'  title="{{name}}/>
				<hr/>
			</div>
			<div class="panel-footer">
				<p data-id={{id}} class="btn btn-disable btn-block">￥{{price}} | 请先登录</p>
			<div>
		</div>`
		</c:if>
		<%--<p style="max-height:120px;overflow: hidden;">{{discription}}</p>--%>

		//请求数据
		$.ajax({
			url: '<%=request.getContextPath()%>/good?page=part&type=2',
			type: 'get',
			success: function (data) {
				console.log('get good list success')
				var list = JSON.parse(data)
				for (var i = 0; i < list.list.length; i++) {
					//填充数据
					main.append(fillTemplet(goodTemplet, list.list[i]))
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
	})
</script>

<jsp:include page="../footer.jsp"/>
