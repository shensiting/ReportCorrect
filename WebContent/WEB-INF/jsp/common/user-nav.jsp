<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="description" content="">
<meta name="keywords" content="">
<%
	String path_nav = request.getContextPath();
	String basePath_nav = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path_nav + "/";
%>

<script type="text/javascript">
</script>
</head>
<body>
	<div id="user-nav" class="navbar navbar-inverse">
		<ul class="nav btn-group">
			<li class="btn btn-inverse dropdown"><a href="#"
				data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle" style="width: 80px"><i
					class="icon icon-user"></i> <span class="text">Hello</span> <b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a class="sAdd" title=""
						href="<%=basePath_nav%>manage/exit">退出</a></li>
					<!-- <li><a class="sInbox" title="" href="#">inbox</a></li>
					<li><a class="sOutbox" title="" href="#">outbox</a></li>
					<li><a class="sAdd" title="" href="#">Logout</a></li> -->
				</ul></li>
		</ul>
	</div>
</body>
</html>