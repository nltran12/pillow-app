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
        <title>Account Settings</title>
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
        <div class="row">
            <div class="col-lg-4"></div>
            <div class="col-lg-4">
                <h2 align="left">Account Settings</h2>
                <div align="center" style="border:1px solid black;border-radius: 10px">
                    <%
                        String url = "./tenantaccountinfo?username=" + user.getUserName();
                    %>
                    <a href="<%=url%>" class="btn btn-outline-secondary"
                       style="margin: 10px 0px">View Rentals</a>
                    <br/>
                    <a href="#" class="btn btn-outline-secondary" style="margin: 10px 0px">View/Add
                        Background Check</a>
                    <br/>
                    <a href="./referencecreate" class="btn btn-outline-secondary" style="margin: 10px 0px">View/Add
                        References</a>
                    <br/>
                    <a href="./tenantdelete" class="btn btn-outline-danger" style="margin: 10px 0px">Delete Account</a>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>