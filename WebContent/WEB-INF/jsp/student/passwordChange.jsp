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
<title>学生密码修改</title>
<script type="text/javascript">

//校验密码：只能输入6-20个字母、数字、下划线   
function isPasswd(passwordString){   
var patrn=/^(\w){6,20}$/;   
return patrn.exec(passwordString);
}   

function passwordChange(registeform){
	 var password=registeform.password.value;
     var  repassword=registeform.repassword.value;
	if(!isPasswd(password)||!isPasswd(repassword)){
		alert("密码只能为6-20个字母、数字、下划线。   ");	
	}
	else if(password!=repassword){
		alert("两次密码不一致，请确认后输入。 ");	
	}
	else{
		$.ajax({
			type : 'get',
			dataType : 'json',
			async: false,
			url:'<%=basePath%>student/passwordChange.action?password='+password,
			success : function(data) {
				if (data.success) {		
					alert(data.msg);
					document.forms[0].action ='<%=basePath%>login.jsp';
						document.forms[0].submit();
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
</script>
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
					href="<%=basePath%>student/studentIndex" title="报告提交">
						<span class="tab_label">报告提交</span> <span class="tab_info">实验报告</span>
				</a></li>
				<li class="i_32_forms"><a
					href="<%=basePath%>student/reportManage" title="报告管理"> <span
						class="tab_label">报告管理</span> <span class="tab_info">报告查询修改</span>
				</a></li>
				<li class="i_32_charts"><a
					href="<%=basePath%>student/scoreInquery" title="成绩查询"> <span
						class="tab_label">成绩查询</span> <span class="tab_info">报告成绩查询</span>
				</a></li>
				<li class="active_tab i_32_ui"><a
					href="<%=basePath%>student/passwordChange" title="修改密码">
						<span class="tab_label">修改密码</span> <span class="tab_info">身份信息</span>
				</a></li>
				
			</ul>
		</aside>

		<div class="contents">
			<div class="grid_wrapper">
				<div class="g_6 contents_header">

					<h3 class="i_32_ui tab_label">&nbsp;信息管理</h3>
					<div>
						<span class="label">&nbsp;请认真仔细填写</span>
					</div>
				</div>
				<!-- Separator -->
				<div class="g_12 separator">
					<span></span>
				</div>
				<div class="g_6" style="width: 100%">
					<div class="widget_header">
						<h4 class="widget_header_title wwIcon i_16_forms">密码修改</h4>
					</div>
					<div class="widget_contents noPadding">
						<form action="" method="post">
							<div class="line_grid" align="center">
								<input name="password" class="simple_field" type="password"
									placeholder="密码" required="required" style="width: 98%" />
							</div>
							<div class="line_grid" align="center">
								<input name="repassword" class="simple_field" type="password"
									placeholder="确认密码" required="required" style="width: 98%" />
							</div>
							<div class="line_grid">
								<div class="g_12">
									<input type="button" value="提交" class="simple_buttons"
										style="height: 40px; width: 100px;background: #FF5511;color: #FFFFFF"
										onclick="passwordChange(this.form)" />
								</div>
							</div>
						</form>
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