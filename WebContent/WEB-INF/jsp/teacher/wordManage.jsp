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
<title>报告管理</title>
<style type="text/css">
.editor td {
	text-align: center;
}
</style>
<script type="text/javascript">
//修改函数

	function showAddInput() {
		$("#addinfo").css('display', 'block');
		$("#addinfo").css('text-align', 'center');
	}
/*提交*/
function submit() {
	var experiment =$("#experiment").val();
	var cGradeId =  $("#cGradeId").val();
	if(experiment==""||cGradeId=="")
	{
		layer.alert("条件不允许为空！")
	}else{		
		document.forms[0].action = '<%=basePath%>teacher/getWordList?eptId='+experiment+'&gradeId='+cGradeId+'';		
		document.forms[0].submit();		
	}
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
			<form class="form-horizontal" method="post" action=""
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
								<option value="">----请选择实验----</option>
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
									<option value="${item.cId}">${item.getCollege().getcCollegeName()}
										${item.getcYearClass()} ${item.getMajor().getcMajorName()}
										${item.getcClass()}</option>
								</c:forEach>
						</select></span>
					</div>
					<div style="width: 40%; text-align: right; float: left;">
						<a style="width: 100px; margin-right: 60%"
							class="btn btn-primary radius" id="search" name="search"
							onclick="submit()" href="javascript:;"> <i
							class="Hui-iconfont">&#xe665;</i>查找
						</a>
					</div>
				</div>
			</form>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <a class="btn btn-success radius"
				onclick="showAddInput()" href="javascript:;"><i
					class="Hui-iconfont"></i>按条件查找</a></span> <span class="r">共有数据：<strong>${reportRelatives.size() }</strong>条
			</span>
		</div>

		<div class="mt-20">
			<table id="reportList" name="reportList"
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="100">ID</th>
						<th>实验报告编号</th>
						<th>实验名称</th>
						<th>学号</th>
						<th>班级</th>
						<th width="100px">查看</th>
						<th width="10%">状态</th>
						<th width="50px">删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${reportRelatives}">
						<tr class="editor">
							<td>${item.cId}</td>
							<td>${item.cReportNum}</td>
							<td>${item.experimental.cExperimentName}</td>
							<td>${item.student.getcStudentNumber()}</td>
							<td><c:forEach var="gradeitem" items="${gradeMajorColleges}">
									<c:if test="${item.student.cGradeId==gradeitem.cId}">
											    ${gradeitem.getCollege().getcCollegeName()} ${gradeitem.getcYearClass()} ${gradeitem.getMajor().getcMajorName()}${gradeitem.getcClass()}
												<c:set var="isDoing" value="1" />
									</c:if>
								</c:forEach></td>
							<td><a class="btn btn-primary radius r mr-20"
								onclick="lookWordFunc(${item.getcId()})"
								style="text-decoration: none" class="ml-5" id="edit"
								href="javascript:;" title="查看">查看</a></td>
							<td>
								<!-- 判断是否到批改时间 --> <c:choose>
									<c:when test="${item.cStatu==1}">
										<span class="label label-success radius">已批改</span>
									</c:when>
									<c:when test="${item.cStatu==3}">
										<span class="label label-success radius">报告提交中</span>
									</c:when>
									<c:when test="${item.cStatu==4}">
										<span class="label label-danger radius">不及格</span>
									</c:when>
									<c:when test="${item.cStatu==2}">
										<span class="label label-primary radius">正在审核</span>
									</c:when>
									<c:when test="${item.getGradeExam().getcStatus()==0}">
										<span class="label label-success radius">报告提交中</span>
									</c:when>
									<c:otherwise>
										<input type="button" value="批改" class="btn btn-primary radius"
											style="height: 30px; width: 60px;"
											onclick="location.href='<%=basePath%>teacher/wordCorrect?rid=${item.cId}'" />
									</c:otherwise>
								</c:choose>
							</td>
							<td><a style="text-decoration: none" class="ml-5"
								onClick="article_del(this,'${ item.cId}')" href="javascript:;"
								title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
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
	<script type="text/javascript"
		src="<%=basePath%>ueditor/ueditor.parse.js"></script>
	<script type="text/javascript">
		$('.table-sort').dataTable({
			"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
			"bStateSave" : true,//状态保存
			"aoColumnDefs" : [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{
				"orderable" : false,
				"aTargets" : [ 2,4,5,7  ]
			} // 制定列不参与排序
			]
		});
		//查看word报告
		function lookWordFunc(id){			
				layer_show("查看word报告","<%=basePath%>student/reportWordShow?id="+id,'1000','600')			
		}
	
		
		/*资讯-删除*/
		function article_del(obj, id) {
			layer.confirm('删除将不可恢复！确认要删除该学生上传的作业？', function(sender, modal, index) {
				//只有一个确定按钮，这里进行删除操作
				//通过ajax向后台请求
				$.ajax({
					type : 'get',
					dataType : 'json',
					url : '<%=basePath%>teacher/delWord.action?id=' + id,
					success : function(data) {
						if (data.success) {
							alert(data.msg);
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