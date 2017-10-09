<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="../common/userslib.jsp"%>
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=basePath%>users/lib/html5.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/PIE_IE678.js"></script>
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>新增文章</title>
</head>
<body>
	<div class="pd-30">
		<form action="" method="post" class="form form-horizontal"
			id="form-article-add">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>原始密码：</label>
				<div class="formControls col-6">
					<input type="password" class="input-text" placeholder=""
						name="oldPassword">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>密码：</label>
				<div class="formControls col-6">
					<input type="password" class="input-text" placeholder=""
						name="password">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>确认密码：</label>
				<div class="formControls col-6">
					<input type="password" class="input-text" placeholder=""
						name="repassword">
				</div>
			</div>
			<div class="row cl">
				<div class="col-10 col-offset-3">
					<button onclick="passwordChange(this.form)"
						class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 保存并提交
					</button>
				</div>
			</div>
		</form>
		<div
			style="margin-top: 5%; text-align: center; font-size: 10px; color: gray;">
			<footer> Copyright &copy; 2017, Crazy Code, All Rights
			Reserved </footer>
		</div>
	</div>
	<script type="text/javascript">

//校验密码：只能输入6-20个字母、数字、下划线   
function isPasswd(passwordString){   
var patrn=/^(\w){6,20}$/;   
return patrn.exec(passwordString);
}   
function passwordChange(registeform){
	 var oldPassword=registeform.oldPassword.value;
	 var password=registeform.password.value;
   var  repassword=registeform.repassword.value;
   if(password==""||oldPassword==""||repassword==""){
		alert("密码不能为空！   ");	
	}
   else if(!isPasswd(password)||!isPasswd(oldPassword)||!isPasswd(repassword)){
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
			url:'<%=basePath%>student/passwordChange.action?password='+password+'&oldPassword='+oldPassword+'',
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
</script>
</body>
</html>