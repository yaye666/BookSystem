<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 左侧导航栏 -->
<div class="col-md-2 bootstrap-admin-col-left">
	<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
		<li><a href="${pageContext.request.contextPath}/book?method=list"><i
				class="glyphicon glyphicon-chevron-right"></i> 图书管理</a></li>
		<li><a href="${pageContext.request.contextPath}/user?method=list"><i
				class="glyphicon glyphicon-chevron-right"></i> 用户管理</a></li>
		<li><a href="${pageContext.request.contextPath}/type?method=list"><i
				class="glyphicon glyphicon-chevron-right"></i> 图书分类管理</a></li>
		<li><a href="${pageContext.request.contextPath}/borrow?method=list"><i
				class="glyphicon glyphicon-chevron-right"></i> 图书借阅信息</a></li>
		<li><a href="${pageContext.request.contextPath}/history?method=list"><i
				class="glyphicon glyphicon-chevron-right"></i> 图书归还信息</a></li>
	</ul><br><br>
	<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
		<li><a href="${pageContext.request.contextPath}/recommend?method=list"><i
				class="glyphicon glyphicon-chevron-right"></i> 热门推荐</a></li>
		<li><a href="${pageContext.request.contextPath}/bestreader?method=list"><i
				class="glyphicon glyphicon-chevron-right"></i> 最佳读者</a></li>
	</ul><br><br>
	<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
		<li><a href="${pageContext.request.contextPath}/feedback?method=list"><i
				class="glyphicon glyphicon-chevron-right"></i> 读者反馈</a></li>
	</ul>
</div>