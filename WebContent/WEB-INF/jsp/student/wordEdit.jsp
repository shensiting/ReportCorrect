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
<%@ include file="../common/userslib.jsp"%>
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=basePath%>users/lib/html5.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/PIE_IE678.js"></script>
<![endif]-->
<link href="<%=basePath%>users/lib/icheck/icheck.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>users/lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>users/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>报告编辑</title>
<script type="text/javascript">
function load(){         
	var experiment= $("#test").val(); 
	var process= $("#process").val(); 
	var file=$("#file").val(); 
    if(process==""){
		alert("实验过程不能为空！");
	}
	else if(file==""){
		alert("实验不能为空！");
	}
	else if (process.length> 100||process.length<=10) {
			alert('报告过程字数不合格，请修改后上传');
		}
	else if (file.lastIndexOf(".doc")==-1&&file.lastIndexOf(".docx")==-1) {
		alert('文件格式错误，请重新选择！');
	}
	else{
		document.forms[0].action='<%=basePath%>student/updateWord.action';
		document.forms[0].submit(); 
		document.getElementById('loading').style.display='';
	}
	
}

</script>
<style>
.black_overlay {
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
	color: white;
}
</style>
</head>
<body>
	<div class="pd-20">
		<!--弹出层时背景层DIV-->
		<div id="loading" class="black_overlay" style="display: none;">
			<div style="margin: auto; text-align: center; padding-top: 30%">
				<p>
					<img src="<%=basePath%>users/images/loading_072.gif" />
				</p>
				<p>正在上传中，请耐心等待...</p>
			</div>
		</div>
		<form enctype="multipart/form-data" action="" method="post"
			id="reportFrom" class="form form-horizontal" id="form-article-add">
			<div class="row cl" style="text-align: left;">
				<label class="form-label col-1">实验：</label> <label
					class="form-label col-1">${experimentName}</label> <input
					name="test" id="test" type="hidden" value="${test}" />
			</div>
			<div class="row cl">
				<label class="form-label col-1">报告过程：</label>
				<div class="formControls col-10">
					<textarea name="process" id="process" cols="" rows=""
						class="textarea" placeholder="说点什么...最少输入10个字符" datatype="*10-100"
						dragonfly="true" onKeyUp="textarealength(this,200)">${process}</textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/100
					</p>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-1">注意事项：</label>1.在上传作业之前请先把照片上传。2.请根据老师要求选择具体上传形式
			</div>
			<div class="row cl">
				<label class="form-label col-1">报告文档：</label>
				<div class="formControls col-10">
					<span class="btn-upload form-group"> <input
						class="input-text upload-url" type="text" name="uploadfile-2"
						id="uploadfile-2" readonly="" datatype="*" nullmsg="请添加附件！"
						style="width: 200px"> <a href="javascript:void();"
						class="btn btn-primary upload-btn"><i class="Hui-iconfont"></i>
							浏览文件</a> <input type="file" multiple="" id="file" name="file"
						class="input-file">
					</span>
					<!--<input type="file" name="file" id="file" class="simple_form" required="required" />-->
					<span style="color: gray;">文件格式为doc或docx格式，最大限度: 10Mb</span>
				</div>
			</div>
			<div class="row cl">
				<div class="col-10 col-offset-2" style="margin-left: 81%">
					<button class="btn btn-primary radius" onclick="load()">
						<i class="Hui-iconfont">&#xe632;</i> 确认上传实验
					</button>
					<!-- onclick="submit(this.form)" -->
				</div>
			</div>
		</form>
		<div
			style="margin-top: 5%; text-align: center; font-size: 10px; color: gray;">
			<footer> Copyright &copy; 2017, Crazy Code, All Rights
			Reserved </footer>
		</div>
	</div>


</body>
</html>