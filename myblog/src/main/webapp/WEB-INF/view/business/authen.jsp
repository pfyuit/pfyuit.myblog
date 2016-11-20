<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.9">
<meta name="description" content="">
<meta name="author" content="">
<title>Sign In</title>
</head>
<body style="background-color: #6f5499;">
	<div class="container" style="margin-left: 300px; margin-top: 200px;">
		<c:if test="${not empty messages}">
			<div style="margin-left: 100px; margin-bottom: 10px; max-width: 500px">
				<c:forEach var="message" items="${messages}">
					<font size="3px" class="text-danger">${message}</font>
					<br />
				</c:forEach>
			</div>
		</c:if>

		<form class="form-horizontal" role="form" action="${contextPath}/login.html" method="post">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Username</label>
				<div class="col-sm-5">
					<input name="username" type="text" class="form-control" id="inputEmail3" placeholder="Username">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-5">
					<input name="password" type="password" class="form-control" id="inputPassword3" placeholder="Password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-12">
					<button type="submit" class="btn btn-success">
						<span class="glyphicon glyphicon-log-in"></span> &nbsp;Login
					</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>