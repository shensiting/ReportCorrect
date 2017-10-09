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
<title>评论管理</title>
<script type="text/javascript">
//修改函数

	function showAddInput() {
		$("#addinfo").css('display', 'block');
		$("#addinfo").css('text-align', 'center');
	}

</script>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 评论管理 <a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20">
		<div id="addinfo" style="display: none; margin-left: 23%">
			<form class="form-horizontal" method="post" action="" id="exid">
				<div class="row cl">
					<label class="form-label col-1">实验分类：</label>
					<div class="formControls col-2">
						<select style="width: 250px" id="testId" name="testId"
							onchange="testIdChag()" class="select">
							<option value="">----请选择实验分类----</option>
							<c:forEach var="item" items="${tests}">
								<option value="${item.cId }">${item.cTestName}</option>
							</c:forEach>
						</select>
					</div>
					<label class="form-label col-1" style="margin-left: 8%">实&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验：</label>
					<div class="formControls col-2">
						<select style="width: 250px" id="examId" name="examId"
							class="select">
							<option value="">-----请选择实验-----</option>
						</select>
					</div>
				</div>
				<div class="row cl" style="margin-top: 1%">
					<label class="form-label col-1"> 开始时间：</label>
					<div class="formControls col-2">
						<input type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
							id="datemin" name="datemin" class="input-text Wdate"
							style="width: 250px;">
					</div>
					<label class="form-label col-1" style="margin-left: 8%">
						结束时间：</label>
					<div class="formControls col-2">
						<input type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'datemin\')}'})"
							id="datemax" name="datemax" class="input-text Wdate"
							style="width: 250px;">
					</div>
				</div>
				<div class="row cl" style="margin-top: 1%">
					<label class="form-label col-1">帐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：
					</label>
					<div class="formControls col-2">
						<input class="input-text" type="text" style="width: 250px"
							id="num" name="num">

					</div>
					<div style="width: 40%; text-align: right; float: left;">
						<a style="width: 100px; margin-right: 34%"
							class="btn btn-primary radius" id="search" name="search"
							onclick="submit()" href="javascript:;"> <i
							class="Hui-iconfont">&#xe665;</i>查找
						</a>
					</div>
				</div>

			</form>
		</div>

		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <a href="javascript:;" onclick="deleteFunc()"
				class="btn btn-danger radius"> <i class="Hui-iconfont">&#xe6e2;</i>批量删除
			</a> <a class="btn btn-success radius" onclick="showAddInput()"
				href="javascript:;"> <i class="Hui-iconfont">&#xe665;</i>更多查找
			</a></span>
		</div>

		<div class="mt-20">
			<div class="row cl" style="margin-bottom: 2%">
				<label class="form-label col-0">实验分类：</label>
				<div class="formControls col-2">
					<span class="select-box"> <select id="test" name="test"
						onchange="test()" class="select">
							<option value="">----请选择实验分类----</option>
							<c:forEach var="item" items="${tests}">
								<option value="${item.cId }">${item.cTestName}</option>
							</c:forEach>
					</select>
					</span>
				</div>

				<label class="form-label col-0" style="margin-left: 5%">实验：</label>
				<div class="formControls col-2">

					<span class="select-box"> <select id="experiment"
						name="experiment" class="select">
							<option value="">-----请选择实验-----</option>
					</select>
					</span>
				</div>
			</div>
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox"
							id="title-table-checkbox" name="title-table-checkbox"></th>
						<th width="40px">ID</th>
						<th width="52%">帖子主题</th>
						<th>创建时间</th>
						<th>状态</th>
						<th width=10%>评论人数</th>
						<th width="100px">查看</th>
						<th width="50px">删除</th>
					</tr>
				</thead>
				<tbody id="tbody-result">
					<c:forEach var="item" items="${TopicTheme }">

						<tr class="text-c">
							<td width="25"><input type="checkbox" name="checkboxid"
								value="${item.cId }"></td>
							<td>${item.cId }</td>
							<td><a class="maincolor" href="javascript:;"
								onclick="getContent('${item.getcId()}')">${item.cTitle }</a></td>

							<td><fmt:formatDate type="both" value="${item.cCreateTime }" />
							</td>
							<td><c:choose>
									<c:when test="${item.getcTopicStatus()==1}">
												通过
									</c:when>
									<c:otherwise>
												等待审核
									</c:otherwise>
								</c:choose></td>
							<td>${item.cCommentNum}</td>
							<td><a class="btn btn-success radius r mr-20"
								onclick="qusShow('${item.getcId()}')"
								style="text-decoration: none" class="ml-5" id="edit"
								href="javascript:;" title="查看">查看</a></td>
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
		function getContent(id) {
			var url='<%=basePath%>manage/showTopic?id='+id;
			layer_show("问题显示",url,1000,650);
		}
		
		function qusShow(id){
			$.ajax({
				type : 'get',
				dataType : 'json',
				url : '<%=basePath%>teacher/getContent?id=' + id,
				success : function(data) {
					layer.open({
						  type: 1,
						  skin: 'layui-layer-rim', //加上边框
						  area: ['750px', '350px'], //宽高
						  content: '<div class="pd-20" style="margin-left: 5%; margin-right: 5%;">'+data.content+'</div>'
						});
					
				},error : function() {
					alert("服务器异常，请稍候再试！");
				}
			});
		}
		//第一个下拉框
		 function testIdChag(){               
		        var testId = $("#testId").val();  
		        $.ajax({  
		            type:"POST",  
		            url :"../teacher/getRelevance",  
		            data:{                    
		                id:testId  
		            },  
		            dataType:"json",  
		            success:function(data){ 
		                $("#examId").empty();   
		                $("#examId").append( "<option value=''>-----请选择实验-----</option>");  
		                $.each(data,function(index,item){                        
		                    //填充内容  
		                    $("#examId").append( "<option value='"+item.cId+"'>"+item.cExperimentName+"</option>");  
		                });  
		            }
		        });  
           }
		
	
		
		//第二个下拉框
		 function test(){               
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
		                $("#experiment").append( "<option value=''>-----请选择实验-----</option>");  
		                $.each(data,function(index,item){                        
		                    //填充内容  
		                    $("#experiment").append( "<option value='"+item.cId+"'>"+item.cExperimentName+"</option>");  
		                });  
		            }
		        });  
           }
		
		$(function(){  
		    //触发的下拉框chang事件  
		    $("#experiment").change(function(){  
		        var experiment = $("#experiment").val();  
		        var tbody=document.getElementById("tbody-result"); 
		        var str="";
		        $.ajax({  
		            type:"POST",  
		            url :"../manage/getTopicComment",  
		            data:{                    
		                id:experiment  
		            },  
		            dataType:"json",  
		            success:function(data){  
		            	if(data=="")
					    	  str="无数据";
		            	  $("#tbody-result").empty(); 
							 $.each(data,function(index,item){                        		                	
								  str += "<tr  class='text-c'><td><input type='checkbox' name='checkboxid' value="+item.cId +"></td>" +  
		                          "<td>"+item.cId+"</td>" +  
		                          "<td><a class='maincolor' href='javascript:;' onclick='getContent("+item.cId+")'>"+item.cTitle+"</a></td>"+
		                           "<td>" + item.cCreateTime+ "</td>" +
		                           "<td>" + item.status+ "</td>" + 
		                          "<td>" + item.cCommentNum+ "</td>" +  
		                          "<td> <a class='btn btn-success radius r mr-20' onclick='qusShow("+item.cId+")' style='text-decoration: none' class='ml-5' id='edit' href='javascript:;' title='查看'>查看</a></td>" +                             
		                         "<td><a style='text-decoration: none' class='ml-5' onClick='article_del(this,"+item.cId+")' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></td>"+
		                          "</tr>";  		                      			                         		                
		                       });  
							  tbody.innerHTML = str;  
		            }  
		        });  
		    });                                       
		});  
		function submit() { 
			var examId =$("#examId").val();
			var datemin =  $("#datemin").val();
			var datemax =  $("#datemax").val();
			var num =  $("#num").val();						
			if(experiment=="")
			{
				layer.alert("实验不允许为空！")
			}else if(num==""){
				layer.alert("学号不允许为空！")
			}
			else{
				var str="";
				 var tbody=document.getElementById("tbody-result"); 
				$.ajax({
					type : 'POST',
					dataType : 'json',
					async: false,
					url : '<%=basePath%>manage/getTopic',
					data: {experiment:examId,datemin:datemin,datemax:datemax,num:num},	
					success : function(data) {
						if(data=="")
					    	  str="无数据";
						       $("#tbody-result").empty(); 
								 $.each(data,function(index,item){                        		                	
									  str += "<tr  class='text-c'><td><input type='checkbox' name='checkboxid' value="+item.cId +"></td>" +  
			                          "<td>"+item.cId+"</td>" +  
			                          "<td><a class='maincolor' href='javascript:;' onclick='getContent("+item.cId+")'>"+item.cTitle+"</a></td>"+
			                           "<td>" + item.cCreateTime+ "</td>" +
			                           "<td>" + item.status+ "</td>" + 
			                          "<td>" + item.cCommentNum+ "</td>" +  
			                          "<td> <a class='btn btn-success radius r mr-20' onclick='qusShow("+item.cId+")' style='text-decoration: none' class='ml-5' id='edit' href='javascript:;' title='查看'>查看</a></td>" +                             
			                         "<td><a style='text-decoration: none' class='ml-5' onClick='article_del(this,"+item.cId+")' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></td>"+
			                          "</tr>";  		                      			                         		                
			                       });  
								  tbody.innerHTML = str;    
						},
						error : function() {
							alert("服务器异常，请稍候再试！");
						}
					});
				}
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
						url : '<%=basePath%>manage/delTheme.action?ids=' + ids,
						success : function(data) {
							if (data.success) {
								//删除成功，刷新页面
								alert("删除成功！");
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
		/*添加*/
		function article_add(title, url, w, h) {
			layer_show(title,url,w,h)
		}
		
		/*删除*/
		function article_del(obj, id) {
			layer.confirm('确认要删除吗？', function(sender, modal, index) {
				//只有一个确定按钮，这里进行删除操作
				//通过ajax向后台请求
				$.ajax({
					type : 'get',
					dataType : 'json',
					url : '<%=basePath%>manage/delTheme.action?ids=' + id,
					success : function(data) {
						if (data.success) {
							alert("删除成功！");	
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