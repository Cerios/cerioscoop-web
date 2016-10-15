<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="nl.cerios.cerioscoop.domain.Transaction"%>
<%@page import="nl.cerios.cerioscoop.domain.Movie"%>
<%@page import="nl.cerios.cerioscoop.domain.Show"%>
<%@page import="nl.cerios.cerioscoop.domain.Customer"%>
<%@page import="java.util.List"%>
<c:if test="${usertype ne 'customer'}">
	<c:redirect url="/"/>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='/cerioscoop-web/css/shared.css' type='text/css'
	rel='stylesheet' />
<title>Insert title here</title>
<link href='/cerioscoop-web/css/now-showing.css' type='text/css'
	rel='stylesheet' />
</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
	</div>
	<h1>Transactions</h1>
	
	<table>
		<thead>
			<th>Movietitle</th>
			<th>Location</th>
			<th>Date</th>
			<th>Time</th>
			<th>Reserved places</th>

		</thead>
		<tbody>
<% for(Transaction transactions : (List<Transaction>)request.getAttribute("transactions")) { %>
		<tr>
			<td id="movietitle"><%=transactions.getMovie().getTitle()%></td>
				<td id="roomname"><%=transactions.getRoom().getRoomName()%></td>
				<td id="showdate"><%=transactions.getShow().getShowDate()%></td>
				<td id="showtime"><%=transactions.getShow().getShowTime()%></td>
				<td id="reservedchairs"><%=transactions.getReservedChairs()%></td>
			</tr>
			
		<%	} %>
		</tbody>
	</table>
	
	<div>
		<form action="/cerioscoop-web/LoginServlet">
			<input type="submit" value="Cancel" />
		</form>
	</div>
</body>
</html>