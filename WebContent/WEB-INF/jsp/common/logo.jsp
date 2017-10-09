<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path_logo = request.getContextPath();
	String basePath_logo = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path_logo + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="description" content="">
<meta name="keywords" content="">

</head>
<body>
	<div class="logo">
		<a href="#" title="广州医科大学"> <img
			src="<%=basePath_logo%>images/GZHMU_logo.png" alt="kanrisha_logo" />
		</a>
	</div>
</body>
</html>