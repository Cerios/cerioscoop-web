package nl.cerios.cerioscoop.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Room;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.Transaction;

@Stateless
public class CustomerDaoImpl{

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
	        	
	        	System.out.println("Data inserted.");
		    }catch (final SQLException e) {
		    	throw new ServiceException("Something went wrong while inserting the customer items.", e);
		    }
	}
	
	public List<Transaction> getTransactionByUsername(String username){
    	final List<Transaction> transactions = new ArrayList<>();
    	try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedStatement = connection.prepareStatement("SELECT M.title, R.room_name, S.show_date, S.show_time, T.reserved_places FROM show_transaction T INNER JOIN customer C on C.customer_id = T.customer_id INNER JOIN show_table S on S.show_id = T.show_id INNER JOIN movie M on M.movie_id = S.movie_id INNER JOIN room R on R.room_id = S.room_id WHERE C.username = ?");
            preparedStatement.setString(1, username);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		{
				while (resultSet.next()) {
					Transaction transaction = new Transaction();
					transaction.setReservedChairs(resultSet.getInt("T.reserved_places"));
					
					Show show = new Show();
					show.setShowDate(resultSet.getDate("S.show_date"));
					show.setShowTime(resultSet.getTime("S.show_time"));
									
					Room room = new Room();
					room.setRoomName(resultSet.getString("R.room_name"));
					
					Movie movie = new MovieBuilder().withMovieTitle(resultSet.getString("M.title")).build();
					
					show.setRoom(room);
					show.setMovie(movie);
					transaction.setShow(show);
					
					//de class show moet nog gerefactored worden volgens OO en dan zit de movietitle gewoon in het show-object
					
					transactions.add(transaction);
					//todo rest of code
				}
				 System.out.println("Transaction(s) retrieved.");
				return transactions;
			}
      
        }catch (final SQLException e) {
            throw new ServiceException("Something went wrong while retrieving the transactions.", e);
        }
    }
	
	
	public void deleteCustomerByUsername(String username) {
    	try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE username = ?");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
                
            System.out.println("Customer is deleted.");
        }catch (final SQLException e) {
            throw new ServiceException("Something went wrong while deleting the customer items.", e);
        }
    }
	
	public void updateChairsSold(int chairsSold, int showingId) {

		String updateSQL = "UPDATE show_table SET chairs_sold = chairs_sold +? WHERE show_id = ?";

		try {
			final Connection connection = dataSource.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setInt(1, (chairsSold));
			preparedStatement.setInt(2, showingId);
			preparedStatement.executeUpdate();

			System.out.println("Chairs_sold is updated.");
		} catch (final SQLException e) {
			throw new ServiceException("Something went wrong while updating the chairsSoldAmount.", e);
		}
	}
	
	public Show getShowByShowId(int show_id) {
		Show show = new Show();
		String selectSQL = "SELECT movie_title, show_date, show_time FROM show_presentation WHERE show_id = ?";

		try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedstatement = connection.prepareStatement(selectSQL);
			preparedstatement.setInt(1, show_id);
			ResultSet resultSet = preparedstatement.executeQuery();{
				while (resultSet.next()) {
				final int showId = show_id;
				final int movieId = resultSet.getInt("movie_id");
				final int roomId = resultSet.getInt("room_id");
				final Date showDate = resultSet.getDate("show_date");
				final Time showTime = resultSet.getTime("show_time");
				final int availablePlaces = resultSet.getInt("available_places");
				final float showPrice = resultSet.getInt("show_price");
				
	        	show = new Show(showId, movieId, roomId, showDate, showTime, availablePlaces, showPrice);
	        	}
	        return show;
			   }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the first date.", e);
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
