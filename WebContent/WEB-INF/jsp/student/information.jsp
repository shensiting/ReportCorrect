<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="../common/userslib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->

<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>个人信息</title>
<style type="text/css">
td, th {
	height: 50px;
	font-size: 15px;
}
.pch{
text-align: center;
}
</style>
</head>
<body>
	<div class="pd-40">
		<table class="table table-border table-bordered table-bg mt-60">
			<thead>
				<tr>
					<th colspan="3" scope="col">学生基本信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="10%">学号：</td>					
					<td>${student.cStudentNumber}</td>
					<td class="pch" width="15%" rowspan="4"><img alt=""
						src="<%=basePath%>gzhmc/picture${student.getcPicturePath()}"
						onerror="this.src='<%=basePath%>gzhmc/picture/sl.png'" width="150px"
						height="150px"></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td>${student.getcName()}</td>

				</tr>
				<tr>
					<td>学院：</td>
					<td>${student.getCollege(). getcCollegeName()}</td>
				</tr>
				<tr>
					<td>年级：</td>
					<td>${student.getGrade(). getcYearClass()}</td>

				</tr>
				<tr>
					<td>专业：</td>
					<td colspan="2">${student.getMajor(). getcMajorName()}</td>
				</tr>
				<tr>
					<td>班级：</td>
					<td colspan="2">${student.getGrade(). getcClass()}</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>