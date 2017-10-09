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
<title>证书管理</title>
<script type="text/javascript">
function test(){
	var gradeid=document.getElementById("gradeid").value;
	var experimentid=document.getElementById("experimentid").value;
	//通过ajax向后台请求
	$.ajax({
		type : 'get',
		dataType : 'json',
		url : '<%=basePath%>teacher/downloadpdf.action?gradeid=' + gradeid+'&experimentid='+experimentid,
		success : function(data) {
			if (data.success) {
				//删除成功，刷新页面

			} else {
				alert(data.msg);
				
			}
			
		},
		error : function() {
			alert("服务器异常，请稍候再试！");
		
		}
	});
}
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
function outFunc() {
	var ids = getSelectedId();
	if (ids.length == 0) {
	    alert("请选择一条记录!")
	}else{
		var statu=confirm('确认所选' + ids.length + '条记录?');
		if(statu){
		   window.location.href='<%=basePath%>teacher/excelout.action?ids='
						+ ids;
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
			<li><a title="报告审阅" class="i_22_inbox"
				href="<%=basePath%>teacher/teacherIndex"></a></li>
			<li><a title="证书管理" class="i_22_charts"
				href="<%=basePath%>teacher/downloadreport"></a></li>
			<li><a title="修改密码" class="i_22_ui"
				href="<%=basePath%>teacher/tpasswordChange"></a></li>
		</ul>
	</div>


	<div class="wrapper contents_wrapper">
		<aside class="sidebar">
			<ul class="tab_nav">
				<li class="i_22_forms"><a
					href="<%=basePath%>teacher/teacherIndex" title="报告审阅"> <span
						class="tab_label">报告审阅</span> <span class="tab_info">报告批改或审查</span>
				</a></li>
				<li class="active_tab i_22_pages"><a
					href="<%=basePath%>teacher/downloadpdf" title="证书管理"> <span
						class="tab_label">证书管理</span> <span class="tab_info">证书下载等</span>
				</a></li>
				<li class="i_32_ui"><a
					href="<%=basePath%>teacher/tpasswordChange" title="修改密码"> <span
						class="tab_label">修改密码</span> <span class="tab_info">身份信息</span>
				</a></li>
			</ul>
		</aside>

		<div class="contents">

			<div class="grid_wrapper">
				<div class="g_6 contents_header">
					<h3 class="i_22_pages tab_label">证书管理</h3>
					<div>
						<span class="label">成绩导出，证书下载</span>
					</div>
				</div>
				<!-- Separator -->
				<div class="g_12 separator">
					<span></span>
				</div>
				<form action=" <%=basePath%>teacher/downloadPdfExce.action">
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
							<input type="submit" name="action" value="导出表格"
								class="simple_buttons"
								style="height: 40px; width: 90px; background: #FF5511; color: #FFFFFF" />
							<!-- onclick="outFunc()" 用checkBox选择导出表格-->
							<input type="submit" name="action" value="下载证书"
								class="simple_buttons"
								style="height: 40px; width: 90px; background: #FF5511; color: #FFFFFF" />
						</div>
					</div>
				</form>
				<!-- Separator -->
				<hr
					style="height: 1px; border: none; border-top: 1px dashed #DDDDDD;" />
				<form action="<%=basePath%>teacher/queryPdf">
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
						<h4 class="widget_header_title wwIcon i_16_data">证书管理</h4>
					</div>
					<div class="widget_contents noPadding">
						<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper"
							role="grid">
							<div class="dtTables">
								<table class="datatable tables dataTable"
									id="DataTables_Table_0"
									aria-describedby="DataTables_Table_0_info">
									<thead>
										<tr role="row">
											<th>学号</th>
											<th>学生姓名</th>
											<th>证书编号</th>
											<th>实验名称</th>
											<th>班级</th>
											<th>成绩</th>
										</tr>
									</thead>

									<tbody role="alert" aria-live="polite" aria-relevant="all">
										<c:forEach var="rRitem1" items="${reportRelativesbystatus}">
											<tr class="odd">
												<td>${rRitem1.student.cStudentNumber }</td>
												<td>${rRitem1.student.cName }</td>
												<td>${rRitem1.cReportNum }</td>
												<td>${rRitem1.experimentalTest.cExperimentName }</td>
												<c:forEach var="gMCitem1" items="${gradeMajorColleges}">
													<c:if test="${gMCitem1.cId==rRitem1.student.cGradeId}">
														<td>
															${gMCitem1.cYearClass}${gMCitem1.major.cMajorName}${gMCitem1.cClass}</td>
														<c:set var="isDoing" value="1" />
													</c:if>
												</c:forEach>
												<td>${rRitem1.scoreSheet.cSum }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<!-- 
					<div class="widget_contents noPadding twCheckbox">

						<table class="datatable tables">
							<thead>
								<tr>
									<th><input id="title-table-checkbox"
										name="title-table-checkbox" type="checkbox"
										class="simple_form tMainC" /></th>
									<th>学号</th>
									<th>学生姓名</th>
									<th>证书编号</th>
									<th>实验名称</th>
									<th>班级</th>
									<th>成绩</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="rRitem1" items="${reportRelativesbystatus}">
									<tr>
										<td><input name="checkboxid" id="checkboxid"
											type="checkbox" class="simple_form" value="${rRitem1.cId}" /></td> 
										<td>${rRitem1.student.cStudentNumber }</td>
										<td>${rRitem1.student.cName }</td>
										<td>${rRitem1.cReportNum }</td>
										<td>${rRitem1.experimentalTest.cExperimentName }</td>
										<c:forEach var="gMCitem1" items="${gradeMajorColleges}">
											<c:if test="${gMCitem1.cId==rRitem1.student.cGradeId}">
												<td>
													${gMCitem1.cYearClass}${gMCitem1.major.cMajorName}${gMCitem1.cClass}</td>
												<c:set var="isDoing" value="1" />
											</c:if>
										</c:forEach>
										<td>${rRitem1.scoreSheet.cSum }</td>
									</tr>
									<c:forEach var="i" begin="20" end="100">
								<tr><td><input name="checkboxid" id="checkboxid" type="checkbox" class="simple_form" value="${rRitem1.cId}"/></td>
								<td>${i}</td><td>${i}</td><td>${i}</td><td>${i}</td><td>${i}</td><td>${i}</td></tr>
								</c:forEach>
								</c:forEach>

							</tbody>
						</table>

					</div>

			-->

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