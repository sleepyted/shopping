<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>
<form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/comment?action=add">
	<fieldset>
		<legend>填写评价</legend>
		<input type="hidden" name="goodId" value="${comment.goodId}">
		<div class="form-group">
			<label for="text1" class="col-lg-2 control-label"></label>
			<div class="col-lg-10">
				<textarea id="text1" style="width:100%; height:200px;" name="content"></textarea>
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
//		var E = window.wangEditor
//		var editor = new E('#div1')
//		var $text1 = $('#text1')
//		editor.customConfig.onchange = function (html) {
//			// 监控变化，同步更新到 textarea
//			$text1.val(html)
//		}
//		editor.create()
//		// 初始化 textarea 的值
//		$text1.val(editor.txt.html())
		//init the editor
		var editor = new wangEditor('text1');
		editor.config.menus = [
			'source',
			'|',
			'bold',
			'underline',
			'italic',
			'strikethrough',
			'eraser',
			'forecolor',
			'|',
			'quote',
			'fontfamily',
			'fontsize',
			'unorderlist',
			'orderlist',
			'|',
			'link',
			'unlink',
			'table',
			'|',
			'img',
			'location',
			'insertcode',
			'|',
			'undo',
			'redo',
		];
		editor.create();
	})

</script>
<jsp:include page="../footer.jsp"/>