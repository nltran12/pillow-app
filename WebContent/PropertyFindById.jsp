<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
	<script src="https://kit.fontawesome.com/c37af9ab60.js" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Property Info</title>
</head>
<body>
	<%--Logo--%>
	<div style="background-color: darkcyan; color: white; padding-top: 3px;
			padding-bottom: 3px;" align="center">
		<h1><i class="fas fa-couch"></i> Pillow</h1>
	</div>
	<%--Logo end--%>
	<h1 style="text-align: center; padding: 20px 20px 0px;">${property.getTitle()}</h1>
	<h5 style="text-align: center;"><b>Hosted by: </b> ${property.getUser().getFirstName()} ${property.getUser().getLastName()}</h5>
	<div class="container" style="padding: 20px 0px;">
		<div class="row">
			<div class="col-lg-8">
				<img src="${property.getPicture()}" class="img-fluid" style="width: 100%"/>

				<ul class="list-group list-group-horizontal">
					<li class="list-group-item" 
						style="border: none; max-width: 75px; text-align: center; padding-left: 0px;"> 
							${property.getAccomodates()} Guests
					</li>
					<li class="list-group-item" 
						style="border: none; max-width: 130px; text-align: center;">
						${property.getBathrooms()} Bathrooms
					</li>
					<li class="list-group-item" 
						style="border: none; max-width: 120px; text-align: center;">
						${property.getBedrooms()} Bedrooms
					</li>
					<li class="list-group-item" 
						style="border: none; max-width: 120px; text-align: center;">
						${property.getBedrooms()} Bedrooms
					</li>
					<li class="list-group-item" 
						style="border: none; text-align: center; padding: 1.5em 1.25em; max-width: 150px;">
						${property.getPropertyType()} 
					</li>				
				</ul>
		      	
	
				<h3>Description:</h3>
				<p>${property.getDescription()}</p>
		      
			    <div class="row">
			      	<div class="col">
			      		<div><b>Close to transit:</b>  ${property.isTransit() ? 'Yes' : 'No'}</div>
			      		<div><b>Room Type:</b> ${property.getRoomType()}</div>
			      		<div><b>Security Deposit:</b> $<fmt:formatNumber type="number" value="${property.getSecurityDeposit()}"/></div>
		      		</div>
		      		<div class="col">
			      		<div><b>Neighborhood:</b> ${property.getNeighborhood()} </div>
			      		<div><b>City:</b> ${property.getCity()}</div>
			      		<div><b>State:</b> ${property.getState()}</div>
			      	</div>
		    	</div>		
		    </div>
			<div class="col-lg-4">
				<h5 style="margin: 0px;">$<fmt:formatNumber type="number" minFractionDigits="2" value="${property.getMonthlyPrice()}"/></h5>
				<p>per month</p>
				<h3>Reserve a place now!</h3>
				<form class="row form-group" action="reservationcreate" method="post">
					<label class="col-4 col-form-label">Check in:</label>
					<div class="col-8">
				    	<input class="form-control" type="date" 
				    			value="2019-12-01" id="checkIn" name="startDate">
				  	</div>

					<label class="col-4 col-form-label">Check out:</label>
					<div class="col-8">
				    	<input class="form-control" type="date" 
				    			value="2020-01-01" id="checkOut" name="endDate">
				  	</div>
				  	<label class="col-4 col-form-label">Number of Guests:</label>
					<div class="col-8">
				    	<input class="form-control" type="text" 
				    			value="1" id="numOfGuests" name="numOccupants">
				  	</div>
				  	<input type="hidden" name="propertyId" value="${property.getPropertyId()}">
				  	<input class="btn btn-secondary btn-block" type="submit" value="Book"> 
				</form>
				
			</div>
		</div>
	</div>
</body>
</html>
