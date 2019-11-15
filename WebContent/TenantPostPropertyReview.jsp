<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Property Reviews</title>
</head>
<body>
<h1>Create Property Review</h1>
<form action="tenantpostpropertyreview" method="post">
    <p>
        <label for="propertyId">Property Id</label>
        <input id="propertyId" name="propertyId" value="">
    </p>
    <p>
        <label for="tenantUsername">Tenant Username</label>
        <input id="tenantUsername" name="tenantUsername" value="">
    </p>
    <p>
        <label for="rating">Rating (0.0 - 5.0)</label>
        <input id="rating" name="rating" value="">
    </p>
    <p>
        <label for="content">Review Content</label>
        <input id="content" name="content" value="">
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