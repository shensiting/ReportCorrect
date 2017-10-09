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
<meta name="renderer" content="webkit|ie-comp|ie-stand" />
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


<title>用户查看</title>
</head>
<body>
	<div class="cl pd-20" style="background-color: #5bacb6">
		<img class="avatar size-XL l" src="<%=basePath%>users/images/user.png">
		<dl style="margin-left: 80px; color: #fff">
			<dt>
				<span class="f-18">${teacher.cName}</span>
			</dt>
			<dd class="pt-10 f-12" style="margin-left: 0">${teacher.cTeacherId}</dd>
		</dl>
	</div>
	<div class="pd-20">
		<table class="table">
			<tbody>

				<tr>
					<th class="text-r">学院：</th>
					<td>${teacher.college.cCollegeName}</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>