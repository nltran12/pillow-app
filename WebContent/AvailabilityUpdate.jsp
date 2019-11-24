<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update availability</title>
</head>
<body>
	<h1>Update availability</h1>
	<form action="availabilityupdate" method="post">
		<p>
			<label for="propertyid">PropertyId</label>
			<input id="propertyid" name="propertyid" value="${fn:escapeXml(param.propertyid)}">
		</p>
		<p>
			<label for="availability">New Availability Status</label>
			<select id="availability" name="availability">
			  <option value="true">Yes</option>
			  <option value="false">No</option>
			</select>
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