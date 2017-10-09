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
<title>成绩查询</title>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页<a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20">

		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="40">ID</th>
						<!--<th>证书编号</th>  -->
						<th>实验名称</th>
						<th>成绩</th>
						<th width="100">评语</th>
						<th width="100">批注</th>
						<th width="150px">状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="rRitem1" items="${reportRelativesbyStuid}">
						<tr class="text-c">
							<td>${rRitem1.cId }</td>
							<!--  <td>${rRitem1.cReportNum }</td>-->
							<td>${rRitem1.experimental.cExperimentName }</td>

							<td>${rRitem1.scoreSheet.cSum}</td>
							<td><a class="btn btn-primary radius r mr-20"
								style="line-height: 1.6em; margin-top: 3px" href="javascript:;"
								onclick="lookConclusion('${rRitem1.scoreSheet.cConclution}')">查看</a></td>
							<td><a class="btn btn-success radius r mr-20"
								style="line-height: 1.6em; margin-top: 3px" href="javascript:;"
								onclick="article_add('查看批注','<%=basePath%>student/scoreShow?rid='+${rRitem1.cId},'1000','600')">查看</a></td>
							<td><c:choose>
									<c:when test="${rRitem1.cStatu=='1'}">
										<span class="label label-success radius">通过</span>
									</c:when>
									<c:when test="${rRitem1.cStatu=='3'}">
										<a class="btn btn-success radius"
											onclick="editFunc('${rRitem1.cId}')" href="javascript:;">再次提交作业</a>
									</c:when>
									<c:when test="${rRitem1.cStatu=='4'}">
										<span class="label label-danger radius">不及格</span>
									</c:when>
									<c:otherwise>
										<span class="label label-primary radius">正在审核</span>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
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
		
		function lookConclusion(msg) {
			layer.msg(msg, {				
				time : 4000
			});	
		}
		function article_add(title, url, w, h) {
			layer_show(title,url,w,h)
		}
		//编辑报告
		function editFunc(id){			
				layer_show("编辑报告","<%=basePath%>student/reportEdit?id="+id,'1000','600')			
		}
	</script>
</body>
</html>