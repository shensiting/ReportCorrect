<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/userslib.jsp"%>
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
<script type="text/javascript" src="<%=basePath_userslib%>users/lib/html5.js"></script>
<script type="text/javascript" src="<%=basePath_userslib%>users/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath_userslib%>users/lib/PIE_IE678.js"></script>
<![endif]-->


<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath_userslib%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>新增学院</title>
</head>
<body>
	<div class="pd-30">
		<form class="form-horizontal" method="post"
			action="<%=basePath %>manage/addCollege.action" name="basic_validate"
			id="basic_validate">

			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>学院名称：</label>
				<div class="formControls col-6">
					<!-- 隐藏值 学院id -->
					<input type="hidden" name="id" value="${college.cId }" />
					<input
						type="text" name="collegename" value="${college.cCollegeName }"
						 required="required" /> 
	
				</div>
			</div>
			<div class="row cl">
				<div class="col-10 col-offset-3">
				<input type="submit" value="保存" class="btn btn-primary radius" />
					
				</div>
			</div>
		</form>
	</div>
	
</body>
</html>