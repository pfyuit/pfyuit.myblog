<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.5, maximum-scale=0.5, minimum-scale=0.5, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<title><sitemesh:write property='title' /></title>

<!-- Bootstrap core CSS -->
<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- //////Special configuration for IE browser compatibility////// -->

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="${contextPath}/static/ieonly/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${contextPath}/static/ieonly/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="${contextPath}/static/ieonly/html5shiv.min.js"></script>
      <script src="${contextPath}/static/ieonly/respond.min.js"></script>
    <![endif]-->

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="${contextPath}/static/ieonly/ie10-viewport-bug-workaround.js"></script>

<!-- //////End Special configuration for IE browser compatibility////// -->

<!-- Bootstrap core JavaScript -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${contextPath}/static/jquery/jquery.min.js"></script>
<script src="${contextPath}/static/bootstrap/js/bootstrap.min.js"></script>

<!-- CKEditor JavaScript -->
<script type="text/javascript" src="static/ckeditor/ckeditor.js"></script>

</head>

<body>
	<div class="navbar navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">我的博客</a>
			</div>
			<div class="collapse navbar-collapse">
				<c:set var="home" value="Home"></c:set>
				<c:set var="about" value="About"></c:set>
				<c:set var="newblog" value="NewBlog"></c:set>
				<c:set var="admin" value="Admin"></c:set>

				<ul class="nav navbar-nav">
					<li <c:if test="${home==menu}">class="active"</c:if>><a href="${contextPath}/index.html"><span class="glyphicon glyphicon-home"></span> 博客主页</a></li>
					<li <c:if test="${about==menu}">class="active"</c:if>><a href="${contextPath}/about.html"><span class="glyphicon glyphicon-user"></span>&nbsp;关于我</a></li>
					<shiro:user>
						<li <c:if test="${newblog==menu}">class="active"</c:if>><a href="${contextPath}/newblog.html"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;新建博客</a></li>
					</shiro:user>
					<shiro:user>
						<li <c:if test="${admin==menu}">class="active"</c:if>><a href="${contextPath}/admin.html"><span class="glyphicon glyphicon-lock"></span>&nbsp;博客管理</a></li>
					</shiro:user>
				</ul>

				<shiro:guest>
					<form class="navbar-form navbar-right" role="form" action="${contextPath}/login.html" method="post">
						<div class="form-group">
							<input name="username" type="text" placeholder="Username" class="form-control">
						</div>
						<div class="form-group">
							<input name="password" type="password" placeholder="Password" class="form-control">
						</div>
						<button type="submit" class="btn btn-success">
							<span class="glyphicon glyphicon-log-in"></span>&nbsp;登录
						</button>
					</form>
				</shiro:guest>

				<form class="navbar-form navbar-right">
					<div class="form-group" style="margin-top: 6px">
						<shiro:user>
							<li><font color="#777"><span class="glyphicon glyphicon-log-out"></span>&nbsp;Welcome [<shiro:principal />], </font> <a href="${contextPath}/logout.html">退出</a></li>
						</shiro:user>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-6 col-md-3 sidebar-offcanvas" id="sidebar" role="navigation">
				<jsp:include page="/navigator.html"></jsp:include>
			</div>
			<div class="col-xs-12 col-md-9">
				<div class="panel panel-info">
					<div class="panel-heading">
						<sitemesh:write property='title' />
					</div>
					<div class="panel-body">
						<sitemesh:write property='body' />
					</div>
				</div>
			</div>
		</div>

<!-- 		<hr> -->
<!-- 		<footer> -->
<!-- 			<p> -->
<!-- 				Powered by Myblog by <a href="mailto:pfyuit@gmail.com">pfyuit@gmail.com</a>. All rights reserved. -->
<!-- 			</p> -->
<!-- 		</footer> -->
	</div>

</body>
</html>