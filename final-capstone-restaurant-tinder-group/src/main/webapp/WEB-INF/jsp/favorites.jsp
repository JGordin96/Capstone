<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="header.jsp"%>

<div class = "fav-outer-container">
  <div class = "fav-inner-container">
  <div class="fave-header"><h1>&#9829; Your Matches:</h1></div>
  <div class = "each-item">
<c:forEach items="${favorites }" var="fav">

<div class="fave-name">${fav.name }</div>

<a href="<c:url value="/restaurantDetails/${fav.resId}"/>"><img src="${fav.photoUrl }" class="fave-picture"></a>

<div class="orange-bar"></div>
</c:forEach>
</div>
</div>
</div>

<%@ include file="footer.jsp"%>