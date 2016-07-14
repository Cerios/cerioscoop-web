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
	
</head>
<body>

<div class="navbar">
	<ul>
		<li><a id="cerioscoop" href="/cerioscoop-web/index.jsp">CERIOSCOOP</a></li>
		<li class="dropdown">
			<a  id="movies" href="../#movies">MOVIES</a>
  			<div class="dropdown-content">
			    <a id="trailers" href="/cerioscoop-web/jsp/trailers.jsp">TRAILERS</a>
			    <a id="nowshowing" href="/cerioscoop-web/jsp/now-showing.jsp">NOW SHOWING</a>
			    <a id="comingsoon" href="#">COMING SOON</a>
			    <a id="category" href="#">CATEGORY</a>
		  	</div>
		</li>
		<li><a id="about" href="/cerioscoop-web/jsp/about.jsp">ABOUT</a></li>
		<li><a id="team" href="/cerioscoop-web/jsp/team.jsp">TEAM</a></li>
		<li><a id="register" href="/cerioscoop-web/jsp/register.jsp">REGISTER</a></li>
	</ul>
</div>

</body>
</html>