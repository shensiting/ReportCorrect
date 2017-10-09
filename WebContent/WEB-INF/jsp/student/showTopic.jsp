<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>问题显示</title>
</head>
<body>
	<div class="pd-30">

		<label style="float: left;">楼主（${stuName}）：</label>
		<div class="row cl">
			<div class="formControls col-11" style="margin-left: 5%;">${topic.cContent}</div>
		</div>
		<div class="row cl"
			style="margin-left: 70%; margin-bottom: 2%; width: 50%">
			<span style="color: gray;">实验：${exam} | 发表于：<fmt:formatDate
					type="both" value="${topic.cCreateTime}" /></span>
		</div>


		<c:forEach var="item" items="${topicResponses}">
			<hr style="color: #DDDDDD;">
			<label style="float: left; margin-top: 20px;">${item.user.cUserName}：</label>

			<div class="row cl" style="margin-top: 30px;">
				<label class="form-label col-2"></label>
				<div class="formControls col-11" style="margin-left: 5%;">${item.cContent}</div>
			</div>
			<div class="row cl"
				style="margin-left: 80%; margin-bottom: 2%; width: 50%">
				<span style="color: gray;">发表于：<fmt:formatDate type="both"
						value="${item.cCreateTime}" /></span>
			</div>
		</c:forEach>
		<div class="row cl" style="margin-top: 60px; margin-left: 8%">
			<label style="color: blue">注意事项：学号与姓名都有记录，请注意文明用语</label>

		</div>
		<form action="" method="post" class="form form-horizontal"
			id="form-article-add">
			<div class="row cl">
				<input value="${topic.cId}" id="qid" name="qid" type="hidden">
				<label class="form-label col-1">回复：</label>
				<div class="formControls col-11">
					<textarea id="container" name="content" style="width: 100%">${answer}</textarea>
				</div>
			</div>
		</form>
		<div class="row cl">
			<div class="col-11 col-offset-1">
				<input name="code" id="code" class="input-text size-l" type="text"
					placeholder="请输入验证码" style="width: 150px;"> <img id="img"
					src="<%=basePath%>manage/check"> <a id="take">看不清，换一个</a>
			</div>
			<div style="width: 20%; margin-left: 85%">
				<a class="btn btn-primary radius" onclick="submit()"
					href="javascript:;"> <i class="Hui-iconfont">&#xe632;</i>
					保存并提交审核
				</a>
			</div>
		</div>
		<div
			style="margin-top: 5%; text-align: center; font-size: 10px; color: gray;">
			<%@include file="../common/footer.jsp"%>
		</div>
	</div>

	<!-- 配置文件 -->
	<script type="text/javascript"
		src="<%=basePath%>ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript"
		src="<%=basePath%>ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#take").on("click",function(){
			var url = '<%=basePath%>manage/check?number='+Math.random()+'';  
		    $("#img").attr("src",url);  
		});
	});
	/*提交*/
	function submit() {										
		var code=$("#code").val();
		if(code==""){
			layer.alert("请输入验证码！");
		}
		else{		
			$.ajax({
				type : 'POST',
				dataType : 'json',
				async: false,
				url : '<%=basePath%>student/codeCheck?code='+code+'',
					success : function(data) {
						if (data.success) {						
					        	document.forms[0].action = '<%=basePath%>student/replyQus.action';
								document.forms[0].submit();
								} else {
									alert("验证码输入错误，请重新输入！");
								}
							},
							error : function() {
								alert("服务器异常，请稍候再试！");
							}
						});

			}
		}

		var ue = UE.getEditor('container', {
			initialFrameHeight : 200,
			toolbars : [ [ 'fullscreen', 'undo', 'redo', '|', 'bold', 'italic',
					'underline', 'fontborder', 'strikethrough', 'blockquote',
					'|', 'forecolor', 'backcolor', 'selectall', 'fontfamily',
					'fontsize', '|', 'link', 'unlink', '|', 'imagenone',
					'imageleft', 'imageright', 'imagecenter', '|',
					'simpleupload', 'emotion', 'preview' ] ]
		});
		ue.addListener('ready', function(editor) {
			ue.execCommand('drafts');
		});
		ue.ready(function() {
			ue.execCommand('drafts');
		});
	</script>
</body>
</html>