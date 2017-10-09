<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/userslib.jsp"%>
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
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=basePath_userslib%>users/lib/html5.js"></script>
<script type="text/javascript" src="<%=basePath_userslib%>users/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath_userslib%>users/lib/PIE_IE678.js"></script>
<![endif]-->


<link href="<%=basePath_userslib%>users/lib/icheck/icheck.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath_userslib%>users/lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath_userslib%>users/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath_userslib%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>报告编辑</title>
<script type="text/javascript">
function sumit(reportform) {
	var content = reportform.content.value;
	 var process =reportform.process.value;
	 var test = reportform.test.value;
	 
	 if (process.length> 50||process.length<=10) {
			alert('报告过程字数不合格，请修改后上传');
		}
	 else{			
		
			$.ajax({
				type : 'GET',
				dataType : 'json',
				async: false,
				url : '<%=basePath%>student/updateExperiment.action?content='+content+'&process='+process+'&test='+test+'',				
				success : function(data) {
					if (data.success) {
						alert("修改成功！");							
					} else {	
						alert("操作失败，请重新操作!");							
					}
					
				},error : function() {
					alert("操作失败，请重新操作!");							
				}
			});
			
	 }
		
}
</script>
</head>
<body>

	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal"
			id="form-article-add">
			<div class="row cl" style="text-align: left;">
				<label class="form-label col-1">实验：</label>
				
					<label class="form-label col-1">${experimentName}</label>
				<input name="test" id="test" type="hidden" value="${test}" />

			</div>
			<div class="row cl">
				<label class="form-label col-1">报告过程：</label>
				<div class="formControls col-10">
					<textarea name="process" id="process" cols="" rows="" class="textarea"
						placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true"
					 onKeyUp="textarealength(this,200)">${report.getcProcess()}</textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/50
					</p>
				</div>
			</div>
			<div class="row cl"><label class="form-label col-1">注意事项：</label>需要使用表格时请选中表格，右键表格，选择设置表格属性，设置颜色，否则表格边线默认为透明。若提交失败数据清空，刷新本页面就可以重新出来</div>
			<div class="row cl">
				<label class="form-label col-1">报告内容：</label>
				<div class="formControls col-10">
					<textarea  id="container" name="content"  style="height: 400px;width: 100%">
					${report.getcPath()}
                    </textarea>
				</div>
			</div>
			<div class="row cl">
				<div class="col-10 col-offset-2" style="margin-left: 80%">
					<button class="btn btn-primary radius" type="submit" onclick="sumit(this.form)">
						<i class="Hui-iconfont">&#xe632;</i> 保存并提交审核
					</button>
					<!--  <button onClick="article_save();" class="btn btn-secondary radius"
						type="button">
						<i class="Hui-iconfont">&#xe632;</i> 保存草稿
					</button>-->
					
				</div>
			</div>
		</form>
	</div>
	<!-- 配置文件 -->
	<script type="text/javascript" src="<%=basePath%>ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="<%=basePath%>ueditor/ueditor.all.min.js"></script>

	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		var ue = UE.getEditor('container');
	</script>
	
</body>
</html>