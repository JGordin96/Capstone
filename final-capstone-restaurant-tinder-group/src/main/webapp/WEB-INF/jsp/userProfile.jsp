<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="/WEB-INF/jsp/header.jsp">
	<c:param name = "imgPath" value = "/img/resttinder2.png"/>
</c:import>

<div id="pref-container">



<c:url var="formAction" value="/users/${user.userName}" />
<div class="inner">
<div class="user">
	
	<h1>Hello, ${user.userName }!</h1>
</div>
<div class="current-status">
	<p>Your current address is <span class="bold">${user.address}</span>, and you are searching
		for restaurants within a ${user.milerange} mile radius.
		
	<p>Your current food preferences are:<p><span class="bold-big"><span class="red">&#9829;</span></span>
	<c:forEach var="preferences" items="${preferences}">
		<span class="bold-big"> ${preferences.name} <span class="red">&#9829;</span></span>
	</c:forEach></div>
	<div class="linebreak">
	</div>

<form:form method="POST" action="${formAction}" modelAttribute="user">


<div class="changes-text">If you would like to make changes to your profile, you may do it below:</div><p>


<div class="linebreak"></div>

	<div class="address">
		<label for="address"><span class="bold">Update Address:</span> ( Please use the following format: 123 Fake St Detroit, MI 12345)</label>
		<p>
		<form:input type="text" path="address" id="address" name="address"
			placeHolder="Address" class="form-control" />
	</div>
	
	<div class="preferences">
		<label for="preferences"><span class="bold">Change your food preferences:</span> </label>
		<br><form:checkbox path="preferences" value="Burger" />
		Burgers
		<br><form:checkbox path="preferences" value="Sandwich" />
		Sandwiches
		<br><form:checkbox path="preferences" value="Mexican" />
		Mexican
		<br><form:checkbox path="preferences" value="Pizza" />
		Pizza
		<br><form:checkbox path="preferences" value="Greek" />
		Greek
		<br><form:checkbox path="preferences" value="BBQ" />
		Barbecue
		<br><form:checkbox path="preferences" value="Sushi" />
		Sushi
		<br><form:checkbox path="preferences" value="Chinese" />
		Chinese
		<br><form:checkbox path="preferences" value="Italian" />
		Italian
		<br><form:checkbox path="preferences" value="Vegetarian" />
		Vegan
	</div>
	
	<div class="mile">

		<label class="mt-3" for="milerange">Range (Miles): </label>

				<label class="mt-3" for="milerange"><span class="bold">Range (Miles): </span></label>

				
		<input type="range" id="milerange" 
				 name="milerange" value="0" min="5" max="75" step="5"
			     oninput="amount.value=milerange.value">
				
				

<output name="amount" id="amount" for="milerange"><span class="bold">0</span></output>

				

	<p><br>
	<button type="submit" class="btn btn-primary">Update</button>
	</form:form>
	</div>


<c:import url="/WEB-INF/jsp/footer.jsp" />