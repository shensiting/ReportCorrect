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
<link href="<%=basePath%>users/lib/icheck/icheck.css" rel="stylesheet"
	type="text/css">
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=basePath%>users/lib/html5.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/PIE_IE678.js"></script>
<![endif]-->


<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>实验报告管理</title>
<script type="text/javascript">
//修改函数
	function showAddInput() {
		$("#addinfo").css('display', 'block');
		$("#addinfo").css('text-align', 'center');
	}
/*添加*/
function sumit(userform) {
	var id = userform.id.value;
	 var userNum =userform.userNum.value;
	 var password = userform.password.value;
	 var role = userform.role.value;
	$.ajax({
		type : 'get',
		dataType : 'json',
		async: false,
		url : '<%=basePath%>manage/addUser.action?id=' + id+'&userNum='+userNum+'&password='+password+'&role='+role+'',
		success : function(data) {
			if (data.success) {
				alert("修改成功！");				
			} else {				
				layer.msg('操作失败，请重新操作', {
					icon : 6,
					time : 5000
				});		
			}
			
		},error : function() {
			layer.msg('操作失败，请重新操作', {
				icon : 6,
				time : 4000
			});				
		}
	});
		
}

</script>
</head>
<body>
	<nav class="breadcrumb"> <a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20">
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span>在线编辑实验查看</span><span>word实验查看</span>
			</div>
			<div class="tabCon">
				<div class="mt-20">
					<table
						class="table table-border table-bordered table-bg table-hover table-sort">
						<thead>
							<tr class="text-c">
								<th width="100">ID</th>
								<th>实验编号</th>
								<th>实验名称</th>
								<th>上传时间</th>
								<th width="100px">查看</th>
								<th width="100px">编辑</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${reportOnlineRelatives}">
								<tr class="text-c">
									<td>${item.getcId()}</td>
									<td>${item.getcReportNum()}</td>
									<td>${item.getExperimental().cExperimentName}</td>
									<td><fmt:formatDate value="${item.getcCreateTime() }"
											type="date" pattern="yyyy-MM-dd" /></td>
									<td><a class="btn btn-success radius r mr-20"
										onclick="lookFunc(${item.getcId()})"
										style="text-decoration: none" class="ml-5" id="edit"
										href="javascript:;" title="查看">查看</a></td>
									<td><c:choose>
											<c:when test="${item.getcScoreId()!=0}">
												<span class="label label-success radius">已批改</span>
											</c:when>
											<c:when test="${item.getGradeExam().getcStatus()==1}">
												<span class="label radius">批改中</span>
											</c:when>
											<c:otherwise>
												<a class="btn btn-success radius r mr-20"
													onclick="editFunc(${item.getcId()})"
													style="text-decoration: none" class="ml-5" id="edit"
													href="javascript:;" title="编辑">编辑</a>
											</c:otherwise>
										</c:choose></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tabCon">
				<div class="mt-20">
					<table
						class="table table-border table-bordered table-bg table-hover table-sort">
						<thead>
							<tr class="text-c">
								<th width="100">ID</th>
								<th>实验编号</th>
								<th>实验名称</th>
								<th>上传时间</th>
								<th width="100px">查看</th>
								<th width="100px">编辑</th>
								<th width="100px">下载</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${reportWordRelatives}">
								<tr class="text-c">
									<td>${item.getcId()}</td>
									<td>${item.getcReportNum()}</td>
									<td>${item.getExperimental().cExperimentName}</td>
									<td><fmt:formatDate value="${item.getcCreateTime() }"
											type="date" pattern="yyyy-MM-dd" /></td>
									<td><a class="btn btn-primary radius r mr-20"
										onclick="lookWordFunc(${item.getcId()})"
										style="text-decoration: none" class="ml-5" id="edit"
										href="javascript:;" title="查看">查看</a></td>
									<td><c:choose>
											<c:when test="${item.getcScoreId()!=0}">
												<span class="label label-success radius">已批改</span>
											</c:when>
											<c:when test="${item.getGradeExam().getcStatus()==1}">
												<span class="label radius">批改中</span>
											</c:when>
											<c:otherwise>
												<a class="btn btn-primary radius r mr-20"
													onclick="editWordFunc(${item.getcId()})"
													style="text-decoration: none" class="ml-5" id="edit"
													href="javascript:;" title="上传">上传</a>
											</c:otherwise>
										</c:choose></td>
									<td><a class="btn btn-primary radius r mr-20"
										style="text-decoration: none" class="ml-5" id="edit"
										href="<%=basePath %>student/downLoadWord?id=${item.getcId()}"
										title="下载">下载</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div style="margin-top: 5%">
				<%@include file="../common/footer.jsp"%>
			</div>
		</div>

		<script type="text/javascript"
			src="<%=basePath%>users/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>users/lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>users/lib/icheck/jquery.icheck.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>users/lib/Validform/5.3.2/Validform.min.js"></script>
		<script type="text/javascript">
		$('.table-sort').dataTable({
			"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
			"bStateSave" : true,//状态保存
			"aoColumnDefs" : [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{
				"orderable" : false,
				"aTargets" : [ 0, 3 ]
			} // 制定列不参与排序
			]
		});

		$(function(){
			$('.skin-minimal input').iCheck({
				checkboxClass: 'icheckbox-blue',
				radioClass: 'iradio-blue',
				increaseArea: '20%'
			});
			$.Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
		});
		
		//查看报告
		function lookFunc(id){			
				layer_show("查看报告","<%=basePath%>student/reportShow?id="+id,'1000','600')			
		}
		
		//编辑报告
		function editFunc(id){			
				layer_show("编辑报告","<%=basePath%>student/reportEdit?id="+id,'1000','600')			
		}
		//编辑word报告
		function editWordFunc(id){			
				layer_show("上传报告","<%=basePath%>student/reportWordEdit?id="+id,'1000','600')			
		}

		//查看word报告
		function lookWordFunc(id){			
				layer_show("查看word报告","<%=basePath%>student/reportWordShow?id="+id,'1000','600')			
		}
		//编辑
		$(function() {
			$(".editor").each(function(){
			var tmp=$(this).children().eq(5);
			var btn=tmp.children();
			btn.bind("click",function(){				
				document.getElementById("id").value=btn.parent().parent().children("td").get(0).innerHTML;
				document.getElementById("userNum").value=btn.parent().parent().children("td").get(1).innerHTML;
				document.getElementById("password").value=btn.parent().parent().children("td").get(2).innerHTML;
				document.getElementById("role").value =btn.parent().parent().children("td").get(4).innerHTML.trim();
				document.getElementById('addinfo').style="display:block-inline;text-align: center;" ;	
			});
			});
		});
		
		
	</script>
</body>
</html>