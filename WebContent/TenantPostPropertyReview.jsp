<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"
         import="pillow.model.Users" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/c37af9ab60.js" crossorigin="anonymous"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Post Property Review</title>
</head>
<body>
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
                <h2>Post a Review</h2>
                <div style="border: 1px solid black; border-radius: 2px; padding-left: 5px;
                padding-top: 10px;">
                    <form action="tenantpostpropertyreview" method="post">
                        <input type="hidden" name="username" value="${messages.username}">
                        <input type="hidden" name="propertyId" value="${messages.propertyId}">
                        <p>
                            <label for="rating">Rating  </label>
                            <select id="rating" name="rating" value="${fn:escapeXml(param.rating)}">
                                <option value="1.0">1.0</option>
                                <option value="2.0">2.0</option>
                                <option value="3.0">3.0</option>
                                <option value="4.0">4.0</option>
                                <option value="5.0">5.0</option>
                            </select>
                        </p>
                        <p>
                            <label for="content">Review Content</label>
                            <br/>
                            <input id="content" name="content" value="${fn:escapeXml(param.content)}">
                        </p>
                        <p>
                            <input type="submit" class="btn btn-outline-secondary">
                        </p>
                    </form>
                    <br/>
                    <p>
                        <span id="successMessage"><b>${messages.success}</b></span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>