<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="nl.cerios.cerioscoop.domain.ErrorMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
	</div>

	<h1>Register</h1>

		
	<div>
		<form method="POST" action="/cerioscoop-web/RegisterServlet">
			First name:<br> <input id="firstname" type="text"
				name="firstname" placeholder="Enter your first name" value=${registerAttributes.firstname}> 
				<br>Last name:<br> <input id="lastname" type="text" name="lastname" placeholder="Enter your last name"
				value=${registerAttributes.lastname}>
				<br>Username:<br>
			<input id="username" type="text" name="username"
				placeholder="Enter your username">
				<br>Password:<br>
			<input id="password" type="password" name="password"
				placeholder="Enter your password"> <br>Password:<br>
			<input id="password2" type="password" name="password2"
				placeholder="Enter your password">
				 <br>E-mail:<br>
			<input id="email" type="text" name="email"
				placeholder="Enter your email address" value=${registerAttributes.email}><br>
				 <br> 
			<input id="submit" type="submit" name="submitit" value="Register">
		</form>
		<form action="/cerioscoop-web">
			<input type="submit" value="Cancel" />
		</form>
	</div>
	
	<div style="color:#FF0000">
	<p>
	${errorMessage.firstnameError}<br>
	${errorMessage.lastnameError}<br>
	${errorMessage.usernameError}<br>
	${errorMessage.passwordError}<br>
	${errorMessage.emailError}<br>

	</p>

		</div>
				
</body>
</html>