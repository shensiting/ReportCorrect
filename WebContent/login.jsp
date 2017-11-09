<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=basePath%>users/lib/html5.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/PIE_IE678.js"></script>
<![endif]-->
<script src="<%=basePath%>users/js/jquery.min.js"></script>

<script src="<%=basePath%>users/js/md5.js"></script>
<link href="<%=basePath%>users/css/H-ui.min.css" rel="stylesheet" />
<link href="<%=basePath%>users/css/H-ui.login.css" rel="stylesheet" />
<link href="<%=basePath%>users/css/style.css" rel="stylesheet" />
<link href="<%=basePath%>users/css/iconfont.css" rel="stylesheet" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>学生实验报告登录</title>
<script type="text/javascript">

	$(function() {
		var flag1 = false;
		var flag2 = false;
		var flag3 = false;
		$("#number").blur(
		function() {
			var number = $("#number").val();
			if ($.trim(number) == ''
					|| $.trim(number).length < 4
					|| $.trim(number).length > 15) {
				$("#userspan").html("<font color='red'>用户名不能为空，且用户名长度为5-15</font>");
				flag1 = false;
			} else{
				$.ajax({
					type : 'POST',
					dataType : 'json',
					async : false,
					url : '<%=basePath%>manage/checkUser?number='+number,
					success:function(data){
			            if(data.success){    
			            	  flag2 = true;		
			            	  $("#userspan").html("");
			            }else{
			            	flag2=false;
			            	$("#userspan").html("<font color='red'>用户名不存在</font>");
			            }
					 }//,
					//error : function() {
					//	 flag2 = false;
					//	alert("服务器异常，请稍候再试！");				
					//}
						
				});
			}
	    });
  
		
function checkPwd(number,password) {	
		password=md5(password);	
		var flag=0;
		$.ajax({
			type : 'POST',
			dataType : 'json',
			async : false,
			url : '<%=basePath%>manage/checkPassword?number='+number+'&password='+password+'',
			success:function(data){
	            if(data.success){    	            	 	            	            	  
	            	 flag=1;
	            }else{	            	
	            	$("#pswspan").html("<font color='red'>密码错误</font>");
	            	
	            }
			 }//,
			//error : function() {				
			//	alert("服务器异常，请稍候再试！");					
			//}				
		});
    return flag;
	}

				

		$("#formButton").click(function() {
			var code = $("#code").val();
			var number = $("#number").val();
			var password = $("#password").val();
			if ($.trim(password) == "") {
				$("#pswspan").html("<font color='red'>密码不能为空</font>");
				flag2 = false;
			}
			else if ($.trim(code) == "") {
				$("#codespan").html("<font color='red'>验证码不能为空</font>");				
			} else {											
				$.ajax({
					type : 'POST',
					dataType : 'json',
					async: false,
					url : '<%=basePath%>student/codeCheck?code='+code+'',
						success : function(data) {
							if (data.success) {									
								if (checkPwd(number,password)=='1') {
									document.forms[0].submit();
									//$("#codespan").html("<font color='red'>登录成功</font>");	
								}else{
									$("#pswspan").html("<font color='red'>密码填写错误</font>");
								}
							}else{	
								var url = '<%=basePath%>manage/check?number='+Math.random()+'';  
							    $("#img").attr("src",url);  
								$("#codespan").html("<font color='red'>验证码填写错误</font>");	
							}
						}//,
						//error : function() {
						//	 flag3= false;
						//	alert("服务器异常，请稍候再试！");
						//}
					});			
			}			
			
		})

	});



$(function(){
	$("#take").on("click",function(){
		var url = '<%=basePath%>manage/check?number='+Math.random()+'';  
	    $("#img").attr("src",url);  
	});
});
    </script>
</head>
<body>

	<div class="header"></div>
	<div class="loginWraper">
		<div id="loginform" class="loginBox">
			<form id="loginform" class="form form-horizontal"
				action="<%=basePath %>manage/login.action" method="post">
				<div class="row cl">
					<label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
					<div class="formControls col-6">
						<input id="number" name="number" value="${ScId }" placeholder="账户"
							required="required" class="input-text size-L"> <span
							id="userspan"></span>
					</div>

				</div>

				<div class="row cl">
					<label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
					<div class="formControls col-6">
						<input id="password" name="password" type="password"
							value="${ScPasswordd }" required="required" placeholder="密码"
							class="input-text size-L"><span id="pswspan"></span>
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-8 col-offset-3">
						<input name="code" id="code" class="input-text size-L"
							required="required" type="text" placeholder="请输入验证码"
							style="width: 150px;"> <img id="img"
							src="<%=basePath%>manage/check"> <a id="take">看不清，换一个
							&nbsp;&nbsp;&nbsp;</a> <span id="codespan"></span>
					</div>

				</div>

				<!--  <div class="row">
    <div class="formControls col-8 col-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="online" value="">
            使我保持登录状态</label>
        </div>  
      </div>-->
				<div style="margin-top: 40px" class="row">
					<div class="formControls col-8 col-offset-3">
						<input name="formButton" id="formButton" type="button"
							class="btn btn-success radius size-L"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
						&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
						&nbsp;&nbsp; &nbsp;&nbsp; <input name="" type="reset"
							class="btn btn-default radius size-L"
							value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="footer">
		2016 &copy; Design By <a style="color: #FFFFFF"> Crazy Code </a>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>users/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>users/js/H-ui.js"></script>
</body>
</html>