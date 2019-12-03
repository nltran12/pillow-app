<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java"
         contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"
         import="pillow.model.Users"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/c37af9ab60.js" crossorigin="anonymous"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update Background Check</title>
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
    <form action="updatebackgroundcheck" method="post">
        <div class="form-group row">
            <label for="creditscore" class="col-sm-2 col-form-label">Credit Score</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="creditscore" name="creditscore"
                       placeholder="credit score" value=${phone}>
            </div>
        </div>
        <div class="form-group row">
            <label for="socialsecurity" class="col-sm-2 col-form-label">Social Security
                Number</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="socialsecurity" name="socialsecurity"
                       placeholder="social security" value=${phone}>
            </div>
        </div>
        <input type="hidden" name="username" value="<%=user.getUserName()%>">
        <button type="submit" class="btn btn-outline-secondary">Submit</button>
    </form>
</div>
</body>
</html>
