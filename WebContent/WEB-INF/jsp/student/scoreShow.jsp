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
<%@ include file="../common/userslib.jsp"%>
<title>查看批注</title>
<script type="text/javascript"
	src="<%=basePath %>users/htmlPostil/artDialog.min.js"></script>
<script type="text/javascript"
	src="<%=basePath %>users/htmlPostil/jquery-1.6.4.min.js"></script>
<style type="text/css">
.content {
	width: 50%;
	padding: 5px;
	height: 100%;
	width: 50%;
	margin-bottom: 5px;
}

.list {
	border: 1px solid #ddd;
	height: 100%;
	width: 45%;
}

.list div {
	border: 1px solid #ddd;
	margin-bottom: 5px;
	padding: 10px;
	cursor: pointer;
}

.list div span {
	display: none;
}
/*css批注样式*/
.postil, .postilFocus {
	border-left: 1px solid red;
	border-right: 1px solid red;
	text-decoration: none;
	cursor: default;
}

.postil {
	background: yellow;
}

.postilFocus {
	background: Tomato;
}
/*批注提示图标样式*/
.tipsIcon {
	opacity: 0.80;
	z-index: 1;
	filter: alpha(opacity = 80);
}
</style>
<script type="text/javascript">

//解析批注
function loader(){	
	var $list = $(".list");
	$list.children().remove();
	$.each($(".content ins"), function(a, b){
		var content = $(b).attr("comment");
		var $postil = $("<div forid='"+$(b).get(0).id+"'>"+content+"<span style='color:red;' onClick='removePostil(this)'>     　　删除</span></div>");
		$postil.hover(function(){
			$(this).css("border-color", "red");			
			$("#"+$(this).attr("forid")+"").removeClass().addClass("postilFocus");
		}
		,
		function(){
			$(this).css("border-color", "#ddd");
			$("#"+$(this).attr("forid")+"").removeClass().addClass("postil");
		});
		$(b).hover(function(){
			$(this).removeClass().addClass("postilFocus");			
			$("div[forid='"+$(this).get(0).id+"']").css("border-color", "red");			
		}
		,
		function(){
			$(this).removeClass().addClass("postil");
			$("div[forid='"+$(this).get(0).id+"']").css("border-color", "#ddd");
			
		});
		$list.append($postil);
	});
}

</script>
</head>
<body onload="loader()">
	<div class="pd-20">${postil}</div>

</body>
</html>