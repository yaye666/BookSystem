<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 用户端左侧导航栏 -->
<div class="col-md-2 bootstrap-admin-col-left">
	<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
		<li><a href="/Demo_war_exploded/book?method=list2"><i
				class="glyphicon glyphicon-chevron-right"></i> 图书查询</a></li>
		<li><a href="/Demo_war_exploded/borrow?method=list"><i
				class="glyphicon glyphicon-chevron-right"></i> 借阅信息</a></li>
		<li><a href="/Demo_war_exploded/history?method=myHistory"><i
				class="glyphicon glyphicon-chevron-right"></i> 借阅历史</a></li>
	</ul><br><br>
	<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
		<li><a href="/Demo_war_exploded/recommend?method=list2"><i
				class="glyphicon glyphicon-chevron-right"></i> 热门推荐</a></li>
		<li><a href="/Demo_war_exploded/bestreader?method=list2"><i
				class="glyphicon glyphicon-chevron-right"></i> 最佳读者</a></li>
	</ul><br><br>
	<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
		<li><a href="/Demo_war_exploded/feedback?method=myplist"><i
				class="glyphicon glyphicon-chevron-right"></i> 问题反馈</a></li>
	</ul>
</div>
