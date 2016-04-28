package nl.cerios.cerioscoop.service;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DatabaseConnection {
	private static Connection con;
	
	public static Connection connectionDatabase() {
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver found");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found: "+e);
		}
		String url="jdbc:mysql://localhost/cerioscoopdb";
		String user="root";
		String password="mhx60099";
		 
		try {
			 con=(Connection) DriverManager.getConnection(url, user, password);
			 
			 System.out.println("Connected succesfully");
		}catch (SQLException e) {
			System.out.println("Something went wrong in the connection string");
		}
		return con; 
	}

}
