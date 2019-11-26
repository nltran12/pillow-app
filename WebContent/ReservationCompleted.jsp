<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Reservation Confirmation</title>
</head>
<body>
	<div class="jumbotron jumbotron-fluid" style="min-height: 100vh;">
		<div class="container text-center" style="margin: auto;">
			<h1 class="display-4">Reservation Booked!</h1>
	  		<hr class="my-4">
	  		<p class="lead">Check your email ${reservation.getUser().getEmail()} 
	  			for full details!</p>
	  			
	  		<a href="./findpropertiesbyneighborhood"
	  			class="btn btn-secondary">
	  			Back to search
	  		</a>
	  		<a href="./tenantaccountinfo?username=${reservation.getUser().getUserName()}"
	  			class="btn btn-secondary">
	  			To account overview
	  		</a>
		</div> 
	</div>
</body>
</html>