<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>作业统计</title>

<script type="text/javascript">

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
</script>
</head>
<body>
	<nav class="breadcrumb"> <a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20">
		<div id="addinfo" class="pd-20">
			<form class="form-horizontal" method="post" action=""
				name="basic_validate" id="basic_validate">
				<div class="row cl">
					<label class="form-label col-2">实验分类：</label>
					<div class="formControls col-4">
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
					<div class="formControls col-4">

						<span class="select-box"> <select id="experiment"
							name="experiment" class="select">
								<option value="">----请选择实验----</option>
						</select>
						</span>
					</div>
				</div>
				<div style="margin-top: 2%;"></div>
				<div class="row cl">
					<label class="form-label col-2">关联班级：</label>
					<div class="formControls col-4">
						<span class="select-box"> <select name="cGradeId"
							id="cGradeId" class="select" size="1" datatype="*"
							nullmsg="请选择隶属班级！">
								<c:forEach var="item" items="${gradeMajorColleges }">
									<option value="${item.cId}">${item.getcYearClass()}
										${item.getMajor().getcMajorName()} ${item.getcClass()}</option>
								</c:forEach>
						</select></span>
					</div>
					<div style="width: 40%; text-align: right; float: left;">
						<a style="width: 100px; margin-right: 50%" type="submit"
							class="btn btn-primary radius" onclick="exportExamAccount()"
							id="" href="javascript:;" name="">统计</a>
					</div>
				</div>

			</form>
		</div>

		<hr style="height: 1px; border: none; border-top: 1px solid #C0C0C0;" />
		<div class="pd-30">
			<div class="row cl" id="result"
				style="text-align: left; margin-left: 40%; width: 40%; font-size: 16px;">请选择条件...</div>
		</div>
		<!-- 底部 -->
		<div
			style="margin-top: 5%; text-align: center; font-size: 10px; color: gray;">
			<footer> Copyright &copy; 2017, Crazy Code, All Rights
			Reserved </footer>
		</div>
	</div>

	<script type="text/javascript"
		src="<%=basePath%>users/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>users/js/jquery.dataTables.min.js"></script>


	<script type="text/javascript">
		$('.table-sort').dataTable({
			"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
			"bStateSave" : true,//状态保存
			"aoColumnDefs" : [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{
				"orderable" : false,
				"aTargets" : [ 0, 5 ]
			} // 制定列不参与排序
			]
		});
	
	
		//导出函数
		function exportExamAccount() {
		   var cGradeId = $("#cGradeId").val();  
		   var experiment = $("#experiment").val();  
		   if(experiment==""){
			   layer.alert("请选择实验！");
		   }else if(cGradeId==""){
        	   layer.alert("请选择班级！");
           }else{        	 
        	   $.ajax({
					type : 'post',
					dataType : 'json',
					url : '<%=basePath%>teacher/checkRelated',
					data:{
						cGradeId:cGradeId,
						experiment:experiment
					},
					success : function(data) {
						if (data.success) {							
							getMes(cGradeId,experiment);							
						} else {
							$("#result").html(""); 	
							alert("该实验与班级为曾关联，请重新选择！");	
						}
						
					},error : function() {
						alert("服务器异常，请稍候再试！");
					}
				});
           }
           
		}
		
		function getMes(cGradeId,experiment){
			var str="";
			  $.ajax({
					type : 'post',
					dataType : 'json',
					url : '<%=basePath%>teacher/exportExamAccount',
					data:{
						cGradeId:cGradeId,
						experiment:experiment
					},
					success : function(data) {
						 if(data==""){
					    	  str="暂无数据";
					    	  $("#result").append(str); 
						 }
						 else{
							$("#result").html(""); 								 
							str+="班级总人数为："+data[0]["sum"]+"<br>";
							str+="实验提交人数为："+data[0]["submitStu"]+"<br>";
							str+="未提交人数为："+data[0]["noSubmit"]+"<br>";
							if(data.length>1){
							str+="未提交学生名单：<br>学号&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; 姓名<br>";
							}
							$("#result").append(str); 
							for(var i=1,l=data.length;i<l;i++){
							   str=data[i]["stuNum"]+"&nbsp;&nbsp;"+data[i]["name"]+"<br>";
                             $("#result").append(str); 
							    }

						 }
						
					},error : function() {
						alert("服务器异常，请稍候再试！");
					}
				});
		}
		
	
	
	</script>
</body>
</html>