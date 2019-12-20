<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="/WEB-INF/jsp/limitedheader2.jsp" />
<div class="body-color-new">
<script type="text/javascript">
	$(document).ready(function() {
						$.validator.addMethod('capitals', function(thing) {
							return thing.match(/[A-Z]/);
						});
						$.validator.addMethod('lowercase', function(thing) {
							return thing.match(/[a-z]/);
						});
						$("form")
								.validate(
										{

											rules : {
												userName : {
													required : true
												},
												password : {
													required : true,
													minlength : 8,
													capitals : true,
													lowercase : true
												},
												confirmPassword : {
													required : true,
													equalTo : "#password"
												},

											},
											messages : {
												password : {
													minlength : "Password too short, make it at least 8 characters",
													capitals : "Field must contain a capital letter",
													lowercase : "Field must contain a lowercase letter"
												},
												confirmPassword : {
													equalTo : "Passwords do not match"
												}
											},
											errorClass : "error"
										});
					});
</script>

<c:url var="formAction" value="/users/new" />
<form:form method="POST" action="${formAction}" modelAttribute="user">
 	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4"><div class = "newuser-outer-container">
			<div class="form-group"><p><br><h1>Create An Account</h1>
			<div class="linebreak"></div>
			<p>Please enter the following information to create your new account.<p>
				<label for="userName"><span class="bold">User Name:</span> </label>
				<form:input type="text" path="userName" id="userName" name="userName" placeHolder="User Name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="password"><span class="bold">Password: </span></label>
				<form:input type="password" path="password" id="password" name="password" placeHolder="Password" class="form-control" />
			</div>
			<div class="form-group">
				<label for="confirmPassword"><span class="bold">Confirm Password: </span></label>
				<form:input type="password" path="confirmPassword" id="confirmPassword" name="confirmPassword" placeHolder="Re-Type Password" class="form-control" />	
			</div>
			<div class="address">
				<label for="address"><span class="bold">Address:</span> ( Please use the following format: 123 Fake St Apt 42 Detroit MI 12345 )</label>
				<form:input type="text" path="address" id="address" name="address"
					placeHolder="Address" class="form-control" />
			</div>

			<div class="preferences"><br><label for="preferences"><span class="bold">Food Preferences:</label><br>

			<div class="row">
			<div class="col"> <form:checkbox path="preferences" value="Burger" /> Burgers </div>
			<div class="col"> <form:checkbox path="preferences" value="Sandwich" /> Sandwiches</div>
			</div>
			
			<div class="row">
			<div class="col"> <form:checkbox path="preferences" value="Mexican" /> Mexican </div>
			<div class="col"><form:checkbox path="preferences" value="Pizza" /> Pizza</div>
			</div>	
			
			<div class="row">
			<div class="col"><form:checkbox path="preferences" value="Greek" /> Greek </div>
			<div class="col"><form:checkbox path="preferences" value="BBQ" /> Barbecue</div>
			</div>	
			
			<div class="row">
			<div class="col"><form:checkbox path="preferences" value="Sushi" /> Sushi</div>
			<div class="col"><form:checkbox path="preferences" value="Chinese" /> Chinese</div>
			</div>
			
			<div class="row">
			<div class="col"><form:checkbox path="preferences" value="Italian" /> Italian </div>
		    <div class="col"><form:checkbox path="preferences" value="Vegetarian" /> Vegan</div>
			</div>
			</div>	
			

			<div class="mile">
				<label class="mt-3" for="milerange"><br><span class="bold">Range (Miles): </span></label>
				
				<input type="range" id="milerange" 
				 name="milerange" value="0" min="5" max="75" step="5"
			     oninput="amount.value=milerange.value">
				
				<output name="amount" id="amount" for="milerange"><span class="bold">0</span></output>
			</div>
			<button type="submit" class="btn btn-primary">Create User</button>
			<div class="col-sm-4"></div>
		</div>
		</div>
		</div>
</form:form>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />