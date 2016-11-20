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
</head>
<body style="zoom:1.5;-moz-transform:scale(1.5);-moz-transform-origin:top left;">

	<div class="list-group">
		<a href="#" class="list-group-item list-group-item-info"><span class="glyphicon glyphicon-tags"></span>&nbsp;文章分类</a>
		<c:forEach var="category" items="${categories}">
			<a href="${contextPath}/content.html?categoryid=${category.categoryid}" class="list-group-item">${category.name} (${category.blogCount})</a>
		</c:forEach>
	</div>
	
	<form method="post" action="${contextPath}/search.html"  enctype="application/x-www-form-urlencoded; charset=UTF-8">
		<input type="text" name="key" class="form-control"></input>
		<p />
		<input type="submit" class="btn btn-success" value="搜索博客"></input>
	</form>

<!-- 	<div class="list-group"> -->
<!-- 		<a href="#" class="list-group-item list-group-item-info"><span class="glyphicon glyphicon-time"></span>&nbsp;文章归档</a> -->
<%-- 		<c:forEach var="archive" items="${archives}"> --%>
<%-- 			<a href="${contextPath}/content.html?createdate=${archive.month}" class="list-group-item">${archive.month} (${archive.blogCount})</a> --%>
<%-- 		</c:forEach> --%>
<!-- 	</div> -->

<!-- 	<div class="list-group"> -->
<!-- 		<a href="#" class="list-group-item list-group-item-info"><span class="glyphicon glyphicon-book"></span>&nbsp;阅读排行</a> -->
<%-- 		<c:forEach var="read" items="${reads}"> --%>
<%-- 			<a href="${contextPath}/viewblog.html?blogid=${read.blogid}" class="list-group-item">${read.title} (${read.count})</a> --%>
<%-- 		</c:forEach> --%>
<!-- 	</div> -->

<!-- 	<div class="list-group"> -->
<!-- 		<a href="#" class="list-group-item list-group-item-info"><span class="glyphicon glyphicon-eye-open"></span>&nbsp;评论排行</a> -->
<%-- 		<c:forEach var="comment" items="${comments}"> --%>
<%-- 			<a href="${contextPath}/viewblog.html?blogid=${comment.blogid}" class="list-group-item">${comment.title} (${comment.commentCount})</a> --%>
<%-- 		</c:forEach> --%>
<!-- 	</div> -->

<!-- 	<div class="list-group"> -->
<!-- 		<a href="#" class="list-group-item list-group-item-info"><span class="glyphicon glyphicon-link"></span>&nbsp;项目链接</a> -->
<%-- 		<c:forEach var="link" items="${links}"> --%>
<%-- 			<a href="${link.url}" class="list-group-item">${link.name}</a> --%>
<%-- 		</c:forEach> --%>
<!-- 	</div> -->

</body>
</html>