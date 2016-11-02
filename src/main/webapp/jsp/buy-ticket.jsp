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
			<th>Show price</th>
			<th>Number of ticket</th>
		</thead>
		<tbody>
				<tr>
					<td id="movie-title"> ${show.movie.title}</td>
					<td id="room-name"> ${show.room.roomName}</td>
					<td id="show-price"> ${show.showPrice}</td>
					<td>
						<form method="GET" action="/cerioscoop-web/PaymentServlet">
						<input id="reserved_places" type="text" name="number_of_tickets" placeholder="Enter the number of tickets"> 
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