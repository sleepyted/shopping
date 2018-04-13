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
        <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="price">
    </div>
    <div class="form-group">
        <label for="count">库存</label>
        <input type="number" class="form-control" id="count" name="count" placeholder="price">
    </div>

    <a href="#" id="openBtn" class="btn btn-default" data-toggle="modal" data-target="#iconModel">上传商品图片</a>
    <%--<button id="uploadBtn"class="btn btn-default">上传商品图片</button>--%>
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

<form  methord="POST" enctype="multipart/form-data" id="iconForm">
    <!-- icon modify model -->
    <div class="modal" id="iconModel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" id="closeBtn"
                            aria-hidden="true">&times;</button>
                    <h4 class="modal-title">上传图片</h4>
                </div>
                <div class="modal-body">
                    <div id="iconpreview" class="text-center">
                        <img id="icon" class=" img-circle" style="height: 120px; width: 120px"  />
                    </div>
                    <input type="file" name="upImage" id="inputhd"   accept="image/*,">
                </div>
                <div class="modal-footer">
                    <button id="selBtn" type="button" class="btn btn-default btn-sm ">选择图片
                    </button>
                    <button type="button" id="subicon" class="btn btn-primary btn-sm disabled">上传
                    </button>
                </div>
            </div>
        </div>
    </div>
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

//        var upBtn = $("#uploadBtn")
//        upBtn.on("click", function(){
//
//        })

        $("#inputhd").hide();
        $("#selBtn").on('click',function(){
            $("#inputhd").click();
        });
        $("#inputhd").on("change",function(){
            var prevDiv = $("#iconpreview");
            if (this.files && this.files[0])
            {
                var reader = new FileReader();
                reader.onload = function(evt){
                    prevDiv.html('<img  style="height:220px;width:220px;" src="' + evt.target.result + '" />');
                }
                reader.readAsDataURL(this.files[0]);
                $("#subicon").removeClass("disabled");
            }

            $("#subicon").one("click",function(){

                $.ajax("<%=request.getContextPath()%>/file", {
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    data : new FormData($("#iconForm")[0]),
                    type : 'post',
                    success : function(rt) {
                        alert('上传成功')
                        var data = JSON.parse(rt)
                        console.log(data)
                        $("#picId").val(data.msg)
                        $("#openBtn").text("已上传")
                        $("#closeBtn").click()
                    },
                    error:function(){
                        alert("失败")
                    }
                });
            });
        });
    })
</script>
<jsp:include page="../footer.jsp"/>