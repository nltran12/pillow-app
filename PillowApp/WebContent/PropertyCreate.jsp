<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add a property</title>
	</head>
	<body>
		<h1>Add Property</h1>
		<form action="propertycreate" method="post">
			<p>
				<label for="username">Landlord's Username</label>
				<input id="username" name="username" value="">
			</p>
			<p>
				<label for="title">Title</label>
				<input id="title" name="title" value="">
			</p>
			<p>
				<label for="description">Description</label>
				<textarea id="description" name="description"></textarea>
			</p>
			<p>
				<label for="transit">Close to transit?</label>
				<select id="transit" name="transit">
				  <option value="true">Yes</option>
				  <option value="false">No</option>
				</select>
			</p>
			<p>
				<label for="picture">Picture</label>
				<input id="picture" name="picture" value="">
			</p>
			<p>
				<label for="street">Street</label>
				<input id="street" name="street" value="">
			</p>
			<p>
				<label for="neighborhood">Neighborhood</label>
				<input id="neighborhood" name="neighborhood" value="">
			</p>
			<p>
				<label for="city">City</label>
				<input id="city" name="city" value="">
			</p>
			<p>
				<label for="state">State</label>
				<input id="state" name="state" value="">
			</p>
			<p>
				<label for="zip">Zip</label>
				<input id="zip" name="zip" value="">
			</p>
			<p>
				<label for="latitude">Latitude</label>
				<input id="latitude" name="latitude" value="">
			</p>
			<p>
				<label for="longitude">Longitude</label>
				<input id="longitude" name="longitude" value="">
			</p>
			<p>
				<label for="propertytype">Property type</label>
				<input id="propertytype" name="propertytype" value="">
			</p>
			<p>
				<label for="roomtype">Room type</label>
				<input id="roomtype" name="roomtype" value="">
			</p>
			<p>
				<label for="accommodates">Maximum number of guests</label>
				<input id="accommodates" name="accommodates" value="">
			</p>
			<p>
				<label for="bathrooms">Bathrooms</label>
				<input id="bathrooms" name="bathrooms" value="">
			</p>
			<p>
				<label for="bedrooms">Bedrooms</label>
				<input id="bedrooms" name="bedrooms" value="">
			</p>
			<p>
				<label for="monthlyprice">Monthly price</label>
				<input id="monthlyprice" name="monthlyprice" value="">
			</p>
			<p>
				<label for="securitydeposit">Security deposit</label>
				<input id="securitydeposit" name="securitydeposit" value="">
			</p>
			<p>
				<label for="available">Available?</label>
				<select id="available" name="available">
				  <option value="true">Yes</option>
				  <option value="false">No</option>
				</select>
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