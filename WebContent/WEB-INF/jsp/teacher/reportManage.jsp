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
<title>报告管理</title>
<script type="text/javascript">
//修改函数

function showAddInput(){
	 document.getElementById('addinfo').style="display:block-inline;text-align: center;" ;}	
	 
/*添加*/
function sumit(teacherform) {
	var experiment =teacherform.experiment.value; 
	var cGradeId = teacherform.cGradeId.value; 
	alert(experiment+" "+cGradeId)
	if(experiment==""||cGradeId=="")
	{
		alert("条件不允许为空！")
	}else{
		
		document.forms[0].action = '<%=basePath%>teacher/getReportList?eptId='+experiment+'&gradeId='+cGradeId+'';		
		document.forms[0].submit();
		
	}
}
	
	function changeStatus(id){
		layer.confirm(
				'确认修改实验当前状态?',
				{
					btn : [ '是的', '放弃' ],
					shade : false
				},function(sender, modal, index) {
				//只有一个确定按钮，这里进行删除操作
				//通过ajax向后台请求
				$.ajax({
					type : 'get',
					dataType : 'json',
					url : '<%=basePath%>teacher/updateGradeExam?id=' + id,
					success : function(data) {
						if (data.success) {
							//删除成功，刷新页面
							alert("修改成功！");	
						} else {
							alert("修改失败！");		
						}
						//刷新页面
						location.reload();
					},error : function() {
						alert("服务器异常，请稍候再试！");
					}
						});
				});
	}
$(function(){  
    //触发的下拉框chang事件  
    $("#test").change(function(){  
        var test = $("#test").val();  
        $.ajax({  
            type:"POST",  
            url :"../teacher/getRelevance",  
            data:{                    
                id:test  
            },  
            dataType:"json",  
            success:function(data){  
                $("#experiment").empty();   
                $.each(data,function(index,item){                        
                    //填充内容  
                    $("#experiment").append( "<option value='"+item.cId+"'>"+item.cExperimentName+"</option>");  
                });  
            }  
        });  
    });                                       
});  


</script>
</head>
<body>
	<nav class="breadcrumb"> 实验报告管理 </nav>
	<div class="pd-20">
		<div style="display: none;" id="addinfo" class="pd-20">
			<form class="form-horizontal" method="post" action="<%=basePath%>teacher/getReportList"
				name="basic_validate" id="basic_validate">
				<div class="row cl">
					<label class="form-label col-1">实验分类：</label>
					<div class="formControls col-5">
						<span class="select-box"> <select id="test" name="test"
							class="select">
								<option value="">----请选择实验分类----</option>
								<c:forEach var="item" items="${tests}">
									<option value="${item.cId }">${item.cTestName}</option>
								</c:forEach>
						</select>
						</span>
					</div>
					<label class="form-label col-1">实验：</label>
					<div class="formControls col-5">

						<span class="select-box"> <select id="experiment"
							name="experiment" class="select">
								<option>----请选择实验----</option>
						</select>
						</span>
					</div>
				</div>
				<div style="margin-top: 2%;"></div>
				<div class="row cl">
					<label class="form-label col-1">关联班级：</label>
					<div class="formControls col-5">
						<span class="select-box"> <select name="cGradeId"
							id="cGradeId" class="select" size="1" datatype="*"
							nullmsg="请选择隶属班级！">
								<c:forEach var="item" items="${gradeMajorColleges }">
									<option value="${item.cId}">${item.getcYearClass()}
										${item.getMajor().getcMajorName()} ${item.getcClass()}</option>
								</c:forEach>
						</select></span>
					</div>
					<div style="width: 40%; text-align: right; float: left;">
						<button style="width: 100px; margin-right: 60%"
							class="btn btn-primary radius"
							id="search" name="search">
							<i class="Hui-iconfont">&#xe665;</i>查找
						</button>
					</div>
				</div>
			</form>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">		 
			<span class="l"> <a class="btn btn-success radius"
				onclick="showAddInput()" href="javascript:;"><i
					class="Hui-iconfont"></i>按条件查找</a></span>
			 <span class="r">共有数据：<strong>${reportRelatives.size() }</strong>条
			</span>
		</div> 

		<div class="mt-20">
			<table id="reportList" name="reportList" class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">						
						<th width="100">ID</th>
						<th>实验报告编号</th>	
						<th>实验名称</th>			
						<th>学号</th>			
						<th>班级</th>
						<th width="100px">查看</th>		
						<th width="10%">状态</th>													
					</tr>
				</thead>
				<tbody><c:forEach var="item" items="${reportRelatives}">
									<tr>
									<td>${item.cId}</td>
										<td>${item.cReportNum}</td>
										<td><a
											href="<%=basePath%>teacher/teacherreportshow?rid=${item.cId}">${item.experimental.cExperimentName}</a></td>
										<td>${item.student.getcStudentNumber()}</td>
										<td><c:forEach var="gradeitem"
												items="${gradeMajorColleges}">
												<c:if test="${item.student.cGradeId==gradeitem.cId}">
											     ${gradeitem.getcYearClass()} ${gradeitem.getMajor().getcMajorName()}${gradeitem.getcClass()}
												<c:set var="isDoing" value="1" />
												</c:if>
											</c:forEach></td>
											<td><a class="btn btn-success radius r mr-20" onclick="lookFunc(${ item.getcId()})" style="text-decoration: none"
								class="ml-5" id="edit" href="javascript:;" title="查看">查看</a></td>
										<td>
											<!-- 判断是否到批改时间 --> <c:choose>
												<c:when test="${item.cStatu==1}">
											已批改
											</c:when>
												<c:when test="${item.getGradeExam().getcStatus()==0}">
											报告提交中
											</c:when>
												<c:otherwise>
													<input type="button" value="批改" class="btn btn-primary radius"
														style="height: 30px; width: 60px; "
														onclick="location.href='<%=basePath%>teacher/reportcorrect?rid=${item.cId}'" />
												</c:otherwise>
											</c:choose>
										</td>
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
						url : '<%=basePath%>teacher/delGradeExam.action?ids=' + ids,
						success : function(data) {
							if (data.success) {
								//删除成功，刷新页面
								alert("删除成功！");	
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
					url : '<%=basePath%>teacher/delGradeExam.action?ids=' + id,
					success : function(data) {
						if (data.success) {
							alert("删除成功！");
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
		//查看报告
		function lookFunc(id){			
				layer_show("查看报告","<%=basePath%>student/reportShow?id="+id,'500','400')			
		}
	</script>
</body>
</html>