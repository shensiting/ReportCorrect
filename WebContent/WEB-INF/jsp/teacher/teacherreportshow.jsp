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
<title>报告批改</title>
<script type="text/javascript">
function reinitIframe() {

     var iframe = document.getElementById("frame");

     try {

         var bHeight = iframe.contentWindow.document.body.scrollHeight;

         var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;

         var height = Math.max(bHeight, dHeight);

         iframe.height = height;

     } catch (ex) { }

 }
 window.setInterval("reinitIframe()", 200);

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
				href="<%=basePath %>teacher/teacherIndex"></a></li>
				<li><a title="Visual Data" class="i_22_charts"
				href="<%=basePath%>teacher/downloadreport"></a></li>
			<li><a title="Kit elements" class="i_22_ui" href="<%=basePath %>teacher/tpasswordChange"></a></li>
		</ul>
	</div>


	<div class="wrapper contents_wrapper">
		<aside class="sidebar">
			<ul class="tab_nav">
				<li class="active_tab i_22_forms"><a href="<%=basePath %>teacher/teacherIndex"
					title="Your Messages"> <span class="tab_label">报告审阅</span> <span
						class="tab_info">报告批改或审查</span>
				</a></li>
				<li class="i_22_pages"><a
					href="<%=basePath%>teacher/downloadpdf" title="Visual Data"> <span
							class="tab_label">证书管理</span> <span class="tab_info">证书下载等</span>
				</a></li>			
				<li class="i_32_ui"><a href="<%=basePath %>teacher/tpasswordChange" title="Kit elements">
						<span class="tab_label">修改密码</span> <span class="tab_info">身份信息</span>
				</a></li>
			</ul>
		</aside>

		<div class="contents">
			<div class="grid_wrapper">
				<div class="g_6 contents_header">
					<h3 class="i_22_pages tab_label">报告批改</h3>
					<div>
						<span class="label">请认真仔细填写</span>
					</div>
				</div>
				<!-- Separator -->
				<div class="g_12 separator">
					<span></span>
				</div>

				<div class="g_6" style="width: 100%">
					<div
						style="margin: auto; text-align: center; vertical-align: middle;">
						<iframe src="<%=basePath %>report${wordpath }" id="frame"
							scrolling="no" frameborder="0"
							style="overflow: auto; margin: auto; width: 80%;"> </iframe>

					</div>
					<div class="line_grid" style="float: right">
						<div class="g_12">
							<input type="button" value="返回" class="simple_buttons"
								style="height: 40px; width: 100px;background: #FF5511;color: #FFFFFF"
								onclick="location.href='<%=basePath%>teacher/teacherIndex'">
						</div>
					</div>
				</div>
				<!-- Separator -->
				<div class="g_12 separator">
					<span></span>
				</div>
	
	    </div></div>
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