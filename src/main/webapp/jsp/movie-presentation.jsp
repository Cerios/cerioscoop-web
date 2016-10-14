<%@page import="nl.cerios.cerioscoop.domain.Movie"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href='/cerioscoop-web/css/now-showing.css' type='text/css'rel='stylesheet' />
<link href='/cerioscoop-web/css/shared.css' type='text/css' rel='stylesheet' />

<title>Movie Presentation</title>
</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
	</div>
	
	<h1>Movie presentation</h1>
	
	<table>
		<thead>
			<th>Movietitle</th>
			<th>Description</th>
		</thead>
		<tbody>
		<% Movie movie = (Movie)request.getAttribute("movie");%>
				<tr>
					<td id="movie-title"><%=movie.getTitle()%></td>
					<td id="movie-description"><%=movie.getDescription()%></td>
				</tr>
		</tbody>
	</table>
</body>
</html>