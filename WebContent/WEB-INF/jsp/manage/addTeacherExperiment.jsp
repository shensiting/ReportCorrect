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
<title>增加/修改考核信息</title>

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
				首页</a> <a href="#" class="current">班级管理</a>
		</div>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-align-justify"></i>
							</span>
							<h5>增加/修改考核信息</h5>
						</div>
						<div class="widget-content nopadding">
							<form class="form-horizontal" method="post"
								action="<%=basePath %>manage/addTeacherExperiment.action"
								name="basic_validate" id="basic_validate">								
								<div class="control-group">
									<label class="control-label" style="font-size: 20px">教师</label>
									<!-- 隐藏值 考核id -->
										<input type="hidden" name="id" value="${teacherlinkExperiment.cId }" />
									<div class="controls">
										<select name="cteacher" id="cteacher" style="width: 250px">
											<c:forEach var="teacheritem" items="${teachers}">
												<option value="${teacheritem.getcUserId() }">${teacheritem.getcTeacherId()}&nbsp;&nbsp;${teacheritem.getcName()}</option>
											</c:forEach>
										</select>
										<script>
										var cId="${teacherlinkExperiment.getcTeacherId()}";
										if(cId!=""){
										var cteacher=document.getElementById("cteacher");
										cteacher.value=cId;	}
										</script> 
									</div>
								</div>
									<div class="control-group">
									<label class="control-label" style="font-size: 20px">实验</label>
									
									<div class="controls">
										<select name="cexperiment" id="cexperiment" style="width: 250px">
											<c:forEach var="item" items="${experimentalTests}">
												<option value="${item.getcId() }">${item.getcExperimentName()}</option>
											</c:forEach>
										</select>
										<script>
										var cId="${teacherlinkExperiment.getcExperimentalTestId()}";
										if(cId!=""){
										var cexperiment=document.getElementById("cexperiment");
										cexperiment.value=cId;	}
										</script> 	
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" style="font-size: 20px"></label>
									<div class="controls">
										<input type="submit" value="保存" class="btn btn-primary" /> <input
											type="button" value="取消"
											onclick="location='<%=basePath%>manage/indexTeacherExperiment.html'"
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
	

</body>
</html>