<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>

		<form>
			<div class="form-group">
				<label for="Email">邮箱</label>
				<input type="email" class="form-control" id="Email" name="email" placeholder="Email">
			</div>
			<div class="form-group">
				<label for="Password">密码</label>
				<input type="password" class="form-control" id="Password" name="password" placeholder="Password">
			</div>
			<%--<div class="checkbox">--%>
				<%--<label>--%>
					<%--<input type="checkbox">记住我--%>
				<%--</label>--%>
			<%--</div>--%>

			<hr>
			<button type="submit" id="LoginBtn"  class="btn btn-primary">登录</button>
			<a href="<%=request.getContextPath()%>/user?page=register"  class="btn btn-default">注册</a>
			<hr>
			<a href="<%=request.getContextPath()%>/admin?page=login">管理员登录</a>
		</form>

<script>
	$(function(){
		var email = $("#Email")
		var password = $("#Password")

		$("#LoginBtn").on("click", function(e){
			e.preventDefault()
			if(email.val().indexOf("@") <= 0){
				alert("邮箱格式错误")
				email.focus()
			}else if(email.val() === ""){
				alert("请填写邮箱")
			}else if (password.val() === ""){
				alert("请填写密码")
			}else {
				$.ajax({
					async: false,
					type:"post",
					url:"<%=request.getContextPath()%>/user?action=login",
					data:{
						email:email.val(),
						password: password.val()
					},
					success:function(data){
						var data = JSON.parse(data)
						console.log(data)
						if(data && data.msg){
							if(data.status == 0){
								alert("登录失败，请检查邮箱、密码")
								window.location.reload()
							}else if(data.status == 1){
								alert("登录成功")
								window.location.href = "/home"
							}
						}
					}
				})
			}
		})
	})
</script>
<br>
<br>
<br>
<br>

<jsp:include page="../footer.jsp"/>