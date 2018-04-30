<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>
<form class="form-horizontal" action="<%=request.getContextPath()%>/order" method="post">
	<fieldset>
		<legend>填写收货信息</legend>
		<div class="form-group">
			<label for="name" class="col-xs-2 control-label">收货人</label>
			<div class="col-lg-10">
				<input type="text" class="form-control" id="name" placeholder="name" name="name" required>
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail" class="col-xs-2 control-label">电话</label>
			<div class="col-lg-10">
				<input type="number" class="form-control" id="inputEmail" placeholder="tel" name="tel" maxlength="11" required>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword" class="col-xs-2 control-label">地址</label>
			<div class="col-lg-10">
				<input type="text" class="form-control" id="inputPassword" placeholder="address" name="addr" required>
			</div>
		</div>
		<div class="col-xs-10 col-xs-offset-1 col-md-4 col-md-offset-4">
			<p>扫描二维码付款</p>
			<hr>
			<img src="<%=request.getContextPath()%>/down?imgId=1" alt="Qrcode" style="width: 100%;">
		</div>
		<div class="form-group">
			<div class="col-lg-10 col-lg-offset-2">
				<hr>
				<button type="submit" class="btn btn-primary btn-block">购买</button>
			</div>
		</div>
	</fieldset>
</form>

<script>
	$(function(){
	})
</script>
<jsp:include page="../footer.jsp"/>