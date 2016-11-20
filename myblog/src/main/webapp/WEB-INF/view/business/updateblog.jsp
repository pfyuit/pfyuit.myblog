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
<title>更新博客</title>
</head>
<body style="zoom:0.9;-moz-transform:scale(0.9);-moz-transform-origin:top left;">

	<c:if test="${not empty messages}">
		<blockquote>
			<font size="3px" class="text-danger"><span class="glyphicon glyphicon-ban-circle">&nbsp;</span>Form validation error:</font><br />
			<c:forEach var="message" items="${messages}">
				<font size="3px" class="text-danger">${message}</font>
				<br />
			</c:forEach>
		</blockquote>
	</c:if>

	<form method="post" action="${contextPath}/blog/update.html">
		<input type="hidden" name="blogid" value="${blog.blogid}"></input>

		<div style="background-color: aliceblue; height: 30px; font-weight: bold">
			博文名称<font color="red">*</font>
		</div>
		<input type="text" name="title" class="form-control" value="${blog.title}"></input>
		<p />

		<div style="background-color: aliceblue; height: 30px; font-weight: bold">
			博文作者<font color="red">*</font>
		</div>
		<input type="text" name="author" class="form-control" value="${blog.author}"></input>
		<p />

		<div style="background-color: aliceblue; height: 30px; font-weight: bold">
			是否原创<font color="red">*</font>
		</div>
		<select name="isOriginal" class="form-control">
			<option value="true" <c:if test="${blog.isOriginal==true}">selected</c:if>>是</option>
			<option value="false" <c:if test="${blog.isOriginal==false}">selected</c:if>>否</option>
		</select>
		<p />

		<div style="background-color: aliceblue; height: 30px; font-weight: bold">
			类别<font color="red">*</font>
		</div>
		<c:set var="current" value="${blog.category.name}"></c:set>
		<select name="categoryName" class="form-control">
			<c:forEach var="category" items="${categories}">
				<option value="${category.name}" <c:if test="${category.name==current}">selected</c:if>>${category.name}</option>
			</c:forEach>
		</select>
		<p />

		<div style="background-color: aliceblue; height: 30px; font-weight: bold">博文内容</div>
		<textarea name="content" rows="3">${blog.content}</textarea>
		<script type="text/javascript">
			CKEDITOR.replace('content');
		</script>
		<p />

		<input type="submit" class="btn btn-success" value="更新"></input>
	</form>

</body>
</html>