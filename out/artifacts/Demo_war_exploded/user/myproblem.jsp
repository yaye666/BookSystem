<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html class="ax-vertical-centered">
<head>
	<title>图书馆管理系统</title>
	<jsp:include page="../common/css.jsp"></jsp:include>
	<style>
		body {
			/*background-image: url("04.jpg");*/
		}
	</style>
</head>

<body class="bootstrap-admin-with-small-navbar">
	<jsp:include page="../common/top.jsp"></jsp:include>

	<div class="container">
		<!-- left, vertical navbar & content -->
		<div class="row">
			<jsp:include page="../common/user_left.jsp"></jsp:include>
			<!-- content -->
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">我的反馈</div>
							</div>
							<div
									class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form class="form-horizontal" action="/Demo_war_exploded/selectServlet"
									  method="post">
									<div class="col-lg-3 form-group">
										<button type="button" class="btn btn-primary" id="btn_add"
												data-toggle="modal" data-target="#addModal" onclick="location.href='/Demo_war_exploded/feedback.jsp'">反馈问题</button>
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
									<th>标题</th>
									<th>问题页面</th>
									<th>问题描述</th>
									<th>联系方式</th>
									<th>问题状态</th>
								</tr>
							</thead>

							<tbody>
							<c:forEach items="${p.list}" var="problem" varStatus="status">
								<tr>
									<td>${problem.pid}</td>
									<td>${problem.title}</td>
									<td>${problem.page}</td>
									<td>${problem.content}</td>
									<td>${problem.link}</td>
									<td>
										<c:if test="${problem.status==0}">未解决</c:if>
										<c:if test="${problem.status==1}">已解决</c:if>
									</td>

								</tr>
							</c:forEach>
							</tbody>
						</table>
						<br>
						<jsp:include page="/page.jsp">
							<jsp:param value="feedback?method=mylist" name="url"/>
						</jsp:include>
						<br>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--------------------------------------添加的模糊框------------------------>

	<jsp:include page="../common/userInfo.jsp"></jsp:include>
	<jsp:include page="../common/js.jsp"></jsp:include>
</body>
</html>