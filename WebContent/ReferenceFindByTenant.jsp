<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find References for Tenant</title>
</head>
<body>
	<form action="findreferencebytenant" method="post">
		<h2>Search for references</h2>
		<p>
			<label for="tenant">Tenant</label>
			<input id="tenant" name="tenant" value="">
		</p>
	</form>
	<br />
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
</body>
</html>
