<%@page import="nl.cerios.cerioscoop.domain.Film"%>
<%@page import="java.util.Comparator"%>
<%@page import="nl.cerios.cerioscoop.web.ShowingException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.List"%>
<%@page import="nl.cerios.cerioscoop.domain.Showing"%>
<%@page import="nl.cerios.cerioscoop.service.ShowingService"%>
<%@page import="nl.cerios.cerioscoop.util.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Now Showing</title>


   	<!-- Main CSS
   	================================================== -->
	<link rel="stylesheet" href="css/style.css">

	<!-- Google web font
   ================================================== -->
	<link href='https://fonts.googleapis.com/css?family=Raleway:700' rel='stylesheet' type='text/css'>

	<!-- Cerioscoop CSS
   ================================================== -->
	<link href='/cerioscoop-web/cerioscoop.css' type='text/css' rel='stylesheet' />
	
</head>



<body>

<!-- Navigation section
================================================== -->
<section class="navbar navbar-fixed-top custom-navbar" role="navigation">
	<div class="container">

		<div class="navbar-header">
			<button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon icon-bar"></span>
				<span class="icon icon-bar"></span>
				<span class="icon icon-bar"></span>
			</button>
			<a href="http://localhost:9080/cerioscoop-web/index.html" class="smoothScroll navbar-brand">CERIOSCOOP</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
            
				<!-- <li><a href="#home" class="smoothScroll">HOME</a></li> -->
                
				<li><a href="http://localhost:9080/cerioscoop-web/FilmShowingServlet" class="smoothScroll">NOW SHOWING</a></li>
				<li><a href="http://localhost:9080/cerioscoop-web/index.html" class="smoothScroll">ABOUT</a></li>
				<li><a href="http://localhost:9080/cerioscoop-web/index.html" class="smoothScroll">TEAM</a></li>
				<li><a href="add-showing.html" class="smoothScroll">ADD SHOWING</a></li>
				<li><a href="add-film.html" class="smoothScroll">ADD FILM</a></li>
				<li><a href="http://localhost:9080/cerioscoop-web/index.html" class="smoothScroll">TRAILERS</a></li>
			</ul>
		</div>

	</div>
</section>

<h1>Now Showing</h1>
<%
final DateUtils dateUtils = new DateUtils();
final ShowingService showingService = new ShowingService();
final List<Showing> showings = showingService.getShowings();
final List<Film> films = showingService.getFilms();
final Showing firstShowing = showingService.getFirstShowingAfterCurrentDate();
java.util.Date showingPremiere = null;
String showingPremiereSqlDatabase = null;
 
showingPremiereSqlDatabase = dateUtils.sqlDatabaseFormat(firstShowing.getPremiereDate())+" "+dateUtils.timeFormat(firstShowing.getPremiereTime());
try {
	showingPremiere = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(showingPremiereSqlDatabase);
} catch (ParseException e) {
	throw new ShowingException("Something went wrong while parsing premiere datum.", e);
}

showings.sort(new Comparator<Showing>() {

	public int compare(final Showing itemL, final Showing itemR) {  //is itemL groter dan itemR? anders bovenaan
		if (itemL.getPremiereDate().before(itemR.getPremiereDate())) {
			return -1;
		} else if (itemL.getPremiereDate().after(itemR.getPremiereDate())) {
			return 1;
		} else {
			return 0;
		}
	}
});		
	


 %>
<table>
<thead><th>Filmtitle</th><th>plays on:</th><th>time</th></thead>
<tbody>
<% for (Showing item : showings) {

if (item.getPremiereDate().after(dateUtils.getCurrentDate())){
%>
<tr>
	<td><%=item.getFilmId()%></td>
	<td><%=dateUtils.format(item.getPremiereDate())%> </td>
	<td><%=dateUtils.timeFormat(item.getPremiereTime())%></td>
</tr>
<% }} %>
</tbody>
</table>
<p>Today it is <%= dateUtils.getDate()%>
<br />The first upcoming film: <%=firstShowing.getFilmId() %> is on <%=dateUtils.format2(firstShowing.getPremiereDate())%> at <%=dateUtils.timeFormat(firstShowing.getPremiereTime())%>
<br />That's in <%= dateUtils.calculateTime(dateUtils.getSecondsBetween(showingPremiere, dateUtils.getCurrentDate())) %></p>

</body>
</html>