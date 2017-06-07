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
	 document.getElementById('addinfo').style="display:block-inline;text-align: center;" ;}	
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
			layer.msg('服务器异常，请稍候再试！', {
				icon : 6,
				time : 4000
			});	
		}
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
			
		<div style="display: none;" id="addinfo" class="pd-20">
			<form class="form-horizontal" method="post" action=""
				name="basic_validate" id="basic_validate">
				<div class="row cl">
					<label class="form-label col-1">学号：</label>
					<div class="formControls col-2">

						<input type="hidden" id="id" name="id" value="" />
						<input type="hidden" id="role" name="role" value="" />
						 <input readonly="readonly"
							type="text" class="input-text" value="" style="width: 250px"
							placeholder="输入学号" required="required" id="userNum"
							name="userNum">
					</div>

					<label class="form-label col-2">密码：</label>
					<div class="formControls col-2">

						<input
							type="text" class="input-text" value="" style="width: 250px"
							placeholder="输入密码" required="required" id="password"
							name="password">
					</div>
					<button style="width: 100px"  type="submit" class="btn btn-success radius" onclick="sumit(this.form)"
					id="" name="">提交</button>
				</div>
				
			</form>
		</div>
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
						<th width="50px">编辑</th>
								
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
							<td class="f-14 td-manage" style="text-align: center"><a style="text-decoration: none"
								class="ml-5" id="edit" href="javascript:;" title="编辑"><i
									class="Hui-iconfont">&#xe6df;</i></a></td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
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