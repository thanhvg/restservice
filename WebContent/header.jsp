<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Travel Experts JSP Website
     Author: Kelsey Alexander
     Date: September 26, 2016 -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Travel Experts</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script type='text/javascript' src='scripts/login.js'></script>
		<script type='text/javascript' src='scripts/account.js'></script>
		<script type='text/javascript' src='scripts/agent.js'></script>
		<script type='text/javascript' src='scripts/agency.js'></script>
		<script type='text/javascript' src='scripts/packages.js'></script>
		<script type='text/javascript' src='scripts/logout.js'></script>
		<script type='text/javascript' src='scripts/purchase.js'></script>
		<script type='text/javascript' src='scripts/register.js'></script>
		<link rel="stylesheet" type="text/css" href="css/menu.css">
		<link rel="stylesheet" type="text/css" href="css/content.css">
		<link rel="stylesheet" type="text/css" href="css/agency.css">
		<link rel="stylesheet" type="text/css" href="css/agent.css">
		<link rel="stylesheet" type="text/css" href="css/package.css">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="css/register.css">
		
		<!-- fonts -->
		<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
		
		<!-- Accordion script -->
		<script>
			function showAccordion() {
				var acc = document.getElementsByClassName("accordion");
				var i;
				
				for (i = 0; i < acc.length; i++) {
				    acc[i].onclick = function(){
				        this.classList.toggle("active");
				        this.nextElementSibling.classList.toggle("show");
				  }
				}
			}
			function home() {
				// TODO: Show welcome message and logo
				document.getElementById("MainContent").innerHTML = 
					"<div class='title' style='font-size: 50pt;letter-spacing:15px;font-family:'Roboto', sans-serif;'>Travel Experts</div>";
			}
       </script>		
	</head>