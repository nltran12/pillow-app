<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
    <script src="https://kit.fontawesome.com/c37af9ab60.js" crossorigin="anonymous"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Reservation Confirmation</title>
</head>
<body>
	<%--Logo--%>
	<div style="background-color: darkcyan; color: white; padding-top: 3px;
			padding-bottom: 3px;" align="center">
		<h1><i class="fas fa-couch"></i> Pillow</h1>
	</div>
	<%--Logo end--%>
	<div class="jumbotron jumbotron-fluid" style="min-height: 100vh;">
		<div class="container text-center" style="margin: auto;">
			<h1 class="display-4">Reservation Details</h1>
	  		<hr class="my-4">
	  		<table class="table" style="width: 75%; margin: auto;">
			  <tbody>
			    <tr style="text-align: left;">
			      <th>Check in date</th>
			      <td><fmt:formatDate value="${reservation.getStartDate()}" pattern="yyyy-MM-dd"/></td>

			    </tr>
			    <tr class="text-left">
			      <th >Check out date</th>
			      <td><fmt:formatDate value="${reservation.getEndDate()}" pattern="yyyy-MM-dd" /></td>
			    </tr>
			    <tr class="text-left">
			      <th >Number of guests</th>
			      <td>${reservation.getNumOccupants()}</td>
			    </tr>
			     <tr class="text-left">
			      <th >Sub-total</th>
			      <td><fmt:formatNumber type="currency" value="${monthly}"/></td>
			    </tr>
			     <tr class="text-left">
			      <th>Security deposit</th>
			      <td><fmt:formatNumber type="currency" value="${reservation.getProperty().getSecurityDeposit()}"/></td>
			    </tr>
			     <tr class="text-left">
			      <th>Total</th>
			      <td><fmt:formatNumber type="currency" value="${total}"/></td>
			    </tr>
			  </tbody>
			</table>
	  		<form action="reservationconfirm" method="post" class="d-flex justify-content-center">
			    <p>
			        <input type="hidden" id="tenantUsername" name="tenantUsername" value="${reservation.getUser().getUserName()}">
			        <input type="hidden" id="propertyId" name="propertyId" value="${reservation.getProperty().getPropertyId()}">
			        <input type="hidden" id="startDate" name="startDate" value=<fmt:formatDate value="${reservation.getStartDate()}" pattern="yyyy-MM-dd" />>
			        <input type="hidden" id="endDate" name="endDate" value=<fmt:formatDate value="${reservation.getEndDate()}" pattern="yyyy-MM-dd" />>
			        <input type="hidden" id="numOccupants" name="numOccupants" value="${reservation.getNumOccupants()}">			        
			    </p>
			    <p><button type="submit" class="btn btn-secondary btn-block" style="margin: 20px 0px 0px;">Reserve</button></p>
			</form>
	  		
		</div>
	</div>

</body>
</html>