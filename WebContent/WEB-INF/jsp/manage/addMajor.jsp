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

<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath_userslib%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>新增学院</title>
<script type="text/javascript">
/*资讯-删除*/
function sumit(majorform) {

	$.ajax({
		type : 'get',
		dataType : 'json',
		async: false,
		url : '<%=basePath%>manage/addMajor.action?id=' + majorform.id.value+'&Majorname='+majorform.Majorname.value+'',
		success : function(data) {
			if (data.success) {
				alert(data.msg);
				
			} else {
				
				layer.msg('操作失败，请重新操作', {
					icon : 6,
					time : 5000
				});		
			}
			
		},error : function() {
			layer.msg('操作失败，请重新操作', {
				icon : 6,
				time : 4000
			});	
			layer.msg('服务器异常，请稍候再试！', {
				icon : 6,
				time : 4000
			});	
		}
			});
		
}
</script>
</head>
<body>
	<div class="pd-30">
		<form class="form-horizontal" method="post"
			action="" name="basic_validate"
			id="basic_validate">

			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>专业名称：</label>
				<div class="formControls col-6">
					<!-- 隐藏值 学院id -->
					<input type="hidden" name="id" value="${major.cId }" />
					<input
						type="text" name="Majorname" value="${major.cMajorName }"
						 required="required" /> 
	
				</div>
			</div>
			<div class="row cl">
				<div class="col-10 col-offset-3">
				<input type="submit" value="保存" class="btn btn-primary radius" onclick="sumit(this.form)"/>
					
				</div>
			</div>
		</form>
	</div>
	
</body>
</html>