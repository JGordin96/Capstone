<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>

<meta charset="UTF-8">
<title>Restaurant Tinder</title>

<c:url var="bootstrapCss" value="/css/bootstrap3.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<c:url var="siteCss" value="/css/site.css" />

<c:url var="jQueryJs" value="/js/jquery.min.js" />
<c:url var="jqValidateJs" value="/js/jquery.validate.min.js" />
<c:url var="jqvAddMethJs" value="/js/additional-methods.min.js" />
<c:url var="jqTimeagoJs" value="/js/jquery.timeago.js" />
<c:url var="popperJs" value="/js/popper.min.js" />
<c:url var="bootstrapJs" value="/js/bootstrap.min.js" />


<!-- This is the API key for all three Google APIs -->
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBaN-0u2UdyNdHzwJ4GAmh3a0S7uycImuo&callback=initMap"
  type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="${bootstrapCss}">
<link rel="stylesheet" type="text/css" href="${siteCss}">


<script src="${jQueryJs}"></script>
<script src="${jqValidateJs}"></script>
<script src="${jqvAddMethJs}"></script>
<script src="${jqTimeagoJs}"></script>
<script src="${popperJs}"></script>
<script src="${bootstrapJs}"></script>

<link href="https://fonts.googleapis.com/css?family=Varela+Round&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lilita+One&display=swap" rel="stylesheet">

</head>
<nav class="navbar navbar-expand-lg navbar-custom">
 <div class="container">
 
 	<c:set var = "imgPath" value = "http://localhost:8080/capstone/img/resttinder2.png"/>
 
    <a class="navbar-brand" href="/capstone/">
          <img src="${imgPath}" alt="Logo" style="width: 120px; padding-right: 20px;">
        </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button> 
  <div class="collapse navbar-collapse" id="navbarToggler">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
    
      <li class="nav-item active">
        <a class="nav-link" href="/capstone/">Home<span class="sr-only">(current)</span></a>
      </li>
      
      <li class="nav-item active">
        <a class="nav-link" href="/capstone/login">Log In<span class="sr-only">(current)</span></a>
      </li>
     
    </ul>
  </div>
  </div>
</nav>

<body>