<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="pillow.model.Users" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		crossorigin="anonymous">
	<script src="https://kit.fontawesome.com/c37af9ab60.js"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
		window.onload = function() {
			function setSelectedValue(element, valueToSet) {
			    for (var i = 0; i < element.options.length; i++) {
			        if (element.options[i].text == valueToSet) {
			            element.options[i].selected = true;
			            return;
			        }
			    }
			}
			
			// get, set city
			var cityEl = document.getElementById("city");
			var city = "<%=request.getAttribute("city")%>";
			setSelectedValue(cityEl, city);
			
			// get, set rating
			var ratingEl = document.getElementById("rating");
			var rating = "<%=request.getAttribute("rating")%>";
			setSelectedValue(ratingEl, rating);

			// get, set bedrooms
			var bedroomsEl = document.getElementById("bedrooms");
			var bedrooms = "<%=request.getAttribute("bedrooms")%>";
			setSelectedValue(bedroomsEl, bedrooms);

		}
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Property Search</title>
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
		
			<%-- search form --%>
			<div class="col-4">
				<h1>Search</h1>
				<form action="propertysearch" method="post">
					<div class="form-group">
					  	<label for="city">City</label>
					  	<select class="form-control" id="city" name="city">
					  		<option value="none" selected disabled hidden>-- Select --</option>
							<option>Los Angeles, CA</option>
							<option>Oakland, CA</option>
							<option>San Diego, CA</option>
							<option>San Francisco, CA</option>
							<option>Santa Clara, CA</option>
							<option>Portland, OR</option>
							<option>Salem, OR</option>
							<option>Austin, TX</option>
							<option>Seattle, WA</option>
						</select>
					</div>
					<div class="form-group">
						<label for="neighborhood">Neighborhood</label>
						<input type="text" class="form-control" id="neighborhood" name="neighborhood" placeholder="neighborhood" value=${neighborhood}>
					</div>
					<div class="form-group">
						<label for="price">Price</label>
						<input type="number" class="form-control" id="price" name="price" placeholder="max" value=${price}>
					</div>
					<div class="form-group">
					  	<label for="rating">Rating</label>
					  	<select class="form-control" id="rating" name="rating">
					  		<option value="none" selected disabled hidden>-- Select --</option> 
							<option>4 starts & above</option>
							<option>3 stars & above</option>
							<option>2 stars & above</option>
							<option>1 star & above</option>
						</select>
					</div>
					<div class="form-group">
					  	<label for="bedrooms">Bedrooms</label>
					  	<select class="form-control" id="bedrooms" name="bedrooms">
					  		<option value="none" selected disabled hidden>-- Select --</option> 
							<option>0+</option>
							<option>1+</option>
							<option>2+</option>
							<option>3+</option>
							<option>4+</option>
							<option>5+</option>
						</select>
					</div>
					<button type="submit" class="btn btn-secondary">Submit</button>
				</form>
				<br />
			</div>
			<%-- search form end --%>
			
			<%-- results --%>
			<div class="col-8">
			
				<h1>Matching Properties</h1>
				
				<div class="row row-cols-1 row-cols-sm-2">
				
					<c:forEach items="${properties}" var="property">
						
						<div class="col-4 mb-4">
							<div class="card h-100">
								<a href="findproperty?propertyid=<c:out value="${property.getPropertyId()}"/>">
									<img src="<c:out value="${property.getPicture()}"/>" class="card-img-top" alt="...">
								</a>
								<div class="card-body">
									<a href="findproperty?propertyid=<c:out value="${property.getPropertyId()}"/>">
										<p class="card-text"><c:out value="${property.getTitle()}"/></p>
									</a>
								</div>
							</div>
						</div>
						
					</c:forEach>
					
				</div>
			
			</div>
			<%-- results end --%>
			
		</div>
	</div>
</body>
</html>
