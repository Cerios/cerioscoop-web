<%@page import="java.sql.SQLException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DatabaseMetaData"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cerioscoop - Technische informatie</title>
</head>
<body>
 <h1>Manifest file</h1>
<pre>
<%=readManifest()%>
</pre>

 <hr>
<pre>
<%
try {
	String jndi = "jdbc/cerioscoop";
	Context ctx = new InitialContext();
	out.print("lookup datasource "+jndi+" ...");
	DataSource ds = (DataSource) ctx.lookup(jndi);
	out.println("ok");
	out.print("get connection...");
	try (Connection con = ds.getConnection()) {
		out.println("ok. \n>>connection = "+con);
		
		DatabaseMetaData md = con.getMetaData();
		printMetaData(out, md);
		printTables(out, md);
	}
} catch (Exception e) {
    out.println("Exception: "+e);
}
 
 
%>
</pre>

</body>
</html>

<%!
private String readManifest() throws IOException {
	try (InputStream input = getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF")) {
		return new BufferedReader(new InputStreamReader(input))
  					.lines()
  					.collect(Collectors.joining("\n"));
  	} catch (Exception e) {
  		return "Exception while reading manifest: "+e;
  	}
}


private void printMetaData(JspWriter out, DatabaseMetaData md) throws SQLException, IOException {
	out.println("\n*Show some metadata*");
	out.println("database productname = "+md.getDatabaseProductName());
	out.println("database productversion = "+md.getDatabaseProductVersion());
	out.println("driver name = "+md.getDriverName());
	out.println("driver version = "+md.getDriverVersion());
	out.println("jdbc version = "+ md.getJDBCMajorVersion() +"."+md.getJDBCMinorVersion());
}

private void printTables(JspWriter out, DatabaseMetaData md) throws SQLException, IOException {
	
	out.println("\n*Tables*");
	try (ResultSet rs = md.getTables(null, null, "%", null)) {
		while (rs.next()) {
			String table = rs.getString(3);
		  	out.println(" "+table);
		  	try (ResultSet rsCol = md.getColumns(null, null, table, null)) {
			  	while (rsCol.next()) {
					out.println("  - "+rsCol.getString("COLUMN_NAME")
						+" ("+rsCol.getString("TYPE_NAME")+", "+rsCol.getInt("COLUMN_SIZE")+")");
			  	}
			}
		}
	}
}
 %>
 
