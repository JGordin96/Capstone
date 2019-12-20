<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBEa0baiiUJhgHR8CkZaNKLjGBhmveiXVY&callback=initMap"
    async defer></script>
 <style>
     
      #map {
        width: 350px;
        height: 300px;
      }
      

    </style>

<div id = "outer-container">


<div class = "detail-inner-container">
<div class = "r-name"><p><h1>${details.name }</h1></div>

<div class = "r-image">
<img src="${details.photoUrl }" class = "rest-image">

<div class="likebutton-container">
<c:url var="formAction" value="/users/match/${user.userName}" />

	
			<c:set var="likebutton" value="http://localhost:8080/capstone/img/tinderheart.png" />
			
			<c:url var="formActionLike" value="/users/favorites/${user.userName}" />
			
			<form method="POST" action="${formActionLike}">			
				<input type="hidden" value=" ${restaurants.resId }" name="resId" />
				<input class="like-button" type="submit"/>
			</form></div>
			
			<c:set var="returnbutton" value="http://localhost:8080/capstone/img/tinderreturn.png" />
			
			<c:url var="matching" value="/users/match/${user.userName}" />
			
			<form method="GET" action="${matching}">
			
				<a href="/restaurantDetails/${restaurants.resId}/${user.userName }">
				
					<input type="image" class="return-button" src="${returnbutton}" />
				</a>
			</form>
			<div class="dislikebutton-container">
			<form method="POST" action="${formActionLike}">
				<input class="dislike-button" type="submit" path="users/match/${userName}" />
			</form></div></div>

<div class = "r-details">
<p><span class="bold">CONTACT:</span> <br>${details.phoneNumber }
<p><span class="bold">HOURS:</span> <br>${details.hours }
<p><span class="bold">FOOD TYPE:</span> <br>${details.cuisine }

<div class = "r-rating">
<span class="bold">INFORMATION:</span>
<c:set var="textInUpperCase" value="${details.ratingText }" /> 
<c:set var="textInUpperCase" value="${fn:toLowerCase(textInUpperCase)}" />
<br>This restaurant has ${textInUpperCase } reviews, with an average rating of ${details.rating }/5</div>

<div class = "r-cost">
<br>On average a meal for two costs $${details.costForTwo }</div><br>
<p>
<span class="bold">ADDRESS:</span> <br>${details.address }



    <div id="map"></div>
    <script>
function initMap() {
  var myLatLng = {lat: 42.365059, lng: -83.073013};

  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 8,
    center: myLatLng
  });

  var marker = new google.maps.Marker({
    position: {lat:${details.latitude}, lng:${details.longitude}},
    map: map
  });
}

</script>  
</div>
</div>
</div>


<c:import url="/WEB-INF/jsp/footer.jsp" />