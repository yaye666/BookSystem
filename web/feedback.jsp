<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html class="ax-vertical-centered">
<head>
	<title>图书馆管理系统</title>
	<jsp:include page="common/css.jsp"></jsp:include>
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>

	<style>
		body {
			/*background-image: url("04.jpg");*/
		}
	</style>
</head>

<body class="bootstrap-admin-with-small-navbar">
	<jsp:include page="common/top.jsp"></jsp:include>

	<div class="container">
		<!-- left, vertical navbar & content -->
		<div class="row">
			<jsp:include page="common/user_left.jsp"></jsp:include>
			<!-- content -->
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">问题反馈</div>
							</div>

							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form class="form-horizontal"  method="post"   action="/Demo_war_exploded/problem?method=problem"
									>
									<div class="col-lg-8 form-group">
										<label class="col-lg-4" for="query_bname">标题</label>
										<div class="col-lg-12">
											<input class="form-control" id="addtitle" name="title"
												type="text" value="" required="required" placeholder="请勿超过30个字符">
											<label for="query_bname" style="display: none;"></label>
										</div>
									</div>
									<div class="col-lg-8 form-group">
										<label class="col-lg-4" for="query_bname">问题页面</label>
										<div class="col-lg-12">
											<input class="form-control" id="addpage" name="page"
												type="text" value="" required="required" placeholder="问题界面">
											<label for="query_bname" style="display: none;"></label>
										</div>
									</div>
									<div class="col-lg-8 form-group">
										<label class="col-lg-4" for="query_bname">问题描述</label>
										<div class="col-lg-12">
											<textarea name="content" required="required" id="addcontent" class="form-control" placeholder="请勿超过255个字符"
												style="height: 150px;resize: none;"></textarea>
											<label for="query_bname" style="display: none;"></label>
										</div>
									</div>
									<div class="col-lg-8 form-group">
										<label class="col-lg-4" for="query_bname">联系方式</label>
										<div class="col-lg-12">
											<input class="form-control" id="addlink" name="link"
												type="text" value="" required="required" placeholder="电话/邮箱">
											<label for="query_bname" style="display: none;"></label>
										</div>
									</div>
									<div class="col-lg-8 form-group">
										<label class="control-label" for="query_bname"></label>
										<button type="submit" class="btn btn-primary" id="btn_query">提 交</button>

									</div>
								</form>
                                <script type="text/javascript">
                                    function PosProblem(){
                                        confirm("是否提交？");
                                        alert(1)
                                        $.post("problem_add",function (data){
                                            if(data=="ok"){
                                                alert(1);
                                            }
                                        })

                                    }
                                    function test1(){
                                        confirm("11");
                                        $.post("book_add",function (){
                                            alert(1);
                                        })
                                    }


                                </script>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

	</div>

	<!-------------------------------------------------------------->

	<jsp:include page="common/userInfo.jsp"></jsp:include>
	<jsp:include page="common/js.jsp"></jsp:include>
</body>

</html>