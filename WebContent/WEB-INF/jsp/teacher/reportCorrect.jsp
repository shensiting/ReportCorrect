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
<title>报告编辑</title>
<link id="artDialogSkin"
	href="<%=basePath %>users/htmlPostil/skin/facebook/facebook.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=basePath %>users/htmlPostil/artDialog.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>users/lib/jquery/1.9.1/jquery.min.js"></script>
<style type="text/css">
.content {
	border: 1px solid #ddd;
	width: 50%;
	padding: 5px;
	height: 100%;
	margin-bottom: 5px;
}

.list {
	border: 1px solid #ddd;
	height: 100%;
	width: 48%;
}

.list div {
	border: 1px solid #ddd;
	margin-bottom: 5px;
	padding: 10px;
	cursor: pointer;
}
/*css批注样式*/
.postil, .postilFocus {
	border-left: 1px solid red;
	border-right: 1px solid red;
	text-decoration: none;
	cursor: default;
}

.postil {
	background: yellow;
}

.postilFocus {
	background: Tomato;
}
/*批注提示图标样式*/
.tipsIcon {
	opacity: 0.80;
	z-index: 1;
	filter: alpha(opacity = 80);
}
</style>
<script type="text/javascript">

	$(function() {
		$("#submit").click(function() {
			
			var rid = $("#rid").val();
			var score = $("#score").val();
			var process = $("#process").val();
			var pattern = new RegExp(/^[0-9-+]+$/);
			var htmlForm= $("#postilForm").html();
			if (!pattern.test(score)) {
				layer.alert("报告成绩只能为数字！");
			}
			else if(score>100||score<0){
				layer.alert("报告成绩只能在0与100之间！");
			}else if(process==""){
				layer.alert("报告总评不能为空！");
			}else{
				layer.confirm(
						'确认要提交成绩和批注？最终成绩为：'+score+'',
						{
							btn : [ '是的', '放弃' ],
							shade : false
						},function(sender, modal, index) {
							$.ajax({
								type : 'POST',
								dataType : 'json',
								async: false,
								url : '<%=basePath%>teacher/reportCorrect.action',
								data: {rid:rid,score:score,process:process,htmlForm:htmlForm},
								success : function(data) {
									if (data.success) {
										alert(data.msg)
										location.href="<%=basePath%>teacher/reportManage";
									} else {
										alert(data.msg);	
									}								
								},error : function() {
									alert("服务器异常，请稍候再试！");
								}
							});
				
						});
			}

		});
	});
</script>
<script type="text/javascript"
	src="<%=basePath %>users/htmlPostil/postil.js"></script>
</head>
<body>
	<nav class="breadcrumb"> <a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-40">
		<div class="row cl" style="margin-bottom: 10px">
			<label class="form-label col-1">报告成绩：</label>
			<div class="formControls col-10">
				<input value="${rid}" id="rid" name="rid" type="hidden"> <input
					value="" id="score" name="score" class="input-text size-s"
					style="width: 20%">
			</div>
		</div>
		<div class="row cl" style="margin-bottom: 25px;">
			<label class="form-label col-1">报告总评：</label>
			<div class="formControls col-10" style="width: 91%">
				<textarea name="process" id="process" cols="" rows=""
					class="textarea" placeholder="说点什么......" dragonfly="true"
					onKeyUp="textarealength(this,200)">${process}</textarea>

			</div>
		</div>
		<div class="row cl" style="margin-bottom: 25px; font-size: 15px">
			<label class="form-label col-1" style="color: red">注意事项：</label>
			<div class="formControls col-10" style="width: 80%">
				<p>
					1.在左边文档中选中需要批注的文字<a style="color: red">(只能批注文字，请不要选择图片或表格，出错了只能退出重新再次批改)</a>，填写批注，在右边即可生成批注;
				</p>
				<p>2：在批改中请不要退出或者刷新本页面，否则内容会丢失</p>
			</div>
		</div>
		<div class="row cl" style="margin-bottom: 20px; font-size: 15px">

			<label class="form-label col-1">实验报告批改：</label>
		</div>

		<div id="postilForm">
			<div class="content"
				style="float: left; width: 50%; height: 650px; overflow-y: scroll;">
				${report}</div>
			<div class="list"
				style="height: 650px; float: right; color: blue; overflow-y: scroll;"></div>
		</div>

		<div class="row cl">
			<div class="col-10 col-offset-2" style="margin-left: 90%">
				<a class="btn btn-primary radius" id="submit" href="javascript:;">
					<i class="Hui-iconfont">&#xe632;</i> 保存并提交审核
				</a>
			</div>
		</div>
		<div style="margin-top: 5%; height: 35px">
			<%@include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>