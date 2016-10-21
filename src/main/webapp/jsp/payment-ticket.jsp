<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='/cerioscoop-web/css/now-showing.css' type='text/css'rel='stylesheet' />
<link href='/cerioscoop-web/css/shared.css' type='text/css' rel='stylesheet' />
<title>Payment tickets</title>
</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
	</div>
	
	<% //Movie movie = (Movie)request.getAttribute("movie");%>
	
	<h1>Payment tickets</h1>
	<table>
		<thead>
			<th>Total price</th>
			<th>IBAN</th>
		</thead>
		<tbody>
				<tr>
					<td>${total_price}</td>
					<td>
						<form method="POST" action="/cerioscoop-web/PaymentServlet">
						<input id="bankaccount" type="text" name="bankaccount" placeholder="Enter your bankaccount">
						<input class="button" id="paybutton" type="submit" name="submitit" value="Pay"> 
						</form>
					</td>
				</tr>
		</tbody>
	</table>
</body>
</html>