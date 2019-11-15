<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Properties</title>
</head>
<body>
	<form action="findpropertiesmulti" method="post">
		<h1>Search for Properties</h1>
		<p>
			<label for="neighborhood">Neighborhood</label>
			<input id="neighborhood" name="neighborhood" value="${fn:escapeXml(param.neighborhood)}">
		</p>
		<p>
			<label for="rating">Rating</label>
			<select id="rating" name="rating" value="${fn:escapeXml(param.rating)}">
				<option value="1.0">1.0</option>
			    <option value="2.0">2.0</option>
			    <option value="3.0">3.0</option>
			    <option value="4.0">4.0</option>
			    <option value="5.0">5.0</option>			    
			</select>
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
