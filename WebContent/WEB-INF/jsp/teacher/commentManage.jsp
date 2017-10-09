<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en-US"> <![endif]-->
<!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en-US"> <![endif]-->
<!--[if IE 8]>    <html class="lt-ie9" lang="en-US"> <![endif]-->
<!--[if gt IE 8]><!-->

<!--<![endif]-->
<head>
<!-- META TAGS -->
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>评论管理</title>

<!-- Style Sheet-->

<link rel='stylesheet' id='bootstrap-css-css'
	href='<%=basePath%>users/topic/css/bootstrap5152.css?ver=1.0'
	type='text/css' media='all' />

<link rel='stylesheet' id='main-css-css'
	href='<%=basePath%>users/topic/css/main5152.css?ver=1.0'
	type='text/css' media='all' />

<style type="text/css">
.post-title {
	color: #33CCFF;
}
</style>
<style>
* {
	padding: 0;
	margin: 0;
}
/*
			  * 外面盒子样式---自己定义
			  */
.page_div {
	margin-top: 20px;
	margin-bottom: 20px;
	font-size: 15px;
	font-family: "microsoft yahei";
	color: #666666;
	margin-right: 10px;
	padding-left: 20px;
	box-sizing: border-box;
}
/*
			 * 页数按钮样式
			 */
.page_div a {
	min-width: 30px;
	height: 28px;
	border: 1px solid #dce0e0 !important;
	text-align: center;
	margin: 0 4px;
	cursor: pointer;
	line-height: 28px;
	color: #666666;
	font-size: 13px;
	display: inline-block;
}

#firstPage, #lastPage {
	width: 50px;
	color: #0073A9;
	border: 1px solid #0073A9 !important;
}

#prePage, #nextPage {
	width: 70px;
	color: #0073A9;
	border: 1px solid #0073A9 !important;
}

.page_div .current {
	background-color: #0073A9;
	border-color: #0073A9;
	color: #FFFFFF;
}

.totalPages {
	margin: 0 10px;
}

.totalPages span, .totalSize span {
	color: #0073A9;
	margin: 0 5px;
}
</style>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
 <script src="<%=basePath%>users/topic/js/html5.js"></script></script>
 <![endif]-->
</head>
<body>

	<!-- End of Search Wrapper -->

	<!-- Start of Page Container -->
	<div class="page-container">
		<div class="container">
			<div class="row cl" style="margin-left: 2%">
				<form method="post" action="" id="exid">
					<div style="width: 50%; float: left;">
						<span style="font-size: 18px;">实验分类： </span><select
							style="width: 250px" id="test" name="test" class="select">
							<option value="">----请选择实验分类----</option>
							<c:forEach var="item" items="${tests}">
								<option value="${item.cId }">${item.cTestName}</option>
							</c:forEach>
						</select>
					</div>

					<div style="width: 50%; float: left; text-align: left;">
						<span style="font-size: 18px;">
							实&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验：</span> <select
							style="width: 250px" id="experiment" name="experiment"
							class="select">
							<option value="">-----请选择实验-----</option>
						</select>
					</div>
					<div style="width: 50%; float: left; text-align: left;">
						<span style="font-size: 18px;"> 开始时间：</span> <input type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
							id="datemin" name="datemin" class="input-text Wdate"
							style="width: 240px;">
					</div>
					<div style="width: 50%; float: left; text-align: left;">
						<span style="font-size: 18px;"> 结束时间：</span> <input type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'datemin\')}'})"
							id="datemax" name="datemax" class="input-text Wdate"
							style="width: 240px;">
					</div>
					<div style="width: 50%; float: left;">
						<span style="font-size: 18px;">帐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：
						</span> <input type="text" style="width: 240px" id="num" name="num">
					</div>
					<div>
						<a style="width: 50px; margin-left: 11%"
							class="btn btn-success radius" id="search" name="search"
							onclick="submit()" href="javascript:;"> 查找 </a>
					</div>
				</form>
			</div>
			<hr>
			<!-- start of page content -->
			<div class="span12 main-listing" id="topicMes">请输入查询条件...</div>
			<!-- end of page content -->

			<div id="page" class="page_div"></div>
			<div
				style="margin-top: 5%; text-align: center; font-size: 10px; color: gray;">
				<footer> Copyright &copy; 2017, Crazy Code, All Rights
					Reserved </footer>
			</div>
		</div>
	</div>
	<!-- End of Page Container -->

	<script type="text/javascript"
		src="<%=basePath%>users/lib/My97DatePicker/WdatePicker.js"></script>
	<script src="<%=basePath%>users/topic/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>users/topic/js/paging.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>users/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="<%=basePath%>users/js/H-ui.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>users/js/H-ui.admin.js"></script>
	<script>
	function submit() { 
		var experiment =$("#experiment").val();
		var datemin =  $("#datemin").val();
		var datemax =  $("#datemax").val();
		var num =  $("#num").val();						
		if(experiment=="")
		{
			layer.alert("实验不允许为空！")
		}else if(num==""){
			layer.alert("学号不允许为空！")
		}
		else{
			var str="";
			$.ajax({
				type : 'POST',
				dataType : 'json',
				async: false,
				url : '<%=basePath%>teacher/getComment',
				data: {experiment:experiment,datemin:datemin,datemax:datemax,num:num},	
				success : function(data) {
					      if(data=="")
					    	  str="暂无数据";
							$("#topicMes").html("");  
							 $.each(data,function(index,item){                        		                	
		                         str += "<article class='format-standard type-post hentry clearfix'><header class='clearfix'><h3 class='post-title' style='color: #33CCFF'>" + item.cExperimentName +
		                          "</h3><div class='post-meta clearfix'><span class='date'>" +item.cCreateTime+	                          
		                          "</span> <span>帐号：" + item.cUserName+ 
		                          "</span> <span><a href='javascript:;' style='color:blue;' onclick='deleteReq("+item.cId+")'>删除</a></span></div></header><p>" + item.cContent+		                                                   
		                          "</p></article>";  		                      
		                         		                
		                       });  
							 $("#topicMes").append(str);   
					},
					error : function() {
						alert("服务器异常，请稍候再试！");
					}
				});
			}
		}
	
function deleteReq(id){
	layer.confirm(
			'确认删除此回复?',
			{
				btn : [ '是的', '放弃' ],
				shade : false
			},function(sender, modal, index) {
			//只有一个确定按钮，这里进行删除操作
			//通过ajax向后台请求
			$.ajax({
				type : 'get',
				dataType : 'json',
				url : '<%=basePath%>teacher/delComment?id=' + id,
				success : function(data) {
					if (data.success) {
						//删除成功，刷新页面
						alert("删除成功");
						//layer.closeAll('dialog');
						location.reload();
					} else {
						alert(data.msg);	
					}
					
				},error : function() {
					alert("服务器异常，请稍候再试！");
				}
					});
			});
}
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
	                $("#experiment").append( "<option value=''>-----请选择实验-----</option>");  
	                $.each(data,function(index,item){                        
	                    //填充内容  
	                    $("#experiment").append( "<option value='"+item.cId+"'>"+item.cExperimentName+"</option>");  
	                });  
	            }  
	        });  
	    });                                       
	});  
	

		
	</script>

</body>
</html>