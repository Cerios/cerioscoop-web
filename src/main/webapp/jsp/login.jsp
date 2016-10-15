<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

	<!-- Login CSS
   	================================================== -->
	<link rel="stylesheet" href="/cerioscoop-web/css/login.css">
	<link href='/cerioscoop-web/css/shared.css' type='text/css' rel='stylesheet' />
	
</head>
<body>
<div class="login-dropdown">
	Welcome
	<div class="login-form">
		<form name="login" method="post" action="/cerioscoop-web/LoginServlet">
			User Name: 
		<br><input id="loginUsername" type="text" name="txtUserName"/>
		<br>Password: 
		<br><input id="loginPassword" type="password" name="txtPassword"/>
		<br><button id="login-button" type="submit">LOGIN</button>
		</form>
		<a id="forgot-password" href="#">Forgot Password?</a>
	</div>
	<div>
		<br>New Customer
		<br><a id="register-button" href="/cerioscoop-web/RegisterServlet" class="button">REGISTER</a>
	</div>
</div>
</body>
</html>