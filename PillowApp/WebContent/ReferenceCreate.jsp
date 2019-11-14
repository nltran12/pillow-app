<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add a reference</title>
	</head>
	<body>
		<h1>Add a reference</h1>
		<form action="referencecreate" method="post">
			<p>
				<label for="username">User</label>
				<input id="username" name="username" value="">
			</p>
			<p>
				<label for="phone">Reference's phone number</label>
				<input id="phone" name="phone" value="">
			</p>
			<p>
				<label for="name">Reference's name</label>
				<input id="name" name=name value="">
			</p>
			<p>
				<input type="submit">
			</p>
		</form>
		<br/><br/>
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</body>
</html>