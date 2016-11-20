<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title></title>
</head>
<body style="zoom:0.9;-moz-transform:scale(0.9);-moz-transform-origin:top left;">
	<c:if test="${not empty message}">
		<blockquote>
			<font size="3px" class="text-success">${message}</font> <br />
		</blockquote>
	</c:if>

	<p>
		<c:forEach var="blog" items="${blogs}">
			<a href="${contextPath}/viewblog.html?blogid=${blog.blogid}"><c:if test="${blog.isOriginal==true}">
					<span class="glyphicon glyphicon-bookmark"></span>&nbsp;</c:if><b>${blog.title}</b></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">分类( ${blog.category.name}) </a>
			<br />
        		${blog.contentAbstract}
        	<br />
        		${blog.createDate}&nbsp;&nbsp;&nbsp;阅读(${blog.readCount})&nbsp;&nbsp;&nbsp;评论(${blog.commentCount})&nbsp;&nbsp;&nbsp;
        	<shiro:user>
				<a href="${contextPath}/updateblog.html?blogid=${blog.blogid}">编辑</a>
			</shiro:user>
			<hr />
		</c:forEach>
	</p>
</body>
</html>