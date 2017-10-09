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
<style type="text/css">
.editor td {
	text-align: center;
}
</style>
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=basePath%>users/lib/html5.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/PIE_IE678.js"></script>
<![endif]-->


<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>登录信息管理</title>
<script type="text/javascript">
//修改函数

function showAddInput(){
	$("#addinfo").css('display', 'block');
	$("#addinfo").css('text-align', 'center');}	
/*添加*/
function initialise(id) {
	layer.confirm(
			'确认初始化密码?',
			{
				btn : [ '是的', '放弃' ],
				shade : false
			},function(sender, modal, index) {
				$.ajax({
					type : 'post',
					dataType : 'json',
					async: false,
					url : '<%=basePath%>manage/addUser.action?id='+id+'',
					success : function(data) {
						if (data.success) {
							alert("初始化成功！");	
							window.location.reload();
						} else {				
							alert('操作失败，请重新操作');		
						}
						
					},error : function() {
						alert('操作失败，请重新操作');	
					
					}
				});
		});
		
}

</script>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 教师管理 <a
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
						<th width="100">ID</th>
						<th>账号</th>
						<th>密码</th>
						<th>身份</th>
						<th width="50px">身份ID</th>
						<th width="10%">初始化</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${users}">
						<tr class="editor">

							<td>${item.getcId()}</td>
							<td>${item.getcUserName()}</td>
							<td>${item.getcPassword()}</td>
							<td><c:choose>
									<c:when test="${item.getcRole()==1}">												
											管理员
											</c:when>
									<c:when test="${item.getcRole()==2}">
											教师
											</c:when>
									<c:when test="${item.getcRole()==3}">
											学生
											</c:when>
									<c:otherwise>
											学生
											</c:otherwise>
								</c:choose></td>
							<td>${item.getcRole()}</td>
							<td class="f-14 td-manage" style="text-align: center"><a
								class="btn btn-success radius" id="edit" href="javascript:;"
								onclick="initialise(${item.getcId()})" title="编辑">密码初始化</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div style="margin-top: 5%; height: 35px">
			<%@include file="../common/footer.jsp"%>
		</div>
	</div>

	<script type="text/javascript"
		src="<%=basePath%>users/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>users/lib/My97DatePicker/WdatePicker.js"></script>
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

		//返回id数组
		function getSelectedId() {
			//获取所有name为checkboxid的 CheckBox
			var checkBoxs = document.getElementsByName("checkboxid");
			var idStr = "";
			//遍历所有CheckBox，当CheckBox为选中时将这行数据的id值拼接到idStr中
			for (var i = 0; i < checkBoxs.length; i++) {
				if (checkBoxs[i].checked) {
					idStr += checkBoxs[i].value + ",";
				}
			}
			//去掉最后一个字符（“，”）
			idStr = idStr.substring(0, idStr.length - 1);
			if (idStr == "") {
				var obj = new Array();
				return obj;
			}
			//将idStr按“，”分割成字符串数组
			var ids = idStr.split(",");
			return ids;
		}

	</script>
</body>
</html>