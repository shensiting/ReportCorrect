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
<title>教师班级管理</title>
<style type="text/css">
.editor td{ text-align: center;}
</style>
<script type="text/javascript">
//修改函数

function showAddInput(){
	 document.getElementById('addinfo').style="display:block-inline;text-align: center;" ;}	
	 
//验证函数
function verify(id) {
		if(confirm("确认教师关联班级通过审核?")){
			$.ajax({
				type : 'get',
				dataType : 'json',
				async: false,
				url : '<%=basePath%>manage/verify.action?id='+id+'',
				success : function(data) {
					if (data.success) {
						alert("审核成功!");

					} else {
						layer.msg('操作失败，请重新操作', {
							icon : 6,
							time : 5000
						});	
						
					}
					//刷新页面
					location.reload();
				},
				error : function() {
					layer.msg('操作失败，请重新操作', {
						icon : 6,
						time : 5000
					});	
					
				}
			});
			}
			

} 
	 
	 
/*添加*/
function sumit(teacherform) {
	var cUserId = teacherform.id.value;
	 var cTeacherId =teacherform.teachernum.value;
	 var cName = teacherform.teachername.value;
	 var cCollegeId = teacherform.colgid.value;
	$.ajax({
		type : 'get',
		dataType : 'json',
		async: false,
		url : '<%=basePath %>manage/addTeacher.action?id=' + cUserId+'&teachername='+cName+'&teachernum='+cTeacherId+'&ccollege='+cCollegeId+'',
		success : function(data) {
			if (data.success) {
				alert(data.msg);
				
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
			<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l">
			 <a href="javascript:;" onclick="deleteFunc()"
				class="btn btn-danger radius"> <i class="Hui-iconfont">&#xe6e2;</i>批量删除
			</a> 
			
			</span> <span class="r">共有数据：<strong>${teacherGrades.size() }</strong>条
			</span>
		</div>
	
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox"></th>
						<th width="100">ID</th>
						<th>教师工号</th>
						<th>教师姓名</th>
						<th>关联班级</th>
						<th>关联班级ID</th>
						<th>状态</th>
						
						<th width="35px">删除</th>			
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${teacherGrades}">
						<tr class="editor">
							<td style="text-align: center;"><input name="checkboxid"
								type="checkbox" value="${item.getcId()}" /></td>
							<td id="cUserId">${item.getcId()}</td>
							<td id="cTeacherId">${item.getTeacher().getcTeacherId()}</td>
							<td id="cName">${item.getTeacher().getcName()}</td>
							<td>${item.getCollege().getcCollegeName()} ${item.getGrade().getcYearClass()}
								 ${item.getMajor().getcMajorName()} ${item.getGrade().getcClass()}</td>
							<td>${item.getcGradeId()}</td>
							<td id="cCollegeId"><c:choose>
									<c:when test="${item.getcStatus()=='1'}">
											已审核
											</c:when>
									<c:otherwise>
									
							<a class="btn btn-primary radius"
				              onclick="verify('${item.getcId()}')" href="javascript:;">未审核</a>		
									
									</c:otherwise>
								</c:choose></td>
							
							<td><a style="text-decoration: none" class="ml-5"
								onClick="article_del(this,'${item.getcId()}')"
								href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
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
		
	
		
		//删除函数
		function deleteFunc() {
			var ids = getSelectedId();
			if (ids.length == 0) {
				layer.msg('请选择一条记录！', {
					icon : 6,
					time : 1000
				});			
				return;
			}
			layer.confirm(
					'确认所选' + ids.length + '条记录?',
					{
						btn : [ '是的', '放弃' ],
						shade : false
					},function(sender, modal, index) {
					//只有一个确定按钮，这里进行删除操作
					//通过ajax向后台请求
					$.ajax({
						type : 'get',
						dataType : 'json',
						url : '<%=basePath%>manage/delTeacherGrade.action?ids=' + ids,
						success : function(data) {
							if (data.success) {
								//删除成功，刷新页面
								layer.msg('删除成功', {
									icon : 6,
									time : 4000
								});	
							} else {
								var str=data.msg;
								layer.msg(str, {
									icon : 6,
									time : 1000
								});		
							}
							//刷新页面
							location.reload();
						},error : function() {
							alert("服务器异常，请稍候再试！");
						}
							});
					});
		}
		/*资讯-添加*/
		function article_add(title, url, w, h) {
			layer_show(title,url,w,h)
		}
		
		/*资讯-删除*/
		function article_del(obj, id) {
			layer.confirm('确认要删除吗？', function(sender, modal, index) {
				//只有一个确定按钮，这里进行删除操作
				//通过ajax向后台请求
				$.ajax({
					type : 'get',
					dataType : 'json',
					url : '<%=basePath%>manage/delTeacherGrade.action?ids=' + id,
					success : function(data) {
						if (data.success) {
							
						} else {
							var str=data.msg;
							layer.msg(str, {
								icon : 6,
								time : 1000
							});		
						}
						//刷新页面
						location.reload();
					},error : function() {
						alert("服务器异常，请稍候再试！");
					}
						});
				});
		}
		
	</script>
</body>
</html>