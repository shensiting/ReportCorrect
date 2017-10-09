<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath_userlib = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- The Main CSS File -->
<link rel="stylesheet" href="<%=basePath_userlib %>user/CSS/style.css" />
<!-- jQuery -->
<script
	src="<%=basePath_userlib %>user/Javascript/jQuery/jquery-1.7.2.min.js"></script>
<!-- DataTables -->
<script
	src="<%=basePath_userlib %>user/Javascript/DataTables/jquery.dataTables.min.js"></script>
<!-- 1ColResizable -->
<script
	src="<%=basePath_userlib %>user/Javascript/ColResizable/colResizable-1.3.js"></script>
<!-- jQuryUI -->
<script
	src="<%=basePath_userlib %>user/Javascript/jQueryUI/jquery-ui-1.8.21.min.js"></script>
<!-- 1Uniform -->
<script
	src="<%=basePath_userlib %>user/Javascript/Uniform/jquery.uniform.js"></script>

<!-- 1Kanrisha Script -->
<script src="<%=basePath_userlib %>user/Javascript/kanrisha.js"></script>
