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
<title>成绩管理</title>
<style>
label {
	font-size: 12px;
	cursor: pointer;
}

label i {
	font-size: 12px;
	font-style: normal;
	display: inline-block;
	width: 12px;
	height: 12px;
	text-align: center;
	line-height: 12px;
	color: #fff;
	vertical-align: middle;
	margin: -2px 2px 1px 0px;
	border: #2489c5 1px solid;
}

input[type="radio"] {
	display: none;
}

input[type="radio"]+i {
	border-radius: 7px;
}

iinput[type="radio"]:checked+i {
	background: #2489c5;
}

input[type="radio"]:disabled+i {
	border-color: #ccc;
}

input[type="radio"]:checked:disabled+i {
	background: #ccc;
}
</style>
<script type="text/javascript">

function showAddInput() {
	$("#addinfo").css('display', 'block');
	$("#addinfo").css('text-align', 'center');
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
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页<a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
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
									<option value="${item.cId}">${item.getcYearClass()}
										${item.getMajor().getcMajorName()} ${item.getcClass()}</option>
								</c:forEach>
						</select></span>
					</div>
					<div style="width: 40%; text-align: right; float: left;">
						<a style="width: 100px; margin-right: 60%" type="submit"
							class="btn btn-primary radius" onclick="exportExcelByGrade()"
							id="" href="javascript:;" name="">导出</a>
					</div>
				</div>

			</form>
		</div>

		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <a class="btn btn-primary radius"
				onclick="exportExcel()" href="javascript:;">导出成绩</a> <a
				class="btn btn-success radius" onclick="showAddInput()"
				href="javascript:;">分班导出</a>
			</span>
		</div>

		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox"
							id="title-table-checkbox" name="title-table-checkbox"></th>
						<th width="40">ID</th>
						<!--<th>证书编号</th>  -->
						<th>实验名称</th>
						<th>学号</th>
						<th>成绩</th>
						<th width="100">评语</th>
						<th width="100">批注</th>
						<th width="200px">状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="rRitem1" items="${reportRelativesbyStuid}">
						<tr class="text-c">
							<td><input name="checkboxid" type="checkbox"
								value="${ rRitem1.cId}" /></td>
							<td>${rRitem1.cId }</td>
							<!--  <td>${rRitem1.cReportNum }</td>-->
							<td>${rRitem1.experimental.cExperimentName }</td>
							<td>${rRitem1.student.cStudentNumber }</td>
							<td>${rRitem1.scoreSheet.cSum}</td>
							<td><a class="btn btn-primary radius r mr-20"
								style="line-height: 1.6em; margin-top: 3px" href="javascript:;"
								onclick="lookConclusion('${rRitem1.scoreSheet.cConclution}')">查看</a></td>
							<td><a class="btn btn-success radius r mr-20"
								style="line-height: 1.6em; margin-top: 3px" href="javascript:;"
								onclick="article_add('查看批注','<%=basePath%>student/scoreShow?rid='+${rRitem1.cId},'1000','600')">查看</a></td>
							<td><c:choose>
									<c:when test="${rRitem1.cStatu=='2'}">
										<div id="${rRitem1.cId}">

											<label><input type="radio" name="${rRitem1.cId}"
												onclick="allowSubmit('${rRitem1.cId}','3')" value="3"><i>✓</i>补考</label>
											<label><input type="radio" name="${rRitem1.cId}"
												onclick="allowSubmit('${rRitem1.cId}','4')" value="4"><i>✓</i>不及格</label>
										</div>
									</c:when>
									<c:when test="${rRitem1.cStatu=='3'}">
										<span class="label label-success radius">补考中</span>
									</c:when>
									<c:when test="${rRitem1.cStatu=='4'}">
										<span class="label label-danger radius">不及格</span>
									</c:when>
									<c:when test="${rRitem1.cStatu=='0'}">
										<span class="label label-danger radius">补考未批改</span>
									</c:when>
									<c:otherwise>
										<span class="label label-success radius">通过</span>
									</c:otherwise>
								</c:choose></td>
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
		//导出函数
		function exportExcel() {
			var ids = getSelectedId();
			if (ids.length == 0) {
				layer.msg('请选择一条记录！', {
					icon : 6,
					time : 1000
				});			
				return;
			}
           var statu=confirm('确认所选' + ids.length + '条记录?');
			if(statu){
				window.location.href='<%=basePath%>teacher/excelout.action?ids='+ ids;
			}
				
		}
		//导出函数
		function exportExcelByGrade() {
		   var cGradeId = $("#cGradeId").val();  
		   var experiment = $("#experiment").val();  
		   if(experiment==""){
			   alert("请选择实验！");
		   }else if(cGradeId==""){
        	   alert("请选择班级！");
           }else{
			var statu=confirm('确认要导入记录?');
			if(statu){
				  $.ajax({
						type : 'get',
						dataType : 'json',
						url : '<%=basePath%>teacher/checkExport',
						data:{
							cGradeId:cGradeId,
							experiment:experiment
						},
						success : function(data) {
							if (data.success) {						
								window.location.href='<%=basePath%>teacher/exportExcelByGrade?cGradeId='+ cGradeId+'&experiment='+experiment;
					
							} else {
								alert("数据不存在，请确认后再导出！");	
							}
							
						},error : function() {
							alert("服务器异常，请稍候再试！");
						}
					});
                }
           }
		}
		
		function lookConclusion(msg) {
			layer.msg(msg, {				
				time : 4000
			});	
		}
		//查看批注
		function article_add(title, url, w, h) {
			layer_show(title,url,w,h)
		}
		//更改不及格状态
	 function allowSubmit(id,msg){
		 var str;
		  if(msg=="3"){
			  str="确定要允许该学生补考?<br>确定之后将不可更改！";
		  }else if(msg=="4"){
			str="确定将该学生报告判为不及格?<br>确定之后将不可更改！";
		  }
		  layer.confirm(str, {icon: 3, title:'提示'},  function(index){
			  $.ajax({
					type : 'get',
					dataType : 'json',
					url : '<%=basePath%>teacher/allowSubmit',
					data:{
						id:id,
						msg:msg
					},
					success : function(data) {
						if (data.success) {						
							alert(data.msg);							
						} else {
							alert(data.msg);	
						}
						location.reload();
					},error : function() {
						alert("服务器异常，请稍候再试！");
					}
				});
			  
			  layer.close(index);
		});
 }
	</script>
</body>
</html>