<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>文件上传实例——servlet 异步上传文件（ajaxFileupload）</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	function ajaxFileUpload()
	{



		$.ajaxFileUpload
		(
				{
					url:'<%=request.getContextPath()%>/file',//用于文件上传的服务器端请求地址
					secureuri:false,//一般设置为false
					fileElementId:'file',//文件上传空间的id属性 <input type="file" id="file" name="file" />
					dataType: 'text',//返回值类型 一般设置为json
					success: function (data, status) //服务器成功响应处理函数
					{
						//方法一，用后台返回值
						alert(data);//后台out输出的值
						//用ajaxFileUpload来判断成功与否，设置值
						$('#myspan').html("上传成功！");

					},
					error: function (data, status, e)//服务器响应失败处理函数
					{
						//方法一，用后台返回值
						alert(data);//后台out输出的值
						//用ajaxFileUpload来判断成功与否，设置值
						$('#myspan').html("上传失败！");
					}
				}
		)

		return false;

	}
</script>
<body>
<h1>文件上传实例——servlet 异步上传文件（ajaxFileupload）</h1>
<!-- <form method="post" action="/servleToRestful/uploadFile" enctype="multipart/form-data">
    选择一个文件:
    <input type="file" name="uploadFile" />
    <br/><br/>
    <input type="submit" value="上传" />
</form>
 -->

<br/>
<input type="file" id="file" name="file" />
<br />
<input type="button" value="上传" onclick="return ajaxFileUpload();">

<br>
<p><span id="myspan"></span></p>
</body>
</html>