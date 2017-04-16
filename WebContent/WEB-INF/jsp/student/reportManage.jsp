<%@ page language="java" import="org.gzhmc.common.util.DateUtils" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/userlib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>报告管理</title>

</head>
<body>
	<!-- Change Pattern -->
	<div class="changePattern">
		<span id="pattern2"></span>
	</div>
	<!-- Top Panel -->
		<!-- Top Panel -->
	<div class="top_panel">
		<div class="wrapper" style="background-color: #3a3a3a">
			<div class="user">
				<img src="<%=basePath%>user/Images/user_avatar.png"
					alt="user_avatar" class="user_avatar" /> <span
					style="color: #DADADA; font-weight: bold;">${studentName}
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
		  <li><a title="上传照片" class="i_22_ui"
				href="<%=basePath%>student/uploadPicture"></a></li>
			<li><a title="报告提交" class="i_22_inbox"
				href="<%=basePath%>student/studentIndex"></a></li>
			<li><a title="报告管理" class="i_32_forms"
				href="<%=basePath%>student/reportManage"></a></li>
			<li><a title="成绩查询" class="i_22_charts"
				href="<%=basePath%>student/scoreInquery"></a></li>
			<li><a title="修改密码" class="i_22_ui"
				href="<%=basePath%>student/passwordChange"></a></li>
		</ul>
	</div>



	<div class="wrapper contents_wrapper">

		<aside class="sidebar">
			<ul class="tab_nav">
				<li class="i_32_ui"><a
					href="<%=basePath%>student/uploadPicture" title="上传照片"> <span
						class="tab_label">上传照片</span> <span class="tab_info">身份信息</span>
				</a></li>
				<li class="i_22_pages"><a
					href="<%=basePath%>student/studentIndex" title="报告提交"> <span
						class="tab_label">报告提交</span> <span class="tab_info">实验报告</span>
				</a></li>
				<li class="active_tab i_32_forms"><a
					href="<%=basePath%>student/reportManage" title="报告管理"> <span
						class="tab_label">报告管理</span> <span class="tab_info">报告查询修改</span>
				</a></li>
				<li class="i_32_charts"><a
					href="<%=basePath%>student/scoreInquery" title="成绩查询"> <span
						class="tab_label">成绩查询</span> <span class="tab_info">报告成绩查询</span>
				</a></li>
				<li class="i_32_ui"><a
					href="<%=basePath%>student/passwordChange" title="修改密码"> <span
						class="tab_label">修改密码</span> <span class="tab_info">身份信息</span>
				</a></li>

			</ul>
		</aside>


		<div class="contents">
			<form action="">
				<div class="grid_wrapper">
					<div class="g_6 contents_header">
						<h3 class="i_22_pages tab_label">&nbsp;报告管理</h3>
						<div>
							<span class="label"> &nbsp;查看修改报告</span>
						</div>
					</div>
					<!-- Separator -->
					<div class="g_12 separator">
						<span></span>
					</div>
					<div class="g_12">
						<div class="widget_header wwOptions">
							<h4 class="widget_header_title wwIcon i_16_data">实验报告</h4>
						<!--  	<div class="w_Options i_16_settings">
								
								<ul class="drop_menu menu_with_icons right_direction">
									<li><a class="i_16_add" href="<%=basePath%>student/studentIndex" title="New Flie"> <span
											class="label">添加报告</span>
									</a></li>
								</ul>
							</div> -->
						</div>
						<div class="widget_contents noPadding">
							<table class="datatable tables">
								<thead>
									<tr>										
										<th>实验编号</th>
										<th>实验名称</th>
										<th>批改状态</th>
										<th>上传时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${reportRelatives}">
										<tr>											
											<td>${item.cReportNum}</td>
											<td><a href="<%=basePath%>student/reportShow?rid=${item.cId}">${item.experimentalTest.cExperimentName}</a></td>
											<td>
											<c:choose>
											<c:when test="${item.getcScoreId()!=0}">
											报告批改完毕
											</c:when>
											<c:when test="${item.experimentalTest.cStatu==1}">
											进入批改时间，不能上传
											</c:when>
											<c:otherwise>
											可以重复上传，以最后一份为准
											</c:otherwise>
											</c:choose>
                                            </td>
											<td>
											<fmt:formatDate value="${item.cCreateTime }" type="date" pattern="yyyy-MM-dd"/>											
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

				</div>
			</form>
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