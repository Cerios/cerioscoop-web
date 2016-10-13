<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="nl.cerios.cerioscoop.domain.Customer"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:if test="${usertype ne 'customer'}">
	<c:redirect url="/"/>
</c:if> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='/cerioscoop-web/css/now-showing.css' type='text/css'rel='stylesheet' />
<link href='/cerioscoop-web/css/shared.css' type='text/css' rel='stylesheet' />
<title>Buy Tickets</title>
</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
	</div>
	
	<h1>Buy Tickets</h1>
	<table>
		<thead>
			<th>Movietitle</th>
			<th>Location</th>
			<th>Ticket amount</th>
		</thead>
		<tbody>
				<tr>
					<td> ${show.getMovie().getTitle()}</td>
					<td> ${show.getRoom().getRoomName()}</td>
					<td>
						<form method="GET" action="/cerioscoop-web/PaymentServlet">
						<input id="reserved_places" type="text" name="reserved_places" placeholder="Enter your ticket amount"> 
						<input id="showId"type="hidden" name="showId" value=${show.showId}>
						<input class="button" id="buybutton" type="submit" name="submitit" value="Buy">
						</form>
						<form action="/cerioscoop-web">
							<input class="button" id="cancelbuybutton" type="submit" value="Cancel" />
						</form>
					</td>
				</tr>
		</tbody>
	</table>
</body>
</html>