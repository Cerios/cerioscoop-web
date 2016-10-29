package nl.cerios.cerioscoop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.service.ServiceException;

@Stateless
public class CustomerDaoImpl{

	private static final Logger LOG = LoggerFactory.getLogger(CustomerDaoImpl.class);

	@Resource(name = "jdbc/cerioscoop")
	private DataSource dataSource;
	
	
	public List<Customer> getCustomers(){
		final List<Customer> customers = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT customer_id, first_name, last_name, username, password, email FROM customer"); { 

			while (resultSet.next()) {
				final int customerId = resultSet.getInt("customer_id");
				final String firstName = resultSet.getString("first_name");
				final String lastName = resultSet.getString("last_name");
				final String username = resultSet.getString("username");
				final String password = resultSet.getString("password");
				final String email = resultSet.getString("email");

				customers.add(new Customer(customerId, firstName, lastName, username, password, email));
	        	}
	        return customers;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the customers.", e);
	    }
	}
	
	public void registerCustomer(final Customer customer){
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO customer (first_name, last_name, username, password, email) VALUES (?,?,?,?,?)")) {
				
	        	preparedStatement.setString(1, customer.getFirstName());
	        	preparedStatement.setString(2, customer.getLastName());
	        	preparedStatement.setString(3, customer.getUsername());
	        	preparedStatement.setString(4, customer.getPassword());
	        	preparedStatement.setString(5, customer.getEmail());
	        	preparedStatement.executeUpdate();
	        	
	        	LOG.debug("Data inserted.");
		    }catch (final SQLException e) {
		    	throw new ServiceException("Something went wrong while inserting the customer items.", e);
		    }
	}
	
	public void deleteCustomerByUsername(String username) {
    	try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE username = ?");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
                
            LOG.debug("Customer is deleted.");
        }catch (final SQLException e) {
            throw new ServiceException("Something went wrong while deleting the customer items.", e);
        }
    }
	
	public boolean isUniqueUser(String user) {
		String SQL = "SELECT username FROM customer where username=?";
		try (final Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, user);
			ResultSet resultSet = preparedstatement.executeQuery();

			if (!resultSet.next()) {
				return true;
			} else {
				return false;
			}

		} catch (final SQLException e) {
			throw new ServiceException("Something went terribly wrong while retrieving the username.", e);
		}

	}
}
