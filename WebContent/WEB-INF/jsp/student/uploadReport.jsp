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
<link href="<%=basePath%>users/lib/icheck/icheck.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>users/lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>users/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>新增报告</title>
<script type="text/javascript">
$(function(){  
    //触发的下拉框chang事件  
    $("#test").change(function(){  
        var test = $("#test").val();  
        $.ajax({  
            type:"POST",  
            url :"../student/checkHtml.action",  
            data:{                    
                id:test  
            },  
            dataType:"json",  
            success:function(data){  
               //返回true，代表不允许上传此实验
            	if(data.mes!=""){
            		alert(data.mes);
            		//此处作用是当用户连续选择实验错误后，会导致selected一直处于在第一个被选中，解决出错问题使用
            		 $("#test").find("option").eq(0).attr("selected",true);
            		 $("#test").find("option").eq(0).attr("selected",false);
            	}
            	
            }  
        });  
    });                                       
}); 

function load(){         
	var test= $("#test").val(); 
	var process= $("#process").val(); 
	var content=$("#content").val(); 
	if(test==""){
		alert("请选择实验！");
	}
	else if(process==""){
		alert("实验过程不能为空！");
	}
	else if(content==""){
		alert("实验不能为空！");
	}
	else if (process.length> 100||process.length<=10) {
			alert('报告过程字数不合格，请修改后上传');
		}
	else{
		document.forms[0].action='<%=basePath %>student/uploadExperiment.action';
		document.forms[0].submit(); 
		document.getElementById('loading').style.display='block';
	}
	
}

</script>
<style>
.black_overlay {
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
	color: white;
}
</style>
</head>
<body>

	<div class="pd-20">
		<!--弹出层时背景层DIV-->
		<div id="loading" class="black_overlay" style="display: none;">
			<div style="margin: auto; text-align: center; padding-top: 30%">
				<p>
					<img src="<%=basePath%>users/images/loading_072.gif" />
				</p>
				<p>正在上传中，请耐心等待...</p>
			</div>
		</div>
		<form action="" method="post" class="form form-horizontal"
			id="form-article-add">
			<div class="row cl">
				<label class="form-label col-1">选择实验：</label>
				<div class="formControls col-5">
					<span class="select-box"> <select id="test" name="test"
						class="select">
							<option value="">---请选择实验---</option>
							<c:forEach var="item" items="${tests}">
								<option value="${item. getcExperimentId()}">${item.getExperiment().getcExperimentName()}</option>
							</c:forEach>
					</select>
					</span>
				</div>

			</div>
			<div class="row cl">
				<label class="form-label col-1">报告过程：</label>
				<div class="formControls col-10">
					<textarea name="process" id="process" cols="" rows=""
						class="textarea" placeholder="说点什么...最少输入10个字符" datatype="*10-100"
						dragonfly="true" onKeyUp="textarealength(this,200)">${process}</textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/50
					</p>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-1">注意事项：</label>1.在上传作业之前请先把照片上传。2.请根据老师要求选择具体上传形式：<a
					onclick="article_add('报告上传','<%=basePath%>student/uploadWord','1000','600')"
					class="" style="color: blue;"> 选择word文档实验上传 </a>。3.若提交失败数据清空，刷新本页面就可以重新出来
			</div>
			<div class="row cl">
				<label class="form-label col-1">报告内容：</label>
				<div class="formControls col-10">
					<textarea id="container" name="content"
						style="height: 400px; width: 100%">
                    </textarea>
				</div>
			</div>

			<div class="row cl">

				<div class="col-10 col-offset-2" style="margin-left: 80%">
					<a class="btn btn-primary radius" onclick="load()"
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

	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		var ue = UE.getEditor('container');

		ue.addListener('ready', function(editor) {
			ue.execCommand('drafts');
		});
		ue.ready(function() {
			ue.execCommand('drafts');
		});
		function article_add(title, url, w, h) {
			layer_show(title,url,w,h)
		}
	</script>

</body>
</html>