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
<title>提示</title>

</head>
<script type="text/javascript">  


//设定倒数秒数  
var t = 5;  
//显示倒数秒数  
function showTime(){  
    t=t-1;;  
    alert(t);
   document.getElementById("div1").innerHTML=t;
    if(t==0){  
    	var showpath=<%=basePath%>${showpath};
        window.location.href=showpath;  
    }  
    //每秒执行一次,showTime()  
    setTimeout("showTime()",1000);  
}  
  function a(){
	  alert("asfas");
  }
  
//执行showTime()  
//showTime();
</script>  
<body>
 <div style="width: 50%;height: 50%;margin: auto;padding-top: 10%" align="center">
 <h1 style="font-size: 60px;color: red;"><img src="<%=basePath %>images/smiley_004.png" alt="icon" style="width: 150px;height: 120px;"></h1>
	<h2>糟糕！${message}</h2>
	<img src="<%=basePath %>user/Images/Icons/Preview/house_home_building.png" alt="icon" style="width: 60px;height: 50px">
	<br/>
	<div><a href="<%=basePath %>${showpath}" style="text-decoration: underline;">点击返回</a></div>
	</div> 

</body>
</html>