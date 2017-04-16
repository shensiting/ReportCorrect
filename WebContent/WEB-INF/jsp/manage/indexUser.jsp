<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/lib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户信息管理</title>
<meta name="description" content="">
<meta name="keywords" content="">

<script type="text/javascript">
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
	//修改并新增函数
	function editFunc(){
		var ids = getSelectedId();
		if (ids.length != 1 ) {
			$.teninedialog({
				title : '系统提示',
				content : '请选择一条记录!'
			});
			return;
		}else{
			//取得当前记录id
			var id= ids[0];
			//跳转到页面
			window.location.href='<%=basePath%>manage/addUser.html?id='+id;
		}
	}
	
	//删除函数
	function deleteFunc() {
		var ids = getSelectedId();
		if (ids.length == 0) {
			$.teninedialog({
				title : '系统提示',
				content : '请选择一条记录!'
			});
			return;
		}
		$.teninedialog({
			title : '系统提示',
			content : '确认所选' + ids.length + '条记录?',
			showCloseButton : true,
			otherButtons : [ "确定" ],
			otherButtonStyles : [ 'btn-primary' ],
			clickButton : function(sender, modal, index) {
				//只有一个确定按钮，这里进行删除操作
				//通过ajax向后台请求
				$.ajax({
					type : 'get',
					dataType : 'json',
					url : '<%=basePath%>manage/delUser.action?ids=' + ids,
					success : function(data) {
						if (data.success) {
							//删除成功，刷新页面

						} else {
							$.teninedialog({
								title : '系统提示',
								content : data.msg
							});
						}
						//刷新页面
						location.reload();
					},
					error : function() {
						$.teninedialog({
							title : '系统提示',
							content : '服务器异常，请稍候再试！'
						});
					}
				});
				//刷新当前页

				/* //关闭对话框
				$(this).closeDialog(modal); */
			}
		});

	}
</script>
</head>
<body>
	<div id="header">
		<h1>
			<img alt="" src="<%=basePath%>images/gzykdx.png"
				style="width: 100%; height: 100%">
		</h1>
	</div>
	<!-- 右上角用户按钮 -->
	<%@include file="../common/user-nav.jsp"%>
	<!-- 左侧导航栏 -->
	<div id="sidebar">
		<ul>
			<li class="submenu"><a href="#"><i
					class="icon icon-th-list"></i> <span>基本信息管理</span> <span
					class="label">3</span></a>
				<ul>
                    <li><a href="<%=basePath%>manage/indexCollege">学院管理</a></li>
					<li><a href="<%=basePath%>manage/indexMajor">专业管理</a></li>
					<li><a href="<%=basePath%>manage/indexGrade">班级管理</a></li>
				</ul></li>

			<li class="submenu"><a href="#"><i class="icon icon-pencil"></i>
					<span>实验信息管理</span> <span class="label">2</span></a>
				<ul>
					<li><a href="<%=basePath%>manage/indexExperiment.html">实验管理</a></li>
					<li><a href="<%=basePath%>manage/indexTeacherExperiment.html">考核管理</a></li>
				</ul></li>
			<li class="active submenu open"><a href="#"><i class="icon icon-file"></i>
					<span>用户信息管理</span> <span class="label">3</span></a>
				<ul>
					<li><a href="<%=basePath%>manage/indexStudent">学生信息管理</a></li>
					<li><a href="<%=basePath%>manage/indexTeacher">教师信息管理</a></li>
					<li><a href="<%=basePath%>manage/indexUser.html">登陆信息管理</a></li>
				</ul></li>
		</ul>
	</div>
	<!-- 左侧导航栏 -->

	<div id="content">
		<div id="content-header">
			<h1>
				<a>广医学生报告后台管理系统</a>
			</h1>
		</div>
		<div id="breadcrumb">
			<a href="<%=basePath%>manage/index" title="主页" class="tip-bottom"><i class="icon-home"></i>
				首页</a> <a href="#" class="current">用户信息管理</a>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="btn-group">
						<a class="btn btn-sm tip-bottom" title="新增"
							href="<%=basePath%>manage/addUser.html"><i class="icon-plus"></i></a>
						<a class="btn btn-sm tip-bottom" title="修改" onclick="editFunc()"><i
							class="icon-edit"></i></a> <a class="btn btn-sm tip-bottom"
							title="删除" onclick="deleteFunc()"><i class="icon-remove"></i>
						</a>
					</div>
					<div class="widget-box">
						<div class="widget-title">
							<h5>用户信息列表</h5>
						</div>
						<div class="widget-content nopadding">
							<!-- ---------------------------------Table------------------------------------------------------- -->
							<table class="table table-bordered data-table  with-check ">
								<!-- table table-bordered table-striped with-check -->
								<thead>
									<tr>
										<th class="center"><input type="checkbox"
											id="title-table-checkbox" name="title-table-checkbox" /></th>
										<th>账号</th>
										<th>密码</th>
										<th>权限</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${users}">
										<tr>
											<td><input name="checkboxid" type="checkbox"
												value="${item.getcId()}" /></td>
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
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- footer -->
			<%@include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>