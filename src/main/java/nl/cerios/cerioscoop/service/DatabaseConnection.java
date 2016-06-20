package nl.cerios.cerioscoop.service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class DatabaseConnection {
	private static Connection connection;
	private static final Scanner SCANNER = new Scanner(System.in);
	
	public static Connection connectionDatabase() {
		try {		
			Class.forName("com.mysql.jdbc.Driver");
			
			/*TODO Find a real solution for this workaround!
			 * We need something like a properties file.
			 * But one that works on a server
			 * 
			 * note: Ron spoke of a connection pool 
			 * */
			final String url="jdbc:mysql://localhost/cerioscoop_db";
			final String user= "root";
			// Cheap solution to get the pasword out of the git repo without-
			// having to update your files after every pull.
			System.out.println("Enter your password:");
			final String password= SCANNER.nextLine();
			
			for(int i = 0; i < 100; i++)// Enter a lot of white space to hide the password
				System.out.println(" ");
			// End of the cheap solutions
		 
			connection = (Connection) DriverManager.getConnection(url, user, password);
			 
			System.out.println("Connected succesfully");
		}catch (final SQLException | ClassNotFoundException e) {
			System.out.println("Something went wrong in the connection string" + e);
		}
		return connection; 
	}
}
