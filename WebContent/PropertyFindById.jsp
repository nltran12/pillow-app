<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Property</title>
</head>
<body>
	<form action="findproperty" method="post">
		<h1>Search for a Property by PropertyId</h1>
		<p>
			<label for="propertyid">PropertyId</label>
			<input id="propertyid" name="propertyid" value="${fn:escapeXml(param.propertyid)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="resultMessage"><b>${messages.result}</b></span>
		</p>
	</form>
	<br/>
	<h1>Property Info</h1>
	<ul>
		<li><b>PropertyId</b>: <c:out value="${property.getPropertyId()}" /></li>
		<li><b>Title</b>: <c:out value="${property.getTitle()}" /></li>
		<li><b>UserName</b>: <c:out value="${property.getUser().getUserName()}" /></li>
		<li><b>Description</b>: <c:out value="${property.getDescription()}" /></li>
		<li><b>Transit</b>: <c:out value="${property.isTransit()}" /></li>
<!-- 		<li><b>Picture</b>:</li> -->
		<li><b>Street</b>: <c:out value="${property.getStreet()}" /></li>
		<li><b>Neighborhood</b>: <c:out value="${property.getNeighborhood()}" /></li>
		<li><b>City</b>: <c:out value="${property.getCity()}" /></li>
		<li><b>State</b>: <c:out value="${property.getState()}" /></li>
		<li><b>Zip</b>: <c:out value="${property.getZip()}" /></li>
<!-- 		<li><b>Latitude</b>:</li>
		<li><b>Longitude</b>:</li> -->
		<li><b>PropertyType</b>: <c:out value="${property.getPropertyType()}" /></li>
		<li><b>RoomType</b>: <c:out value="${property.getRoomType()}" /></li>
		<li><b>Accommodates</b>: <c:out value="${property.getAccomodates()}" /></li>
		<li><b>Bathrooms</b>: <c:out value="${property.getBathrooms()}" /></li>
		<li><b>Bedrooms</b>: <c:out value="${property.getBedrooms()}" /></li>
		<li><b>MonthlyPrice</b>: <c:out value="${property.getMonthlyPrice()}" /></li>
		<li><b>SecurityDeposit</b>: <c:out value="${property.getSecurityDeposit()}" /></li>
		<li><b>Available</b>: <c:out value="${property.isAvailable()}" /></li>
	</ul>
</body>
</html>
