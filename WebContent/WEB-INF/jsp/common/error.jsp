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
<meta name="description" content="">
<meta name="keywords" content="">
</head>
<body>
 <div style="width: 50%;height: 50%;margin: auto;padding-top: 10%" align="center">
 <h1 style="font-size: 60px;color: red;"><img src="<%=basePath %>images/404_1.gif" alt="icon" style="width: 100px;height: 90px;">出错啦!</h1>
	<h2>糟糕！${msg}</h2>
	<img src="<%=basePath %>user/Images/Icons/Preview/house_home_building.png" alt="icon" style="width: 60px;height: 50px">
	<br/>
	<div><a href="<%=basePath %>login.jsp" style="text-decoration: underline;">返回登陆</a></div>
	</div> 
</body>
</html>