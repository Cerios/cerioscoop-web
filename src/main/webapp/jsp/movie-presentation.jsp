<%@page import="nl.cerios.cerioscoop.domain.Movie"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Presentation</title>
</head>
<body>
	<div id="navbar">
		<jsp:include page="/jsp/shared/navbar.jsp"></jsp:include>
	</div>
	<% Movie movie = (Movie)request.getAttribute("movie");%>
	<div id="movie-title">MovieTitle: <%=movie.getTitle()%></div><br>
	<div id="movie-description">Description: <%=movie.getDescription()%></div>
	

</body>
</html>