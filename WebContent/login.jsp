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
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<script src="<%=basePath%>manage/js/jquery.min.js"></script>

<script src="<%=basePath%>manage/js/md5.js"></script>
<link href="<%=basePath%>users/css/H-ui.min.css" rel="stylesheet" />
<link href="<%=basePath%>users/css/H-ui.login.css" rel="stylesheet" />
<link href="<%=basePath%>users/css/style.css" rel="stylesheet" />
<link href="<%=basePath%>users/css/iconfont.css" rel="stylesheet" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>后台登录 - H-ui.admin v2.3</title>
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
		url:'<%=basePath%>manage/check.action?number='+number+'&password='+password+'',
		success:function(data){
            if(data.success){    
            	var path='<%=basePath%>' + data.msg;
						document.forms[0].action = path;
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

	<div class="header"></div>
	<div class="loginWraper">
		<div id="loginform" class="loginBox">
			<form id="loginform" class="form form-horizontal" action=""
				method="post">
				<div class="row cl">
					<label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
					<div class="formControls col-8">
						<input id="number" name="number" value="${ScId }" placeholder="账户"
							required="required" class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
					<div class="formControls col-8">
						<input id="password" name="password" type="password"
							value="${ScPasswordd }" required="required" placeholder="密码"
							class="input-text size-L">
					</div>
				</div>
				<!--   <div class="row cl">
        <div class="formControls col-8 col-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
          <img src="images/VerifyCode.aspx.png"> <a id="kanbuq" href="javascript:;">看不清，换一张</a> </div>
      </div> 
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="online" value="">
            使我保持登录状态</label>
        </div>
      </div>-->
				<div style="margin-top: 40px" class="row">
					<div class="formControls col-8 col-offset-3">
						<input name="" type="submit" class="btn btn-success radius size-L"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"
							onclick="login(this.form)"> &nbsp;&nbsp; &nbsp;&nbsp;
						&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; <input
							name="" type="reset" class="btn btn-default radius size-L"
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