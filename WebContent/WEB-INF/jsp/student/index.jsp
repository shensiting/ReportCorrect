<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link href="<%=basePath%>users/css/skin.css" rel="stylesheet"
	type="text/css" />
<%@ include file="../common/userslib.jsp"%>
<LINK rel="Bookmark" href="/favicon.ico">
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=basePath%>users/lib/html5.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/PIE_IE678.js"></script>
<![endif]-->


<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>实验报告管理系统</title>
<script type="text/javascript">
$(function(){
	 setInterval(getMes,1000*30);
	 function getMes(){
		 $.ajax({  
	            type:"POST",  
	            url :"../student/getCountMes",  	          
	            dataType:"json",  
	            success:function(data){  
	              if(data.num!==""){
	            	  $("#num").html(data.num);
	              }
	            }  
	        });  			
	 }
	})
</script>
</head>
<body>
	<header class="Hui-header cl"> <a class="Hui-logo l"
		title="H-ui.admin v2.3" href="/">广州医科大学</a> <a class="Hui-logo-m l"
		href="/" title="H-ui.admin">基础学院</a> <span class="Hui-subtitle l">实验报告管理系统</span>
	<nav class="mainnav cl" id="Hui-nav"> <!-- <ul>
		<li class="dropDown dropDown_click"><a href="javascript:;"
			class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i
				class="Hui-iconfont">&#xe6d5;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="javascript:;"
					onclick="article_add('添加资讯','article-add.html')"><i
						class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
				<li><a href="javascript:;"
					onclick="picture_add('添加资讯','picture-add.html')"><i
						class="Hui-iconfont">&#xe613;</i> 图片</a></li>
				<li><a href="javascript:;"
					onclick="product_add('添加资讯','product-add.html')"><i
						class="Hui-iconfont">&#xe620;</i> 产品</a></li>
				<li><a href="javascript:;"
					onclick="member_add('添加用户','member-add.html','','510')"><i
						class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
			</ul></li>
	</ul> --> </nav>
	<ul class="Hui-userbar">
		<li>${studentName}</li>
		<li class="dropDown dropDown_hover"><a href="#"
			class="dropDown_A">更多 <i class="Hui-iconfont">&#xe6d5;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="javascript:;"
					onclick="member_edit('个人信息','<%=basePath%>student/information','4','','510')">个人信息</a></li>
				<li><a href="javascript:;"
					onclick="member_edit('照片上传','<%=basePath%>student/uploadPicture','4','','510')">照片上传</a></li>
				<li><a href="javascript:;"
					onclick="member_edit('修改密码','<%=basePath%>student/passwordChange','4','480','350')"
					style="text-decoration: none">修改密码</a></li>
				<li><a href="<%=basePath%>manage/exit">退出</a></li>
			</ul></li>
		<li id="Hui-msg"><a href="javascript:;"
			onclick="member_edit('消息','<%=basePath%>student/friendResponse','4','','510')"
			title="消息"><span class="badge badge-danger" id="num">${num}</span><i
				class="Hui-iconfont" style="font-size: 18px">&#xe68a;</i></a></li>

	</ul>
	<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a> </header>
	<aside class="Hui-aside"> <input runat="server"
		id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">
		<dl id="menu-article">
			<dt>
				<i class="Hui-iconfont">&#xe616;</i> 报告管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a _href="<%=basePath%>student/uploadReport"
						href="javascript:void(0)">报告上传</a></li>
					<li><a _href="<%=basePath%>student/reportManage"
						href="javascript:void(0)">报告查看</a></li>
				</ul>
			</dd>
		</dl>

		<dl id="menu-tongji">
			<dt>
				<i class="Hui-iconfont">&#xe61a;</i> 成绩管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a _href="<%=basePath%>student/scoreInquery"
						href="javascript:void(0)">在线编辑成绩查询</a></li>
					<li><a _href="<%=basePath%>student/scoreWordInquery"
						href="javascript:void(0)">word上传成绩查询</a></li>
				</ul>
			</dd>
		</dl>

		<dl id="menu-comments">
			<dt>
				<i class="Hui-iconfont">&#xe622;</i>消息管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a _href="<%=basePath%>student/myTopic"
						href="javascript:;">我的提问</a></li>
					<li><a _href="<%=basePath%>teacher/myResponse">我的回复</a></li>
					<li><a _href="<%=basePath%>student/friendResponse">朋友回复</a></li>
					<li><a _href="<%=basePath%>student/allTopicTheme"
						href="javascript:;">全部问答</a></li>
				</ul>
			</dd>
		</dl>

	</div>
	</aside>
	<div class="dislpayArrow">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active"><span title="报告上传"
					data-href="<%=basePath%>student/uploadReport">报告上传</span><em></em></li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group">
			<a id="js-tabNav-prev" class="btn radius btn-default size-S"
				href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
				id="js-tabNav-next" class="btn radius btn-default size-S"
				href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
		</div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display: none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0"
				src="<%=basePath%>student/uploadReport"></iframe>
		</div>
	</div>
	</section>
	<script type="text/javascript">
	
	/*用户-编辑*/
	function member_edit(title,url,id,w,h){
		layer_show(title,url,w,h);
	}

		</script>
</body>
</html>