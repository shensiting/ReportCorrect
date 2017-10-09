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
<title>学生管理</title>
<script type="text/javascript">
//修改函数
	function showAddInput() {
		$("#addinfo").css('display', 'block');
		$("#addinfo").css('text-align', 'center');
		 $("#cStudentNumber").attr("readOnly",false);
	}

/*添加*/
function sumit(studentform) {
	var cUserId = studentform.id.value;
	 var cStudentNumber =studentform.cStudentNumber.value;
	 var cName = studentform.cName.value;
	 var cGradeId = studentform.cGradeId.value;
	$.ajax({
		type : 'get',
		dataType : 'json',
		async: false,
		url : '<%=basePath %>manage/addStudent.action?id='+cUserId+'&Studentnum='+cStudentNumber+'&Studentname='+cName+'&cGradeId='+cGradeId+'',
		success : function(data) {
			if (data.success) {
				alert(data.msg);
				
			} else {
				alert(data.msg);	
			}			
		},error : function() {			
			layer.msg('服务器异常，请稍候再试！', {
				icon : 6,
				time : 4000
			});	
		}
	});
		
}

</script>
<style type="text/css">
.editor td {
	text-align: center;
}
</style>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 学生管理 <a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20">
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <a href="javascript:;" onclick="deleteFunc()"
				class="btn btn-danger radius"> <i class="Hui-iconfont">&#xe6e2;</i>批量删除
			</a> <a class="btn btn-primary radius"
				onclick="article_add('批量导入学生信息','<%=basePath%>manage/batchImportStu')"
				href="javascript:;"><i class="Hui-iconfont">&#xe600;</i>批量导入</a> <a
				class="btn btn-success radius" onclick="showAddInput()"
				href="javascript:;"><i class="Hui-iconfont">&#xe600;</i>单个添加</a>
			</span> <span class="r">共有数据：<strong>${studentGrades.size() }</strong>条
			</span>
		</div>
		<div style="display: none;" id="addinfo" class="pd-20">
			<form class="form-horizontal" method="post" action=""
				name="basic_validate" id="basic_validate">
				<div class="row cl">
					<label class="form-label col-1">学号：</label>
					<div class="formControls col-2">

						<input type="hidden" id="id" name="id" value="" /> <input
							type="text" class="input-text" value="" style="width: 250px"
							placeholder="输入学号" required="required" id="cStudentNumber"
							name="cStudentNumber">
					</div>

					<label class="form-label col-2">姓名：</label>
					<div class="formControls col-2">

						<input type="text" class="input-text" value=""
							style="width: 250px" placeholder="输入姓名" required="required"
							id="cName" name="cName">
					</div>

					<label class="form-label col-2">所属班级：</label>
					<div class="formControls col-2">
						<span class="select-box"> <select name="cGradeId"
							id="cGradeId" class="select" size="1" datatype="*"
							nullmsg="请选择隶属班级！">
								<c:forEach var="item" items="${gradeMajorColleges }">
									<option value="${item.cId}">${item.getcYearClass()}
										${item.getMajor().getcMajorName()} ${item.getcClass()}</option>
								</c:forEach>
						</select></span>
					</div>
					<button style="width: 100px" type="submit"
						class="btn btn-primary radius" onclick="sumit(this.form)" id=""
						name="">提交</button>
				</div>

			</form>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox"
							id="title-table-checkbox" name="title-table-checkbox"></th>
						<th width="100">ID</th>
						<th>学号</th>
						<th>姓名</th>
						<th>班级</th>
						<th width="50px">班级ID</th>
						<th width="50px">手机号码</th>
						<th width="35px">编辑</th>
						<th width="35px">删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${studentGrades}">
						<tr class="editor">
							<td style="text-align: center;"><input name="checkboxid"
								type="checkbox" value="${item.getcUserId()}" /></td>
							<td>${item.getcUserId()}</td>
							<td>${item.getcStudentNumber()}</td>
							<td>${item.getcName()}</td>
							<td>${item.getGrade().getcYearClass()}
								${item.getMajor().getcMajorName()}
								${item.getGrade().getcClass()}
								${item.getCollege().getcCollegeName()}</td>
							<td>${item.getcGradeId()}</td>
							<td>${item.getcPhoneNum()}</td>
							<td class="f-14 td-manage"><a style="text-decoration: none"
								class="ml-5" id="edit" href="javascript:;" title="编辑"><i
									class="Hui-iconfont">&#xe6df;</i></a></td>
							<td><a style="text-decoration: none" class="ml-5"
								onClick="article_del(this,'${item.getcUserId()}')"
								href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
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
		
		
		//编辑
		$(function() {
			$(".editor").each(function(){
			var tmp=$(this).children().eq(7);
			var btn=tmp.children();
			btn.bind("click",function(){				
				document.getElementById("id").value=btn.parent().parent().children("td").get(1).innerHTML;
				document.getElementById("cStudentNumber").value=btn.parent().parent().children("td").get(2).innerHTML;
				document.getElementById("cName").value=btn.parent().parent().children("td").get(3).innerHTML;
				document.getElementById("cGradeId").value =btn.parent().parent().children("td").get(5).innerHTML.trim();
				document.getElementById('addinfo').style="display:block-inline;text-align: center;" ;	
				$("#cStudentNumber").attr("readOnly",true);
			});
			});
		});
		
		
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
						url : '<%=basePath%>manage/delStudent.action?ids=' + ids,
						success : function(data) {
							if (data.success) {
								//删除成功，刷新页面
								alert("删除成功");
									
							} else {								
								alert(data.msg);	
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
					url : '<%=basePath%>manage/delStudent.action?ids=' + id,
					success : function(data) {
						if (data.success) {
							alert("删除成功");
						} else {
							alert(data.msg);		
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