<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="pillow.model.Users"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://kit.fontawesome.com/c37af9ab60.js" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Delete User</title>
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
		<div class="row">
			<div class="col-lg-3">
			</div>
			<div class="col-lg-6" align="center">
				<form action="tenantdelete" method="post">
					<input type="hidden" id="tenantUserName" name="tenantUserName" value="<%=user.getUserName()%>">
					<span <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
						<h4>Are you sure you want to delete your account?</h4>
					</span>
					<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
						<input type="submit" value="confirm"  class="btn btn-outline-alert">
						<a href="./propertysearch" class="btn btn-outline-secondary">cancel</a>
					</span>
					<c:if test="${messages.disableSubmit}">
						<h4>${messages.title}</h4>
					</c:if>
				</form>
				<br/><br/>
			</div>
		</div>
	</div>
	
</body>
</html>