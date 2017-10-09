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

<title>所有问答</title>

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

	<!-- Start of Search Wrapper -->
	<div class="search-area-wrapper">
		<div class="search-area container">
			<h3 class="search-header">Search</h3>


			<form id="search-form" class="search-form clearfix" method="post"
				action="<%=basePath%>student/getByMes" autocomplete="off">
				<input class="search-term required" required="required" type="text"
					id="mes" name="mes" placeholder="请输入搜索条件" title="* 请输入搜索条件!" /> <input
					class="search-btn" style="background-color: #5599FF" type="submit"
					value="Search" />
				<div id="search-error-container"></div>
			</form>
		</div>
	</div>
	<!-- End of Search Wrapper -->

	<!-- Start of Page Container -->
	<div class="page-container">
		<div class="container">
			<div class="row cl" style="margin-left: 2%">
				<form method="post" action="" id="exid">
					<div style="width: 60%; float: left;">
						<span style="font-size: 22px; font-weight: bold;">分类搜索：</span> <span
							style="font-size: 18px;">实验分类： </span><select
							style="width: 250px" id="test" name="test" class="select">
							<option value="">----请选择实验分类----</option>
							<c:forEach var="item" items="${tests}">
								<option value="${item.cId }">${item.cTestName}</option>
							</c:forEach>
						</select>
					</div>

					<div style="width: 35%; float: left; text-align: left;">
						<span style="font-size: 18px;"> 实验：</span> <select
							style="width: 250px" id="experiment" name="experiment"
							class="select">
							<option value="">-----请选择实验-----</option>
						</select>

					</div>
				</form>
			</div>
			<hr>
			<!-- start of page content -->
			<div class="span12 main-listing" id="topicMes">
				<c:forEach var="item" items="${topicThemes}">
					<article class="format-standard type-post hentry clearfix">
						<header class="clearfix">
							<h3 class="post-title" style="color: #33CCFF">
								<a href="javascript:;" onclick="article_add('${item.getcId()}')">${item.getcTitle()}</a>
							</h3>
							<div class="post-meta clearfix">
								<span class="date"><fmt:formatDate type="both"
										value="${item.getcCreateTime()}" /></span> <span class="comments"><a
									href="#" class="maincolor">${item.getcCommentNum()}
										Comments</a></span>
							</div>
							<!-- end of post meta <span class="like-count">66</span>-->
						</header>
						<p>${item.getcContent()}</p>

					</article>
				</c:forEach>
			</div>
			<!-- end of page content -->

			<div id="page" class="page_div"></div>
			<div style="margin-top: 4%; text-align: center;">
				<hr color="gray">
				<footer> Copyright &copy; 2017, Crazy Code, All Rights
					Reserved </footer>
			</div>
		</div>

	</div>
	<!-- End of Page Container -->


	<script src="<%=basePath%>users/topic/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>users/topic/js/paging.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>users/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="<%=basePath%>users/js/H-ui.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>users/js/H-ui.admin.js"></script>
	<script>
	function article_add(id) {
		var url='<%=basePath%>student/showTopic?id='+id;
		layer_show("问题显示",url,1000,650);
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
	
	$(function(){  
	    //触发的下拉框chang事件  
	    $("#experiment").change(function(){  
	        var experiment = $("#experiment").val();  
	       if(experiment!=""){
	    	   document.getElementById("exid").action="<%=basePath%>student/getByExamId";
	    	   document.getElementById("exid").submit();
	       }	        	      
	    });                                       
	});  
	
		//分页
		$("#page").paging({
			pageNo:1,
			totalPage: ${end},
			totalSize: ${sum},
			callback: function(num) {	
				var str="";
				$.ajax({
					type : 'POST',
					dataType : 'json',
					async: false,
					url : '<%=basePath%>student/getPageTopic?id=' +num+'',
					success : function(data) {						
							$("#topicMes").html("");  
							 $.each(data,function(index,item){                        		                	
		                         str += "<article class='format-standard type-post hentry clearfix'><header class='clearfix'><h3 class='post-title' style='color: #33CCFF'><a href='javascript:;' onclick='article_add("+item.cId+")'>" + item.cTitle +
		                          "</a></h3><div class='post-meta clearfix'><span class='date'>" +item.cCreateTime+	                          
		                          "</span> <span class='comments'><a href='#' class='maincolor'>" + item.cCommentNum+ 
		                          " Comments</a></span> </div></header><p>" + item.cContent+		                                                   
		                          "</p></article>";  		                      
		                         		                
		                       });  
							 $("#topicMes").append(str);   
                           }
				   });
			}
		})
	</script>

</body>
</html>