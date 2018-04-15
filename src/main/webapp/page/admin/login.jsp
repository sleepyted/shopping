<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>

<form>
	<div class="form-group">
		<label for="Email">邮箱</label>
		<input type="email" class="form-control" id="Email" name="email" value="${user.username}" placeholder="Email">
	</div>
	<div class="form-group">
		<label for="Password">密码</label>
		<input type="password" class="form-control" id="Password" name="password" value="${user.tel}" placeholder="Password">
	</div>
	<%--<div class="checkbox">--%>
	<%--<label>--%>
	<%--<input type="checkbox">记住我--%>
	<%--</label>--%>
	<%--</div>--%>

	<hr>
	<button type="submit" id="LoginBtn"  class="btn btn-primary">登录</button>
	<hr>
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
					url:"<%=request.getContextPath()%>/admin?action=login",
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
								window.location.href = data.msg
							}
						}
					}
				})
			}
		})
	})
</script>
<jsp:include page="../footer.jsp"/>