<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=basePath%>users/lib/html5.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/PIE_IE678.js"></script>
<![endif]-->

<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<%@ include file="../common/userslib.jsp"%>
<link href="<%=basePath_userslib%>users/lib/icheck/icheck.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath_userslib%>users/lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath_userslib%>users/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />

<title>新增图片</title>
<style type="text/css">
.file {
	position: relative;
	display: inline-block;
	background: #66CCFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 15px 25px;
	overflow: hidden;
	color: #FFFFFF;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
	font-size: 15px;
}

.file input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.file:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="pd-20">
		<form action="<%=basePath%>student/uploadPicture.action" method="post"
			enctype="multipart/form-data" class="form form-horizontal"
			id="form-article-add">
			<!-- <div class="row cl">
				<label class="form-label col-1"><span class="c-red">*</span>身份证号：</label>
				<div class="formControls col-10">
					<input type="text" class="input-text" value="" placeholder=""
						id="idcard" name="idcard">
				</div>
			</div>
             -->
			<div class="row cl">
				<label class="form-label col-1"><span class="c-red">*</span>图片上传：</label>
				<div class="formControls col-10">
					<div class="uploader-list-container">
						<div class="queueList">
							<div id="dndArea">

								<div id="filePicker-2">
									<img src="" height="150"
										onerror="this.src='<%=basePath_userslib%>users/lib/webuploader/0.1.5/images/image.png'">
								</div>

								<p>文件为jpg，jpeg格式，最大限度: 5Mb</p>
								<a href="javascript:;" class="file">点此选择文件 <input
									type="file" name="file" id="file" onchange="previewFile()">
								</a>

							</div>
						</div>
						<div class="statusBar" style="display: none;">
							<div class="progress">
								<span class="text">0%</span> <span class="percentage"></span>
							</div>
							<div class="info"></div>
							<div class="btns">
								<div id="filePicker2"></div>
								<!-- <div class="uploadBtn">开始上传</div> -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button onClick="article_save_submit();"
						class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 保存并上传图片和数据
					</button>
					<button onClick="javascript:location.replace(location.href);"
						class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
	<div
		style="margin-top: 5%; text-align: center; font-size: 10px; color: gray;">
		<footer> Copyright &copy; 2017, Crazy Code, All Rights
		Reserved </footer>
	</div>
	</div>

	<script type="text/javascript"
		src="<%=basePath_userslib%>users/lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=basePath_userslib%>users/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath_userslib%>users/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath_userslib%>users/lib/webuploader/0.1.5/webuploader.min.js"></script>

	<script type="text/javascript">
	function previewFile() {
		  var preview = document.querySelector('img');
		  var file    = document.querySelector('input[type=file]').files[0];
		  var reader  = new FileReader();

		  reader.addEventListener("load", function () {
		    preview.src = reader.result;
		  }, false);

		  if (file) {
		    reader.readAsDataURL(file);
		  }
		}
	</script>
</body>
</html>