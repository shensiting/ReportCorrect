<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="<%=basePath%>manage/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=basePath%>manage/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath%>manage/css/unicorn.login.css" />
<script src="<%=basePath%>manage/js/jquery.min.js"></script>
<script src="<%=basePath%>manage/js/jquery.ui.custom.js"></script>
<script src="<%=basePath%>manage/js/bootstrap.min.js"></script>
<script src="<%=basePath%>manage/js/md5.js"></script>
<!-- Dialog Js -->
<script src="<%=basePath%>/js/jquery.bootstrap.teninedialog.v3.min.js"></script>
<script type="text/javascript">
function login(loginform){//传入表单参数  
	var number=loginform.number.value;
	var password=md5(loginform.password.value);
	//var password=md5(md5(loginform.password.value));
    if(number!=""&&password!=""){  
    $.ajax({
    	type : 'get',
		dataType : 'json',	
		async: false,
		url:'<%=basePath%>manage/check.action?number='+number+'&password='+password,
		success:function(data){
            if(data.success){    
            	var path='<%=basePath%>' + data.msg;
						document.forms[0].action = path;
						document.forms[0].submit();
					} else {
						alert(data.msg);
						//$.teninedialog({
						//	title : '系统提示',
						//	content : data.msg
						//});
					}
				},
				error : function() {
					alert("服务器异常，请稍候再试！");					
					//$.teninedialog({
					//	title : '系统提示',
					//	content : '服务器异常，请稍候再试！'
					//});
				}
			});
		}
	}
	
</script>
</head>
<body>
	<div id="logo" style="padding-top: 7%">

		<h2 align="center" style="color: #FFF">广州医科大学生物技术系</h2>
		<h2 align="center" style="color: #FFF">学生报告管理系统</h2>
	</div>
	<div id="loginbox">
		<form id="loginform" class="form-vertical" action="" method="post">
			<p style="color: #616161; font-size: 16px">请输入账号和密码</p>
			<div class="control-group">
				<div class="controls">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-user"></i></span><input
							name="number" type="text" placeholder="账号" required="required" value="${ScId }" />
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-lock"></i></span><input
							name="password" type="password" placeholder="密码"
							required="required" value="${ScPasswordd }"  />
					</div>
				</div>
			</div>

			<div class="form-actions">
				<span class="pull-left"><a href="<%=basePath%>manage/register">还没有账号？注册</a>
				</span>
				 <span class="pull-right">
				  <input type="submit" class="btn btn-inverse" value="登录"
					onclick="login(this.form)" /></span>
			</div>
		</form>
		
	</div>
</body>
</html>