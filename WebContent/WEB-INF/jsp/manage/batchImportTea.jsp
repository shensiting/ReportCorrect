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

<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>批量导入教师</title>
<script type="text/javascript">
function load(){         	
	var file=$("#file").val(); 
	if(file==""){
		alert("请选择文件路径！");
	}
	else if (file.lastIndexOf(".xlsx")==-1&&file.lastIndexOf(".xls")==-1) {
			alert('文件格式错误，请重新选择！');
		}
	else{
		document.forms[0].action='<%=basePath%>manage/importTeaExcel';
		document.forms[0].submit(); 
		document.getElementById('loading').style.display='block';
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
				<p>正在导入中，请耐心等待...</p>
			</div>
		</div>
		<div class="row cl">
			<img src="<%=basePath%>users/images/教师表格模板.jpg" />
		</div>

		<div class="row cl"
			style="margin-top: 4%; margin-left: 60%; height: 50px">
			<p>
				导入教师模板.xlsx <a style="width: 150px;" class="btn btn-primary radius"
					href="../gzhmc/data/导入教师模板.xlsx">下载模板</a>
			</p>
		</div>
		<hr>
		<div style="margin-top: 3%">请务必先下载模板，并在模板上编辑数据，才能正确导入数据</div>
		<div class="row cl" style="margin-top: 3%">
			<form enctype="multipart/form-data" action="" method="post"
				class="form form-horizontal">
				<div class="formControls col-10">
					<span class="btn-upload form-group"> <input
						class="input-text upload-url" type="text" name="uploadfile-2"
						id="uploadfile-2" readonly="" datatype="*" nullmsg="请添加附件！"
						style="width: 200px"> <a href="javascript:void();"
						class="btn btn-primary upload-btn"><i class="Hui-iconfont"></i>
							浏览文件</a> <input type="file" multiple="" id="file" name="file"
						class="input-file">
					</span> <span style="color: gray;">文件必须为表格</span>
				</div>
				<div class="row cl" style="margin-top: 5%">
					<a style="width: 150px; margin-left: 75%"
						class="btn btn-success radius" onclick="load()" id=""
						href="javascript:;" name="">导入数据</a>
				</div>
			</form>
		</div>
		<div class="row cl" style="color: red;">
			<p>${msg}</p>
		</div>
		<div
			style="margin-top: 5%; text-align: center; font-size: 10px; color: gray;">
			<footer> Copyright &copy; 2017, Crazy Code, All Rights
			Reserved </footer>
		</div>
	</div>
</body>
</html>