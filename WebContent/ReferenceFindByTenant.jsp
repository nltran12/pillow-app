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
	<div style="background-color: darkcyan; color: white; padding-top: 3px;
				padding-bottom: 3px;" align="center">
		<h1><i class="fas fa-couch"></i> Pillow</h1>
	</div>
	<%--Logo end--%>
	<div class="container">
		<%--Header--%>
		<div class="row">
			<div class="col-md-12 col-lg-12" align="right">
				<% Users user = (Users) session.getAttribute("currentUser"); %>
				<p>Welcome <%= user.getFirstName() + " " + user.getLastName() %>
					<a class="btn-outline-secondary" href="TenantAccountSettings.jsp" role="button">
						<i class="fas fa-cog"></i></a></p>
			</div>
		</div>
		<%--Header end--%>
		<h1>Displaying References</h1>
		<table border="1">
			<tr>
				<th>Reference Name</th>
				<th>Phone number</th>
			</tr>
			<c:forEach items="${references}" var="reference">
				<tr>
					<td><c:out value="${reference.getName()}" /></td>
					<td><c:out value="${reference.getPhone()}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
		<form action="findreferencebytenant" method="post">
		<h2>Search for references</h2>
		<p>
			<label for="tenant">Tenant</label>
			<input id="tenant" name="tenant" value="">
		</p>
	</form>
	<br />

</body>
</html>
