<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出错啦</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>404错误模板源码</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta name="Copyright" content="" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>users/error/css/style.css" />
</head>

<body>
	<div class="error_404" style="margin-top: 8%">
		<div class="inner">
			<!-- <a href="<%=basePath %>login.jsp">返回首页</a> -->
		</div>
	</div>
</body>
</html>