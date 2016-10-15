<%@page import="nl.cerios.cerioscoop.ValueObjects.ShowPresentationVO"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="nl.cerios.cerioscoop.domain.Movie"%>
<%@page import="java.util.Comparator"%>
<%@page import="nl.cerios.cerioscoop.web.ShowException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.List"%>
<%@page import="nl.cerios.cerioscoop.domain.Show"%>
<%@page import="nl.cerios.cerioscoop.service.GeneralService"%>
<%@page import="nl.cerios.cerioscoop.util.DateUtils"%>
<%@page import="nl.cerios.cerioscoop.ValueObjects.ShowsPresentationVO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Now Showing</title>

<!-- Cerioscoop CSS
   ================================================== -->
<link href='/cerioscoop-web/css/now-showing.css' type='text/css'rel='stylesheet' />
<link href='/cerioscoop-web/css/shared.css' type='text/css' rel='stylesheet' />

</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
	</div>
	<h1>Today Showing</h1>
	<table>
		<thead>
			<th>Movietitle</th>
			<th>Movie times</th>
		</thead>
		<tbody>
			<% for(ShowsPresentationVO showsPresentationVO : (List<ShowsPresentationVO>)request.getAttribute("todaysShowsTable")) { %>
			<%if(showsPresentationVO != null) { %>
				<tr>
					<td>
					<form method="GET" action="/cerioscoop-web/MoviePresentationServlet">
					<input type="hidden" name="movieId" value=<%=showsPresentationVO.getMovie().getMovieId().intValue()%>>
					<input id="<%=showsPresentationVO.getMovie().getMovieId().intValue()%>" class="button" type="submit" value="<%=showsPresentationVO.getMovie().getTitle()%>"></form>
					</td>
			
					<% for(ShowPresentationVO show : showsPresentationVO.getShowsPresentationVO()){ %>
					<% if(showsPresentationVO.getShowsPresentationVO() != null){ %>
					<% String soldOut; %>
					<% if(show.getSoldOut()){soldOut = "*";}else{soldOut ="";}{ %>
					<td><a class="button" href="/cerioscoop-web/#"><%=show.getShow().getShowTime()+soldOut%></a></td>
					<% }}} %>
				</tr>
			<% }} %>
		</tbody>
	</table>
	<p>
		Today it is ${todays_date} <br />The first movie today:
		${first_movie_today} <br />That's in ${countdown}
	</p>

</body>
</html>