<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath_userslib = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath_userslib%>users/css/H-ui.min.css" rel="stylesheet" type="text/css" />

<link href="<%=basePath_userslib%>users/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath_userslib%>users/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath_userslib%>users/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath_userslib%>users/lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="<%=basePath_userslib%>users/js/H-ui.js"></script>
<script type="text/javascript" src="<%=basePath_userslib%>users/js/H-ui.admin.js"></script>