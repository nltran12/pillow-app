<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
	</head>
	<body>
		<div class="container">
			<h1>Login</h1>
			<form action="login" method="post">
				<p>
					<label for="username">UserName</label>
					<input id="username" name="username">
				</p>
				<p>
					<label for="password">Password</label>
					<input type="password" id="password" name="password">
				</p>
			
				<div style="color: red">
					<span id="successMessage"><b>${messages.success}</b></span>
				</div>
				<button type=submit class="btn btn-primary">Submit</button>
			</form>
			<span>
				
				<a href="/PillowApp/tenantcreate" class="btn btn-primary" style="margin: 10px 0px">Register</a>
			</span>
		</div>
	</body>
</html>