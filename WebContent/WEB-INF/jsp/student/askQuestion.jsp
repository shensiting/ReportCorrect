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
<title>提问</title>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal"
			id="form-article-add">
			<div class="row cl">
				<label class="form-label col-1">实验分类：</label>
				<div class="formControls col-5">
					<span class="select-box"> <select id="test" name="test"
						class="select">
							<option value="">----请选择实验分类----</option>
							<c:forEach var="item" items="${tests}">
								<option value="${item.cId }">${item.cTestName}</option>
							</c:forEach>
					</select>
					</span>
				</div>
				<label class="form-label col-1">实验：</label>
				<div class="formControls col-5">

					<span class="select-box"> <select id="experiment"
						name="experiment" class="select">
							<option value="">----请选择实验----</option>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-1">标题：</label>
				<div class="formControls col-11">

					<input value="" placeholder="字数在100以内" id="title" name="title"
						class="input-text size-s">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-1">内容：</label>
				<div class="formControls col-11">
					<textarea id="container" name="content" style="width: 100%">
                    </textarea>
				</div>
			</div>
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

		</form>
		<div style="margin-top: 5%; height: 35px">
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
	$(function(){  
	    //触发的下拉框chang事件  
	    $("#test").change(function(){  
	        var test = $("#test").val();  
	        $.ajax({  
	            type:"POST",  
	            url :"../teacher/getRelevance",  
	            data:{                    
	                id:test  
	            },  
	            dataType:"json",  
	            success:function(data){  
	                $("#experiment").empty();   
	                $.each(data,function(index,item){                        
	                    //填充内容  
	                    $("#experiment").append( "<option value='"+item.cId+"'>"+item.cExperimentName+"</option>");  
	                });  
	            }  
	        });  
	    });                                       
	});  
	/*提交*/
	function submit() {
		var experiment =$("#experiment").val();
		var title =  $("#title").val();						
		var code=$("#code").val();
		if(experiment==""){
			layer.alert("实验不允许为空！");
		}else if(title==""){
			layer.alert("标题不允许为空！");
		}else if(title.length>100){
			layer.alert("标题字数超过限制！");
		}else if(code==""){
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
							document.forms[0].action = '<%=basePath%>student/askQuestion.action';		
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
	
	var ue =UE.getEditor('container', {
			initialFrameHeight : 200
			,toolbars : [ [ 'fullscreen', 'undo', 'redo', '|', 'bold', 'italic',
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