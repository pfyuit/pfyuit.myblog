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
</head>
<body>

   <div class="list-group">
      <a href="#" class="list-group-item list-group-item-info"><span class="glyphicon glyphicon-tags"></span>&nbsp;文章分类</a>
      <c:forEach var="category" items="${categories}">
         <a href="#" class="list-group-item">${category.name} (23)</a>
      </c:forEach>
   </div>

   <div class="list-group">
      <a href="#" class="list-group-item list-group-item-info"><span class="glyphicon glyphicon-time"></span>&nbsp;文章归档</a>
      <c:forEach var="archive" items="${archives}">
         <a href="#" class="list-group-item">${archive} (23)</a>
      </c:forEach>
   </div>

   <div class="list-group">
      <a href="#" class="list-group-item list-group-item-info"><span class="glyphicon glyphicon-link"></span>&nbsp;项目链接</a>
      <c:forEach var="link" items="${links}">
         <a href="${link.url}" class="list-group-item">${link.name}</a>
      </c:forEach>
   </div>

</body>
</html>