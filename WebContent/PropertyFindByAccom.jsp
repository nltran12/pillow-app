<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Find Properties</title>
</head>
<body>
	<form action="findpropertiesbyaccom" method="post">
		<h1>Search for Properties by Accommodation Size</h1>
		<p>
			<label for="accom">Accommodates</label>
			<input id="accom" name="accom" value="${fn:escapeXml(param.accom)}">
		</p>
		<p>
			<input type="submit">
			<br /> <br /> <br />
			<span id="message"><b>${messages.result}</b></span>
		</p>
	</form>
	<br />
	<h1>Matching Properties</h1>
	<table border="1">
		<tr>
			<th>PropertyId</th>
			<th>Title</th>
		</tr>
		<c:forEach items="${properties}" var="property">
			<tr>
				<td><a href="findproperty?propertyid=<c:out value="${property.getPropertyId()}"/>"><c:out value="${property.getPropertyId()}" /></a></td>
				<td><c:out value="${property.getTitle()}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
