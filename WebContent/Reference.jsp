<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	 import="pillow.model.Users"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://kit.fontawesome.com/c37af9ab60.js" crossorigin="anonymous"></script>
	<title>References</title>
</head>
<body>
	<%--Logo--%>
	<% Users user = (Users) session.getAttribute("currentUser"); %>
	<div style="background-color: darkcyan; color: white; padding-top: 3px;
					padding-bottom: 3px;" align="center">
		<h1><a href="./propertysearch" role="home-button"
			   style="color: white; text-decoration: none;"><i
				class="fas fa-couch"></i> Pillow</a></h1>
	</div>
	<%--Logo end--%>
	<div class="container">
		<%--Header--%>
		<div class="row" style="padding-top: 6px;">
			<div class="col-md-12 col-lg-12" align="right">
				<p>Welcome <%= user.getFirstName() + " " + user.getLastName() %>
					<a class="btn-outline-secondary" href="TenantAccountSettings.jsp" role="button">
						<i class="fas fa-cog"></i></a></p>
			</div>
		</div>
		<%--Header end--%>
		<h2>Current References</h2>
		<table class="table table-bordered">
			<tr>
				<th scope="col">Reference Name</th>
				<th scope="col">Phone number</th>
			</tr>
			<c:forEach items="${references}" var="reference">
				<tr>
					<td><c:out value="${reference.getName()}" /></td>
					<td><c:out value="${reference.getPhone()}" /></td>
				</tr>
			</c:forEach>
		</table>
		<br/>

		<%--ADD REFERENCE FORM--%>
		<form action="reference" method="post">
			<h2>Add a Reference</h2>
			<div class="form-group row">
				<label for="name" class="col-sm-2 col-form-label">Reference Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name"
						   placeholder="name" value=${name}>
				</div>
			</div>
			<div class="form-group row">
				<label for="phone" class="col-sm-2 col-form-label">Reference Phone</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="phone" name="phone"
						   placeholder="phone number" value=${phone}>
				</div>
			</div>
			<input type="hidden" name="username" value="<%=user.getUserName()%>">
			<button type="submit" class="btn btn-outline-secondary">Submit</button>
		</form>
		<%--ADD REFERENCE FORM END--%>
	</div>
</body>
</html>
