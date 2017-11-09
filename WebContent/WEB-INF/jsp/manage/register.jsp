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
<title>注册</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=basePath%>manage/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=basePath%>manage/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath%>manage/css/uniform.css" />
<link rel="stylesheet" href="<%=basePath%>manage/css/select2.css" />
<link rel="stylesheet" href="<%=basePath%>manage/css/unicorn.main.css" />
<link rel="stylesheet" href="<%=basePath%>manage/css/unicorn.grey.css"
	class="skin-color" />

<!-- validate -->
<script src="<%=basePath%>/manage/js/jquery.min.js"></script>
<script src="<%=basePath%>/manage/js/jquery.ui.custom.js"></script>
<script src="<%=basePath%>/manage/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/manage/js/unicorn.js"></script>
<link rel="stylesheet" href="<%=basePath%>manage/css/unicorn.login.css" />
<script src="<%=basePath%>manage/js/md5.js"></script>
<!-- Dialog Js -->
<script src="<%=basePath%>js/jquery.bootstrap.teninedialog.v3.min.js"></script>
<script type="text/javascript">

//校验密码：只能输入6-20个字母、数字、下划线   
function isPasswd(passwordString){   
  var patrn=/^(\w){6,20}$/;   
  return patrn.exec(passwordString);
}   
//校验账号是否全由数字组成
function isDigit(number){ 
  var patrn=/^[0-9]{1,20}$/; 
  return patrn.exec(number);
}

function studentRegister(registeform){
	var cId =registeform.ScId.value;
	var cName=registeform.ScName.value;
	var cPassword=registeform.ScPassword.value;
	var cYearClass=registeform.ScYearClass.value;
	var grade=document.getElementById(cYearClass).text;;
	if(cId==""||cPassword==""||cName==""||grade==""){
		alert("信息不能为空。");
	}
	else if(!isPasswd(cPassword)){
		alert("密码只能为6-20个字母、数字、下划线   ");			
	}
	else if(!isDigit(cId)){
		alert("学号只能为数字 ");			
	}
	else{		
			var statu=confirm( "填写信息为：\n学号：" + cId + "\n姓名："+cName+"\n密码："+
					cPassword+"\n班级："+grade+"\n提交后将不能修改！");		
			
			if(statu){
			    	cPassword=md5(cPassword);
					$.ajax({
						type : 'get',
						dataType : 'json',
						async: false,
						url:'<%=basePath%>manage/Studentregister.action?cId='+cId+'&cPassword='+cPassword
						+'&cYearClass='+cYearClass+'&cName='+cName,
						success : function(data) {
							if (data.success) {		             						   								
								document.forms[0].action ='<%=basePath%>login.jsp';
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
}
function teacherRegister(registeform){
	var cId =registeform.TcId.value;
	var cPassword=registeform.TcPassword.value;
	var cCollege=registeform.TcCollege.value;
	var cName=registeform.TcName.value;
	var college=document.getElementById(cCollege).text;
	if(cId==""||cPassword==""||cName==""||college==""){
		alert("信息不能为空。");
	}
	else if(!isPasswd(cPassword)){
		alert("密码只能为6-20个字母、数字、下划线   ");	
	}
	else if(!isDigit(cId)){
		alert("工号只能为数字 ");	
	}
	else{
		var statu=confirm( "填写信息为：\n工号：" + cId + "\n姓名："+cName+"\n密码："+cPassword+"\n学院："+college+"\n提交后将不能修改！");		
		if(statu){
			cPassword=md5(cPassword);
		$.ajax({
			type:'get',	
			dataType:'json',
			async: false,
			url:'<%=basePath%>manage/Teacherregister.action?cId='+cId+'&cPassword='+cPassword+'&cCollege='+cCollege+'&cName='+cName,
			success:function(data){
			            if(data.success){   
			                       alert("注册成功，请耐心等管理员验证身份之后登陆。\n时间一般为一天。");

								} else {
									alert(data.msg);
									//$.teninedialog({
									//title : '系统提示',
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
	}
</script>
</head>
<body>
	<div id="logo" style="padding-top: 1%">

		<h2 align="center" style="color: #FFF">广州医科大学生物技术系</h2>
		<h2 align="center" style="color: #FFF">学生报告管理系统</h2>
	</div>
	<div id="loginbox" style="height: 394px; width: 450px">

		<form id="loginform" class="form-vertical" action="" method="post">
			<h1 style="color: #616161;">注册</h1>

			<div class="widget-box">
				<div class="widget-title">
					<ul class="nav nav-tabs">
						<li class="active" style="width: 50%"><a data-toggle="tab"
							href="#tab1">学生</a></li>
						<li style="width: 50%"><a data-toggle="tab" href="#tab2">教师</a></li>

					</ul>
				</div>
				<div class="widget-content tab-content">
					<div id="tab1" class="tab-pane active">

						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-user"></i></span><input
										name="ScId" type="text" placeholder="学号" />
								</div>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-user"></i></span><input
										name="ScName" type="text" placeholder="姓名" />
								</div>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-lock"></i></span><input
										name="ScPassword" type="password" placeholder="密码" />
								</div>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-pencil"></i></span> <select
										name="ScYearClass">
										<c:forEach var="item" items="${gradeMajorColleges }">
											<option id="${item.getcId()}" value="${item.getcId()}">${item.getcYearClass()}
												${item.getMajor().getcMajorName()} &nbsp;${item.getcClass()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div style="text-align: left">
								<p style="color: #7B68EE; font-size: 13px;">温馨提示：请使用谷歌浏览器</p>
							</div>
						</div>

						<div class="form-actions">
							<span class="pull-left"><a href="<%=basePath%>login.jsp"
								class="flip-link" id="to-recover">登录</a></span> <span
								class="pull-right"> <input type="submit"
								class="btn btn-inverse" value="注册"
								onclick="studentRegister(this.form)" />
							</span>
						</div>


					</div>
					<div id="tab2" class="tab-pane">

						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-user"></i></span><input
										name="TcId" type="text" placeholder="工号" />
								</div>
							</div>
						</div>

						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-user"></i></span><input
										name="TcName" type="text" placeholder="姓名" />
								</div>
							</div>
						</div>

						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-lock"></i></span><input
										name="TcPassword" type="password" placeholder="密码" />
								</div>
							</div>
						</div>

						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-home"></i></span> <select
										name="TcCollege">
										<c:forEach var="item" items="${colleges}">
											<option id="${item.cId}" value="${item.cId}">${item.cCollegeName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div style="text-align: left">
								<p style="color: #7B68EE; font-size: 13px;">温馨提示：请使用谷歌浏览器</p>
							</div>
						</div>

						<div class="form-actions">
							<span class="pull-left"><a href="<%=basePath%>login.jsp"
								class="flip-link" id="to-recover">登录</a></span> <span
								class="pull-right"> <input type="submit"
								class="btn btn-inverse" value="注册"
								onclick="teacherRegister(this.form)" />
							</span>
						</div>
					</div>
				</div>
			</div>
		</form>

	</div>

</body>
</html>