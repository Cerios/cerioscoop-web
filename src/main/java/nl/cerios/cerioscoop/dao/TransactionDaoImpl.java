package nl.cerios.cerioscoop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Room;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.Transaction;
import nl.cerios.cerioscoop.service.ServiceException;

@Stateless
public class TransactionDaoImpl {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerDaoImpl.class);

	@Resource(name = "jdbc/cerioscoop")
	private DataSource dataSource;
	
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
				LOG.debug("Transaction(s) retrieved.");
				return transactions;
			}
      
        }catch (final SQLException e) {
            throw new ServiceException("Something went wrong while retrieving the transactions.", e);
        }
    }
	
	public void addTransaction(Transaction transaction) {
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO show_transaction (customer_id, show_id, bankaccount, reserved_places, total_price) VALUES (?,?,?,?,?)")) {
				
	        	preparedStatement.setInt(1, transaction.getCustomer().getCustomerId());
	           	preparedStatement.setInt(2, transaction.getShow().getShowId());
	        	preparedStatement.setString(3, transaction.getBankAccount());
	        	preparedStatement.setInt(4, transaction.getReservedChairs());
	        	preparedStatement.setFloat(5, transaction.getTotalPrice());
	        	preparedStatement.executeUpdate();
	        	
	        	System.out.println("Transaction inserted.");
		    }catch (final SQLException e) {
		    	throw new ServiceException("Something went wrong while inserting the transaction items.", e);
		    }
		
	}
	
	public void updateChairsSold(int chairsSold, int showId) {

		String updateSQL = "UPDATE show_table SET available_places = available_places + ? WHERE show_id = ?";

		try {
			final Connection connection = dataSource.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setInt(1, (chairsSold));
			preparedStatement.setInt(2, showId);
			preparedStatement.executeUpdate();

			LOG.debug("Chairs_sold is updated.");
		} catch (final SQLException e) {
			throw new ServiceException("Something went wrong while updating the chairsSoldAmount.", e);
		}
	}
	
}
