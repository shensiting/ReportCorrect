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
/*$(function(){  
    //触发的下拉框chang事件  
    $("#experiment").change(function(){  
        var experiment = $("#experiment").val();  
        $.ajax({  
            type:"POST",  
            url :"../student/checkWord.action",  
            data:{                    
                id:experiment  
            },  
            dataType:"json",  
            success:function(data){  
               //返回true，代表不允许上传此实验
            	if(data.mes!=""){
            		alert(data.mes);
            	}
            }  
        });  
    });                                       
}); 
*/
function load(){         
    	var score= $("#score").val(); 
    	var process= $("#process").val(); 
    	var file=$("#file").val(); 
    	var pattern = new RegExp(/^[0-9-+]+$/);
    	if (!pattern.test(score)) {
			layer.alert("报告成绩只能为数字！");
		}else if(score>100||score<0){
			layer.alert("报告成绩只能在0与100之间！");
		}
    	else if(process==""){
    		layer.alert("实验总评不能为空！");
    	}
    	else if(file==""){
    		layer.alert("实验不能为空！");
    	}    	   	
    	else if (file.lastIndexOf(".doc")==-1&&file.lastIndexOf(".docx")==-1) {
    		layer.alert('文件格式错误，请重新选择！');
    		}
    	else{
    		document.forms[0].action='<%=basePath%>teacher/wordCorrect.action';
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
	<nav class="breadcrumb"> <a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
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
		<form enctype="multipart/form-data" action="" method="post"
			id="reportFrom" class="form form-horizontal" id="form-article-add">
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
			<div class="row cl">
				<label class="form-label col-1" style="color: red">注意事项：</label>
				<div class="formControls col-10" style="width: 80%">
					<p>1.下载学生实验报告，进行批改，批改后上传实验;</p>
					<p>2：在批改中请不要退出或者刷新本页面，否则内容会丢失</p>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-1">学生报告：</label>
				<div class="formControls col-10">

					<a id="edit" style="color: blue"
						href="<%=basePath %>student/downLoadWord?id=${rid}" title="下载">下载学生实验报告</a>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-1">报告文档：</label>
				<div class="formControls col-10">
					<span class="btn-upload form-group"> <input
						class="input-text upload-url" type="text" name="uploadfile-2"
						id="uploadfile-2" readonly="" datatype="*" nullmsg="请添加附件！"
						style="width: 200px"> <a href="javascript:void();"
						class="btn btn-primary upload-btn"><i class="Hui-iconfont"></i>
							浏览文件</a> <input type="file" multiple="" id="file" name="file"
						class="input-file">
					</span>
					<!--<input type="file" name="file" id="file" class="simple_form" required="required" />-->
					<span style="color: gray;">文件格式为doc或docx格式，最大限度: 10Mb</span>
				</div>
			</div>
			<div class="row cl">
				<div class="col-10 col-offset-2" style="margin-left: 81%">
					<a id="load" class="btn btn-primary radius" onclick="load()"
						href="javascript:;"><i class="Hui-iconfont">&#xe632;</i>
						确认上传实验</a>
					<!-- onclick="submit(this.form)" -->
				</div>

			</div>
		</form>
		<div style="margin-top: 5%; height: 35px">
			<%@include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>