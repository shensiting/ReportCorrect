<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/userlib.jsp"%>
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
<title>报告审阅</title>
<script type="text/javascript">
//返回id数组
function getSelectedId() {
	//获取所有name为checkboxid的 CheckBox
	var checkBoxs = document.getElementsByName("checkboxid");
	var idStr = "";
	//遍历所有CheckBox，当CheckBox为选中时将这行数据的id值拼接到idStr中
	for (var i = 0; i < checkBoxs.length; i++) {
		if (checkBoxs[i].checked) {
			idStr += checkBoxs[i].value + ",";
		}
	}
	//去掉最后一个字符（“，”）
	idStr = idStr.substring(0, idStr.length - 1);
	if (idStr == "") {
		var obj = new Array();
		return obj;
	}
	//将idStr按“，”分割成字符串数组
	var ids = idStr.split(",");
	return ids;
}
function downloadFunc() {
	var ids = getSelectedId();
	if (ids.length == 0) {
	    alert("请选择一条记录!")
	}else{
		var statu=confirm('确认所选导出' + ids.length + '条记录?');
		if(statu){
			$.ajax({
				type : 'get',
				dataType : 'json',
				url : '<%=basePath%>teacher/downloadreport.action?ids=' + ids,
				success : function(data) {
					if (data.success) {
						alert(data.msg);
					} else {
						alert(data.msg);				
					}					
				},
				error : function() {
					alert("服务器异常，请稍候再试！");			
				}
			});
		}
	}
}
</script>
</head>
<body>
	<!-- Change Pattern -->
	<div class="changePattern">
		<span id="pattern2"></span>
	</div>
	<!-- Top Panel -->
	<div class="top_panel">
		<div class="wrapper" style="background-color: #3a3a3a">
			<div class="user">
				<img src="<%=basePath%>user/Images/user_avatar.png"
					alt="user_avatar" class="user_avatar" /> <span
					style="color: #DADADA; font-weight: bold;">${teachername}
					欢迎来到实验报告管理系统 </span>
			</div>
			<div style="float: right; padding-top: 12px">
				<a href="<%=basePath%>manage/exit"
					style="color: #DADADA; font-weight: bold;">退&nbsp;出</a>
			</div>
		</div>
	</div>
	<header class="main_header">
		<div class="wrapper">
			<!-- logo -->
			<%@include file="../common/logo.jsp"%>
			<%@include file="../common/nav.jsp"%>
		</div>
	</header>


	<div class="wrapper small_menu">
		<ul class="menu_small_buttons">
			<li><a title="Your Messages" class="i_22_inbox"
				href="<%=basePath%>teacher/teacherIndex"></a></li>
			<li><a title="Visual Data" class="i_22_charts"
				href="<%=basePath%>teacher/downloadreport"></a></li>
			<li><a title="Kit elements" class="i_22_ui"
				href="<%=basePath%>teacher/tpasswordChange"></a></li>
		</ul>
	</div>

	<div class="wrapper contents_wrapper">
		<aside class="sidebar">
			<ul class="tab_nav">
				<li class="active_tab i_22_forms"><a
					href="<%=basePath%>teacher/teacherIndex" title="Your Messages">
						<span class="tab_label">报告审阅</span> <span class="tab_info">报告批改或审查</span>
				</a></li>
				<li class="i_22_pages"><a
					href="<%=basePath%>teacher/downloadpdf" title="Visual Data"> <span
						class="tab_label">证书管理</span> <span class="tab_info">证书下载等</span>
				</a></li>
				<li class="i_32_ui"><a
					href="<%=basePath%>teacher/tpasswordChange" title="Kit elements">
						<span class="tab_label">修改密码</span> <span class="tab_info">身份信息</span>
				</a></li>
			</ul>
		</aside>
		<div class="contents">
			<div class="grid_wrapper">
				<div class="g_6 contents_header">
					<h3 class="i_22_forms tab_label">报告审阅</h3>
					<div>
						<span class="label">请认真批改，提交后将不能更改</span>
					</div>
				</div>
				<!-- Separator -->
				<div class="g_12 separator">
					<span></span>
				</div>
				<form action="<%=basePath%>teacher/downloadreport.action">
					<div class="line_grid">
						<div class="g_9">
							<span class="label">班&nbsp;&nbsp;级:</span> <select
								class="simple_form" name="gradeid" id="gradeid">
								<c:forEach var="gMCitem" items="${gradeMajorColleges}">
									<option value="${gMCitem.cId}" />
											${gMCitem.college.cCollegeName}
												${gMCitem.cYearClass} ${gMCitem.major.cMajorName}
												${gMCitem.cClass} 																					
									</c:forEach>
							</select>
						</div>
						<div class="g_9">
							<span class="label">实验名称:</span> <select class="simple_form"
								name="experimentid" id="experimentid">
								<c:forEach var="eTitem" items="${experimentalTests}">
									<option value="${eTitem.cId}" />
												${eTitem.cExperimentName}																				
									</c:forEach>
							</select>

						</div>
						<div style="float: right">
							<input type="submit"  value="下载" class="simple_buttons"
								style="height: 35px; width: 80px; background: #FF5511; color: #FFFFFF" />
						</div>
					</div>
				</form>
				<!-- Separator -->
				<hr
					style="height: 1px; border: none; border-top: 1px dashed #DDDDDD;" />
				<form action="<%=basePath%>teacher/queryreport">
					<div class="line_grid">
						<div class="g_9">
							<span class="label">班&nbsp;&nbsp;级:</span> <select
								class="simple_form" name="gradeidq" id="gradeidq">
								<c:forEach var="gMCitem" items="${gradeMajorColleges}">
									<option value="${gMCitem.cId}" />
											${gMCitem.college.cCollegeName}
												${gMCitem.cYearClass} ${gMCitem.major.cMajorName}
												${gMCitem.cClass} 																					
									</c:forEach>
							</select>
						</div>
						<div class="g_9">
							<span class="label">实验名称:</span> <select class="simple_form"
								name="experimentidq" id="experimentidq">
								<c:forEach var="eTitem" items="${experimentalTests}">
									<option value="${eTitem.cId}" />
												${eTitem.cExperimentName}																				
									</c:forEach>
							</select>
						</div>
						<div style="float: right">
							<input type="submit" value="查看" class="simple_buttons"
								style="height: 35px; width: 80px; background: #FF5511; color: #FFFFFF" />
						</div>
					</div>
				</form>

				<!-- Column Checker -->
				<div class="g_12">
					<div class="widget_header wwOptions">
						<h4 class="widget_header_title wwIcon i_16_data">实验报告管理</h4>
						<!-- Drop Menu -->
						<ul class="drop_menu menu_with_icons right_direction">
							<li><a class="i_16_add" href="#" title="New Flie"> <span
									class="label">添加报告</span>
							</a></li>
						</ul>
					</div>
					<div class="widget_contents noPadding">
						<table class="datatable tables">
							<thead>
								<tr>

									<th>实验报告编号</th>
									<th>实验名称</th>
									<th>学号</th>
									<th>班级</th>
									<th>是否批改</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${reportRelatives}">
									<tr>
										<td>${item.cReportNum}</td>
										<td><a
											href="<%=basePath%>teacher/teacherreportshow?rid=${item.cId}">${item.experimentalTest.cExperimentName}</a></td>
										<td>${item.student.getcStudentNumber()}</td>
										<td><c:forEach var="gradeitem"
												items="${gradeMajorColleges}">
												<c:if test="${item.student.cGradeId==gradeitem.cId}">
											     ${gradeitem.getcYearClass()} ${gradeitem.getMajor().getcMajorName()}${gradeitem.getcClass()}
												<c:set var="isDoing" value="1" />
												</c:if>
											</c:forEach></td>
										<td>
											<!-- 判断是否到批改时间 --> <c:choose>
												<c:when test="${item.cStatus=='1'}">
											已批改
											</c:when>
												<c:when test="${item.experimentalTest.cStatu=='0'}">
											还未到批改时间
											</c:when>
												<c:otherwise>
													<input type="button" value="批改" class="simple_buttons"
														style="height: 30px; width: 60px; background: #FF5511; color: #FFFFFF"
														onclick="location.href='<%=basePath%>teacher/reportcorrect?rid=${item.cId}'" />
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer -->
	<footer>
		<div class="wrapper">
			<span class="copyright"> <%@include
					file="../common/footer.jsp"%>
			</span>
		</div>
	</footer>

</body>
</html>