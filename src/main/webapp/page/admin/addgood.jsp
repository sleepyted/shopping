<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>

<form action="<%=request.getContextPath()%>/good?action=add" method="post">
    <div class="form-group">
        <label for="goodname">商品名称</label>
        <input type="text" class="form-control" id="goodname" name="name" placeholder="good name">
    </div>
    <div class="form-group">
        <label for="discription">描述</label>
        <input type="text" class="form-control" id="discription" name="discription" placeholder="discription">
    </div>
    <div class="form-group">
        <label for="price">价格</label>
        <input type="number" class="form-control" id="price" name="price" placeholder="price">
    </div>

    <button id="uploadBtn"class="btn btn-default">上传商品图片</button>
    <input type="hidden" value="" id="picId" name="picId">
<%--<div class="checkbox">--%>
    <%--<label>--%>
    <%--<input type="checkbox">记住我--%>
    <%--</label>--%>
    <%--</div>--%>

    <hr>
    <button type="submit" id="addBtn"  class="btn btn-primary">添加</button>
    <hr>
</form>

<script>
    $(function(){
//        var upBtn = $("#uploadBtn")
//        var picHidden = $("#picId")
//        var submitBtn = $("#addBtn")
//
//        submitBtn.on('click', function(e){
//            e.preventDefault()
//
//        })
    })
</script>
<jsp:include page="../footer.jsp"/>