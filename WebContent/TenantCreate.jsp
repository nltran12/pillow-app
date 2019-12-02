<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/c37af9ab60.js" crossorigin="anonymous"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Create a tenant account</title>
	</head>
	<body style="background: #ededed;">
		<%--Logo--%>
		<div
			style="background-color: darkcyan; color: white; padding-top: 3px; padding-bottom: 3px;"
			align="center">
			<h1><i class="fas fa-couch"></i> Pillow</h1>
		</div>
		<%--Logo end--%>
		
		<div class="container d-flex flex-column align-items-center justify-content-center mt-4">
			<div class="row">
				<div class="col" style="margin-bottom: 10px;">
					<h2>Create an account</h2>
				</div>
			</div>
			
			<%--Create form--%>
			<div class="row">
				<div class="col">
					<form action="tenantcreate" method="post">
						<div class="form-group">
							<div>
								<label style="font-weight: bold; width: 140px;" for="username">Username</label>
								<input id="username" name="username" class="form-control-sm">
							</div>
						</div>
						<div class="form-group">
							<div>
								<label style="font-weight: bold; width: 140px;" for="password">Password</label>
								<input type="password" id="password" name="password" class="form-control-sm">
							</div>
						</div>
						<div class="form-group">
							<div>
								<label style="font-weight: bold; width: 140px;" for="firstname">First Name</label>
								<input  id="firstname" name="firstname" class="form-control-sm">
							</div>
						</div>
						<div class="form-group">
							<div>
								<label style="font-weight: bold; width: 140px;" for="lastname">Last Name</label>
								<input id="lastname" name="lastname" class="form-control-sm">
							</div>
						</div>
						<div class="form-group">
							<div>
								<label style="font-weight: bold; width: 140px;" for="dob">Date of Birth</label>
								<input class="form-control-sm" type="date" value="2000-01-01" id="dob" name="dob">
							</div>
						</div>
						<div class="form-group">
							<div>
								<label style="font-weight: bold; width: 140px;" for="email">Email</label>
								<input id="email" name="email" class="form-control-sm">
							</div>
						</div>
						<div class="form-group">
							<div>
								<label style="font-weight: bold; width: 140px;" for="phone">Phone</label>
								<input id="phone" name="phone" class="form-control-sm">
							</div>
						</div>
						<div class="form-group">
							<div>
								<label style="font-weight: bold; width: 140px;" for="income">Income</label>
								<input id="income" name="income" class="form-control-sm">
							</div>
						</div>
						<div style="color: red">
							<span id="successMessage"><b>${messages.success}</b></span>
						</div>
						<button type=submit class="btn btn-secondary btn-block" style="font-size: large;">Create</button>
					</form>		
				</div>
			</div>
			<%--Create form end--%>
		</div>
	</body>
</html>