<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Create a Reservation</title>
</head>
<body>
<h1>Create Reservation</h1>
<form action="reservationcreate" method="post">
    <p>
        <label for="tenantUsername">Tenant Username</label>
        <input id="tenantUsername" name="tenantUsername" value="">
    </p>
    <p>
        <label for="propertyId">Property Id</label>
        <input id="propertyId" name="propertyId" value="">
    </p>
    <p>
        <label for="startDate">Start date (yyyy-mm-dd)</label>
        <input id="startDate" name="startDate" value="">
    </p>
    <p>
        <label for="endDate">End date (yyyy-mm-dd)</label>
        <input id="endDate" name="endDate" value="">
    </p>
    <p>
        <label for="numOccupants">Num Occupants</label>
        <input id="numOccupants" name="numOccupants" value="">
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