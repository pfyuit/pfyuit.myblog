<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title></title>
</head>
<body>

	<p>
		<b>${blog.title}</b> <br /> ${blog.createDate}&nbsp;&nbsp;&nbsp;阅读(${blog.readCount})&nbsp;&nbsp;&nbsp;评论(${blog.commentCount})&nbsp;&nbsp;&nbsp;
		<shiro:user>
			<a href="${contextPath}/updateblog.html?blogid=${blog.blogid}">编辑</a>
		</shiro:user>
		<br /> ${blog.content} <br />
	</p>

	<hr />

	<c:forEach var="comment" items="${blog.comments}">
		<div style="background-color: aliceblue; height: 30px; font-weight: bold">${comment.author}&nbsp;&nbsp;发表于${comment.createTime}</div>
		<p />
		<div>${comment.content}</div>
		<p />
	</c:forEach>

	<hr />
	<form method="post" action="${contextPath}/comment/create.html">
		<input type="hidden" name="blogid" value="${blog.blogid}" />

		<div style="background-color: aliceblue; height: 30px; font-weight: bold">
			作者<font color="red">*</font>
		</div>
		<input type="text" name="author" class="form-control"></input>
		<p />

		<div style="background-color: aliceblue; height: 30px; font-weight: bold">评论内容</div>
		<textarea name="content" style="width: 100%; height: 100px;"></textarea>
		<p />
		<p />

		<input type="submit" class="btn btn-success" value="提交评论"></input>
	</form>


</body>
</html>