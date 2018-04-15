<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>
<form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/comment?action=add">
	<fieldset>
		<legend>填写评价</legend>
		<input type="hidden" name="goodId" value="${comment.goodId}">
		<div class="form-group">
			<label for="textArea" class="col-lg-2 control-label"></label>
			<div class="col-lg-10">
				<textarea class="form-control" rows="4" id="textArea" name="content"></textarea>
				<span class="help-block">评价将会显示在商品详情页中.</span>
			</div>
		</div>
		</div>
		<div class="form-group">
			<div class="col-lg-10 col-lg-offset-2">
				<button type="reset" class="btn btn-default">清空</button>
				<button type="submit" class="btn btn-primary">提交</button>
			</div>
		</div>
	</fieldset>
</form>

<script>
	$(function(){
	})
</script>
<jsp:include page="../footer.jsp"/>