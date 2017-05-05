<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/lib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>增加/修改实验</title>

<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="header">
		<h1>
			<img alt="" src="<%=basePath%>images/gzykdx.png"
				style="width: 100%; height: 100%">
		</h1>
	</div>
	<!-- 右上角用户按钮 -->
	<%@include file="../common/user-nav.jsp"%>

	<!-- 左侧导航栏 -->
	<div id="sidebar">
		<ul>
			<li class="submenu"><a href="#"><i
					class="icon icon-th-list"></i> <span>基本信息管理</span> <span
					class="label">3</span></a>
				<ul>		
				<li><a href="<%=basePath%>manage/indexCollege">学院管理</a></li>	
					<li><a href="<%=basePath%>manage/indexMajor">专业管理</a></li>
					<li><a href="<%=basePath%>manage/indexGrade">班级管理</a></li>
				</ul></li>

			<li class="active submenu open"><a href="#"><i class="icon icon-pencil"></i>
					<span>实验信息管理</span> <span class="label">2</span></a>
				<ul>
					<li><a href="<%=basePath%>manage/indexExperiment.html">实验管理</a></li>
					<li><a href="<%=basePath%>manage/indexTeacherExperiment.html">考核管理</a></li>
				</ul></li>
			<li class="submenu"><a href="#"><i class="icon icon-file"></i>
					<span>用户信息管理</span> <span class="label">3</span></a>
				<ul>
					<li><a href="<%=basePath%>manage/indexStudent">学生信息管理</a></li>
					<li><a href="<%=basePath%>manage/indexTeacher">教师信息管理</a></li>
					<li><a href="<%=basePath%>manage/indexUser.html">登陆信息管理</a></li>
				</ul></li>
		</ul>
	</div>
	<!-- 左侧导航栏 -->

	<div id="content">
		<div id="content-header">
			<h1>
				<a>广医学生报告后台管理系统</a>
			</h1>
		</div>
		<div id="breadcrumb">
			<a href="<%=basePath%>manage/index" title="主页" class="tip-bottom"><i class="icon-home"></i>
				首页</a> <a href="#" class="current">实验管理</a><a href="#" class="current">增加/修改实验</a>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-align-justify"></i>
							</span>
							<h5>增加/修改实验</h5>
						</div>
						<div class="widget-content nopadding">
							<form class="form-horizontal" method="post"
								action="<%=basePath %>manage/addExperiment.action"
								name="basic_validate" id="basic_validate">
								<div class="control-group">
									<label class="control-label" style="font-size: 20px">实验名称</label>
									<div class="controls">
										<!-- 隐藏值 学院id -->
										<input type="hidden" name="id" value="${experimentalTest.getcId()}" /> <input
											type="text" name="name"
											value="${experimentalTest.getcExperimentName()}" style="height: 33px" required="required" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" style="font-size: 20px">实验英文名称</label>
									<div class="controls">
									 <input type="text" name="Englishname"
											value="${experimentalTest.getcExperimentEnglishName()}" style="height: 33px" required="required" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" style="font-size: 20px">实验考核时间</label>
									<div class="controls">										
										<input
											type="text" name="TestTime"
											value="${experimentalTest.getcExperimentTime()}" style="height: 33px" required="required" placeholder="yyyy年mm日"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" style="font-size: 20px"></label>
									<div class="controls">
										<input type="submit" value="保存" class="btn btn-primary" /> <input
											type="button" value="取消"
											onclick="location='<%=basePath%>manage/indexExperiment'"
											class="btn btn-primary" style="margin-left: 10px" />
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>