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
<script type="text/javascript" src="<%=basePath%>users/lib/html5.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/PIE_IE678.js"></script>
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
<title>新增报告</title>
<script type="text/javascript">
//一级引起二级的变化  
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
                    $("#experiment").append("<option value=''>----请选择实验----</option>");  
                    $.each(data,function(index,item){                        
                        //填充内容  
                        $("#experiment").append( "<option value='"+item.cId+"'>"+item.cExperimentName+"</option>");  
                    });  
                }  
            });  
        });                                       
    });  
</script>
</head>
<body>

	<div class="pd-20">
		<form action="<%=basePath %>student/uploadExperiment.action" method="post" class="form form-horizontal"
			id="form-article-add">
			<nav class="breadcrumb">					
					请选择所要关联的班级 <a class="btn btn-success radius r mr-20"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:location.replace(location.href);" title="刷新"><i
						class="Hui-iconfont">&#xe68f;</i></a>
				</nav>
				<div class="row cl">
				<label class="form-label col-1">学院：</label>
				<div class="formControls col-5">
					<span class="select-box"> <select id="college" name=""college"" class="select">
					        <option value="">----请选择学院----</option>	
							<c:forEach var="item" items="${colleges}">
								<option value="${item.cId }">${item.cCollegeName}</option>
							</c:forEach>
					</select>
					</span>
				</div>
				<label class="form-label col-1">班级：</label>
				<div class="formControls col-5">
					<span class="select-box"> <select id="grade" name=grade class="select">
							<option>----请选择班级----</option>							
					</select>
					</span>
				</div>
			</div>
			<div class="row cl" style="text-align: right;padding-right: 7%">
				<button style="width: 120px" onClick="article_save();"
					class="btn btn-secondary radius" type="button">提交</button>
			</div>
			<div style="margin-top: 100px;margin-bottom: 30px"></div>
				<nav class="breadcrumb">					
					请选择班级所要关联的实验 <a class="btn btn-success radius r mr-20"
						style="line-height: 1.6em; margin-top: 3px"
						href="javascript:location.replace(location.href);" title="刷新"><i
						class="Hui-iconfont">&#xe68f;</i></a>
				</nav>
			<div class="row cl">
				<label class="form-label col-1">实验分类：</label>
				<div class="formControls col-5">
					<span class="select-box"> <select id="test" name="test" class="select">
					        <option value="">----请选择实验分类----</option>	
							<c:forEach var="item" items="${tests}">
								<option value="${item.cId }">${item.cTestName}</option>
							</c:forEach>
					</select>
					</span>
				</div>
				<label class="form-label col-1">实验：</label>
				<div class="formControls col-5">
				
					<span class="select-box"> <select id="experiment" name="experiment" class="select">
							<option>----请选择实验----</option>							
					</select>
					</span>
				</div>
			</div>
			
		
			<div class="row cl">
				<div class="row cl" style="text-align: right;padding-right: 7%">
				<button style="width: 120px" onClick="article_save();"
					class="btn btn-secondary radius" type="button">提交</button>
			</div>
					
				</div>
			</div>
		</form>
	</div>
	

	
	
</body>
</html>