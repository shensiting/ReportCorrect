<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="../common/userslib.jsp"%>
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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->

<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>数据字典</title>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页<a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20">
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
				<button name="" id="" class="btn btn-success radius r mr-20"
					type="submit">
					<i class="Hui-iconfont">&#xe600;</i>批量导出
				</button>
			</span> <span class="r">共有数据：<strong>9</strong> 条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th>证书编号</th>
						<th>实验名称</th>
						<th>时间</th>
						<th>成绩</th>
						<th width="100">查看</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="rRitem1" items="${reportRelativesbyStuid}">
						<tr class="text-c">
							<td><input type="checkbox" value="" name=""></td>
							<td>${rRitem1.cReportNum }</td>
							<td>${rRitem1.experimentalTest.cExperimentName }</td>
							<td>${rRitem1.experimentalTest.cExperimentTime }</td>
							<td>${rRitem1.scoreSheet.cSum }</td>
							<td><a class="btn btn-success radius r mr-20"
								style="line-height: 1.6em; margin-top: 3px"
								href="<%=basePath%>student/reportShow?rid=${item.cId}">查看</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
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
	</script>
</body>
</html>