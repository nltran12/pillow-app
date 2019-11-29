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
		<title>Login</title>
	</head>
	<body>
		<div class="jumbotron" style="min-height: 100vh; background: darkcyan;">
			<div class="container">
				<div class="row" style="display: flex; align-items: center; justify-content: center; height: 80vh;">
					<div class="col-6 flex-column" 
						style="display: flex; align-items: center; justify-content: center; color: white;">
						<h1><i class="fas fa-couch" style="font-size: 100px; color: white;"></i></h1>
						<h1 style="font-size: 100px;">Pillow</h1>
					</div>
					
					<div class="col-6 flex-column" style="display: flex; align-items: center; justify-content: center;">
						<form action="login" method="post">
							<div class="form-group">
								<div>
									<label style="color: white; font-weight: bold;" for="username">Username</label>
									<input id="username" name="username" class="form-control-lg">
								</div>
							</div>
							
							
							<div class="form-group">
								<div>
									<label style="color: white; font-weight: bold;" for="password">Password</label>
									<input type="password" id="password" name="password" class="form-control-lg">
								</div>
							</div>
							<div style="color: red">
								<span id="successMessage"><b>${messages.success}</b></span>
							</div>
							<button type=submit class="btn btn-secondary btn-block" style="font-weight: bold; font-size: large;">Login</button>
						</form>
						<div class="d-flex">		
							<a href="./tenantcreate" class="btn btn-secondary" style="margin: 10px 5px 10px 0px">Register as tenant</a>
							<a href="./landlordcreate" class="btn btn-secondary" style="margin: 10px 0px 10px 5px">Register as landlord</a>		
						</div>
							
					</div>	
				</div>
			</div>
		</div>
	</body>
</html>