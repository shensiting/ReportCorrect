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
<title>实验报告管理系统</title>
	<script type="text/javascript">
		function showAddInput(){
			 document.getElementById('addinfo').style="display:block-inline;text-align: center;" ;}	
		/*添加*/
		function sumit(majorform) {
			$.ajax({
				type : 'get',
				dataType : 'json',
				async: false,
				url : '<%=basePath%>manage/addMajor.action?id=' + majorform.id.value+'&Majorname='+majorform.Majorname.value+'',
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
	<span class="c-gray en">&gt;</span> 专业管理 <a
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
			<!-- article_add('专业添加','<%=basePath%>manage/addMajor','450','200','200') -->
			<a class="btn btn-success radius"
				onclick="showAddInput()" href="javascript:;"><i
					class="Hui-iconfont">&#xe600;</i>单个添加</a>
			</span> <span class="r">共有数据：<strong>${majorList.size() }</strong>条
			</span>
		</div>
		<div style="display: none;" id="addinfo" class="pd-20">
			<form class="form-horizontal" method="post" action=""
				name="basic_validate" id="basic_validate">
				<input type="hidden" id="id" name="id" value="" /> <input
					type="text" class="input-text" value="" style="width: 250px"
					placeholder="输入专业" required="required" id="Majorname"
					name="Majorname"> &nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-success radius"
					onclick="sumit(this.form)" id="" name="">提交</button>
			</form>
		</div>

		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox"></th>
						<th width="80">ID</th>
						<th width="75%">专业列表</th>
						<th width="20px">编辑</th>
						<th width="20px">删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${majorList }">
						<tr class="text-c">
							<td><input name="checkboxid" type="checkbox"
								value="${ item.cId}" /></td>
							<td id="cId">${item.cId }</td>
							<td id="cMajorName">${item.cMajorName }</td>
							<td class="f-14 td-manage">
							<a style="text-decoration: none" class="ml-5" id="edit"
								href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a></td>
								<td><a style="text-decoration: none" class="ml-5"
								onClick="article_del(this,'${ item.cId}')" href="javascript:;"
								title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
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
		//修改函数
		
		 $(function() {
				$(".text-c").each(function(){
				var tmp=$(this).children().eq(3);
				var btn=tmp.children();
				btn.bind("click",function(){
					document.getElementById("id").value=btn.parent().parent().children("td").get(1).innerHTML;
					document.getElementById("Majorname").value=btn.parent().parent().children("td").get(2).innerHTML;
					document.getElementById('addinfo').style="display:block-inline;text-align: center;" ;	
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
						url : '<%=basePath%>manage/delMajor.action?ids=' + ids,
						success : function(data) {
							if (data.success) {
								//删除成功，刷新页面
								layer.msg('删除成功', {
									icon : 6,
									time : 4000
								});	
							} else {
								alert(data.msg);
									
							}
							//刷新页面
							location.reload();
						},error : function() {
							layer.msg('服务器异常，请稍候再试！', {
								icon : 6,
								time : 4000
							});	
						}
							});
					});
		}
		/*资讯-删除*/
		function article_del(obj, id) {
			layer.confirm('确认要删除吗？', function(sender, modal, index) {
				//只有一个确定按钮，这里进行删除操作
				//通过ajax向后台请求
				$.ajax({
					type : 'get',
					dataType : 'json',
					url : '<%=basePath%>manage/delMajor.action?ids=' + id,
					success : function(data) {
						if (data.success) {
							alert("删除成功！");
						} else {
							alert(data.msg);	
						}
						//刷新页面
						location.reload();
					},error : function() {
						
						layer.msg('服务器异常，请稍候再试！', {
							icon : 6,
							time : 4000
						});	
					}
						});
				});
		}
		
	</script>
</body>
</html>