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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报告查看</title>
<script type="text/javascript"
	src="<%=basePath%>ueditor/ueditor.parse.js"></script>
</head>
<body>
	${report}
	<div
		style="margin-top: 5%; text-align: center; font-size: 10px; color: gray;">
		<footer> Copyright &copy; 2017, Crazy Code, All Rights
		Reserved </footer>
	</div>
</body>
</html>