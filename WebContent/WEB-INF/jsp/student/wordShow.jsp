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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>word在线查看</title>
<script type="text/javascript">
	function reinitIframe() {
		var iframe = document.getElementById("frame");
		try {
			var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);
			iframe.height = height;
		} catch (ex) {
		}
	}
	window.setInterval("reinitIframe()", 200);
</script>
</head>
<body>
	<div style="margin: auto; text-align: center; vertical-align: middle;">
		<iframe src="${wordpath}" id="frame" scrolling="no" frameborder="0"
			style="overflow: auto; margin: auto; width: 80%;"> </iframe>
	</div>
	<div
		style="margin-top: 5%; text-align: center; font-size: 10px; color: gray;">
		<%@include file="../common/footer.jsp"%>
	</div>
</body>
</html>