<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Tenants</title>
</head>
<body>
	<form action="findtenantsby" method="post">
		<h2>Search for Tenants by Credit Score, Background Check and Income</h2>
		<p>
			<label for="creditscore">Credit Score</label>
			<select id="creditscore" name="creditscore">
				<option value="400"> &gt; 400</option>
				<option value="500"> &gt; 500 </option>
				<option value="600"> &gt; 600</option>
				<option value="700"> &gt; 700 </option>
				<option value="0"> All </option>
			</select>
		</p>
		<p>
			<label for="bgcheck">Background Check</label>
			<select id="bgcheck" name="bgcheck">
			  <option value="true">Yes</option>
			  <option value="false">No</option>
			</select>
		</p>
		<p>
			<label for="income">Income</label>
			<input id="income" name="income" value="0">
		</p>
		<p>
			<input type="submit">
			<br /> <br /> <br />
			<span id="message"><b>${messages.result}</b></span>
		</p>
	</form>
	<br />
	<h1>Matching Tenants</h1>
	<table border="1">
		<tr>
			<th>User Name</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Phone</th>
			<th>Email</th>
			<th>Credit Score</th>
			<th>Background Check</th>
			<th>Income</th>
		</tr>
		<c:forEach items="${tenants}" var="tenant">
			<tr>
				<td><c:out value="${tenant.getUserName()}" /></td>
				<td><c:out value="${tenant.getFirstName()}" /></td>
				<td><c:out value="${tenant.getLastName()}" /></td>
				<td><c:out value="${tenant.getPhone()}" /></td>
				<td><c:out value="${tenant.getEmail()}" /></td>
				<td><c:out value="${tenant.getCreditScore()}" /></td>
				<td><c:out value="${tenant.isBackgroundCheck()}" /></td>
				<td><c:out value="${tenant.getIncome()}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
