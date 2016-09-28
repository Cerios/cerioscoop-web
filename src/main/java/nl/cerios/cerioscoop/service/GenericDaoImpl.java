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

@Stateless
public class GenericDaoImpl{
	
	@Resource(name = "jdbc/cerioscoop")
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
							.withMovieDescription(resultSet.getString("movie_description"))
							.build();
				}
				return movie;
			}
		} catch (final SQLException e) {
			throw new ServiceException("Something went terribly wrong while retrieving the movie.", e);
		}
	}
	
	
	public List<Show> getShows(){
		final List<Show> shows = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT show_id, movie_id, room_id, show_date, show_time, available_places, show_price FROM show_table"); { 
			while (resultSet.next()) {
				final int showId = resultSet.getInt("show_id");
				final int movieId = resultSet.getInt("movie_id");
				final int roomId = resultSet.getInt("room_id");
				final Date showDate = resultSet.getDate("show_date");
				final Time showTime = resultSet.getTime("show_time");
				final int availablePlaces = resultSet.getInt("available_places");
				final float showPrice = resultSet.getInt("show_price");
				
	        	shows.add(new Show(showId, movieId, roomId, showDate, showTime, availablePlaces, showPrice));
	        	}
	        return shows;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the first date.", e);
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
    
	
    public Show getShowById(int showid){
    	Show show = new Show();
       	String SQL = "SELECT M.title, R.room_name FROM show_table S INNER JOIN movie M on M.movie_id = S.movie_id INNER JOIN room R on R.room_id = S.room_id WHERE S.show_id = ?";
    	try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, showid);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		{
				while (resultSet.next()) {
				Room room = new Room();
				room.setRoomName(resultSet.getString("R.room_name"));

				Movie movie = new MovieBuilder().withMovieTitle(resultSet.getString("M.title")).build();
				
				show.setMovie(movie);
				show.setRoom(room);
 
				}
				 System.out.println("Transaction(s) retrieved.");
				return show;
			}
      
        }catch (final SQLException e) {
            throw new ServiceException("Something went wrong while retrieving the transactions.", e);
        }
    }
}
