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

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Room;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.Transaction;

@Stateless
public class DataAccessObject {
	
	@Resource(name = "jdbc/cerioscoop")			//Content Dependency Injection techniek
	private DataSource dataSource;

	public List<Movie> getMovies(){
		final List<Movie> movies = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()) {			//AutoCloseable
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT movie_id, title, movie_description FROM movie");
			while (resultSet.next()) {
				final Movie movie = new MovieBuilder()
						.withMovieId(resultSet.getBigDecimal("movie_id").toBigInteger())
						.withMovieTitle(resultSet.getString("title"))
						.withMovieDescription(resultSet.getString("movie_description"))
						.build();
				movies.add(movie);
			}
			return movies;
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the movie.", e);
	    }
	}
	
	public Movie getMovieByMovieId(int movieId) {
		Movie movie = null;
		try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedstatement = connection
					.prepareStatement("SELECT movie_id, title, movie_description FROM movie WHERE movie_id = ?");
			preparedstatement.setInt(1, movieId);
			ResultSet resultSet = preparedstatement.executeQuery();
			{
				while (resultSet.next()) {
					movie = new MovieBuilder().withMovieId(resultSet.getBigDecimal("movie_id").toBigInteger())
							.withMovieTitle(resultSet.getString("title"))
							.withMovieDescription(resultSet.getString("movie_description")).build();

				}
				return movie;
			}
		} catch (final SQLException e) {
			throw new ServiceException("Something went terribly wrong while retrieving the movie.", e);
		}
	}
	
	public List<Show> getShowsForToday(){
		final List<Show> shows = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT show_id, movie_id, show_date, show_time, available_places FROM show_table WHERE show_date = CURDATE()"); { 

			while (resultSet.next()) {
				final int showId = resultSet.getInt("show_id");
				final int movieId = resultSet.getInt("movie_id");
				final Date showDate = resultSet.getDate("show_date");
				final Time showTime = resultSet.getTime("show_time");
				final int availablePlaces = resultSet.getInt("available_places");
				shows.add(new Show(showId, movieId, showDate, showTime, availablePlaces));
        	}
        return shows;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the ShowingList.", e);
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
					
					transaction.setShow(show);
					transaction.setRoom(room);
					transaction.setMovie(movie);
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

}
