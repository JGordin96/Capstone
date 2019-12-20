<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/limitedheader.jsp" />
<div class="body-color">
<script type="text/javascript">
	$(document).ready(function() {

		$("form").validate({

			rules : {
				userName : {
					required : true,
					equalTo : "#userName"
				},
				password : {
					required : true,
					equalTo : "#password"
				}
			},
			messages : {
				confirmPassword : {
					equalTo : "Passwords do not match"
				},
				userName : {
					equalTo : "This username does not exist"
				},
				password : {
					equalTo : "The password is not correct"
				}
			},
			errorClass : "error"
		});
	});
</script>

 <!--Load the API from the specified URL
 * The async attribute allows the browser to render the page while the API loads
 * The key parameter will contain your own API key (which is not needed for this tutorial)
 * The callback parameter executes the initMap() function
 -->
 <script async defer
 src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAndynEBomy_GMuBdscawZAJp27744xiI4&callback=initMap">
 </script>
<br>
<p>

<div class="row">
	<div class="col-sm-4"></div>
	<div class="col-sm-4">
		<c:url var="formAction" value="/login" />
		<form method="POST" action="${formAction}">
			<input type="hidden" name="destination" value="${param.destination}" />
			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
			<div class="form-group">
			<h1>Log in to Start Matching!</h1>
				<label for="userName">User Name: </label> <input type="text"
					id="userName" name="userName" placeHolder="User Name"
					class="form-control" />
			</div>
			<div class="form-group">
				<label for="password">Password: </label> <input type="password"
					id="password" name="password" placeHolder="Password"
					class="form-control" />
			</div>
			<a class="button" href="<c:url value="/capstone/users?userName=${user.userName}"/>">
				<button type="submit" class="btn btn-primary">Login</button>
			</a>
		</form>
	</div>
	<div class="col-sm-4"></div>
</div>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp" />