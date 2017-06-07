<%--
  User: mingfei.net@gmail.com
  Date: 13-11-25
  Time: 上午11:14
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8">
    <title>book add page</title>
</head>
<body>
   <form action="<%=basePath %>topicAction/topicCommit.action" method="post">
		<!-- 加载编辑器的容器 -->
		<script id="container" name="content" type="text/plain">
        这里写你的初始化内容
    </script>
    <input type="submit" value="提交">
	</form>
	
	<!-- 配置文件 -->
	<script type="text/javascript" src="<%=basePath %>ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="<%=basePath %>ueditor/ueditor.all.js"></script>
	
	<!-- 实例化编辑器 -->
	<script type="text/javascript">
	
        var ue = UE.getEditor('container');
     
        ue.addListener('ready', function( editor ) {  
             ue.execCommand( 'drafts' );  
        });  
        ue.ready(function() {  
            ue.execCommand( 'drafts' );  
        });  
    </script>
</body>
</html>