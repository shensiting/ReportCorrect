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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示</title>
<script type="text/javascript">
        function reinitIframe() {
            var iframe = document.getElementById("frame");
            try {
                var bHeight = iframe.contentWindow.document.body.scrollHeight;
                var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
                var height = Math.max(bHeight, dHeight);
                iframe.height = height;
            } catch (ex) { }
        }
        window.setInterval("reinitIframe()", 200);
    </script>
<style type="text/css">
table tr td {
	border: thin solid #000000;
	text-align: center;
	width: 5%;
}
</style>

</head>
<body>
	<div
		style="padding-right: 20%; padding-left: 20%; background-color: white;">
		<h1 align="center">${reportRelative.getExperimentalTest().getcExperimentName()}</h1>
		<h1 align="center">良好证书</h1>
		<div align="center">
			<img
				alt="<%=basePath%>picture${reportRelative.getStudent().getcPicturePath()}"
				src="<%=basePath%>picture${reportRelative.getStudent().getcPicturePath()}"
				style="width: 100px; height: 120px;">
		</div>
		<p style="font-size: 18pt; line-height: 2;">${reportRelative.getStudent().getcName()}
			于 ${reportRelative.getExperimentalTest().getcExperimentTime()}参加
			生物技术实践能力考试
			（${reportRelative.getExperimentalTest().getcExperimentName()}），成绩合格（理论
			${theory} ，实验操作 ${operation} ，实验结果 ${ labresult}，综评 ${ conclusion2}），特发此证。</p>
		<p style="font-size: 25px;">证书编号：${reportRelative.getcReportNum()}</p>
		<p style="font-size: 25px;">身份证号码：${ reportRelative.getStudent().getcIDNumber()}
		</p>
		<hr />
		<div align="center" style="font-size: 20px;">
			<h1 style="font-size: 30px;">${reportRelative.getExperimentalTest().getcExperimentEnglishName()}
			</h1>
			<h1 style="font-size: 30px;">EXAMINATION CERTIFICATE</h1>
			<p>Rank：Basic</p>
			<p>Grade: ${conclusion}</p>
			<p>IDNumber:${reportRelative.getStudent().getcIDNumber() }</p>
			<p>Certificate Number: ${ reportRelative.getcReportNum()}</p>
			<p>Biotechnology Department, Guangzhou Medical University
				Authority</p>
			<div align="center">
				<img alt="<%=basePath%>picture/校徽.JPG"
					src="<%=basePath%>picture/校徽.JPG"
					style="width: 80px; height: 80px;">
			</div>
			<br />
			<div style="float: right;">
				更多请访问 <img alt="<%=basePath%>picture/公众号.jpg"
					src="<%=basePath%>picture/公众号.jpg"
					style="width: 80px; height: 80px;"> &nbsp;&nbsp; <img
					alt="<%=basePath%>report/${reportRelative.getcQRcode()}"
					src="<%=basePath%>report/${reportRelative.getcQRcode()}"
					style="width: 80px; height: 80px;">
			</div>
		</div>
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />

		<h1 align="center">${reportRelative.getExperimentalTest().getcExperimentName()}良好证书</h1>
		<br /> <br />
		<p style="font-size: 20px;">姓名：${reportRelative.getStudent().getcName()}</p>
		<p style="font-size: 20px;">证书编号：${reportRelative.getcReportNum()}</p>
		<p style="font-size: 20px;">身份证号码：${ reportRelative.getStudent().getcIDNumber()}</p>
		<p style="font-size: 20px;">实验过程：${ reportRelative.getcProcess()}</p>

		<p style="font-size: 30px">成绩：由生技老师集体评判</p>
		<br />
		<table id="correct" class="table table-bordered"
			style="font-size: 20px; text-align: center;">
			<tr style="border: thin solid #000000">
				<td class="auto-style9"></td>
				<td class="auto-style8">原理</td>
				<td colspan="3" class="auto-style1">操作</td>
				<td class="auto-style4">实验结果</td>
				<td class="auto-style3">分数</td>
			</tr>
			<tr>
				<td class="auto-style9"></td>
				<td class="auto-style8">&nbsp;</td>
				<td class="auto-style7">试剂配制</td>
				<td class="auto-style6">仪器操作</td>
				<td class="auto-style5">实验习惯实验安全</td>
				<td id="labresult" class="auto-style4" rowspan="3">${labresult}</td>

				<td id="grade" class="auto-style3" rowspan="3">${reportRelative.getScoreSheet().getcSum() }</td>

			</tr>
			<tr>
				<td class="auto-style9">评分</td>
				<td id="theory" class="auto-style8">${theory}</td>
				<td id="reagen" class="auto-style7">${reagen }</td>
				<td id="instrument" class="auto-style6">${instrument }</td>
				<td id="experiment" class="auto-style5">${experiment}</td>
			</tr>
			<tr>
				<td class="auto-style9">结论</td>
				<td id="conclusion" class="auto-style23" colspan="4">${ conclusion2}</td>
			</tr>

		</table>
		<p class="text-right">
			<a href="<%=basePath%>report/生物技术简介.htm">生物技术系简介</a>
		</p>
		<p style="font-size: 30px">以下部分，全部由张三完成。</p>
		<div>
			<iframe src="<%=basePath %>report${reportRelative.getcPath()}"
				id="frame" scrolling="no" frameborder="0"
				style="overflow: auto; width: 100%;">

				<a href="#">你的浏览器不支持iframe页面嵌套，请点击这里访问页面内容。</a>

			</iframe>
		</div>
	</div>

</body>
</html>