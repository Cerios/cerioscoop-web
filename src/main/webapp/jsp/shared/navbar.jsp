<%@page import="nl.cerios.cerioscoop.domain.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Navigation</title>

<!-- Navigation CSS
   	================================================== -->
<link rel="stylesheet" href="/cerioscoop-web/css/navbar.css">
<link href='/cerioscoop-web/css/shared.css' type='text/css'
	rel='stylesheet' />

</head>
<body>

	<%	if(session.getAttribute("user") !=null) { User customer = (User) session.getAttribute("user"); customer.getUsername();} %>

	<div class="navbar">
		<div class="navbar-buttons-wrapper">
			<div class="navbar-button">
				<a href="/cerioscoop-web/index.jsp"> <span
					class="navbar-button-text">CERIOSCOOP</span>
				</a>
			</div>
			<% 
			User user = (User)session.getAttribute("user");
			if (user == null) {
			%>
				<div class="navbar-button">
				<a id="navbar-register" href="/cerioscoop-web/RegisterServlet"> <span
					class="navbar-button-text">REGISTER</span>
				</a>
			</div>

			<div class="navbar-button">
				<a id="navbar-login" href="#Login"> <span
					class="navbar-button-text">LOGIN</span>
				</a>
				<div class="login-menu">
					<jsp:include page="/jsp/login.jsp" />
				</div>
			</div>
			<% } else {
			%>
				<div class="navbar-button">
				<a href="/cerioscoop-web/LogOutServlet"> <span
					class="navbar-button-text">LOGOUT</span>
				</a>
			</div>
			
				<div class="navbar-button">
				<a id="transaction-button" href="/cerioscoop-web/TransactionServlet"> <span
					class="navbar-button-text">TRANSACTIONS</span>
				</a>
			</div>
			<% } %>
		           	<div class="navbar-button"><span>
			<%	if(session.getAttribute("user") !=null) { User customer = (User) session.getAttribute("user"); out.println("Logged in as: "+customer.getUsername());} %>
		</span>
			</div>
		
		</div>
		
		
		
	</div>
</body>
</html>