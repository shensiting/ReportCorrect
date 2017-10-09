<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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

<title>班级管理</title>
<script type="text/javascript">

function sumit(Gradeform) {
	var id=Gradeform.id.value;
	var cYearClass=Gradeform.cYearClass.value;
	var cClass=Gradeform.cClass.value;
	var cMajor=Gradeform.cMajor.value;
	var cCollege=Gradeform.cCollege.value;
	$.ajax({
		type : 'get',
		dataType : 'json',
		async: false,
		url : '<%=basePath%>manage/addGrade.action?id=' +id+'&cYearClass='+cYearClass+'&cClass='+cClass+'&cMajor='+cMajor+'&cCollege='+cCollege+'',
		success : function(data) {
			if(data.success){ 
				alert(data.msg);	
			}else{
				layer.msg('操作失败，请重新操作', {
					icon : 6,
					time : 5000
				});	
			}

		}, error: function(XMLHttpRequest, textStatus, errorThrown) {
			 alert(XMLHttpRequest.status);
			 alert(XMLHttpRequest.readyState);
			 alert(textStatus);
			   }
	});
		
}

</script>
</head>
<body>

	<div style="margin-left: 80px; margin-top: 40px; text-align: center;">
		<form action="" method="post" class="form form-horizontal"
			id="Gradeform">
			<div class="row cl">
				<label class="form-label col-2">学院：</label> <input type="hidden"
					name="id" value="${gradeMajorCollege.cId }" />
				<div class="formControls col-6">
					<span class="select-box"> <select name="cCollege"
						id="cCollege" class="select" size="1" datatype="*"
						nullmsg="请选择学院！">
							<c:forEach var="Collegeitem" items="${colleges}">
								<option value="${Collegeitem.cId }">${Collegeitem.cCollegeName}</option>
							</c:forEach>
					</select>
					</span>
					<script>
						var cId = "${gradeMajorCollege.cCollegeId }";
						if (cId != "") {
							var cCollege = document.getElementById("cCollege");
							cCollege.value = cId;
						}
					</script>
				</div>
			</div>
			<div class="col-4"></div>
			<div class="row cl">
				<label class="form-label col-2">年级：</label>
				<div class="formControls col-6">
					<span class="select-box"> <select class="select" size="1"
						datatype="*" nullmsg="请选择年级！" name="cYearClass" id="cYearClass">
							<c:forEach var="i" begin="2011" end="2018">
								<option><c:out value="${i}" />级
								</option>
							</c:forEach>
					</select>

					</span>
					<script>
						var cId = "${gradeMajorCollege.cYearClass}";
						if (cId != "") {
							var cYearClass = document
									.getElementById("cYearClass");
							for (var i = 0; i < cYearClass.options.length; i++) {
								if (cYearClass.options[i].value.toString() == cId) {
									cYearClass.options[i].selected = true;
									break;
								}
							}
						}
					</script>
				</div>
			</div>
			<div class="col-4"></div>
			<div class="row cl">
				<label class="form-label col-2">专业：</label>
				<div class="formControls col-6">
					<span class="select-box"> <select class="select" size="1"
						datatype="*" nullmsg="请选择专业！" name="cMajor" id="cMajor">
							<c:forEach var="Majoritem" items="${majorList}">
								<option value="${Majoritem.cId }">${Majoritem.cMajorName}</option>
							</c:forEach>
					</select>

					</span>
					<script>
						var cId = "${gradeMajorCollege.cMajorId }";
						if (cId != "") {
							var cMajor = document.getElementById("cMajor");
							cMajor.value = cId;
						}
					</script>
				</div>
			</div>

			<div class="col-4"></div>
			<div class="row cr">
				<label class="form-label col-2">班别：</label>
				<div class="formControls col-6">
					<span class="select-box"> <select class="select" size="1"
						datatype="*" nullmsg="请选择班别！" name="cClass" id="cClass">
							<c:forEach var="i" begin="1" end="10">
								<option><c:out value="${i}" />班
								</option>
							</c:forEach>
					</select>
					</span>
					<script>
						var cId = "${gradeMajorCollege.cClass}";

						if (cId != "") {
							var cClass = document.getElementById("cClass");
							for (var i = 0; i < cClass.options.length; i++) {
								if (cClass.options[i].value.toString() == cId) {
									cClass.options[i].selected = true;
									break;
								}
							}
						}
					</script>
				</div>
			</div>
			<div class="col-8" id="show" style="display: none;">
				<label class="form-label" style="color: red">操作成功！请返回原来页面刷新。</label>
			</div>
			<div class="row cl" style="margin-left: -70px;">
				<button style="width: 150px" type="submit"
					class="btn btn-success radius" onclick="sumit(this.form)" id=""
					name="">提交</button>
			</div>
		</form>

	</div>
</body>
</html>