<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
	<title>图书类型</title>
	<jsp:include page="../common/css.jsp"></jsp:include>
	<style>
	body {
		/*background-image: url("static/images/01.jpg");*/
	}
	</style>
</head>

<body class="bootstrap-admin-with-small-navbar">
	<jsp:include page="../common/top.jsp"></jsp:include>
	
	<div class="container">
		<!-- left, vertical navbar & content -->
		<div class="row">
			<jsp:include page="../common/left.jsp"></jsp:include>

			<!-- content -->
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">图书分类管理</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form class="form-horizontal" action="/Demo_war_exploded/selectServlet"
									method="post">
									<div class="col-lg-3 form-group">
										<button type="button" class="btn btn-primary" id="btn_add"
											data-toggle="modal" data-target="#addModal">添加分类</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-12">
						<table id="data_list" class="table table-hover table-bordered"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>ID</th>
									<th>图书分类名称</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${p.list}" var="type" >
									<tr>
										<td>${type.tid}</td>
										<td>${type.typeName}</td>
										<td>
											<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#updateModal" id="btn_update"
										onclick="showInfo2('${type.tid}','${type.typeName}')">修改</button>
											<button type="button" class="btn btn-danger btn-xs" onclick="deleteType(${type.tid})">删除</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br>
						<jsp:include page="/page.jsp">
							<jsp:param value="type?method=list	" name="url"/>
						</jsp:include>
						<br>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function showInfo2(tid, typeName) {

			console.log(tid);
			console.log(typeName);
			document.getElementById("updatetid").value = tid;
			document.getElementById("updateType").value = typeName;
		}

		function deleteType(tid) {
			con=confirm("是否删除?");
			$.post("type_delete",{tid:tid},function (data){
				if(data=="ok"){
					location.reload();
				}
			});
		}
		function addType(){

			$.post("type_add",{typeName:document.getElementById('addBookName').value
			},function (data){
				if(data=="ok"){
					location.reload();
				}
			});
		}


	</script>
	<!-------------------------------------------------------------->

	<!-- 修改模态框（Modal） -->
	<form class="form-horizontal" method="post" onsubmit="updType()"
		action="<%=basePath%>/type?method=updateType">
		<!--保证样式水平不混乱-->
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
			aria-labelledby="updateModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="updateModalLabel">修改图书分类</h4>
					</div>
					<div class="modal-body">

						<!---------------------表单-------------------->

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">图书名称</label>
							<div class="col-sm-7">
								<input type="hidden" name="tid" id="updatetid">
								 <input
									type="text" class="form-control" id="updateType" name="typeName1"
									placeholder="请输入图书分类名称"> <label class="control-label"
									for="updateType" style="display: none;"></label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary" >修改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>
	<!-------------------------------------------------------------->

	<!--------------------------------------添加的模糊框------------------------>
	<form class="form-horizontal" method="post" onsubmit="addType()"
		action="<%=basePath%>/type?method=addType">
		<!--保证样式水平不混乱-->
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">添加新图书分类</h4>
					</div>
					<div class="modal-body">

						<!---------------------表单-------------------->

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">分类名称</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="addBookName"
									required="required" name="typeName" placeholder="请输入图书分类名称">
								<label class="control-label" for="addBookName"
									style="display: none;"></label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">添加</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>
	<!--------------------------------------添加的模糊框------------------------>

	<!-------------------------------------------------------------->
	<script>
		function addType(){

			$.post("type_add",{typeName:document.getElementById('addBookName').value
			},function (data){
				if(data=="ok"){
					location.reload();
				}
			});
		}
        function updType(){
            //console.log(1)
            //alert(1);
            $.post("type_update",{typeName:document.getElementById('updateType').value
            ,tid:document.getElementById("updatetid").value},function (data){
                if(data=="ok"){
                    location.reload();
                }
            });
        }
        function test() {
            alert(1);
        }
	</script>
	<jsp:include page="../common/userInfo.jsp"></jsp:include>
	<jsp:include page="../common/js.jsp"></jsp:include>
</body>
</html>