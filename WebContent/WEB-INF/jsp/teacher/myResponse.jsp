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
<link href="<%=basePath%>users/lib/icheck/icheck.css" rel="stylesheet"
	type="text/css">
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=basePath%>users/lib/html5.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>users/lib/PIE_IE678.js"></script>
<![endif]-->


<!--[if IE 6]>
<script type="text/javascript" src="<%=basePath%>users/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>我的评论</title>
</head>
<body>
	<nav class="breadcrumb"> <a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20" style="margin-left: 5%; margin-right: 5%;">
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
			<div id="tadiv">
			<table class="table table-border table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th class="text-l">问答标题</th>
						<th>ID</th>
						<th width="100px">操作</th>
					</tr>
				</thead>
				<tbody id="tbody-result">
					<c:forEach var="item" items="${topicResponseLinks}">
						<tr class="text-c">
							<td class="text-l">RE: <a class="maincolor"
								href="javascript:;" onclick="article_add(${item.cTopicId})">${item.topicTheme.cTitle}</a>
								<span style="color: gray;">（<fmt:formatDate type="both"
										value="${item.getcCreateTime()}" /> ）
							</span></td>

							<td>${item.getcId()}</td>
							<td><a class="maincolor"
								onClick="getContent('${item.getcId()}')" href="javascript:;">
									查看评论 </a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			</div>
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
		src="<%=basePath%>users/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>users/lib/Validform/5.3.2/Validform.min.js"></script>

	<script type="text/javascript">
		$('.table-sort').dataTable({
			//"aaSorting" : [ [ 2, "desc" ] ],//默认第几个排序
			 "bLengthChange": true,                  //是否允许用户自定义每页显示条数。  
         
			"bStateSave" : true,//状态保存
			"aoColumnDefs" : [
			//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
			{
				"orderable" : false,
				"aTargets" : [ 0,2 ]
			} // 制定列不参与排序
			]
		});
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
		 function taReload(){
				$('.table-sort').dataTable({
					"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
					 "bLengthChange": true,                  //是否允许用户自定义每页显示条数。  
		         
					"bStateSave" : true,//状态保存
					"aoColumnDefs" : [
					//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
					{
						"orderable" : false,
						"aTargets" : [ 0,2 ]
					} // 制定列不参与排序
					]
				});
			}
			
		$(function(){  
		    //触发的下拉框chang事件  
		    $("#experiment").change(function(){  
		        var experiment = $("#experiment").val();  		       
		        var str="";
		        $.ajax({  
		            type:"POST",  
		            url :"../teacher/getTeaResponse",  
		            data:{                    
		                id:experiment  
		            },  
		            dataType:"json",  
		            success:function(data){  
		            	$("#tadiv").empty()
		            	str="<table class='table table-border table-bg table-sort'><thead><tr class='text-c'><th class='text-l'>问答标题</th><th>ID</th><th width='100px'>操作</th></tr></thead><tbody id='tbody-result'>";
		            	$.each(data,function(index,item){                        		                	
	                          str += "<tr  class='text-c'><td class='text-l'>RE: <a class='maincolor' href='javascript:;' onclick='article_add("+item.cTopicId+")'>"+item.cTitle+
	                          "</a><span style='color: gray;'>（"+item.cCreateTime+" ）</span></td>" +  
	                          
	                          "<td>" + item.cId+ "</td>" +  
	                          "<td> <a class='maincolor' onclick='getContent("+item.cId+
	                        		  ")' href='javascript:;'>查看评论 </a></td>" +                             
	                          "</tr>";  		                      
	                    		                
	                    });  
		            	str+="</tbody></table>";
		            	$("#tadiv").html(str);  
                        taReload();           
		            }  
		        });  
		    });                                       
		});  
		function article_add(id) {
			var url='<%=basePath%>student/showTopic?id='+id;
			layer_show("问题显示",url,1000,650);
		}
		function getContent(id){
			$.ajax({
				type : 'get',
				dataType : 'json',
				url : '<%=basePath%>teacher/getResponseContent?id=' + id,
				success : function(data) {
					layer.open({
						  type: 1,
						  skin: 'layui-layer-rim', //加上边框
						  area: ['600px', '250px'], //宽高
						  content: '<div class="pd-20" style="margin-left: 5%; margin-right: 5%;">'+data.content+'</div>'
						});
					
				},error : function() {
					alert("服务器异常，请稍候再试！");
				}
			});
		}
		
		</script>
</body>
</html>