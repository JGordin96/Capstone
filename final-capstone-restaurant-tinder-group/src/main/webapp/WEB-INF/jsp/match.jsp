
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="header.jsp"%>

<div id="match-outer-container">
	<div class="match-inner-container">
		<c:url var="formAction" value="/users/match/${user.userName}" />
		<c:forEach items="${match }" var="restaurants">

			<div class="r-name2">
				<p>${restaurants.name }
			</div>
			<div class="r-image">
				<a
					href="<c:url value="/restaurantDetails/${restaurants.resId}/${user.userName }"/>">
					<img src="${restaurants.photoUrl }" class="rest-image">
				</a>
				<div class="likebutton-container">
					<c:url var="formActionLike"
						value="/users/favorites/${user.userName}" />
					<c:url var="formActionDisLike"
						value="/users/match/${user.userName}" />
					<c:set var="likebutton"
						value="http://localhost:8080/capstone/img/tinderheart.png" />

					<form method="POST" action="${formActionLike}">
						<input type="hidden" value=" ${restaurants.resId }" name="resId" />
						<input class="like-button" type="submit" />
					</form>
				</div>

				<c:set var="detailsbutton"
					value="http://localhost:8080/capstone/img/tinderdetails.png" />
				<c:url var="detailsform"
					value="/restaurantDetails/${restaurants.resId}/${user.userName }" />
				<form method = "GET" action ="${ detailsform}">
				<a href="/restaurantDetails/${restaurants.resId}/${user.userName }">
					<input type="image" class="details-button" src="${detailsbutton}" />
				</a>
				</form>

				<div class="dislikebutton-container">

					<form method="POST" action="${formActionLike}">
						<input type="hidden" value=" ${restaurants.resId }" name="resId" />
						<input class="dislike-button" type="submit" />
					</form>

				</div>
			</div>


			<div class="match-food-type">
				<p>${restaurants.cuisine}</p>
			</div>
		</c:forEach>

	</div>
</div>
<%@ include file="footer.jsp"%>