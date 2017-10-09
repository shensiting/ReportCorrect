<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath_lib = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<link rel="stylesheet"
	href="<%=basePath_lib %>manage/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=basePath_lib %>manage/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath_lib %>manage/css/uniform.css" />
<link rel="stylesheet" href="<%=basePath_lib %>manage/css/select2.css" />
<link rel="stylesheet"
	href="<%=basePath_lib %>manage/css/unicorn.main.css" />
<link rel="stylesheet"
	href="<%=basePath_lib %>manage/css/unicorn.grey.css" class="skin-color" />
<script src="<%=basePath_lib %>manage/js/jquery.min.js"></script>
<script src="<%=basePath_lib %>manage/js/jquery.ui.custom.js"></script>
<script src="<%=basePath_lib %>manage/js/bootstrap.min.js"></script>
<script src="<%=basePath_lib %>manage/js/jquery.uniform.js"></script>
<script src="<%=basePath_lib %>manage/js/select2.min.js"></script>
<script src="<%=basePath_lib %>manage/js/jquery.dataTables.min.js"></script>
<script src="<%=basePath_lib %>manage/js/unicorn.js"></script>
<script src="<%=basePath_lib %>manage/js/unicorn.tables.js"></script>
<!-- Dialog Js -->
<script
	src="<%=basePath_lib %>js/jquery.bootstrap.teninedialog.v3.min.js"></script>
<!-- validate -->
<script src="<%=basePath_lib %>manage/js/jquery.validate.js"></script>
<script src="<%=basePath_lib %>manage/js/unicorn.form_validation.js"></script>

