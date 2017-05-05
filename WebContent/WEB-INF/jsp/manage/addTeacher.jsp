<%@page import="java.util.List"%>
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
<title>增加/修改教师信息</title>

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

			<li class="submenu"><a href="#"><i class="icon icon-pencil"></i>
					<span>实验信息管理</span> <span class="label">2</span></a>
				<ul>
					<li><a href="<%=basePath%>manage/indexExperiment.html">实验管理</a></li>
					<li><a href="<%=basePath%>manage/indexTeacherExperiment.html">考核管理</a></li>
				</ul></li>
			<li class="active submenu open"><a href="#"><i class="icon icon-file"></i>
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
				首页</a> <a href="#" class="current">教师信息管理</a>
		</div>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-align-justify"></i>
							</span>
							<h5>增加/修改教师信息</h5>
						</div>
						<div class="widget-content nopadding">
							<form class="form-horizontal" method="post"
								action="<%=basePath %>manage/addTeacher.action"
								name="basic_validate" id="basic_validate">

								<div class="control-group">
									<label class="control-label" style="font-size: 20px">教师工号</label>
									<div class="controls">
										<!-- 隐藏值 教师id -->
										<input type="hidden" name="id"
											value="${teacherCollege.getcUserId()}" /> <input type="text"
											name="teachernum" value="${teacherCollege.getcTeacherId()}"
											style="height: 33px" required="required" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" style="font-size: 20px">教师姓名</label>
									<div class="controls">
										<input type="text" name="teachername"
											value="${teacherCollege.getcName()}" style="height: 33px"
											required="required" />
									</div>
								</div>

								<div class="control-group">
									<label class="control-label" style="font-size: 20px">学院</label>

									<div class="controls">
										<select name="ccollege" id="ccollege" style="width: 200px">
											<c:forEach var="collegeitem" items="${college}">
												<option value="${collegeitem.getcId()}">${collegeitem.getcCollegeName()}</option>
											</c:forEach>
										</select>
										<script>
										var cId="${teacherCollege.getcCollegeId()}";
										if(cId!=""){
										var ccollege=document.getElementById("ccollege");
										ccollege.value=cId;	}
										</script>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" style="font-size: 20px"></label>
									<div class="controls">
										<input type="submit" value="保存" class="btn btn-primary" /> <input
											type="button" value="取消"
											onclick="location='<%=basePath%>manage/indexTeacher'"
											class="btn btn-primary" style="margin-left: 10px" />
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<%@include file="../common/footer.jsp"%>
	</div>
	</div>

</body>
</html>